package PractiseQuestions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownPractise {

	WebDriver driver;

	@BeforeMethod
	public void openApp() {

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");

		driver = new ChromeDriver(option);

		driver.get("https://www.automationtesting.co.uk/datepicker.html");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(enabled=false)
	public void testMethod() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement datePickerButton = driver.findElement(By.id("basicDate"));

		wait.until(ExpectedConditions.elementToBeClickable(datePickerButton)).click();

		String expectedMonth = "May";
		String expectedYear = "1993";
		String expectedDate = "21";

		while (true) {

			// Current month
			WebElement month = driver.findElement(
					By.xpath("//span[contains(@class,'cur-month')]"));
			wait.until(ExpectedConditions.visibilityOf(month));

			String currentMonth = month.getText().trim();
			System.out.println("Current Month : " + currentMonth);
			// Current year
			WebElement year = driver.findElement(
					By.xpath("//input[contains(@class,'cur-year')]"));
			wait.until(ExpectedConditions.visibilityOf(year));
			String currentYear = year.getAttribute("value").trim();

			
			System.out.println("Current Year : " + currentYear);

			// Check month and year
			if (currentMonth.equalsIgnoreCase(expectedMonth)
					&& currentYear.equalsIgnoreCase(expectedYear)) {

				WebElement date = driver.findElement(
						By.xpath("//span[contains(@class,'flatpickr-day') and text()='"
								+ expectedDate + "']"));

				date.click();

				break;

			} else {

				// Previous month button
				WebElement backButton = driver.findElement(
						By.xpath("//span[contains(@class,'flatpickr-prev-month')]"));

				backButton.click();
			}
		}

		// Read selected date from input field
		String selectedDate = datePickerButton.getAttribute("value");

		System.out.println("Selected Date is : " + selectedDate);
	}

	@Test()
	public void fasterExecution() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement datePickerButton = driver.findElement(By.id("basicDate"));

		wait.until(ExpectedConditions.elementToBeClickable(datePickerButton)).click();

		String expectedMonth = "May";
		String expectedYear = "1993";
		String expectedDate = "21";

		while (true) {

			// Current month
			WebElement month = driver.findElement(
					By.xpath("//span[contains(@class,'cur-month')]"));
			wait.until(ExpectedConditions.visibilityOf(month));

			String currentMonth = month.getText().trim();
			System.out.println("Current Month : " + currentMonth);
			// Current year
			WebElement year = driver.findElement(
					By.xpath("//input[contains(@class,'cur-year')]"));
			wait.until(ExpectedConditions.visibilityOf(year));
			String currentYear = year.getAttribute("value").trim();

			
			System.out.println("Current Year : " + currentYear);

			// Check month and year
			if (currentMonth.equalsIgnoreCase(expectedMonth)) {
				if(!currentYear.equalsIgnoreCase(expectedYear)) {
					Actions a = new Actions(driver);
					a.moveToElement(year).perform();
					
					WebElement clickOnUpperArrow = driver.findElement(By.xpath("//span[contains(@class,'arrowDown')]"));
				    wait.until(ExpectedConditions.visibilityOf(clickOnUpperArrow));
				    clickOnUpperArrow.click();
				}else {
				

				WebElement date = driver.findElement(
						By.xpath("//span[contains(@class,'flatpickr-day') and text()='"
								+ expectedDate + "']"));

				date.click();

				break;
				
				}

			} else {

				// Previous month button
				WebElement backButton = driver.findElement(
						By.xpath("//span[contains(@class,'flatpickr-prev-month')]"));

				backButton.click();
			}
		}

		// Read selected date from input field
		String selectedDate = datePickerButton.getAttribute("value");

		System.out.println("Selected Date is : " + selectedDate);
	}
	
	
	@AfterMethod
	public void closeApp() throws InterruptedException {

		Thread.sleep(5000);

		driver.quit();
	}
}