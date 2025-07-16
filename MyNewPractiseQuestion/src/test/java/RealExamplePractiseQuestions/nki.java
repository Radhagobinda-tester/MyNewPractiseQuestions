package RealExamplePractiseQuestions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class nki {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.nkindia.in/");

        // Wait and click on Login
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.//em[normalize-space()='Login']]")));
        loginLink.click();

        // Store parent window
        String parentWindow = driver.getWindowHandle();

        // Wait and click on PMIS
        WebElement pmisLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://arena.nkindia.inncircles.com/' and contains(text(), 'PMIS')]")));
        pmisLink.click();

        // Wait for new tab to open and switch to it
        Thread.sleep(2000); // optional wait to ensure new tab opens
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                Thread.sleep(2000); // optional wait to ensure new tab opens

                // Wait and enter username
                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='mat-input-0'])[1]")));
                usernameField.sendKeys("REPK6_APULIP");
                Thread.sleep(2000); // optional wait to ensure new tab opens

                // Wait and enter password
                WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='mat-input-1'])[1]")));
                passwordField.sendKeys("Package@6");

                // Wait and click login button
                WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='mdc-button__label'])[1]")));
                loginButton.click();
                Thread.sleep(5000);
/*
                // Wait and click on project name (mini-project-name div)
                WebElement projectDiv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'mini-project-name')]")));
                projectDiv.click();
                */
                
                WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                	    By.xpath("//h3[contains(text(), 'Ahmedabad Peri Urban Livability Improvement Project')]/following-sibling::div//mat-icon[contains(text(), 'chevron_right')]")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click();", menu);

                Thread.sleep(5000);

                // Wait and click on Field Works
                WebElement fieldWorks = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class, 'menu-collapsed-item') and .//div[normalize-space()='Field Works']]")));
                fieldWorks.click();

                // Wait and click on RFI card
                WebElement rfiCard = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//mat-card[.//div[normalize-space()='RFI']]")));
                rfiCard.click();
             // Read Excel File for Email addresses
                FileInputStream fis = new FileInputStream("F:\\Reject file sheet 2.xlsx");
                Workbook wb = new XSSFWorkbook(fis);
                Sheet sheet = wb.getSheet("Sheet2");

                // Loop through all the emails in the Excel file
                int fromRow = 10;  // Start from row 2 (0-based index)
                int toRow = 20;  // Last used row index
                int RF = 1; // Column index for emails

                for (int i = fromRow; i <= toRow; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null && row.getCell(RF) != null) {
                        String rfn = row.getCell(RF).getStringCellValue();
                        
                        if (rfn != null && !rfn.isEmpty()) {
                            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//input[@placeholder='Search']")));
                            
                            searchInput.clear(); // clear previous input
                            
                            searchInput.sendKeys(rfn);
                            Thread.sleep(20000); 
                           
                            driver.findElement(By.xpath("//button[.//span[normalize-space()='Reject']]")).click();
                            Thread.sleep(10000); // Better: use WebDriverWait for result visibility
                           

                            driver.findElement(By.xpath("//*[.//span[normalize-space()='Reject'] and contains(@class, 'mdc-button')][1]")).click();
                            Thread.sleep(10000); // Better: use WebDriverWait for result visibility
                           
                            driver.findElement(By.xpath("//mat-select[@formcontrolname='priority']")).click();
                            driver.findElement(By.xpath("//mat-option[.//span[normalize-space()='Low']]")).click();
                            Thread.sleep(3000);
                            driver.findElement(By.xpath("(//div[@contenteditable='true' and contains(@class, 'ql-editor')])[3]")).sendKeys("Wrong RFI");
                            Thread.sleep(3000);
                            driver.findElement(By.xpath("//button[.//span[normalize-space()='Submit']]")).click();
                            Thread.sleep(3000);
                            driver.navigate().back();
                            // Optional: wait for search results to load or do something after search
                            Thread.sleep(1000); // Better: use WebDriverWait for result visibility
                        }
                    }
                }


    	        // Close the file and input stream
    	        wb.close();
    	        fis.close();
    	        System.out.println("Excel file closed.");

                break;
            }
        }
    }
}
