/***
 * File : CreateCampaignPage.java
 * Description : This java class is used to create new automated Campaign
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *    
 */

package com.yesmail.qa.pageobjects.campaign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.PagesHelper;

public class CreateCampaignPage {

	/*
	 * ##########################################################################
	 * * Campaign page web element allocation
	 * ###################################
	 * #######################################
	 */

	@FindBy(css = "div:nth-child(2) button")
	private static WebElement createViewBtn;

	@FindBy(css = ".modal.fade.in")
	private static WebElement popUpModal;

	@FindBy(id = "input[data-id='nameInput']")
	private static WebElement createCampaignnamePopupWindow;

	@FindBy(css = ".checkbox input[type='checkbox']")
	private static WebElement automatedCampaignChkBox;

	@FindBy(css = "form.form-inline select.span3")
	private static WebElement typeDropDown;

	@FindBy(css = ".modal-footer button[class='btn btn-primary ok']")
	private static WebElement createCampaignOkBtn;

	@FindBy(css = "span.icon-chevron-down")
	private WebElement selectDropdown;

	@FindBy(css = "li[class = 'create-campaign']")
	private WebElement createAutoCampaignBtn;

	@FindBy(css = "input [data-id= 'typeInput']")
	private WebElement campaignType;

	@FindBy(css = "button.ok")
	private WebElement okBtn;

	private WebDriver driver;
	private String pageUrl;

	/*
	 * ##########################################################################
	 * * define the constructor
	 * #################################################
	 * #########################
	 */

	public CreateCampaignPage(WebDriver driver, String pageUrl) {

		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	} // end of constructor

	/*
	 * ##########################################################################
	 * * define required methods
	 * ################################################
	 * ##########################
	 */

	public CreateCampaignPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;

	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(createViewBtn), driver,
				20))
			throw new FrameworkException(CreateCampaignPage.class.getName()
					+ "is not loaded");
	}

	/***
	 * This method is added to create a new Campaign
	 * 
	 * @author Sangeeta Pote
	 * @modified on 27th Dec 2013
	 * @param automatedCampaignCheck
	 *            - boolean yes / no for check / uncheck
	 * @return - created campaign name
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

} // end of CreateCampaignPage class

