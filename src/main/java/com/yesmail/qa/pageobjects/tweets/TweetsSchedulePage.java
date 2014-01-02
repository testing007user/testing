/***
 * File : TweetsSchedulePage.java
 * Description : This java class is used to schedule the time for tweet
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *    
 */

package com.yesmail.qa.pageobjects.tweets;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;

public class TweetsSchedulePage extends TweetsContentPage {

	/*
	 * ##########################################################################
	 * Tweet Schedule page web element allocation
	 * ###############################
	 * ###########################################
	 */

	// @FindBy(css= "input[id='scheduleDatePicker']")
	@FindBy(css = "input#scheduleDatePicker")
	private WebElement dateBox;

	@FindBy(css = "button[id = 'save']")
	private WebElement saveSchedule;

	@FindBy(css = "button[data-id = 'masterEnableButton']")
	private WebElement enable;

	@FindBy(css = "input[name = 'scheduleHour']")
	private WebElement hourTextBox;

	@FindBy(css = "input[name = 'scheduleMinute']")
	private WebElement minuteTextBox;

	@FindBy(css = "button.confirm")
	private WebElement confirm;

	@FindBy(css = "input[value = 'am']")
	private WebElement amRadioButton;

	@FindBy(css = "input[value = 'pm']")
	private WebElement pmRadioButton;

	@FindBy(css = "a[href = '#twitter/schedule']")
	private WebElement schedule;

	@FindBy(css = "div:nth-child(2)>a:nth-of-type(1)")
	private WebElement contentTab;

	@FindBy(css = "input[id = 'scheduleNow']")
	private WebElement scheduleImmediately;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td a")
	public List<WebElement> tds;

	private WebDriver driver;

	/*
	 * ##########################################################################
	 * define the constructor
	 * ###################################################
	 * #######################
	 */

	public TweetsSchedulePage(WebDriver driver, String pageUrl) {

		super(driver, pageUrl);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	} // end of constructor

	/*
	 * ##########################################################################
	 * define required methods
	 * ##################################################
	 * ########################
	 */

	public TweetsSchedulePage load() {
		navigateToScheduleTab();
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditions
				.elementToBeClickable(By.id("scheduleDatePicker")), driver, 50))
			throw new FrameworkException(TweetsSchedulePage.class.getName()
					+ " is not loaded");
	}

	/***
	 * This method is added to insert date
	 */

	public void insertDate() {
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
		DriverUtility.waitforElementDisplay(driver, dateBox, 50);
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
	 * This method is added to save scedule
	 */
	public void saveSchedule() {
		saveSchedule.click();
	}

	/***
	 * This method is added to enable and confirm the tweet
	 */
	public void enableTweet() {
		enable.click();
		confirm.click();
	}

	/***
	 * This method is added to schedule tweets immediately
	 */
	public void scheduleImmediately() {
		setDateTime();
		scheduleImmediately.click();
		saveSchedule();
	}

	/***
	 * This method is added to schedule tweets on specific time /later
	 */
	public void scheduleTweet() {

		setDateTime();
		saveSchedule();
		enableTweet();

	}

} // end of TweetsSchedulePage class

