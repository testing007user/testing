package com.yesmail.qa.pageobjects;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;

public class ContentLibraryPage extends BasePage {

	private WebDriver driver;
	private String pageUrl;

	@FindBy(css = ".icon.icon-chevron-down")
	private WebElement iconDropdown;

	@FindBy(css = ".create-content-block>a")
	private WebElement createNewContentBlockLink;

	@FindBy(css = "button.ym-btn.ym-btn-primary.alternative.updateAssets")
	private WebElement uploadAssetsBtn;

	@FindBy(css = "input.contentBlockName")
	private WebElement contentNameTxtBox;

	@FindBy(css = "button.saveContentBlock")
	private WebElement saveContentBlockBtn;

	@FindBy(id = "fileupload")
	private WebElement uploadformTextField;

	@FindBy(xpath = "//div[@class='modal fade in']//div[3]/button[2]")
	private WebElement uploadBtn;

	@FindBys({ @FindBy(css = "tbody > tr > td:nth-of-type(2)") })
	private List<WebElement> tdNameCollections;

	@FindBy(css = "table > tbody")
	private WebElement tbody;

	@FindBy(css = "button.confirm")
	private WebElement createBtn;

	@FindBy(css = ".modal.fade.in > .modal-footer > button.confirm")
	private WebElement popUpDeleteBtn;

	/**
	 * Constructor section
	 * 
	 */
	public ContentLibraryPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	/***
	 * This method is used to load the page.
	 * 
	 */
	public void load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
	}

	/***
	 * This method is added to verify whether page is loaded.
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(elementToBeClickable(tbody), driver,
				50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
	}

	public enum operations {
		Copy, Delete
	}

	/***
	 * This method is used to create content.
	 * 
	 * @param - Content Name
	 * @return - Content Successfully Created (True/False)
	 */
	public boolean createContentBlock(String contentName) {
		iconDropdown.click();
		createNewContentBlockLink.click();
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(contentNameTxtBox),
				driver, 10);
		contentNameTxtBox.sendKeys(contentName);
		createBtn.click();
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(contentNameTxtBox),
				driver, 10);
		uploadAssets();
		getRibbonText(10);
		saveContentBlockBtn.click();
		getRibbonText(10);
		return searchContent(contentName);

	}

	/***
	 * This method is used to search the required content.
	 * 
	 * @param - Content Name
	 * @return - Content Found (True/False)
	 */
	private boolean searchContent(String contentName) {
		boolean contentFound = false;
		load();
		isLoaded();
		for (WebElement name : tdNameCollections) {
			if (name.getText().equalsIgnoreCase(contentName)) {
				contentFound = true;
				break;
			}
		}
		return contentFound;
	}

	/***
	 * This method is perform Content Operations(Copy/Delete).
	 * 
	 * @param - Content Name, Operations
	 * @return - Operation successful (True/False)
	 */
	public boolean contentOperations(String contentName, operations operation) {
		int index = 0;
		boolean operationSatus = false;

		for (int i = 0; i < tdNameCollections.size(); i++) {
			if (tdNameCollections.get(i).getText()
					.equalsIgnoreCase(contentName)) {
				index = i;
				break;
			}
		}

		switch (operation) {
		case Copy:
			String newcontentName = Utils.getUniqueName("testContent");
			WebElement copyElement = driver.findElement(By
					.cssSelector("tbody > tr:nth-of-type(" + (index + 1)
							+ ") > td:nth-of-type(4) >a.copyContentBlock"));
			copyElement.click();
			DriverUtility.waitFor(
					ExpectedConditions.elementToBeClickable(contentNameTxtBox),
					driver, 10);
			contentNameTxtBox.sendKeys("newcontentName");
			createBtn.click();
			operationSatus = searchContent(newcontentName);
			break;

		case Delete:
			WebElement deleteElement = driver.findElement(By
					.cssSelector("tbody > tr:nth-of-type(" + (index + 1)
							+ ") > td:nth-of-type(4) >a.deleteContentBlock"));
			deleteElement.click();
			popUpDeleteBtn.click();
			operationSatus = !searchContent(contentName);
			break;

		}
		return operationSatus;

	}

	/***
	 * This method is added to Upload File for content.
	 * 
	 * @return - step Completed (True/False)
	 */
	public boolean uploadAssets() {
		uploadAssetsBtn.click();
		uploadformTextField
				.sendKeys("D:\\sangeeta_files\\Test 12 March 2013.zip");
		uploadBtn.click();
		return true;

	}

}
