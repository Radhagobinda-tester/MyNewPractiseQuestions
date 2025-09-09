package RealExamplePractiseQuestions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.nio.file.Files;
import java.nio.file.Path;

public class NewtestBrowser {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Radha\\edgedriver_win64\\msedgedriver.exe");

        // Create a unique temporary directory for each session
        Path tempProfileDir = Files.createTempDirectory("edge-profile");

        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Use the temporary unique user data dir
        options.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath().toString());

        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }
}

