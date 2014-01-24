/**File name : TestTargetPage.java
 * @Description: This Java Class is used to fill the Test Target page. 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @since completed by 12/4/13
 * @Version History
 * Version name Updated By Reason / Comments
 *  
 * */

package com.yesmail.qa.pageobjects.mvt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.DriverUtility.CLICK_STRATEGY;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

public class TestTargetPage extends MvtBase {

	private WebDriver driver;

	/***
	 * Page Elements for TestTarget Class
	 */

	@FindBy(css = ".segment_group>div:nth-of-type(2)>div:nth-of-type(2) select")
	private WebElement subStatusDropdown;

	@FindBy(css = ".targetActions button:nth-of-type(2)")
	private WebElement saveGetCount;

	@FindBy(css = "input.clearable")
	private WebElement attributeFilterInput;

	@FindBys({ @FindBy(css = ".m-attributesList") })
	private List<WebElement> attributeList;

	@FindBy(css = "input.string-value")
	private WebElement segmentInputTextBox;

	@FindBy(css = "div.segments div:nth-child(2).segment")
	private WebElement segmentE1;

	/***
	 * Constructor to initialize page objects and navigate to page URL
	 * 
	 * @param driver
	 * @param pageUrl
	 * @author sangeetap
	 */
	public TestTargetPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public TestTargetPage load() {
		navigateToTargetTab();
		return this;
	}

	public boolean isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended
				.elementsToBeClickable(attributeFilterInput), driver, 50)) {
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
		}
		return true;
	}

	/**
	 * This method is added to select the subscription status drop down value
	 * 
	 * @param subscription
	 *            status
	 */
	public void selectSubscriptionStatus(String subscription) {
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(subStatusDropdown),
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
				ExpectedConditions.elementToBeClickable(attributeFilterInput),
				driver, 10);
		int i = 1;
		attributeFilterInput.sendKeys(selectAttribute);

		for (WebElement elementToClick : attributeList) {
			elementToClick = driver.findElement(By
					.cssSelector("div.mAttr:nth-child(" + i + ")"));
			if ((elementToClick.getText().trim())
					.equalsIgnoreCase(selectAttribute)) {
				DriverUtility.doubleClick(elementToClick, driver,
						CLICK_STRATEGY.USING_ACTION);
				break;
			} else
				i++;
		}
	}

	/***
	 * This method is added to get the count for the target attribute
	 * 
	 * @param - attribute Name, attribute Value, Subscription Status
	 * @author sangeetap
	 */
	public boolean clickSaveGetCount(String attributeName,
			String attributeValue, String subscriptionStatus) {
		selectSubscriptionStatus(subscriptionStatus);
		filterSelect(attributeName);
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(segmentE1), driver, 10);

		WebElement segmentHeaderText = driver
				.findElement(By
						.cssSelector("div.segments div:nth-child(2).ui-draggable>div.header"));
		if (segmentHeaderText.getText().equalsIgnoreCase(attributeName)) {
			segmentInputTextBox.sendKeys(attributeValue);

			saveGetCount.click();
			Reporter.log("Ribbon Text Message for Test Target Page is:"
					+ getRibbonText(10) + "<br>", true);
		}
		return stepCompleted(4, 10);
	}

}
