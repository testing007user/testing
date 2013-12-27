/**File name : TestEnvelopPage.java
 * @Description: This Java Class is used to fill the Test Envelop page. 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @since completed by 12/4/13
 * @Version History
 * Version name Updated By Reason / Comments 
 * */

package com.yesmail.qa.pageobjects.mvt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.yesmail.qa.pageobjects.PagesHelper;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;

public class TestEnvelopPage extends MvtBase {

	// Page Elements for TestEnvelope Class

	@FindBy(id = "name")
	private WebElement nameTextBox;

	@FindBy(css = "select[name='divisionName']")
	private WebElement divisionDropDown;

	@FindBy(css = "div#fromName")
	private WebElement fromTextBox;

	@FindBy(css = "div:nth-child(2) form div:nth-child(2)>div:nth-child(1)>span>.campaign")
	private WebElement campaignDropDown;

	@FindBy(css = "input.span3")
	private WebElement campaignNewCampaignName;

	@FindBy(css = "select.span3>option:nth-of-type(2)")
	private WebElement newCampaignTypeSelect;

	@FindBy(css = "input.span2")
	private WebElement newCampaignNewTypeTextBox;

	@FindBy(css = "div.modal-footer>button:nth-of-type(2)")
	private WebElement newCampaignCreateButton;

	@FindBy(css = "select[name='encodingId']")
	private WebElement encodingDropDown;

	@FindBy(id = "saveEnvelope")
	private WebElement saveEnvelopeButton;

	@FindBy(css = "div[data-target='subject']>div>div>div>div.personalizedField")
	private WebElement subject;

	@FindBys(value = { @FindBy(css = "div[data-target='subject']>div>div>div>div") })
	private List<WebElement> subjects;

	@FindBy(css = "div>i.icon-plus-sign")
	private WebElement plusSignSubject;

	@FindBy(css = "div>i.icon-minus-sign")
	private WebElement minusSignSubject;

	@FindBy(css = "select[name='deliveryType']")
	private WebElement deliveryType;

	private WebDriver driver;

	// Constructor

	public TestEnvelopPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		

	}
	
	public TestEnvelopPage load()
	{
		
		navigateToEnvelopeTab();
		return this;
	}
	
	public void isLoaded()
	{
		if(null == DriverUtility.waitFor(ExpectedConditions.elementToBeClickable(By.id("name")), driver,50))
			throw new FrameworkException(TestEnvelopPage.class.getName()+" is not loaded");
	}

	/****
	 * Enter Name field text
	 * 
	 * @author sangeetap
	 */

	public void fillEnvName() {

		String strEnv = Utils
				.getUniqueName(PagesHelper.MULTIVARIATE_ENVELOPE_NAME,25);
		Reporter.log("Entering Envelope name as:"+strEnv);
		nameTextBox.clear();
		nameTextBox.sendKeys(strEnv);
	}

	/***
	 * This method is added to select the division from the Division drop down
	 * 
	 * @param option
	 *            - String of the division drop down
	 * 
	 * @author sangeetap
	 */
	public void selectDivision(String visibleDivision) {

		Reporter.log("Selecting division:"+visibleDivision);
		DriverUtility.selectDropDown(divisionDropDown,
				PagesHelper.MULTIVARIATE_ENVELOPE_DIVISION, 0);
	}

	/***
	 * This method is added to fill the subject as per the subject count
	 * 
	 * @param countSubject
	 *            -number of subject to add
	 * 
	 * @author sangeetap
	 */

	public void fillSubject(int countSubject) {
		// Variable declaration
		int i = 1;

		while (countSubject >= 0) {
			String strSub = "Subject" + countSubject;
			WebElement keyEle = driver
					.findElement(By
							.cssSelector("div[data-target='subject']>div>div:nth-child("
									+ i + ")>div:nth-child(1)>div:nth-child(1)"));
			keyEle.sendKeys(strSub);
			countSubject--;

			if (countSubject == 0)
				break;
			else
				i++;
			plusSignSubject.click();
		}
	}

	/***
	 * This method is added to select campaign from drop down, if 'Create New'
	 * option selected it will create a new campaign
	 * 
	 * 
	 * @author sangeetap
	 */
	public void selectCampaign() {
		DriverUtility.selectDropDown(campaignDropDown, PagesHelper.MULTIVARIATE_ENVELOPE_CAMPAIGN_NAME, 1);
		
		if (PagesHelper.MULTIVARIATE_ENVELOPE_CAMPAIGN_NAME.equalsIgnoreCase("Create New...")) {
			//DriverUtility.selectDropDown(campaignDropDown,PagesHelper.MULTIVARIATE_ENVELOPE_CAMPAIGN_NAME, 1);
			campaignNewCampaignName.clear();
			String strName = Utils
					.getUniqueName(PagesHelper.MULTIVARIATE_CONTENT_NAME);
			strName = strName.substring(0, strName.length() - 20);
			campaignNewCampaignName.sendKeys(strName);
			if (newCampaignTypeSelect.toString().equalsIgnoreCase(
					("Create New..."))) {
				String StrDesc = Utils
						.getUniqueName(PagesHelper.MULTIVARIATE_CONTENT_DESC);
				StrDesc = StrDesc.substring(0, StrDesc.length() - 20);
				newCampaignNewTypeTextBox.sendKeys(StrDesc);
				newCampaignCreateButton.click();
			}

		}
	}
	/***
	 * Enter from field text
	 * 
	 */

	public void fillfrom() {
		fromTextBox.clear();
		fromTextBox.sendKeys("from_test");
	}

	/***
	 * This method is added to fill and save the envelop page.
	 * 
	 * @param subjectCount
	 *            - number of subject to add
	 */
	public void fillEnevlopePage(int subjectCount,String visibleDivision) {

		fillEnvName();
		selectDivision(visibleDivision);
		fillfrom();
		fillSubject(subjectCount);
		DriverUtility.selectDropDown(encodingDropDown,
				PagesHelper.MULTIVARIATE_ENVELOPE_ENCODING, 1);
		saveEnvelopeButton.click();
	}

}
