package RealExamplePractiseQuestions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CountTheLinks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.get("https://www.selenium.dev");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      List<WebElement> list= driver.findElements(By.tagName("a"));
      System.out.println("The links counts : "+list.size());
      for(WebElement lists : list) {
//			Storing the value of href links 
			String hrefValue = lists.getAttribute("href");
			 // Print the href value (link)
		    if (hrefValue != null && !hrefValue.isEmpty()) {
		        System.out.println(hrefValue);
		    }
      }
      
//      limiting the webdriver scope
      WebElement link = driver.findElement(By.xpath("//nav[@class='js-navbar-scroll navbar navbar-expand-lg navbar-light bg-white flex-row td-navbar']"));
      List<WebElement>linkLists=link.findElements(By.tagName("a"));
      System.out.println("The limited links on the selected are : "+linkLists.size());
      for(WebElement linkListsAre : linkLists) {
    	  String hrefLinksAre=linkListsAre.getAttribute("href");
//    	  Printing all the links 
    	  if(hrefLinksAre!=null && !hrefLinksAre.isEmpty()) {
    		  System.out.println("The links which are inside the tag : "+ hrefLinksAre);
    	  }
      }
     driver.close();
      
	}

}
