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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class TestSchedulePage extends MvtBase {

	private WebDriver driver;

	@FindBy(id = "startDatepicker")
	private WebElement dateBox;

	@FindBy(id = "winningStartDatePicker")
	private WebElement dateBoxWinning;

	@FindBy(css = "div:nth-child(1) span input.hour")
	private WebElement hourTextBox;

	@FindBy(css = "div:nth-child(1) span input.minute")
	private WebElement minuteTextBox;

	@FindBy(css = "div:nth-child(1) span input[value='am']")
	private WebElement amRadioButton;

	@FindBy(css = "div:nth-child(1) span input[value='pm']")
	private WebElement pmRadioButton;

	@FindBy(css = "div > button[data-action=saveSchedule]")
	private WebElement saveButton;

	@FindBys({ @FindBy(css = "table.ui-datepicker-calendar tbody tr td") })
	public List<WebElement> tds;

	@FindBys({ @FindBy(css = "table.ui-datepicker-calendar tbody tr td a") })
	public List<WebElement> autoWinnningtds;

	@FindBy(css = "button.testCase-generate")
	private WebElement enableBtn;

	@FindBy(css = ".confirm")
	private WebElement confirmBtn;

	@FindBy(css = ".btn-danger")
	private WebElement disableBtn;

	@FindBy(css = "input[name = 'obeyDeliveryLimits']")
	private WebElement obeyLimitCheckBox;

	@FindBy(css = "span[data-id='autoSendStartDateControls'] > label:nth-of-type(1) > input[value='am']")
	private WebElement winningAm;

	@FindBy(css = "span[data-id='autoSendStartDateControls'] > label:nth-of-type(2) > input[value='pm']")
	private WebElement winningPm;

	@FindBy(css = "div:nth-child(1) span input[name='winningHour']")
	private WebElement winningHourElement;

	@FindBy(css = "input[name = 'winningMinute']")
	private WebElement winningMinuteElement;

	@FindBy(css = ".span6>select")
	private WebElement occurenceDropdown;

	@FindBy(id = "autoSendElapsedTime")
	private WebElement testDurationTimeTextBox;

	public static String preCompileMinutes;
	public static String preCompileHour;
	public static String preCompilemeridian;
	public static String hourString;

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

	public TestSchedulePage load() {
		navigateToScheduleTab();
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(dateBox),
				driver, 50)) {
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
			DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(pmRadioButton), driver, 20);
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
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(hourTextBox),
				driver, 20);
		hourTextBox.clear();
		hourString = Utils.getHourString();
		Reporter.log("Hour Enter is:" + hourString);
		hourTextBox.sendKeys(hourString);
	}

	/***
	 * This method is added to enter the current minutes
	 */
	public void enterCurrentMinutes() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(minuteTextBox),
				driver, 20);
		minuteTextBox.clear();
		String minString = Utils.getMinuteString();
		Reporter.log("Minute Enter is:" + minString);
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

	private void insertDateAutoWinning() {
		// TODO Auto-generated method stub
		Calendar cal = new GregorianCalendar();
		int dateOne = cal.get(Calendar.DATE);
		String month_Day = "" + dateOne;
		dateBoxWinning.clear();
		dateBoxWinning.click();
		for (WebElement td : autoWinnningtds) {
			if (td.getText().equals(month_Day)) {
				if (td.getAttribute("class").contains("ui-state-highlight")) {
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
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(hourTextBox),
				driver, 20);
		enterCurrentHour();
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(minuteTextBox),
				driver, 20);
		enterCurrentMinutes();
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(pmRadioButton),
				driver, 20);
		setAmPm();
	}

	/***
	 * This method is added to click enable button after saving.
	 * 
	 */
	public boolean enableAndConfirmSchedule() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(enableBtn),
				driver, 20);
		Actions inputClick = new Actions(driver);
		inputClick.moveToElement(enableBtn).click().perform();
		if (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(confirmBtn),
				driver, 20) != null)
			confirmBtn.click();

		return (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(disableBtn),
				driver, 20) != null);
	}

	/***
	 * This method is added to click on save button
	 */

	public boolean saveSchedule() {
		saveButton.click();
		Reporter.log("Ribbon Text Message for Test Schedule Page is:"
				+ getRibbonText(10) + " <br> ", true);
		return stepCompleted(5, 15);
	}

	/***
	 * This method is added to fill and save the schedule page
	 * 
	 * 
	 * @return displayed message
	 */
	public boolean fillSchedulePage(String autoSendcheck) {

		setDateTime();
		if (autoSendcheck.equalsIgnoreCase("True"))
			AutoSendWinningMessageSchedule();
		
		return saveSchedule();

	}

	/***
	 * This method is added to select occurrence.
	 * 
	 */
	public void checkObeyLimit() {
		if (!obeyLimitCheckBox.isSelected())
			obeyLimitCheckBox.click();
	}

	/***
	 * This method is added to select occurrence.
	 * 
	 */
	public void selectOccurence() {
		DriverUtility.selectDropDown(occurenceDropdown, "Occurs Once", 1);
	}

	/***
	 * This method is added to select AM/PM for preComplile Time.
	 * 
	 */
	private void winningSetAmPm() {
		if (Utils.getMeriDian().equals("am")) {
			winningAm.click();
		} else {
			winningPm.click();
			if (!winningPm.isSelected()) {
				winningPm.click();
			}
		}
	}

	/***
	 * This method is added to enter the current hours
	 * 
	 */

	public void enterCurrentWinninhHour() {

		winningHourElement.clear();
		String hourWinningString = CurrentWinningHour();
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(winningHourElement), driver, 20);
		Reporter.log("Hour Entered is:" + hourWinningString + "<br>", true);
		winningHourElement.sendKeys(hourWinningString);
	}

	private String CurrentWinningHour() {

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.HOUR, 2);
		String strHoursString = calendar.get(Calendar.HOUR_OF_DAY) % 12 + "";
		if (calendar.get(Calendar.HOUR_OF_DAY) == 12)
			strHoursString = "12";
		return (strHoursString.length() == 1) ? ("0" + strHoursString)
				: strHoursString;

	}

	/**
	 * This method is added to get AutoWinning Minute string in schedule page
	 * with +5 minutes
	 */
	public static String getwinningMinuteString() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, 20);
		String strMinuteString = calendar.get(Calendar.MINUTE) + "";
		return (strMinuteString.length() == 1) ? ("0" + strMinuteString)
				: strMinuteString;
	}

	/***
	 * This method is added to enter minutes in AutoWinning Minutes textBox.
	 * 
	 */
	private void enterWinningCurrentMinutes() {
		winningMinuteElement.clear();
		winningMinuteElement.sendKeys(getwinningMinuteString());
	}

	public void AutoSendWinningMessageSchedule()

	{
		checkObeyLimit();
		testDurationTimeTextBox.clear();
		insertDateAutoWinning();
		DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(winningHourElement), driver, 20);
		enterCurrentWinninhHour();
		enterWinningCurrentMinutes();
		winningSetAmPm();
		testDurationTimeTextBox
				.sendKeys(PagesHelper.MULTIVARIANT_SCHEDULE_TESTSCHEDULETIME);
	}

}