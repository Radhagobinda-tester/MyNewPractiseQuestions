package RealExamplePractiseQuestions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class linkedIncancelButton {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

            driver.get("https://www.linkedin.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

            // Login steps
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            emailField.sendKeys("radhagobinda7894@gmail.com");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("Deepti@1993");

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[normalize-space()='Sign in'])[1]")));
            loginButton.click();

            // Navigate My Network
            WebElement myNetwork = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@title,'My Network')]")));
            myNetwork.click();

            // Click Connections
            WebElement connections = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Connections']")));
            connections.click();

            // Search person
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Search by name']")));
            searchBox.clear();
            searchBox.sendKeys("deepti sonika tripathy");

            // Pause to let search results load (better to replace with explicit wait if possible)
            Thread.sleep(5000);

            // Click Message button
            WebElement messageBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a[aria-label='Message'] span")));
            messageBtn.click();
            Thread.sleep(7000);
            // Shadow DOM - close button
            WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#interop-outlet")));

            SearchContext shadowRoot = shadowHost.getShadowRoot();

            WebElement closeButton = wait.until(driver1 -> {
                try {
                    WebElement button = shadowRoot.findElement(By.cssSelector("use[href='#close-small']"));
                    if (button.isDisplayed() && button.isEnabled()) {
                        return button;
                    } else {
                        return null;
                    }
                } catch (Exception e) {
                    return null;
                }
            });

            if (closeButton != null) {
            	Thread.sleep(5000);
                closeButton.click();
                System.out.println("Close button clicked successfully.");
            } else {
                System.out.println("Close button not clickable after waiting.");
            }

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        // Note: No driver.quit() here, browser will remain open.
    }
}
