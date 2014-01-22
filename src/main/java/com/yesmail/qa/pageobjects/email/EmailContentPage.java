/***
 * File : EmailContentPage.java
 * Description : Page object class for EmailContentPage functionality 
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

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

/***
 * 
 * @author kapilag
 * 
 */
public class EmailContentPage extends EmailBase {

	private WebDriver driver;

	@FindBy(id = "viewHtmlContent")
	private WebElement hTMLtab;

	@FindBy(id = "viewTextContent")
	private WebElement texttab;

	@FindBy(id = "sourceEditor")
	private WebElement sourceButton;

	@FindBy(id = "contentUpload")
	private WebElement uploadButton;

	@FindBy(id = "file-upload")
	private WebElement uploadformTextField;

	@FindBy(xpath = "//div[@class='modal fade in']//div[3]/button[2]")
	private WebElement uploadAssetsButton;

	@FindBy(css = "button[id = 'contentEditor']")
	private WebElement WYSIWYG_Editor;

	@FindBy(css = "#messageContent .ace_text-input")
	private WebElement hTMLSourceTextBox;

	@FindBy(css = "button.saveContent")
	private WebElement hTMLSaveButton;

	@FindBy(css = "button.saveContent")
	private WebElement textSaveButton;

	@FindBy(css = "#messageContent .ace_text-input")
	private WebElement textSourceBox;

	@FindBy(css = ".emailContent")
	private WebElement emailContent;

	@FindBy(css = "html>body")
	private WebElement htmlContent;

	@FindBy(id = "emailPreviewModal")
	private WebElement emailPreviewBtn;

	@FindBy(css = "input[data-id='emailAddressInput']")
	private WebElement emailAddressTextBox;

	@FindBy(css = "input[value='singleAddress']")
	private WebElement singleAddressRadioBtn;

	@FindBy(css = "input[value='emailList']")
	private WebElement emailListRadioBtn;

	@FindBy(css = "input[data-id='userIdInput']")
	private WebElement userIdTextBox;

	@FindBy(css = "button[data-id='sendPreview']")
	private WebElement sendPreviewBtn;

	@FindBy(css = ".emailList>select")
	private WebElement emailListDropdown;

	public EmailContentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	/***
	 * This method is added to load the page.
	 */
	public EmailContentPage load() {
		navigateToContentTab();
		return this;
	}

	/***
	 * This method is added to verify page load.
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(emailContent),
				driver, 50)) {
			driver.navigate().refresh();
			if (null == DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(emailContent), driver, 50))
				throw new FrameworkException(this.getClass().getName()
						+ " is not loaded in 50 seconds");
		}

	}

	/***
	 * This method is added to Create Content.
	 * 
	 * @param - Content Type(html/text)
	 * @param - wysiwygEditor (True/False)
	 */
	public void createContent(String contentType, boolean wysiwygEditor) {
		// driver not focusing on content Editor screen.
		if (contentType.equalsIgnoreCase("html")) {
			hTMLtab.click();

			if (wysiwygEditor) {
				WYSIWYG_Editor.click();
			} else {
				sourceButton.click();
			}

			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver)
						.executeScript("document.getElementById('messageContent').setAttribute('class',' ace_editor ace-chrome ace_focus')");
			}

			hTMLSourceTextBox
					.sendKeys("<html><body> sample test</body></html>");
			hTMLSaveButton.click();

		} else if (contentType.equalsIgnoreCase("text")) {
			texttab.click();

			sourceButton.click();

			DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(textSourceBox), driver, 10);

			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver)
						.executeScript("document.getElementById('messageContent').setAttribute('class',' ace_editor ace-chrome ace_focus')");
			}
			textSourceBox.sendKeys("Sample Text");

			textSaveButton.click();
		}

	}

	/***
	 * This method is added to Upload File for content.
	 * 
	 * @return - step Completed (True/False)
	 */
	public boolean uploadFile() {

		uploadButton.click();
		uploadformTextField.sendKeys(Utils.getResources(this,
				"Test 12 March 2013.zip"));
		uploadAssetsButton.click();
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(htmlContent),
				driver, 30);
		Reporter.log("Ribbon Text for ContentPage is: " + getRibbonText(10)
				+ "<br>");
		return stepCompleted(2, 10);

	}

	/***
	 * This method is added to get the Master ID.
	 * 
	 * @return - Master ID
	 */
	public String getMasterId() {
		String loginUrl = driver.getCurrentUrl();
		String jobNum = driver.getCurrentUrl()
				.substring(loginUrl.lastIndexOf("#") + 1)
				.replaceAll("[^0-9]", "");
		return jobNum;
	}

	/***
	 * This method is added to select Single Email Preview.
	 * 
	 * @return - Single Preview Sent(True/False)
	 */
	public boolean selectSinglePreview() {
		String ribbonText = null;
		emailPreviewBtn.click();
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(singleAddressRadioBtn), driver, 30);
		singleAddressRadioBtn.click();
		emailAddressTextBox.sendKeys(PagesHelper.EMAIL_ATTR_VALUE);
		userIdTextBox.sendKeys(PagesHelper.EMAIL_USERID);
		sendPreviewBtn.click();
		ribbonText = getRibbonText(20);
		Reporter.log("Ribbon Text for Single Email Preview is: " + ribbonText,
				true);
		return (ribbonText != null);

	}

	/***
	 * This method is added to select Group Email Preview.
	 * 
	 * @return - Group Preview Sent(True/False)
	 */
	public boolean selectGroupPreview() {
		String ribbonText = null;
		emailPreviewBtn.click();
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(emailListRadioBtn), driver, 30);
		emailListRadioBtn.click();
		DriverUtility.selectDropDown(emailListDropdown,
				PagesHelper.EMAIL_GROUP_NAME, 0);
		sendPreviewBtn.click();
		ribbonText = getRibbonText(20);
		Reporter.log("Ribbon Text for Group Email Preview is: " + ribbonText,
				true);
		return (ribbonText != null);
	}

}
