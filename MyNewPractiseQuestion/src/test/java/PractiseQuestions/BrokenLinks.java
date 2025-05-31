package PractiseQuestions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/* 
 * 1) Link href="Links value"
// * 2)https:// xyz to ------> sever ------> status code
 * status code>= 400 broken link lessthan it is not a broken link 
 * 
 * 
 * 
 * */

public class BrokenLinks {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.deadlinkcity.com/");
//		Getting the link from the server 
		int countOfBrokenlink = 0;
		List<WebElement> href = driver.findElements(By.tagName("a"));
//		 getting the count of href links 
		System.out.println("The total number of links : "+href.size());
//		 for each loop 
		for(WebElement links :href ) {
//			Storing the value of href links 
			String hrefValue = links.getAttribute("href");
//			 Write if condition cheking the href value which are empty or not 
			if(hrefValue==null || hrefValue.isEmpty()) {
				System.out.println("THe href link is empty or null"+ hrefValue);
//				If found some null value rest exection will happen so writting continue 
				continue;
			}
			try {
//			Hitting tthe URL 
		   URL linkUrl = new URL(hrefValue); //	coverting th string tpo URL 
		   HttpURLConnection conn = (HttpURLConnection) linkUrl.openConnection(); //open connection to connect the URL 
		   conn.connect(); //Connect tthe URL and sent request
		   if(conn.getResponseCode()>=400) {
			   System.out.println("The link is broken Link : "+ hrefValue);
			   countOfBrokenlink++;
		   }else {
			   System.out.println("The link is not  broken Link : "+ hrefValue);
		   }
			}catch(Exception e) {
				System.out.println("The link URL issue ");
			}
		}
		Thread.sleep(5000);
		System.out.println("The count of broken link is : "+ countOfBrokenlink);
		Thread.sleep(5000);
		driver.close();
	}

}
