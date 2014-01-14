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
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;

public class TweetsSchedulePage extends TweetsContentPage {

	// Tweet Schedule page web element allocation

	// @FindBy(css= "input[id='scheduleDatePicker']")
	@FindBy(css = "input#scheduleDatePicker")
	private WebElement dateBox;

	@FindBy(css = "button[id = 'save']")
	private WebElement saveScheduleButton;

	@FindBy(css = "div:nth-child(3) button.social-enable")
	private WebElement enable;

	@FindBy(css = "input[name = 'scheduleHour']")
	private WebElement hourTextBox;

	@FindBy(css = "input[name = 'scheduleMinute']")
	private WebElement minuteTextBox;

	@FindBy(css = "div.modal button.confirm")
	private WebElement confirm;

	@FindBy(css = "input[value = 'am']")
	private WebElement amRadioButton;

	@FindBy(css = "input[value = 'pm']")
	private WebElement pmRadioButton;	

	@FindBy(css = "input[id = 'scheduleNow']")
	private WebElement scheduleImmediatelyButton;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td")
	public List<WebElement> tds;

	@FindBy(css = ".workflow-master-btn.btn-mini.social-disable.btn-danger")
	public WebElement disableBtn;
	
	private WebDriver driver;

	// Constructor

	public TweetsSchedulePage(WebDriver driver, String pageUrl) {

		super(driver, pageUrl);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

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
					DriverUtility.waitforElementDisplay(driver, dateBox, 50);
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
		DriverUtility.waitforElementDisplay(driver,hourTextBox,50);
		enterCurrentHour();
		DriverUtility.waitforElementDisplay(driver,minuteTextBox,50);
		enterCurrentMinutes();
		DriverUtility.waitforElementDisplay(driver,amRadioButton,50);
		setAmPm();
	}

	/**
	 * This method is added to save schedule
	 */
	public boolean saveSchedule() {
		saveScheduleButton.click();
		Reporter.log("Ribbon Text Message for Tweet Schedule Page is: " +getRibbonText(10)+"<br>",true);
		return stepCompleted(2, 10);
	}

	/***
	 * This method is added to enable and confirm the tweet
	 */
	public boolean enableandConfirmTweet() {
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(enable), driver, 20);
		enable.click();
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(confirm), driver, 30);
		confirm.click();
		return(DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(disableBtn), driver, 30) != null);
		
	}

	/***
	 * This method is added to schedule Tweet immediately
	 */
	public void scheduleImmediately() {
		setDateTime();
		scheduleImmediatelyButton.click();
		saveSchedule();
	}

	/***
	 * This method is added to schedule Tweet on specific time /later
	 * @return 
	 */
	public boolean scheduleTweet() {

		setDateTime();
		return saveSchedule();	

	}

	/***
	 * This method is added to get the generated tweet id
	 * 
	 * @return getMaserId - generated tweet id
	 */

	public String getMasterId() {
				String loginUrl = driver.getCurrentUrl();
		String jobNum = driver.getCurrentUrl()
				.substring(loginUrl.lastIndexOf("#") + 1)
				.replaceAll("[^0-9]", "");
		return jobNum;
	}
}
