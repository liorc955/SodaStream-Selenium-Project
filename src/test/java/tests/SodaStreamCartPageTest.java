package tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objects.Item;
import pages.SodaStreamCartPage;
import pages.SodaStreamGiftsAndFlavorsPage;

public class SodaStreamCartPageTest extends BaseClass {
	
	private ExtentTest test;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("SodaStream Cart Page Test", "SodaStream Cart Page SodaStream");
	}
	
	@Test (priority =  1)
	public void cartEmptyTest() {
		test.log(Status.INFO, "While the cart is empty click cart icon");
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		assertEquals(topNav.getValueOfCartCounter(), 0);
		topNav.clickOnCartIcon();
		assertTrue(objSodaStreamCartPage.checkIfCartPageIsEmpty());
		assertEquals(objSodaStreamCartPage.getNumberOfOfferItems() >= 1, true);
		test.pass("The cart opens\r\n"
				+ " It appears empty \r\n"
				+ " At below appear items that can be added to the cart");
	}
	
	@Test (priority =  2)
	public void checkOfferingItemsLinks() {
		test.log(Status.INFO, "Click the images of the items of right side");
		objSodaStreamCartPage.clickOnAllItemsImages();
		test.pass("The page with details of each product opens");
	}
	
	@Test (priority =  3)
	public void addItemToCartFromTheSite() {
		test.log(Status.INFO, "Add and remove  product accross the website");
		SodaStreamGiftsAndFlavorsPage objSodaStreamGiftsPage = new SodaStreamGiftsAndFlavorsPage(driver);
		objSodaStreamGiftsPage.navigate();
		Item item = objSodaStreamGiftsPage.addItemToCart(2);
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
		test.pass("The product is added to the cart \r\n"
				+ " The price is correctly updated\r\n"
				+ " The cart count is update");
	}
	
	@Test (priority =  4)
	public void checkPlusAndMinusQuantity() {
		test.log(Status.INFO, "Update the quantity with + and - button");
		objSodaStreamCartPage.plusQuantity(2);
		objSodaStreamCartPage.minusQuantity(1);
		test.pass("The quantity is updated \r\n"
				+ " Also the prices are correctly updated\r\n"
				+ " The cart count is update");
	}
	
	@Test (priority =  5)
	public void checkRemoveItemFromCart() throws InterruptedException {
		test.log(Status.INFO, "Click the - button until you remove the product from cart");
		objSodaStreamCartPage.removeItemFromCart();
		test.pass("Product removed\r\n"
				+ " The cart count is update");
	}
	
}
