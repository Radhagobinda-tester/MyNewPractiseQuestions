package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpicejetcalenderAutomate {

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");
        option.addArguments("--disable-notifications");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(option);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.spicejet.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Click departure
        WebElement dtButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-testid='departure-date-dropdown-label-test-id']")));
        dtButton.click();

        // Values
        String Dyr = "2026";
        String Dmnth = "January";
        String Ddate = "12";

        String Ryr = "2026";
        String Rmnth = "November";
        String Rdate = "17";

        // ================= Departure Month Loop ====================
        while (true) {

            WebElement months = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.cssSelector("div.css-76zvg2.r-homxoj.r-adyw6z.r-1kfrs79")));

            boolean found = false;

            for (WebElement m : months) {
                String[] arr = m.getText().trim().split(" ");

                if (arr.length >= 2) {
                    if (arr[0].equalsIgnoreCase(Dmnth) &&
                            arr[1].equalsIgnoreCase(Dyr)) {

                        found = true;

                        // Select departure date from ANY visible month
                        List<WebElement> depDates = wait.until(
                                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                        By.xpath("//div[contains(@data-testid,'calendar-day-')]")));

                        for (WebElement d : depDates) {
                            if (d.getAttribute("data-testid")
                                    .contains("calendar-day-" + Ddate)) {

                                wait.until(ExpectedConditions.elementToBeClickable(d)).click();
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            if (found) break;

            WebElement next = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@aria-label,'Next')]")));
            next.click();
        }

        // ================= Return Calendar ====================
        WebElement rele = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Select Date']")));
        rele.click();

        while (true) {

            List<WebElement> rmonths = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.cssSelector("div.css-76zvg2.r-homxoj.r-adyw6z.r-1kfrs79")));

            boolean found = false;

            for (WebElement rm : rmonths) {
                String rarr[] = rm.getText().trim().split(" ");

                if (rarr.length >= 2) {
                    if (rarr[0].equalsIgnoreCase(Rmnth) &&
                            rarr[1].equalsIgnoreCase(Ryr)) {
                        found = true;
                        // Select return date from ANY visible calendar block
                        List<WebElement> rDates = wait.until(
                                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                        By.xpath("//div[contains(@data-testid,'calendar-day-')]")));

                        for (WebElement rd : rDates) {
                            if (rd.getAttribute("data-testid")
                                    .contains("calendar-day-" + Rdate)) {

                                wait.until(ExpectedConditions.elementToBeClickable(rd)).click();
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            if (found) break;

            WebElement next = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@aria-label,'Next')]")));
            next.click();
        }

      
    }
}
