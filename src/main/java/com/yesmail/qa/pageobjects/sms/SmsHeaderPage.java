/**File Name : SmsHeaderPage.Java
 * Description: This Java Class is for SMS Header page creation
 * 
 * @author sangeetap
 * @since Completed 6th December, 2013
 * Version History
 * Version name Updated By Reason / Comments  
 */

package com.yesmail.qa.pageobjects.sms;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
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
		driver.get(PagesHelper.URL+pageUrl);
		return this;
	}

	public void isLoaded() {
		if(null == DriverUtility.waitFor(ExpectedConditions.elementToBeClickable(By.id("name")), driver, 50))
		{
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

		
		// waitForRibbonToClose();
		smsName.sendKeys(Utils.getUniqueName("SMS"));
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
		DriverUtility.selectDropDown(campaign,
				"campaignName", 1); //get from pagesHelper

		if ("campaignName"
				.equalsIgnoreCase("Create New...")) {
			// DriverUtility.selectDropDown(campaignDropDown,PagesHelper.MULTIVARIATE_ENVELOPE_CAMPAIGN_NAME,
			// 1);
			campaignNewCampaignName.clear();
			String strName = Utils
					.getUniqueName("campaignNewName");//get from pagesHelper
			strName = strName.substring(0, strName.length() - 20);
			campaignNewCampaignName.sendKeys(strName);
			if (newCampaignTypeSelect.toString().equalsIgnoreCase(
					("Create New..."))) {
				String strDesc = Utils
						.getUniqueName("newStr",20);//get from pagesHelper
				//strDesc = strDesc.substring(0, StrDesc.length() - 20);
				newCampaignNewTypeTextBox.sendKeys(strDesc);
				newCampaignCreateButton.click();
			}

		}
	}

	/***
	 * This method added to click on save button
	 * 
	 * @return -return the generated masterid
	 */
	private String saveHeader() {

		saveButton.click();
		return getMasterId();
	}

	/***
	 * This method will return the job number
	 * 
	 * @author sangeetap
	 */

	public String getMasterId() {
		String strloginUrl = driver.getCurrentUrl();
		System.out.println(strloginUrl);
		String jobNum = driver.getCurrentUrl().substring(
				strloginUrl.lastIndexOf("#") + 1);
		System.out.println(jobNum);
		return jobNum;
	}

	/**
	 * This method is addded to save SMS Header with Short code & Campaign
	 * 
	 * @author sangeetap
	 */

	public void saveSmsHeader(String visibleShortCode) {
		//
		fillSMSMasterName();
		selectShortcode(visibleShortCode);
		saveHeader();

	}

}
