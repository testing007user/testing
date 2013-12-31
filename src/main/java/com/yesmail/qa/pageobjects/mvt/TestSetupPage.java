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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.yesmail.qa.pageobjects.PagesHelper;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.DriverUtility.CHECK_UNCHECK;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;

public class TestSetupPage extends MvtBase {

	// Page Elements for Test Set up class

	@FindBy(css = "button[class='ym-btn ym-btn-secondary create-test']")
	private WebElement createNewTestBtn;

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

	@FindBy(css = ".input-large")
	private WebElement successCriterionForAutoSend;

	@FindBy(id = "saveTest")
	private WebElement saveTestButton;

	@FindBys({ @FindBy(css = ".dataTable>tbody>tr") })
	private List<WebElement> mastersCreated;

	@FindBy(css = ".modal-footer button:nth-of-type(2)")
	private WebElement createTestBtn;

	private WebDriver driver;
	private String pageUrl;
	

	// Constructor

	public TestSetupPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}
	
	
	public TestSetupPage load()
	{
		Reporter.log("Navigating to -->Test Setup Page");
		driver.navigate().to(PagesHelper.URL+pageUrl);
		return this;
	}
	
	public void isLoaded()
	{
		if(null == DriverUtility.waitFor(ExpectedConditions.elementToBeClickable(testName), driver, 50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
		
	}

	/***
	 * This method is added to fill the Name on Test Setup page
	 * 
	 * @param testString
	 */

	private void fillName(String testString) {
		testName.clear();
		testName.sendKeys(testString);
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
	public String fillSetUpPage(CHECK_UNCHECK checkboxFlag,
			boolean contentCheckBox, boolean autoSendcheck) {
		fillName(Utils.getUniqueName(PagesHelper.MULTIVARIATE_SETUP_NAME,20));
		fillDescription(Utils
				.getUniqueName(PagesHelper.MULTIVARIATE_SETUP_DESC,20));
		DriverUtility.checkUncheckCheckBox(checkboxSubject, checkboxFlag);
		checkUncheckCheckboxContent(contentCheckBox);
		checkboxAutoSendWinningMessage(autoSendcheck);
		selectCriterionAutoSend(PagesHelper.MULTIVARIATE_SETUP_AUTOSEND_CRITERIA);
		String jobId = saveTest();
		return jobId;
	}

	/***
	 * This method is added to save the Test on the Test Setup page
	 */
	public String saveTest() {
		saveTestButton.click();
		return getMasterId();

	}

	/***
	 * This method will return the job number
	 * 
	 */

	public String getMasterId() {
		DriverUtility.waitFor(ExpectedConditionExtended.elementToBeDisabled(checkboxSubject), this.driver, 40);  
		String loginUrl = driver.getCurrentUrl();
		  //System.out.println(loginUrl);
		  String jobNum = driver.getCurrentUrl().substring(
		  loginUrl.lastIndexOf("#") + 1).replaceAll("[^0-9]", "");
		  
		  Reporter.log("Job Number is:"+jobNum,true);
		  //System.out.println(jobNum);
		  return jobNum;
		 }

	/**
	 * This method is added to check /uncheck subject checkbox in Test Setup
	 * 
	 * @param checkUncheck
	 *            input value will be either "check" or "uncheck"
	 */

	/*public void checkUncheckCheckboxSubject(boolean check)

	{
		boolean checkBoxSelected = checkboxSubject.isSelected();

		if (checkBoxSelected) {
			if (!(check))
				checkboxSubject.click();
		} else {
			if (check)
				checkboxSubject.click();
		}
	}*/

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

	/**
	 * This Method select the visible text of the select criterion check box if
	 * the option is not present then will select option 1 by default
	 * 
	 * @param option
	 *            :Visible text of the Success Criterion for Auto Send drop down
	 */
	
	private void selectCriterionAutoSend(String option) {
		DriverUtility.selectDropDown(successCriterionForAutoSend, option, 1);
	}

	/***
	 * This method is added return the number of masters created after
	 * completion test generation
	 * 
	 * @return the number of masters created
	 */

	public int createdMastersCount() {
		return (mastersCreated.size());
	}

}
