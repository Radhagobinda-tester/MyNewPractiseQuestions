package webTablePractise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dynamicTableHandling {
WebDriver driver;
	
	@BeforeMethod
	public void OpenApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.get("https://dynamictable.com/demos/index.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	@Test
	public void extractingData() {
		List<WebElement> list = driver.findElements(By.xpath("//table[@id='dynamicTable']/tbody/tr"));
		int s = list.size();
		System.out.println("The column of the rows "+s);
		for (WebElement row : list) {
			WebElement  country = row.findElement(By.xpath("./td[2]"));
			String c = country.getText();
			String columnName = "VJCZVVWWOOHJTCSXMWP";
			if(columnName.equalsIgnoreCase(c)) {
				String date = country.findElement(By.xpath("./following-sibling::td")).getText();
				 System.out.println(" Date is " + c + " is: " + date);
				
			}
		}
	}
	
	@AfterMethod
	public void closeApp(){
		driver.quit();
	}


}
