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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TestContentPage extends MvtBase {
	

	private WebDriver driver;

	/***
	 * Element locator section
	 * */

	@FindBy(id = "addContent")
	private WebElement addContentButton;

	@FindBy(css = ".controls input[name='name']")
	private WebElement contentNameTextBox;

	@FindBy(css = ".controls input[name='description']")
	private WebElement contentDescriptionTextBox;

	@FindBy(id = "confirmAdd")
	private WebElement addConfirmButton;

	@FindBy(xpath = "//div[@aria-hidden='false']/*/button[text()='Upload']")
	private WebElement uploadAssetsButton;

	@FindBy(css = "button[id='contentUpload']")
	private WebElement uploadButton;

	@FindBy(id = "fileupload")
	private WebElement uploadformTextField;

	@FindBys({ @FindBy(css = "#testCasePanel ul>li") })
	private List<WebElement> selectContentDropDownList;

	@FindBy(css = "#testCasePanel ul")
	private WebElement selectContentDropDown;

	@FindBy(css = "#testCasePanel span#currentContent")
	private WebElement selectedCurrentContent;

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
		if (null == DriverUtility.waitFor(elementToBeClickable(By
				.cssSelector("button[id='contentUpload']")), driver, 50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
	}

	/***
	 * This Method is to add content in the Test content page
	 * 
	 * @param count
	 *            ` - numbder of content to add
	 * @param uploadFileName
	 *            :Name of the file like :Lexus.zip etc
	 * @return countStore - number of content added
	 */
	public int addContent(int count, String uploadFileName) {
		int countStore = count;

		if (count != 0) {
			addContentButton.click();
			contentNameTextBox.sendKeys(Utils.getUniqueName(
					PagesHelper.MULTIVARIATE_CONTENT_NAME, 25));
			contentDescriptionTextBox.sendKeys(Utils.getUniqueName(
					PagesHelper.MULTIVARIATE_CONTENT_DESC, 25));
			addConfirmButton.click();
			DriverUtility.waitforElementDisplay(driver, addContentButton, 30);
			uploadFile(uploadFileName);
			count--;
			if (count >= 1)
				addContent(count, uploadFileName);
		}
		return countStore;
	}

	/***
	 * This Method is added to upload content file in the Test content page
	 * 
	 * @param uploadFileName
	 *            :Name of the file like :Lexus.zip etc
	 */
	public void uploadFile(String uploadFileName) {

		uploadButton.click();
		String uploadFilePath = Utils.getResources(this, uploadFileName);
		Reporter.log("Uploaded file path is:"+uploadFilePath);
		uploadformTextField.sendKeys(uploadFilePath);
		// uploadformTextField
		// .sendKeys("D:\\NEWUIAutomation\\kapil test\\target\\test-classes\\Test 12 March 2013.zip");
		uploadAssetsButton.click();
		stepCompleted();

	}

}
