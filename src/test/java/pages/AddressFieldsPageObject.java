package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.WebElementUtils;

public class AddressFieldsPageObject extends WebElementUtils {
	
	protected WebElement firstNameTextBox;
	protected WebElement lastNameTextBox;
	protected WebElement companyTextBox;
	protected WebElement addressOneTextBox;
	protected WebElement addressTwoTextBox;
	protected WebElement cityTextBox;
	protected WebElement selectCountry;
	protected WebElement selectState;
	protected WebElement zipTextBox;
	protected WebElement phoneTextBox;
	protected WebElement emailTextBox;
	protected Select select = null;
	
	public AddressFieldsPageObject(WebDriver driver) {
		super(driver);
	}
	
	
	protected void firstNameTextBox(String text) {
		sendTextToElement(firstNameTextBox, text);
	}

	protected void lastNameTextBox(String text) {
		sendTextToElement(lastNameTextBox, text);
	}

	protected void companyTextBox(String text) {
		sendTextToElement(companyTextBox, text);
	}

	protected void addressOneTextBox(String text) {
		sendTextToElement(addressOneTextBox, text);
	}

	protected void addressTwoTextBox(String text) {
		sendTextToElement(addressTwoTextBox, text);
	}

	protected void cityTextBox(String text) {
		sendTextToElement(cityTextBox, text);
	}

	protected void zipTextBox(String text) {
		sendTextToElement(zipTextBox, text);
	}

	protected void phoneTextBox(String text) {
		sendTextToElement(phoneTextBox, text);
	}

	protected void countrySelector(String nameOfCountry) {
		select = new Select(selectCountry);
		select.selectByValue(nameOfCountry);
	}

	protected void provinceSelector(String nameOfState) {
		select = new Select(selectState);
		select.selectByValue(nameOfState);
	}
	
	protected void emailTextBox(String text) {
		sendTextToElement(emailTextBox, text);
	}

}
