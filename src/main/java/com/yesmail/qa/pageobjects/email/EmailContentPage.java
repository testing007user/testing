package com.yesmail.qa.pageobjects.email;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

public class EmailContentPage extends EmailBase {

	private WebDriver driver;
	private String pageUrl;

	@FindBy(id = "viewHtmlContent")
	private WebElement hTMLtab;

	@FindBy(id = "viewTextContent")
	private WebElement texttab;

	@FindBy(id = "sourceEditor")
	private WebElement sourceButton;

	@FindBy(id = "contentUpload")
	private WebElement uploadButton;

	@FindBy(id = "fileupload")
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

	public EmailContentPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
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
		if (null == DriverUtility.waitFor(elementToBeClickable(emailContent),
				driver, 50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
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

			DriverUtility.waitforElementDisplay(driver, textSourceBox, 10);

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
		uploadformTextField
				//.sendKeys("D:\\sangeeta_files\\Test 12 March 2013.zip");
		.sendKeys("C:\\Users\\sangeetap\\Desktop\\Test 12 March 2013.zip");

		DriverUtility.waitforElementDisplay(driver, htmlContent, 10);
		return stepCompleted();

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

}
