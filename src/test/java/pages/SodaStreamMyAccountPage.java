package pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import objects.UserDeatils;
import utils.WebElementUtils;

public class SodaStreamMyAccountPage extends WebElementUtils {
	
	private String url = null;
	private AddNewAddressPopUp objAddNewAddressPopUp = null;
	private SodaStreamMainPage objSodaStreamMainPage = null;
	@FindBy (linkText = "MY ADDRESSES")
	private WebElement myAddressesLink;
	@FindBy (linkText = "Add a New Address")
	private WebElement addNewAddressButton;
	@FindBy (id = "newAddressLabel")
	private WebElement newAddressPopUp;
	@FindBy (xpath = "//div[@class='border p-5 border-black text-break address_box']//h3")
	private List<WebElement> listOfMyAddress;
	@FindBy (linkText = "LOG OUT")
	private WebElement logOutLink;
	
	public SodaStreamMyAccountPage(WebDriver driver) {
		super(driver);
		this.url = "https://sodastream.com/account";
		this.objSodaStreamMainPage = new SodaStreamMainPage(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getUrl() {
		return url;
	}
	
	public void clickOnMyAddressesLink() {
		checkUrlAfterClick(myAddressesLink);
	}
	
	public void clickOnNewAddress() {
		addNewAddressButton.click();
		isElementPresent(newAddressPopUp);
	}
	
	public void addNewAddress(UserDeatils user) {
		objAddNewAddressPopUp = new AddNewAddressPopUp(driver);
		objAddNewAddressPopUp.firstNameTextBox(user.getFirstName());
		objAddNewAddressPopUp.lastNameTextBox(user.getLastName());
		objAddNewAddressPopUp.companyTextBox(user.getCompany());
		objAddNewAddressPopUp.addressOneTextBox(user.getAddressOne());
		objAddNewAddressPopUp.addressTwoTextBox(user.getAddressTwo());
		objAddNewAddressPopUp.cityTextBox(user.getCity());
		objAddNewAddressPopUp.countrySelector(user.getCountry());
		objAddNewAddressPopUp.provinceSelector(user.getState());
		objAddNewAddressPopUp.zipTextBox(user.getZip());
		objAddNewAddressPopUp.phoneTextBox(user.getPhone());
		objAddNewAddressPopUp.clickOnSubmit();
	}
	
	public boolean checkIfAddressIsAdded(String addresOne) {
		for (int i=0; i<listOfMyAddress.size(); i++) {
			if (listOfMyAddress.get(i).getText().contains(addresOne)) {
				return true;
			}
		}
		return false;
	}

	public void clickOnLogOutLink() {
		logOutLink.click();
		assertEquals(driver.getCurrentUrl(), objSodaStreamMainPage.getUrl());
	}
}
