/***
 * File : EmailSchedulePage.java
 * Description : Page object class for EmailSchedulePage functionality 
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *    
 */
package com.yesmail.qa.pageobjects.email;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;

public class EmailSchedulePage extends EmailBase {

	private WebDriver driver;
	public static String preCompileMinutes;
	public static String preCompileHour;
	public static String preCompilemeridian;

	@FindBy(css = "input[name = 'obeyDeliveryLimits']")
	private WebElement obeyLimitCheckBox;

	@FindBy(id = "startDatepicker")
	private WebElement dateBox;

	@FindBy(css = "button[data-action = 'saveSchedule']")
	private WebElement saveSchedule;

	@FindBy(css = "button[data-id = 'masterEnableButton']")
	private WebElement enableBtn;

	@FindBy(css = "input[name='startHour']")
	private WebElement hourElement;

	@FindBy(css = "input[name='startMinute']")
	private WebElement minute;

	@FindBy(css = "span[data-id=startDateControls] input[value='am']")
	private WebElement am;

	@FindBy(css = "span[data-id=startDateControls] input[value='pm']")
	private WebElement pm;

	@FindBy(css = "span[data-id=precompileDateControls] input[value='am']")
	private WebElement preAm;

	@FindBy(css = "span[data-id=precompileDateControls] input[value='pm']")
	private WebElement prePm;

	@FindBy(id = "precompileDatepicker")
	private WebElement precompileDate;

	@FindBy(css = "input[name = 'precompileHour']")
	private WebElement preCompileHourElement;

	@FindBy(css = "input[name = 'precompileMinute']")
	private WebElement preCompileMinuteElement;

	@FindBy(css = "input[value = 'prepareAtSpecific']")
	private WebElement preCompileRadioButton;

	@FindBy(css = ".span6>select")
	private WebElement occurenceDropdown;

	@FindBys({ @FindBy(css = "table.ui-datepicker-calendar td a") })
	private List<WebElement> dateList;

	@FindBy(css = ".confirm")
	private WebElement confirmBtn;

	@FindBy(css = ".btn-danger")
	private WebElement disableBtn;

	/**
	 * Constructor section
	 * 
	 */
	public EmailSchedulePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);

	}

	/***
	 * This method is added to load the page.
	 */
	public EmailSchedulePage load() {
		navigateToScheduleTab();
		return this;
	}

	/***
	 * This method is added to verify whether page is loaded.
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(dateBox), driver, 50))
			throw new FrameworkException(EmailEnvelopePage.class.getName()
					+ " is not loaded");
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

		for (WebElement date : dateList) {
			if (date.getText().equals(month_Day)) {
				date.click();
				break;
			}
		}
	}

	/***
	 * This method is added to enter Current Hour.
	 * 
	 * @return
	 */
	private void enterCurrentHour() {
		hourElement.clear();
		hourElement.sendKeys(Utils.getHourString());
	}

	/***
	 * This method is added to enter Current Minutes.
	 * 
	 * @return
	 */
	private void enterCurrentMinutes() {
		minute.clear();
		minute.sendKeys(Utils.getMinuteString());
	}

	/***
	 * This method is added to select AM/PM value based on the current time.
	 * 
	 * @return
	 */
	private void setAmPm() {
		if (Utils.getMeriDian().equals("am")) {
			am.click();
		} else {
			pm.click();
			if (!pm.isSelected()) {
				pm.click();
			}
		}
	}

	/***
	 * This method is added to Save schedule by clicking the save button.
	 * 
	 * @return step completed Icon present(True/False)
	 */
	public boolean saveScheduleButton() {
		saveSchedule.click();
		getRibbonText(10);
		return stepCompleted(4, 10);
	}

	/***
	 * This method is added to click enable button after saving.
	 * 
	 */
	public void enableAndConfirmSchedule() {
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(enableBtn), driver, 10);
		enableBtn.click();
		if (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(confirmBtn), driver, 5) != null)
			confirmBtn.click();
		if (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(confirmBtn), driver, 5) != null)
			confirmBtn.click();

		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(disableBtn), driver, 5);
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
	private void preCompileSetAmPm() {
		if (preCompilemeridian.equals("am")) {
			preAm.click();
		} else {
			prePm.click();
			if (!prePm.isSelected()) {
				prePm.click();
			}
		}
	}

	/***
	 * This method is added to select preComplile Radio Button.
	 * 
	 */
	private void selectPrecompileRadioButton() {
		if (!preCompileRadioButton.isSelected())
			preCompileRadioButton.click();
	}

	/***
	 * This method is added to enter hour in PreCompile Hour textBox.
	 * 
	 */
	private void enterCurrentPreCompileHour() {
		preCompileHourElement.clear();
		preCompileHourElement.sendKeys(preCompileHour);
	}

	/***
	 * This method is added to enter minutes in PreCompile Minutes textBox.
	 * 
	 */
	private void enterPreCompileCurrentMinutes() {
		preCompileMinuteElement.clear();
		preCompileMinuteElement.sendKeys(preCompileMinutes);
	}

	/***
	 * This method is added to set Formatted Date and Time.
	 * 
	 */
	public void setDateTime() {
		insertDate();
		enterCurrentHour();
		enterCurrentMinutes();
		setAmPm();
	}

	/***
	 * This method is added to fill the formatted date for preCompile.
	 * 
	 */
	public void preCompileSetformattedTime() {
		preCompileHour = Utils.getHourString();
		String minutes = Utils.getMinuteString();
		int mins = Integer.parseInt(minutes) - 0;
		preCompileMinutes = Integer.toString(mins);
		preCompileMinutes = preCompileMinutes + 5;
		preCompilemeridian = Utils.getMeriDian();
		selectPrecompileRadioButton();
		insertDateForPreCompile();
		enterCurrentPreCompileHour();
		enterPreCompileCurrentMinutes();
		preCompileSetAmPm();

	}

	/***
	 * This method is added to insert date for preCompile.
	 * 
	 */
	private void insertDateForPreCompile() {
		Calendar cal = new GregorianCalendar();
		int dateOne = cal.get(Calendar.DATE);
		String month_Day = "" + dateOne;
		precompileDate.clear();
		precompileDate.click();
		for (WebElement date : dateList) {
			if (date.getText().equals(month_Day)) {
				date.click();
				break;
			}
		}
	}

}