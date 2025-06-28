package RealExamplePractiseQuestions;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v122.webaudio.model.AudioListenerWillBeDestroyed;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PaginationStreamAPI {

	public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://poshpeople.in/");
	WebElement ele = driver.findElement(By.id("menu-item-3597"));
	Actions f = new Actions(driver);
	f.moveToElement(ele).build().perform();
	driver.findElement(By.xpath("//span[contains(text(),'iPhone 15 Pro Max')]")).click();
 // Getting all the link 
	
	List<String> Price;
do {
	List<WebElement> elements = driver.findElements(By.tagName("a"));
	Thread.sleep(3000);
	Price = elements.stream().filter(a->a.getText().contains("Pink Cute 3D Teddy cases for iPhone")).map(a-> getPrice(a)).collect(Collectors.toList());
	Thread.sleep(3000);
     Price.forEach(s -> System.out.println(s));
     Thread.sleep(3000);
//	Write a if condition 
	if(Price.size()<1) {
		// Locate the element
		WebElement nextButton = driver.findElement(By.cssSelector(".next.page-numbers"));

		// Scroll into view using JavaScriptExecutor
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
		Thread.sleep(3000);

		// Then click on the element
		nextButton.click();
		Thread.sleep(3000);
	}
}while(Price.size()<1);
	}
	
	
public static String getPrice(WebElement a) {
	WebElement priceElement = a.findElement(By.xpath(".//following::span[contains(@class, 'price')]"));


    return priceElement.getText();
}
	

}

