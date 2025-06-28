package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightBookingfromMakeMyTrip {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Close login/signup popup
        try {
            WebElement popupClose = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".commonModal__close")));
            popupClose.click();
        } catch (Exception e) {
            System.out.println("Popup not found or already closed");
        }

        // Click departure calendar
        driver.findElement(By.xpath("//label[@for='departure']")).click();

        // Desired month and year
       

        // Navigate calendar until the desired month is visible
        while (true) {
        	 String returnMonthYear = "December 2025";
            List<WebElement> monthElements = driver.findElements(By.xpath("//div[@class='DayPicker-Caption']/div"));

            List<String> monthTexts = monthElements.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            boolean found = monthTexts.stream()
                    .anyMatch(text -> text.equalsIgnoreCase(returnMonthYear));

            if (found) {
                break;
            } else {
                WebElement nextBtn = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
                wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
                
            }
        }
           Thread.sleep(5000);
           // Select departure date
//           WebElement nextBtn = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
//           nextBtn.click();
//           Thread.sleep(5000);
        String startDate = "13";
        List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='dateInnerCell']"));
        for (WebElement d : allDates) {
            String date = d.getText().trim();
            if (date.equals(startDate)) {
            	Thread.sleep(5000);
                d.click();
                break;
            }
        }

        // Click on return date dropdown (if return date selection is part of flow)
        //driver.findElement(By.xpath("//p[@class='latoBlack font12 greyText lineHeight16']")).click();

        // Add logic here for return date selection if needed

        // driver.quit(); // Add at end to close browser
    }
}
