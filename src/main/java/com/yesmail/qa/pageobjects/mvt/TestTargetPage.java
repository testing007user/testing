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

import com.yesmail.qa.pageobjects.PagesHelper;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.DriverUtility.CLICK_STRATEGY;
import com.yesmail.qa.framework.exception.FrameworkException;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TestTargetPage extends MvtBase {


	/***
	 *  Page Elements for TestTarget Class
	 */
	
	@FindBy(css = ".targetActions button:nth-of-type(2)")
	private WebElement targetButton;

	@FindBy(css = ".segment_group>div:nth-of-type(2)>div:nth-of-type(2) select")
	private WebElement subStatusDropdown;

	@FindBy(css = ".targetActions button:nth-of-type(2)")
	private WebElement saveGetCount;

	@FindBy(css = ".targetActions button:nth-of-type(1)")
	private WebElement saveTarget;

	@FindBy(css = "input.clearable")
	private WebElement attributeFilterInput;

	// List of attribute
	@FindBys({ @FindBy(css = ".m-attributesList") })
	private List<WebElement> attributeList;

	//
	@FindBy(css = "input.string-value")
	private WebElement emailSegMentInput;

	@FindBy(css = "div.header")
	private WebElement segmentHeader;
	
	//Has Email and Subscription status segment css
	@FindBy(css = "div.segments div:nth-child(2).segment")
	private WebElement segmentE1;

	//Has Email and Subscription status segment header css
	@FindBy(css = "div.segments div:nth-child(2).ui-draggable>div.header")
	private WebElement segmentE1Header;

	private WebDriver driver;



/***
 * Constructor to initalise page objects and navigate to page url
 * @param driver
 * @param pageUrl
 * @author sangeetap
 */
	public TestTargetPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public TestTargetPage load()
	{
		navigateToTargetTab();
		return this;
	}
	
	public boolean isLoaded()
	{
		if(null == DriverUtility.waitFor(elementToBeClickable(By.cssSelector("input.clearable")), driver,50))
		{
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
		}
		return true;
	}

	/***
	 * This mehtod added to select subscription satus from subscription status drop down
	 */
	public void selectSubsStatus() {
		DriverUtility.selectDropDown(subStatusDropdown,
				PagesHelper.MULTIVARIATE_TARGET_SUBSCRIPTION_STATUS, 1);
	}

	/***
	 * This mehtod added to search the given attribute and double click on the same 
	 * @author sangeetap
	 */
	
	public void filterSelect() {
		int i = 1;
		attributeFilterInput
				.sendKeys(selectAttr.attrString.toString());
		attributeList.size();
		System.out.println(attributeList.size());
		for (WebElement elementToClick : attributeList) {
			elementToClick = driver.findElement(By
					.cssSelector("div.mAttr:nth-child(" + i + ")"));
			if ((elementToClick.getText())
					.equalsIgnoreCase(selectAttr.attrString.toString())) {
				DriverUtility.doubleClick(elementToClick, driver, CLICK_STRATEGY.USING_ACTION);
//				Actions inputClick = new Actions(driver);
//				inputClick.doubleClick(elementToClick).perform();
				break;
			}
			else
				i++;

		}

	}
	/***
	 * This enum selectAttr class is added to list attribute to be searched
	 */

	public enum selectAttr {
		eMail, attrString
	}
	

	/***
	 * This mehtod added to get the count for the target attribute
	 * 
	 * @author sangeetap
	 */
	public void clickSaveGetCount(selectAttr selectAttr) {

		selectSubsStatus();
		filterSelect();
		DriverUtility.waitforElementDisplay(driver, segmentE1, 30);
		if (segmentE1Header.getText().equalsIgnoreCase(
				selectAttr.toString())) {
			
			switch (selectAttr) {
			case eMail:
				emailSegMentInput
						.sendKeys(PagesHelper.MULTIVARIATE_TARGET_FILTER_ATTRI_EMAIL);
				break;
			case attrString:
				emailSegMentInput
						.sendKeys(PagesHelper.MULTIVARIATE_TARGET_FILTER_ATTRI_STRING);
			default:
				break;
			}
		}
		saveGetCount.click();
	}
	
}
