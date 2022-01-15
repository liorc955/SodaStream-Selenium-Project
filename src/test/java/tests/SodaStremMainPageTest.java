package tests;


import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pages.SodaStreamMainPage;


public class SodaStremMainPageTest extends BaseClass {
	
	private ExtentTest test = null;
	private SodaStreamMainPage objSodaStreamMainPage;
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("SodaStrem Main Page Test", "SodaStrem Main Page test SodaStream");
	}
	
	@Test(priority =  1)
	public void mainPageTest() throws InterruptedException {
		objSodaStreamMainPage = new SodaStreamMainPage(driver);
		test.log(Status.INFO, "Navigate to SodaStrem site");
		objSodaStreamMainPage.navigate();
		test.log(Status.INFO, "Scroll down page till bottom");
		objSodaStreamMainPage.scrollPageToBottom();
		Thread.sleep(200);
		test.pass("Site can be scrolled without issues");
		objSodaStreamMainPage.scrollPageToUpper();
		Thread.sleep(200);
		test.log(Status.INFO, "Click all buttons in the promos below top nav and before footer");
		objSodaStreamMainPage.clickAllThePromoButtons();
		test.pass("All buttons leads to the correct page");
		test.log(Status.INFO, "At top nav click Shop");
		objSodaStreamMainPage.checkShopTopNav();
		test.pass("A menu opens with 3 options ");
		test.log(Status.INFO, "At top nav click Get Gas");
		objSodaStreamMainPage.checkGetGasTopNav();
		test.pass("A menu opens with 2 options ");
		test.log(Status.INFO, "Click Flavors");
		topNav.clickOnFlavorsTopNav();
		test.pass("The The Shop Flavors page opens\r\n"
				+ " Top nav and footer still appear without issues ");
		test.log(Status.INFO, "Click Gifts");
		topNav.clickOnGiftsTopNav();
		test.pass("The Gift page opens\r\n"
				+ " Top nav and footer still appear without issues ");
		test.log(Status.INFO, "Click Explore+");
		topNav.clickOnExploreTopNav();
		test.pass("The Explore content section opens\r\n"
				+ "Top nav and footer still appear without issues");
		
	}

}
