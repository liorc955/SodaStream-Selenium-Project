package pages;


import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import objects.CreditCardInformation;
import objects.Item;
import objects.UserDeatils;
import utils.WebElementUtils;

public class SodaStreamCheckOutPage extends WebElementUtils {
	
	
	private AddCreditCardCheckOutPage objAddCreditCardCheckOutPage = null;
	private CheckOutShippingAddress objCheckOutShippingAddress = null;
	private CheckOutBillingAddress objCheckOutBillingAddress = null;
	@FindBy (xpath = "//*[@class='_1qHzu06EhANVVG3mlfU6Ho paypalLight _1n3zwJXRK30xbubjNiZsKV']")
	private WebElement  expressCheckoutButton ;
	@FindBy (xpath = "//*[@class='product__description__name order-summary__emphasis']")
	private WebElement titleOfItemInCheckOut;
	@FindBy (xpath = "//*[@class='product__price']//span")
	private WebElement priceOfItemInCheckOut;
	@FindBy (tagName = "main")
	private WebElement fieldsUserArea;
	@FindBy (id = "checkout_remember_me")
	private WebElement saveAddressTextBox;
	@FindBy (id = "continue_button")
	private WebElement continueButton;
	@FindBy (id = "myModal")
	private WebElement errorSubmitMessage;
	@FindBy (id="btn-proceed-address")
	private WebElement proceedPopUpButton;
	@FindBy (xpath = "//div[@class='section section--shipping-method']//*[@class='section__content']")
	private List<WebElement> listOfShippingMethods;
	@FindBy (xpath = "//div[@data-gateway-name='credit_card']")
	private  WebElement creditCardMethod;
	@FindBy (id = "checkout_payment_gateway_18553077802")
	private WebElement paypalMethod;
	@FindBy (id = "checkout_different_billing_address_true")
	private WebElement differentBillingAddressOption;
	@FindBy (xpath = "//div[@class='notice notice--error default-background']")
	private WebElement invalidCreditCardError;
	@FindBy (id = "checkout_shipping_rate_id_shopify-standard20shipping-14_99")
	private WebElement 	standardShippingOption;
	
	public SodaStreamCheckOutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void verifyTitleAndPriceInCheckOut(Item item) {
		String titleOfItem = item.getItemDetails().get("titleOfItem");
		String priceOfItem = item.getItemDetails().get("priceOfItem");
		assertTrue(titleOfItemInCheckOut.getText().contains(titleOfItem) && priceOfItemInCheckOut.getText().contains(priceOfItem));
	}
	
	public boolean checkExpressCheckoutButtonDisplay() {
		return expressCheckoutButton.isDisplayed();
	}
	
	public boolean checkFieldsUserAreaDisplay() {
		return fieldsUserArea.isDisplayed();
	}
	
	public void clickOnExpressButton() {
		checkUrlInPopUpWindow(expressCheckoutButton, "paypal");
		
	}
	
	public void clickOncontinueButton() {
		continueButton.submit();
	}
	
	public boolean checkForErrorMessageAfterSubmit() {
		return errorSubmitMessage.isDisplayed();
	}
	
	public void insertShippingAddress(UserDeatils user) {
		objCheckOutShippingAddress = new CheckOutShippingAddress(driver);
		objCheckOutShippingAddress.emailTextBox(user.getEmail());
		objCheckOutShippingAddress.firstNameTextBox(user.getFirstName());
		objCheckOutShippingAddress.lastNameTextBox(user.getLastName());
		objCheckOutShippingAddress.addressOneTextBox(user.getAddressOne());
		objCheckOutShippingAddress.addressTwoTextBox(user.getAddressTwo());
		objCheckOutShippingAddress.cityTextBox(user.getCity());
		objCheckOutShippingAddress.provinceSelector(user.getState());
		objCheckOutShippingAddress.countrySelector(user.getCountry());
		objCheckOutShippingAddress.zipTextBox(user.getZip());
		objCheckOutShippingAddress.phoneTextBox(user.getPhone());
	}
	
	public void clickOnProceedPopUpButton() {
		waitForElementToBeClickable(proceedPopUpButton);
		proceedPopUpButton.click();
	}
	
	public void checkValidationOfShippingMethods() {
		assertTrue(listOfShippingMethods.size() >= 1);
	}
	
	public void clickOnPayPalMethod() {
		waitForElementToBeClickable(paypalMethod);
		paypalMethod.click();
	}
	
	public void clickOnCreditCardMethod() {
		waitForElementToBeClickable(creditCardMethod);
		creditCardMethod.click();
	}
	
	public void insertCreditCardMethodDetails(CreditCardInformation creditCard) {
		objAddCreditCardCheckOutPage = new AddCreditCardCheckOutPage(driver);
		objAddCreditCardCheckOutPage.creditCardNumberTextBox(creditCard.getCardNumber());
		objAddCreditCardCheckOutPage.nameOnCardTextBox(creditCard.getNameOnCard());
		objAddCreditCardCheckOutPage.expirationDate(creditCard.getExpirationDate());
		objAddCreditCardCheckOutPage.securityNumberTextBox(creditCard.getSecurityCode());
	}
	
	public void clickOnDifferentBillingAddressOption() {
		differentBillingAddressOption.click();
	}
	
	public void insertBillingAddress(UserDeatils user) {
		objCheckOutBillingAddress = new CheckOutBillingAddress(driver);
		objCheckOutBillingAddress.firstNameTextBox(user.getFirstName());
		objCheckOutBillingAddress.lastNameTextBox(user.getLastName());
		objCheckOutBillingAddress.addressOneTextBox(user.getAddressOne());
		objCheckOutBillingAddress.addressTwoTextBox(user.getAddressTwo());
		objCheckOutBillingAddress.cityTextBox(user.getCity());
		objCheckOutBillingAddress.countrySelector(user.getCountry());
		objCheckOutBillingAddress.provinceSelector(user.getState());
		objCheckOutBillingAddress.zipTextBox(user.getZip());
		objCheckOutBillingAddress.phoneTextBox(user.getPhone());
	}
	
	public void verifyPayPalPageOpen() {
		assertTrue(driver.getTitle().toLowerCase().contains("paypal"));
	}
	
	public boolean checkForErrorMessageAfterInvalidCreditCard() {
		waitForElementToBeVisibale(invalidCreditCardError);
		return invalidCreditCardError.isDisplayed();
	}
	
	public boolean checkPaypalMethodSelected() {
		return paypalMethod.isSelected();
	}
	
	public boolean checkDifferentBillingAddressOptionSelected() {
		return differentBillingAddressOption.isSelected();
	}
	
	public boolean standardShippingOptionSelected() {
		return standardShippingOption.isSelected();
	}
	
	public void checkShippingFields(UserDeatils user) {
		objCheckOutShippingAddress.checkAllAttributeFields(user);
	}

}
