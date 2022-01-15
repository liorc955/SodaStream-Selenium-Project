package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import objects.UserDeatils;


public class CheckOutShippingAddress extends AddressFieldsPageObject  {
	
	
	@FindBy (id = "checkout_email")
	private WebElement email;
	@FindBy (id="checkout_shipping_address_first_name")
	private WebElement firstName;
	@FindBy (id= "checkout_shipping_address_last_name")
	private WebElement lastName;
	@FindBy (id = "checkout_shipping_address_address1")
	private WebElement addressOne;
	@FindBy (id = "checkout_shipping_address_address2")
	private WebElement addressTwo;
	@FindBy (id="checkout_shipping_address_city")
	private WebElement city;
	@FindBy (id = "checkout_shipping_address_country")
	private WebElement country;
	@FindBy (id = "checkout_shipping_address_province")
	private WebElement state;
	@FindBy (id = "checkout_shipping_address_zip")
	private WebElement zip;
	@FindBy (id = "checkout_shipping_address_phone")
	private WebElement phone;
	@FindBy (xpath = "//main[@class='main__content']//input[@placeholder]")
	private List<WebElement> listOfInputFields;
	@FindBy (xpath = "//main[@class='main__content']//select[@placeholder]")
	private List<WebElement> listOfSelectionFields;
	
	public CheckOutShippingAddress(WebDriver driver) {
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
		emailTextBox = email;
	}
	
	public void checkAllAttributeFields(UserDeatils user) {
		assertTrue(getAttributeOfElement(emailTextBox,"value").equals(user.getEmail()));
		assertTrue(getAttributeOfElement(firstNameTextBox,"value").equals(user.getFirstName()));
		assertTrue(getAttributeOfElement(lastNameTextBox,"value").equals(user.getLastName()));;
		assertTrue(getAttributeOfElement(addressOneTextBox,"value").equals(user.getAddressOne()));
		assertTrue(getAttributeOfElement(addressTwoTextBox,"value").equals(user.getAddressTwo()));
		assertTrue(getAttributeOfElement(cityTextBox,"value").equals(user.getCity()));
		assertTrue(getAttributeOfElement(selectCountry,"value").equals(user.getCountry()));
		assertTrue(getAttributeOfElement(selectState,"value").equals(user.getState()));
		assertTrue(getAttributeOfElement(zipTextBox,"value").equals(user.getZip()));
		assertTrue(getAttributeOfElement(phoneTextBox,"value").equals(printPhone(Long.parseLong(user.getPhone()))));
	}
	
	private String printPhone(Long phoneNum) {
	    StringBuilder sb = new StringBuilder(15);
	    StringBuilder temp = new StringBuilder(phoneNum.toString());

	    while (temp.length() < 10)
	        temp.insert(0, "0");

	    char[] chars = temp.toString().toCharArray();

	    sb.append("(");
	    for (int i = 0; i < chars.length; i++) {
	        if (i == 3)
	            sb.append(") ");
	        else if (i == 6)
	            sb.append("-");
	        sb.append(chars[i]);
	    }

	    return sb.toString();
	}
	
	
}
