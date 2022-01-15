package tests;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pages.SparklingWaterMakersPage;

public class OneTouchPageTest extends BaseClass {
	
	private ExtentTest test;
	private SparklingWaterMakersPage objSparklingWaterMakersPage;
	
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("One Touch Page Test", "One Touch Page test SodaStream");
	}
	
	@Test (priority =  1)
	public void navigateToOneTouchPage() {
		test.log(Status.INFO, "Select Water Maker Type One Touch");
		topNav.clickOnShopTopNav();
		topNav.clickOnOptionFromShopMenu(1);
		test.pass("Water maker is selected");
	}
	
	@Test (priority =  2)
	public void selectColorTest() {
		test.log(Status.INFO, "Select Color");
		objSparklingWaterMakersPage = new SparklingWaterMakersPage(driver);
		objSparklingWaterMakersPage.chooseColor("Black");
		test.pass("Color is selected");
	}
	
	@Test (priority =  3)
	public void clickOnBuyNowTest() throws InterruptedException {
		test.log(Status.INFO, "Confirm Term and click Buy  now");
		objSparklingWaterMakersPage.buyPackItem();
		test.pass("Items are added to the cart");
		driver.navigate().back();
		
	}
	
	@Test (priority =  4)
	public void checkAllQuestionsAndFAQ() {
		test.log(Status.INFO, "At the question section click the questions");
		test.log(Status.INFO, "Click again a question that has been clicked previously");
		objSparklingWaterMakersPage.clickOnAllQuestions();
		test.pass("When clicked the answer appears below question");
		test.pass("The answer is hidden again");
		test.log(Status.INFO, "Click Read all faqs");
		objSparklingWaterMakersPage.clickReadAllFAQButton();
		test.pass("All faqs appear");
	}
	
	@Test (priority =  5)
	public void clickWriteReviewSuccessTest() {
		test.log(Status.INFO, "Click the rating stars");
		objSparklingWaterMakersPage.clickOnRatingStars();
		test.pass("Page is navigating to rating area");
		test.log(Status.INFO, "Click write a review");
		objSparklingWaterMakersPage.clickOnWriteReviewButton();
		objSparklingWaterMakersPage.checkWriteReviewTabPanelDisplay();
		test.pass("Write a review pops up");
	}
	
	@AfterSuite
	public void teardownHtml() {
		extent.flush();
	}
	
	@AfterSuite
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
