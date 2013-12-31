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
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

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

	@FindBy(css = "div:nth-child(1) span input[name='startMinute'])")
	public WebElement minuteTextBox;

	@FindBy(css = "div.ym-emailSchedule>div:nth-child(1)>div.alert")
	public WebElement confirm;

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

	private WebDriver driver;

	@SuppressWarnings("unused")
	private String pageUrl;

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
		
		if(null == DriverUtility.waitFor(elementToBeClickable(dateBox), driver, 50))
		{
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
		
	}
	
	public SmsSchedulePage load()
	{
		navigateToSchedule();
		return this;
	}

	/***
	 * This method is added to insert date
	 */

	public void insertDate() {
		DriverUtility.waitforElementDisplay(driver, dateBox, 10);
		Calendar cal = new GregorianCalendar();
		int dateOne = cal.get(Calendar.DATE);
		String month_Day = "" + dateOne;
		dateBox.clear();
		dateBox.click();
		for (WebElement td : tds) {
			if (td.getText().equals(month_Day)) {
				if (td.getAttribute("class").contains("ui-datepicker-today")) {
					td.click();
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
		DriverUtility.waitforElementDisplay(driver, hourTextBox, 5);
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
	 * Clicking on "confirm" button on Popup
	 * 
	 * @author sangeetap
	 */

	public void enableButtonAndConfirm() {

		enableBtn.click();
		confirm.click();
	}

	/**
	 * Setting current date and time and scheduling the schedule page.
	 * 
	 * @author sangeetap
	 */
	public void scheduleSMSMaster() {
		setDateTime();
		saveScheduleButton();
		enableButtonAndConfirm();

	}
}
