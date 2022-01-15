package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.SodaStreamTopNav;

public class SodaStreamTopNavTest extends BaseClass {
	
	private ExtentTest test;
	
	@Test (priority =  0)
	public void setExtentReport() {
		test = extent.createTest("SodaStream Top Nav Test", "SodaStream Top Nav Test SodaStream");
	}
	
	@Test (priority =  1)
	public void topNavTest() {
		driver.get("https://sodastream.com/");
		SodaStreamTopNav objSodaStreamTopNav = new SodaStreamTopNav(driver);
		test.log(Status.INFO, "Click Sodastream logo");
		objSodaStreamTopNav.clickOnSodaStreamLogo();
		test.pass("User is navigated to home page");
		test.log(Status.INFO, "Click Support button");
		objSodaStreamTopNav.clickOnSupportButton();
		test.pass("User is navigated to support page");
		driver.navigate().back();
		test.log(Status.INFO, "Click Account button");
		objSodaStreamTopNav.clickOnAccountButton();
		test.pass("Log in page opens");
		driver.navigate().back();
		test.log(Status.INFO, "Click Cart button");
		objSodaStreamTopNav.clickOnCartIcon();
		test.pass("User is navigated to cart");
	}

}
