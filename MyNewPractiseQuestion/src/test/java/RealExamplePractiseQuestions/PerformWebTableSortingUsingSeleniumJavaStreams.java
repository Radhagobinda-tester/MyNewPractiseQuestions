package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PerformWebTableSortingUsingSeleniumJavaStreams {

	public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();	
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://datatables.net");
    WebElement sortingButton = driver.findElement(By.xpath("(//*[name()='svg'])[6]"));
    sortingButton.click();
    Thread.sleep(10000);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement svg = driver.findElement(By.xpath("//svg[path[@d='m3 16 4 4 4-4']]"));
    js.executeScript("arguments[0].click();", svg);

    List<WebElement> list = driver.findElements(By.xpath("//tr//td[3]"));
   // Storing the list after sorted
    List<String>elementList = list.stream().map(s->s.getText()).collect(Collectors.toList());
//    Comparing the list with doing sorted by using java stream 
    List<String> origanlList= elementList.stream().sorted().collect(Collectors.toList());
    if(elementList.equals(origanlList)) {
    	System.out.println("List is sorted"+ origanlList);
    }else {
    	System.out.println("List is not sorted"+ origanlList);
    }
    
    
	}

}
