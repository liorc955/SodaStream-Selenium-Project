package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WebElementUtils;

public class AddCreditCardCheckOutPage extends WebElementUtils {
	
	
	private WebDriverWait wait = null;
	@FindBy (xpath = "//div[@class='field__input field__input--iframe-container'][@data-card-field-placeholder='Card number']//iframe")
	private WebElement creditCardNumberFrame;
	@FindBy (xpath = "//div[@class='field__input field__input--iframe-container'][@data-card-field-placeholder='Name on card']//iframe")
	private WebElement nameOnCardFrame;
	@FindBy (xpath = "//div[@class='field__input field__input--iframe-container'][contains(@data-card-field-placeholder,'Expiration')]//iframe")
	private WebElement expirationDateFrame;
	@FindBy (xpath = "//div[@class='field__input field__input--iframe-container'][contains(@data-card-field-placeholder,'Security')]//iframe")
	private WebElement securityNumberFrame;
	@FindBy ( id = "number")
	private WebElement creditCardNumberTextBox;
	@FindBy ( id = "name")
	private WebElement nameOnCardTextBox;
	@FindBy ( id = "expiry")
	private WebElement expirationDateTextBox;
	@FindBy ( id = "verification_value")
	private WebElement securityNumberTextBox;
	
	
	public AddCreditCardCheckOutPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, 45);
		PageFactory.initElements(driver, this);
	}
	
	public void creditCardNumberTextBox(String text) {
		insertTextIntoIframeFields(creditCardNumberFrame, creditCardNumberTextBox, text);
	}
	
	public void nameOnCardTextBox(String text) {
		insertTextIntoIframeFields(nameOnCardFrame, nameOnCardTextBox, text);
	}
	
	public void expirationDate(String text) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(expirationDateFrame));
		wait.until(ExpectedConditions.elementToBeClickable(expirationDateTextBox));
		for (int i=0; i<text.length(); i++) {
			expirationDateTextBox.sendKeys(String.valueOf(text.charAt(i)));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		driver.switchTo().defaultContent();
	}
	
	public void securityNumberTextBox(int number) {
		insertTextIntoIframeFields(securityNumberFrame, securityNumberTextBox, String.valueOf(number));
	}
}
