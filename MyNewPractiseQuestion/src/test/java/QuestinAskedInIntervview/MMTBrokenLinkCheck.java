package QuestinAskedInIntervview;


	

	import java.io.IOException;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class MMTBrokenLinkCheck {
	WebDriver driver;

	@BeforeMethod 
	public void openApp(){
	WebDriverManager.chromedriver().setup();
	ChromeOptions option  = new ChromeOptions();
	option.addArguments("--start-maximized");
	driver = new ChromeDriver(option);
	//  https://en.wikipedia.org/wiki/Main_Page 
	// "https://rustylinks.zomdir.com/" 
	// https://www.makemytrip.com/
	driver.get("https://www.makemytrip.com/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


	}

	@Test()
	public void test() throws IOException{
		  // Explicit wait (used for specific dynamic elements)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // ===== Close Login/Signup Popup if Present =====
        try {
            WebElement popupClose = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector(".commonModal__close")));
            popupClose.click();
        } catch (Exception e) {
            System.out.println("Popup not found or already closed");
        }

	 List<WebElement> ele = driver.findElements(By.tagName("a"));
	 for(WebElement a :ele){
	 String l = a.getAttribute("href");
	 if(l != null && !l.isEmpty()) {
		 try {
	 URL links = new URL(l);
	 HttpURLConnection conn = (HttpURLConnection)links.openConnection();
	 conn.setConnectTimeout(20000);
	 conn.setReadTimeout(20000);
	 conn.setRequestProperty(
		        "User-Agent",
		        "Mozilla/5.0");
	 conn.connect();
	 
	 int code = conn.getResponseCode();

	 System.out.println(l + " --> Response Code: " + code);

	 if(code == 404 || code == 500) {
	     System.out.println(l + " --> Broken");
	 } else {
	     System.out.println(l + " --> Valid");
	 }
	 }catch(Exception e) {
		 System.out.println(l + " --> Error : " + e.getMessage());
	 }
	 }
	}
	}

	@AfterMethod 
	public void closeApp(){
	driver.quit();

	}

	}

