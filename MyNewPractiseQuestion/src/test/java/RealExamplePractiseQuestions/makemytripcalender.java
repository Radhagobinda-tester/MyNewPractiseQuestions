package RealExamplePractiseQuestions;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class makemytripcalender {
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Create WebDriverWait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		// Cancel initial pop up
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();



		// Select From city
		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='From']"))).sendKeys("Bangalore");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Bangalore, India']"))).click();

		// Select To city
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']"))).sendKeys("Delhi");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Delhi, India']"))).click();

		// Select departure date
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='departure']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Mon Oct 15 2025']"))).click();

		// Select return date (from the same opened calendar)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Thu Nov 20 2025']"))).click();

	}
}
