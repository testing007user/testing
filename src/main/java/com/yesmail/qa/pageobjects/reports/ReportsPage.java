/** File Name: ReportsPage.java  
 * Description: This Java Class is 
 * used to searh the different type of reports based on  Type,owned by, Status,requested options
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * Date: 12/5/2013 
 * Version History 
 * Version name Updated By Reason / Comments 
 * 
 * */

package com.yesmail.qa.pageobjects.reports;

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

public class ReportsPage extends BasePage {
	
	private WebDriver driver;
	private String pageUrl;

	/**
	 * Initializing Page objects
	 */

	@FindBy(css = "#mainContentArea div:nth-child(3) select[name='typeSelect']")
	private WebElement typeSelectDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(3) select[name='ownerSelect']")
	private WebElement ownerSelectDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(3) select[name='requestedSelect']")
	private WebElement requestedSelectDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(2) button")
	private WebElement requestNewReportButton;

	@FindBys({ @FindBy(css = "div:nth-child(2) table tbody tr") })
	private List<WebElement> trCollections;
	
	@FindBys({ @FindBy(css = "table tbody tr td:nth-child(2)") })
	private List<WebElement> messageIdTds;

	@FindBy(css = "div[data-target='reportsTable']>div.tableContainer")
	private WebElement tableContainer;
	
	@FindBy(css = "tbody > tr > td:nth-of-type(3).selectable")
	private List<WebElement> filterByType;

	@FindBy(css = "tbody > tr > td:nth-of-type(5).selectable")
	private List<WebElement> filterByMe;	

	/**
	 * Initializing Constructor
	 */

	public ReportsPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public void isLoaded() {
	
		if (null == DriverUtility
				.waitFor(
						ExpectedConditionExtended.elementsToBeClickable(tableContainer),
						driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}

	}

	public ReportsPage load() {
		// TODO Auto-generated method stub
		driver.get(PagesHelper.URL + pageUrl);
		return this;
	}

	/***
	 * This method is added to search the Reports by Type,owned by, Status
	 * ,requested options
	 * 
	 * @author sangeetap
	 * 
	 */

	public void selectDropDownOnReportsPage() {
		DriverUtility.selectDropDown(typeSelectDropDown,
				PagesHelper.REPORT_TYPE, 1);
		DriverUtility.verifyListFilteredBy(driver, filterByType,
				PagesHelper.REPORT_TYPE);
		DriverUtility.selectDropDown(ownerSelectDropDown, "Me", 1);
		DriverUtility.verifyListFilteredBy(driver, filterByMe, "Me");
		DriverUtility.selectDropDown(requestedSelectDropDown,
				"Within the Last 24 Hours", 1);		
	}	
	
	
	/***
	 * This method is added to verify the status of the created report type
	 * Delivery and Response
	 * 
	 * @param jobId
	 *            - created report id
	 * @param expectedStatus
	 *            - expected status of the created report
	 * @return
	 */

	public boolean verifyReportsStatus(String reportId, String expectedStatus) {
		int index;
		boolean jobFound = false;
		boolean expStatus = false;
		int retryCount = 0;

		long startTime = System.currentTimeMillis() / 1000;

		long stopTime = startTime + 300;

		while (System.currentTimeMillis() / 1000 <= stopTime) {

			DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(trCollections),driver,30);

			for (index = 0; index < trCollections.size(); index++) {

				if (messageIdTds.get(index).getText()
						.equalsIgnoreCase(reportId)) {
					jobFound = true;
					WebElement statusCol = driver
							.findElement(By
									.cssSelector("div:nth-child(2) table tbody tr:nth-of-type("
											+ (index + 1) + ")> td span"));
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
			DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(tableContainer),driver,40);			
			selectDropDownOnReportsPage();
		}
		return expStatus;
	}	

	/***
	 * This method is added to navigate to the request new report page
	 * 
	 * @author sangeetap
	 * 
	 */
	public void navigateToRequestNewReport() {
		requestNewReportButton.click();
	}
}