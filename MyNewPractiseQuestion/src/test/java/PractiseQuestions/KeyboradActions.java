package PractiseQuestions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeyboradActions {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://text-compare.com/");
		
		WebElement textField1 = driver.findElement(By.id("inputText1"));
		WebElement textField2 = driver.findElement(By.id("inputText2"));
		Thread.sleep(10);
		textField1.sendKeys("Hi My name is Radha Gobinda Dash");
//		 Performing copy and paste 
		Actions act = new Actions(driver);
//		Pressing CTRL +A  
		act.keyDown(Keys.CONTROL);
		act.sendKeys("a");
		act.keyUp(Keys.CONTROL);
		act.perform();
		
//		Pressing CTRL+C KeyDown is used to hold the key and Key up is used to release the key 
		act.keyDown(Keys.CONTROL);
		act.sendKeys("c");
		act.keyUp(Keys.CONTROL);
		act.perform();
//		Pressing on tab button navigat eto he next tab 
		act.sendKeys(Keys.TAB);
		act.perform();
//		Pasting on next tab 
		act.keyDown(Keys.CONTROL);
		act.sendKeys("v");
		act.keyUp(Keys.CONTROL);
		act.perform();
//  Writting if else condition to check whether the text is visible on another fiels 
		
		if(textField1.getAttribute("value").equals(textField2.getAttribute("value"))) {
			System.out.println("Text copied");
		}else {
			System.out.println("Text is not copied");
	
		}
		Thread.sleep(5000);

		driver.close();
		
	}

}
