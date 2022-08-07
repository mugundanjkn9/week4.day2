package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));

		//go to men--sports shoes and get count
		WebElement men=driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		Actions builder=new Actions(driver);
		builder.moveToElement(men).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='category-name category-count']")));
		String count=driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText().replaceAll("[()]", "");
		System.out.println("The count of sports shoes is "+count);

		//click training shoes,sort

		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sort-selected")));
		driver.findElement(By.className("sort-selected")).click();
		driver.findElement(By.xpath("(//ul[@class='sort-value']/li)[2]")).click();

		//select price range and color
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).sendKeys("500");
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("1000");
		driver.findElement(By.xpath("//div[@class=\"price-go-arrow btn btn-line btn-theme-secondary\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label[1]")).click();
		Thread.sleep(3000);

		//verify the filters
		System.out.println("The filters used are");	
		System.out.println(driver.findElement(By.xpath("(//div[@class=\"navFiltersPill\"])[1]")).getText());
		System.out.println(driver.findElement(By.xpath("(//div[@class='navFiltersPill'])[2]")).getText());

		//mouseover and click on quickview
		WebElement item=driver.findElement(By.xpath("//source[@class='product-image']/following::img"));
		builder.moveToElement(item).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Quick View')]")));
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();

		//print cost and discount		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='payBlkBig']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='percent-desc ']")));
		System.out.println("The cost and discount provided are");
		System.out.println(driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText());
		System.out.println(driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText());

		//screenshot
		File Source =driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("D:\\TestLeaf\\screenshots\\snapdeal screenshot.png");
		FileUtils.copyFile(Source, dest);
		System.out.println(FileUtils.getFile(Source,args));
		driver.close();
	}

}
