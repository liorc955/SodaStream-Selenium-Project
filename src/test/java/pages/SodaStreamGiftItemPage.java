package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import objects.Item;
import utils.WebElementUtils;

public class SodaStreamGiftItemPage extends WebElementUtils implements QuantityMethods {
	
	
	private String url = null;
	private SodaStreamCartPage objSodaStreamCartPage = null;
	@FindBy (xpath = "//button[@name='add']")
	private WebElement buyNowButton;
	@FindBy (css = "div[class='qtyplus plus text-center']")
	private WebElement plusQuantity;
	@FindBy (css ="div[class='qtyminus minus text-center']")
	private WebElement minusQuantity;
	@FindBy (css = "input[name='quantity']")
	private WebElement quantityText;
	@FindBy (css = "h1[class='col-12 fs-sm-24 fs30']")
	private WebElement titleOfItem;
	@FindBy (xpath = "//div[@class='col-12 fs-sm-24 fs30']//div[@class='mb-2']")
	private WebElement priceOfItem;
	
	public SodaStreamGiftItemPage(WebDriver driver) {
		super(driver);
		this.url = driver.getCurrentUrl();
		PageFactory.initElements(driver, this);
	}
	
	public void navigate() {
		driver.get(url);
	}

	public void plusQuantity(int numberOfPlus) {
		int numberOfQuantityBefore = getNumberOfQuantity();
		for(int i=0; i<numberOfPlus; i++) {
			plusQuantity.click();
		}
		assertTrue(getNumberOfQuantity() == numberOfQuantityBefore + numberOfPlus);
	}

	public void minusQuantity(int numberOfMinus) {
		int numberOfQuantityBefore = getNumberOfQuantity();
		isElementPresent(minusQuantity); // if the minus button is enable
		for(int i=0; i<numberOfMinus; i++) {
			minusQuantity.click();
		}
		assertTrue(getNumberOfQuantity() == numberOfQuantityBefore - numberOfMinus);
	}
	
	public int getNumberOfQuantity() {
		return Integer.parseInt(getAttributeOfElement(quantityText, "value"));
	}
	
	public void clickOnBuyNowButton() {
		objSodaStreamCartPage = new SodaStreamCartPage(driver);
		buyNowButton.click();
		String price = priceOfItem.getText().split(" ")[0];
		Item item = new Item(titleOfItem.getText(), price);
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
	}
	
	
	

}
