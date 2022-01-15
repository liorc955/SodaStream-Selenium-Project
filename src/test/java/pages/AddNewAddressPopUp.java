package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AddNewAddressPopUp extends AddressFieldsPageObject {

	@FindBy (id = "AddressFirstNameNew")
	private WebElement firstName;
	@FindBy (id = "AddressLastNameNew")
	private WebElement lastName;
	@FindBy (id = "AddressCompanyNew")
	private WebElement company;
	@FindBy (id = "AddressAddress1New")
	private WebElement addressOne;
	@FindBy (id = "AddressAddress2New")
	private WebElement addressTwo;
	@FindBy (id = "AddressCityNew")
	private WebElement city;
	@FindBy (id ="AddressCountryNew")
	private WebElement country;
	@FindBy (id = "AddressProvinceNew")
	private WebElement state;
	@FindBy (id = "AddressZipNew")
	private WebElement zip;
	@FindBy (id="AddressPhoneNew")
	private WebElement phone;
	@FindBy (id="submit_new")
	private WebElement submitButton;

	public AddNewAddressPopUp(WebDriver driver) {
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
	
	public void clickOnSubmit() {
		submitButton.click();
	}


}
