package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import objects.Item;
import pages.SodaStreamCartPage;
import pages.SodaStreamCheckOutPage;
import pages.SodaStreamGiftsAndFlavorsPage;

public class SodaStreamCheckOutPageTest extends BaseClass {
	
	private ExtentTest test;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	private SodaStreamCheckOutPage objSodaStreamCheckOutPage = null;
	private SodaStreamGiftsAndFlavorsPage objSodaStreamGiftsPage = null;
	private Item item = null;
	
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("SodaStream CheckOut Page Test", "SodaStream CheckOut Page Test SodaStream");
	}
	
	@Test(priority = 1)
	public void checkOutTest() throws InterruptedException {
		test.log(Status.INFO, "User is logged out\r\n"
				+ " Go to the cart with some items in there\r\n"
				+ " Click Checkout");
		assertEquals(topNav.checkIfUserAccountIconAppear(), false);
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		objSodaStreamCheckOutPage = new SodaStreamCheckOutPage(driver);
		objSodaStreamGiftsPage = new SodaStreamGiftsAndFlavorsPage(driver);
		driver.findElement(By.xpath("//*[text()='It’s ok, I want to browse the USA site']")).click();
		topNav.clickOnGiftsTopNav();
		item = objSodaStreamGiftsPage.addItemToCart(2);
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
		objSodaStreamCartPage.clickOnCheckOutButton();
		test.pass("Checkout page opens");
		objSodaStreamCheckOutPage.
				verifyTitleAndPriceInCheckOut(item);
		test.pass("The products in cart with the correct prices");
		assertTrue(objSodaStreamCheckOutPage.checkExpressCheckoutButtonDisplay());
		test.pass("The fields for the user to enter his details");
		assertTrue(objSodaStreamCheckOutPage.checkFieldsUserAreaDisplay());
		test.log(Status.INFO, "Click the options in express checkout module");
		objSodaStreamCheckOutPage.clickOnExpressButton();
		test.pass("A pop up opens with the login page of each mehtod");
		test.log(Status.INFO, "Close the express checkout pop up and return to checkout page");
		Thread.sleep(4000);
		test.pass("Back to checkout without issues");
	}
	
	@Test(priority = 2)
	public void continueCheckOutWithEmptyFields() {
		test.log(Status.INFO, "Leave fields empty and click Continue to shipping method");
		objSodaStreamCheckOutPage.clickOncontinueButton();
		assertTrue(objSodaStreamCheckOutPage.checkForErrorMessageAfterSubmit());
		test.pass("Not able to proceed \r\n"
				+ " Correct validation warning appear");
	}
	
	@Test (priority = 3)
	public void continueCheckOutWithValidFields() throws InterruptedException {
		test.log(Status.INFO, "Enter valid info and click Continue to shipping method");
		objSodaStreamCheckOutPage.insertShippingAddress(user);
		Thread.sleep(500);
		objSodaStreamCheckOutPage.clickOncontinueButton();
		objSodaStreamCheckOutPage.clickOnProceedPopUpButton();
		test.pass("Shipping methods appear");
		test.log(Status.INFO, "Make sure that all shipping methods can be selected, select one and proceed with Continue to payment method");
		objSodaStreamCheckOutPage.checkValidationOfShippingMethods();
		objSodaStreamCheckOutPage.clickOncontinueButton();
		test.pass("Payment method appears");
	}
	
	@Test (priority =  4)
	public void enterInValidCreditCard() {
		test.log(Status.INFO, "Select Credit card - enter invalid card details and click Complete order");
		objSodaStreamCheckOutPage.clickOnCreditCardMethod();
		objSodaStreamCheckOutPage.insertCreditCardMethodDetails(user.getCreditCard());
		objSodaStreamCheckOutPage.clickOncontinueButton();
		assertTrue(objSodaStreamCheckOutPage.checkForErrorMessageAfterInvalidCreditCard());
		test.pass("An error message appear");
	}
	
	@Test (priority =  5)
	public void checkPayPalMethodFunctionality() {
		test.log(Status.INFO, "Repeat process with Paypal");
		objSodaStreamCheckOutPage.clickOnPayPalMethod();
		objSodaStreamCheckOutPage.clickOncontinueButton();
		objSodaStreamCheckOutPage.verifyPayPalPageOpen();
		test.pass("Paypal pages appears correctly");
		driver.navigate().back();
	}
	
	@Test (priority =  6)
	public void succesfulyAddDifferentBillingAddress() {
		test.log(Status.INFO, "On Payment method select use a different billing address");
		objSodaStreamCheckOutPage.clickOnPayPalMethod();
		objSodaStreamCheckOutPage.clickOnDifferentBillingAddressOption();
		objSodaStreamCheckOutPage.insertBillingAddress(user);
		objSodaStreamCheckOutPage.clickOncontinueButton();
		objSodaStreamCheckOutPage.verifyPayPalPageOpen();
		test.pass("The user is able to enter a different biling address");
	}
	
	@Test (priority =  7)
	public void succesfulySavedAllTheDetailsInPreviousPages() {
		test.log(Status.INFO, "Click return to shipping method");
		driver.navigate().back();
		assertTrue(objSodaStreamCheckOutPage.checkPaypalMethodSelected());
		objSodaStreamCheckOutPage.
		verifyTitleAndPriceInCheckOut(item);
		assertTrue(objSodaStreamCheckOutPage.checkDifferentBillingAddressOptionSelected());
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		objSodaStreamCheckOutPage.
		verifyTitleAndPriceInCheckOut(item);
		assertTrue(objSodaStreamCheckOutPage.standardShippingOptionSelected());
		driver.navigate().back();
		objSodaStreamCheckOutPage.
		verifyTitleAndPriceInCheckOut(item);
		objSodaStreamCheckOutPage.checkShippingFields(user);
		test.pass("User is able to return to previous pages and all the details and selections are kept");
	}
	
}
