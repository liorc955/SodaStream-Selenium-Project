package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class BlogExplorePage extends WebElementUtils {
	
	
	@FindBy (xpath = "//a[@class='d-n text-dark fs40 mb-3']")
	private List<WebElement> listOfArticles;
	@FindBy (xpath = "//div[@class='col-12 col-md-6 pl-md-5']//a[@href and not(@target)]")
	private List<WebElement> listOfLinks;
	@FindBy (xpath = "//div[@class='col-12 col-md-6 pl-md-5']//a[@href and @target='_blank']")
	private List<WebElement> listOfTargetLinks;
	
	public BlogExplorePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnArticle(int indexOfArticle) {
		checkUrlAfterClick(listOfArticles.get(indexOfArticle));
	}
	
	public void clickAndCheckArticle(int indexOfArticle) {
		clickOnArticle(indexOfArticle);
		for (int i=0; i<listOfLinks.size(); i++) {
			checkUrlAfterClick(listOfLinks.get(i));
			driver.navigate().back();
		}
		
		for (int i=0; i<listOfTargetLinks.size(); i++) {
			checkUrlInPopUpWindow(listOfTargetLinks.get(i));
		}
	}

}
