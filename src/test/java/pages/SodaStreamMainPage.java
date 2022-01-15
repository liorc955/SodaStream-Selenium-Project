package pages;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class SodaStreamMainPage extends WebElementUtils {
	
	
	private SodaStreamTopNav objSodaStreamTopNav = null;
	private String url = null;
	@FindBy (linkText = "Shop Now")
	private List<WebElement> shopNowPromoButtons;
	
	
	public SodaStreamMainPage(WebDriver driver) {
		super(driver);
		this.objSodaStreamTopNav = new SodaStreamTopNav(driver);
		this.url = "https://sodastream.com/";
		PageFactory.initElements(driver, this);
	}
	
	public void navigate() {
		driver.get(url);
	}
	
	
	public void clickAllThePromoButtons() {
		for (int i=0; i<shopNowPromoButtons.size(); i++) {
			checkUrlAfterClick(shopNowPromoButtons.get(i));
			driver.navigate().back();
		}
	}
	
	public void checkShopTopNav() {
		objSodaStreamTopNav.clickOnShopTopNav();
		assertEquals(3, objSodaStreamTopNav.getShopMenuOptions().size());
	}
	
	public void checkGetGasTopNav() {
		objSodaStreamTopNav.clickOnGetGasTopNav();
		assertEquals(2, objSodaStreamTopNav.getGasMenuOptions().size());
		
	}
	
	public String getUrl() {
		return this.url;
	}

}
