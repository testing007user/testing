/**File name : ImportHomePage.java
 * @Description: This Java Class is used to search created jobId and find the respective 
 * status for the same by Type, Owned By, status or requested options 
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
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class ImportHomePage extends BasePage {

	/***
	 * Page Elements for ImportHomePage Class
	 */
	private WebDriver driver;
	private String pageUrl;

	@FindBy(css = "select[data-id='type']")
	private WebElement typeDropDown;

	// Owned by WebElement locator
	@FindBy(css = "select[data-id='owner']")
	private WebElement ownedByDropDown;

	// Status by WebElement locator
	@FindBy(css = "select[data-id='status']")
	private WebElement statusByDropDown;

	// Requested by WebElement locator
	@FindBy(css = "select[data-id='period']")
	private WebElement requestedByDropDown;

	@FindBy(css = "table tbody tr td:nth-child(1)")
	private List<WebElement> jobIdTds;

	@FindBys({ @FindBy(css = "div:nth-child(2) table tbody tr") })
	private List<WebElement> trCollections;

	@FindBy(css = ".dataTable tbody")
	private WebElement tableBody;

	// Constructor section

	public ImportHomePage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
	
		PageFactory.initElements(driver, this);

	}

	public ImportHomePage load() {
		driver.navigate().to(PagesHelper.URL+pageUrl);
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditions.visibilityOfElementLocated(By
						.cssSelector(".dataTable tbody")), driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
	}

	/***
	 * This method is added to search the import by Type, Ownedby, Status,
	 * Requested options
	 * 
	 * @param searchTypeEle
	 *            : Type to select from Type drop down
	 * @param label
	 *            : select type label to match
	 * @param defaultIndex
	 *            : select defaultIndex option if given search string does not
	 *            present.
	 * @author sangeetap
	 */

	public enum IMPORTS_HOME_DROPDOWN {
		TYPE, OWNDE_BY, STATUS, REQUESTED
	}

	public void selectDropDownOnImportPage(IMPORTS_HOME_DROPDOWN whichDropDown,String visibleText) {
		switch (whichDropDown) {
		case OWNDE_BY:
			DriverUtility.selectDropDown(ownedByDropDown, visibleText, 1);
			break;
		case REQUESTED:
			DriverUtility.selectDropDown(requestedByDropDown,
					visibleText, 1);
		default:
			throw new FrameworkException(whichDropDown.toString()+" is not mapped for selection in method");
		}
		DriverUtility.waitforElementDisplay(driver, tableBody, 40);

	}

	/***
	 * This mehtod is added to verify the status of the created import job
	 * 
	 * @param jobId
	 *            - job number to search
	 * @param label
	 *            - status of import job FINISHED/ERROR
	 * @return
	 */
	public boolean verifyStatusImportPage(String jobId, String expectedStatus) {
		selectDropDownOnImportPage(IMPORTS_HOME_DROPDOWN.OWNDE_BY,"Me");
		selectDropDownOnImportPage(IMPORTS_HOME_DROPDOWN.REQUESTED,"Within the last 24 hours");
		int index;
		boolean jobFound = false;
		boolean expStatus = false;
		int retryCount = 0;
		long startTime = System.currentTimeMillis() / 1000;
		Reporter.log("startTime = " + startTime,true);
		long stopTime = startTime + 300;
		Reporter.log("stopTime = " + stopTime,true);

		while (System.currentTimeMillis() / 1000 <= stopTime) {
			DriverUtility.waitforElementDisplay(driver, tableBody, 30);
			for (index = 0; index < trCollections.size(); index++) {

				if (jobIdTds.get(index).getText().equalsIgnoreCase(jobId)) {
					jobFound = true;
					WebElement statusCol = driver
							.findElement(By
									.cssSelector("div:nth-child(2) table tbody tr:nth-of-type("
											+ (index + 1)
											+ ")> td:nth-of-type(4) span"));
					if (statusCol.getText().equalsIgnoreCase(expectedStatus)) {
						expStatus = true;
						break;
					}
				}
			}
			if (!jobFound) {
				retryCount++;
			}

			if (expStatus || retryCount == 3) {
				break;
			}
			driver.navigate().refresh();
			DriverUtility.waitforElementDisplay(driver, tableBody, 40);
			selectDropDownOnImportPage(IMPORTS_HOME_DROPDOWN.OWNDE_BY,"Me");
			selectDropDownOnImportPage(IMPORTS_HOME_DROPDOWN.REQUESTED,"Within the last 24 hours");
		}
		return expStatus;
	}

}
