/**File Name : SmsHeaderPage.Java
 * Description: This Java Class is for SMS Header page creation
 * 
 * @author sangeetap
 * @since Completed 6th December, 2013
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
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class SmsHeaderPage extends SmsBasePage {

	/**
	 * Initializing Page objects
	 */

	@FindBy(css = "input[id=name]")
	private WebElement smsName;

	@FindBy(css = "select[name='shortCode']")
	private WebElement shortCode;

	@FindBy(css = "select.campaign")
	private WebElement campaign;

	@FindBy(css = "button#saveSmsHeader")
	private WebElement saveButton;

	@FindBy(css = "div:nth-child(2).modal-body>input")
	private WebElement campaignNewCampaignName;

	@FindBy(css = "div:nth-child(2).modal-body form>select")
	private WebElement newCampaignTypeSelect;

	@FindBy(css = "div:nth-child(2).modal-body form>select>option:nth-child(2)")
	private WebElement newCampaignNewTypeTextBox;

	@FindBy(css = "div.modal-footer>button:nth-of-type(2)")
	private WebElement newCampaignCreateButton;
	
	@FindBy(id = "saveContent")
	private WebElement saveContent;

	private WebDriver driver;
	private String pageUrl;

	/**
	 * Initializing Constructor
	 */
	public SmsHeaderPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public SmsHeaderPage load() {
		driver.get(PagesHelper.URL + pageUrl);
		return this;
	}
	
	public SmsHeaderPage load(String strMMID) {
		driver.get(PagesHelper.URL + "#sms/" + strMMID + "/header");
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(smsName),
				driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}

	}

	/***
	 * This method is added to fill name
	 * 
	 * @author sangeetap
	 */
	private void fillSMSMasterName() {
		
		smsName.sendKeys(Utils.getUniqueName(PagesHelper.SMS_MASTER_NAME));
	}

	/***
	 * This method is added to select short code
	 * 
	 * @author sangeetap
	 */
	private void selectShortcode(String visibleShortCode) {
		DriverUtility.selectDropDown(shortCode, visibleShortCode, 1);
	}

	/***
	 * This method is added to select campaign from drop down, if 'Create New'
	 * option selected it will create a new campaign
	 * 
	 * @author sangeetap
	 */
	public void selectCampaign() {
		DriverUtility.selectDropDown(campaign, PagesHelper.SMS_CAMPAIGN, 1);

		if (PagesHelper.SMS_CAMPAIGN_NAME.equalsIgnoreCase("Create New...")) {
			campaignNewCampaignName.clear();
			String strName = Utils.getUniqueName(PagesHelper.SMS_CAMPAIGN_NAME);
			strName = strName.substring(0, strName.length() - 20);
			campaignNewCampaignName.sendKeys(strName);
			if (newCampaignTypeSelect.toString().equalsIgnoreCase(
					("Create New..."))) {
				String strDesc = Utils.getUniqueName(
						PagesHelper.SMS_CAMPAIGN_NAME, 20);
				newCampaignNewTypeTextBox.sendKeys(strDesc);
				newCampaignCreateButton.click();
			}

		}
	}

	/***
	 * This method added to click on save button
	 * 
	 */
	private void saveHeader() {
		saveButton.click();
		Reporter.log("Ribbon text after creating the SMS Header: "
				+ getRibbonText(20) + "<br>");
	}

	/**
	 * This method is addded to save SMS Header with Short code & Campaign
	 * 
	 * @author sangeetap
	 */
	public boolean saveSmsHeader(String visibleShortCode) {
		fillSMSMasterName();
		selectShortcode(visibleShortCode);
		saveHeader();
		DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(saveContent),
				driver, 30);//This is added as workaround for header page reload issue.
		return stepCompleted(1, 20);
	}
	
	/**
	 * This method is added to get short Code value
	 * @return short code value
	 * @author sangeetap
	 */
	public String getShortCode()
	{
		DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(shortCode), driver, 20);
		return disabledSelectBoxText(shortCode, driver);
	}

}
