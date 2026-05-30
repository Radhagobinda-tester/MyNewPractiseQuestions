package RealExamplePractiseQuestions;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

public class HousitivitySearchFunctionality {

    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60)); // General wait

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long startTime = System.currentTimeMillis();

        try {
            driver.get("https://housivity.com/");

            // Array of all 23 cities
            String[] cities = {
                "Ahmedabad", "Gandhinagar", "Pune", "Vadodara", "Surat",
                "Chennai", "Hyderabad", "Mumbai", "Rajkot", "Thane",
                "Navi Mumbai", "Bengaluru", "Gurgaon", "Bhavnagar", "Kolkata",
                "Mehsana", "Jamnagar", "Noida", "Greater Noida", "Junagadh",
                "Delhi", "Gift City", "Dholera"
            };

            for (String city : cities) {
                System.out.println("\n==============================");
                System.out.println("🔍 Searching for city: " + city);
                System.out.println("==============================");

                try {
                    // Locate and type city
                    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='hidden max-w-[12.75rem] grow-0 md:block']//input[contains(@class,'combobox-input')]")
                    ));
                    input.clear();
                    input.sendKeys(city);
                    input.sendKeys(Keys.ENTER);

                    // Click Search button
                    try {
                        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[normalize-space(text())='Search']")
                        ));
                        js.executeScript("arguments[0].click();", searchBtn);
                    } catch (NoSuchElementException e) {
                        System.out.println("❌ Search button not found for " + city);
                        continue;
                    }

                    // Fetch expected property count
                    int expectedCount = 0;
                    try {
                        WebElement resultCount = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//div[contains(@class,'font_bold_8') and contains(@class,'text-C_FB6514')])[1]")
                        ));
                        expectedCount = Integer.parseInt(resultCount.getText().replaceAll("[^0-9]", ""));
                    } catch (Exception e) {
                        System.out.println("⚠️ Could not fetch property count for " + city);
                    }
                    System.out.println("Expected Count: " + expectedCount);

                    // Track property info and duplicates
                    Map<String, Integer> allProperties = new HashMap<>();
                    Map<String, Integer> duplicateProperties = new HashMap<>();
                    int pageCount = 0;

                    while (true) {
                        pageCount++;

                        // Scroll to load properties
                        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//div[contains(@class,'flex') and contains(@class,'flex-col') and contains(@class,'justify-between') and contains(@class,'p-4')]")
                        ));

                        List<WebElement> cards = driver.findElements(By.xpath(
                            "//div[contains(@class,'flex') and contains(@class,'flex-col') and contains(@class,'justify-between') and contains(@class,'p-4')]"
                        ));

                        for (WebElement card : cards) {
                            try {
                                String propertyName = card.findElement(By.cssSelector("div.truncate.font_bold_9")).getText().trim();
                                String developerName = card.findElement(By.cssSelector("div.font_reg_8")).getText().trim();
                                String location = card.findElement(By.cssSelector("h2 span.font_bold_10"))
                                        .getText().replace("for Sale in ", "").trim();

                                if (propertyName.isEmpty() || developerName.isEmpty()) continue;

                                String key = propertyName + " | " + developerName + " | " + location;

                                if (!allProperties.containsKey(key)) {
                                    allProperties.put(key, pageCount);
                                } else {
                                    duplicateProperties.put(key, pageCount);
                                }

                            } catch (NoSuchElementException e) {
                                continue;
                            }
                        }

                        // Check for next button
                        try {
                            WebElement nextBtn = driver.findElement(By.xpath("//button[.//span[text()='Next']]"));
                            if (nextBtn.isEnabled()) {
                                js.executeScript("arguments[0].click();", nextBtn);
                                wait.until(ExpectedConditions.stalenessOf(cards.get(cards.size() - 1))); // Wait for new page
                            } else {
                                break;
                            }
                        } catch (NoSuchElementException e) {
                            break; // last page
                        }
                    }

                    // Summary per city
                    System.out.println("Pages visited: " + pageCount);
                    System.out.println("Unique properties found: " + allProperties.size());

                    if (!duplicateProperties.isEmpty()) {
                        System.out.println("⚠️ Duplicate properties in " + city + ":");
                        for (String key : duplicateProperties.keySet()) {
                            int firstPage = allProperties.get(key);
                            int duplicatePage = duplicateProperties.get(key);
                            System.out.println("➡️ " + key + " -> First: Page " + firstPage + ", Duplicate: Page " + duplicatePage);
                        }
                    } else {
                        System.out.println("✅ No duplicates found in " + city);
                    }

                } catch (Exception e) {
                    System.out.println("❌ Error processing city: " + city + " | " + e.getMessage());
                }

                // Navigate back to homepage after each city
                System.out.println("🌐 Navigating back to homepage for next city...");
                driver.get("https://housivity.com/");
                wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='hidden max-w-[12.75rem] grow-0 md:block']//input[contains(@class,'combobox-input')]")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            long endTime = System.currentTimeMillis();
            System.out.println("\n==============================");
            System.out.println("🏁 Total Execution Time: " + ((endTime - startTime) / 1000) + " seconds");
            System.out.println("==============================");
        }
    }
}
