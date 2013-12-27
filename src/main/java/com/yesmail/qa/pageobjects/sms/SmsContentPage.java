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


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class SmsContentPage extends SmsBasePage {

	/**
	 * Initializing Page objects
	 */

	private WebDriver driver;

	@FindBy(id = "btn_textMessageContent")
	private WebElement contentTab;

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

	@FindBy(css = "div:nth-child(3).previewContent")
	private WebElement previewContenttextBox;

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
		if(null == DriverUtility.waitFor(elementToBeClickable(By.id("saveContent")), driver, 50))
		{
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
		}
	}
	
	public SmsContentPage load()
	{
		navigateToContent();
		return this;
	}

	/***
	 * This method is added to save the SMS content
	 * 
	 * @param mastername
	 */

	public void saveContent(String textToEnterInContent) {
		contentText.sendKeys(textToEnterInContent);
		saveContent.click();

	}

	/**
	 * On screen Preview with user id
	 * 
	 * @author sangeetap
	 */
	public void previewWithUserid(String textToEnterInContent,String smsUserID) {

		
		contentText.clear();
		contentText.sendKeys(textToEnterInContent);
		saveContent.click();
		previewTab.click();
		userId.sendKeys(smsUserID);
		testButton.click();
	}

	/**
	 * Sending the SMS preview to a Mobile number
	 * 
	 * @param mobileNumber
	 * @author sangeetap
	 */
	public void previewWithMobileNumber(String textToEnterInContent,String smsUserID,String smsMobileNo) {
		DriverUtility.waitforElementDisplay(driver, contentText, 10);
		contentText.clear();
		contentText.sendKeys(textToEnterInContent);
		saveContent.click();
		previewTab.click();
		userId.sendKeys(smsUserID);
		previewid.sendKeys(smsMobileNo);
		previewBtn.click();
		testButton.click();
	}

}
