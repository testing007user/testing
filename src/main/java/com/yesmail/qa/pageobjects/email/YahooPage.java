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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
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

	@FindBy(css = "a > em[title='Mail']")
	private WebElement mailLink;

	@FindBy(css = ".inbox-label")
	private WebElement inboxLink;

	@FindBys({ @FindBy(css = "div[class='from'] >span") })
	private List<WebElement> fromElement;

	@FindBy(css = ".list-view-items-page")
	private WebElement inboxList;

	//constructor.
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
				ExpectedConditions.elementToBeClickable(mailLink), driver, 10))
			throw new FrameworkException(YahooPage.class.getName()
					+ " is not loaded");

	}

	/**
	 * This method is added to click Mail link.
	 * 
	 */
	public void clickMailLink() {
		mailLink.click();

	}

	/**
	 * This method is added to login into Yahoo.
	 * 
	 */
	public void loginToYahoo() {
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(userNameTxtBox),
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
		DriverUtility.waitFor(ExpectedConditions.visibilityOf(inboxLink),
				driver, 10);
		inboxLink.click();
		DriverUtility.waitFor(ExpectedConditions.visibilityOf(inboxList),
				driver, 10);

	}

	/**
	 * This method is added to search email from given master name.
	 * @param : MasterName
	 * @param : Time to wait for required email (in minutes)
	 * @return : MailFound(True/false)
	 */
	public boolean findEmail(String MasterName, int waitTime) {
		boolean masterNameFound = false;
		long startTime = System.currentTimeMillis() / 1000;
		long stopTime = startTime + (waitTime * 60);

		while (System.currentTimeMillis() / 1000 <= stopTime) {
			for (WebElement mail : fromElement) {
				if (mail.getText().equalsIgnoreCase(MasterName)) {
					masterNameFound = true;
					break;
				}
			}
			if (masterNameFound) {
				break;
			}
			driver.navigate().refresh();
			DriverUtility.waitFor(
					ExpectedConditions.elementToBeClickable(inboxList), driver,
					30);
		}
		return masterNameFound;
	}

}
