package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class SodaStreamLoginPage extends WebElementUtils {
	
	
	private String url = null;
	private SodaStreamResetPasswordPage objSodaStreamResetPasswordPage = null;
	@FindBy (id = "CustomerEmail")
	private WebElement emailTextBox;
	@FindBy(id = "CustomerPassword")
	private WebElement passTextBox;
	@FindBy (xpath = "//*[text()='Sign In']")
	private WebElement signInButton;
	@FindBy (xpath = "//*[@class='errors']")
	private WebElement errorMessage; 
	@FindBy ( id = "customer_register_link")
	private WebElement registerLink;
	@FindBy (id="RecoverPassword")
	private WebElement forgotPasswordLink;
	
	public SodaStreamLoginPage(WebDriver driver) {
		super(driver);
		this.url = "https://sodastream.com/account/login?return_url=%2Faccount";
		this.objSodaStreamResetPasswordPage = new SodaStreamResetPasswordPage(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void navigate() {
		driver.get(url);
	}
	
	public void emailTextBox(String text) {
		emailTextBox.sendKeys(text);
	}
	
	public void passwordTextBox(String text) {
		passTextBox.sendKeys(text);
	}
	
	public void clickSignInButton() {
		signInButton.click();
	}
	
	public void checkIfErrorMessageApper() {
		assertTrue(errorMessage.isDisplayed());
	}
	
	public void clickOnRegisterLink() {
		checkUrlAfterClick(registerLink);
	}
	
	public void clickOnForgotPasswordLink() {
		forgotPasswordLink.click();
		assertEquals(driver.getCurrentUrl(), objSodaStreamResetPasswordPage.getUrl());
	}
	
	public String getUrl() {
		return url;
	}
	
	
}
