package PractiseQuestions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class toolTip {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method s.stub
		WebDriverManager.chromedriver().setup();
      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.get("https://jqueryui.com/tooltip/");
       driver.switchTo().frame(0);
       WebElement ele = driver.findElement(By.id("age"));
     Thread.sleep(2000);
     String text = ele.getAttribute("title");
       System.out.println(text);
       Thread.sleep(2000);

//        Video URl is : https://www.youtube.com/watch?v=3x7nf3tZ05w&t=273s
//driver.close();

	}

}
