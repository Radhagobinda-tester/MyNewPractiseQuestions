package infosys;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import javax.swing.JScrollBar;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseNow {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		/*ChromeOptions option = new ChromeOptions();
		option.addArguments("--Disable--notifications");
		*/
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
         JavascriptExecutor js = (JavascriptExecutor) driver;
         WebElement sg   = driver.findElement(By.id("autocomplete"));
         sg.sendKeys("India");
        List<WebElement> ele = driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
        for(WebElement options :ele) {
        	String text = options.getText();
        	System.out.println(text);
        	if(text.equalsIgnoreCase("India")) {
        		options.click();
        	}
        }
        
        WebElement dd = driver.findElement(By.id("dropdown-class-example"));
         Select s = new Select(dd);
         s.selectByVisibleText("Option3");
//          Opening a new tab 
         String parent = driver.getWindowHandle();
         System.out.print(parent);
        WebElement newW= driver.findElement(By.id("openwindow"));
        js.executeScript("arguments[0].scrollIntoView(true)", newW);
        newW.click();
        Set<String> child = driver.getWindowHandles();
        for(String c: child){
        	if(!c.equals(parent)) {
        	driver.switchTo().window(c);
        	String URL = driver.getCurrentUrl();
        	System.out.println(URL);
        	driver.close();
        	}
        }
        driver.switchTo().window(parent);
        WebElement newTab= driver.findElement(By.id("opentab"));
        js.executeScript("arguments[0].scrollIntoView(true)", newTab);
        newTab.click();
        Set<String> c1 = driver.getWindowHandles();
        for(String c2:c1) {
        	if(!c2.equals(parent)) {
        	driver.switchTo().window(c2);
        	String URL1 = driver.getCurrentUrl();
        	System.out.println(URL1);
        	driver.close();
        	}
        }
        
        driver.switchTo().window(parent);
        WebElement textField = driver.findElement(By.id("name"));
		js.executeScript("arguments[0].scrollIntoView(true)", textField);
		
		wait.until(ExpectedConditions.visibilityOf(textField)).sendKeys("radhagobindadash");
		WebElement alert = driver.findElement(By.id("alertbtn"));
		Thread.sleep(2000);
		alert.click();
        Alert a = driver.switchTo().alert();
        a.accept();
    	Thread.sleep(2000);
        WebElement m = driver.findElement(By.id("mousehover"));
        js.executeScript("arguments[0].scrollIntoView(true)", m);
       Actions hs = new Actions (driver);
       hs.moveToElement(m).perform();
    	Thread.sleep(2000);
    	/*
       List<WebElement> links = driver.findElements(By.tagName("a"));
       for(WebElement l : links ) {
    	   String hrefValue = l.getAttribute("href");
       
       
        URL u = new URL(hrefValue);
        
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.connect();
        if(conn.getResponseCode()>=400) {
        	System.out.println(hrefValue);
        }else {
        	System.out.println(hrefValue);
        }
       }
       */
        WebElement frame = driver.findElement(By.id("courses-iframe"));
        driver.switchTo().frame(frame);
        WebElement more = driver.findElement(By.xpath("//a[@class='dropdown-toggle']"));
         js.executeScript("arguments[0].scrollIntoView(true)", more);
        Actions s1 = (Actions) driver;
        s1.moveToElement(more).perform();
        Thread.sleep(30000);
        
        driver.switchTo().defaultContent();
        
        
        
       
        
	}
	

}
