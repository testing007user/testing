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


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
		// TODO Auto-generated method stub
		if (null == DriverUtility
				.waitFor(
						elementToBeClickable(typeSelectDropDown),
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