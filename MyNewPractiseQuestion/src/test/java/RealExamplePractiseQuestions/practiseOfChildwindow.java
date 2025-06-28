package RealExamplePractiseQuestions;
//TODO Auto-generated method stub
	

		import java.time.Duration;
		import java.util.Set;

		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.support.ui.ExpectedConditions;
		import org.openqa.selenium.support.ui.WebDriverWait;

		import io.github.bonigarcia.wdm.WebDriverManager;


public class practiseOfChildwindow {

	public static void main(String[] args) {
		
		
		        WebDriverManager.chromedriver().setup();
		        WebDriver driver = new ChromeDriver();

		        driver.manage().window().maximize();
		        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		        String parent = driver.getWindowHandle();
		        driver.findElement(By.xpath("//a[contains(text(),'Free Access to')]")).click();

		        Set<String> windows = driver.getWindowHandles();
		        String emailText = null; // ✅ Declare the variable outside the loop

		        for (String window : windows) {
		            if (!window.equals(parent)) {
		                driver.switchTo().window(window);

		                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		                WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                        By.xpath("(//p[@class='im-para red'])[1]")));

		                // ✅ Store extracted email text
		                emailText = ele.getText().split("at")[1].trim().split(" ")[0];
                         System.out.println(emailText);
		                break; // ✅ Optional: break after getting the email
		            }
		        }

		        // ✅ Switch back and paste the email
		        driver.switchTo().window(parent);
		        driver.findElement(By.id("username")).sendKeys(emailText);
		    }
		}

	


