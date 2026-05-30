package TCSPractise;

import java.awt.Window;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practiseExamples {
	WebDriver driver;
	@BeforeMethod()
	
	public void openApp() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		
		driver = new ChromeDriver(option);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		
		
	}
	
	@Test	
public void test() throws InterruptedException {
		
		WebElement ele =  driver.findElement(By.id("autocomplete"));
		ele.sendKeys("Bu");
		List<WebElement> lists =  driver.findElements(By.xpath("//ul[@id='ui-id-1']/li"));
		
		for(WebElement list : lists) {
			
			String text = list.getText();
			String org = "Bulgaria";
			if(text.equalsIgnoreCase(org)){
				list.click();
			}
			
		}
		
		WebElement  dd = driver.findElement(By.id("dropdown-class-example"));
		Select s = new Select(dd);
		s.selectByVisibleText("Option3");
		
//		 Selecting window
		 String Parent = driver.getWindowHandle();
		 WebElement open = driver.findElement(By.id("openwindow"));
		 open.click();
		 Set<String> c = driver.getWindowHandles();
		 for(String c1:c) {
			 if(!c1.equalsIgnoreCase(Parent)) {
				 driver.switchTo().window(c1);
				 String title = driver.getTitle();
				 System.out.println(title);
				 Thread.sleep(5000);
				 driver.close();
			 }
			 
		 }
		 driver.switchTo().window(Parent);
		 
	//	 WebElement tab =  driver.findElement(By.id("opentab"));
	//	 tab.click();
	WebElement al = driver.findElement(By.id("alertbtn"));
	al.click();
		 Alert a = driver.switchTo().alert();
	a.accept();
//		 getting the value of dwayne
	
	List<WebElement> rows = driver.findElements(By.xpath("//legend[text()='Web Table Fixed header']/following::table[@id='product']/tbody/tr"));
   int size = rows.size();
   for(int i=0;i<size; i++) {
	   WebElement names= driver.findElement(By.xpath("//legend[text()='Web Table Fixed header']/following::table[@id='product']/tbody/"));
	   String name = names.getText();
	   String org = 
	   
	   
	   
   }
	}
	
	@AfterMethod
public void closeApp() {
		driver.quit();
	}

}
