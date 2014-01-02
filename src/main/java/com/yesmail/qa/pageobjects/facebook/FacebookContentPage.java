/***
 * File : FacebookContentPage.java
 * Description : This java class is used to fill the content of Facebook post
 * @author ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * 1.Add header comment and comment for each method in 
 * FacebookContentPage java files.  
 * 2.The method selectPage(String) is undefined for the type FacebookContentPage .Please create. 
 *   
 */

package com.yesmail.qa.pageobjects.facebook;

import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class FacebookContentPage {

	
	@FindBy(css = "span[data-target='account'] select")
	private WebElement accountDropDown;

	@FindBy(css = ".m-facebookContent>div:nth-of-type(1) select")
	private WebElement campaignDropDown;

	@FindBy(id = "message")
	private WebElement messageTextBox;

	@FindBy(css = ".m-facebookContent>div:nth-of-type(1) input#name")
	private WebElement nameTextBox;

	// @FindBy(id="pages")
	@FindBy(css = "div:nth-child(2)>span[data-target='page'] select")
	private WebElement pageDropDown;

	@FindBy(id = "savePost")
	private WebElement savebutton;

	@FindBy(id = "preview")
	private WebElement previewPost;

	@FindBy(id = "linkUrl")
	private WebElement linkURL;

	@FindBy(id = "linkName")
	private WebElement linkName;

	@FindBy(id = "thumbnailUrl")
	private WebElement thumbNailUrl;

	@FindBy(id = "caption")
	private WebElement caption;

	@FindBy(id = "description")
	private WebElement desCription;

	@FindBy(css = "input.span3")
	private WebElement campaignNewCampaignName;

	@FindBy(css = "select.span3>option:nth-of-type(2)")
	private WebElement newCampaignTypeSelect;

	@FindBy(css = "input.span2")
	private WebElement newCampaignNewTypeTextBox;

	@FindBy(css = "button.btn:nth-child(2)")
	private WebElement newCampaignCreateButton;

	@FindBy(css = "#pages option[selected='selected']")
	private WebElement defaultPageSelected;

	@FindBy(css = "div:nth-child(2)>a:nth-of-type(1)")
	private WebElement contentTab;

	@FindBy(css = "div:nth-child(2)>a:nth-of-type(2)")
	private WebElement scheduleTab;

	private WebDriver driver;
	private String pageUrl;

	//define the constructor
	 
	public FacebookContentPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	} // end of constructor

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(By.id("name")), driver,
				50))
			throw new FrameworkException(FacebookContentPage.class.getName()
					+ " is not loaded");
	}

	public FacebookContentPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	/***
	 * This method is added to navigate to Content Tab
	 */
	public void navigateToContentTab() {
		contentTab.click();
	}

	/***
	 * This method is added to navigate to Schedule Tab
	 */
	public void navigateToScheduleTab() {
		scheduleTab.click();
	}

	/**
	 * 
	 * *This method is added to fill and save the Facebook contents
	 * 
	 * @author sangeetap
	 * 
	 */
	public String fillFacebookContent() {
		enterFacebookName();
		selectCampaign();
		selectFacebookAccount();
		selectPage();
		enterFacebookContent();
		String FbId = saveFacebookContent();
		return FbId;
	}

	/***
	 * This method is added to select the Facebook page
	 * 
	 * @author sangeetap
	 */

	private void selectPage() {

		DriverUtility.waitforElementDisplay(driver, defaultPageSelected, 10);
		DriverUtility.selectDropDown(pageDropDown,
				PagesHelper.FACEBOOK_PAGE_NAME, 1);
	}

	/***
	 * This method is added to select Facebook account
	 * 
	 * @author sangeetap
	 * 
	 */

	public void selectFacebookAccount() {
		DriverUtility.selectDropDown(accountDropDown,
				PagesHelper.FACEBOOK_ACCOUNT, 2);
	}

	/***
	 * This method is added to select campaign from drop down, if 'Create New'
	 * option selected it will create a new campaign
	 * 
	 * @author sangeetap
	 */
	public void selectCampaign() {
		DriverUtility.waitforElementDisplay(driver, campaignDropDown, 20);
		DriverUtility.selectDropDown(campaignDropDown,
				PagesHelper.FACEBOOK_CONTENT_CAMPAIGN_NAME, 2);

		if (PagesHelper.FACEBOOK_CONTENT_CAMPAIGN_NAME
				.equalsIgnoreCase("Create New...")) {
			campaignNewCampaignName.clear();
			String strName = Utils
					.getUniqueName(PagesHelper.FACEBOOK_CONTENT_NAME);
			Utils.getUniqueName(strName);
			campaignNewCampaignName.sendKeys(strName);

			/***
			 * No option currently avaliable in type drop down so added comment
			 * to below code
			 */

			/*
			 * if(newCampaignTypeSelect.toString().equalsIgnoreCase(
			 * ("Create New..."))) { String StrDesc = Utils
			 * .getUniqueName(PagesHelper.FACEBOOK_CONTENT_DESC); StrDesc =
			 * StrDesc.substring(0, StrDesc.length() - 20);
			 * newCampaignNewTypeTextBox.sendKeys(StrDesc);
			 */
			newCampaignCreateButton.click();
		}

	}

	/***
	 * This method is added to enter the Facebook message content
	 */

	public void enterFacebookContent() {
		String strMsgName = PagesHelper.FACEBOOK_CONTENT_NAME
				+ UUID.randomUUID();
		messageTextBox.clear();
		messageTextBox.sendKeys(strMsgName);
	}

	/***
	 * This method is added to enter the Facebook post name
	 */

	public void enterFacebookName() {
		String strFbName = Utils.getUniqueName(PagesHelper.FACEBOOK_NAME, 25);
		nameTextBox.clear();
		nameTextBox.sendKeys(strFbName);
	}

	/***
	 * This method is added to click on save button
	 * 
	 * @return getMaserId - return generated Facebook matster id
	 */

	public String saveFacebookContent() {
		savebutton.click();
		return getMasterId();
	}

	/***
	 * This method is added to get the generated Facebook master id
	 * 
	 * @return getMaserId - return generated Facebook master id
	 */

	public String getMasterId() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(nameTextBox),
				this.driver, 20);
		String loginUrl = driver.getCurrentUrl();
		String jobNum = driver.getCurrentUrl()
				.substring(loginUrl.lastIndexOf("#") + 1)
				.replaceAll("[^0-9]", "");
		return jobNum;
	}

} // end of FacebookContentPage class

