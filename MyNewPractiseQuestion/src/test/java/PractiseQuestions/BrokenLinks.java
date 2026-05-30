package PractiseQuestions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
<<<<<<< Updated upstream
import org.openqa.selenium.chrome.ChromeOptions;
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
		ChromeOptions option =new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		//https://www.yatra.com/
//		
		driver.get("https://www.ixigo.com/");
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
		   URL linkUrl = new URL(hrefValue); //	coverting th string to URL 
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
=======

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrokenLinks {

    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
      // http://www.deadlinkcity.com/
        driver.get("https://housivity.com/blog");
>>>>>>> Stashed changes

        int countOfBrokenlink = 0;

        // Collect all links
        List<WebElement> hrefElements = driver.findElements(By.tagName("a"));
        System.out.println("The total number of links found: " + hrefElements.size());

        // Use Set to avoid duplicate URLs
        Set<String> uniqueLinks = new HashSet<>();
        for (WebElement link : hrefElements) {
            String hrefValue = link.getAttribute("href");
            if (hrefValue != null && !hrefValue.trim().isEmpty() && !hrefValue.startsWith("javascript")) {
                uniqueLinks.add(hrefValue);
            }
        }

        System.out.println("Unique links to check: " + uniqueLinks.size());

        // Check each link
        for (String url : uniqueLinks) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setConnectTimeout(5000); // avoid hanging
                conn.connect();

                int statusCode = conn.getResponseCode();

                if (statusCode >= 400) {
                    System.out.println("❌ Broken link: " + url + " [Status: " + statusCode + "]");
                    countOfBrokenlink++;
                } else {
                    System.out.println("✅ Valid link: " + url + " [Status: " + statusCode + "]");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Error checking: " + url + " -> " + e.getMessage());
                countOfBrokenlink++;
            }
        }

        System.out.println("\nTotal unique links checked: " + uniqueLinks.size());
        System.out.println("Broken links found: " + countOfBrokenlink);

        driver.quit();
    }
}
