package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownListPractise {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoqa.com/select-menu");
		driver.findElement(By.xpath("(//div[contains(text(),'Select Option')])[1]")).click();
		Thread.sleep(5000);
		List<WebElement> FirstDD = driver.findElements(By.xpath("//div[contains(@class,'css-11unzgr')]//div[contains(@class,'option')]"));
		System.out.println("The first Drop down size is : "+FirstDD.size());
		String dd = "Group 1, option 1";
		for(WebElement ele :FirstDD ) {
			String ele1 = ele.getText();
			System.out.println(ele1);
			if(ele1.equalsIgnoreCase(dd)) {
				ele.click();
			}
			
		}
		
		//div[text()='Select Option']
	}

}
