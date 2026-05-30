package petPooja;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class practiseQuestions {
	WebDriver driver;

	@BeforeMethod()
	public void OpenApp() {

		/*
		 * I am creating a HashMap to store Chrome browser preferences, then I set the
		 * default download directory path. After that, I use ChromeOptions to apply
		 * these preferences and launch the browser with the customized configuration so
		 * that files get downloaded automatically to the specified location without any
		 * popup.
		 */

		HashMap<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", "C:\\Downloads");
		prefs.put("download.prompt_for_download", false);
		prefs.put("safebrowsing.enabled", true);

		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(option);
		driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@Test(enabled = false)
	public void tests() throws InterruptedException, IOException {
//		 Text box programming started 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		/*
		 * WebElement textFullName = driver.findElement(By.id("fullname"));
		 * textFullName.sendKeys("Radha Gobina"); // email field WebElement Emailid =
		 * driver.findElement(By.id("email"));
		 * Emailid.sendKeys("radhagobinda7894@gmail.com");
		 * 
		 * // address field WebElement addressField =
		 * driver.findElement(By.id("address"));
		 * addressField.sendKeys("At-radhey apartment, Thaltej,gujarat, pin - 380059 ");
		 * // password field WebElement passwordField =
		 * driver.findElement(By.id("password"));
		 * passwordField.sendKeys("Password@123"); // Submit WebElement Submit =
		 * driver.findElement(By.xpath("//input[@type='submit']")); Submit.click(); //
		 * Clicking on elements button WebElement elementsButton =
		 * driver.findElement(By.xpath("//h2/button[contains(text(),'Elements')]"));
		 * wait.until(ExpectedConditions.elementToBeClickable(elementsButton)).click();
		 * 
		 * // Click on check box Link WebElement checkBoxlink =
		 * driver.findElement(By.xpath("//a[@href='check-box.php']"));
		 * checkBoxlink.click(); // Clicking on plus icon
		 * 
		 * WebElement plus1icon =
		 * driver.findElement(By.xpath("//li[@id='bs_1']/span[@class='plus']"));
		 * plus1icon.click(); // Clicking on SubFolder 1 WebElement subfolder1icon =
		 * driver.findElement(By.xpath("//li[@id='bf_1']/span[@class='plus']"));
		 * subfolder1icon.click(); // Checked on checkbox WebElement subfolder4icon =
		 * driver.findElement(By.xpath("//input[@id='c_io_4']"));
		 * subfolder4icon.click();
		 * 
		 * // Cicking on sub folder 2 WebElement subfolder2icon =
		 * driver.findElement(By.xpath("//li[@id='bf_2']/span[@class='plus']"));
		 * subfolder2icon.click(); JavascriptExecutor js = (JavascriptExecutor) driver;
		 * 
		 * WebElement subfolder8checkBox =
		 * wait.until(ExpectedConditions.elementToBeClickable(By.id("c_io_8")));
		 * 
		 * js.executeScript("arguments[0].scrollIntoView(true);", subfolder8checkBox);
		 * js.executeScript("arguments[0].click();", subfolder8checkBox);
		 * 
		 * // Getting the button of buttons WebElement buttons =
		 * driver.findElement(By.xpath("//a[@href='buttons.php']")); buttons.click(); //
		 * Clicking on click utton WebElement clickB =
		 * driver.findElement(By.xpath("//button[text()='Click Me']")); clickB.click();
		 * WebElement text = driver.findElement(By.id("welcomeDiv"));
		 * 
		 * String msg = text.getText(); String actual = "You have done a dynamic click";
		 * Assert.assertEquals(msg, actual);
		 * 
		 * // Right click perform by actions class WebElement RightClickB =
		 * driver.findElement(By.xpath("//button[text()='Right Click Me']")); Actions a
		 * = new Actions(driver); a.contextClick(RightClickB).perform();
		 * 
		 * // Double click Actions a = new Actions(driver); WebElement doubleClickB =
		 * driver.findElement(By.xpath("//button[text()='Double Click Me']"));
		 * 
		 * a.doubleClick(doubleClickB).perform();
		 * 
		 * WebElement text1 = driver.findElement(By.id("doublec")); String msg1 =
		 * text1.getText().trim(); String actual1 = "You have Double clicked ".trim();
		 * Assert.assertEquals(msg1, actual1);
		 * 
		 * // Checking links WebElement links =
		 * driver.findElement(By.xpath("//a[@href='links.php']")); links.click(); String
		 * parent = driver.getWindowHandle(); WebElement home =
		 * driver.findElement(By.xpath(
		 * "//a[@href='https://www.tutorialspoint.com/index.htm']")); home.click();
		 * Set<String> child = driver.getWindowHandles(); for(String c : child) {
		 * if(!parent.equalsIgnoreCase(c)) { driver.switchTo().window(c); String URL =
		 * driver.getCurrentUrl(); System.out.println("The url is : "+URL);
		 * driver.close();
		 * 
		 * }
		 * 
		 * }
		 * 
		 * driver.switchTo().window(parent);
		 * 
		 * // Clicking on broken image button WebElement image =
		 * driver.findElement(By.xpath("//a[@href='broken-links.php']")); image.click();
		 * 
		 * // Check broken image
		 * 
		 * WebElement linkB =
		 * driver.findElement(By.xpath("//a[@href='broken-link.php']"));
		 * 
		 * String u = linkB.getAttribute("href");
		 * 
		 * URL l = new URL(u);
		 * 
		 * HttpsURLConnection con = (HttpsURLConnection) l.openConnection();
		 * con.connect(); int responseCode = con.getResponseCode(); if(responseCode
		 * >=400) { System.out.println("The link is a broken link : "+responseCode);
		 * }else { System.out.println("Valid Link: " + responseCode); }
		 * 
		 * 
		 * 
		 * // Clicking on upload file and download file
		 * 
		 * WebElement uploadB =
		 * driver.findElement(By.xpath("//a[@href='upload-download.php']"));
		 * uploadB.click();
		 * 
		 * WebElement ul = driver.findElement(By.id("uploadFile"));
		 * ul.sendKeys("C:\\Users\\ADMIN\\Downloads\\sampleFile.jpeg");
		 * 
		 */
		WebElement uploadB = driver.findElement(By.xpath("//a[@href='upload-download.php']"));
		uploadB.click();
//		 Downloading the file 
		WebElement downloadD = wait.until(ExpectedConditions.elementToBeClickable(By.id("downloadButton")));

		downloadD.click();

		String d = System.getProperty("user.home") + "/Downloads/sampleFile.jpeg";

		File file = new File(d);
		if (file.exists()) {
			System.out.println("File downloaded succesfully");
		} else {
			System.out.println("File does not downloaded succesfully");
		}

	}
	
//	 First create data provider clas which we declare the data 
	@DataProvider(name="data")
	
	public Object[][] getData(){
		
		return new Object[][] {
			{"radha78@gmail.com", "Radha!23"},
			{"testuser01@gmail.com", "Test@123"},
			{"automation.qa@gmail.com", "Auto@456"},
			{"selenium.user@gmail.com", "Selen!789"},
			{"demo.account@gmail.com", "Demo@111"},
			{"qa.engineer@gmail.com", "Qa@2024"},
			{"practice.user@gmail.com", "Practice@99"},
			{"sample.mail@gmail.com", "Sample@321"},
			{"user.testing@gmail.com", "User@555"},
			{"login.check@gmail.com", "Login@777"}
			
		};
	}
	
	
	
	@Test(dataProvider="data")
	public void dataProviderT(String Username, String Password) throws InterruptedException {
		
		WebDriverWait wait =  new  WebDriverWait(driver,Duration.ofSeconds(20));
//		 Clicking on forms
		
		
		WebElement ElementsDD = driver.findElement(By.xpath("//button[normalize-space()='Elements']"));	
		ElementsDD.click();
		Thread.sleep(4000);
		WebElement formDD = driver.findElement(By.xpath("//button[normalize-space()='Forms']"));
		
		formDD.click();
		/*
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].scrollIntoView(true);", formDD);
		wait.until(ExpectedConditions.elementToBeClickable(formDD));
		*/
		
//		 Clicking on login button 
		WebElement  loginDD =  driver.findElement(By.xpath("//a[text()=' Login']"));
		loginDD.click();
//		 Entering User name
		WebElement  userName =  driver.findElement(By.xpath("//input[@placeholder='UserName']"));
		userName.sendKeys(Username);
//		 Entering Password 
		WebElement  PasswordB =  driver.findElement(By.xpath("//input[@placeholder='Password']"));
		PasswordB.sendKeys(Password);
		
//		 Clicking on submit button 
	
		WebElement  submitB =  driver.findElement(By.xpath("//input[@type='submit']"));
		submitB.click();
		
	}

	
	
	@AfterMethod
	public void CloseApp() throws InterruptedException {
		Thread.sleep(10000);
		driver.quit();
		;

	}

}
