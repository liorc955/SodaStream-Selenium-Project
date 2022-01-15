package pages;


import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import objects.Item;
import utils.WebElementUtils;

public class SodaStreamGiftsAndFlavorsPage extends WebElementUtils {

	
	protected String url = null;
	@FindBy (xpath = "//div[@class='pointer selected-filter pt-20 pb-20 fs13 position-relative']")
	private WebElement sortItems;
	@FindBy (xpath = "//div[@class='filtering-menu-wrap']//a")
	private List<WebElement> sortMenu;
	@FindBy (xpath = "//*[@id='AjaxinateContainer']//form")
	private List<WebElement> listOfItems;
	@FindBy (xpath = "//a[contains(@class,'pr-4 pl-4 pt-1 pb-2')]")
	private List<WebElement> filterMenu;
	private By priceOfItem = By.cssSelector("span[class='fs18 text-black price']");
	private By titleOfItem = By.cssSelector("h2[class='fs16 fs-lg-14 mb-4 pt-3 flex-grow-1 zi-5']");
	private By addToCardButtonItem = By.cssSelector("button[bi-type='Add to cart']");

	public SodaStreamGiftsAndFlavorsPage(WebDriver driver) {
		super(driver);
		this.url = "https://sodastream.com/collections/gifts";
		PageFactory.initElements(driver, this);
	}
	
	public void navigate() {
		driver.get(url);
	}

	public void clickOnSortBy() {
		sortItems.click();
	}

	public void clickAndCheckSortOption(String option) {
		for (int i=0; i<sortMenu.size(); i++) {
			if (sortMenu.get(i).getText().equals(option)) {
				checkUrlAfterClick(sortMenu.get(i));
				if(option.equals("Low Price")) {
					assertTrue(checkLowPriceSortItems());
				}
			}
		}
	}

	public void clickAndCheckAllSortOptions() {
		clickOnSortBy();
		for (int i=0; i<sortMenu.size(); i++) {
			checkUrlAfterClick(sortMenu.get(i));
			if (sortMenu.get(i).getText().equals("Low Price")) {
				assertTrue(checkLowPriceSortItems());
			}
			driver.navigate().back();
			clickOnSortBy();
		}
	}

	public boolean checkLowPriceSortItems() { 
		boolean isASC = false; 
		double priceOfPrevItem;
		double priceOfNextItem;
		for (int i=0; i<listOfItems.size(); i++) {
			priceOfPrevItem = getPriceOfItem(listOfItems.get(i));
			priceOfNextItem = getPriceOfItem(listOfItems.get(i+1));
			if (priceOfNextItem >= priceOfPrevItem) {
				isASC = true;
			}
		}
		return isASC;
	}

	private double getPriceOfItem(WebElement element) {
		String textOfPrevItem = element.findElement(priceOfItem).getText().replace("$", "");
		return Double.parseDouble(textOfPrevItem);
	}

	private String getTitleOfItem(WebElement element) {
		return element.findElement(titleOfItem).getText();
	}

	public void clickAndCheckFilterOption(String filterName) {
		for (int i=0; i<filterMenu.size(); i++) {
			if(filterMenu.get(i).getText().equalsIgnoreCase(filterName)) {
				checkUrlAfterClick(filterMenu.get(i));
			}
		}
	}

	public void clickAndCheckAllFilterOptions() {
		for (int i=0; i<filterMenu.size(); i++) {
			checkUrlAfterClick(filterMenu.get(i));
		}
	}

	public Item addItemToCart(int indexOfItem) {
		WebElement chosenItem =  listOfItems.get(indexOfItem);
		Item item = new Item(getTitleOfItem(chosenItem), String.valueOf(getPriceOfItem(chosenItem)));
		chosenItem.findElement(addToCardButtonItem).click();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(driver.getCurrentUrl().contains(url));
		return item;
	}

	public void clickOnItem(int indexOfItem) {
		checkUrlAfterClick(listOfItems.get(indexOfItem).findElement(By.tagName("a")));
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
