package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SodaStreamFlavorItemPage extends SodaStreamGiftItemPage {
	
	@FindBy (css = "button[class='btn fs16 fs-sm-15 nutrition-buttons']")
	private WebElement viewlabelButton;
	@FindBy (xpath = "//div[@class='modal-dialog modal-dialog-centered']//div[@class='modal-body']//img")
	private List<WebElement> labelsImagesPopUp;
	
	public SodaStreamFlavorItemPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickOnViewlabelButton() {
		viewlabelButton.click();
	}
	
	public boolean labelsImagesPopUpIsDisplay() {
		boolean isDisplay = false;
		for (WebElement img : labelsImagesPopUp) {
			if (isElementPresent(img)) {
				isDisplay = true;
			}
		}
		return isDisplay;
	}

}
