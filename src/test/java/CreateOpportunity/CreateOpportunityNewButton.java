package CreateOpportunity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CreateOpportunityNewButton {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// using Firefox as other browsers are not opening in Accenture system
		WebDriver driver = new FirefoxDriver();
		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		String closeDate = "Nov 5, 2024";
		String salesDate = "Nov 4, 2024";
		String oppName = "AOPP-01";
		String accName = "Dummy Account_Finland";

		driver.get("https://test.salesforce.com/");
		driver.manage().window().maximize();

		// login to Salesforce Org
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		driver.findElement(By.id("username")).sendKeys("touheed.kazmi@gehc.amer.qa");
		driver.findElement(By.id("password")).sendKeys("new@1996");
		driver.findElement(By.cssSelector("#Login")).click();

		// Flow Opportunity tab --> New
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Opportunities']")));
		driver.findElement(By.xpath("//a[@title='Opportunities']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='New']")));
		driver.findElement(By.xpath("//a[@title='New']")).click();

		// record type = opportunity
		driver.findElement(By.xpath("//button[.='Next']")).click();

		// wait till last field is visible on crate record
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.='Budget Funding Status']")));

		// perform assertion for create record label
		String label = driver.findElement(By.xpath("//h3[text()='  New Opportunity']")).getText();
		softAssert.assertEquals(label, "  New Opportunity");

		Thread.sleep(5000);
		// Fill create opportunity page
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(oppName);
		driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).sendKeys(accName);

		Thread.sleep(3000);
		List<WebElement> accounts = driver.findElements(By.xpath("//ul[@role='group']"));
		for (WebElement options : accounts) {
			if (options.getText().equals(accName)) {
				options.click();
				break;
			}
		}

		// check opportunity step dropdown values
		String stage[] = { "--None--", "1. Understand Customer", "2. Discover Needs", "3. Build Strategy/Solution",
				"4. Develop Sponsorship", "5. Validate Solution", "6. Negotiate & Close" };

		ArrayList<String> expStage = new ArrayList<String>();
		expStage.addAll(Arrays.asList(stage));

		driver.findElement(By.xpath("//button[@aria-label='Opportunity Step']")).click();
		List<WebElement> stageName = driver
				.findElements(By.xpath("//div[@aria-label='Opportunity Step']/child::lightning-base-combobox-item"));

		ArrayList<String> arStage = new ArrayList<String>();
		for (WebElement step : stageName) {
			arStage.add(step.getText());
		}
		softAssert.assertEquals(arStage, expStage);

		// check opportunity socket type dropdown values
		String socket[] = { "--None--", "GE IB replacement", "Competition IB replacement", "New Socket",
				"Options & Upgrades", "Misc - Workstation/Apps/Accessories" };

		ArrayList<String> expSocket = new ArrayList<String>();
		expSocket.addAll(Arrays.asList(socket));

		driver.findElement(By.xpath("//button[@aria-label='Opportunity Socket Type']")).click();
		List<WebElement> socketType = driver.findElements(
				By.xpath("//div[@aria-label='Opportunity Socket Type']/child::lightning-base-combobox-item"));

		ArrayList<String> arSocket = new ArrayList<String>();
		for (WebElement type : socketType) {
			arSocket.add(type.getText());
		}
		softAssert.assertEquals(arSocket, expSocket);

		// select GE IB Replacement value socket type
		driver.findElement(By.xpath("//lightning-base-combobox-item[.='GE IB replacement']")).click();

		// check OFC LOVS
		String order[] = { "--None--", "Unqualified", "Not addressable", "Open", "Upside", "Committed w/risk",
				"Committed" };

		ArrayList<String> expOrder = new ArrayList<String>();
		expOrder.addAll(Arrays.asList(order));

		driver.findElement(By.xpath("//button[@aria-label='Order Forecast Category']")).click();
		List<WebElement> orderForecast = driver.findElements(
				By.xpath("//div[@aria-label='Order Forecast Category']/child::lightning-base-combobox-item"));

		ArrayList<String> arOrder = new ArrayList<String>();
		for (WebElement ofc : orderForecast) {
			arOrder.add(ofc.getText());
		}
		softAssert.assertEquals(arOrder, expOrder);

		// check SFC LOVS
		String sales[] = { "--None--", "Open", "Upside", "Committed At Risk", "Committed", "Invoiced" };

		ArrayList<String> expSales = new ArrayList<String>();
		expSales.addAll(Arrays.asList(sales));

		driver.findElement(By.xpath("//button[@aria-label='Sales Forecast Category']")).click();
		List<WebElement> salesForecast = driver.findElements(
				By.xpath("//div[@aria-label='Sales Forecast Category']/child::lightning-base-combobox-item"));

		ArrayList<String> arSales = new ArrayList<String>();
		for (WebElement sfc : salesForecast) {
			arSales.add(sfc.getText());
		}
		softAssert.assertEquals(arSales, expSales);

		// Fill Estimated order date
		driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(closeDate);

		// Fill Estimated sales date
		driver.findElement(By.xpath("//input[@name='Estimated_Sales_Date__c']")).sendKeys(salesDate);

		// Storing expected probability values
		String prob[] = { "--None--", "100", "80", "60", "40", "20", "0" };

		ArrayList<String> expProb = new ArrayList<String>();
		expProb.addAll(Arrays.asList(prob));

		// check EOD LOVS
		driver.findElement(By.xpath("//button[@aria-label='Probability to Order by EOD']")).click();
		List<WebElement> probEod = driver.findElements(
				By.xpath("//div[@aria-label='Probability to Order by EOD']/child::lightning-base-combobox-item"));

		ArrayList<String> arEod = new ArrayList<String>();
		for (WebElement eod : probEod) {
			arEod.add(eod.getText());
		}
		softAssert.assertEquals(arEod, expProb);

		// check GE LOVs
		driver.findElement(By.xpath("//button[@aria-label='Probability to Order from GE']")).click();
		List<WebElement> proGe = driver.findElements(
				By.xpath("//div[@aria-label='Probability to Order from GE']/child::lightning-base-combobox-item"));

		ArrayList<String> arGe = new ArrayList<String>();
		for (WebElement ge : proGe) {
			arGe.add(ge.getText());
		}
		softAssert.assertEquals(arGe, expProb);

		// check Budget LOVS
		String status[] = { "--None--", "Budget not yet requested", "Budget approved", "Budget denied",
				"Budget requested, not yet approved" };

		ArrayList<String> expStatus = new ArrayList<String>();
		expStatus.addAll(Arrays.asList(status));

		driver.findElement(By.xpath("//button[@aria-label='Budget Funding Status']")).click();
		List<WebElement> budgetStatus = driver.findElements(
				By.xpath("//div[@aria-label='Budget Funding Status']/child::lightning-base-combobox-item"));

		ArrayList<String> arBudget = new ArrayList<String>();
		for (WebElement budget : budgetStatus) {
			arBudget.add(budget.getText());
		}
		softAssert.assertEquals(arBudget, expStatus);

		// click on save
		driver.findElement(By.xpath("//button[text()='Save']")).click();

		// print the error message
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[contains(text(),'Estimated Sales Date must be greater than or equal')]")));
		String dateValidation = (driver
				.findElement(By.xpath("//p[contains(text(),'Estimated Sales Date must be greater than or equal')]"))
				.getText());
		Assert.assertEquals(dateValidation,
				"Estimated Sales Date must be greater than or equal to Estimated Order Date");

		// validate create record fields
		String fields[] = { "", "", "", "", "", "*Name", "*Account Name", "Secondary Owner", "*Opportunity Currency",
				"*Opportunity Step", "*Opportunity Socket Type", "*Order Forecast Category", "Competitor Owning IB",
				"*Sales Forecast Category", "Primary Contact", "Search Record", "*Estimated Order Date",
				"Parent Opportunity", "*Estimated Sales Date", "Competitor", "Global Region",
				"Probability to Order by EOD", "Probability to Order from GE", "Budget Funding Status",
				"Appointment Complete" };

		ArrayList<String> expFields = new ArrayList<String>();
		expFields.addAll(Arrays.asList(fields));

//		System.out.println(expFields);

		List<WebElement> createfields = driver.findElements(By.xpath("//label"));

		ArrayList<String> arFields = new ArrayList<String>();
		for (WebElement field : createfields) {
			arFields.add(field.getText());
		}
//		System.out.println(arFields);
		softAssert.assertEquals(arFields, expFields);

		Thread.sleep(3000);
		// renter sales date and save
		driver.findElement(By.xpath("//input[@name='Estimated_Sales_Date__c']")).clear();
		driver.findElement(By.xpath("//input[@name='Estimated_Sales_Date__c']")).sendKeys(closeDate);

		// click on save
//		driver.findElement(By.xpath("//button[text()='Save']")).click();
//
//		// check created opportunity name
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'highlights-primary-field')]")));
//		WebElement name = driver.findElement(By.xpath("//div[contains(@class,'highlights-primary-field')]"));
//		System.out.println(name.getText());
//
//		softAssert.assertEquals(name.getText(), "AOPP-01");

		softAssert.assertAll();
	}

}
