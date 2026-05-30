package RealExamplePractiseQuestions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HousivityDropDown {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get("https://housivity.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            Thread.sleep(2000);
            // Array of all 23 city names
            String[] cities = {
                "Ahmedabad", "Gandhinagar", "Pune", "Vadodara", "Surat",
                "Chennai", "Hyderabad", "Mumbai", "Rajkot", "Thane",
                "Navi Mumbai", "Bengaluru", "Gurgaon", "Bhavnagar", "Kolkata",
                "Mehsana", "Jamnagar", "Noida", "Greater Noida", "Junagadh",
                "Delhi", "Gift City", "Dholera"
            };

            for (String city : cities) {
                try {
                    // Wait and locate the input field
                    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='hidden max-w-[12.75rem] grow-0 md:block']//input[contains(@class,'combobox-input')]")
                    ));
                    Thread.sleep(2000);
                     input.clear();
                    // Clear and type the city
//                    input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE)); // Clear field
                    Thread.sleep(1000);
                    input.sendKeys(city);
                    Thread.sleep(1000);

                    // Press Enter key
                    input.sendKeys(Keys.ENTER);
                    System.out.println("✅ Entered and submitted city: " + city);

                    // Small wait before next iteration
                    Thread.sleep(2000);

                } catch (Exception e) {
                    System.out.println("❌ Could not process city: " + city);
                }
            }

        } finally {
            driver.quit();
        }
    }
}
