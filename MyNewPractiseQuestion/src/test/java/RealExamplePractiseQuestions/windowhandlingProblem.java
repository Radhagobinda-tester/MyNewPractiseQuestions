package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/*go to rahul shetty page copy the eemail id and paste on the login page
 * 
 * */

public class windowhandlingProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
 
    driver.manage().window().maximize();
    driver.get("https://rahulshettyacademy.com/loginpagePractise/");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//  Parent window storing 
  String parent = driver.getWindowHandle();
//    Clicking on the link naviagte to next page 
    driver.findElement(By.xpath("//a[contains(text(),'Free Access to')]")).click();
    String text = null; // ✅ Declare the variable outside the loop
    Set<String> child = driver.getWindowHandles();
//    Using for each loop 
    for(String c : child) {
//    	Write a if consition 
    	if(!c.equals(parent)) {
    	driver.switchTo().window(c);
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//p[@class='im-para red'])[1]")));
    	 text = ele.getText().split("at")[1].trim().split(" ")[0];
    	
    	
    	
    }
    	
    	
    }
    driver.switchTo().window(parent);
	 driver.findElement(By.id("username")).sendKeys(text);
	
	}

}
