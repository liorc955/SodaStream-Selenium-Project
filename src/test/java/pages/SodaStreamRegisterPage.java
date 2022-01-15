package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import objects.UserDeatils;


public class SodaStreamRegisterPage extends AddressFieldsPageObject {
	
	
	private String url= null;
	private SodaStreamMyAccountPage objSodaStreamMyAccountPage = null;
	@FindBy (id = "FirstName")
	private WebElement firstName;
	@FindBy (id = "LastName")
	private WebElement lastName;
	@FindBy (id = "Email")
	private WebElement email;
	@FindBy (id = "CreatePassword")
	private WebElement passTextBox;
	@FindBy (xpath = "//*[@class='btn btn-primary w-100 mt-5 fs18 font-weight-bold']")
	private WebElement submitButton;
	
	
	public SodaStreamRegisterPage(WebDriver driver) {
		super(driver);
		this.url = "https://sodastream.com/account/register";
		this.objSodaStreamMyAccountPage = new SodaStreamMyAccountPage(driver);
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
		passTextBox(user.getPassword());
	}
	
	public void passTextBox(String text) {
		passTextBox.sendKeys(text);
	}
	
	public boolean clickOnSubmitButton() {
		submitButton.click();
		return driver.getCurrentUrl().equals(objSodaStreamMyAccountPage.getUrl());
	}
	
	public WebElement getFirstNameElement() {
		return this.firstNameTextBox;
	}

}
