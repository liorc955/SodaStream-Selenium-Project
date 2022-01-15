package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class SodaStreamExplorePage extends WebElementUtils {
	
	
	private String url = null;
	@FindBy (xpath = "//img[contains(@class,'pb-4')]")
	private List<WebElement> imagesPage;
	@FindBy (xpath = "//div[@class='ytp-cued-thumbnail-overlay-image']")
	private List<WebElement> videoElements;
	@FindBy (linkText = "Gas")
	private WebElement gasOption;
	@FindBy (linkText = "Why SodaStream")
	private WebElement whySodaStreamOption;

	public SodaStreamExplorePage(WebDriver driver) {
		super(driver);
		this.url = "https://sodastream.com/blogs/explore";
		PageFactory.initElements(driver, this);
	}
	
	public void navigate() {
		driver.get(url);
	}
	
	public void checkAllImagesAppear() {
		for (WebElement image: imagesPage) {
			assertTrue(isElementPresent(image));
		}
	}
	
	public void checkAllVideoAppear() {
		for (WebElement video: videoElements) {
			assertTrue(isElementPresent(video));
		}
	}
	
	public void clickOnGasOption() {
		checkUrlAfterClick(gasOption);
	}
	
	public void clickOnWhySodaStreamOption() {
		checkUrlAfterClick(whySodaStreamOption);
	}
	

}
