package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.SodaStreamLoginPage;
import pages.SodaStreamMyAccountPage;
import pages.SodaStreamRegisterPage;
import pages.SodaStreamResetPasswordPage;

public class MyAccountAreaTest extends BaseClass {
	
	private ExtentTest test;
	private SodaStreamLoginPage objSodaStreamLoginPage = null;
	private SodaStreamRegisterPage objSodaStreamRegisterPage = null;
	private SodaStreamMyAccountPage objSodaStreamMyAccountPage = null;
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("My Account Test", "My Account test SodaStream");
	}
	
	@Test (priority = 1)
	public void failedLogin() {
		test.log(Status.INFO, "From the top nav click My account button");
		topNav.clickOnAccountButton();
		test.pass("Login page appears");
		test.log(Status.INFO, "Try to login with an account that does not exist");
		objSodaStreamLoginPage = new SodaStreamLoginPage(driver);
		objSodaStreamLoginPage.emailTextBox("text@text.com");
		objSodaStreamLoginPage.passwordTextBox("Aa123456");
		objSodaStreamLoginPage.clickSignInButton();
		objSodaStreamLoginPage.checkIfErrorMessageApper();
		test.pass("INCORRECT USERNAME OR PASSWORD message appears");
		test.log(Status.INFO, "Click Don't have a Sodastream account yet?");
		objSodaStreamLoginPage.clickOnRegisterLink();
		test.pass("Register page opens");
	}
	
	@Test (priority = 2)
	public void failedRegistration() {
		test.log(Status.INFO, "Try to register by leaving fields blank");
		objSodaStreamRegisterPage = new SodaStreamRegisterPage(driver);
		assertEquals(objSodaStreamRegisterPage.clickOnSubmitButton(), false);
		objSodaStreamRegisterPage.checkErrorValidationMessage(objSodaStreamRegisterPage.getFirstNameElement());
		test.pass("The correct verification messages appears");
	}
	
	/*  I Can't run 3, 4 and 5 tests anymore because probably SodaStream blocked me after I ran those tests a few times */
	//@Test (priority = 3)
	public void successfulRegistration() {
		test.log(Status.INFO, "Fill in all fields with valid data and click Create account");
		objSodaStreamRegisterPage.insertUserDetailsIntoForm(user);
		assertTrue(objSodaStreamRegisterPage.clickOnSubmitButton());
		test.pass("The user is logged in");
		
	}
	
	
	//@Test (priority = 4)
	public void verifyMyAccountPage() { 
		topNav.clickOnSodaStreamLogo();
		test.log(Status.INFO, "While logged in go to homepage\r\n"
				+ " From the top nav click My account button");
		topNav.clickOnAccountButton();
		objSodaStreamMyAccountPage = new SodaStreamMyAccountPage(driver);
		objSodaStreamMyAccountPage.clickOnMyAddressesLink();
		test.pass("Account overview appears");
	}
	
	//@Test (priority = 5)
	public void successfulAddNewAddress() throws InterruptedException {
		test.log(Status.INFO, "Click Add new address");
		objSodaStreamMyAccountPage.clickOnNewAddress();
		Thread.sleep(200);
		test.pass("ADD A NEW ADDRESS pop up appears");
		test.log(Status.INFO, "Add a new address");
		objSodaStreamMyAccountPage.addNewAddress(user);
		assertTrue(objSodaStreamMyAccountPage.checkIfAddressIsAdded(user.getAddressOne()));
		test.pass("New address successfully added");
		test.log(Status.INFO, "Click logout");
		objSodaStreamMyAccountPage.clickOnLogOutLink();
		test.pass("The user is logged out");
	}
	
	@Test (priority = 6)
	public void successfulRecoverPassword() throws InterruptedException {
		test.log(Status.INFO, "From the top nav click My account button\r\n"
				+ " Click forgot password");
		topNav.clickOnAccountButton();
		objSodaStreamLoginPage.clickOnForgotPasswordLink();
		test.pass("Forgot password page appears");
		test.log(Status.INFO, "Enter your account email and click Recover account");
		SodaStreamResetPasswordPage objSodaStreamResetPasswordPage = new SodaStreamResetPasswordPage(driver);
		objSodaStreamResetPasswordPage.emailTextBox(user.getEmail());
		objSodaStreamResetPasswordPage.clickSubmitButton();
		Thread.sleep(1500);
		objSodaStreamResetPasswordPage.checkIfSuccessResetMessageAppear();
		test.pass("User received an email with further instructions");
	}


}
