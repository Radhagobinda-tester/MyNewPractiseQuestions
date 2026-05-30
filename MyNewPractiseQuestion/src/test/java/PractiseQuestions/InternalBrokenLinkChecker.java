package PractiseQuestions;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InternalBrokenLinkChecker {

    static WebDriver driver;
    static Set<String> visitedPages = new HashSet<>();      // For crawling
    static Set<String> allLinks = new LinkedHashSet<>();    // All collected links
    static PrintWriter reportWriter;

    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Create CSV report file
        reportWriter = new PrintWriter(new FileWriter("BrokenLinksReport.csv"));
        reportWriter.println("Page URL, Link, Status, ResponseCode");

        String baseUrl = "https://darshanpatelwp.com/";

        // Phase 1: Crawl site and collect all links
        crawlPage(baseUrl, baseUrl);

        System.out.println("\nCollected " + allLinks.size() + " unique links.\n");

        // Phase 2: Check all collected links individually
        for (String href : allLinks) {
            checkLink(href);
        }

        System.out.println("================================");
        System.out.println("📊 Report saved as BrokenLinksReport.csv");

        driver.quit();
        reportWriter.close();
    }

    // Recursive crawler to collect links
    public static void crawlPage(String url, String baseUrl) {
        if (visitedPages.contains(url)) return;

        System.out.println("🔎 Crawling page: " + url);
        visitedPages.add(url);

        try {
            driver.get(url);

            // Wait until page is loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            Thread.sleep(2000); // allow JS to render

            // Scroll to bottom to load lazy elements like "Read More" buttons
            JavascriptExecutor js = (JavascriptExecutor) driver;
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight;");
            while (true) {
                js.executeScript("window.scrollBy(0, 500);");
                Thread.sleep(1000);
                long newHeight = (long) js.executeScript("return document.body.scrollHeight;");
                if (newHeight == lastHeight) break;
                lastHeight = newHeight;
            }

            // Collect all <a> links after lazy load
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                if (href == null || href.isEmpty()) continue;

                // Convert relative URLs
                if (href.startsWith("/")) {
                    href = baseUrl + href.substring(1);
                }

                // Skip mailto:, javascript:
                if (href.startsWith("mailto:") || href.startsWith("javascript:")) continue;

                // Add to allLinks
                allLinks.add(href);

                // Crawl internal pages
                if (href.startsWith(baseUrl) && !visitedPages.contains(href)) {
                    crawlPage(href, baseUrl);
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error crawling page: " + url + " -> " + e.getMessage());
        }
    }

    // Check a single link via HTTP
    public static void checkLink(String href) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(href).openConnection();
            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            int respCode = conn.getResponseCode();

            String status;
            if (respCode == 200) {
                status = "VALID";
                System.out.println("✅ Valid link: " + href);
            } else if (respCode >= 400) {
                status = "BROKEN";
                System.out.println("❌ Broken link: " + href + " (Code: " + respCode + ")");
            } else {
                status = "OTHER";
                System.out.println("ℹ️ Other response: " + href + " (Code: " + respCode + ")");
            }

            reportWriter.println(href + "," + href + "," + status + "," + respCode);

        } catch (Exception e) {
            System.out.println("❌ Error checking link: " + href + " -> " + e.getMessage());
            reportWriter.println(href + "," + href + ",ERROR," + e.getMessage().replace(",", " "));
        }
    }
}
