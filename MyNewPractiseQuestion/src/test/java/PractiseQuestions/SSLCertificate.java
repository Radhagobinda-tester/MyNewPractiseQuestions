package PractiseQuestions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SSLCertificate {

	public static void main(String[] args) {
		ChromeOptions option = new ChromeOptions();
		// Activating the ssl certificate 
		option.setAcceptInsecureCerts(true);
		WebDriverManager.chromedriver().setup();
         WebDriver driver = new ChromeDriver(option);
         driver.manage().window().maximize();
         driver.get("https://expired.badssl.com");
	}

}
