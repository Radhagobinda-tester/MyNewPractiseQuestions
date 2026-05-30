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

public class TextBookpractise {
	WebDriver driver;
	
	@BeforeMethod
	public void OpenApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.get("https://testbook.com/static-gk/countries-and-capitals");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	@Test
	public void extractingData() {
		List<WebElement> list = driver.findElements(By.xpath("//strong[contains(text(),'Countries and Capitals of Asia')]/following::div[@class='table-responsive']/table/tbody/tr"));
		int s = list.size();
		System.out.println("The column of the rows "+s);
		for (WebElement row : list) {
			WebElement  country = row.findElement(By.xpath("./td[2]"));
			String c = country.getText();
			String countryName = "Grenada";
			if(countryName.equalsIgnoreCase(c)) {
				String capital = country.findElement(By.xpath("./following::td")).getText();
				 System.out.println("Capital of " + c + " is: " + capital);
				
			}
		}
	}
	
	@AfterMethod
	public void closeApp(){
		driver.quit();
	}

}
