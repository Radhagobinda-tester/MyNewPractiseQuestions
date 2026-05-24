package PractiseQuestions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigatingToSelectedWidow {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions option  = new ChromeOptions();
		option.addArguments("--disable-notifications");
		option.addArguments("--start-maximized");
		
		WebDriver driver = new ChromeDriver(option);
		
		driver.get("https://www.lenskart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
//		 Real logic write 
		String parent = driver.getWindowHandle();
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> aboutUsLinks = driver.findElements(By.xpath("//h2[text()='About Us']/following::ul[1]/li/a"));
		for(WebElement links : aboutUsLinks ) {
			js.executeScript("arguments[0].scrollIntoView(true);", links);
			Actions a = new Actions(driver);
			a.keyDown(Keys.CONTROL).click(links).keyUp(Keys.CONTROL).build().perform();	
		}
		
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		
		for(int i =1; i<tabs.size(); i++) {
			
			
			driver.switchTo().window(tabs.get(i));
			 System.out.println("Title: " + driver.getTitle());
			 driver.close();
			 
			
		}
		
		driver.switchTo().window(parent);
		Thread.sleep(3000);
		
		driver.quit();
		
		
		
		
		

	}

}
