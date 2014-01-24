package com.yesmail.qa.pageobjects.sms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

public class SmsSummaryPage extends SmsBasePage {

	private WebDriver driver;

	@FindBy(css = "td[data-target='eligibleCount']")
	private WebElement eligibleRecipients;

	@FindBy(css = "td[data-target='lastCountTime']")
	private WebElement countPerformed;

	@FindBy(css = "div[data-target='schedulingSection']>table> tbody>tr>td:nth-of-type(2)")
	private WebElement deliveryDate;

	@FindBy(css = "td[data-target='shortCode']")
	private WebElement headerShortCode;

	@FindBy(css = "div[data-target='contentSection']>table> tbody>tr>td:nth-of-type(2)")
	private WebElement contentText;

	@FindBy(css = "td[data-target='targetingSQL']")
	private WebElement advancedTargetingSql;

	@FindBy(css = "div[data-target='masterContent']")
	private WebElement masterSummary;

	/***
	 * Contructor
	 * 
	 * @param driver
	 * @param pageUrl
	 */

	public SmsSummaryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public SmsSummaryPage load() {
		navigateToSummary();
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(masterSummary),
				driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
	}

	/***
	 * This method is added to get target Eligible Recipients
	 * 
	 * @return Eligible Recipient Count
	 * @author sangeetap
	 */
	public String targetEligibleRecipients() {

		DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(eligibleRecipients), driver, 20);
		return eligibleRecipients.getText();
	}

	/***
	 * This method is added to get Count Performed Time
	 * 
	 * @return Time of Count Performed
	 * @author sangeetap
	 */
	public String targetCountPerformed() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(countPerformed),
				driver, 20);
		return countPerformed.getText();
	}

	/***
	 * This method is added to get Scheduled Delivery Date
	 * 
	 * @return Scheduled Delivery Date
	 * @author sangeetap
	 */
	public String schedulingDeliveryDate() {
		String strDeliverDate = deliveryDate.getText();

		String test[] = strDeliverDate.split(", ");
		String strSplitByGmt[] = test[1].split(" GMT");
		String strSplitBySymbol[] = strSplitByGmt[0].split(":");
		String strDate[] = strSplitBySymbol[0].split("\\s+12");
		String strFinalDate = strDate[0];
		
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("MMM dd yyyy").parse(strFinalDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		java.text.DecimalFormat nft = new  
				java.text.DecimalFormat("#00.###");  
				nft.setDecimalSeparatorAlwaysShown(false); 
				
		String monthInt = nft.format(cal.get(Calendar.MONTH) + 1);
		int date = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);
		String strFormattedDate = monthInt+"/"+date+"/"+year;

		String strTime[] = strDeliverDate.split(", ");
		String strTime1[] = strTime[1].split(" GMT");
		String[] strTime2 = strTime1[0].split("\\s+");
		String strTimeFinal = (strTime2[3]);
		System.out.println(strTimeFinal);

		String strFormattedDateTime = strFormattedDate.concat(" ");
		String strFinalFormattedDate = strFormattedDateTime
				.concat(strTimeFinal);

		return strFinalFormattedDate;

	}

	/***
	 * This method is added to get Short Code Value
	 * 
	 * @return Short Code Value
	 * @author sangeetap
	 */
	public String shortCodeHeader() {
		DriverUtility
				.waitFor(ExpectedConditionExtended
						.elementToBeClickable(headerShortCode), driver, 30);
		return headerShortCode.getText();

	}

	/***
	 * This method is added to get Content Message
	 * 
	 * @return Short Code Value
	 * @author sangeetap
	 */
	public String contentTextmessage() {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(contentText),
				driver, 20);
		return contentText.getText();

	}

	/***
	 * This method is added to get targeted SQL
	 * 
	 * @return SQL Section Present(True/False)
	 * @author sangeetap
	 */
	public boolean advancedTargetingSQL() {
		return (DriverUtility.waitFor(ExpectedConditionExtended
				.elementToBeClickable(advancedTargetingSql), driver, 10) != null);
	}

}
