package webTablePractise;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTablePractiseWithTestCases {
WebDriver driver;
	
	@BeforeMethod
	public void OpenApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.get("https://practicetestautomation.com/practice-test-table/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	@Test(enabled=false)
	public void extractingData() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		 Clicking on java check box 
		WebElement javaCheck =  driver.findElement(By.xpath("//input[@value='Java']"));
		javaCheck.click();
		
		List<WebElement> list  = driver.findElements(By.xpath("//table[@id='courses_table']/tbody/tr/td[3]"));
		int s = list.size();
		for(WebElement l : list) {
			js.executeScript("arguments[0].scrollIntoView(true);", l);
			String data = l.getText();
			
			if(!data.isEmpty()) {
			System.out.println("Checking language: " + data);
			Assert.assertEquals(data, "Java", "Non-Java course found in table");
			}
		}
		System.out.println("All visible courses are Java");
	}
	
	/*Test case 2: Level filter → Beginner only
Open page
Uncheck Intermediate and Advanced
Verify that only Beginner courses are visible
Fails if unchecked levels still pass the filter*/
	
	@Test(priority=2)
	public void checkBefinner() {
//		Unchecking both on beginner 
		
		WebElement intermidate = driver.findElement(By.xpath("//input[@value='Intermediate']"));
		
		intermidate.click();
WebElement advanced = driver.findElement(By.xpath("//input[@value='Advanced']"));
		
      advanced.click();
		
      List<WebElement>  l1 = driver.findElements(By.xpath("//table[@id='courses_table']/tbody/tr/td[4]"));
      for(WebElement l2:l1) {
    	  String courseName = l2.getText();
    	  String orgCourseName = "Beginner";
    	  if(!courseName.isEmpty()) {
    		  System.out.println("The corse is for : "+courseName);
    		  Assert.assertEquals(courseName, orgCourseName, "Course name begginer s present");
    		  
    	  }
      }
	}
	/*Test case 7: Sort by Enrollments (ascending, numeric)
Open page
Set Sort by = Enrollments
Verify visible rows are ordered from smallest to largest enrollment
Verify numbers with commas sort correctly
Fails if the sort is lexicographic*/
	@Test(priority=1)
	public void checkLexographic() {
		WebElement enroll = driver.findElement(By.id("sortBy"));
		
		Select s = new Select(enroll);
		s.selectByVisibleText("Enrollments");
		List<WebElement> eLists = driver.findElements(By.xpath("//table[@id='courses_table']/thead/following::tbody/tr/td[5]"));
		int pastvalue = Integer.MIN_VALUE;
		for(WebElement elist:eLists) {
			String value = elist.getText().replace(",", "");
			int currentValue = Integer.parseInt(value);
			
			if(currentValue >=pastvalue) {
				pastvalue=currentValue;
				
			}else {
				
				Assert.fail("The table is not lexographic");
			}
		}
		
	}
	
	/*Open page
Set Sort by = Course Name
Verify visible rows are ordered A→Z by course name
Verify order updates after changing filters
Fails if string compare ignores case/whitespace inconsistently*/
	@Test(priority=0)
	public void Sorting() {
		WebElement enroll = driver.findElement(By.id("sortBy"));
		
		Select s = new Select(enroll);
		s.selectByVisibleText("Course Name");
		List<WebElement> eLists1 = driver.findElements(By.xpath("//table[@id='courses_table']/tbody/tr/td[2]"));
		ArrayList<String> actualList = new ArrayList<>();
		
		
		for(WebElement elist1:eLists1) {
			String value = elist1.getText();
			actualList.add(value);		
		}
		System.out.println("Actual : "+actualList);
		List<String> sortedList = new ArrayList<>(actualList);
		  Collections.sort(sortedList);

		    System.out.println("Sorted List: " + sortedList);

		    // compare both
		    Assert.assertEquals(actualList, sortedList, "Table is not sorted A to Z");

		
	}
	@AfterMethod
	public void closeApp(){
		driver.quit();
	}


}
