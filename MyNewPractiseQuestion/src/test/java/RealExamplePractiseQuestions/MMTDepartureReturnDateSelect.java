package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MMTDepartureReturnDateSelect {

    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        // Maximize browser window
        driver.manage().window().maximize();
        
        // Open MakeMyTrip website
        driver.get("https://www.makemytrip.com/");
        
        // Apply implicit wait for element availability
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Explicit wait (used for specific dynamic elements)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // ===== Close Login/Signup Popup if Present =====
        try {
            WebElement popupClose = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector(".commonModal__close")));
            popupClose.click();
        } catch (Exception e) {
            System.out.println("Popup not found or already closed");
        }

        // ===== Open Departure Calendar =====
        WebElement departureLabel = wait.until(ExpectedConditions
            .elementToBeClickable(By.xpath("//label[@for='departure']")));
        departureLabel.click();

        // Target Departure Date
        String depMonth = "October";
        String depYear = "2025";
        String depDate = "15";

        // Loop until required Departure Month-Year appears (Left Calendar)
        while (true) {
            WebElement header = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")));

            String headerText = header.getText().trim();
            String[] arr = headerText.split(" ");

            if (arr.length >= 2) {
                String mon = arr[0];
                String yr = arr[1];

                if (depMonth.equalsIgnoreCase(mon) && depYear.equalsIgnoreCase(yr)) {
                    break; // Found the required month-year
                } else {
                    WebElement nextBtn = wait.until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//span[@aria-label='Next Month']")));
                    nextBtn.click();
                }
            }
        }

        // Select Departure Date
        List<WebElement> depDates = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.xpath("(//div[@class='DayPicker-Month'])[1]//div[@class='dateInnerCell']/p[1]")));

        for (WebElement d : depDates) {
            if (d.getText().trim().equals(depDate)) {
                wait.until(ExpectedConditions.elementToBeClickable(d)).click();
                break;
            }
        }

        // ===== Open Return Calendar =====
        WebElement returnLabel = wait.until(ExpectedConditions
            .elementToBeClickable(By.xpath("//span[@class='lbl_input appendBottom10' and text()='Return']")));
        returnLabel.click();

        // Target Return Date
        String retMonth = "November";
        String retYear = "2025";
        String retDate = "15";

        // Loop until required Return Month-Year appears (Right Calendar)
        while (true) {
            WebElement headerDiv = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("(//div[@class='DayPicker-Caption']/div)[2]")));

            String mon1 = headerDiv.getText().replaceAll("[0-9]", "").trim(); // Extract month
            String yr1 = headerDiv.findElement(By.tagName("span")).getText().trim(); // Extract year

            System.out.println("Current calendar header = " + mon1 + " " + yr1);

            if (retMonth.equalsIgnoreCase(mon1) && retYear.equalsIgnoreCase(yr1)) {
                break;
            } else {
                WebElement nextBtn1 = wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//span[@aria-label='Next Month']")));
                nextBtn1.click();
            }
        }

        // Select Return Date
        List<WebElement> retDates = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.xpath("(//div[@class='DayPicker-Month'])[2]//div[@class='dateInnerCell']/p[1]")));

        boolean dateClicked = false;
        for (WebElement d1 : retDates) {
            if (d1.getText().trim().equals(retDate)) {
                wait.until(ExpectedConditions.elementToBeClickable(d1)).click();
                dateClicked = true;
                break;
            }
        }

       // Close the browser
        driver.quit();
    }
}
