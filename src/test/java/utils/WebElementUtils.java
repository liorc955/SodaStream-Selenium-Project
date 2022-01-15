package utils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementUtils {

	protected WebDriver driver = null;
	private WebDriverWait wait = null;
	private JavascriptExecutor js = null;

	public WebElementUtils(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		this.wait = new WebDriverWait(driver,15);
	}
	
	protected boolean isElementPresent(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	// for elements with href attribute
	protected void checkUrlInPopUpWindow(WebElement element) {
		String hrefOfElement = element.getAttribute("href");
		Set<String> beforePopup = driver.getWindowHandles();
		element.click();
		switchBetweenWindows(beforePopup);
		assertEquals(driver.getCurrentUrl().contains(hrefOfElement), true);
		driver.close();
		driver.switchTo().window((String)beforePopup.toArray()[0]);
	}

	// for elements without href attribute
	protected void checkUrlInPopUpWindow(WebElement element, String title) {
		Set<String> beforePopup = driver.getWindowHandles();
		element.click();
		switchBetweenWindows(beforePopup);
		assertEquals(driver.getTitle().toLowerCase().contains(title), true);
		driver.close();
		driver.switchTo().window((String)beforePopup.toArray()[0]);
	}

	// if a link doesn't open a new windows
	protected void checkUrlAfterClick(WebElement element) {
		String hrefOfElement = element.getAttribute("href");
		hrefOfElement = hrefOfElement.replace("http://", "https://");
		element.click();
		assertEquals(driver.getCurrentUrl().contains(hrefOfElement), true);
	}

	public void checkErrorValidationMessage(WebElement element) {
		boolean valid = (Boolean)(js).executeScript("return arguments[0].required;", element);
		assertTrue(valid);
	}

	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeVisibale(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void sendTextToElement(WebElement element ,String text) {
		element.clear();
		element.sendKeys(text);
	}

	public void insertTextIntoIframeFields(WebElement iframeElement, WebElement inputElement, String text) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeElement));
		wait.until(ExpectedConditions.elementToBeClickable(inputElement)).sendKeys(text);
		driver.switchTo().defaultContent();
	}

	public String getAttributeOfElement(WebElement element, String nameOfAttribute) {
		return element.getAttribute(nameOfAttribute);
	}

	public void scrollToElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollPageToUpper() {
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollPageToBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollPageByPixels(int xPixels, int yPixels) {
		String jsScript = "window.scrollBy(" + xPixels + "," + yPixels + ")";
		js.executeScript(jsScript);
	}
	
	private void switchBetweenWindows(Set<String> beforePopup) {
		Set<String> afterPopup = driver.getWindowHandles(); 
		afterPopup.removeAll(beforePopup);
		if(afterPopup.size() == 1) 
		{ 
			driver.switchTo().window((String)afterPopup.toArray()[0]); 
		}
	}
}
