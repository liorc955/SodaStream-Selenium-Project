package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import objects.CylinderItem;
import objects.Item;

public class SodaStreamSparePage extends SodaStreamFAQandReviewSection implements QuantityMethods {
	
	
	protected SodaStreamCartPage objSodaStreamCartPage = null;
	protected String decriptionOfCylinder = null;
	private String url = null;
	@FindBy (css = "img[class='img-fluid ml-3 user-select-none quantity-img pointer']")
	private WebElement plusQuantity;
	@FindBy (css ="img[class='img-fluid mr-3 user-select-none quantity-img pointer']")
	private WebElement minusQuantity;
	@FindBy (css = "input[name='quantity']")
	private WebElement quantityText;
	@FindBy (css = "button[name='add']")
	protected WebElement addToCartButton;
	@FindBy (css = "div[data-variant-id='39539279659050']")
	protected WebElement co2CylinderItem;
	@FindBy (css = "div[data-variant-id='39539279691818']")
	protected WebElement co2CylinderQuickConnectItem;
	private By titleOfItem = By.cssSelector("div[class='fs13 text-center font-weight-semi-bold pb-2 pb-lg-0']");
	private By priceOfItem = By.cssSelector("div[class='fs14 text-center']");
	
	public SodaStreamSparePage(WebDriver driver) {
		super(driver);
		this.decriptionOfCylinder = "Spare Cylinder";
		this.objSodaStreamCartPage = new SodaStreamCartPage(driver);
		this.url = "https://sodastream.com/products/spare-carbonator/";
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
	
	public Item addItemToCart(String cylinderType) { // Quick or CO2
		WebElement chosenCylinder = null;
		if (cylinderType.contains("CO2")) {
			chosenCylinder = co2CylinderItem;
		} else if (cylinderType.contains("Quick")) {
			chosenCylinder = co2CylinderQuickConnectItem;
		}
		Item cylinderItem = new CylinderItem(decriptionOfCylinder, getItemPrice(chosenCylinder), getItemTitle(chosenCylinder));
		chosenCylinder.click();
		return cylinderItem;
	}
	
	public void addAndCheckItemOnCart(String cylinderType) {
		Item item = addItemToCart(cylinderType);
		addToCartButton.click();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(item);
	}
	
	protected String getItemTitle(WebElement element) {
		return element.findElement(titleOfItem).getText();
	}
	
	protected String getItemPrice(WebElement element) {
		return element.findElement(priceOfItem).getText();
	}
	
	public void clickOnAddToCartButton() {
		addToCartButton.click();
	}
}
