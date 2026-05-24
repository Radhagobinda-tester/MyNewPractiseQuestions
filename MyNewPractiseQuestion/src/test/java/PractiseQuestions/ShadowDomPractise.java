package PractiseQuestions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDomPractise {
	
	WebDriver driver;
	@BeforeMethod
	public void OpenApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option  = new ChromeOptions();
		 option.addArguments("--start-maximized");
		 driver = new ChromeDriver(option);
		 driver.get("https://www.testmuai.com/selenium-playground/shadow-dom/");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 
	}
//	 Handling all dropdowns
	@Test
	public void testing() throws InterruptedException {
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		 gettig the shadow root 
		
		

		WebElement host = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(By.id("shadow_host"))
		);

		SearchContext shadow = host.getShadowRoot();
		
		
		WebElement field = shadow.findElement(By.cssSelector("input[placeholder='Name']"));
		field.sendKeys("Radha");
		WebElement emailField = shadow.findElement(By.cssSelector("input[placeholder='Email']"));
		emailField.sendKeys("radhagobinda7894@gmail.com");
		
		WebElement  range = shadow.findElement(By.cssSelector("input[type='range']"));
		Point loc = range.getLocation();  // ✅ correct
		int x = loc.getX(); 
		int y  = loc.getY();
		Actions a = new Actions(driver);
		a.clickAndHold(range).moveByOffset(50, 0).release().perform();
		
		
		 
			
			
	}
	
	@AfterMethod
	public void closeApp() throws InterruptedException {
		Thread.sleep(7000);
		driver.quit();
	}


}
