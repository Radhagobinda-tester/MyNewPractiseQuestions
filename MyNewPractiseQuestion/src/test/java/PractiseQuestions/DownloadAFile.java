package PractiseQuestions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadAFile {

    public static void main(String[] args) throws InterruptedException {
        // Set download directory to project's Downloads folder
        String downloadPath = System.getProperty("user.dir") + "\\Downloads\\";

        // Create preferences map for Chrome
        HashMap<String, Object> preferences = new HashMap<>();
        preferences.put("plugin_always_open_pdf_externally", true); // force download instead of opening in browser
        preferences.put("download.default_directory", downloadPath); // set custom download path

        // Set Chrome options with preferences
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);

        // Maximize browser window
        driver.manage().window().maximize();

        // Open the webpage with the PDF download link
        driver.get("https://examplefile.com/document/pdf/1-mb-pdf#google_vignette");

        // Click the download link
        // You may need to adjust this locator depending on actual HTML
        driver.findElement(By.xpath("//a[contains(@href, '/file-download/26')]")).click();
        try {
            Robot robot = new Robot();
            Thread.sleep(2000); // Wait for Save As dialog to appear
            robot.keyPress(KeyEvent.VK_ENTER); // Press Enter
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
        // Optional: wait to ensure file is downloaded before quitting (basic wait)
        
        // Close the browser
        driver.quit();
    }
}
