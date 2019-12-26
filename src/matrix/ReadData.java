package matrix;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadData {
	public WebDriver driver;
	@DataProvider(name="Authentication")
	public Object[][] loginData() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Object[][] arrobj=Dataprvidr.getData();
		return arrobj;	
	}
	@BeforeMethod
	public void openappln()
	{
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		driver.get("https://www.flipkart.com");
	}
	@Test(dataProvider="Authentication")
	public void login(String username,String pword)
	{
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pword);
		driver.findElement(By.xpath("(//span[.='Login'])[3]")).click();
		String title = driver.getTitle();
		Assert.assertEquals(title, "");
	}
	@AfterMethod
	public void closeAppln()
	{
		driver.close();
	}
	
}
