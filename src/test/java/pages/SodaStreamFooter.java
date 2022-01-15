package pages;



import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WebElementUtils;

public class SodaStreamFooter extends WebElementUtils {

	
	@FindBy (id = "footer")
	private WebElement footerSection;
	@FindBy (xpath = "//*[contains(@href,'facebook')]") 
	private WebElement facebookLogo;
	@FindBy (xpath = "//*[contains(@href,'twitter')]")
	private WebElement twitterLogo;
	@FindBy (xpath ="//*[contains(@href,'instagram')]")
	private WebElement instagramLogo; 
	@FindBy (xpath = "//*[contains(@href,'youtube')]")
	private WebElement youtubeLogo;
	@FindBy (xpath = "//*[@class='pl-0']//a")
	private List<WebElement> informationLinks;
	@FindBy (xpath = "//*[@class='pl-0 fs16']//a")
	private List<WebElement> productsLinks;
	@FindBy (xpath= "//*[contains(text(),'Find a store near you')]")
	private WebElement findStoreLink;
	
	public SodaStreamFooter(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnFacebookLogo() {
		checkUrlInPopUpWindow(facebookLogo);
	}
	
	public void clickOnTwittwerLogo() {
		checkUrlInPopUpWindow(twitterLogo);
	}
	
	public void clickOnInstagramLogo() {
		checkUrlInPopUpWindow(instagramLogo);
	}
	
	public void clickOnYoutubeLogo() {
		checkUrlInPopUpWindow(youtubeLogo);
	}
	
	public void clickOnAllInformationLinks() {
		for (int i=0 ; i<informationLinks.size(); i++) {
			checkUrlAfterClick(informationLinks.get(i));
			driver.navigate().back();
		}
	}
	
	public void clickOnAllProductsLinks() {
		for (int i=0 ; i<productsLinks.size(); i++) {
			checkUrlAfterClick(productsLinks.get(i));
			driver.navigate().back();
		}
	}
	
	public void clickOnfindStore() {
		checkUrlAfterClick(findStoreLink);
	}
	
	public void clickOnLinkInInformationLinks(String linkToClick) {
		for (int i=0; i<informationLinks.size(); i++) {
			if (informationLinks.get(i).getText().contains(linkToClick)) {
				informationLinks.get(i).click();
			}
		}
	}
	
	public void clickOnLinkInProductLinks(String linkToClick) {
		for (int i=0; i<productsLinks.size(); i++) {
			if (productsLinks.get(i).getText().contains(linkToClick)) {
				productsLinks.get(i).click();
			}
		}
	}
	
	public boolean footerSectionDisplay() {
		return footerSection.isDisplayed();
	}
	
	public void scrollDownToFooter() {
		scrollToElement(footerSection);
	}
	
}
