 package PractiseQuestions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenLinkInNewTab {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
     WebDriverManager.chromedriver().setup();
     WebDriver driver=new ChromeDriver();
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     driver.get("https://www.flipkart.com/");
     String tab = Keys.chord(Keys.CONTROL,Keys.RETURN);
    driver.findElement(By.xpath("//a[@title='Cart']")).sendKeys(tab);
     Thread.sleep(5000);
     // Opening a new tab on browser 
     driver.switchTo().newWindow(WindowType.TAB);
     driver.get("https://www.nopcommerce.com/en");
     Thread.sleep(5000);
     
    
	}

}
