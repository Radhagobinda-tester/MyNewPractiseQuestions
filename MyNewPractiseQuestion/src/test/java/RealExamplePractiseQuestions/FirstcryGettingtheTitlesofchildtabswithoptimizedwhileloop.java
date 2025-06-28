package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstcryGettingtheTitlesofchildtabswithoptimizedwhileloop {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();	
		  WebDriver driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.get("https://www.firstcry.com/");
//		  Moving to the footer element
		  WebElement ele = driver.findElement(By.xpath("//div[@class='fctr liquid_header_main']"));
		  Point p = ele.getLocation();
		  int x = p.getX();
		  int y = p.getY();
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("window.scrollBy("+x+","+y+")");
		  
		/*
		  JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(5000);
			*/
		  //   Limiting the links
		  WebElement linkF= driver.findElement(By.xpath("//div[text()='CATEGORIES']/following-sibling::div[@class='lcr cnt'][1]"));

		  List<WebElement> ele2 =linkF.findElements(By.tagName("a"));
		  System.out.println("The link size is : "+ele2.size());
		  String parent = driver.getWindowHandle();
		  for(WebElement ele1 : ele2) {
			  String hrefLinks = ele1.getAttribute("href");
//			  Opening all links on a new window at a short 
			 String linkClick= Keys.chord(Keys.CONTROL,Keys.ENTER);
//			 Clicking on each link
			 ele1.sendKeys(linkClick);
			 Thread.sleep(1000); 
		  }
//			 Child window handle 
			 Set<String>child = driver.getWindowHandles();
			 for(String c : child) {
				 if(!c.equals(parent)) {
					 driver.switchTo().window(c);
					  System.out.println("Title: " + driver.getTitle());
					 
				 }
			 }
			 
			  
		  driver.switchTo().window(parent);
		
	}

}
