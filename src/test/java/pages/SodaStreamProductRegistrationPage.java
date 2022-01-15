package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import objects.UserDeatils;

public class SodaStreamProductRegistrationPage extends AddressFieldsPageObject {
	
	private String url = null;
	private Select select = null;
	@FindBy (xpath = "//input[@name='email_address']")
	private WebElement email;
	@FindBy (xpath = "//input[@name='first_name']")
	private WebElement firstName;
	@FindBy (xpath = "//input[@name='last_name']")
	private WebElement lastName;
	@FindBy (xpath = "//select[@name='sodastream_model']")
	private WebElement selectSodaStreamModel;
	@FindBy (xpath = "//select[@name='purchase_location']")
	private WebElement selectPurchaseSite;
	@FindBy (xpath = "//input[@name='purchase_date']")
	private WebElement purchaseDatetBox;
	@FindBy (id = "submit-btn")
	private WebElement submitButton;
	@FindBy (id = "register-form-result")
	private WebElement successfulRegistrationMessage;
	
	public SodaStreamProductRegistrationPage(WebDriver driver) {
		super(driver);
		this.url = "https://sodastream.com/pages/register-your-product";
		PageFactory.initElements(driver, this);
		firstNameTextBox = firstName;
		lastNameTextBox = lastName;
		emailTextBox = email;
	}
	
	public void navigate() {
		driver.get(url);
	}
	
	public void insertUserDetailsIntoForm(UserDeatils user) {
		firstNameTextBox(user.getFirstName());
		lastNameTextBox(user.getLastName());
		emailTextBox(user.getEmail());
	}
	
	public void purchaseDateBox(String text) {
		purchaseDatetBox.sendKeys(text);
	}
	
	public void selectSodaStramModel(String nameOfModel) {
		select = new Select(selectSodaStreamModel);
		select.selectByValue(nameOfModel);
	}
	
	public void selectPurchaseSite(String nameOfSite) {
		select = new Select(selectPurchaseSite);
		select.selectByValue(nameOfSite);
	}
	
	public void clickOnSubmit() {
		submitButton.click();
	}
	
	public boolean checkRegistrationResult() {
		return successfulRegistrationMessage.isDisplayed();
	}
	
	public void checkErrorValidationMessage() {
		checkErrorValidationMessage(emailTextBox);
	}
	
}
