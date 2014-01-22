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
import com.yesmail.qa.pageobjects.BasePage;

public class FacebookSchedulePage extends BasePage {

	// Facebook Schedule Page web element allocation

	@FindBy(css = "div:nth-child(2) input[id='scheduleDatePicker']")
	public WebElement dateBox;

	@FindBy(css = "div.twitter button#save")
	private WebElement saveScheduleButton;

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

	@FindBys({@FindBy(css = "table.ui-datepicker-calendar td a")})
	public List<WebElement> tds;	

	@FindBy(css = "input[id = 'scheduleNow']")
	private WebElement scheduleImmediatelyButton;

	@FindBy(css = "button.social-disable")
	private WebElement disableButton;

	@FindBy(css = "div:nth-child(2)>a:nth-of-type(2)")
	private WebElement scheduleTab;
	
	@FindBy(css = ".workflow-master-btn.btn-mini.social-disable.btn-danger")
	public WebElement disableBtn;
	
	@FindBy(id = "scheduleNowFields")
	private WebElement scheduleNowFields;
	
	

	private WebDriver driver;

	// Constructor

	public FacebookSchedulePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	} // end of constructor

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(scheduleNowFields), driver, 50))
			throw new FrameworkException(FacebookSchedulePage.class.getName()
					+ " is not loaded");
	}

	/***
	 * This method is added to navigate to Schedule Tab
	 */
	public FacebookSchedulePage load() {
		scheduleTab.click();
		return this;
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
				if (td.getAttribute("class").contains("ui-state-highlight")) {
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
	private void setAmPm() {
		if (Utils.getMeriDian().equals("am"))
			amRadioButton.click();
		else
			pmRadioButton.click();
	}

	/***
	 * This method is added to set Date and Time
	 */

	private void setDateTime() {

		insertDate();
		enterCurrentHour();
		enterCurrentMinutes();
		setAmPm();
	}

	/***
	 * This method is added to save schedule
	 */
	private boolean saveSchedule() {

		saveScheduleButton.click();
		Reporter.log("Ribbon Text after saving schedule :"+getRibbonText(20)+"<br>");
		return stepCompleted(2, 20);
	}
	
	/***
	 * This method is added to enable and confirm the Facebook Post
	 */
	public boolean enableandConfirmFacebook() {
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(enableButton), driver, 20);
		enableButton.click();
		
		if (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(confirmButton), driver, 10) != null)
		confirmButton.click();
		
		return(DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(disableBtn), driver, 30) != null);
		
	}
	

	/***
	 * This method is added to schedule post immediately
	 */
	public void scheduleImmediately() {
		scheduleImmediatelyButton.click();
		saveSchedule();
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(disableButton),driver,30);
	}

	/***
	 * This method is added to schedule post on specific time /later
	 */
	public boolean scheduleMaster() {

		setDateTime();
		return saveSchedule();
	}

	/***
	 * This method is added to get the generated Facebook master id
	 * 
	 * @return getMaserId - return generated Facebook master id
	 */

	public String getMasterId() {
		String loginUrl = driver.getCurrentUrl();
		String jobNum = driver.getCurrentUrl()
				.substring(loginUrl.lastIndexOf("#") + 1)
				.replaceAll("[^0-9]", "");
		return jobNum;
	}

}
