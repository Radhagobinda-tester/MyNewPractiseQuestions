package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickOnlocationhousivity {

    public static void main(String[] args) throws InterruptedException {

        // ---------------- Chrome setup ----------------
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ---------------- Open website ----------------
        driver.get("https://housivity.com/");

        // ---------------- Select Bengaluru ----------------
        WebElement cityButton = driver.findElement(By.id("headlessui-combobox-button-_R_jdplkav9vb_"));
        cityButton.click();
        WebElement bengaluru = driver.findElement(By.xpath("//span[normalize-space()='Bengaluru']"));
        wait.until(ExpectedConditions.elementToBeClickable(bengaluru)).click();

        Thread.sleep(5000); // wait for dropdown to load

        // ---------------- Set to store scraped locations ----------------
        Set<String> locationsSet = new HashSet<>();
        boolean clickedWhitefield = false;
        boolean hasNext = true;

        while (hasNext) {
            // Re-find elements to avoid stale references
            List<WebElement> locations = driver.findElements(By.xpath(
                    "//address[@class='font_reg_9 lg:font_med_8 leading-[120%] text-C_909090 line-clamp-2 max-h-8 md:line-clamp-none md:h-auto md:w-full md:truncate not-italic']"
            ));

            for (WebElement loc : locations) {
                if (loc.isDisplayed()) {
                    String locText = loc.getText().trim();

                    // Scroll element into view with offset
                    int locY = loc.getLocation().getY();
                    js.executeScript("window.scrollTo(0, arguments[0]-150);", locY);
                    Thread.sleep(300);

                   
                 // Click Whitefield if found
                    if (locText.toLowerCase().contains("Whitefield, Bengaluru")) {
                        loc.click();
                        clickedWhitefield = true;
                        break;
                    } else {
                        locationsSet.add(locText);
                    }
                }
            }

            // Click "Next Slide" button if not clicked Whitefield
            if (hasNext) {
                try {
                    WebElement nextBtn = driver.findElement(By.xpath(
                            "//img[@alt='Next Slide' and contains(@class,'cursor-pointer')]"));
                    js.executeScript("window.scrollTo(0, arguments[0]-100);", nextBtn.getLocation().getY());
                    js.executeScript("arguments[0].click();", nextBtn);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    hasNext = false; // no more slides
                }
            }
        }

        // ---------------- Print all other locations ----------------
        System.out.println("Other locations found:");
        locationsSet.forEach(System.out::println);

        driver.quit();
    }
}
