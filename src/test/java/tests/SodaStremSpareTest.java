package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.SodaStreamCartPage;
import pages.SodaStreamSparePage;


public class SodaStremSpareTest extends BaseClass {
	
	private ExtentTest test = null;
	private SodaStreamSparePage objSodaStreamSparePage = null;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("SodaStrem Spare Page Test", "Spare Page Test SodaStream");
	}
	
	@Test (priority =  1)
	public void enterToSparePage() {
		test.log(Status.INFO, "Navigate to Spare Page");
		topNav.clickOnGetGasTopNav();
		topNav.clickOnOptionFromGasMenu(1);
		test.pass("The page is open");
	}
	
	@Test (priority =  2)
	public void minusAndPlusQuantitySuccessTest() {
		test.log(Status.INFO, "Click +1 and -1 to adjust the items to be purchases");
		objSodaStreamSparePage = new SodaStreamSparePage(driver);
		objSodaStreamSparePage.plusQuantity(2);
		objSodaStreamSparePage.minusQuantity(1);
		test.pass("Values changing normally");
	}
	
	@Test (priority =  3)
	public void addItemToCartSuccessTest() throws InterruptedException {
		test.log(Status.INFO, "Click Add to cart button");
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		objSodaStreamSparePage.addAndCheckItemOnCart("CO2");
		test.pass("Items are added to the cart based on the quantity selected");
		objSodaStreamCartPage.removeItemFromCart();
	}
	
	@Test (priority =  4)
	public void checkAllQuestionsSuccessTest() {
		test.log(Status.INFO, "Back to previous page");
		test.log(Status.INFO, "At the question section click the questions");
		test.log(Status.INFO, "Click again a question that has been clicked previously");
		driver.navigate().back();
		objSodaStreamSparePage.clickOnAllQuestions();
		test.pass("When clicked the answer appears below question");
		test.pass("The answer is hidden again");
	}
	
	@Test (priority =  5)
	public void checkReadAllFaqsButtonSuccessTest() {
		test.log(Status.INFO, "Click Read all faqs");
		objSodaStreamSparePage.clickReadAllFAQButton();
		test.pass("All faqs appear");
	}
	

}
