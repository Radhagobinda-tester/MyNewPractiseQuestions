package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OYOMinimumPriceHotelName {

    public static void main(String[] args) throws InterruptedException {
        
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");        // start maximized
        option.addArguments("--disable-notifications");  // disable notifications

        WebDriver driver = new ChromeDriver(option);
        driver.get("https://www.oyorooms.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Enter text on the search field
        WebElement text = driver.findElement(By.id("autoComplete__home"));
        wait.until(ExpectedConditions.visibilityOf(text)).sendKeys("Ahmedabad");

        // Select the location from the list
        List<WebElement> location = driver.findElements(
                By.xpath("//span[contains(@class,'geoSuggestionsList__locationName')]")
        );

        for (WebElement locations : location) {
            String name = locations.getText();
            System.out.println(name);

            if (name.equalsIgnoreCase("Ahmedabad, Gujarat, India")) {
                locations.click();
                System.out.println("The button is clickable");
                break;
            }
        }

        // Date picker click
        WebElement dtPickerBtn = driver.findElement(By.xpath(
                "//div[@class='oyo-row oyo-row--no-spacing datePickerDesktop datePickerDesktop--home']"));
        dtPickerBtn.click();

        // Target date
        String targetYear = "2025";
        String targetMonth = "December";
        String targetDay = "23";

        // Wait for calendar panel
        WebElement yearLabel = driver.findElement(By.xpath("(//select[@class='DateRangePicker__MonthHeaderSelect'])[2]"));
        Select year = new Select(yearLabel);
        year.selectByIndex(1);

        Thread.sleep(5000);
     

        WebElement monthLabel = driver.findElement(By.xpath("(//select[@class='DateRangePicker__MonthHeaderSelect'])[1]"));
        Select month = new Select(monthLabel);
        month.selectByVisibleText("February");


        
     
        // Selecting date from date picker
        List<WebElement> date = driver.findElements(
                By.xpath("(//div[@class='DateRangePicker__Month'])[1]//span[@class='DateRangePicker__DateLabel']"));
        
        for (WebElement dates : date) {
            String Text = dates.getText();
            if (Text.equalsIgnoreCase(targetDay)) {
                dates.click();
            }
        }

        List<WebElement> date1 = driver.findElements(
                By.xpath("(//div[@class='DateRangePicker__Month'])[1]//span[@class='DateRangePicker__DateLabel']"));
      
        for (WebElement dates1 : date1) {
            String Text1 = dates1.getText();
            if (Text1.equalsIgnoreCase("24")) {
                dates1.click();
            }
        }

        WebElement btn = driver.findElement(By.xpath("//button[text()='Search']"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();

        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Keep scrolling until the page height stops changing
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

        while (true) {
            // Scroll to bottom
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
           Thread.sleep(5000);

            // Calculate new scroll height
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");

            // Check if no new content loaded
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }
        
        
     // Get all hotel cards (each card contains name + price)
        List<WebElement> hotelCards = driver.findElements(
        By.xpath("//div[contains(@class,'oyo-row oyo-row--no-spacing listingHotelDescription')]"));
        System.out.println(" Total hotels found"+hotelCards.size());

        String lowestPriceHotel = "";
        int lowestPrice = Integer.MAX_VALUE;

        String highestPriceHotel = "";
        int highestPrice = Integer.MIN_VALUE;

        for (WebElement card : hotelCards) {
            // Get hotel name
            WebElement nameElem = card.findElement(By.xpath(".//h3[contains(@class,'listingHotelDescription__hotelName')]"));
            String hotelName = nameElem.getText();
//            System.out.println(hotelName);

            // Get hotel price
            List<WebElement> priceElems = card.findElements(By.xpath(".//span[contains(@class,'listingPrice__finalPrice')]"));
            if (!priceElems.isEmpty()) {
                String priceText = priceElems.get(0).getText().replaceAll("[^0-9]", ""); // remove ₹, commas
                if (!priceText.isEmpty()) {
                    int price = Integer.parseInt(priceText);

                    if (price < lowestPrice) {
                        lowestPrice = price;
                        lowestPriceHotel = hotelName;
                    }

                    if (price > highestPrice) {
                        highestPrice = price;
                        highestPriceHotel = hotelName;
                    }
                }
            }
        }

        System.out.println("Lowest price: ₹" + lowestPrice + " Hotel: " + lowestPriceHotel);
        System.out.println("Highest price: ₹" + highestPrice + " Hotel: " + highestPriceHotel);

        driver.quit();

    }
}
