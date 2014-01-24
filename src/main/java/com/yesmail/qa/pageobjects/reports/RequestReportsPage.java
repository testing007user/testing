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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class RequestReportsPage extends BasePage {

	
	private WebDriver driver;
	private String pageUrl;
	/**
	 * Initializing Page objects
	 */

	@FindBy(css = "table tbody tr:nth-of-type(1) td:nth-of-type(2)")
	private WebElement tableMasterIDMailingView;

	@FindBy(css = "select.reportTypeSelect")
	private WebElement reportTypeDropDown;

	@FindBy(css = "#mainContentArea div:nth-child(3) select.mailingViewSelect")
	private WebElement viewBy;

	@FindBy(css = "table.dataTable tr:nth-of-type(1) td input")
	private WebElement firstRowCheckBox;

	@FindBy(css = "div:nth-child(2) button:nth-of-type(2).moveRight")
	private WebElement singleSelectbtn;

	@FindBy(css = "label.radio:nth-of-type(1) input[name=runNow]")
	private WebElement runReportNowRadioButton;

	@FindBy(css = "div:nth-child(4) span:nth-of-type(1)>input.datepicker")
	private WebElement dateBox;

	@FindBy(css = "div:nth-child(3) button.save")
	private WebElement requestReportButton;

	@FindBys({ @FindBy(css = "select.availableOptions") })
	private List<WebElement> attributesList;	

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

	@FindBy(css = "div:nth-child(4) input.message")
	private WebElement messageId;
	
	@FindBy(css = "tbody > tr:nth-of-type(1) > td:nth-of-type(2).sorting_1")
	private WebElement reportId;	

	/**
	 * Initializing Constructor
	 */
	public RequestReportsPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public RequestReportsPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {		
		if (null == DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(reportTypeDropDown), driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
		
	}

	/**
	 * This method is added to select the report type visible text : Delivery
	 * and Response
	 * 
	 * @author sangeetap
	 */
	public void selectReportType(String visibleText) {

		DriverUtility.selectDropDown(reportTypeDropDown, visibleText, 0);
	}

	/**
	 * This method is added to select Mailing view
	 * 
	 * @author sangeetap
	 */

	public void selectMailingView(String visibleText) {
		DriverUtility.selectDropDown(viewBy, visibleText, 0);
		DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(tableMasterIDMailingView),driver,
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
	 * @return - generated report master id
	 */
	public String RequestRunReport(String visibleTextReportType,
			String visibleTextMailingView, String attributeText) {
		selectReportType(visibleTextReportType);
		selectMailingView(visibleTextMailingView);
		selectAttributes(attributeText);
		runReportNowRadioButton.click();
		clickRequestReport();
		return getMasterId();
	}

	/**
	 * This method is added to click on Request Report Button
	 * 
	 * @author sangeetap	 
	 */
	public void clickRequestReport(){
		requestReportButton.click();	
	}
	
	/**
	 * This method is added to get the report Id
	 * @return - reportId
	 * @author sangeetap	 
	 */
	public String getReportId(){
		return(reportId.getText());
		
	}

	/***
	 * This method is added to get the id of generated report
	 * 
	 * @return
	 */
	public String getMasterId() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(messageId),
				this.driver, 20);
		String strReportMsg = messageId.getText();
		String reportId = strReportMsg.substring(
				strReportMsg.lastIndexOf("#") + 1).replaceAll("[^0-9]", "");
		return reportId;
	}
}