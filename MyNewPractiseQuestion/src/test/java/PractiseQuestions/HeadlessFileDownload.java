package PractiseQuestions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessFileDownload {

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
            Thread.sleep(3000); // Wait for Save As dialog to appear
            robot.keyPress(KeyEvent.VK_ENTER); // Press Enter
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
        // Optional: wait to ensure file is downloaded before quitting (basic wait)
        Thread.sleep(5000);
        File f = new File(downloadPath);
        if(f.exists()) {
        	System.out.println("The file downloaded");
        	if(f.delete()) {
        		System.out.println("The file deleted");
        	}
        }else {
        	System.out.println("The file is not  downloaded");
        }
        
        Thread.sleep(5000);
        // Close the browser
        driver.quit();
    }
}

