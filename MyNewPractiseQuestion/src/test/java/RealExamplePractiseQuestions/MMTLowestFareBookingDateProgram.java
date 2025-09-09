package RealExamplePractiseQuestions;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
public class MMTLowestFareBookingDateProgram {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Step 1: Close Login Popup if visible
        try {
            WebElement popupClose = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".commonModal__close")));
            popupClose.click();
        } catch (Exception e) {
            System.out.println("Login/signup popup not found.");
        }

        // Step 2: Click on departure calendar
        WebElement departure = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='departure']")));
        departure.click();

        // Step 3: Store all date+price info in map
        Map<String, Integer> datePriceMap = new HashMap<>();

        // Step 4: Get first month's name
        String firstMonthName = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Caption']/div")).getText();
        List<WebElement> firstMonthCells = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Day']//div[@class='dateInnerCell']")));

        for (WebElement cell : firstMonthCells) {
            String[] lines = cell.getText().trim().split("\n");
            if (lines.length >= 2) {
                String date = lines[0].trim();
                String priceStr = lines[1].replace(",", "").trim();
                try {
                    int price = Integer.parseInt(priceStr);
                    datePriceMap.put(date + " " + firstMonthName, price);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price in first month: " + priceStr);
                }
            }
        }

        // Step 5: Get second month's name
        String secondMonthName = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Caption']/div")).getText();
        List<WebElement> secondMonthCells = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Day']//div[@class='dateInnerCell']")));

        for (WebElement cell : secondMonthCells) {
            String[] lines = cell.getText().trim().split("\n");
            if (lines.length >= 2) {
                String date = lines[0].trim();
                String priceStr = lines[1].replace(",", "").trim();
                try {
                    int price = Integer.parseInt(priceStr);
                    datePriceMap.put(date + " " + secondMonthName, price);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price in second month: " + priceStr);
                }
            }
        }

        // Step 6: Find date with lowest price
        String lowestDate = null;
        int lowestPrice = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : datePriceMap.entrySet()) {
            if (entry.getValue() < lowestPrice) {
                lowestPrice = entry.getValue();
                lowestDate = entry.getKey();
            }
        }

        // Step 7: Print result
        if (lowestDate != null) {
            System.out.println("✅ Lowest price is ₹" + lowestPrice + " on " + lowestDate);
        } else {
            System.out.println("⚠️ Could not determine the lowest price.");
        }
        
     // Step 8: Click on the lowest fare date
        boolean clicked = false;

        for (WebElement cell : firstMonthCells) {
            String[] lines = cell.getText().trim().split("\n");
            if (lines.length >= 2) {
                String date = lines[0].trim();
                String fullDate = date + " " + firstMonthName;
                if (fullDate.equals(lowestDate)) {
                	((JavascriptExecutor) driver).executeScript("arguments[0].click();", cell);
                    clicked = true;
                    break;
                }
            }
        }

        if (!clicked) {
            for (WebElement cell : secondMonthCells) {
                String[] lines = cell.getText().trim().split("\n");
                if (lines.length >= 2) {
                    String date = lines[0].trim();
                    String fullDate = date + " " + secondMonthName;
                    if (fullDate.equals(lowestDate)) {
                        cell.click();
                        break;
                    }
                }
            }
        }

        Thread.sleep(50000);
        driver.quit();	

	}

}
