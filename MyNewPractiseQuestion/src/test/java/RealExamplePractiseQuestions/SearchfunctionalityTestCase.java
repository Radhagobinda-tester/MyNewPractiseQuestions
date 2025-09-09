package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.DevToolsException;


public class SearchfunctionalityTestCase  {

    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeOptions for anti-bot and new profile
   


        // Setup driver
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(); // IMPORTANT: ChromeDriver for CDP
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            driver.get("https://www.ve3.global/");
            Thread.sleep(5000);
            // Handle cookie banner
            try {
                WebElement disableBtn = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a.seers-cmp-disagree-button"))
                );
                disableBtn.click();
                System.out.println("✅ Clicked 'Disable All' button.");
            } catch (TimeoutException e) {
                System.out.println("⚠️ Cookie banner not found or already handled.");
            }
            Thread.sleep(5000);
            // Click on search icon
            try {
                WebElement searchIcon = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[@class='elementor-icon']"))
                );
                searchIcon.click();
                System.out.println("✅ Search icon clicked.");
            } catch (TimeoutException e) {
                System.out.println("⚠️ Search icon not found.");
                return;
            }

            // Type search query
            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("input.hfe-search-form__input"))
            );
            Thread.sleep(5000);
            searchBox.sendKeys("sap", Keys.ENTER);
            System.out.println("✅ Search submitted");
            Thread.sleep(5000);

            // Wait for search results page to load
            wait.until(ExpectedConditions.urlContains("?s=sap"));

            // Print all links
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement link : links) {
                String text = link.getText().trim();
                String url = link.getAttribute("href");
                System.out.println(text + " --> " + url);
            }

         // After your tests
            driver.manage().deleteAllCookies();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.localStorage.clear();");
            js.executeScript("window.sessionStorage.clear();");

            System.out.println("🧹 Cleared cookies, localStorage, and sessionStorage.");
            Thread.sleep(5000);
           driver.navigate().refresh();
           Thread.sleep(5000);
           driver.navigate().refresh();

        
        }
    }

