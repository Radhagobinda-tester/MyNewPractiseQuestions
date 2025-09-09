package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTitlesofiPhoneProducts {

    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Amazon India
        driver.get("https://www.amazon.in");

        // Search for 'iPhone'
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iPhone");
        searchBox.sendKeys(Keys.ENTER);
        WebElement checkboxLabel = driver.findElement(By.xpath("//span[text()='Apple']"));
        checkboxLabel.click();

        // Create a LinkedHashSet to store unique iPhone titles in order
        Set<String> allIphoneNames = new LinkedHashSet<>();

        // Explicit wait for pagination and product titles
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Loop until no more 'Next' page is available
        while (true) {
            // Get all <a> tags from the current page
            List<WebElement> links = driver.findElements(By.tagName("a"));

            // Filter and collect only iPhone product titles
            List<String> iphoneNames = links.stream()
                    .map(WebElement::getText)
                    .filter(name -> name.toLowerCase().contains("iphone"))
                    .filter(name -> !name.trim().isEmpty())
                    .collect(Collectors.toList());

            // Add to the main Set to avoid duplicates
            allIphoneNames.addAll(iphoneNames);

            try {
                // Wait for 'Next' button to appear and be clickable
                WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(text(), 'Next')]")));
     // Scroll to 'Next' button and click using JavaScript (more reliable)
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
 
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);

                // Wait for new page to load (wait for the presence of search results again)
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));

            } catch (TimeoutException | NoSuchElementException e) {
                // 'Next' button not found or not clickable — exit the loop
                break;
            }
        }

        // Print all unique iPhone product titles collected
        allIphoneNames.forEach(System.out::println);

        // Close the browser
        driver.quit();
    }
}
