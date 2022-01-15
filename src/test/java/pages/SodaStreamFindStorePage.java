package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SodaStreamFindStorePage {
	
	private WebDriver driver= null;
	private String url = null;
	@FindBy (id = "storepoint-results-container")
	private WebElement listOfStoresContainer;
	@FindBy (xpath = "//*[@id='storepoint-results-container']//div[@class='storepoint-location']")
	private List<WebElement> listOfStoresElements;
	@FindBy (xpath = "//*[@class='mapboxgl-canvas']")
	private WebElement mapWithStores;
	@FindBy (xpath = "//input[@class='mapboxgl-ctrl-geocoder--input']")
	private WebElement searchStoreTextBox;
	@FindBy (id = "storepoint-tag-dropdown")
	private WebElement filterSection;
	@FindBy (xpath = "//*[@class='mapboxgl-popup mapboxgl-popup-anchor-bottom']//b")
	private WebElement popUpStoreInMap;

	
	public SodaStreamFindStorePage(WebDriver driver) {
		this.driver= driver;
		this.url = "https://sodastream.com/pages/store-locator";
		PageFactory.initElements(driver, this);
	}
	
	public void navigate() {
		driver.get(url);
	}
	
	public void VerifyFindStorePageElements() {
		assertTrue(listOfStoresContainer.isDisplayed());
		assertTrue(mapWithStores.isDisplayed());
		assertTrue(searchStoreTextBox.isDisplayed());
		assertTrue(filterSection.isDisplayed());
	}
	
	public void clickOnStoreFromTheList(int indexOfStore) throws InterruptedException {
		//get the name of the store 
		WebElement chosenStore = listOfStoresElements.get(indexOfStore);
		String nameOfStore = chosenStore.getText().split("\n")[0];
		chosenStore.click();
		Thread.sleep(200);
		// now check the PopUp at the map
		assertEquals(popUpStoreInMap.getText(), nameOfStore);
	}
	
	public void sendTextToSearchBox(String text) throws InterruptedException {
		searchStoreTextBox.clear();
		Thread.sleep(200);
		searchStoreTextBox.sendKeys(text);
		Thread.sleep(200);
		searchStoreTextBox.sendKeys(Keys.RETURN);
	}
	
}
