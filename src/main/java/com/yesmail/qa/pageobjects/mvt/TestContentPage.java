/**File name : TestContentPage.java
 * Description: This Java Class is 
 * used to create content for new test. 
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * completed by 12/2/13
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
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class TestContentPage extends MvtBase {

	private WebDriver driver;

	/***
	 * Element locator section
	 * */

	@FindBy(id = "addContent")
	private WebElement addContentButton;

	@FindBy(css = "div.modal.fade.in input[name=name]")
	private WebElement contentNameTextBox;

	@FindBy(css = "div.modal.fade.in input[name=description]")
	private WebElement contentDescriptionTextBox;

	@FindBy(css = "div.modal.fade.in button[id=confirmAdd]")
	private WebElement addConfirmButton;

	@FindBy(xpath = "//div[@aria-hidden='false']/*/button[text()='Upload']")
	private WebElement uploadAssetsButton;

	@FindBy(css = "button[id='contentUpload']")
	private WebElement uploadButton;

	@FindBy(css = "html>body")
	private WebElement htmlContent;

	@FindBy(id = "file-upload")
	private WebElement uploadformTextField;

	@FindBys({ @FindBy(css = "div.open> .dropdown-menu.input-xlarge li") })
	private List<WebElement> selectContentDropDownList;

	@FindBy(css = "span[data-target='contents'] span.caret")
	private WebElement downArrow;
	
	@FindBy(id = "dummyElement")
	private WebElement dummyElement;

	/**
	 * Constructor section
	 * 
	 * @author sangeetap
	 */
	public TestContentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public TestContentPage load() {
		navigateToContentTab();
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(uploadButton),
				driver, 50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
	}

	/***
	 * This Method is to add content in the Test content page
	 * 
	 * @param count
	 *            ` - number of content to add
	 * @param uploadFileName
	 *            :Name of the file like :Lexus.zip etc
	 * @return countStore - number of content added
	 */
	public void addContent(int count) {		
		while(count != 0)
		{
			DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(addContentButton), driver, 30);
			addContentButton.click();
			DriverUtility.waitFor(ExpectedConditionExtended
					.elementsToBeClickable(contentNameTextBox), driver, 30);
			String strContentName = Utils.getUniqueName(
					PagesHelper.MULTIVARIATE_CONTENT_NAME, 25);
			contentNameTextBox.sendKeys(strContentName);
			contentDescriptionTextBox.sendKeys(Utils.getUniqueName(
					PagesHelper.MULTIVARIATE_CONTENT_DESC, 25));
			addConfirmButton.click();
			Reporter.log("Ribbon Text for Test Content <br>"
					+ getRibbonText(20), true);
			DriverUtility.waitFor(
					ExpectedConditionExtended.elementToBeClickable(dummyElement),
					driver, 10);
			DriverUtility.waitFor(
					ExpectedConditionExtended.elementToBeClickable(downArrow),
					driver, 30);
			selectContentFromDropDown(strContentName);
			uploadFile();
			DriverUtility.waitFor(
					ExpectedConditionExtended.elementToBeClickable(dummyElement),
					driver, 10);
			count--;
		}		
	}

	/***
	 * This method is added to select the content from select content drop down
	 * 
	 * @param stringToSelect
	 */
	public void selectContentFromDropDown(String stringToSelect) {
		downArrow.click();
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(selectContentDropDownList), driver, 30);
		for (int index = 0; index < selectContentDropDownList.size(); index++) {
			if (selectContentDropDownList.get(index).getText()
					.equalsIgnoreCase(stringToSelect)) {
				selectContentDropDownList.get(index).click();
				break;

			}
		}
		Reporter.log(" Ribbon text for Upload Content : <br>"
				+ getRibbonText(20), true);
	}

	/***
	 * This Method is added to upload content file in the Test content page
	 * 
	 * @param uploadFileName
	 *            :Name of the file like :Lexus.zip etc
	 */
	public void uploadFile() {

		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(uploadButton),
				driver, 50);
		uploadButton.click();		
		Reporter.log("Uploading File :<br>", true);
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(uploadformTextField),
				driver, 30);
		uploadformTextField.sendKeys( Utils.getResources(this, "Test 12 March 2013.zip"));	
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(uploadAssetsButton), driver, 50);
		uploadAssetsButton.click();
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(htmlContent),
				driver, 50);
		
	}

}
