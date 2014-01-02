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
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ReportsPage extends BasePage {

	/**
	 * Initializing Page objects
	 */

	@FindBy(css = "#mainContentArea div:nth-child(3) select[name='typeSelect']")
	private WebElement typeSelectDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(3) select[name='ownerSelect']")
	private WebElement ownerSelectDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(3) select[name='statusSelect']")
	private WebElement statusSelectDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(3) select[name='requestedSelect']")
	private WebElement requestedSelectDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(2) button")
	private WebElement requestNewReportButton;
	
	@FindBys({@FindBy(css = "table tbody tr td:nth-child(1)") })
	private List<WebElement> jobIdTds;
	
	@FindBys({@FindBy(css = "table tbody tr td:nth-child(4) span") })
	private List<WebElement> currentStatusTds;

	@FindBys({ @FindBy(css = "div:nth-child(2) table tbody tr") })
	private List<WebElement> trCollections;

	@FindBy(css = ".dataTable tbody")
	private WebElement tableBody;

	private WebDriver driver;
	private String pageUrl;

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
						elementToBeClickable(By
								.cssSelector("#mainContentArea div:nth-child(3) select[name='typeSelect']")),
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
	 * This is the enum define for report option
	 * 
	 * @author sangeetap
	 * 
	 */
	public enum REPORTS_DROPDOWN {
		TYPE, OWNED_BY, STATUS, REQUESTED
	}

	/***
	 * This method is added to search the Reports by Type,owned by, Status
	 * ,requested options
	 * 
	 * @author sangeetap
	 * 
	 */

	public void selectDropDownOnReportsPage(REPORTS_DROPDOWN selectDropDown,
			String TextValue) {
		WebElement targetElement = null;
		switch (selectDropDown) {
		case TYPE:
			targetElement = typeSelectDropDown;
			break;
		case OWNED_BY:
			targetElement = ownerSelectDropDown;
			break;
		case STATUS:
			targetElement = statusSelectDropDown;
			break;
		case REQUESTED:
			targetElement = requestedSelectDropDown;
			break;
		}
		DriverUtility.selectDropDown(targetElement, TextValue, 1);

	}
	
	
	/**
	 * This method is added to select the report type Delivery and Response from Type
	 * drop down.
	 * 
	 * @param typeOption
	 *            - text to select type option
	 */
	public void selectViewType(String typeOption) {

		DriverUtility
				.selectDropDown(typeSelectDropDown, typeOption, 0);
		DriverUtility.waitforElementDisplay(driver, tableBody, 30);
	}

	/***
	 * This method is added to find the job number of the current generated report by status requested.
	 * @param currentStatus - generated report current status 
	 * @return - report job id
	 */
	public String getReportId(String currentStatus)
	{
		
		String reportId = null;
	
		for (int index = 0; index < trCollections.size(); index++) 

			{

			if (currentStatusTds.get(index).getText().equalsIgnoreCase(currentStatus)) {
				
				WebElement jobIdCol = driver
						.findElement(By
								.cssSelector("div:nth-child(2) table tbody tr> td:nth-of-type(1)"));
				reportId= jobIdCol.getText();
					break;
				}
		}
				
		return reportId;
	}

	/***
	 * This method is added to verify the status of the created report type Delivery and Response
	 * 
	 * @param jobId
	 *            - created report id 
	 * @param expectedStatus
	 *            - expected status of the created report
	 * @return
	 */

	public boolean verifyReportsStatus(String jobId, String expectedStatus) {
		// selectViewType("Delivery and Response");
		int index;
		boolean jobFound = false;
		boolean expStatus = false;
		int retryCount = 0;

		long startTime = System.currentTimeMillis() / 1000;

		long stopTime = startTime + 300;

		while (System.currentTimeMillis() / 1000 <= stopTime) {

			DriverUtility.waitforElementDisplay(driver, tableBody, 30);

			for (index = 0; index < trCollections.size(); index++) {

				if (jobIdTds.get(index).getText().equalsIgnoreCase(jobId)) {
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
			DriverUtility.waitforElementDisplay(driver, tableBody, 40);
			selectViewType("Delivery and Response");
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