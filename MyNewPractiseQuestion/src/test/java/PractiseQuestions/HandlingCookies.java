package PractiseQuestions;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingCookies {

	public static void main(String[] args) {
		WebDriverManager.chromiumdriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		//https://myntra.com/
		//https://www.nopcommerce.com/en
//  Capturing the cookiew from browsers 
		Set <Cookie> cookie = driver.manage().getCookies();
		System.out.println("The cookies sizes are : "+cookie.size());
//	 Printing  cookies from browsers
		for(Cookie cookies :  cookie) {
			System.out.println(cookies.getName()+" : " +cookies.getValue() );
			
			
		}
//		Adding the cookies with name and value 
		Cookie cookieobj = new Cookie("Radha","Radha1234");
		driver.manage().addCookie(cookieobj);
		Set <Cookie> cookie2 = driver.manage().getCookies();
		System.out.println("The cookies sizes are : "+cookie2.size());
		for(Cookie cookies1 :  cookie2) {
			System.out.println(cookies1.getName()+" : " +cookies1.getValue() );
// Deleting a seleted cookie 
//			driver.manage().deleteCookie(cookieobj);
			driver.manage().deleteCookieNamed("Radha");
			cookie2 = driver.manage().getCookies();
			System.out.println("The cookies sizes are : "+cookie2.size());
//			Delete all cookies 
			driver.manage().deleteAllCookies();
			
			cookie = driver.manage().getCookies();
			System.out.println("The cookies sizes are : "+cookie.size());
			
		}
		
		driver.quit();
		
	}

}
