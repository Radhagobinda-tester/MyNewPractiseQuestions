package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpiceJet {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(option);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.get("https://www.spicejet.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Click departure
		WebElement dtButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@data-testid='departure-date-dropdown-label-test-id']")));
		dtButton.click();

		// Values
		String Dyr = "2026";
		String Dmnth = "January";
		String Ddate = "12";

		String Ryr = "2026";
		String Rmnth = "November";
		String Rdate = "17";

		// ================= Departure Month Loop ====================

		WebElement months = wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79' and normalize-space()='January 2026']")));

		String name = months.getText();
		System.out.println(name);
		String[] arr = name.trim().split(" ");

		if (arr.length >= 2) {
			if (arr[0].equalsIgnoreCase(Dmnth) && arr[1].equalsIgnoreCase(Dyr)) {
				WebElement next1 = driver.findElement(By.xpath(
						"//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79' and normalize-space()='January 2026']"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", next1);

			}
		}

//		==============================
		List<WebElement> depDates = driver.findElements(By.xpath(
				"//div[@data-testid='undefined-month-January-2026']//div[contains(@data-testid,'undefined-calendar-day-') and not(contains(@data-testid,'undefined-calendar-day-undefined'))]"));

		System.out.println("Total dates found: " + depDates.size());

		for (WebElement d : depDates) {
			if (d.getText().trim().equals(Ddate)) {
				wait.until(ExpectedConditions.elementToBeClickable(d)).click();
				break;
			}
		}
		// Select departure date from ANY visible month

//        ===return date =============================

		WebElement rele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Select Date']")));
		rele.click();

		// ================= Departure Month Loop ====================

		WebElement months1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79' and normalize-space()='November 2026']")));

		String name1 = months1.getText();
		System.out.println(name1);
		String[] arr1 = name1.trim().split(" ");

		if (arr1.length >= 2) {
			if (arr1[0].equalsIgnoreCase(Dmnth) && arr1[1].equalsIgnoreCase(Dyr)) {
				WebElement next2 = driver.findElement(By.xpath(
						"//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79' and normalize-space()='November 2026']"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", next2);

			}
		}

//    		==============================
		List<WebElement> returnDates = driver.findElements(By.xpath(
				"//div[@data-testid='undefined-month-November-2026']//div[contains(@data-testid,'undefined-calendar-day-') and not(contains(@data-testid,'undefined-calendar-day-undefined'))]"));

		System.out.println("Total dates found: " + depDates.size());

		for (WebElement d1 : returnDates) {
			if (d1.getText().trim().equals(Rdate)) {
				wait.until(ExpectedConditions.elementToBeClickable(d1)).click();
				break;
			}
		}

	}
}
