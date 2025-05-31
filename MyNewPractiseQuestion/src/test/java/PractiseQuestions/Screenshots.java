package PractiseQuestions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Screenshots {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get("https://www.myntra.com/");
        Thread.sleep(3000); // Optional wait for full page load

        // Take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
/*
        // Ensure the directory exists
        File folder = new File(".\\Screenshot");
        if (!folder.exists()) folder.mkdir();
*/
        File dest = new File(".\\Screenshot\\homepage1.png");
        FileUtils.copyFile(src, dest);
        System.out.println("Screenshot saved at: " + dest.getAbsolutePath());

        Thread.sleep(2000);
        driver.quit();
    }
}
