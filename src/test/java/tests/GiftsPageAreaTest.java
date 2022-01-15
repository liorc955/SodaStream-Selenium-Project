package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objects.Item;
import pages.SodaStreamCartPage;
import pages.SodaStreamGiftItemPage;
import pages.SodaStreamGiftsAndFlavorsPage;

public class GiftsPageAreaTest extends BaseClass {
	
	private ExtentTest test;
	private SodaStreamGiftsAndFlavorsPage objSodaStreamGiftsPage = null;
	private SodaStreamGiftItemPage objSodaStreamGiftItemPage = null;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("Gifts Page Test", "Gifts Page test SodaStream");
	}
	
	
	@Test (priority = 1)
	public void filterSuccessTest() {
		objSodaStreamGiftsPage = new SodaStreamGiftsAndFlavorsPage(driver);
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		test.log(Status.INFO, "At top nav click Gifts");
		topNav.clickOnGiftsTopNav();
		test.pass("The Gift page opens\r\n"
				+ " Top nav and footer still appear without issues");
		test.log(Status.INFO, "Click all other gifts filter from the top");
		objSodaStreamGiftsPage.clickAndCheckAllFilterOptions();
		test.pass("The results are updated and filtered correctly each time");
		
	}
	
	@Test (priority = 2)
	public void sortSuccessTest() {
		test.log(Status.INFO, "Click Sort by and verify all shorting options");
		objSodaStreamGiftsPage.clickAndCheckFilterOption("All");
		objSodaStreamGiftsPage.clickAndCheckAllSortOptions();
		test.pass("Results are shorted correctly each time");
	}
	
	@Test (priority = 3)
	public void addItemToCartSuccessTest() throws InterruptedException {
		test.log(Status.INFO, "Click Add to cart button");
		Item item = objSodaStreamGiftsPage.addItemToCart(2);
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
		test.pass("The user keeps inside the collection page (there's no automatic redirection to cart)\r\n"
				+ " The product is added in cart\r\n"
				+ " Cart count is updated\r\n"
				+ " The price appears correctly in cart");
		objSodaStreamCartPage.removeItemFromCart();
	}
	
	@Test (priority = 4)
	public void itemPageSuccessTest() {
		driver.navigate().back();
		test.log(Status.INFO, "Click on a product image");
		objSodaStreamGiftsPage.clickOnItem(2);
		test.pass("The product detail page opens");
	}
	
	@Test (priority = 5)
	public void minusAndPlusQuantitySuccessTest() {
		test.log(Status.INFO, "Click the + and - button at the right module");
		objSodaStreamGiftItemPage = new SodaStreamGiftItemPage(driver);
		objSodaStreamGiftItemPage.plusQuantity(2);
		objSodaStreamGiftItemPage.minusQuantity(1);
		test.pass("The quantity is updated");
	}
	
	@Test (priority = 6)
	public void buyNowItemSuccessTest() {
		test.log(Status.INFO, "Click Buy now button");
		objSodaStreamGiftItemPage.clickOnBuyNowButton();
		test.pass("The user goes to cart\r\n"
				+ " The product is added in cart\r\n"
				+ " Cart count is updated\r\n"
				+ " The price appears correctly in cart");
	}

}
