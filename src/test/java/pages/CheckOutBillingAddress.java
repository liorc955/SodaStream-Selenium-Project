package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckOutBillingAddress extends AddressFieldsPageObject {
	
	
	@FindBy (id="checkout_billing_address_first_name")
	private WebElement firstName;
	@FindBy (id= "checkout_billing_address_last_name")
	private WebElement lastName;
	@FindBy (id = "checkout_billing_address_address1")
	private WebElement addressOne;
	@FindBy (id = "checkout_billing_address_address2")
	private WebElement addressTwo;
	@FindBy (id="checkout_billing_address_city")
	private WebElement city;
	@FindBy (id = "checkout_billing_address_country")
	private WebElement country;
	@FindBy (id = "checkout_billing_address_province")
	private WebElement state;
	@FindBy (id = "checkout_billing_address_zip")
	private WebElement zip;
	@FindBy (id = "checkout_billing_address_phone")
	private WebElement phone;
	
	public CheckOutBillingAddress(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		firstNameTextBox = firstName;
		lastNameTextBox = lastName;
		addressOneTextBox = addressOne;
		addressTwoTextBox = addressTwo;
		cityTextBox = city;
		selectCountry = country;
		selectState = state;
		zipTextBox = zip;
		phoneTextBox = phone;
	}
	
}
