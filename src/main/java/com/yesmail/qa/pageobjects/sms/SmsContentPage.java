/**
 * File Name : SMSContentPage.Java
 * Description: This Java Class is for SMS  Content page creation
 * 
 * @author sangeetap
 * @since Completed 10th December, 2013
 * Version History
 * Version name Updated By Reason / Comments  
 */

package com.yesmail.qa.pageobjects.sms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

public class SmsContentPage extends SmsBasePage {

	/**
	 * Initializing Page objects
	 */

	private WebDriver driver;

	@FindBy(css = "div.m-sms-content-edit textarea[name='content']")
	private WebElement contentText;

	@FindBy(id = "saveContent")
	private WebElement saveContent;

	@FindBy(css = "div:nth-child(1) ul li a[data-id='PREVIEW']")
	private WebElement previewTab;

	@FindBy(id = "smsUserId")
	private WebElement userId;

	@FindBy(css = "button#test")
	private WebElement testButton;

	@FindBy(id = "smsDestination")
	private WebElement previewid;

	@FindBy(id = "preview")
	private WebElement previewBtn;

	@FindBy(css = ".page div.alert-error")
	private WebElement alertError;
	
	@FindBy( css = "div.previewContent")
	private WebElement previewContentdisableTextBox;

	@SuppressWarnings("unused")
	private String pageUrl;

	/**
	 * Initializing Constructor
	 */

	public SmsContentPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(previewTab),
				driver, 50)) {
			navigateToContent();
			if (null == DriverUtility
					.waitFor(ExpectedConditionExtended
							.elementToBeClickable(previewTab), driver, 50))
				throw new FrameworkException(this.getClass().getName()
						+ " is not loaded in 50 seconds");
		}
	}

	public SmsContentPage load() {
		navigateToContent();
		return this;
	}

	/**
	 * On screen Preview with user id
	 * 
	 * @author sangeetap
	 */
	public boolean previewWithUserid(String textToEnterInContent,
			String smsUserID) {

		contentText.clear();
		contentText.sendKeys(textToEnterInContent);
		saveContent.click();
		previewTab.click();
		userId.sendKeys(smsUserID);
		testButton.click();
		Reporter.log(" SMS Content User Id Test Ribben Text Message : <br>"
				+ getRibbonText(10), true);
		return (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(alertError),
				driver, 20) == null);

	}

	/**
	 * Sending the SMS preview to a Mobile number
	 * 
	 * @param mobileNumber
	 * @author sangeetap
	 */
	public boolean previewWithMobileNumber(String textToEnterInContent,
			String smsUserID, String smsMobileNo) {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(contentText),
				driver, 20);
		contentText.clear();
		contentText.sendKeys(textToEnterInContent);
		saveContent.click();
		Reporter.log(" SMS Header Ribben Text Message : <br>"
				+ getRibbonText(10));
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(previewTab),
				driver, 20);
		previewTab.click();
		userId.sendKeys(smsUserID);
		previewid.sendKeys(smsMobileNo);
		previewBtn.click();
		Reporter.log(" SMS Content Send Preview Ribben Text Message : <br>"
				+ getRibbonText(10), true);
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(testButton),
				driver, 20);
		return (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(alertError),
				driver, 20) == null);

	}

	/**
	 * Sending the SMS preview to a Mobile number
	 * 
	 * @param mobileNumber
	 * @author sangeetap
	 */
	public boolean saveContent(String textToEnterInContent, String smsUserID,
			String smsMobileNo) {
		contentText.clear();
		contentText.sendKeys(textToEnterInContent);
		saveContent.click();
		Reporter.log("Ribbon Text for Content Page is : " + getRibbonText(20)
				+ "<br>");
		return stepCompleted(2, 20);
	}
	
	/**
	 * This method is added to get the preview content message
	 * 
	 * @param mobileNumber
	 * @author sangeetap
	 */
	public String getContentMessage()
	{
		DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(previewContentdisableTextBox), driver, 20);
				return previewContentdisableTextBox.getText();
	}

}
