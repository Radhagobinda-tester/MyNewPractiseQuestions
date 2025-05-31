package PractiseQuestions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class upladingFilesByUsingSendKeysMethod {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://davidwalsh.name/demo/multiple-file-upload.php");
		Thread.sleep(3000);
//	    driver.findElement(By.id("filesToUpload")).sendKeys("C:\\Users\\ADMIN\\OneDrive\\Desktop\\Radha Gobinda Dash MT Updated resume.pdf");
	    Thread.sleep(3000);
//	    Entering multiple files 
	    String file1 = "C:\\Users\\ADMIN\\OneDrive\\Desktop\\Radha Gobinda Dash MT Updated resume.pdf";
	    String file2 = "C:\\Users\\ADMIN\\OneDrive\\Desktop\\Radha Gobinda Dash MT Updated resume.pdf";
//	    \n will move to next line 
	    driver.findElement(By.id("filesToUpload")).sendKeys(file1+"\n"+file2);
	    Thread.sleep(3000);
         String text = driver.findElement(By.xpath("//ul[@id='fileList']//li")).getText();
         if(text.equalsIgnoreCase("Radha Gobinda Dash MT Updated resume.pdf")) {
        	 System.out.println("File uploaded");
         }else {
        	 System.out.println("File not uploaded");

         }
         Thread.sleep(3000);		 
        driver.quit();
		

	}

}
