package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/*Locate country and language drop-down 
 * which is present at top right side and verify particular language
 * and check particular country is selected within drop-down or not
*/

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com");
//		Waiting the duration of seconds 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		Clicking on  cancel button from the pop up 
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
//		Clicking on the DD 
		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[@class='headerstyle__Arrow-sc-1fupkep-2 headerstyle__ArrowDown-sc-1fupkep-4 rYfcy bBRZdk']")).click();
//		Clicking on the drop down 
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='styles__DropdownInput-sc-e66som-9 gEEdDv'][1]")).click();
//		Getting all the options from the Country drop downs 
		List <WebElement> option = driver.findElements(By.xpath("//div[@data-testid='dropdown-list-country']//p[contains(@data-testid, '-country')]"));
//		Printing the size of the of the Bootstrap Drop down of country list  
		System.out.println("The size of the Drop down "+ option.size());
//		Writting for each loop 
		for(WebElement ele : option) {
			String elements = ele.getText();
			if(elements.equalsIgnoreCase("India")) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		        System.out.println("Selected country: " + elements);
				 break; // Exit loop after selecting India
			}
		}
		Thread.sleep(3000);
//		Clicking on the Language DD 
		driver.findElement(By.xpath("//div[@data-cy='language-dropdown']")).click();
		
//		Getting all the options from the language Drop downs 
		List <WebElement> languageDD = driver.findElements(By.xpath("//div[@data-testid='dropdown-list-language']//p[contains(@data-testid, '-language')]"));
		System.out.println("The size of the language Drop down "+ languageDD.size());
//		Writting for each loop 
		for(WebElement languages : languageDD) {
			String element = languages.getText();
			if(element.contains("Tamil")) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(languages)).click();
		        System.out.println("Selected Language : " + element);
				 break; // Exit loop after selecting India
			}
		}
		
		driver.findElement(By.xpath("//button[@data-testid='country-lang-submit']")).click();
		
		Thread.sleep(5000);
		driver.close();
		
		

	}

}
