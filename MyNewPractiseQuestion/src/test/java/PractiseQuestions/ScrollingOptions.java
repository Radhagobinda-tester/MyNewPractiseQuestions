package PractiseQuestions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrollingOptions {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.countries-ofthe-world.com./flags-of-the-world.html");
//		Scrolling to a point 
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(3000);
//		toprint the location 
		System.out.println(js1.executeScript("return window.pageYOffset;"));
//		Moving to that element by its location 
		WebElement ele = driver.findElement(By.xpath("//img[@src='flags-normal/flag-of-El-Salvador.png']"));
		Point loc =ele.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy("+x+","+y+")");
		js.executeScript("arguments[0].scrollIntoView();", ele);
		
		Thread.sleep(3000);
//  Scrolling to the bottom of the page 
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		System.out.println(js2.executeScript("return window.pageYOffset;"));
		Thread.sleep(3000);
// travelling to the top of he page 
		
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		System.out.println(js3.executeScript("return window.pageYOffset;"));
		Thread.sleep(5000);
		
		 // Set the zoom 
	     JavascriptExecutor js4 = (JavascriptExecutor)driver;
	     js4.executeScript("document.body.dtyle.zoom='50%'");

	     Thread.sleep(5000);
	     JavascriptExecutor js5 = (JavascriptExecutor) driver;
	     js5.executeScript("document.body.style.zoom='80%'" );
	     Thread.sleep(5000);
		driver.quit();
		
		

	}

}
