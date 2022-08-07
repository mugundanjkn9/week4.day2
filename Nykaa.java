package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));

		//click on loreal paris and check title
		WebElement brand=driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(brand).perform();
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		String title=driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title is correct");
		}
		else {
			System.out.println("Title is incorrect");
		}

		//sort and select customer rated
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("(//label[@for='radio_customer top rated_undefined']//div)[2]")).click();

		//select category,hair,haircare and shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("(//label[@for='checkbox_Shampoo_316']/div)[2]")).click();

		//select concern and color protection and select shampoo
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[@for='checkbox_Color Protection_10764']/div)[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-1rd7vky']//div")));
		driver.findElement(By.xpath("//div[@class='css-1rd7vky']//div")).click();

		//switch to second window
		Set<String> allwindows=driver.getWindowHandles();
		List<String> listofwindows=new ArrayList<String>(allwindows);
		String secondwindow=listofwindows.get(1);
		driver.switchTo().window(secondwindow);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//span[text()='192.5ml']")).click();
		String MRP=driver.findElement(By.xpath("//span[@class='css-1jczs19']")).getText();
		System.out.println("The MRP of the product is "+MRP);

		//add to bag
		driver.findElement(By.xpath("//button[@class=' css-12z4fj0']/span")).click();

		//go to shopping bag
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='css-g4vs13']/span")));
		driver.findElement(By.xpath("//button[@class='css-g4vs13']/span")).click();
		driver.switchTo().frame(0);

		//get grand total and proceed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='first-col']/div")));
		String grandtotal=driver.findElement(By.xpath("//div[@class='first-col']/div")).getText();
		System.out.println("The grand total is "+grandtotal);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

		//continue as guest
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='CONTINUE AS GUEST']")));
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

		//check price
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//span")));
		String price=driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//span")).getText();
		System.out.println("Price is " +price);
		if(grandtotal.contains(price)) {
			System.out.println("Price is same");
		}
		else {
			System.out.println("Price is different");
		}
		driver.quit();
	}

}
