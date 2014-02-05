package com.yesmail.qa.pageobjects.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

public class EmailSummaryPage extends EmailBase {

	private WebDriver driver;

	@FindBy(css = "td[data-target='countStatus']")
	private WebElement countStatus;

	@FindBy(css = "td[data-target='countPerformedData']")
	private WebElement countPerformedData;

	@FindBy(css = "div[data-target='schedulingSection'] tr:nth-of-type(1) > td:nth-of-type(2)")
	private WebElement startDate;

	@FindBy(css = "div[data-target='envelopeSection'] tr:nth-of-type(1) > td:nth-of-type(2)")
	private WebElement division;

	@FindBy(css = "div[data-target='envelopeSection'] tr:nth-of-type(2) > td:nth-of-type(2)")
	private WebElement envelopeFrom;

	@FindBy(css = "div[data-target='envelopeSection'] tr:nth-of-type(10) > td:nth-of-type(2)")
	private WebElement encoding;

	@FindBy(css = ".contentSummary > iframe")
	private WebElement contentIframe;

	@FindBy(css = "html>body > table")
	private WebElement contentTable;

	@FindBy(css = "td[data-target='sql']")
	private WebElement sqlSection;

	@FindBy(css = "div[data-target='contentSection']>div>table")
	private WebElement contentSection;

	/**
	 * Constructor section
	 * 
	 */
	public EmailSummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	/***
	 * This method is added to load the page.
	 */
	public EmailSummaryPage load() {
		navigateToSummaryTab();
		return this;
	}

	/***
	 * This method is added to verify whether page is loaded.
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(contentSection),
				driver, 50))
			throw new FrameworkException(EmailEnvelopePage.class.getName()
					+ " is not loaded");
	}

	/***
	 * This method is added to get the Target Count.
	 * 
	 * @return : Target Count
	 */
	public String targetCount() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(countStatus),
				driver, 30);
		return countStatus.getText();
	}

	/***
	 * This method is added to get count Performed Data.
	 * 
	 * @return : count Performed Data
	 */
	public String countPerformedData() {
		return countPerformedData.getText();
	}

	/***
	 * This method is added to get Scheduled Start Date.
	 * 
	 * @return : Scheduled Start Date
	 */
	public String scheduledStartDate() {
		return startDate.getText();
	}

	/***
	 * This method is added to get the selected envelope division.
	 * 
	 * @return : Envelope Division
	 */
	public String envelopeDivision() {
		return division.getText();
	}

	/***
	 * This method is added to get the selected envelope From Text.
	 * 
	 * @return : From Text
	 */
	public String envelopeFromText() {
		return envelopeFrom.getText();
	}

	/***
	 * This method is added to get the selected envelope Encoding.
	 * 
	 * @return : Envelope Encoding
	 */
	public String envelopeEncoding() {
		return encoding.getText();
	}

	/***
	 * This method is added to verify Content Section.
	 * 
	 * @return : Content Section Present(True/False)
	 */
	public boolean verifyContentSection() {
		driver.switchTo().frame(contentIframe);
		return (DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(contentTable),
				driver, 20) != null);
	}

	/***
	 * This method is added to verify SQL Section.
	 * 
	 * @return : SQL Section Present(True/False)
	 */
	public boolean verifySqlSection() {
		if (DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(sqlSection),
				driver, 10) == null) {
			driver.navigate().refresh();
		}
		return (DriverUtility.waitFor(
				ExpectedConditionExtended.elementsToBeClickable(sqlSection),
				driver, 20) != null);
	}

}
