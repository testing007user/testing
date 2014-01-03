/**File name : Import.java
 * @Description: This Java Class is used  import Subscribers 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @since completed by 12/17/13
 * @Version History
 * Version name Updated By Reason / Comments
 *  
 * */

package com.yesmail.qa.pageobjects.imports;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.test.configuration.XMLParser;

public class Import extends BasePage {

	// ** Variable declaration section
	private WebDriver driver;

	/** Element locator section */

	@FindBy(css = "button[class='ym-btn ym-btn-secondary']")
	private WebElement openImportUtilityBtn;

	// upload new file Choose button locator
	@FindBy(css = "input#uploadFilesAction")
	private WebElement chooseFileBtn;

	@FindBy(css = "#modalUploadFileAction a")
	private WebElement uploadFileBtn;

	@FindBy(css = "a#modalUploadFileCancelAction")
	private WebElement CancelFileBtn;

	@FindBy(css = "a#dataFileContinueAction")
	private WebElement dataFileContinueBtn;

	// import options WebElement

	@FindBy(css = "input#subScribers")
	private WebElement uploadModifyMySubscribers;

	@FindBy(css = "a#selectContinueAction")
	private WebElement selectContinueActionBtn;

	@FindBy(css = "a#uploadNewFileAction")
	private WebElement browserYourDesktopBtn;

	@FindBy(css = "#previewTable >table")
	private WebElement importPreviewTable;

	// Delimiter header WebElement locators

	@FindBy(css = "input#headerY")
	private WebElement yesRadioBtn;

	@FindBy(css = "input#headerN")
	private WebElement noRadioBtn;

	// Delimiter WebElement locators

	@FindBy(css = "input#delimiterComma")
	private WebElement delimiterCommaRadioBtn;

	@FindBy(css = "input#delimiterTab")
	private WebElement delimiterTabRadioBtn;

	@FindBy(css = "input#delimiterSpace")
	private WebElement delimiterSpaceRadioBtn;

	@FindBy(css = "input#delimiterSemicolon")
	private WebElement delimiterSemicolonRadiBtn;

	@FindBy(css = "input#delimiterPipe")
	private WebElement delimiterPipeRadioBtn;

	@FindBy(css = "input#noDelimiter")
	private WebElement noDelimiterRadioBtn;

	@FindBy(css = "a#delimiterContinueAction")
	private WebElement delimiterContinueActionbtn;

	// select Map WebElement

	@FindBy(css = "a#selectMapAction")
	WebElement selectExistingMapBtn;

	@FindBy(css = "a#createNewMapAction")
	WebElement editMapBtn;

	// File Preview / Exclude

	@FindBy(css = "a.linkdisplayed")
	private WebElement previewFileLink;

	@FindBy(css = "div.previewExclude a")
	private WebElement excludeFileLink;

	// Create new Map

	@FindBy(css = "a#associateMapAction")
	private WebElement associtatMapBtn;

	@FindBy(css = "a#modalCreateMapSaveAsTemplateAction")
	private WebElement saveAsTemplate;

	@FindBy(css = "a#modalCreateMapContinueAction")
	private WebElement createMapContinueBtn;

	@FindBy(css = "a#modalCreateMapCancelAction")
	private WebElement createMapCancelBtn;

	@FindBy(css = "a#modalCreateMapClearAction")
	private WebElement createMapClearBtn;

	@FindBy(css = "a#mapContinueAction")
	private WebElement mapContinueBtn;

	// Import Type WebElement locators
	@FindBy(css = "a#tab_import")
	private WebElement importCustomersBtn;

	// Import Type customer options

	@FindBy(css = "select#importDivision")
	private WebElement selectDivisionDropDown;

	@FindBy(css = "a#importSubmit")
	private WebElement submitJobForProcessingBtn;

	@FindBy(css = "a#modalAlertPopUpYesAction")
	private WebElement modalAlertPopUpYesBtn;

	@FindBy(css = "#jobid")
	private WebElement JobID;

	// list of WebElement that represent each option in the File Column
	@FindBys(value = { @FindBy(css = "select#headerList >option") })
	private static List<WebElement> optionFileColumns;

	// list of WebElement that represent each row in the Subscriber
	// Attributes[data type]
	@FindBys(value = { @FindBy(css = "#fieldList>option") })
	private static List<WebElement> optionSubscriberAttributesDataType;

	/**
	 * Constructor section
	 * 
	 * @return
	 */

	public Import(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Import load() {
		openImportUtilityBtn.click();
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(By
						.cssSelector("input#uploadFilesAction")), driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
	}

	/***
	 * This method is added to import Subscribers
	 * 
	 * @param delimiter
	 *            : to define the delimiter as Comma/Tab/Space/Semicolon/Pipe/No
	 * @return JobId of imported subscriber
	 */
	public String importScrubscribers(boolean headerFlag, DELIMITER delimiter) {
		String jobID = null;
		String parentWindowId = driver.getWindowHandle();
		selectDataFileDesktop();
		selectHeader(headerFlag);
		definedDelimiter(delimiter);
		delimiterContinueActionbtn.click();
		createNewMap();
		chooseImportType();
		DriverUtility.waitforElementDisplay(driver, JobID, 5);
		jobID = JobID.getText();
		driver.close();
		driver.switchTo().window(parentWindowId);
		return jobID;
	}

	/***
	 * This method is added to choose Import Type
	 * 
	 * @author sangeetap
	 */
	private void chooseImportType() {
		DriverUtility.waitforElementDisplay(driver, importCustomersBtn, 20);
		importCustomersBtn.click();
		DriverUtility.selectDropDown(selectDivisionDropDown, "marketing", 1);
		DriverUtility.waitforElementDisplay(driver, submitJobForProcessingBtn,
				10);
		submitJobForProcessingBtn.click();
		DriverUtility.waitforElementDisplay(driver, modalAlertPopUpYesBtn, 10);
		modalAlertPopUpYesBtn.click();
	}

	/***
	 * This method is added to select Data file from user desktop
	 * 
	 * @author sangeetap
	 * @return
	 */
	private void selectDataFileDesktop() {
		openImportUtilityBtn.click();
		String childWindowTitle = "Yesmail Enterprise: Import Data";
		DriverUtility.switchToWindow(driver, childWindowTitle);

		// Select DataFile
		uploadModifyMySubscribers.click();
		selectContinueActionBtn.click();
		browserYourDesktopBtn.click();
		DriverUtility.waitforElementDisplay(driver, chooseFileBtn, 20);
		chooseFileBtn.click();
		chooseFileBtn.sendKeys(Utils.getResources(this, XMLParser
				.readComponentValueFromXML("Imports.txtImportFileName")));
		uploadFileBtn.click();
		DriverUtility.waitforElementDisplay(driver, importPreviewTable, 60);
		DriverUtility.waitforElementDisplay(driver, dataFileContinueBtn, 10);
		dataFileContinueBtn.click();
	}

	public enum DELIMITER {
		COMMA, TAB, SPACE, SEMICOLON, PIPE, NO_DELIMITER
	}

	/***
	 * This method is added to select delimiter
	 * 
	 * @param- delimiter(Comma, Tab, Space, Semicolon, Pipe)
	 * @author sangeetap
	 * @return
	 */
	private void definedDelimiter(DELIMITER selectDelimiter) {

		switch (selectDelimiter) {
		case COMMA:
			delimiterCommaRadioBtn.click();
			break;
		case TAB:
			delimiterTabRadioBtn.click();
			break;
		case SPACE:
			delimiterSpaceRadioBtn.click();
			break;
		case SEMICOLON:
			delimiterSemicolonRadiBtn.click();
			break;
		case PIPE:
			delimiterPipeRadioBtn.click();
			break;
		default:
			noDelimiterRadioBtn.click();
			break;
		}

	}

	/***
	 * This method is added to select header
	 * 
	 * @param selectHeaderFlag
	 *            - True / False select header / don't select header
	 */

	private void selectHeader(boolean selectHeaderFlag) {
		if (selectHeaderFlag)
			yesRadioBtn.click();
		else
			noRadioBtn.click();
	}

	/***
	 * This method is added to create New Map
	 * 
	 * @author sangeetap
	 * @return
	 */
	private void createNewMap() {
		editMapBtn.click();
		DriverUtility.waitforElementDisplay(driver, associtatMapBtn, 20);
		for (WebElement option : optionFileColumns) {
			option.click();
			for (WebElement option1 : optionSubscriberAttributesDataType) {
				if (option.getText().equalsIgnoreCase(option1.getText().split("\\[")[0].trim())) {
					option1.click();
					associtatMapBtn.click();
					break;
				}
			}

		}
		createMapContinueBtn.click();
		mapContinueBtn.click();
	}

}
