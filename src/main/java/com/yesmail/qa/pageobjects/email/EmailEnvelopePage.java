/***
 * File : EmailEnvelopePage.java
 * Description : Page object class for EmailEnvelopePage functionality 
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *    
 */
package com.yesmail.qa.pageobjects.email;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class EmailEnvelopePage extends EmailBase {

	private WebDriver driver;
	private String pageUrl;	
	private String masterName = Utils.getUniqueName(PagesHelper.EMAIL_MASTER, 25);

	@FindBy(id = "name")
	private WebElement nameTextBox;

	@FindBy(css = "select[name='divisionName']")
	private WebElement divisionDropDown;

	@FindBy(css = "div:nth-child(2) form div:nth-child(2)>div:nth-child(1)>span>.campaign")
	private WebElement campaignDropDown;

	@FindBy(id = "fromName")
	private WebElement fromTextBox;

	@FindBy(css = "div[data-target=personalize-subject] button")
	private WebElement subjectPersonalizeButton;

	@FindBy(css = "div[data-target=personalize-subject] select.attributes")
	private WebElement subjectPersonalizeDropdown;

	@FindBy(css = "div[data-target=personalize-subject] button.ym-btn.ym-btn-primary.insert")
	private WebElement subjectPersonalizeInsertButton;

	@FindBy(css = "[data-target=subject] .personalizedField")
	private WebElement subjectLine;

	@FindBy(css = "input.span3")
	private WebElement campaignNewCampaignName;

	@FindBy(css = "input.span2")
	private WebElement newCampaignNewTypeTextBox;

	@FindBy(css = "div.modal.fade.in div.modal-footer > button:nth-of-type(2)")
	private WebElement newCampaignCreateButton;

	@FindBy(css = "select[name='deliveryType']")
	private WebElement deliveryTypeDropDown;

	@FindBy(css = "select[name='encodingId']")
	private WebElement encodingDropDown;

	@FindBy(css = "input[id = 'keywordsTextbox']")
	private WebElement keyWordsTextBox;

	@FindBy(css = "button[id = 'keywordsAddBtn']")
	private WebElement addBTN;

	@FindBy(css = ".envelope-description label:nth-of-type(1)")
	private WebElement keyWordsTextArea;

	@FindBy(id = "emailEnvelopeUpload")
	private WebElement xmlUploadLink;

	@FindBy(id = "fileupload")
	private WebElement xmlUploadPath;

	@FindBy(css = "button[data-id = 'ok']")
	private WebElement submitxmlUploadLinkBTN;

	@FindBy(css = "button[id = 'saveEnvelope']")
	private WebElement saveEnvelope;

	@FindBy(css = "form > select.span3")
	private WebElement campaignTypeDropdown;

	/**
	 * Constructor section
	 * 
	 */
	public EmailEnvelopePage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	/***
	 * This method is added to load the page.
	 */
	public EmailEnvelopePage load() {
		driver.get(PagesHelper.URL+pageUrl);
		return this;
	}

	/***
	 * This method is added to verify whether page is loaded.
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(nameTextBox), driver,
				50))
			throw new FrameworkException(EmailEnvelopePage.class.getName()
					+ " is not loaded");
	}

	/****
	 * Enter Name field text
	 * 
	 * @author sangeetap
	 */
	private void fillEnvName() {		
		nameTextBox.clear();
		nameTextBox.sendKeys(masterName);
	}

	/***
	 * This method is added to select the division from the Division drop down
	 * 
	 * @param option
	 *            - String of the division drop down
	 * 
	 * @author sangeetap
	 */
	private void selectDivision() {

		DriverUtility.selectDropDown(divisionDropDown,
				PagesHelper.EMAIL_DIVISION, 0);
	}

	/***
	 * This method is added to select campaign from drop down, if 'Create New'
	 * option selected it will create a new campaign
	 * 
	 * 
	 * @author sangeetap
	 */
	private void selectCampaign(String campaignType) {
		DriverUtility.selectDropDown(campaignDropDown,
				PagesHelper.EMAIL_CAMPAIGN_NAME, 1);		

		if (PagesHelper.EMAIL_CAMPAIGN_NAME.equalsIgnoreCase("Create New...")) {
			campaignNewCampaignName.clear();
			String strName = Utils.getUniqueName("test_campaign", 20);
			campaignNewCampaignName.sendKeys(strName);

			DriverUtility.selectDropDown(campaignTypeDropdown, campaignType, 1);
			if (campaignType.equalsIgnoreCase("Create New...")) {
				String StrDesc = Utils.getUniqueName("test_campaign_type");
				StrDesc = StrDesc.substring(0, StrDesc.length() - 20);
				newCampaignNewTypeTextBox.sendKeys(StrDesc);
			}			
			newCampaignCreateButton.click();

		}
	}

	/***
	 * Enter from field text
	 * 
	 */
	private void fillfromName() {
		fromTextBox.clear();
		fromTextBox.sendKeys(masterName);
	}

	/***
	 * Enter Subject field text
	 * 
	 */
	private void fillSubject() {
		subjectLine.clear();
		subjectPersonalizeButton.click();
		DriverUtility.waitFor(ExpectedConditions
				.elementToBeClickable(subjectPersonalizeDropdown), driver, 10);

		selectSubjectPersonalization("firstName");
	}

	/***
	 * Select Delivery type
	 * 
	 */
	private void selectDeliveryType(String deliveryType) {
		DriverUtility.selectDropDown(deliveryTypeDropDown, deliveryType, 1);

	}

	/***
	 * Select Encoding type
	 * 
	 */
	private void selectEncodingType(String encodingType) {
		DriverUtility.selectDropDown(encodingDropDown, encodingType, 1);
	}

	/***
	 * Select User Defined Keyword
	 * 
	 */
	public boolean userDefineKeyWord(String keyWord) {
		keyWordsTextBox.clear();
		keyWordsTextBox.sendKeys(keyWord);
		addBTN.click();
		String keyWords = keyWordsTextArea.getText();
		if (keyWords.contains(keyWord)) {
			return true;
		}
		return false;
	}

	/***
	 * This method is added to fill and save the envelop page.
	 * 
	 * @return - Master Name
	 */
	public String createEnvelope() {
		fillEnvName();
		selectCampaign("Create New...");
		selectDivision();		
		fillSubject();
		selectDeliveryType("Send HTML and Plain Text");
		selectEncodingType(PagesHelper.EMAIL_ENCODING_TYPE);
		fillfromName();
		saveEnvelope.click();
		Reporter.log("Ribbon Text for EnvelopePage is: "+getRibbonText(10)+"<br>");		
		return masterName;		
	}

	/***
	 * This method is added to fill and save the envelop page.
	 * 
	 * @param list
	 *            of attributes for Subject Personalization
	 * @return Envelope Created(True/False)
	 */
	public String createEnvelope(List<String> attributes) {
		fillEnvName();
		selectCampaign("Create New...");
		selectDivision();
		fillfromName();
		subjectPersonalizeButton.click();
		for (String attribute : attributes) {
			selectSubjectPersonalization(attribute);
		}
		selectDeliveryType("Send HTML and Plain Text");
		selectEncodingType(PagesHelper.EMAIL_ENCODING_TYPE);
		saveEnvelope.click();
		return masterName;
	}

	/***
	 * This method is added to upload XML.
	 * 
	 * @return envelope Created(True/False)
	 */
	public boolean uploadXml() {
		xmlUploadLink.click();
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(xmlUploadPath), driver,
				10);

		xmlUploadPath.sendKeys(Utils.getResources(this, "fileName"));// Replace File Name with Actual.
		submitxmlUploadLinkBTN.click();
		return stepCompleted(1, 10);		
	}

	/***
	 * This method is added to select Subject Personalization.
	 * 
	 */
	private void selectSubjectPersonalization(String attribute) {
		DriverUtility.selectDropDown(subjectPersonalizeDropdown, attribute, 1);
		subjectPersonalizeInsertButton.click();
	}

}
