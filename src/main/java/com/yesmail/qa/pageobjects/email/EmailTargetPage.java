/***
 * File : EmailTargetPage.java
 * Description : Page object class for EmailTargetPage functionality 
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

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.DriverUtility.CLICK_STRATEGY;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

public class EmailTargetPage extends EmailBase {

	private WebDriver driver;

	@FindBy(css = ".subscription-status-options")
	private WebElement subStatusDropdown;

	@FindBy(css = "input.clearable")
	private WebElement attributeFilterInput;

	@FindBys({ @FindBy(css = ".m-attributesList") })
	private List<WebElement> attributeList;

	@FindBy(css = "div.segments div:nth-child(2).segment")
	private WebElement segmentE1;

	@FindBy(css = ".targetActions button:nth-of-type(2)")
	private WebElement saveGetCount;

	@FindBy(css = "div#n0e0 span")
	private WebElement selectedYesDropDown;

	@FindBy(css = ".selected-values>div>select")
	private WebElement hasEmailDropDown;

	@FindBy(css = ".segments")
	private WebElement segments;

	@FindBy(css = "input.string-value")
	private WebElement segmentInputTextBox;


	/**
	 * Constructor section
	 * 
	 */
	public EmailTargetPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);

	}

	/***
	 * This method is added to load the page.
	 */
	public EmailTargetPage load() {
		navigateToTargetTab();
		return this;
	}

	/***
	 * This method is added to verify whether page is loaded.
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(segments),
				driver, 50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
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
	public void filterSelect(String selectAttribute) {
		DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(attributeFilterInput),
				driver, 10);
		int i = 1;
		attributeFilterInput.sendKeys(selectAttribute);

		for (WebElement elementToClick : attributeList) {
			elementToClick = driver.findElement(By
					.cssSelector("div.mAttr:nth-child(" + i + ")"));
			if ((elementToClick.getText().trim())
					.equalsIgnoreCase(selectAttribute)) {
				DriverUtility.doubleClick(elementToClick, driver,CLICK_STRATEGY.USING_ACTION);
				break;
			} else
				i++;
		}
	}

	/***
	 * This method added to get the count for the target attribute
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
			Reporter.log("Ribbon Text for TargetPage is: "+getRibbonText(10)+"<br>");
		}
		return stepCompleted(3, 10);
	}

	/**
	 * This method is added to check the has Email option as yes / no
	 * 
	 * @return true/false
	 */
	public boolean hasEmailCheck() {

		if (DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(selectedYesDropDown),
				driver, 10) != null) {
			return true;
		} else {
			if (DriverUtility.waitFor(
					ExpectedConditionExtended.elementToBeClickable(hasEmailDropDown),
					driver, 10) != null)
				DriverUtility.selectDropDown(hasEmailDropDown, "Yes", 1);
		}
		return true;
	}

}
