/**File name : TestEnvelopPage.java
 * @Description: This Java Class is used to fill the Test Envelop page. 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @since completed by 12/4/13
 * @Version History
 * Version name Updated By Reason / Comments 
 * */

package com.yesmail.qa.pageobjects.mvt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.yesmail.qa.pageobjects.PagesHelper;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;

public class TestEnvelopPage extends MvtBase {

	private WebDriver driver;

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

	@FindBy(css = "div.last>div:nth-child(1)>div:nth-child(2)>i.add")
	private WebElement plusSignSubject;

	// Constructor

	public TestEnvelopPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public TestEnvelopPage load() {

		navigateToEnvelopeTab();
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(nameTextBox),
				driver, 50))
			throw new FrameworkException(TestEnvelopPage.class.getName()
					+ " is not loaded");
	}

	/****
	 * Enter Name field text
	 * 
	 * @author sangeetap
	 */

	public void fillEnvName() {

		String strEnv = Utils.getUniqueName(
				PagesHelper.MULTIVARIATE_ENVELOPE_NAME, 25);
		Reporter.log("Entering Envelope name as:" + strEnv + "<br>");
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
	public void selectDivision() {
		if (DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(divisionDropDown), driver, 50) != null)
			DriverUtility.selectDropDown(divisionDropDown,
					PagesHelper.MULTIVARIATE_ENVELOPE_DIVISION, 1);
		Reporter.log("Selecting division:"
				+ PagesHelper.MULTIVARIATE_ENVELOPE_DIVISION + "<br>");
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
			Reporter.log("Subject Name:" + strSub + "<br>");
			countSubject--;

			if (countSubject == 0)
				break;
			else
				i++;
			if (DriverUtility.waitFor(ExpectedConditionExtended
					.elementsToBeClickable(plusSignSubject), driver, 10) != null)
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
		DriverUtility.selectDropDown(campaignDropDown,
				PagesHelper.MULTIVARIATE_ENVELOPE_CAMPAIGN_NAME, 1);

		if (PagesHelper.MULTIVARIATE_ENVELOPE_CAMPAIGN_NAME
				.equalsIgnoreCase("Create New...")) {
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
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(fromTextBox),
				driver, 20);
		fromTextBox.clear();
		fromTextBox.sendKeys(Utils.getUniqueName(
				PagesHelper.MULTIVARIATE_ENVELOPE_NAME, 25));
	}

	/***
	 * Save Envelope
	 */
	public boolean saveEnvelope() {
		saveEnvelopeButton.click();
		Reporter.log("Ribbon Text Message for Test Envelop Page is: <br> "
				+ getRibbonText(20) + " <br> ", true);
		return stepCompleted(2, 20);
	}

	/***
	 * This method is added to select encoding type
	 * 
	 * @param encodingType
	 */
	private void selectEncodingType(String encodingType) {
		DriverUtility.selectDropDown(encodingDropDown, encodingType, 1);

	}

	/***
	 * This method is added to fill and save the envelop page.
	 * 
	 * @param subjectCount
	 *            - number of subject to add
	 */
	public boolean fillEnvelopePage(int subjectCount) {
		Reporter.log("Started filling Envelope: <br>", true);
		fillEnvName();
		selectDivision();
		fillSubject(subjectCount);
		fillfrom();
		selectEncodingType(PagesHelper.MULTIVARIATE_ENVELOPE_ENCODING);
		return saveEnvelope();

	}

}
