package RealExamplePractiseQuestions;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 WebDriverManager.chromedriver().setup();
		 ChromeOptions option = new ChromeOptions();
		 option.addArguments("--start-maximized");
         WebDriver driver = new ChromeDriver(option);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
         driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_6o3s351fev_e&adgrpid=150668181581&hvpone=&hvptwo=&hvadid=774088017343&hvpos=&hvnetw=g&hvrand=15182872394908511423&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9061733&hvtargid=kwd-300061672064&hydadcr=5621_2466977&gad_source=1");
      // 1️⃣ Click the hamburger menu
         WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-hamburger-menu")));
         menu.click();

         // 2️⃣ Click on "Fire TV"
         By fireTv = By.xpath("(//div[normalize-space()='Fire TV'])[1]");
         wait.until(ExpectedConditions.elementToBeClickable(fireTv)).click();

         // 3️⃣ Wait for the submenu (Amazon Prime Video) to appear and click it
         By amazonLink = By.xpath("(//a[@class='hmenu-item'][normalize-space()='Amazon Prime Video'])[3]");
         wait.until(ExpectedConditions.visibilityOfElementLocated(amazonLink));
         wait.until(ExpectedConditions.elementToBeClickable(amazonLink)).click();

         System.out.println("✅ Successfully navigated to Amazon Prime Video page.");
	}

}
