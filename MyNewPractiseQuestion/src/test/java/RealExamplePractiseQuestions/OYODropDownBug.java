package RealExamplePractiseQuestions;

// Importing required packages
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OYODropDownBug {

    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver using WebDriverManager (automatically downloads driver if not present)
        WebDriverManager.chromedriver().setup();

        // Configure Chrome browser options
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");  // Start browser maximized
        option.addArguments("--disable-notifications");  // Disable popup notifications

        // Launch Chrome browser with specified options
        WebDriver driver = new ChromeDriver(option);

        // Navigate to OYO website
        driver.get("https://www.oyorooms.com/");

        // Implicit wait for 50 seconds (waits for elements to appear before throwing exception)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        // Explicit wait object (not used here but initialized)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        // Click on the date picker button to open calendar
        WebElement dtPickerBtn = driver.findElement(By.xpath(
            "//div[@class='oyo-row oyo-row--no-spacing datePickerDesktop datePickerDesktop--home']"));
        dtPickerBtn.click();

        // Thread sleep to allow calendar to appear (better replaced with explicit wait)
        Thread.sleep(2000);

        // Locate the year dropdown in the calendar (second select element for month/year)
        WebElement yearLabel = driver.findElement(
            By.xpath("(//select[@class='DateRangePicker__MonthHeaderSelect'])[2]"));

        // Wrap it in Select object to easily access options
        Select year = new Select(yearLabel);

        // Get all the options available in the year dropdown
        List<WebElement> yearOptions = year.getOptions();

        // Flags to check if 2025 and 2026 exist
        boolean year2025Found = false;
        boolean year2026Found = false;

        System.out.println("Year dropdown options:");
        for (WebElement options : yearOptions) {
            String text = options.getText().trim();  // Get text of each option
            System.out.println(text);  // Print each year option

            // Check if 2025 is present
            if (text.equals("2025")) {
                year2025Found = true;
            }

            // Check if 2026 is present
            if (text.equals("2026")) {
                year2026Found = true;
            }
        }

        // Fail test if either 2025 or 2026 is missing
        if (!year2025Found || !year2026Found) {
            System.out.print("Year dropdown does not contain both 2025 and 2026!");
        }else {
            System.out.println("Both 2025 and 2026 are present in the dropdown.");
        }

        // Close the browser
        driver.quit();
    }
}
