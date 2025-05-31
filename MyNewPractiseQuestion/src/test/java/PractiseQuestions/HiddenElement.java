package PractiseQuestions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HiddenElement {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
WebDriver driver =new ChromeDriver();
driver.manage().window().maximize();

driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
driver.findElement(By.xpath("//button[@type='submit']")).click();

driver.findElement(By.xpath("//span[text()='PIM']")).click();
driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[6]/div[1]/div[2]/div[1]/div[1]/div[2]/i[1]")).click();
//driver.findElement(By.xpath("//span[text()='Automaton Tester']")).click();
Thread.sleep(3000);
//  getting the options size
List<WebElement>options =driver.findElements(By.xpath("//div[@role='option']//span"));
System.out.println("The option sizes are : "+ options.size());
// Printing the option sizes 
for(WebElement op :options ) {
	System.out.println(op.getText());
	
}
Thread.sleep(5000);
driver.close();

	}
//  you tube link :-https://www.youtube.com/watch?v=Ei9-O1p5E_4&list=PLUDwpEzHYYLtQzEEEldbjPAR-gnStv4sR&index=26 
}
