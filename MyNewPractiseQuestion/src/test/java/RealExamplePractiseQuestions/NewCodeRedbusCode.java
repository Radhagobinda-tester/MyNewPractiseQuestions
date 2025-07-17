package RealExamplePractiseQuestions;


import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewCodeRedbusCode {


public static void main(String[] args) throws InterruptedException {
	  WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      driver.get("https://www.redbus.in/");
      WebElement fromField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .//div[text()='From']]")));
		fromField.click();
      // FROM city
	 By searchoptions = By.xpath("//div[contains(@class, 'suggestionsWrapper')]");
	 wait.until(ExpectedConditions.visibilityOfElementLocated(searchoptions));
      WebElement fromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("srcDest")));
      fromInput.sendKeys("Pune");
  
	List<WebElement> fromOptions = wait.until(
          ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'listItem') and contains(., 'Board at')]//div[@class='listHeader___40b031']")));
          
	
      String sourceName = "Swargate, Pune";
      boolean sourceSelected = false;
      for (WebElement option : fromOptions) {
    	    wait.until(ExpectedConditions.visibilityOf(option));

          String text = option.getText().trim();
          
          System.out.println("Matching options: " + text);
          if (text.equalsIgnoreCase(sourceName)) {
        	  System.out.println("Matching option: " + text);
              wait.until(ExpectedConditions.elementToBeClickable(option)).click();
              sourceSelected = true;
              break;
          }
      }
      if (!sourceSelected) {
		    System.out.println("No matching option found for: " + sourceName);
		}

      // TO city
      WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("srcDest")));
      toInput.sendKeys("Mumbai");

      List<WebElement> toOptions = wait.until(
          ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'listItem') and contains(., 'Drop at')]//div[@class='listHeader___40b031']")));
      

      String destinationName = "Nalasopara East, Mumbai";
      boolean destinationSelected = false;
      for (WebElement option1 : toOptions) {
    	  wait.until(ExpectedConditions.visibilityOf(option1));
          String text1 = option1.getText().trim();
          if (text1.equalsIgnoreCase(destinationName)) {
        	  wait.until(ExpectedConditions.elementToBeClickable(option1)).click();
        	  System.out.println("Matching option: " + text1);
              destinationSelected = true;
              break;
          }
      }
      if (!destinationSelected) {
          System.out.println("Destination not found: " + destinationName);
         
        
      }

      // Select travel date
      String targetMonth = "July";
      String targetYear = "2025";
      String travelDay = "25";

      WebElement calendar = wait.until(ExpectedConditions.elementToBeClickable(
          By.xpath("//div[@class='dateInputWrapper___f4c22e']")));
      calendar.click();

      while (true) {
          WebElement monthYear = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//p[@class='monthYear___93a489']")));
          String[] parts = monthYear.getText().split(" ");
          if (parts.length >= 2 && parts[0].equalsIgnoreCase(targetMonth) && parts[1].equalsIgnoreCase(targetYear)) {
              break;
          } else {
              WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
                  By.xpath("//i[contains(@class,'arrow') and contains(@class,'right')]")));
              nextBtn.click();
          }
      }

      List<WebElement> allDates = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
          By.xpath("//div[contains(@class, 'calendarDate')]/span")));
      for (WebElement date : allDates) {
          if (date.getText().trim().equals(travelDay)) {
              date.click();
              break;
          }
      }

      // Click Search button
      WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
          By.xpath("//button[contains(@class,'searchButtonWrapper')]")));
      searchBtn.click();

      // Apply Primo Bus filter
      WebElement primoBus = wait.until(ExpectedConditions.elementToBeClickable(
          By.xpath("//div[contains(@class,'label') and contains(text(),'Primo Bus')]")));
      primoBus.click();

      // Scroll to bottom
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

      // Capture all operator names
      List<WebElement> operatorsElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
          By.xpath("//div[@class='travelsName___495898']")));
      Set<String> allOperators = operatorsElements.stream()
          .map(WebElement::getText)
          .filter(name -> !name.trim().isEmpty())
          .collect(Collectors.toCollection(LinkedHashSet::new));

      // Print operator count and names
      System.out.println("Total Bus Operators: " + allOperators.size());
      allOperators.forEach(System.out::println);

      driver.quit();
    }
}