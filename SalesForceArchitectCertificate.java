package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceArchitectCertificate {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));

		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Learn More']")));
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		//switch to second window
		Set<String> allwindows=driver.getWindowHandles();
		List<String> listofwindows=new ArrayList<String>(allwindows);
		String secondwindow=listofwindows.get(1);
		driver.switchTo().window(secondwindow);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//locate shadow root element resources
		Shadow dom=new Shadow(driver);
		WebElement resources=dom.findElementByXPath("//span[text()='Resources']");
		resources.click();
		
		//mousehover on learning on trailhead and select salesforce certification
		WebElement learning=dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions builder=new Actions(driver);
		Thread.sleep(3000);
		builder.moveToElement(learning).perform();
		WebElement salesforce = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		builder.scrollToElement(salesforce).perform();
		salesforce.click();
		
		//click on certification administrator and verify title
		driver.findElement(By.linkText("Administrator")).click();
		String title=driver.findElement(By.xpath("//div[@class='certification-banner-text']/div")).getText();
		System.out.println(title);
		if(title.equalsIgnoreCase("Administrator")) {
			System.out.println("Title verified");
		}
		else {
			System.out.println("Wrong title");
		}
	}

}

/*
 * 1. Launch Salesforce application https://login.salesforce.com/
2. Login with username as "ramkumar.ramaiah@testleaf.com " and password as "Password$123"
3. Click on Learn More link in Mobile Publisher
4. Navigate to the Salesforce Customer Service
5. Mouse hover on Support & Services
6. Click on Ceritifications link
7. Navigate to Certification - Administrator Overview window
8. Verify the Certifications available for Administrator Certifications should be displayed
*/
