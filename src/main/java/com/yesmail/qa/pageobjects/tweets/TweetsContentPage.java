/***
 * File : TweetsContentPage.java
 * Description : This java class is used to fill the tweets content for particular twitter account 
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * 1.Please add comments in header and for each method in both files 
 * 2.Check if findElement(By.xpath("..//select[@name can be removed 
 * 3.Check return type This method must return a result of type boolean 
 * public boolean saveTweetContent() { 
 * save.click(); 
 * } 
 *    
 */

package com.yesmail.qa.pageobjects.tweets;

import java.util.UUID;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class TweetsContentPage extends BasePage{

	/*
	 * Tweet Content page web element allocation
	 */

	@FindBy(css = "select[name='twitterAccount']")
	private WebElement accountDropDown;

	@FindBy(css = "textarea#tweetContentArea")
	private WebElement contentTextBox;

	@FindBy(css = "form#tweetContentForm button")
	private WebElement saveButton;	

	@FindBy(css = "div:nth-child(2)>a:nth-of-type(2)")
	private WebElement scheduleTab;
	
	private WebDriver driver;
	private String pageUrl;

	// define the constructor

	public TweetsContentPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public TweetsContentPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {
		if (!DriverUtility.waitforElementDisplay(driver, accountDropDown, 30))
			throw new FrameworkException(TweetsContentPage.class.getName()
					+ " is not loaded");

	}

	/**
	 * This method is added to navigate to schedule page
	 */
	public void navigateToScheduleTab() {
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(scheduleTab), driver, 50);
		scheduleTab.click();
	}

	/***
	 * This method added to select twitter account
	 * 
	 * @param twitterAccount
	 *            - account need to be selected
	 */
	public void selectTwitterAccount(String twitterAccount) {
		DriverUtility.selectDropDown(accountDropDown, twitterAccount, 3);

	}

	/***
	 * This method is added to enter the Tweets Content
	 * 
	 * @param msgName
	 *            - message string name
	 */

	public void enterTweetsContent() {
		String strMsgName = PagesHelper.TWEETS_CONTENT_NAME + UUID.randomUUID();
		contentTextBox.clear();
		contentTextBox.sendKeys(strMsgName);
	}

	/***
	 * This method is added to save the Tweet Content
	 * 
	 * @return
	 */
	public boolean saveTweetContent() {
		saveButton.click();
		Reporter.log("Ribbon Text for TweetContent is:"+getRibbonText(10)+"<br>",true);
		return stepCompleted(1, 10);
		
	}

	/**
	 * This method is added to create and save tweet
	 * 
	 * @param twitterAccount
	 *            - account need to be selected
	 * @return 
	 */
	public boolean createTweet(String twitterAccount) {		
		selectTwitterAccount(twitterAccount);
		enterTweetsContent();
		return saveTweetContent();
		}

	

} // end of TweetsContentPage class

