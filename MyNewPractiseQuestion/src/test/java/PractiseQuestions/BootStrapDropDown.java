package PractiseQuestions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BootStrapDropDown {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
		driver.findElement(By.xpath("//b[@class='caret']")).click();

		List<WebElement> option = driver.findElements(By.xpath("//ul[contains(@class,multiselect)]//label"));
//		Selecting single options 
		//driver.findElement(By.className("//input[@value='jQuery']")).click();
		System.out.println("The sizes are : "+option.size());
//		Writting a advanced for loop 
		for(WebElement opt :option ) {
			System.out.println(opt.getText());
			String op = opt.getText();
			if(op.equals("jQuery") || op.equals("Python") || op.equals("csharp")) {
				opt.click();
			}
		}
		
		Thread.sleep(5000);
		driver.quit();
		
	}

}
