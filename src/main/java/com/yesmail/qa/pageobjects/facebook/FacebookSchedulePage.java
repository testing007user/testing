/***
 * File : FacebookSchedulePage.java
 * Description : This java class is used to schedule Facebook post
 * @author ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * 1.Add header comment and comment for each method in 
 * FacebookSchedulePage  java files. 
 * 2.Please remove List <WebElement> radios = driver.findElements(By.xpath("//*[@name='schedule']")); on line number 127.Try to define it as webelement. As its breaking page object concept.(FacebookSchedulePage.java) 
 * 3.getMasterId() method is used but there is no definition.Please create the method.(FacebookSchedulePage.java) 
 *   
 */
package com.yesmail.qa.pageobjects.facebook;

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

public class FacebookSchedulePage {

	// Facebook Schedule Page web element allocation

	@FindBy(css = "div:nth-child(2) input[id='scheduleDatePicker']")
	public WebElement dateBox;

	@FindBy(css = "button[id = 'save']")
	private WebElement saveSchedule;

	@FindBy(css = "button.social-enable")
	private WebElement enableButton;

	@FindBy(css = "div:nth-child(1) input[name='scheduleHour']")
	public WebElement hourTextBox;

	@FindBy(css = "div:nth-child(1) input[name='scheduleMinute']")
	public WebElement minuteTextBox;

	@FindBy(css = "div:nth-child(3) button.confirm")
	private WebElement confirmButton;

	@FindBy(css = "div:nth-child(1) div input[value='am']")
	public WebElement amRadioButton;

	@FindBy(css = "div:nth-child(1) div input[value='pm']")
	public WebElement pmRadioButton;

	@FindBy(css = "div:nth-child(2) a:nth-of-type(4) span:nth-of-type(2)")
	public WebElement scheduleButton;

	@FindBy(css = "//div[@class='datePickerNextButton datePickerNextButton-up']")
	public WebElement month;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td")
	public List<WebElement> tds;

	@FindBy(css = "a[href = '#facebook/schedule']")
	private WebElement schedule;

	@FindBy(css = "div:nth-child(2)>a:nth-of-type(1)")
	private WebElement contentTab;

	@FindBy(css = "input[id = 'scheduleNow']")
	private WebElement scheduleImmediately;

	private WebDriver driver;
	private String pageUrl;

	// Constructor

	public FacebookSchedulePage(WebDriver driver, String pageUrl) {

		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	} // end of constructor

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditions
				.elementToBeClickable(By.id("scheduleDatePicker")), driver, 50))
			throw new FrameworkException(FacebookSchedulePage.class.getName()
					+ " is not loaded");
	}

	/***
	 * This method is added to navigate to Content Tab
	 */
	public void navigateToContentTab() {
		contentTab.click();
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
		DriverUtility.waitforElementDisplay(driver, dateBox, 20);
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

	/***
	 * This method is added to save schedule
	 */
	public void saveSchedule() {
		saveSchedule.click();
	}

	/***
	 * This method is added to enable and confirm the facebook post
	 */
	public void enableFacebook() {
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(enableButton), driver,
				50);
		enableButton.click();
		DriverUtility.waitforElementDisplay(driver, confirmButton, 30);
		confirmButton.click();

	}

	/***
	 * This method is added to schedule post immediately
	 */
	public void scheduleImmediately() {
		setDateTime();
		scheduleImmediately.click();
		saveSchedule();
		enableFacebook();
	}

	/***
	 * This method is added to schedule post on specific time /later
	 */
	public void scheduleMaster() {

		setDateTime();
		saveSchedule();
		enableFacebook();

	}

}

// end of FacebookSchedulePage class

