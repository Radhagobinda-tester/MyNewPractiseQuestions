package NewPackege;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class demoQaTool {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--disable--notifications");
		
		WebDriver driver = new ChromeDriver(option);
		
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		WebElement subjectField = driver.findElement(By.id("subjectsInput"));
		wait.until(ExpectedConditions.elementToBeClickable(subjectField)).sendKeys("Hindi");
		
		WebElement hindiText = driver.findElement(By.id("react-select-2-listbox"));
		wait.until(ExpectedConditions.elementToBeClickable(hindiText)).click();
		Thread.sleep(4000);
		
//		City dropdown handling
		WebElement sateteDD= driver.findElement(By.id("react-select-3-input"));
		wait.until(ExpectedConditions.elementToBeClickable(sateteDD)).sendKeys("NCR");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
//		Clicking on ncr 
		
		WebElement selectNcr= driver.findElement(By.id("react-select-3-option-0"));
		
		js.executeScript("arguments[0].scrollIntoView(true)", selectNcr);
		wait.until(ExpectedConditions.elementToBeClickable(selectNcr)).click();
		Thread.sleep(4000);
		WebElement cityDD = driver.findElement(By.id("react-select-4-input"));
		wait.until(ExpectedConditions.elementToBeClickable(cityDD)).click();
		
		WebElement selectDelhi = driver.findElement(By.id("react-select-4-option-0"));
		wait.until(ExpectedConditions.elementToBeClickable(selectDelhi)).click();
		Thread.sleep(4000);
		
		driver.quit();

	}

}
