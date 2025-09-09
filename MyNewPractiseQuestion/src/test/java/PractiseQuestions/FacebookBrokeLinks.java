package PractiseQuestions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookBrokeLinks {

    public static void main(String[] args) throws InterruptedException {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.facebook.com/business?locate=enus");

        // Wait for page to load dynamic content
        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down to load all dynamic links
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        while (true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(3000); // wait for content to load

            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break; // reached bottom
            }
            lastHeight = newHeight;
        }

        // Use a set to avoid duplicate links
        Set<String> allLinks = new HashSet<>();

        // Fetch all <a> elements
        List<WebElement> hrefElements = driver.findElements(By.tagName("a"));
        System.out.println("Total <a> elements found: " + hrefElements.size());

        for (WebElement link : hrefElements) {
            try {
                // Only visible links
                if (!link.isDisplayed()) continue;

                String hrefValue = link.getAttribute("href");

                if (hrefValue == null || hrefValue.isEmpty()) continue;

                // Convert relative URLs to absolute
                if (hrefValue.startsWith("/")) {
                    hrefValue = "https://www.facebook.com" + hrefValue;
                }

                allLinks.add(hrefValue);

            } catch (Exception e) {
                System.out.println("Error reading link: " + e.getMessage());
            }
        }

        System.out.println("Total unique links to check: " + allLinks.size());

        // Check links using HTTP connection
        int brokenLinkCount = 0;
        for (String urlStr : allLinks) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("HEAD"); // faster than GET
                conn.connect();
                int respCode = conn.getResponseCode();
                if (respCode >= 400) {
                    System.out.println("Broken link: " + urlStr + " (Response code: " + respCode + ")");
                    brokenLinkCount++;
                } else {
                    System.out.println("Valid link: " + urlStr + " (Response code: " + respCode + ")");
                }
            } catch (Exception e) {
                System.out.println("Error checking link: " + urlStr);
            }
        }

        System.out.println("Total broken links: " + brokenLinkCount);

        
    }
}
