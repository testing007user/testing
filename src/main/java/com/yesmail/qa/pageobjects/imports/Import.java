package com.yesmail.qa.pageobjects.imports;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class Import {

	// ** Variable declaration section

	private String subscriberEmail;
	private static final WebDriver driver = null;

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
	

	/*@FindBy(css = "a#modalAlertPopUpYesAction")
	private WebElement modalAlertPopUpYesBtn;*/

	// list of WebElement that represent each option in the File Column
	@FindBys(value = { @FindBy(css = "select#headerList") })
	private static List<WebElement> optionFileColumns;

	// list of WebElement that represent each row in the Subscriber
	// Attributes[data type]
	@FindBys(value = { @FindBy(css = "div#dbSelectList.selectList") })
	private static List<WebElement> optionSubscriberAttributesDataType;

	
	
	
	
	/***
	 * 
	 * @param delimiter
	 *            : to define the delimiter as Comma/Tab/Space/Semicolon/Pipe/No
	 *            Delimiter
	 */

	/*public void importScrubscribers(WebElement delimiter) {

		String[] expected = { "eMail[Email]", "firstName[Name]",
				"lastName[Name]" };

		boolean yesHeader = true;// // Need to get this value from PagesHelper
									// for header true or false

		openImportUtilityBtn.click();

		java.util.Set<String> winId = driver.getWindowHandles();
		Iterator<String> it = winId.iterator();

		String winParentId = it.next();
		String winChildId = it.next();

		driver.switchTo().window(winChildId);
		{

			// Select DataFile
			uploadModifyMySubscribers.click();
			selectContinueActionBtn.click();
			browserYourDesktopBtn.click();

			// String winParentId1= it.next();
			String winChildId1 = it.next();

			driver.switchTo().window(winChildId1);
			{
				chooseFileBtn.click();
				chooseFileBtn
						.sendKeys("C:\\Users\\sangeetap\\Desktop\\import.txt");
				uploadFileBtn.click();
				driver.close();
			}
			driver.switchTo().window(winChildId);
			dataFileContinueBtn.click();

			 Define delimiter 

			if (yesHeader) {
				yesRadioBtn.click();
				delimiter.click();
				delimiterContinueActionbtn.click();
			} else {
				noRadioBtn.click();
				delimiterContinueActionbtn.click();
			}
			// Select Map

			editMapBtn.click();
			String winChildId2 = it.next();
			driver.switchTo().window(winChildId2);
			{
				optionFileColumns.size();
				optionSubscriberAttributesDataType.size();
				for (WebElement option : optionFileColumns) {
					System.out.println(String.format("Value is: %s",
							option.getAttribute("value")));
					option.click();
					{
						for (WebElement option1 : optionSubscriberAttributesDataType) {

							if (option.getText().equals(option1.getText())) {
								option1.click();
							}
						}
						// System.out.println(String.format("Value is : %s",option1.getAttribute("value")));
						associtatMapBtn.click();
					}
					createMapContinueBtn.click();
					driver.close();
					driver.switchTo().window(winChildId);
					mapContinueBtn.click();
				}
				//
				importCustomersBtn.click();
				selectDivisionDropDown.click();
				submitJobForProcessingBtn.click();
				Alert alert = driver.switchTo().alert();
				alert.accept();

				driver.switchTo().window(winParentId);
				driver.close();
				

			}

		}

	}
	
	*/
	
	public void selectDataFileYesmailFTP() {
	}
	
	public void importScrubscribers() {
		WebElement element = null;
		
		String winhandParentId = selectDataFileDesktop();
		String winhandle1=defindDelimiter(winhandParentId,element );
		String winhandle2=createNewMap(winhandle1);
		String winhandle3=chooseImportType(winhandle2);
		driver.close();
		driver.switchTo().window(winhandParentId);
		
	}
	
	
	
	
	
	private String chooseImportType(String winhandle2) {
		// TODO Auto-generated method stub
		driver.switchTo().window(winhandle2);
		importCustomersBtn.click();
		selectDivisionDropDown.click();
		submitJobForProcessingBtn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return winhandle2;
	}

	/***
	 * This method is added to select Data file from user desktop
	 * 
	 * @author sangeetap
	 * @return
	 * @Since completed by 26 Nov 13
	 */
	public String selectDataFileDesktop(/*Window winhandle*/) {
		openImportUtilityBtn.click();
		java.util.Set<String> winId = driver.getWindowHandles();
		Iterator<String> it = winId.iterator();
		String winParentId = it.next();
		String winChildId = it.next();
		driver.switchTo().window(winChildId);
		{
			// Select DataFile
			uploadModifyMySubscribers.click();
			selectContinueActionBtn.click();
			browserYourDesktopBtn.click();
			// String winParentId1= it.next();
			String winChildId1 = it.next();
			driver.switchTo().window(winChildId1);
			{
				chooseFileBtn.click();
				chooseFileBtn
						.sendKeys("C:\\Users\\sangeetap\\Desktop\\import.txt");
				uploadFileBtn.click();
				driver.close();
			}
			driver.switchTo().window(winChildId);
			dataFileContinueBtn.click();
			return winParentId;
		}
	}
	
	public String defindDelimiter(String winhandle,WebElement delimiter)
	{
		boolean yesHeader = true;// // Need to get this value from PagesHelper
		// for header true or false
		driver.switchTo().window(winhandle);
		if (yesHeader) {
			yesRadioBtn.click();
			delimiter.click();
			delimiterContinueActionbtn.click();
		} else {
			noRadioBtn.click();
			delimiterContinueActionbtn.click();
			
			}
		driver.close();
		return winhandle;
	}


	public void selectExistingMap()
	{
		
	}
	
	public String createNewMap(String winhandle1)
	{

	driver.switchTo().window(winhandle1);
		editMapBtn.click();
		
		Iterator<String> it = null;
		String winChildId2 = it.next();
		driver.switchTo().window(winChildId2);
		{
			optionFileColumns.size();
			optionSubscriberAttributesDataType.size();
			for (WebElement option : optionFileColumns) {
				System.out.println(String.format("Value is: %s",
						option.getAttribute("value")));
				option.click();
				{
					for (WebElement option1 : optionSubscriberAttributesDataType) {

						if (option.getText().equals(option1.getText())) {
							option1.click();
						}
					}
					// System.out.println(String.format("Value is : %s",option1.getAttribute("value")));
					associtatMapBtn.click();
				}
				createMapContinueBtn.click();
				driver.close();
				driver.switchTo().window(winhandle1);
				mapContinueBtn.click();
				
				
			}
	}
		return winhandle1;
}
	
}
