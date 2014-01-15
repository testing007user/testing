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
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class Import extends BasePage {

	private WebDriver driver;
	private String pageUrl;

	@FindBy(css = "button[class='ym-btn ym-btn-secondary']")
	private WebElement openImportUtilityBtn;

	@FindBy(css = "input#uploadFilesAction")
	private WebElement chooseFileBtn;

	@FindBy(css = "#modalUploadFileAction a")
	private WebElement uploadFileBtn;

	@FindBy(css = "a#dataFileContinueAction")
	private WebElement dataFileContinueBtn;

	@FindBy(css = "input#subScribers")
	private WebElement uploadModifyMySubscribers;

	@FindBy(css = "a#selectContinueAction")
	private WebElement selectContinueActionBtn;

	@FindBy(css = "a#uploadNewFileAction")
	private WebElement browserYourDesktopBtn;

	@FindBy(css = "#previewTable >table")
	private WebElement importPreviewTable;

	@FindBy(css = "input#headerY")
	private WebElement yesRadioBtn;

	@FindBy(css = "input#headerN")
	private WebElement noRadioBtn;

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

	@FindBy(css = "a#createNewMapAction")
	private WebElement editMapBtn;

	@FindBy(css = "a#associateMapAction")
	private WebElement associtatMapBtn;

	@FindBy(css = "a#modalCreateMapContinueAction")
	private WebElement createMapContinueBtn;

	@FindBy(css = "a#mapContinueAction")
	private WebElement mapContinueBtn;

	@FindBy(css = "a#tab_import")
	private WebElement importCustomersBtn;

	@FindBy(css = "select#importDivision")
	private WebElement selectDivisionDropDown;

	@FindBy(css = "a#importSubmit")
	private WebElement submitJobForProcessingBtn;

	@FindBy(css = "a#modalAlertPopUpYesAction")
	private WebElement modalAlertPopUpYesBtn;

	@FindBy(css = "#jobid")
	private WebElement JobID;

	@FindBys(value = { @FindBy(css = "select#headerList >option") })
	private static List<WebElement> optionFileColumns;

	@FindBys(value = { @FindBy(css = "#fieldList>option") })
	private static List<WebElement> optionSubscriberAttributesDataType;

	@FindBy(css = ".dataTable tbody")
	private WebElement tableBody;

	// Constructor section
	public Import(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}

	public Import load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(tableBody),
				driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
	}

	/***
	 * This method is added to import Subscribers
	 * 
	 * @param delimiterHeaderFlag
	 *            : whether to include Header for delimiter(True/False)
	 * @param delimiter
	 *            : to define the delimiter as Comma/Tab/Space/Semicolon/Pipe/No
	 * @return JobId of imported subscriber
	 */
	public String importScrubscribers(boolean delimiterHeaderFlag,
			DELIMITER delimiter) {
		String jobID = null;
		String parentWindowId = driver.getWindowHandle();
		openImportUtilityBtn.click();
		DriverUtility.switchToWindow(driver,  PagesHelper.IMPORT_PAGE_TITLE);
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(uploadModifyMySubscribers), driver, 30);
		uploadModifyMySubscribers.click();
		selectContinueActionBtn.click();
		uploadDataFile();
		selectHeader(delimiterHeaderFlag);
		definedDelimiter(delimiter);
		delimiterContinueActionbtn.click();
		createNewMap();
		chooseImportType();
		DriverUtility.waitforElementDisplay(driver, JobID, 10);
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
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(importCustomersBtn), driver, 20);
		importCustomersBtn.click();
		DriverUtility.selectDropDown(selectDivisionDropDown, "marketing", 1);
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(submitJobForProcessingBtn), driver, 20);
		submitJobForProcessingBtn.click();
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(modalAlertPopUpYesBtn), driver, 20);
		modalAlertPopUpYesBtn.click();
	}

	/***
	 * This method is added to select Data file from user desktop
	 * 
	 * @author sangeetap
	 * @return
	 */
	private void uploadDataFile() {
		Reporter.log("Uploading Data File<br>");
		browserYourDesktopBtn.click();
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(chooseFileBtn),
				driver, 20);		
		chooseFileBtn.sendKeys(Utils.getResources(this,"import.txt"));
		uploadFileBtn.click();
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(importPreviewTable), driver, 30);
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(dataFileContinueBtn), driver, 20);
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
		Reporter.log("Selecting Delimiter<br>");
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
	 * @param selectDelimiterHeader
	 *            - True / False select header / don't select header
	 */
	private void selectHeader(boolean selectDelimiterHeader) {
		Reporter.log("Select Delimiter Header<br>");
		if (selectDelimiterHeader)
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
		Reporter.log("Creating New Map<br>");
		editMapBtn.click();
		DriverUtility.waitforElementDisplay(driver, associtatMapBtn, 20);
		for (WebElement option : optionFileColumns) {
			option.click();
			for (WebElement option1 : optionSubscriberAttributesDataType) {
				if (option.getText().equalsIgnoreCase(
						option1.getText().split("\\[")[0].trim())) {
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
