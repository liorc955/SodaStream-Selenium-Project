package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objects.Item;
import pages.SodaStreamCartPage;
import pages.SodaStreamGiftItemPage;
import pages.SodaStreamGiftsAndFlavorsPage;

// On the assumption that the Accessories Page belongs to the Gifts Page


public class AccessoriesPageTest extends BaseClass {
	
	private ExtentTest test;
	private SodaStreamGiftsAndFlavorsPage objSodaStreamAccessoriesPage = null;
	private SodaStreamGiftItemPage objSodaStreamGiftItemPage = null;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("Accessories Page Test", "Accessories Page test SodaStream");
	}
	
	
	@Test (priority = 1)
	public void goAccessoriesPage() {
		objSodaStreamAccessoriesPage = new SodaStreamGiftsAndFlavorsPage(driver);
		objSodaStreamAccessoriesPage.setUrl("https://sodastream.com/collections/gifts/Accessories-Gifts");
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		test.log(Status.INFO, "Navigate to FizziSpirit page");
		topNav.clickOnGiftsTopNav();
		objSodaStreamAccessoriesPage.clickAndCheckFilterOption("Accessories");
		test.pass("The product details page opens");
	}
	
	@Test (priority = 2)
	public void addItemToCartSuccessTest() throws InterruptedException {
		test.log(Status.INFO, "Click Add to cart button");
		Item item = objSodaStreamAccessoriesPage.addItemToCart(2);
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
		test.pass("The user keeps inside the collection page (there's no automatic redirection to cart)\r\n"
				+ " The product is added in cart\r\n"
				+ " Cart count is updated\r\n"
				+ " The price appears correctly in cart");
		objSodaStreamCartPage.removeItemFromCart();
	}
	
	@Test (priority = 3)
	public void sortSuccessTest() {
		test.log(Status.INFO, "Return to previous page");
		driver.navigate().back();
		test.log(Status.INFO, "Click Sort by and verify all shorting options");
		objSodaStreamAccessoriesPage.clickAndCheckAllSortOptions();
		test.pass("Results are sorted correctly each time");
	}
	
	@Test (priority = 4)
	public void itemPageSuccessTest() {
		test.log(Status.INFO, "Click on a product image (not quickshop button)");
		objSodaStreamAccessoriesPage.clickOnItem(2);
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
