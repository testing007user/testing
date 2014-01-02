/**
 * File :SmsTargetPage.java
 * Description: This Java Class is a Target class for Targeting Consumers by
 * dragging and dropping the attributes
 * 
 * @author sangeetap
 * @since Completed on 10th December 2013
 * Version History  
 * 
 * Version name Updated By Reason / Comments 
 */

package com.yesmail.qa.pageobjects.sms;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SmsTargetPage extends SmsBasePage {

	// WebElement locators

	private WebDriver driver;

	@FindBy(css = ".targetActions button:nth-of-type(2)")
	private WebElement targetbutton;

	@FindBy(css = ".segment_group>div:nth-of-type(2)>div:nth-of-type(2) select")
	private WebElement subStatusDropdown;

	@FindBy(css = ".targetActions button:nth-of-type(2)")
	private WebElement saveGetCount;

	@FindBy(css = ".targetActions button:nth-of-type(1)")
	private WebElement saveTarget;

	@FindBy(css = "input.clearable")
	private WebElement attributeFilterInput;

	@FindBys({ @FindBy(css = ".m-attributesList") })
	private List<WebElement> attributeList;

	@FindBy(css = "input.string-value")
	private WebElement emailSegMentInput;

	// All segment header
	@FindBy(css = "div.header")
	private WebElement segmentHeader;

	// Has Mobile number locators
	@FindBy(css = "n0e0")
	private WebElement hasMobileNumber;

	@FindBy(css = "div#n0e0 select>option:nth-child(2)")
	private WebElement selectYesDropDown;

	@FindBy(css = "div#n0e0 span")
	private WebElement selectedYesDropDown;

	@FindBy(css = "div#n0e0 i")
	private WebElement iconRemoveEle;

	@FindBy(css = "div#e0")
	private WebElement requiredSegmentEle;

	// Has Email and Subscription status segment css
	@FindBy(css = "div.segments div:nth-child(2).segment")
	private WebElement segmentE1;

	// Has Email and Subscription status segment header css
	@FindBy(css = "div.segments div:nth-child(2).ui-draggable>div.header")
	private WebElement segmentE1Header;

	@SuppressWarnings("unused")
	private String pageUrl;

	/***
	 * Initialize constructor
	 * 
	 * @param driver
	 * @param pageUrl
	 */
	public SmsTargetPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public void load() {

		navigateToTarget();
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				elementToBeClickable(By.cssSelector("input.clearable")),
				driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
	}

	/***
	 * This enum selectAttrSMS class is added to list attribute to be searched
	 */
	public enum SELECT_SMS_ATTRIBUTE {
		eMail, attrString
	}

	/**
	 * This method is added to select the SMS subscription status drop down
	 * value
	 * 
	 * @param element
	 * @param visibleText
	 * @param defaultIndex
	 */
	public void selectSubscriptionStatus() {
		DriverUtility.selectDropDown(subStatusDropdown, "subscribed", 1);// get
																			// from
																			// pagesHelper
	}

	public String filterSelect(SELECT_SMS_ATTRIBUTE selectAttrSMS) {
		DriverUtility.waitforElementDisplay(driver, attributeFilterInput, 10);
		int i = 1;
		attributeFilterInput.sendKeys(selectAttrSMS.toString());
		attributeList.size();

		for (WebElement elementToClick : attributeList) {
			elementToClick = driver.findElement(By
					.cssSelector("div.mAttr:nth-child(" + i + ")"));
			if ((elementToClick.getText()).equalsIgnoreCase(selectAttrSMS
					.toString())) {
				Actions inputClick = new Actions(driver);
				inputClick.doubleClick(elementToClick).perform();
				break;
			} else
				i++;
		}
		DriverUtility.waitforElementDisplay(driver, segmentE1, 10);
		return (selectAttrSMS.toString());
	}

	/**
	 * This method is added to check the Has Mobile Number option as yes / no
	 * 
	 * @param check
	 *            - retrun true/false if yes/no
	 * 
	 * @return true/false
	 */

	public boolean selectMobileAsYes() {
		DriverUtility.waitforElementDisplay(driver, selectedYesDropDown, 20);

		if (null == DriverUtility.waitFor(ExpectedConditionExtended
				.invisibilityOfElementLocated(selectedYesDropDown), driver, 10)) {
			return true;
		} else {
			DriverUtility.selectDropDown(selectYesDropDown, "Yes", 1);
			return true;
		}
	}

	/***
	 * This mehtod added to get the count for the target attribute
	 * 
	 * @author sangeetap
	 */

	public void clickSaveGetCount(SELECT_SMS_ATTRIBUTE selectAttrSMS,
			String textToEnter) {
		String strAttString;
		boolean segmentInput = false;
		int j;
		DriverUtility.waitforElementDisplay(driver, segmentE1, 10);
		selectSubscriptionStatus();
		strAttString = filterSelect(selectAttrSMS);
		DriverUtility.waitforElementDisplay(driver, segmentE1, 30);

		for (j = 2; j < 5; j++) {

			WebElement segmentHeaderText = driver.findElement(By
					.cssSelector("div.segments div:nth-child(" + j
							+ ").ui-draggable>div.header"));
			if (segmentHeaderText.getText().equalsIgnoreCase(strAttString)) {

				switch (selectAttrSMS) {
				case eMail:
					emailSegMentInput.sendKeys(textToEnter);// //get from
					segmentInput = true; // pagesHelper
					break;
				case attrString:
					emailSegMentInput.sendKeys(textToEnter);// //get from
					segmentInput = true;
					break; // pagesHelper
				default:
					break;
				}
			}
		}
		if (segmentInput)

			saveGetCount.click();
	}

}
