package RealExamplePractiseQuestions;

import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;


import io.github.bonigarcia.wdm.WebDriverManager;

public class HowtoPerformScrollingwithintableandWindowlevelusingJavaScriptExecutor {

	public static void main(String[] args) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://datatables.net/examples/basic_init/scroll_y.html");
	
	WebElement ele = driver.findElement(By.cssSelector(".dt-scroll"));
	
	Point p = ele.getLocation();
	int x = p.getX();
	int y = p.getY();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy("+x+","+y+")");
    Thread.sleep(3000);
    JavascriptExecutor js1 = (JavascriptExecutor) driver;
    js1.executeScript("document.querySelector('.dt-scroll-body').scrollTop = 10000;");
 
    // Initialize the sum variable to hold the total sum of the column values
    double sum = 0.0;

    // Locate all <td> elements from the 6th column inside a table with class 'dt-scroll'
    List<WebElement> totalEle = driver.findElements(By.cssSelector(".dt-scroll td:nth-child(6)"));

    // Print the total number of elements found in the 6th column
    System.out.println("The total size: " + totalEle.size());

    // Loop through each cell in the 6th column
    for (WebElement cell : totalEle) {

        // Get the text from the cell and remove all characters except digits and decimal points
        String cellText = cell.getText().replaceAll("[^\\d.]", "");
//        Printing all the prices 
         System.out.println(cellText);
        // Check if the cleaned string is not empty
        if (!cellText.isEmpty()) {
            try {
                // Parse the cleaned string into a double value
                double value = Double.parseDouble(cellText);

                // Add the parsed value to the running total sum
                sum += value;
            } catch (NumberFormatException e) {
                // Handle cases where parsing fails and print the problematic text
                System.out.println("Could not parse value: " + cellText);
            }
        }
    }

    // Create a DecimalFormat object to format the sum with commas and two decimal places
    DecimalFormat df = new DecimalFormat("#,###.00");

    // Print the final formatted total sum of the 6th column
    System.out.println("Total sum of the 6th column: " + df.format(sum));

    
	
	
	}

}
