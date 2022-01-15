package pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;



public class SodaStreamTopNav extends WebElementUtils  {
	
	
	private Actions action = null;
	private SodaStreamFooter objSodaStreamFooter = null;
	@FindBy (xpath = "//a[@href='/']")
	private WebElement sodaStreamLogo;
	@FindBy (xpath = "//div[@class='d-flex col-3 col-lg-8 box text-right justify-content-end']//a[text()='Support']")
	private WebElement supportButton;
	@FindBy (xpath = "//a[contains(@class,'user-icon')]")
	private WebElement accountButton;
	@FindBy (css = "a[class='cart-icon']")
	private WebElement cartIconButton;
	@FindBy (xpath = "//p[contains(@class,'cart-icon__counter always-white')]")
	private WebElement cartIconCounter;
	@FindBy (xpath="//a[@class='mr-2'][1]") 
	private WebElement shopTopNav;
	@FindBy (xpath="//a[@href='/pages/co2-gas-cylinders']")
	private WebElement gasTopNav;
	@FindBy (xpath="//div[@class='sub-menu-gray']//a[contains(@href, 'https://sodastream.com/products')]")
	private List<WebElement> shopMenuOptions;
	@FindBy (xpath="//div[@class='sub-menu-gray secondary']//a[contains(@href, 'https://sodastream.com/products')]")
	private List<WebElement> gasMenuOptions;
	@FindBy (css = "a[bi-id='FLAVORS']")
	private WebElement flavorsTopNav;
	@FindBy (xpath = "//a[contains(@bi-id, 'GIFTS')]")
	private WebElement giftsTopNav;
	@FindBy (xpath = "//a[contains(@bi-id, 'EXPLORE')]")
	private WebElement exploreTopNav;
	@FindBy (css = "div[class='main-header row pl-5 main-header-two border-solid justify-content-between pt-0 pb-0 d-none d-lg-flex']")
	private WebElement mainPageTopNav;
	@FindBy (xpath = "//*[@class='mr-3 user-icon']")
	private WebElement userAccountIcon;
	
	public  SodaStreamTopNav(WebDriver driver) {
		super(driver);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSodaStreamLogo() {
		sodaStreamLogo.click();
		String urlOfHomePage = new SodaStreamMainPage(driver).getUrl();
		assertEquals(driver.getCurrentUrl(), urlOfHomePage);
	}
	
	public void clickOnSupportButton() {
		checkUrlAfterClick(supportButton);
	}
	
	public void clickOnAccountButton() {
		checkUrlAfterClick(accountButton);
	}
	
	public void clickOnCartIcon() {
		checkUrlAfterClick(cartIconButton);
	}
	
	
	public int getValueOfCartCounter() {
		int valueOfCounter = 0;
		if (isElementPresent(cartIconCounter) && cartIconCounter.getText() != "") {
			valueOfCounter = Integer.parseInt(cartIconCounter.getText());
			
		}
		return valueOfCounter;
	}
	public void clickOnShopTopNav() {
		action.moveToElement(shopTopNav).build().perform();
	}
	
	public void clickOnGetGasTopNav() {
		action.moveToElement(gasTopNav).build().perform();
		
	}
	
	public void clickOnFlavorsTopNav() {
		checkUrlAfterClick(flavorsTopNav);
		this.validateFooterAndTopNav();
	}
	
	public void clickOnGiftsTopNav() {
		checkUrlAfterClick(giftsTopNav);
		this.validateFooterAndTopNav();
	}
	
	public void clickOnExploreTopNav() {
		checkUrlAfterClick(exploreTopNav);
		this.validateFooterAndTopNav();
	}
	
	public void validateFooterAndTopNav() {
		this.objSodaStreamFooter = new SodaStreamFooter(driver);
		assertEquals(objSodaStreamFooter.footerSectionDisplay(), true);
		assertEquals(mainPageTopNav.isDisplayed(), true);
	}
	
	public List<WebElement> getShopMenuOptions() {
		return shopMenuOptions;
	}
	
	public List<WebElement> getGasMenuOptions() {
		return gasMenuOptions;
	}
	
	public void clickOnOptionFromShopMenu(int numberOfOption) {
		checkUrlAfterClick(this.shopMenuOptions.get(numberOfOption));
	}
	
    public void clickOnOptionFromGasMenu(int numberOfOption) {
    	checkUrlAfterClick(this.gasMenuOptions.get(numberOfOption));
	}
    
    public boolean checkIfUserAccountIconAppear() {
    	return isElementPresent(userAccountIcon);
    }
	

}
