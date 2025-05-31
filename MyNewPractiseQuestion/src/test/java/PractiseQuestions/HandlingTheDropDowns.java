package PractiseQuestions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingTheDropDowns {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
//		Handling the select tag options 
		
		WebElement options = driver.findElement(By.id("country"));
		Select s = new Select (options);
		s.selectByVisibleText("India");
		
//		Capturing all the options 
		
		List<WebElement> ptionsAre = s.getOptions();
		System.out.println("The size of the drop down is : "+ ptionsAre.size());
	/*	
	 for(int i =0; i<ptionsAre.size();i++) {
		 
		 System.out.println("The lists are below : "+ ptionsAre.get(i).getText());
		 
	 }
	 */
//		Using advanced forloop
		
	 for(WebElement op : ptionsAre) {
		 
		 System.out.println("The country name : "+ op.getText());
		 
	 }
		
	
	Thread.sleep(3000);
		driver.quit();

	}
//  you tube URl https://www.youtube.com/watch?v=nvOSyRyGAM4&list=PLUDwpEzHYYLtQzEEEldbjPAR-gnStv4sR&index=27
}
