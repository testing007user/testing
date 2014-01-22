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
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class ImportHomePage extends BasePage {

	/***
	 * Page Elements for ImportHomePage Class
	 */
	private WebDriver driver;
	private String pageUrl;
	

	@FindBy(css = "select[data-id='owner']")
	private WebElement ownedByDropDown;	

	@FindBy(css = "select[data-id='period']")
	private WebElement requestedByDropDown;

	@FindBy(css = "table tbody tr td:nth-child(1)")
	private List<WebElement> jobIdTds;

	@FindBys({ @FindBy(css = "div:nth-child(2) table tbody tr") })
	private List<WebElement> trCollections;

	@FindBy(css = ".dataTable tbody")
	private WebElement tableBody;
	
	@FindBy(css = "tbody > tr > td:nth-of-type(5).selectable")
	private List<WebElement> filterByMe;

	// Constructor section
	public ImportHomePage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;

		PageFactory.initElements(driver, this);

	}

	public ImportHomePage load() {
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
	 * This method is added to search the import by Type, Ownedby, Status,
	 * Requested options
	 * 
	 * @param defaultIndex
	 *            : select defaultIndex option if given search string does not
	 *            present.
	 * @author sangeetap
	 */
	public void selectDropDownOnImportPage() {
		DriverUtility.selectDropDown(ownedByDropDown, "Me", 1);
		DriverUtility.verifyListFilteredBy(driver, filterByMe, "Me");
		DriverUtility.selectDropDown(requestedByDropDown,
				"Within the last 24 hours", 1);
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
	public boolean verifyStatusOnImportPage(String jobId, String expectedStatus) {
		selectDropDownOnImportPage();
		int index;
		boolean jobFound = false;
		boolean expStatus = false;
		int retryCount = 0;
		long startTime = System.currentTimeMillis() / 1000;
		long stopTime = startTime + 300;

		while (System.currentTimeMillis() / 1000 <= stopTime) {
			DriverUtility.waitFor(
					ExpectedConditionExtended.elementToBeClickable(jobIdTds),
					driver, 30);
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
			DriverUtility.waitFor(
					ExpectedConditionExtended.elementToBeClickable(jobIdTds),
					driver, 30);
			selectDropDownOnImportPage();
		}
		return expStatus;
	}

}
