/**File name : TestSchedulePage.java
 * @Description: This Java Class is used to fill the Test Schedule page. 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @since completed by 12/4/13
 * @Version History
 * Version name Updated By Reason / Comments 
 * */

package com.yesmail.qa.pageobjects.mvt;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TestSchedulePage extends MvtBase {

	@FindBy(css = "div:nth-child(2) a.mvt-ico-schedule")
	private WebElement scheduleTab;

	@FindBy(id = "startDatepicker")
	private WebElement dateBox;

	@FindBy(css = "div:nth-child(1) span input.hour")
	private WebElement hourTextBox;

	@FindBy(css = "div:nth-child(1) span input.minute")
	private WebElement minuteTextBox;

	@FindBy(css = "div:nth-child(1) span input[value='am']")
	private WebElement amRadioButton;

	@FindBy(css = "div:nth-child(1) span input[value='pm']")
	private WebElement pmRadioButton;

	@FindBy(css = "div > button")
	private WebElement saveButton;

	@FindBy(id = "enableMaster")
	private WebElement masters;

	@FindBy(css = "div.ym-emailSchedule>div:nth-child(1)>div.alert")
	private WebElement confirmpMsg;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td")
	public List<WebElement> tds;

	private WebDriver driver;
	
	/***
	 * Contructor 
	 * 
	 * @param driver
	 * @param pageUrl
	 */

	public TestSchedulePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	public TestSchedulePage load()
	{
		navigateToScheduleTab();
		return this;
	}
	
	public void isLoaded()
	{
		if(null == DriverUtility.waitFor(elementToBeClickable(By.id("startDatepicker")), driver, 50))
		{
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
		}
	}
	

	/***
	 * This method is added to select the AM/PM radio button
	 *  
	 */
	public void setAmPm() {
		if (Utils.getMeriDian().equals("am")) {
			amRadioButton.click();
		} else {
			pmRadioButton.click();
			if (!pmRadioButton.isSelected()) {
				pmRadioButton.click();
			}
		}
	}

	/***
	 * This method is added to enter the current hours 
	 * 
	 */
	
	public void enterCurrentHour() {
		DriverUtility.waitforElementDisplay(driver, hourTextBox, 20);
		hourTextBox.clear();
		String hourString = Utils.getHourString();
		Reporter.log("Hour Enter is:"+hourString);
		hourTextBox.sendKeys();
		}

	/***
	 * This method is added to enter the current minutes 
	 */
		public void enterCurrentMinutes() {
		DriverUtility.waitforElementDisplay(driver, minuteTextBox, 20);
		minuteTextBox.clear();
		String minString = Utils.getMinuteString();
		Reporter.log("Minute Enter is:"+minString);
		minuteTextBox.sendKeys(minString);
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
	}

	/***
	 * This method is added to set Date and Time 
	 */
	
	public void setDateTime() {
		insertDate();
		DriverUtility.waitforElementDisplay(driver, hourTextBox, 20);
		enterCurrentHour();
		DriverUtility.waitforElementDisplay(driver, minuteTextBox, 20);
		enterCurrentMinutes();
		DriverUtility.waitforElementDisplay(driver, amRadioButton, 20);
		setAmPm();
	}

	/***
	 * This method is added to click on save button
	 */
	
	public void saveSchedule() {
		saveButton.click();

	}
	
/***
 * This method is added to fill and save the schedule page
 * 
 * 
 * @return displayed message 
 */
	public boolean fillSchedulePage() {

		setDateTime();
		saveSchedule();
		boolean msgDisplay = confirmpMsg.getText() != null;
		return msgDisplay;
	}

}
