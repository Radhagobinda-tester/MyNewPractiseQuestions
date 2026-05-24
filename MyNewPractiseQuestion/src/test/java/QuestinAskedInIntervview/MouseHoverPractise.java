package QuestinAskedInIntervview;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseHoverPractise {
	
	WebDriver driver;
	
	@BeforeMethod
	public void openApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option  = new ChromeOptions();
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.get("https://www.automationtesting.co.uk/mouse.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	@Test()
	public void testMethod() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
	WebElement red = driver.findElement(By.xpath("//div[@class='out overout']/div"));
	  // outside area
 
    WebElement countLeft = driver.findElement(By.id("counterOne"));
	js.executeScript("window.scrollBy(0,100)");
	
	WebElement grey = driver.findElement(By.xpath("//div[@class='out overout']/span"));
    WebElement white = driver.findElement(By.xpath("//div[@class='out overout']"));
	/*
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter tthe number of time you want to mous hover : ");
	int n = sc.nextInt();
	*/
	 int count =0;
	 for(int i =0; i<5; i++) {
		 Actions a = new Actions(driver);
		 a.moveToElement(white).perform();
		 Thread.sleep(500);
	  a.moveToElement(grey).perform();
		    Thread.sleep(1000);

		    a.moveToElement(red).perform();
		    Thread.sleep(1000);
		 count += 1;
		 count++;
	 } 
		
		 String counter = countLeft.getText();
		 System.out.println("The counter is : "+counter);
//		 Assert.assertEquals(counter, count);
		 System.out.println("The count : "+count);
		 
		 
	 }
	
	
	@AfterMethod
	public void closeApp() {
		driver.quit();
	}

}
