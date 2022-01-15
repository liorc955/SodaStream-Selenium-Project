package tests;


import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pages.SparklingWaterMakersPage;

public class SparklingWaterMakersPageTest extends BaseClass {
	

	private ExtentTest test;
	private SparklingWaterMakersPage objSparklingWaterMakersPage;
	String urlOfgoogleAd = "https://sodastream.com/products/fizzi-starter-kit?variant=14893718929450&utm_medium=cpc&utm_source=google&utm_campaign=Google_Shopping";
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("Sparkling Water Makers Test", "Sparkling Water Makers test SodaStream");
	}
	
	
	@Test (priority = 1)
	public void enterToFizziSpiritPage() {
		test.log(Status.INFO, "Navigate to FizziSpirit page");
		objSparklingWaterMakersPage = new  SparklingWaterMakersPage(driver);
		objSparklingWaterMakersPage.navigate();
		test.pass("The product details page opens");
	}
	
	@Test (priority = 2)
	public void checkThumbnailImages() throws InterruptedException {
		test.log(Status.INFO, "Click the thumbnail images at the left of the big image");
		objSparklingWaterMakersPage.clickOnAllThumbnailImages();
		test.pass("The images change accordingly");
	}
	
	@Test (priority = 3)
	public void checkDifferenPackImage() throws InterruptedException {
		test.log(Status.INFO, "Click to select a different pack at the right side");
		objSparklingWaterMakersPage.clickOnHydrationPackButton();
		Thread.sleep(200);
		objSparklingWaterMakersPage.checkIfHydrationPackBigImageIsDisplay();
		objSparklingWaterMakersPage.clickOnStarterPackButton();
		Thread.sleep(200);
		objSparklingWaterMakersPage.checkIfStarterPackButtonIsDisplay();
		objSparklingWaterMakersPage.clickOnHydrationPackButton();
		test.pass("The new package is selected - the info of the package appear, the image of new package appears");
	}
	
	@Test (priority = 4)
	public void checkDiffernetColors() throws InterruptedException {
		test.log(Status.INFO, "Click different colors");
		objSparklingWaterMakersPage.clickOnAllColors();
		test.pass("The image is changing accordingly");
	}
	
	@Test (priority = 5)
	public void checkBlueVariantGoogleCampaign() {
		test.log(Status.INFO, "Check the link is redirecting to a specific variant: Blue (we use this for marketing ads, and must redirect to specific colors to match the ad");
		driver.get(urlOfgoogleAd);
		objSparklingWaterMakersPage.checkIfColorIsSelected("Icy");
		test.pass("Bule variant is selected");
		driver.navigate().back();
	}
	
	@Test (priority = 6)
	public void checkVideoOnMouseOverImages() {
		test.log(Status.INFO, "Scroll down at the easy as module Mouse over on the three images");
		objSparklingWaterMakersPage.OnMouseOverAllVideoImages();
		test.pass("An animation is playing for each image");
	}
	
	@Test (priority = 7)
	public void checkScoreFilter() throws InterruptedException {
		test.log(Status.INFO, "Scroll down at the rating Click the columns with the stars to filter by column");
		objSparklingWaterMakersPage.clickOnRatingFilter();
		objSparklingWaterMakersPage.clickOnRatingOption(4);
		Thread.sleep(1000);
		objSparklingWaterMakersPage.checkRatingOfAllReviews(4);
		test.pass("Only the customer reviews with the selected stars appear below");
	}
	
	@Test (priority = 8)
	public void checkThumbButtonCounter() throws InterruptedException {
		test.log(Status.INFO, "On a customer review click the thumb up button");
		objSparklingWaterMakersPage.clickOnThumbButton(0, "Down");
		test.pass("The counter is increased by one");
		Thread.sleep(2000);
		test.log(Status.INFO, "Try to click again for the same review either thumb up or down");
		objSparklingWaterMakersPage.clickOnThumbButton(0, "Down");
		test.pass("The counter remains the same");
	}
	
	@Test (priority = 9)
	public void checkSortFilter() {
		test.log(Status.INFO, "From the right side select different sorting for the reviews (Most recent, Highest rating, Most votes etc");
		objSparklingWaterMakersPage.clickOnSelectFilter();
		objSparklingWaterMakersPage.chooseFilter("Highest Rating");
		objSparklingWaterMakersPage.checkFilterReview();
		test.pass("The reviews are shorted based on selection");
	}
	
	@Test (priority = 10)
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
	
	@Test (priority = 11)
	public void checkBuyPack() throws InterruptedException {
		test.log(Status.INFO, "At the top of the page Click Buy button");
		objSparklingWaterMakersPage.scrollPageToUpper();
		objSparklingWaterMakersPage.chooseColor("Black");
		objSparklingWaterMakersPage.buyPackItem();
		test.pass("The user goes to cart");
		test.pass("The product is added in cart");
		test.pass("Cart count is updated");
		test.pass("The details of package/color/price appear");
		test.pass("correctly added in cart");
	}
	
	@Test (priority = 12)
	public void checkAnotherPage() {
		test.log(Status.INFO, "Go back to previous page");
		test.log(Status.INFO, "At the top below top nav click another product to open it");
		driver.navigate().back();
		topNav.clickOnShopTopNav();
		topNav.clickOnOptionFromShopMenu(2);
		test.pass("The next product page opens");
	}
	
}
