package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class SodaStreamFAQandReviewSection extends WebElementUtils {


	@FindBy (xpath = "//div[@class='faq-container pointer']//div[@bi-type='FAQ']")
	private List<WebElement> listOfCloseQuestions;
	@FindBy (xpath = "//div[@class='single-faq p-4 p-lg-5 active']")
	private List<WebElement> listOfOpenQuestions;
	@FindBy (css = "a[class='learn-more-box d-flex align-items-center']")
	private WebElement readAllFaqsButton;
	@FindBy (xpath = "//div[@aria-label='Rating Filter']")
	private WebElement ratingFilter;
	@FindBy (xpath = "//span[@data-type='scores']//li")
	private List<WebElement> scoreListOptions;
	@FindBy (xpath = "//div[@class='yotpo-reviews yotpo-active']//div[contains(@class,'yotpo-review yotpo-regular-box') and not(contains(@class,'template'))]")
	private List<WebElement> reviewList;
	@FindBy (css = "div[class='yotpo-icon-btn-small transparent-color-btn vote-btn']")
	private WebElement thumpUpButton;
	@FindBy (css = "span[class='selected-bold selected pL0']")
	private WebElement selectFilterButton;
	@FindBy (xpath = "//*[@id='filter_lists']//li//a")
	private List<WebElement> filterList;
	@FindBy (xpath = "//span[@data-type='scores']//span[contains(@class, 'selected') and @data-selected-key]")
	private WebElement currentScoreSelect;
	@FindBy (css = "span[class='selected-bold selected pL0 non-default-item-selected']")
	private WebElement currentFilterSelect;
	@FindBy (css = "div[class='yotpo-default-button yotpo-icon-btn write-question-review-button write-button write-review-button']")
	private WebElement writeReviewButton;
	@FindBy (id = "write-review-tabpanel")
	private WebElement writeReviewTabPanel;
	@FindBy (css = "div[class='mb-3 mt-3']")
	private WebElement  ratingStarsLink;
	private By scoreRatingInSingleReview = By.cssSelector("span[class='yotpo-icon yotpo-icon-star rating-star pull-left']");
	private By dateOfReview = By.cssSelector("span[class='y-label yotpo-review-date']");
	private By thumbUpButton  = By.cssSelector("div[class*='yotpo-icon-btn-small transparent-color-btn vote-btn' ][data-type='up']");
	private By thumbDownButton = By.cssSelector("div[class*='yotpo-icon-btn-small transparent-color-btn vote-btn' ][data-type='down']");
	private By counterThumbUp = By.cssSelector("span[class='y-label yotpo-sum vote-sum'][data-type='up']");
	private By counterThumbDown = By.cssSelector("span[class='y-label yotpo-sum vote-sum'][data-type='down']");
	private Hashtable<Integer, Boolean> clickOnThumbBefore; // < indexOfReview , true/false >

	public SodaStreamFAQandReviewSection(WebDriver driver) {
		super(driver);
		this.clickOnThumbBefore = new Hashtable<Integer, Boolean>();
		PageFactory.initElements(driver, this);
	}

	public void clickOnAllQuestions() {
		// First, open all the questions
		for(int i=0; i<listOfCloseQuestions.size(); i++) {
			listOfCloseQuestions.get(i).click(); // open them
		}
		int sizeOfOfOpenQuestions = listOfOpenQuestions.size();
		assertEquals(sizeOfOfOpenQuestions, listOfCloseQuestions.size());
		// Now check question that has been clicked previously (last question)
		listOfOpenQuestions.get(listOfOpenQuestions.size()-1).click();
		assertEquals(listOfOpenQuestions.size(), sizeOfOfOpenQuestions - 1);
	}

	public void clickReadAllFAQButton() {
		checkUrlInPopUpWindow(readAllFaqsButton);
	}

	public void clickOnRatingFilter() {
		ratingFilter.click();
	}

	public void clickOnRatingOption(int ratingNumber) {
		for (int i=0; i<scoreListOptions.size(); i++) {
			WebElement liScore = scoreListOptions.get(i);
			if (getAttributeOfElement(liScore, "data-value").equals(String.valueOf(ratingNumber))) {
				liScore.click(); 
			}
		}
	}

	public void checkRatingOfAllReviews(int ratingNumber) {
		for (int i=0; i<reviewList.size(); i++) {
			WebElement singleReview = reviewList.get(i);
			assertTrue(singleReview.findElements(scoreRatingInSingleReview).size() == ratingNumber);
		}
	}

	public void clickOnThumbButton(int indexOfReview, String upOrDown) {
		WebElement thumbButtonDirection = null; // up or down
		WebElement selectedReview = reviewList.get(indexOfReview);
		By counterThumbObjPage = null;
		int counterThumbDirection = 0;
		if (upOrDown.equalsIgnoreCase("up")) {
			thumbButtonDirection = reviewList.get(indexOfReview).findElement(thumbUpButton); // find a thumb button at a review
			counterThumbDirection = Integer.parseInt(selectedReview.findElement(counterThumbUp).getText()); // get the counter value
			counterThumbObjPage = counterThumbUp;
		} else {
			thumbButtonDirection = reviewList.get(indexOfReview).findElement(thumbDownButton);
			counterThumbDirection = Integer.parseInt(selectedReview.findElement(counterThumbDown).getText());
			counterThumbObjPage = counterThumbDown;
		}
		if (!clickOnThumbBefore.containsKey(indexOfReview)) { // first time to click on thumb button at the review
			clickOnThumbBefore.put(indexOfReview, false);
		}
		waitForElementToBeClickable(thumbButtonDirection);
		thumbButtonDirection.click();
		if(clickOnThumbBefore.containsKey(indexOfReview)) {
			if (clickOnThumbBefore.get(indexOfReview)) { // the user clicked on a thumb button before, check decreasing
				assertEquals(Integer.parseInt(selectedReview.findElement(counterThumbObjPage).getText()), counterThumbDirection -1);
				clickOnThumbBefore.put(indexOfReview, false); // for the next time to check increasing
			} else { // checking increasing
				assertEquals(Integer.parseInt(selectedReview.findElement(counterThumbObjPage).getText()), counterThumbDirection + 1);
				clickOnThumbBefore.put(indexOfReview, true);
			}
		}
	}

	public void clickOnSelectFilter() {
		selectFilterButton.click();
	}

	public void chooseFilter(String nameOfFilter) {
		for (int i=0; i<filterList.size(); i++) {
			if(filterList.get(i).getText().equalsIgnoreCase(nameOfFilter)) {
				filterList.get(i).click();
			}
		}
	}

	public void checkFilterReview() { // check the first review
		String currentScore = getAttributeOfElement(currentScoreSelect, "data-selected-key");
		String currentFilter = isElementPresent(currentFilterSelect) ? currentFilterSelect.getText(): "All";
		System.out.println(currentScore);
		System.out.println(currentFilter);
		switch(currentFilter) {
		case "Highest Rating":
			if (currentScore.equals("All")) {
				checkRatingOfAllReviews(5);
			} else {
				checkRatingOfAllReviews(Integer.parseInt(currentScore));
			}
			break;
		case "Lowest Rating":
			if (currentScore.contains("All")) {
				checkRatingOfAllReviews(1);
			} else {
				checkRatingOfAllReviews(Integer.parseInt(currentScore));
			}
			break;
		case "Most Votes":
			if (currentScore.contains("All")) {
				int numberOfUpVotes = Integer.parseInt(reviewList.get(0).findElement(counterThumbUp).getText());
				System.out.println(numberOfUpVotes);
				assertTrue(numberOfUpVotes > 10);
			}
			break;
		case "Least Votes":
			if (currentScore.contains("All")) {
				int numberOfUpVotes = Integer.parseInt(reviewList.get(0).findElement(counterThumbUp).getText());
				System.out.println(numberOfUpVotes);
				assertTrue(numberOfUpVotes == 0);
			}
			break;
		case "Newest":
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String currentYear = String.valueOf(localDate.getYear()).substring(2,4);
			System.out.println(currentYear);
			assertTrue(reviewList.get(0).findElement(dateOfReview).getText().contains(currentYear));
		}
	}

	public void clickOnWriteReviewButton() {
		writeReviewButton.click();
	}

	public void checkWriteReviewTabPanelDisplay() {
		isElementPresent(writeReviewTabPanel);
	}
	
	public void clickOnRatingStars() {
		ratingStarsLink.click();
	}

}
