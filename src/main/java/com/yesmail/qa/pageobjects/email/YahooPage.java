/**File name : TestSchedulePage.java
 * @Description: This Java Class is used to check the email on yahoo. 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @since completed by 12/4/13
 * @Version History
 * Version name Updated By Reason / Comments 
 * */
package com.yesmail.qa.pageobjects.email;

import java.util.List;

import org.openqa.selenium.By;
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

public class YahooPage extends BasePage {

	private WebDriver driver;
	private String pageUrl;

	@FindBy(id = "username")
	private WebElement userNameTxtBox;

	@FindBy(id = "passwd")
	private WebElement passwordTxtBox;

	@FindBy(css = "button[id='.save']")
	private WebElement signInBtn;

	@FindBy(css = ".inbox-label")
	private WebElement inboxLink;

	@FindBy(css = ".list-view-items-page")
	private WebElement inboxList;
	
	@FindBy(css = "div:nth-of-type(5)[data-action='select-message']")
	private WebElement mailSection;
	

	// constructor.
	public YahooPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is added to load the page.
	 * 
	 */
	public YahooPage load() {
		driver.get(pageUrl);
		return this;
	}

	/**
	 * This method is added to verify whether page is loaded.
	 * 
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(userNameTxtBox), driver, 50))
			throw new FrameworkException(YahooPage.class.getName()
					+ " is not loaded");

	}	

	/**
	 * This method is added to login into Yahoo.
	 * 
	 */
	public void loginToYahoo() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(userNameTxtBox),
				driver, 10);
		userNameTxtBox.clear();
		userNameTxtBox.sendKeys(PagesHelper.YAHOO_USERNAME);
		passwordTxtBox.clear();
		passwordTxtBox.sendKeys(PagesHelper.YAHOO_PASSWORD);
		signInBtn.click();
	}

	/**
	 * This method is added to click inbox link.
	 * 
	 */
	public void clickInbox() {
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(inboxLink),
				driver, 10);
		inboxLink.click();
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(inboxList),
				driver, 10);

	}	

	/**
	 * This method is added to search email from given master name.
	 * 
	 * @param : MasterName
	 * @param : Time to wait for required email (in minutes)
	 * @return : MailFound(True/false)
	 */
	private boolean findEmail(String searchText,
			int waitTime) {
		boolean searchFound = false;
		long startTime = System.currentTimeMillis() / 1000;
		long stopTime = startTime + (waitTime * 60);
			
			DriverUtility
			.waitFor(ExpectedConditionExtended
					.elementToBeClickable(mailSection), driver, 20);
			
			while (System.currentTimeMillis() / 1000 <= stopTime) {	
				List<WebElement> fromElement2 = driver.findElements(By.xpath("//div[@class='from']/span[contains(text(),'"+searchText+"')]"));			   
				for (WebElement mail : fromElement2) {			    
					if (mail.getText().equalsIgnoreCase(searchText)) {
						searchFound = true;
						break;
					}
				}
				if (searchFound) {
					break;
				}
				driver.navigate().refresh();
				DriverUtility.waitFor(ExpectedConditionExtended
						.elementToBeClickable(mailSection), driver, 30);
			}
				if (!searchFound)
			Reporter.log("search Text: " + searchText
					+ " not found after waiting for " + waitTime
					+ " minutes<br>");

		return searchFound;
	}

	/**
	 * This method is added to search email from given master name.
	 * 
	 * @param : MasterName
	 * @param : Time to wait for required email (in minutes)
	 * @return : MailFound(True/false)
	 */
	public boolean verifyEmailOnYahoo(String searchText,
			int waitTime) {
		load().isLoaded();	
		loginToYahoo();
		clickInbox();
		return findEmail(searchText,waitTime);
	}

}
