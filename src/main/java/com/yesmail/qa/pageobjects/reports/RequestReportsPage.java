/** File Name: RequestReportsPage.java  
 * Description: This Java Class is added
 * to create new report based on Deliver and Response
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * Date: 12/6/2013 
 * Version History 
 * Version name Updated By Reason / Comments 
 * 
 * */

package com.yesmail.qa.pageobjects.reports;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class RequestReportsPage {

	/**
	 * Initializing Page objects
	 */

	@FindBy(css = "table tbody tr:nth-of-type(1) td:nth-of-type(2)")
	private WebElement tableMasterIDMailingView;

	@FindBy(css = "#mainContentArea div:nth-child(3) select.reportTypeSelect")
	private WebElement reportTypeDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(3) select.mailingViewSelect")
	private WebElement viewBy;

	@FindBy(css = "button#selectAll")
	private WebElement selectAllButton;

	@FindBy(css = "table.dataTable tr:nth-of-type(1) td input")
	private WebElement firstRowCheckBox;

	@FindBy(name = "viewName")
	private WebElement veiwNameTextBox;

	@FindBy(css = "div:nth-child(2) button:nth-of-type(2).moveRight")
	private WebElement singleSelectbtn;

	@FindBy(css = "div:nth-child(1) select.crossTabSelect")
	private WebElement crosstabCheckBox;

	@FindBy(css = "label.radio:nth-of-type(2) input[name=runNow]")
	private WebElement scheduleReportRadioButton;

	@FindBy(css = "label.radio:nth-of-type(1) input[name=runNow]")
	private WebElement runReportNowRadioButton;

	@FindBy(css = "div:nth-child(4) span:nth-of-type(1)>input.datepicker")
	private WebElement dateBox;

	@FindBy(css = "div:nth-child(3) button.save")
	private WebElement requestReportButton;

	@FindBys({ @FindBy(css = "select.availableOptions") })
	private List<WebElement> attributesList;

	@FindBy(css = "div:nth-child(3)>button:nth-of-type(3)")
	private WebElement viewSaveBtnCreateNew;

	@FindBy(xpath = "//a[@href='#reports']")
	private WebElement reportLink;

	@FindBy(css = "table.ui-datepicker-calendar tbody td a.ui-state-highlight")
	private WebElement calenderTodaysDate;

	@FindBys({ @FindBy(css = "table.ui-datepicker-calendar tbody td") })
	private List<WebElement> calenderTableCol;

	@FindBy(css = "div:nth-child(4) input.am")
	private WebElement am;

	@FindBy(css = "div:nth-child(4) input.pm")
	private WebElement pm;

	@FindBy(css = "div:nth-child(4) input.hour")
	private WebElement hourElement;

	@FindBy(css = "div:nth-child(4) input.minute")
	private WebElement minute;

	private WebDriver driver;

	private String pageUrl;

	/**
	 * Initializing Constructor
	 */
	public RequestReportsPage(WebDriver driver, String pageUrl) {

		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public RequestReportsPage load() {
		driver.navigate().to(PagesHelper.URL+pageUrl);
		return this;
	}

	public void isLoaded() {
		ExpectedCondition<WebElement> condition = ExpectedConditions
				.elementToBeClickable(By
						.cssSelector("#mainContentArea div:nth-child(3) select.reportTypeSelect"));
		if (null == DriverUtility.waitFor(condition, driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
	}

	/**
	 * This method is added to select the report type
	 * visible text : Delivery and Response
	 * @author sangeetap
	 */
	public void selectReportType(String visibleText) {

		DriverUtility.selectDropDown(reportTypeDropDown,
				visibleText, 0);
	}

	/**
	 * This method is added to select Mailing view
	 * 
	 * @author sangeetap
	 */

	public void selectMailingView(String visibleText) {
		DriverUtility.selectDropDown(viewBy, visibleText, 0);
		DriverUtility.waitforElementDisplay(driver, tableMasterIDMailingView,
				30);
		firstRowCheckBox.click();
	}

	/**
	 * This method is added to select attributes
	 * 
	 * @author sangeetap
	 */
	public void selectAttributes(String attribute) {

		for (WebElement attEle : attributesList) {
			attEle.getText().equalsIgnoreCase(attribute);
			attEle.click();
			break;
		}
		singleSelectbtn.click();
	}

	/**
	 * This method is added to set schedule date
	 * 
	 * @author sangeetap
	 */
	public void scheduleDate() {
		Calendar cal = new GregorianCalendar();
		int dateOne = cal.get(Calendar.DATE);
		String month_Day = "" + dateOne;
		dateBox.clear();
		dateBox.click();

		for (WebElement td : calenderTableCol) {
			if (td.getText().equals(month_Day)) {
				if (!td.getAttribute("class").contains("ui-datepicker-today")) {
					td.click();
					break;
				}
			}
		}

	}

	/***
	 * method for getting hours or minute of the day for
	 * 
	 * @param timeUnit
	 *            : String "minute" ,"hour"
	 * @param minuteToAdd
	 *            : number to minutes to add in the current time stamp
	 * @author sangeetap
	 * 
	 */
	public void addHourMinute() {
		minute.sendKeys(Utils.getMinuteString());
		hourElement.sendKeys(Utils.getHourString());
	}

	/**
	 * This method is added to set am/pm
	 * 
	 * @author sangeetap
	 */

	public void setAmPm() {
		if (Utils.getMeriDian().equals("am")) {
			am.click();
		} else {
			pm.click();
			if (!pm.isSelected()) {
				pm.click();
			}
		}
	}

	/**
	 * This method is added to create new report
	 * 
	 * @author sangeetap
	 */
	public void RequestRunReport(String visibleTextReportType,String visibleTextMailingView,String attributeText) {
		selectReportType(visibleTextReportType);
		selectMailingView(visibleTextMailingView);
		selectAttributes(attributeText);
		runReportNowRadioButton.click();
		requestReportButton.click();
	}

}