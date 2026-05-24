package lamdaTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownhandle {
	
	WebDriver driver;
	@BeforeMethod
	public void OpenApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option  = new ChromeOptions();
		 option.addArguments("--start-maximized");
		 driver = new ChromeDriver(option);
		 driver.get("https://www.testmuai.com/selenium-playground/jquery-dropdown-search-demo/");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 
	}
//	 Handling all dropdowns
	@Test
	public void testing() throws InterruptedException {
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
//		 First dropDown 
		WebElement selectCountryDD  =  driver.findElement(By.cssSelector(".select2-selection__arrow"));
		//WebElement  SelectStateMultiple=  driver.findElement(By.xpath(""));
		
		//WebElement selectAFilecategoryOptions=  driver.findElement(By.xpath(""));
//		Scrolling to that drop down 
//		js.executeScript("arguments[0].scrollIntoView(true)", selectCountryDD);
		wait.until(ExpectedConditions.visibilityOf(selectCountryDD)).click();
//		  Search Field  
//		WebElement textFieldOfSCD = driver.findElement(By.cssSelector(".select2-search__field"));
		
		List<WebElement> list =  driver.findElements(By.xpath("//span[@class='select2-results']/ul/li"));
		
		for(WebElement l : list) {
			String text =  l.getText();
			if(text.equalsIgnoreCase("United States of America")) {
				l.click();
			}
		}
//		 Extracting value from drop down 
		WebElement valueFromdropDown =  driver.findElement(By.xpath("//span[@id='select2-country-container']"));
		String textFromDrop =  valueFromdropDown.getText().trim();
		System.out.println(textFromDrop);
		String expected = "United States of America"; 
		Assert.assertEquals(textFromDrop, expected);
		
//		 Another Operation from drop down 2 
		WebElement  SelectStateMultiple=  driver.findElement(By.cssSelector(".select2-search__field"));
//		js.executeScript("arguments[0].scrollIntoView(true)", SelectStateMultiple);
		wait.until(ExpectedConditions.visibilityOf(SelectStateMultiple)).click();
		ArrayList<String> a =  new ArrayList<>();
		
		List<WebElement> allList =  driver.findElements(By.xpath("//span[@class='select2-results']/ul/li"));
		
		for(WebElement l1 : allList) {
			//l1.clear();
			String text1 =  l1.getText().trim();
			a.add(text1);
			}
			for(String a1 : a) {
				SelectStateMultiple.sendKeys(a1);
				WebElement searchText = driver.findElement(By.xpath("//li[text()='"+a1+"']"));
				wait.until(ExpectedConditions.visibilityOf(searchText)).click();
			}
		
//			Dropdown with disabled values 
			ArrayList<String> b =  new ArrayList<>();	
			List<WebElement> dropdowns = driver.findElements(
				    By.cssSelector(".select2-selection--single")
				);

				// Click second dropdown (index 1)
				dropdowns.get(1).click();
			List<WebElement> listOfallCountr =  driver.findElements(By.xpath("//span[@class='select2-results']/ul/li"));
			for(WebElement l2 :listOfallCountr ) {
				
				String enabled = l2.getAttribute("aria-disabled");
				if(enabled == null || enabled.equals("false")) {
					String t3 =  l2.getText().trim();
					b.add(t3);
					
					
				}
			}
			for(String b2 : b) {
				System.out.println("Enabled country name : "+b2);
			}
			for(String b1 : b) {
				if(b1.equalsIgnoreCase("Northern Mariana Islands")) {
					
					WebElement enterText =  driver.findElement(By.cssSelector(".select2-search__field"));
					enterText.sendKeys(b1);
					WebElement clicktheText = driver.findElement(By.xpath("//li[text()='Northern Mariana Islands']"));
					clicktheText.click();
					
				}
			}
			
			
	}
	
	@AfterMethod
	public void closeApp() throws InterruptedException {
		Thread.sleep(7000);
		driver.quit();
	}

}
