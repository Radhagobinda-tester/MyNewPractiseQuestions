package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrollIntoView {

    public static void main(String[] args) {
        // ✅ Setup ChromeDriver automatically using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // ✅ Maximize the browser window for better visibility
        driver.manage().window().maximize();

        // ✅ Launch MakeMyTrip website
        driver.get("https://www.makemytrip.com/");

        // ✅ Set implicit wait (applies globally for all elements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // ✅ Explicit wait for specific conditions
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // ✅ Handle initial popup if present
        try {
            WebElement popupClose = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".commonModal__close"))
            );
            popupClose.click();
        } catch (Exception e) {
            System.out.println("Popup not found or already closed");
        }

        // ✅ Initialize JavaScript Executor for custom scrolling
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ Scroll to "Top Destinations" heading section
        WebElement wondersHeading = driver.findElement(By.cssSelector("p[data-test='topDestinationWrapper_heading']"));
        js.executeScript("arguments[0].scrollIntoView();", wondersHeading);

        // ✅ Capture all destination cards under this section
        List<WebElement> cards = driver.findElements(By.cssSelector("div[data-test='topDestination_item']"));
        System.out.println("Total destinations: " + cards.size());

        // ✅ Extract title for each destination card
        for (WebElement card : cards) {
            try {
                String title = "";

                // ✅ Try extracting title from <p> tag
                try {
                    title = card.findElement(By.cssSelector("p.latoBold.font16.lineHeight22.whiteText")).getText();
                } catch (Exception e) {
                    title = "";
                }

                // ✅ If <p> is empty, fallback to <img alt="">
                if (title.isEmpty()) {
                    try {
                        title = card.findElement(By.tagName("img")).getAttribute("alt");
                    } catch (Exception e) {
                        title = "No title found";
                    }
                }

                // ✅ Print the extracted title
                System.out.println("Title: " + title);
            } catch (Exception e) {
                System.out.println("Could not extract data from one card");
            }
        }

        // ✅ Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println("Scrolled to bottom: " + js.executeScript("return document.documentElement.scrollTop;"));

        // ✅ Scroll back to the top of the page
        js.executeScript("window.scrollTo(0, 0);");
        System.out.println("Scrolled back to top: " + js.executeScript("return document.documentElement.scrollTop;"));

        // ✅ Close browser after execution
        driver.quit();
    }
}
