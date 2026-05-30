package infosys;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseAllWebElement {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		WebElement radio1 = driver.findElement(By.xpath("//input[@value='radio1']"));
		wait.until(ExpectedConditions.visibilityOf(radio1)).click();
		WebElement suggestionField = driver.findElement(By.id("autocomplete"));
		wait.until(ExpectedConditions.visibilityOf(suggestionField)).sendKeys("India");
		// Fetch all suggestion <div> elements inside the list
		List<WebElement> suggestions = driver.findElements(
		    By.xpath("//li[@class='ui-menu-item']"));

		System.out.println("Total suggestions: " + suggestions.size());

		// Loop and click "India"
		for (WebElement suggestion : suggestions) {
		    String text = suggestion.getText();
		    System.out.println("Suggestion: " + text);
		    
		    if (text.equalsIgnoreCase("India")) {
		        suggestion.click();
		        break;
		    }
		}
		
//  handling drop down 
		
		WebElement DD = driver.findElement(By.id("dropdown-class-example"));
		Select op = new Select(DD);
		op.selectByValue("option3");
		

//  Mouse hover 
		
		WebElement ms = driver.findElement(By.id("mousehover"));
		Point loc = ms.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true)", ms);
//		js.executeScript("window.scrollBy(" + x + "," + y + ");");
		Actions a = new Actions(driver);
		Thread.sleep(2000);
		a.moveToElement(ms).perform();
		Thread.sleep(2000);
		
		
		// Store parent window
		String parent = driver.getWindowHandle();

		// ---------- Window Handling ----------
		WebElement newWindowopen = driver.findElement(By.id("openwindow"));
		js.executeScript("arguments[0].scrollIntoView(true)", newWindowopen);
		newWindowopen.click();

		// Get handles
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
		    if (!handle.equals(parent)) {
		        driver.switchTo().window(handle);
		        System.out.println("Child Window URL: " + driver.getCurrentUrl());
		        driver.close();  // Close only child window
		    }
		}
		// Switch back to parent
		driver.switchTo().window(parent);

		// ---------- Tab Handling ----------
		WebElement switchTab = driver.findElement(By.id("opentab"));
		js.executeScript("arguments[0].scrollIntoView(true)", switchTab);
		switchTab.click();

		// Get handles again
		Set<String> tabHandles = driver.getWindowHandles();
		for (String handle : tabHandles) {
		    if (!handle.equals(parent)) {
		        driver.switchTo().window(handle);
		        System.out.println("Child Tab URL: " + driver.getCurrentUrl());
		        driver.close();  // Close only child tab
		    }
		}
		// Switch back to parent
		driver.switchTo().window(parent);
		
		WebElement textField = driver.findElement(By.id("name"));
		js.executeScript("arguments[0].scrollIntoView(true)", textField);
		
		wait.until(ExpectedConditions.visibilityOf(textField)).sendKeys("radhagobindadash");
		WebElement alert = driver.findElement(By.id("alertbtn"));
		Thread.sleep(2000);
		alert.click();
		Alert a1 = driver.switchTo().alert();
		Thread.sleep(2000);
		a1.accept();
		
//		 Handling frame ------------------------------================================
		WebElement frame = driver.findElement(By.id("courses-iframe"));
		driver.switchTo().frame(frame);
		
		WebElement hover = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][1]"));
		
		Actions a2 = new Actions(driver);
		a2.moveToElement(hover).perform();
		driver.switchTo().defaultContent();
        Thread.sleep(2000);
//         Take sreen shot code 
        
        TakesScreenshot  ts = (TakesScreenshot) driver;
        File src =ts.getScreenshotAs(OutputType.FILE);
        
        File dest = new File(".\\Screenshot\\homepage3.png");
        
        FileUtils.copyFile(src, dest);
        
        
        
        Thread.sleep(2000);
        
		

		
		
  driver.quit();
	}

}
