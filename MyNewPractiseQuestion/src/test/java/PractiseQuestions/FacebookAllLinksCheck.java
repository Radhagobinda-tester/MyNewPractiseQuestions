package PractiseQuestions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookAllLinksCheck {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://darshanpatelwp.com/");

        // Wait for page to load dynamic content
        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to bottom (to load dynamic links)
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        while (true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(3000);

            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }

        // Use Set to store unique links
        Set<String> uniqueLinks = new LinkedHashSet<>();

        // Fetch all <a> elements
        List<WebElement> hrefElements = driver.findElements(By.tagName("a"));
        System.out.println("Total <a> elements found: " + hrefElements.size());

        for (WebElement link : hrefElements) {
            try {
                String hrefValue = link.getAttribute("href");

                if (hrefValue == null || hrefValue.isEmpty()) {
                    continue; // skip empty
                }

                // Convert relative URLs to absolute
                if (hrefValue.startsWith("/")) {
                    hrefValue = "https://darshanpatelwp.com" + hrefValue;
                }

                // Skip mailto and javascript links
                if (hrefValue.startsWith("mailto:") || hrefValue.startsWith("javascript:")) {
                    continue;
                }

                uniqueLinks.add(hrefValue);

            } catch (Exception e) {
                System.out.println("Error reading link: " + e.getMessage());
            }
        }

        System.out.println("Total unique links to check: " + uniqueLinks.size());

        // Check links using HTTP connection
        int brokenLinkCount = 0;
        for (String urlStr : uniqueLinks) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("HEAD");
                conn.connect();
                int respCode = conn.getResponseCode();

                if (respCode == 200) {
                    System.out.println("Valid link: " + urlStr + " (Response code: " + respCode + ")");
                } else if (respCode == 999 && urlStr.contains("linkedin.com")) {
                    System.out.println("Protected link (LinkedIn blocked): " + urlStr);
                } else if (respCode >= 400) {
                    System.out.println("Broken link: " + urlStr + " (Response code: " + respCode + ")");
                    brokenLinkCount++;
                } else {
                    System.out.println("Other response: " + urlStr + " (Response code: " + respCode + ")");
                }
            } catch (Exception e) {
                System.out.println("Error checking link: " + urlStr + " -> " + e.getMessage());
                brokenLinkCount++;
            }
        }

        System.out.println("Total broken links: " + brokenLinkCount);

        driver.quit();
    }
}
