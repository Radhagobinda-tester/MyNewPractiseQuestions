package RealExamplePractiseQuestions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.nio.file.Files;
import java.nio.file.Path;

public class undetectableTest {

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Disable Selenium automation flags
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Use a random/temporary user data dir to avoid "already in use" error
        Path tempProfile = Files.createTempDirectory("chrome-profile");
        options.addArguments("--user-data-dir=" + tempProfile.toString());

        // Set a normal user-agent
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115 Safari/537.36");

        // Optional: headless mode off (so you can see it)
        // options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);

        // Hide webdriver flag
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );

        driver.get("https://www.ve3.global/");

        Thread.sleep(4000);
        System.out.println("Page Title: " + driver.getTitle());

        driver.quit();
    }
}
