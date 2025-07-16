package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBusWebsiteFetchingAllTheOperaors {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.redbus.in/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement fromField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .//div[text()='From']]")));
		fromField.click();
		driver.findElement(By.id("srcDest")).sendKeys("Pune");
		
		  Thread.sleep(3000);
		// Wait until the dropdown options are visible
		List<WebElement> fromOptions = wait.until(
		    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='searchCategory___e86c08'][1]")));
		 Thread.sleep(5000);
		System.out.println("Total dropdown options: " + fromOptions.size());
      Thread.sleep(3000);
		String sourceName = "Swargate, Pune";
		boolean matchFound = false;

		// Loop through all options and print matching ones
		for (WebElement option : fromOptions) {
		    String optionText = option.getText().trim();
		    System.out.println("Matching options: " + optionText);
		    if (optionText.contains(sourceName)) {
		        System.out.println("Matching option: " + optionText);
		        matchFound = true;
//		        Optionally click if needed
		        option.click();
		        break;
		    }
		}

		if (!matchFound) {
		    System.out.println("No matching option found for: " + sourceName);
		}

		
	
	/*WebElement tofield = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='labelCityWrapper___ff8ef7'  and .//div[text() = 'To']]")));
	tofield.click();
	*/
	driver.findElement(By.id("srcDest")).sendKeys("Mumbai");
	
	  Thread.sleep(3000);
	// Wait until the dropdown options are visible
	List<WebElement> toOptions = wait.until(
	    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='searchCategory___e86c08'][1]")));
	 Thread.sleep(5000);
	System.out.println("Total dropdown options: " + toOptions.size());
  Thread.sleep(3000);
	String destineName = "Mumbai";
	boolean matchFound1 = false;

	// Loop through all options and print matching ones
	for (WebElement option1 : toOptions) {
	    String optionText1 = option1.getText().trim();
	    System.out.println("Matching options: " + optionText1);
	    if (optionText1.contains(destineName)) {
	        System.out.println("Matching option: " + optionText1);
	        matchFound1 = true;
//	        Optionally click if needed
	        option1.click();
	        break;
	    }
	}

	if (!matchFound1) {
	    System.out.println("No matching option found for: " + sourceName);
	}

	
	
	// Select the date
	String month = "July";
	String Year = "2025";
	String ddate = "18";

	driver.findElement(By.xpath("//div[@class='dateInputWrapper___f4c22e']")).click();
	Thread.sleep(3000);

	// Loop until correct month and year appear
	while (true) {
	    String array = driver.findElement(By.xpath("//p[@class='monthYear___93a489']")).getText();
	    Thread.sleep(1000);
	    String[] arr = array.split(" ");
	    if (arr.length < 2) continue;
	    String mon = arr[0];
	    String yr = arr[1];

	    if (month.equalsIgnoreCase(mon) && Year.equalsIgnoreCase(yr)) {
	        break;
	    } else {
	        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'arrow') and contains(@class,'right')]")));
	        nextBtn.click();
	        Thread.sleep(1000);
	    }
	}

	Thread.sleep(5000);

	// Select the date
	List<WebElement> allDates = driver.findElements(By.xpath("//div[contains(@class, 'calendarDate')]/span"));
	for (WebElement date : allDates) {
	    if (date.getText().trim().equals(ddate)) {
	        date.click();
	        break;
	    }
	}

	WebElement searchBtn = driver.findElement(By.xpath("//button[@class='primaryButton___93b44e searchButtonWrapper___8e4b13']"));
	searchBtn.click();

	WebElement primoBus = driver.findElement(By.xpath("//div[contains(@class, 'label') and contains(text(), 'Primo Bus')]"));
	primoBus.click();

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	Set<String> allOperatorsNames = new LinkedHashSet<>();
	List<WebElement> operatorsNames = driver.findElements(By.xpath("//div[@class='travelsName___495898']"));

	List<String> operatorsNames1 = operatorsNames.stream()
	        .map(WebElement::getText)
	        .filter(name -> !name.trim().isEmpty())
	        .collect(Collectors.toList());

	allOperatorsNames.addAll(operatorsNames1);

	System.out.println("Total Bus Operators: " + allOperatorsNames.size());
	allOperatorsNames.forEach(System.out::println);
}
}
	



