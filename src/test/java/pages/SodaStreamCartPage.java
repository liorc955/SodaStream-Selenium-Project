package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import objects.CylinderItem;
import objects.Item;
import objects.PackItem;
import utils.WebElementUtils;

public class SodaStreamCartPage extends WebElementUtils implements QuantityMethods {

	
	private SodaStreamTopNav objSodaStreamTopNav = null;
	private String url = null;
	@FindBy (xpath = "//*[@class='mt-5 mb-5']")
	private WebElement emptyCartMessage;
	@FindBy (xpath = "//*[@class='single-product s-r col-12 col-md-6 col-lg-3 pb-5 mb-5']")
	private List<WebElement> offerItemsInCart;
	@FindBy (xpath = "//div[@class='row']//p[@class='mb-0']")
	private WebElement priceOfItemInCart;
	@FindBy (xpath = "//div[@class='row']//h5[@class='fs20 mx-3 mx-md-3 fs-sm-13 mb-0']")
	private WebElement quantityOfItemInCart;
	@FindBy (xpath = "//*[@class='text-dark mb-2']")
	private WebElement titleOfItemInCart;
	@FindBy (xpath = "//div[@class='row']//div[@class='plus text-center']//img")
	private WebElement plusQuantityOfItem;
	@FindBy (xpath = "//div[@class='row']//div[@class='minus text-center']//img")
	private WebElement minusQuantityOfItem;
	@FindBy (xpath = "//*[@class='fs20 fs-sm-18 col-lg-3 d-none d-lg-flex flex-column justify-content-center pb-4 pb-md-0']")
	private WebElement totalPriceOfItemInCart;
	@FindBy (xpath = "//*[@class='fs13 mb-2 cart-gray']")
	private WebElement descriptionOfItemInCart;
	@FindBy (xpath ="//button[@name='checkout']")
	private WebElement checkOutButton;

	public SodaStreamCartPage(WebDriver driver) {
		super(driver);
		this.url = "https://sodastream.com/cart";
		this.objSodaStreamTopNav = new SodaStreamTopNav(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(url);
	}
	
	public boolean checkIfCartPageIsEmpty() {
		return isElementPresent(emptyCartMessage);
	}
	
	public int getNumberOfOfferItems() {
		return offerItemsInCart.size();
	}

	public void clickOnAllItemsImages() {
		WebElement aTagOfItem = null;
		String hrefOfItem;
		for (int i=0; i<offerItemsInCart.size(); i++) {
			aTagOfItem = offerItemsInCart.get(i).findElement(By.tagName("a"));
			hrefOfItem = aTagOfItem.getAttribute("href");
			aTagOfItem.click();
			assertEquals(hrefOfItem, driver.getCurrentUrl());
			driver.navigate().back();
		}
	}

	public void removeItemFromCart() throws InterruptedException { // On the assumption that i could add only one item to a cart
		int numberOfRemoveClicks = Integer.parseInt(quantityOfItemInCart.getText());
		for (int i=0; i<numberOfRemoveClicks; i++) {
			minusQuantityOfItem.click();
			Thread.sleep(700);
		}
		assertTrue(checkIfCartPageIsEmpty());
	}

	public void checkCorrectDetailsOfItemInCart(Item item) {
		objSodaStreamTopNav.clickOnCartIcon();
		driver.navigate().refresh(); // twice refresh to make pop up disappear
		driver.navigate().refresh();
		objSodaStreamTopNav.clickOnCartIcon();
		String titleOfItem = item.getItemDetails().get("titleOfItem");
		String priceOfItem = item.getItemDetails().get("priceOfItem");
		assertEquals(titleOfItem, titleOfItemInCart.getText());
		assertTrue(priceOfItemInCart.getText().contains(priceOfItem));
		if (item instanceof PackItem) {
			String colorOfPack = item.getItemDetails().get("colorOfPack");
			String typeOfPack = item.getItemDetails().get("typeOfPack");
			String descriptionOfItem = descriptionOfItemInCart.getText().toLowerCase();
			assertEquals(descriptionOfItem.contains(colorOfPack.toLowerCase()), true);
			assertEquals(descriptionOfItem.contains(typeOfPack.toLowerCase()), true);
		}
		if (item instanceof CylinderItem) {
			String decriptionOfCylinder = item.getItemDetails().get("decriptionOfCylinder").toLowerCase();
			String descriptionOfItem = descriptionOfItemInCart.getText().toLowerCase();
			assertEquals(descriptionOfItem.contains(decriptionOfCylinder), true);
		}
		assertEquals(objSodaStreamTopNav.getValueOfCartCounter(), Integer.parseInt(quantityOfItemInCart.getText()));
	}

	public void clickOnCheckOutButton() {
		checkOutButton.click();
	}

	public String getUrl() {
		return this.url;
	}
	

	public void plusQuantity(int numberOfPlus) {
		double priceOfItem = Double.parseDouble(priceOfItemInCart.getText().replace("$", ""));
		double sumPriceOfItem = Double.parseDouble(totalPriceOfItemInCart.getText().replace("$", ""));
		double totalPriceOfItem = 0;
		// check + quantity
		for (int i=0; i<numberOfPlus; i++) {
			plusQuantityOfItem.click();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(objSodaStreamTopNav.getValueOfCartCounter(), Integer.parseInt(quantityOfItemInCart.getText()));
			sumPriceOfItem += priceOfItem;
			totalPriceOfItem = Double.parseDouble(totalPriceOfItemInCart.getText().replace("$", ""));
			assertEquals(sumPriceOfItem == totalPriceOfItem, true);
		}

	}

	public void minusQuantity(int numberOfMinus) {
		double priceOfItem = Double.parseDouble(priceOfItemInCart.getText().replace("$", ""));
		double sumPriceOfItem = Double.parseDouble(totalPriceOfItemInCart.getText().replace("$", ""));
		double totalPriceOfItem = 0;
		for (int i=0; i<numberOfMinus; i++) {
			minusQuantityOfItem.click();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(objSodaStreamTopNav.getValueOfCartCounter(), Integer.parseInt(quantityOfItemInCart.getText()));
			sumPriceOfItem -= priceOfItem;
			totalPriceOfItem = Double.parseDouble(totalPriceOfItemInCart.getText().replace("$", ""));
			assertEquals(Math.round(sumPriceOfItem) == Math.round(totalPriceOfItem), true);

		}
	}
}
