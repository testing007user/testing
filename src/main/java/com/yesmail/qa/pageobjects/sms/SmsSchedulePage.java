/**
 * File : SmsSchedulePage.java
 * Description: This Java Class is for SMS Header page creation
 * 
 * @author sangeetap
 * @since Completed 10th December, 2013
 * 
 */

package com.yesmail.qa.pageobjects.sms;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

public class SmsSchedulePage extends SmsBasePage {

	/**
	 * Initializing Page objects
	 */

	@FindBy(id = "startDatepicker")
	public WebElement dateBox;

	@FindBy(css = "div.smsSchedule button")
	public WebElement saveSchedule;

	@FindBy(css = "button.workflow-master-btn")
	public WebElement enableBtn;

	@FindBy(css = "div:nth-child(1) span input[name='startHour']")
	public WebElement hourTextBox;

	@FindBy(css = "input[name='startMinute']")
	public WebElement minuteTextBox;

	@FindBy(css = "div:nth-child(1) span div input[value='am']")
	public WebElement amRadioButton;

	@FindBy(css = "div:nth-child(1) span div input[value='pm']")
	public WebElement pmRadioButton;

	@FindBy(css = "div:nth-child(2) a:nth-of-type(4) span:nth-of-type(2)")
	public WebElement scheduleButton;

	@FindBy(css = "//div[@class='datePickerNextButton datePickerNextButton-up']")
	public WebElement month;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td")
	public List<WebElement> tds;

	@FindBys({ @FindBy(css = "table.ui-datepicker-calendar td a") })
	private List<WebElement> dateList;

	private WebDriver driver;

	@SuppressWarnings("unused")
	private String pageUrl;

	@FindBy(css = ".confirm")
	private WebElement confirmBtn;

	@FindBy(css = ".btn-danger")
	private WebElement disableBtn;

	/**
	 * Initializing Constructor
	 */

	public SmsSchedulePage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(dateBox),
				driver, 50)) {

			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}

	}

	public SmsSchedulePage load() {
		navigateToSchedule();
		return this;
	}

	/***
	 * This method is added to Insert Formatted Date.
	 * 
	 * @return
	 */
	private void insertDate() {
		Calendar cal = new GregorianCalendar();
		int dateOne = cal.get(Calendar.DATE);
		String month_Day = "" + dateOne;
		dateBox.clear();
		dateBox.click();
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(dateList),
				driver, 20);
		for (WebElement date : dateList) {
			if (date.getText().equals(month_Day)) {
				if (date.getAttribute("class").contains("ui-state-highlight")) {
					date.click();
					break;
				}
			}
		}
	}

	/***
	 * This method is added to enter the current hours
	 * 
	 */

	public void enterCurrentHour() {
		hourTextBox.clear();
		hourTextBox.sendKeys(Utils.getHourString());
	}

	/***
	 * This method is added to enter the current minutes
	 */
	public void enterCurrentMinutes() {
		minuteTextBox.clear();
		minuteTextBox.sendKeys(Utils.getMinuteString());
	}

	/***
	 * This method is to set am/pm
	 */
	public void setAmPm() {
		if (Utils.getMeriDian().equals("am"))
			amRadioButton.click();
		else
			pmRadioButton.click();
	}

	/***
	 * This method is added to set Date and Time
	 */

	public void setDateTime() {
		insertDate();
		enterCurrentHour();
		enterCurrentMinutes();
		setAmPm();
	}

	/**
	 * Clicking on "save" button
	 * 
	 * @author sangeetap
	 */
	public void saveScheduleButton() {

		saveSchedule.click();

	}

	/**
	 * Clicking on "Enable" and "confirm" button.
	 * 
	 * @author sangeetap
	 */
	public boolean enableButtonAndConfirm() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(enableBtn),
				driver, 10);
		enableBtn.click();
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(confirmBtn),
				driver, 10);
		confirmBtn.click();
		return (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(disableBtn),
				driver, 30) != null);
	}

	/**
	 * Setting current date and time and scheduling the schedule page.
	 * 
	 * @author sangeetap
	 */
	public boolean scheduleSMSMaster() {
		setDateTime();
		saveScheduleButton();
		Reporter.log("Ribbon Text for SchedulePage is: " + getRibbonText(20)
				+ "<br>");
		return stepCompleted(4, 10);

	}

	/**
	 * This method is added to get Scheduled Date
	 * 
	 * @return Scheduled Date
	 * @author sangeetap
	 */
	public String getScheduleDate() {
		return dateBox.getAttribute("value");

	}

	/**
	 * This method is added to get Scheduled Time
	 * 
	 * @return Scheduled Time
	 * @author sangeetap
	 */
	public String getScheduleTime() {
		String strHour = hourTextBox.getAttribute("value");
		String strMinute = minuteTextBox.getAttribute("value");
		String strHourUpdated = strHour.concat(":");
		String strFinalTime = strHourUpdated.concat(strMinute);
		return strFinalTime;
	}

	/**
	 * This method is added to get Scheduled Date Time
	 * 
	 * @return Formatted Scheduled Date Time
	 * @author sangeetap
	 */
	public String getScheduledDateTime() {
		return getScheduleDate() + " " + getScheduleTime();
	}

}
