/**File name : TestSetupPage.java
 * Description: This Java Class is 
 * used to fill details on the Test Setup page 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @scompleted by 12/2/13
 * Version History
 * Version name Updated By Reason / Comments 
 * Version:1.1 Updated for below reason
 *   
 *	1.Please check naming conventions of web elements 
 *	2.Please check css of SelectContentDropdown . It will not work if there are any UI changes.Try to keep it specific to web element 
 *	3.Remove System.out.println() from code 
 *	4.Though Thread.sleep() is added for testing purpose. Do not keep it in final version. Do not use thread.sleep. 
 */

package com.yesmail.qa.pageobjects.mvt;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.yesmail.qa.pageobjects.PagesHelper;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.DriverUtility.CHECK_UNCHECK;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;

public class TestSetupPage extends MvtBase {

	private WebDriver driver;
	private String pageUrl;

	// Page Elements for Test Set up class

	@FindBy(id = "name")
	private WebElement testName;

	@FindBy(id = "description")
	private WebElement testDescription;

	@FindBy(css = "div:nth-of-type(2) > form > label:nth-of-type(1) > input")
	private WebElement checkboxSubject;

	@FindBy(css = "div:nth-of-type(2) > form > label:nth-of-type(2) > input")
	private WebElement checkboxContent;

	@FindBy(name = "isAutoSend")
	private WebElement checkboxAutoSendWinningMessage;

	@FindBy(id = "saveTest")
	private WebElement saveTestButton;

	@FindBys({ @FindBy(css = "div > div:nth-of-type(5) > div > table > tbody > tr > td:nth-of-type(1)") })
	private List<WebElement> mastersIdCreatedSubContent;

	@FindBys({ @FindBy(css = "div > div:nth-of-type(4) > div > table > tbody > tr > td:nth-of-type(1)") })
	private List<WebElement> mastersIdCreatedSubOrContent;

	@FindBy(id = "saveEnvelope")
	private WebElement saveEnvelopeButton;

	// Constructor

	public TestSetupPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}

	public TestSetupPage load() {
		Reporter.log("Navigating to -->Test Setup Page<br>", true);
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(testName),
				driver, 50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");

	}

	/***
	 * This method is added to fill the Name on Test Setup page
	 * 
	 * @param testString
	 */

	private String fillName(String testString) {
		testName.clear();
		testName.sendKeys(testString);
		return testString;
	}

	/***
	 * This method is added to fill the description on Test Setup page
	 * 
	 * @param desc
	 */
	private void fillDescription(String desc) {
		testDescription.clear();
		testDescription.sendKeys(desc);

	}

	/***
	 * This method is added to fill details on the Test Setup page and return
	 * the created jon number
	 * 
	 * @param subjectCheckBox
	 *            Will take two value as input "true(check)" or "false(uncheck)"
	 * @param contentCheckBox
	 *            Will take two value as input "true(check)" or "false(uncheck)"
	 * @param checkUncheck
	 *            Will take two value as input "true(check)" or "false(uncheck)"
	 */
	public boolean fillSetUpPage(CHECK_UNCHECK checkboxFlag,
			boolean contentCheckBox, String autoSendcheck, String masterName) {
		boolean autoSendCheck = false;
		if (autoSendcheck.equalsIgnoreCase("True")) {
			autoSendCheck = true;
		}

		Reporter.log("Filling TestSetUp Page:<br> ", true);
		fillName(masterName);
		fillDescription(Utils.getUniqueName(
				PagesHelper.MULTIVARIATE_SETUP_DESC, 20));
		DriverUtility.checkUncheckCheckBox(checkboxSubject, checkboxFlag);
		checkUncheckCheckboxContent(contentCheckBox);
		checkboxAutoSendWinningMessage(autoSendCheck);
		saveTestButton.click();
		Reporter.log("Ribbon Text Message for Test Setup Page is:"
				+ getRibbonText(10) + "<br>", true);

		// This is added as workaround for setup page reload issue.
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(saveEnvelopeButton), driver, 15);

		return stepCompleted(1, 10);
	}

	/***
	 * This method will return the job number
	 * 
	 */

	public String getMasterId() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeDisabled(checkboxSubject),
				this.driver, 40);
		String loginUrl = driver.getCurrentUrl();
		String jobNum = driver.getCurrentUrl()
				.substring(loginUrl.lastIndexOf("#") + 1)
				.replaceAll("[^0-9]", "");
		Reporter.log("Job Number is:" + jobNum + "<br>", true);
		return jobNum;
	}

	/**
	 * This method is added to check /uncheck Content checkbox in Test Setup
	 * 
	 * @param check
	 *            input value will be either "true(check)" or "false(uncheck)"
	 */

	public void checkUncheckCheckboxContent(boolean check) {
		boolean checkBoxSelected = checkboxContent.isSelected();

		if (checkBoxSelected) {
			if (!(check))
				checkboxContent.click();
		} else {
			if (check)
				checkboxContent.click();
		}
	}

	/**
	 * This method is added to check /uncheck AutoSendWinningMessage checkbox in
	 * Test Setup
	 * 
	 * @param check
	 *            Will take two value as input "true(check)" or "false(uncheck)"
	 */

	public void checkboxAutoSendWinningMessage(boolean check) {
		boolean checkboxSelected = checkboxAutoSendWinningMessage.isSelected();

		if (checkboxSelected) {
			if (!(check))
				checkboxAutoSendWinningMessage.click();
		} else {
			if ((check))
				checkboxAutoSendWinningMessage.click();
		}

	}

	/***
	 * This method is added return the number of masters created after
	 * completion test generation
	 * 
	 * @return the number of masters created
	 */

	public int createdMastersCount(int multivariateCount) {
		long startTime = System.currentTimeMillis() / 1000;
		long stopTime = startTime + 300;
		List<WebElement> masterIdTable;
		int tableSize = 0;

		if (multivariateCount > 2) {
			masterIdTable = mastersIdCreatedSubContent;
		} else {
			masterIdTable = mastersIdCreatedSubOrContent;
		}

		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(masterIdTable),
				driver, 20);
		while (System.currentTimeMillis() / 1000 <= stopTime) {
			if (masterIdTable.size() > 1) {
				for (WebElement masterid : masterIdTable) {
					Reporter.log("Generated Master IDs: " + masterid.getText()
							+ "<br>");
				}
				tableSize = masterIdTable.size();
				break;
			}
			driver.navigate().refresh();
			DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(masterIdTable), driver, 20);
		}
		if (tableSize > 1) {
			return tableSize;
		} else
			return 0;
	}

}
