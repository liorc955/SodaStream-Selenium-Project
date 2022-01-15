package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.SodaStreamFindStorePage;
import pages.SodaStreamProductRegistrationPage;

public class FooterAreaTest extends BaseClass {
	
	
	private ExtentTest test;
	private SodaStreamProductRegistrationPage objSodaStreamProductRegistrationPage = null;
	private SodaStreamFindStorePage objSodaStreamFindStorePage = null;
	
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("Footer Test", "Footer test SodaStream");
	}
	
	@Test (priority = 1) 
	public void checkLinksAndIcons() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='It’s ok, I want to browse the USA site']")));
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[text()='It’s ok, I want to browse the USA site']")).click();
		Thread.sleep(200);
		footer.scrollDownToFooter();
		Thread.sleep(200);
		test.log(Status.INFO, "On the left side\r\n"
				+ " Click FB icon");
		footer.clickOnFacebookLogo();
		test.pass("FB page opens");
		test.log(Status.INFO, "Click twitter icon");
		footer.clickOnTwittwerLogo();
		test.pass("Twitter page opens");
		test.log(Status.INFO, "Click Instagram");
		footer.clickOnInstagramLogo();
		test.pass("Instagram page opens");
		test.log(Status.INFO, "Click youtube icon");
		footer.clickOnYoutubeLogo();
		test.pass("Youtube page opens");
		test.log(Status.INFO, "Click all links under products category");
		footer.clickOnAllInformationLinks();
		test.pass("All pages opens correctly");
		test.log(Status.INFO, "Click all links under information category");
		footer.clickOnAllProductsLinks();
		test.pass("All pages opens correctly");
	}
	
	@Test (priority = 2) 
	public void registrationFaildTest() throws InterruptedException {
		objSodaStreamProductRegistrationPage = new SodaStreamProductRegistrationPage(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		test.log(Status.INFO, "Click product registration\r\n"
				+ " Try to submitt the form without having filled in all required fields");
		footer.clickOnLinkInInformationLinks("Product Registration");
		objSodaStreamProductRegistrationPage.clickOnSubmit();
		Thread.sleep(200);
		assertEquals(objSodaStreamProductRegistrationPage.checkRegistrationResult(), false);
		objSodaStreamProductRegistrationPage.checkErrorValidationMessage();
		test.pass("There is a red msg on each field that is missing or needs to be fixed");
	}
	
	@Test (priority = 3)  
	public void registrationSuccessTest() throws InterruptedException {
		test.log(Status.INFO, "Fill in all fields correctly and submitt");
		objSodaStreamProductRegistrationPage.insertUserDetailsIntoForm(user);
		objSodaStreamProductRegistrationPage.selectSodaStramModel("Jet");
		objSodaStreamProductRegistrationPage.selectPurchaseSite("Amazon.com");
		objSodaStreamProductRegistrationPage.purchaseDateBox("12/08/2021");
		Thread.sleep(600);
		objSodaStreamProductRegistrationPage.clickOnSubmit();
		Thread.sleep(600);
		assertTrue(objSodaStreamProductRegistrationPage.checkRegistrationResult());
		test.pass("A message about successful submitssion will appear");
	}
	
	@Test (priority = 4)
	public void checkFindAstorePage() throws InterruptedException {
		test.log(Status.INFO, "At footer click Find a store near you");
		objSodaStreamFindStorePage = new SodaStreamFindStorePage(driver);
		footer.clickOnfindStore();
		test.pass("Find a store page opens");
		test.log(Status.INFO, "Click any store from the list on left");
		objSodaStreamFindStorePage.sendTextToSearchBox("United States");
		objSodaStreamFindStorePage.clickOnStoreFromTheList(2);
		test.pass("The store is highlighted on the map");
		test.log(Status.INFO, "Verify find a store page");
		objSodaStreamFindStorePage.VerifyFindStorePageElements();
		test.pass("A list of stores at the left side\r\n"
				+ " A map with stores at the right side\r\n"
				+ " Filtering above map");
	}
}
