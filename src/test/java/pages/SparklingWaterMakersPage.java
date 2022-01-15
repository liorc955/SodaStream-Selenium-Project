package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import objects.PackItem;

public class SparklingWaterMakersPage extends SodaStreamFAQandReviewSection {
	
	private Actions action = null;
	private String url = "https://sodastream.com/products/fizzi-starter-kit";
	@FindBy (xpath="//ol//li[contains(@class,'showit')]//img")
	private List<WebElement> thumbnailImages;
	@FindBy (xpath ="//div[contains(@class,'showit active')]//img")
	private WebElement bigImage;
	@FindBy (xpath = "//button[contains(text(), 'Hydration Pack')]")
	private WebElement hydrationPackButton;
	@FindBy (xpath = "//button[contains(text(), 'Starter Pack')]")
	private WebElement starterPackButton;
	@FindBy (xpath = "//div[contains(@class, 'showit active')]//img[@alt='1102830010']")
	private WebElement hydrationPackBigImage;
	@FindBy (xpath = "//div[contains(@class, 'showit active')]//img[@alt='1011711011']")
	private WebElement starertPackBigImage;
	@FindBy (xpath = "//div[contains(@class,'row product-colors')]//input")
	private List<WebElement> chooseColors;
	@FindBy (xpath = "//div[@class='easy-as-hover col-12 col-lg-4 position-relative border-radius-7 d-inline-block pointer']//img")
	private List<WebElement> mouseOverImages;
	@FindBy (id = "AddToCart")
	private WebElement buyNowButton;
	@FindBy (xpath = "//div[contains(@class,'active-card')]")
	private WebElement selectedPack;
	@FindBy (css= "h1[class='fs36 text-left d-none d-lg-block pt-md-4 pt-lg-0']")
	private WebElement titleOfItem;
	private By priceOfPack = By.cssSelector("p[class='item-price fs-sm-20 fs24 mb-0 font-weight-bold']");
	
	public SparklingWaterMakersPage(WebDriver driver) {
		super(driver);
		this.action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getUrl() {return url;}
	
	public void navigate() {
		driver.get(url);
	}
	
	public void clickOnAllThumbnailImages() throws InterruptedException {
		String srcOfSmallImage;
		String srcOfBigImage;
		for(int i=0; i<thumbnailImages.size();i++) {
			// The src of small image is the same as of the big image instead of words "small" and "550X@2x"
			srcOfSmallImage  = thumbnailImages.get(i).getAttribute("src").replace("small","550X@2x");
			thumbnailImages.get(i).click();
			Thread.sleep(200);
			srcOfBigImage = bigImage.getAttribute("src");
			assertEquals(srcOfSmallImage.equals(srcOfBigImage), true);
		}
	}
	
	public void clickOnHydrationPackButton() {
		hydrationPackButton.click();
	}
	
	public void clickOnStarterPackButton() {
		starterPackButton.click();
	}
	
	public void checkIfHydrationPackBigImageIsDisplay() {
		assertEquals(hydrationPackBigImage.isDisplayed(), true);
	}
	
	public void checkIfStarterPackButtonIsDisplay() {
		assertEquals(starterPackButton.isDisplayed(), true);
	}
	
	public void checkIfBigImageIsDisplay() {
		assertEquals(bigImage.isDisplayed(), true);
	}
	
	public void clickOnAllColors() throws InterruptedException {
		String[] chosenColor;
		for (int i=0; i<chooseColors.size(); i++) {
			chosenColor = chooseColors.get(i).getAttribute("value").toLowerCase().split(" ");
			chooseColors.get(i).click();
			Thread.sleep(200);
			assertEquals(bigImage.getAttribute("src").toLowerCase().contains(chosenColor[0]), true);
		}
	}
	
	public void OnMouseOverAllVideoImages() {
		for (int i=0; i<mouseOverImages.size(); i++) {
			action.moveToElement(mouseOverImages.get(i)).build().perform();
		}
	}
	
	
	public void buyPackItem() throws InterruptedException {
		// At first, i save the details of the product i want to add to a cart.
		String typeOfItemPack = selectedPack.getAttribute("data-type");
		String titleOfItemPack = titleOfItem.getText().split("\n")[0];
		String colorOfItemPack = null;
		String priceOfItemPack = selectedPack.findElement(priceOfPack).getText();
		for (int i=0; i<chooseColors.size(); i++) {
			if (chooseColors.get(i).isSelected()) {
				colorOfItemPack = chooseColors.get(i).getAttribute("value").toLowerCase().split(" ")[0];
			}
		}
		// Now click on the buy button and check if the details are correct.
		buyNowButton.click();
		SodaStreamCartPage objSodaStreamCartPage = new SodaStreamCartPage(driver);
		Thread.sleep(1000);
		assertEquals(driver.getCurrentUrl(), objSodaStreamCartPage.getUrl());
		PackItem packItem = new PackItem(titleOfItemPack, priceOfItemPack, colorOfItemPack, typeOfItemPack);
		objSodaStreamCartPage.checkCorrectDetailsOfItemInCart(packItem);
	}
	
	private WebElement searchColorElement(String color) {
		String[] chosenColor;
		WebElement colorElement = null;
		for (int i=0; i<chooseColors.size(); i++) {
			chosenColor = chooseColors.get(i).getAttribute("value").toLowerCase().split(" ");
			if (chosenColor[0].equalsIgnoreCase(color)) {
				colorElement = chooseColors.get(i);
			}
		}
		return colorElement;
	}
	
	
	public void chooseColor(String color) {
		searchColorElement(color).click();
	}
	
	public void checkIfColorIsSelected(String color) {
		assertTrue(searchColorElement(color).isSelected());
	}
}
