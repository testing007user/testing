/** File Name: CampaignManagement.java  
 * Description: This Java Class is 
 * used to perform different opetions Edit/Copy/ Rename/ Save as Template/Create Campaign
 *  /Create view for Campaign , Automated Campaign and Automated Template. 
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * Date: 18/11/2013 
 * Version History  
 * 
 * Version name Updated By Reason / Comments 
 * Version:1.1 Updated for below reason
 * 1.Variable names should start with small character.
 * 2.Also you can remove navigation methods that are returning page objects. As we are going to use 'Page Object Factory' to get new page object. 
 * */

package com.yesmail.qa.pageobjects.campaigns;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.libraries.Utils;

public class CampaignManagement {

	private String pageURL;
	private WebDriver driver;

	@FindBy(css = ".pageTitle")
	private static WebElement CampaignPageTitle;

	@FindBy(css = "div:nth-child(2) button")
	private static WebElement createViewBtn;

	@FindBy(css = "div.leftControls:nth-child(1) ul li:nth-child(1) a")
	private static WebElement campaignsBtn;

	@FindBy(css = "div.leftControls:nth-child(1) ul li:nth-child(2) a")
	private WebElement automatedCampaignsBtn;

	@FindBy(css = ".ym-btn-toggleButton > li:nth-child(3) > a:nth-child(1)")
	private WebElement automatedTemplatesBtn;

	@FindBy(css = "input.search-query")
	private static WebElement campaignsSearchTxtBox;

	@FindBy(id = "input[data-id='nameInput']")
	private static WebElement createCampaignnamePopupWindow;

	@FindBy(css = ".checkbox input[type='checkbox']")
	private static WebElement automatedCampaignChkBox;

	@FindBy(css = ".modal-footer button[class='btn btn-primary ok']")
	private static WebElement createCampaignOkBtn;

	@FindBy(css = ".modal.fade.in")
	private static WebElement popUpModal;

	@FindBys(value = { @FindBy(css = "tbody > tr > td:nth-of-type(2)") })
	private static List<WebElement> searchedCampaign;

	// list of WebElement that represent campaign Name in the table
	@FindBys(value = { @FindBy(css = "table tr>td:nth-of-type(2)") })
	private static List<WebElement> campaigns;

	@FindBy(css = "div.modal.fade.in div.controls > input")
	private static WebElement copyCampaignTxtBoxPopupWindow;

	@FindBy(css = "div.modal.fade.in div.controls > input")
	private static WebElement saveTemplateTxtBoxPopupWindow;

	@FindBy(css = "body div:nth-child(3) button[class='ym-btn ym-btn-primary ok']")
	private static WebElement copyCampaignOkBtnPopupWindow;

	@FindBy(css = "body div:nth-child(3) button[class='ym-btn ym-btn-primary ok']")
	private static WebElement saveTemplateOkBtnPopupWindow;

	/**
	 * Constructor section
	 * 
	 * @return
	 */

	public CampaignManagement(WebDriver driver, String pageUrl) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageURL = pageUrl;

	}

	public void loadCampaignManagementPage() {
		driver.get(pageURL);
		DriverUtility.waitFor(
				ExpectedConditions.visibilityOf(campaignsSearchTxtBox), driver,
				120);
	}

	/***
	 * This method is added to click on the 'Campaigns' button to list out all
	 * Campaign
	 * 
	 * @author Sangeeta Pote
	 * @since Completed on 18th Nov 2013
	 */
	public void navigateToCampaignsTab() {
		campaignsBtn.click();
	}

	/***
	 * This method is added to click on the 'Automated Campaign' button to list
	 * out all Automated Campaign
	 * 
	 * @author Sangeeta Pote
	 * @param driver
	 * @since Completed on 18th Nov 2013
	 */
	public void navigateToAutomatedCampaignsTab() {
		automatedCampaignsBtn.click();
	}

	/***
	 * This method is added to click on the 'Automated Templates' button to list
	 * out all Automated Templates
	 * 
	 * @author Sangeeta Pote
	 * @param driver
	 * @since Completed on 18th Nov 2013
	 */

	public void clickAutomatedTemplatesTab() {
		automatedTemplatesBtn.click();

	}

	/***
	 * This method is added to click on the 'Edit' link to the existing
	 * Campaign.
	 * 
	 * @author Sangeeta Pote
	 * @param CampaignLabel
	 * @return
	 * @since Completed on 18th Nov 2013
	 */
	public void editLinkOperation(String Campaignslabel) {
		int row;
		row = getRowIndex(Campaignslabel);

		WebElement editLink = driver.findElement(By
				.cssSelector("table tr:nth-of-type(" + row
						+ ") > td> ul > li:nth-of-type(1) > a"));
		editLink.click();

	}

	/***
	 * This method is added to create a copy of existing Campaign.
	 * 
	 * @author Sangeeta Pote
	 * @param CampaignLabel
	 * @return Copied Campaign Name
	 * @since Completed on 18th Nov 2013
	 */
	public String copyLinkOperation(String CampaignLabel) {
		int row;
		row = getRowIndex(CampaignLabel);

		WebElement copyLink = driver.findElement(By
				.cssSelector("table tr:nth-of-type(" + row
						+ ") > td> ul > li:nth-of-type(2) > a"));
		copyLink.click();
		DriverUtility.waitforElementDisplay(driver, popUpModal, 5);

		Long number = System.currentTimeMillis();
		String copiedCampaignName = "test" + number;
		copyCampaignTxtBoxPopupWindow.sendKeys(copiedCampaignName);
		copyCampaignOkBtnPopupWindow.click();

		return copiedCampaignName;
	}

	/***
	 * This method is added to click on the 'Rename' link and rename the
	 * existing Campaign.
	 * 
	 * @author Sangeeta Pote
	 * @param CampaignLabel
	 * @return renamed Campaign Name
	 * @since Completed on 18th Nov 2013
	 */
	public String renameLinkOperation(String CampaignLabel) {
		int row;
		String rename = Utils.getUniqueName(CampaignLabel);
		row = getRowIndex(CampaignLabel);

		WebElement renameLink = driver.findElement(By
				.cssSelector("table tr:nth-of-type(" + row
						+ ") > td> ul > li:nth-of-type(3) > a"));
		renameLink.click();
		return rename;
	}

	/***
	 * This method is added to click on the 'Save As Template' link and save the
	 * campaign as a template.
	 * 
	 * @author Sangeeta Pote
	 * @param CampaignLabel
	 * @since Completed on 18th Nov 2013
	 */
	public void saveAsTemplateLinkOperation(String CampaignLabel) {
		int row;
		row = getRowIndex(CampaignLabel);

		WebElement saveAsTemplateLink = driver.findElement(By
				.cssSelector("table tr:nth-of-type(" + row
						+ ") > td> ul > li:nth-of-type(4) > a"));
		saveAsTemplateLink.click();
		DriverUtility.waitforElementDisplay(driver, popUpModal, 5000);
		String templateName = "test" + System.currentTimeMillis();
		saveTemplateTxtBoxPopupWindow.sendKeys(templateName);
		saveTemplateOkBtnPopupWindow.click();
	}

	/***
	 * This method is added to click on the 'Report' link
	 * 
	 * @author Sangeeta Pote
	 * @param CampaignLabel
	 * @since Completed on 18th Nov 2013
	 */
	public void reportLinkOperation(String CampaignLabel) {
		int row;
		row = getRowIndex(CampaignLabel);

		WebElement reportLink = driver.findElement(By
				.cssSelector("table tr:nth-of-type(" + row
						+ ") > td> ul > li:nth-of-type(5) > a"));
		reportLink.click();

	}

	/***
	 * This method is added to search for specific Campaigns in
	 * Campaigns/Automated Campaigns/Automated Templates
	 * 
	 * @param SearchLabel
	 * @author Sangeeta Pote
	 * @since Completed on 18th Nov 2013
	 */
	public boolean searchCampaigns(String searchlabel) {
		// TODO Auto-generated method stub
		campaignsSearchTxtBox.clear();
		campaignsSearchTxtBox.sendKeys(searchlabel);
		if (searchlabel.equalsIgnoreCase(searchedCampaign.get(0).getText()))
			return true;
		else
			return false;
	}

	/***
	 * This method is added to create a new Campaign
	 * 
	 * @param automatedCampaignCheckBox
	 * @return Created Campaign Name
	 * @author Sangeeta Pote
	 * @since Completed on 18th Nov 2013
	 */

	public String createCampaign(boolean automatedCampaignCheck) {

		createViewBtn.click();
		DriverUtility.waitforElementDisplay(driver, popUpModal, 10);
		long number = System.currentTimeMillis();
		String strCampaignTextToType = "test" + number;
		createCampaignnamePopupWindow.sendKeys(strCampaignTextToType);
		// DriverUtility.selectDropDown(typeDropDown, "CampaignName", 2);
		if (automatedCampaignCheck)
			automatedCampaignChkBox.click();
		createCampaignOkBtn.click();
		return strCampaignTextToType;
	}

	/***
	 * This method is added to get the Row Index of the matching Campaign
	 * 
	 * @param label
	 * @return Matched Row Index
	 * @author Sangeeta Pote
	 * @since Completed on 18th Nov 2013
	 */
	private int getRowIndex(String label) {
		int index;

		for (index = 0; index < campaigns.size(); index++) {
			if (campaigns.get(index).getText().equalsIgnoreCase(label)) {
				break;
			}
		}
		return index + 1;
	}

}
