package QuestinAskedInIntervview;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HiddenElement {
	
	WebDriver driver;
	
	@BeforeMethod
	public void openApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option  = new ChromeOptions();
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.get("http://www.automationtesting.co.uk/hiddenElements.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

	@Test(enabled=false)
	public void testMethod() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
	WebElement hidden = driver.findElement(By.xpath("//p[text()='This paragraph should be hidden.']"));
		//js.executeScript("arguments[0].setAttribute('style','display:block; visibility:visible;')", hidden);
		//js.executeScript("arguments[0].hidden=false", hidden);
	  
	    js.executeScript("arguments[0].style.display='block';", hidden);
	String text = hidden.getText();
		System.out.println("The Hidden text is : "+text);
	}
	
	@Test()
	public void toggleButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		WebElement toggle = driver.findElement(By.xpath("//button[text()='Toggle']"));
		js.executeScript("arguments[0].setAttribute('style','display:block; visibility:visible;')", toggle);
		js.executeScript("arguments[0].scrollIntoView(true)", toggle);
		if(toggle.isDisplayed()) {
			toggle.click();
			System.out.println("The button is now visible");
		}else {
			System.out.println("The button is not visible");
		}
		
		
		Thread.sleep(5000);
		WebElement hText =  driver.findElement(By.id("myDIV"));
		String attributeName =  hText.getAttribute("id");
		String textAfterClick = hText.getText();
		System.out.println("After clicking on toggle : "+textAfterClick+"The Atribute name is : "+attributeName);
	}
	@AfterMethod
	public void closeApp() {
		driver.quit();
	}
}
