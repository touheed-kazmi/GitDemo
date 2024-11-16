package CreateOpportunity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class testClass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// using Firefox as other browsers are not opening in Accenture system
		WebDriver driver = new FirefoxDriver();
		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

		String closeDate = "Nov 5, 2024";
		String salesDate = "Nov 4, 2024";
		String oppName = "AOPP-01";
		String accName = "Dummy Account_Finland";

		driver.get("https://test.salesforce.com/");
		driver.manage().window().maximize();

		// login to Salesforce Org
		driver.findElement(By.id("username")).sendKeys("touheed.kazmi@gehc.amer.qa");
		driver.findElement(By.id("password")).sendKeys("new@1996");
		driver.findElement(By.cssSelector("#Login")).click();

		driver.navigate().to(
				"https://gehealthcare-amer--qa.sandbox.lightning.force.com/lightning/r/Opportunity/006DE00000zipeHYAQ/view");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[contains(@class,'highlights-primary-field')]")));
		WebElement name = driver.findElement(By.xpath(
				"//div[contains(@class,'highlights-primary-field')]"));
		System.out.println(name.getText());
		
		softAssert.assertEquals(name.getText(), "EMEA Step Automation");
		
		softAssert.assertAll();

	}

}
