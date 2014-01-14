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
import org.testng.Reporter;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

public class SmsTargetPage extends SmsBasePage {

	// WebElement locators

	private WebDriver driver;

	@FindBy(css = ".segment_group>div:nth-of-type(2)>div:nth-of-type(2) select")
	private WebElement subStatusDropdown;

	@FindBy(css = ".targetActions button:nth-of-type(2)")
	private WebElement saveGetCount;

	@FindBy(css = "input.clearable")
	private WebElement attributeFilterInput;

	@FindBys({ @FindBy(css = ".m-attributesList") })
	private List<WebElement> attributeList;

	@FindBy(css = "div#n0e0 select>option:nth-child(2)")
	private WebElement selectYesDropDown;

	@FindBy(css = "div#n0e0 span")
	private WebElement selectedYesDropDown;

	@FindBy(css = "div.segments div:nth-child(2).segment")
	private WebElement segmentE1;

	@FindBy(css = "input.string-value")
	private WebElement segmentInputTextBox;

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

	public SmsTargetPage load() {
		navigateToTarget();
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended.
				elementToBeClickable(attributeFilterInput), driver, 50)) {

			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
		}
	}

	/**
	 * This method is added to select the subscription status drop down value
	 * 
	 * @param subscription
	 *            status
	 */
	public void selectSubscriptionStatus(String subscription) {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(subStatusDropdown),
				driver, 10);
		DriverUtility.selectDropDown(subStatusDropdown, subscription, 1);
	}

	/**
	 * This method is added to select the filter value from attribute list
	 * 
	 * @param selectAttribute
	 * 
	 */
	private void filterSelect(String selectAttribute) {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(attributeFilterInput),
				driver, 20);
		int i = 1;
		attributeFilterInput.sendKeys(selectAttribute);

		for (WebElement elementToClick : attributeList) {
			elementToClick = driver.findElement(By
					.cssSelector("div.mAttr:nth-child(" + i + ")"));
			if ((elementToClick.getText().trim())
					.equalsIgnoreCase(selectAttribute)) {
				Actions inputClick = new Actions(driver);
				inputClick.doubleClick(elementToClick).perform();
				break;
			} else
				i++;
		}
	}

	/**
	 * This method is added to check the has Email option as yes / no
	 * 
	 * @return true/false
	 */
	public boolean hasMobileNumberCheck() {

		if (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(selectedYesDropDown),
				driver, 20) != null) {
			return true;
		} else {
			if (DriverUtility.waitFor(
					ExpectedConditionExtended.elementToBeClickable(selectYesDropDown),
					driver, 10) != null)
				DriverUtility.selectDropDown(selectYesDropDown, "Yes", 1);
		}
		return true;
	}

	/***
	 * This mehtod added to get the count for the target attribute
	 * 
	 * @param - attribute Name, attribute Value, Subscription Status
	 * @author sangeetap
	 */
	public boolean clickSaveGetCount(String attributeName,
			String attributeValue, String subscriptionStatus) {
		// To do - For other attribute type values.
		selectSubscriptionStatus(subscriptionStatus);
		filterSelect(attributeName);
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(segmentE1), driver, 10);

		WebElement segmentHeaderText = driver
				.findElement(By
						.cssSelector("div.segments div:nth-child(2).ui-draggable>div.header"));
		if (segmentHeaderText.getText().equalsIgnoreCase(attributeName)) {
			segmentInputTextBox.sendKeys(attributeValue);

			saveGetCount.click();
			Reporter.log("Ribbon Text for TargetPage is: " + getRibbonText(10)
					+ "<br>");
		}
		return stepCompleted(3, 10);
	}
	
	/***
	 * This method will return the job number
	 * 
	 * @author sangeetap
	 */
	public String getMasterId() {
		String strloginUrl = driver.getCurrentUrl();
		
		String jobNum = driver.getCurrentUrl().substring(
				strloginUrl.lastIndexOf("#") + 1).replaceAll("[^0-9]", "");
		
		return jobNum;
	}

}
