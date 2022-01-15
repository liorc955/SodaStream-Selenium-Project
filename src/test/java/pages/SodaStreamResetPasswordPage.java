package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SodaStreamResetPasswordPage {
	
	private WebDriver driver = null;
	private String url = null;
	@FindBy (id = "RecoverEmail")
	private WebElement emailTextBox;
	@FindBy (xpath = "//input[@class='blue-bg btn btn-primary w-100 mt-4 fs18 font-weight-bold']")
	private WebElement submitButton;
	@FindBy (id = "ResetSuccess")
	private WebElement successResetMessage;
	
	public SodaStreamResetPasswordPage(WebDriver driver) {
		this.driver = driver;
		this.url = "https://sodastream.com/account/login?return_url=%2Faccount";
		PageFactory.initElements(driver, this);
	}
	
	public void navigate() {
		driver.get(url);
	}
	
	public void emailTextBox(String text) {
		emailTextBox.sendKeys(text);
	}
	
	public void clickSubmitButton() {
		submitButton.sendKeys(Keys.RETURN);
	}

	public String getUrl() {
		return url;
	}
	
	public void checkIfSuccessResetMessageAppear() {
		assertTrue(successResetMessage.isDisplayed());
	}

}
