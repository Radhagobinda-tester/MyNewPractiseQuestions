package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class housivity_com {

	public static void main(String[] args) throws InterruptedException {
		// Create ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Correct arguments
        options.addArguments("--start-maximized");     
        options.addArguments("--disable-notifications"); 
     
        // Initialize driver with options
        WebDriver driver = new ChromeDriver(options);

        // Open the URL
        driver.get("https://housivity.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement button = driver.findElement(By.id("headlessui-combobox-button-_R_jdplkav9vb_"));
		button.click();
		WebElement Banglore = driver.findElement(By.xpath("//span[normalize-space()='Bengaluru']")); 
		wait.until(ExpectedConditions.elementToBeClickable(Banglore)).click();
		
      Thread.sleep(3000);
		
		Set<String> locationsSet = new HashSet<>();

		
		int maxAttempts = 10;

		for (int i = 0; i < maxAttempts; i++) {
		    List<WebElement> locations = driver.findElements(By.xpath(
		        "//address[@class='font_reg_9 lg:font_med_8 leading-[120%] text-C_909090 line-clamp-2 max-h-8 md:line-clamp-none md:h-auto md:w-full md:truncate not-italic']"
		    ));

		    for (WebElement loc : locations) {
		        if (loc.isDisplayed()) {
		        	
		            // Get X and Y coordinates of element
		            int x = loc.getLocation().getX();
		            int y = loc.getLocation().getY();

		            // Scroll window to element coordinates
                    js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);

		            // Optional small wait to allow rendering
		            Thread.sleep(500);

		            // Add text to set
		            locationsSet.add(loc.getText().trim());
		        }
		    }

		    // Click "Next Slide" button using JS
		    try {
		        WebElement nextBtn = driver.findElement(By.xpath("//img[@alt='Next Slide' and contains(@class,'cursor-pointer')]"));
                js.executeScript("window.scrollTo(arguments[0], arguments[1]);", nextBtn.getLocation().getX(), nextBtn.getLocation().getY());
		        js.executeScript("arguments[0].click();", nextBtn);
		    } catch (Exception e) {
		        System.out.println("No more slides.");
		        break;
		    }
		}

		System.out.println("Locations found:");
		locationsSet.forEach(System.out::println);

        driver.quit();

		   

        	}
		
	}
