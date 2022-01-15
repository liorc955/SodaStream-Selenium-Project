package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.SodaStreamCartPage;
import pages.SodaStreamExchangePage;

public class SodaStreamExchangePageTest extends BaseClass {
	
	private ExtentTest test = null;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	private SodaStreamExchangePage objSodaStreamExchangePage = null;
	
	
	@Test (priority =  1)
	public void enterToExchangePage() {
		test = extent.createTest("SodaStrem Exchange Page Test", "Exchange Page Test SodaStream");
		test.log(Status.INFO, "Navigate to Exchange Page");
		topNav.clickOnGetGasTopNav();
		topNav.clickOnOptionFromGasMenu(0);
		test.pass("The page is open");
	}
	
	@Test (priority =  2)
	public void minusAndPlusQuantitySuccessTest() {
		test.log(Status.INFO, "Click +1 and -1 to adjust the items to be purchases");
		objSodaStreamExchangePage = new SodaStreamExchangePage(driver);
		objSodaStreamExchangePage.plusQuantity(2);
		objSodaStreamExchangePage.minusQuantity(1);
		test.pass("Values changing normally");
	}
	
	@Test (priority =  3)
	public void addItemToCartSuccessTest() throws InterruptedException {
		test.log(Status.INFO, "Click Add to cart button");
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		objSodaStreamExchangePage.addAndCheckItemOnCart("Quick");
		test.pass("Items are added to the cart based on the quantity selected");
		objSodaStreamCartPage.removeItemFromCart();
	}
	
	@Test (priority =  4)
	public void clickWriteReviewSuccessTest() {
		driver.navigate().back();
		test.log(Status.INFO, "Click write a review");
		objSodaStreamExchangePage.clickOnWriteReviewButton();
		objSodaStreamExchangePage.checkWriteReviewTabPanelDisplay();
		test.pass("Write a review pops up");
	}
	
	
}
