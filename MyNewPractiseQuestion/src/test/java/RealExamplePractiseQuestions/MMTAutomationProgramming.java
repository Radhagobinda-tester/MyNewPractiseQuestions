package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * Objective:
 * Automate selection of travel dates with the lowest fare on https://www.makemytrip.com/
 *
 * Tasks:
 * - Click on departure calendar.
 * - Extract date and price for current and next month.
 * - Identify lowest fare in each and across both months.
 * - Print results.
 */
public class MMTAutomationProgramming {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");

        // Implicit wait for general elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Handle initial popup if present
        try {
            WebElement popupClose = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".commonModal__close")));
            popupClose.click();
        } catch (Exception e) {
            System.out.println("Popup not found or already closed");
        }

        // Wait for and click on departure date calendar
        By departureCalendar = By.xpath("//label[@for='departure']");
        WebElement departureButton = wait.until(ExpectedConditions.elementToBeClickable(departureCalendar));
        departureButton.click();

        // Process current month calendar
        Set<Integer> pricesMonth1 = new HashSet<>();
        By month1Dates = By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Day']//div[@class='dateInnerCell']");
        List<WebElement> firstMonthDates = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(month1Dates));
        System.out.println("Current Month Dates Count: " + firstMonthDates.size());

        for (WebElement cell : firstMonthDates) {
            String[] parts = cell.getText().trim().split("\n");
            if (parts.length >= 2) {
                int price = Integer.parseInt(parts[1].replace(",", "").trim());
                pricesMonth1.add(price);
                System.out.println("Date: " + parts[0] + ", Price: " + price);
            }
        }

        // Get month label for current month
        WebElement monthLabel1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Caption']/div")));
        String monthName1 = monthLabel1.getText();
        int lowestMonth1 = Collections.min(pricesMonth1);
        System.out.println("Lowest Price in " + monthName1 + ": " + lowestMonth1);

        // Process next month calendar
        Set<Integer> pricesMonth2 = new HashSet<>();
        By month2Dates = By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Day']//div[@class='dateInnerCell']");
        List<WebElement> secondMonthDates = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(month2Dates));
        System.out.println("Next Month Dates Count: " + secondMonthDates.size());

        for (WebElement cell : secondMonthDates) {
            String[] parts = cell.getText().trim().split("\n");
            if (parts.length >= 2) {
                int price = Integer.parseInt(parts[1].replace(",", "").trim());
                pricesMonth2.add(price);
                System.out.println("Date: " + parts[0] + ", Price: " + price);
            }
        }

        // Get month label for next month
        WebElement monthLabel2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Caption']/div")));
        String monthName2 = monthLabel2.getText();
        int lowestMonth2 = Collections.min(pricesMonth2);
        System.out.println("Lowest Price in " + monthName2 + ": " + lowestMonth2);

        // Final comparison
        if (lowestMonth1 < lowestMonth2) {
            System.out.println("Lowest fare is in " + monthName1 + ": Rs." + lowestMonth1);
        } else if (lowestMonth2 < lowestMonth1) {
            System.out.println("Lowest fare is in " + monthName2 + ": Rs." + lowestMonth2);
        } else {
            System.out.println("Lowest fare is same in both months: Rs." + lowestMonth1);
        }

        // Close browser
        driver.quit();
    }
}
