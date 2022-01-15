package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import objects.Item;

public class SodaStreamExchangePage extends SodaStreamSparePage {
	
	
	
	private String url = null;
	@FindBy (css = "div[data-variant-id='39539265601578']")
	private WebElement co2Cylinder;
	@FindBy (css = "div[data-variant-id='39539265568810']")
	private WebElement co2CylinderQuickConnect;
	@FindBy (css = "div[class='terms-checkbox-holder']")
	private WebElement agreeTermsCheckBox;
	
	
	public SodaStreamExchangePage(WebDriver driver) {
		super(driver);
		this.decriptionOfCylinder = "60L Cylinder - Exchange";
		this.url = "https://sodastream.com/products/refill";
		PageFactory.initElements(driver, this);
		this.co2CylinderItem = co2Cylinder;
		this.co2CylinderQuickConnectItem = co2CylinderQuickConnect;
	}
	
	public void navigate() {
		driver.get(url);
	}

	
	
	public void addAndCheckItemOnCart(String cylinderType) {
		Item item = addItemToCart(cylinderType);
		agreeTermsCheckBox.click();
		addToCartButton.click();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
	}
	
}
