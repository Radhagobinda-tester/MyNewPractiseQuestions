package cognizant;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Getting50PercentdicountProductOfshoes {
	WebDriver driver;
	@BeforeMethod
	public void OpenApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		
		
		driver = new ChromeDriver(option);
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		
	}
	
	@Test
	public void getAllfiftyPercentDiscount() {
		
		driver.findElement(By.xpath("//span[@role='button']")).click();
		WebElement ele = driver.findElement(By.xpath("//input[@name='q']"));
				ele.sendKeys("nike");
		WebElement s = driver.findElement(By.xpath("//li[@class='humcQA'][1]//span[text()='nike shoes']"));
		s.click();
		WebElement fityPercent = driver.findElement(By.xpath("//div[text()='Discount']/following::input[@type='checkbox']"));
		fityPercent.click();
		List<WebElement> lists = driver.findElements(By.xpath("//div[@class='nZIRY7']//span[contains(text(),'% off')]"));
		for(WebElement l: lists) {
			String t = l.getText().replaceAll("% off", "").trim();
			int discount = Integer.parseInt(t);
			if(discount>=50) {
				WebElement nameText = l.findElement(By.xpath(".//following::div[contains(text(),'NIKE')]/following::a"));
				String text = nameText.getText();
				 System.out.println("Nike Shoe: " + text + " -> " + discount + "%");
			}
			
			
		}
		
	}
	
	@AfterMethod
	public void closeApp() {
		driver.quit();
	}

}
