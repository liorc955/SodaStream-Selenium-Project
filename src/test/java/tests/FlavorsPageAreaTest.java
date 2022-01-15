package tests;


import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objects.Item;
import pages.SodaStreamCartPage;
import pages.SodaStreamFlavorItemPage;
import pages.SodaStreamGiftsAndFlavorsPage;

public class FlavorsPageAreaTest extends BaseClass {

	private ExtentTest test;
	private SodaStreamGiftsAndFlavorsPage objSodaStreamFlavorsPage = null;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	private SodaStreamFlavorItemPage objSodaStreamFlavorItemPage = null;
	
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("Flavors Page Test", "Flavors Page test SodaStream");
	}
	
	@Test (priority = 1)
	public void filterSuccessTest() {
		objSodaStreamFlavorsPage = new SodaStreamGiftsAndFlavorsPage(driver);
		objSodaStreamFlavorsPage.setUrl("https://sodastream.com/collections/sparkling-water-flavors");
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		objSodaStreamFlavorItemPage = new SodaStreamFlavorItemPage(driver);
		test.log(Status.INFO, "At top nav click Flavors");
		topNav.clickOnFlavorsTopNav();
		test.pass("The Flavors page opens");
		test.pass("Top nav and footer still appear without issues");
		test.log(Status.INFO, "Click any filter (eg. Classic)");
		objSodaStreamFlavorsPage.clickAndCheckFilterOption("Diet");
		driver.navigate().back();
		test.pass("Flavor filtered page opens");
		test.log(Status.INFO, "Click all other flavor filter from the top");
		objSodaStreamFlavorsPage.clickAndCheckAllFilterOptions();
		test.pass("The results are updated and filtered correctly each time");
	}
	
	@Test (priority = 2)
	public void sortSuccessTest() {
		test.log(Status.INFO, "Click Sort by and verify all shorting options");
		objSodaStreamFlavorsPage.clickAndCheckAllSortOptions();
		test.pass("Results are shorted correctly each time");
	}
	
	@Test (priority = 3)
	public void addItemToCartSuccessTest() throws InterruptedException {
		test.log(Status.INFO, "Click Add to cart button");
		Item item = objSodaStreamFlavorsPage.addItemToCart(2);
		Thread.sleep(100);
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
		test.pass("Item is added to the cart and the customer keeps inside the collection page (make sure there's no automatic redirection to cart)");
		objSodaStreamCartPage.removeItemFromCart();
	}
	
	@Test (priority = 4)
	public void itemPageSuccessTest() {
		driver.navigate().back();
		test.log(Status.INFO, "Click on any product image (not quickshop button)");
		objSodaStreamFlavorsPage.clickOnItem(2);
		test.pass("The user is navigated to the details page of product");
	}
	
	@Test (priority = 5)
	public void viewLabelPopUpSuccessTest() {
		test.log(Status.INFO, "Scroll down and click View label button");
		objSodaStreamFlavorItemPage.scrollPageByPixels(0, 1500); // It depends on screen size
		objSodaStreamFlavorItemPage.clickOnViewlabelButton();
		assertTrue(objSodaStreamFlavorItemPage.labelsImagesPopUpIsDisplay());
		test.pass("A pop up with an image of the label of the product appears");
		driver.navigate().refresh();
		objSodaStreamFlavorItemPage.scrollPageToUpper();
	}
	
	@Test (priority = 6)
	public void buyNowItemSuccessTest()  {
		test.log(Status.INFO, "Click Buy Now button");
		objSodaStreamFlavorItemPage.clickOnBuyNowButton();
		test.pass("The user goes to cart\r\n"
				+ " The product is added in cart\r\n"
				+ " Cart count is updated\r\n"
				+ " The price appears correctly in cart");
	}
}
