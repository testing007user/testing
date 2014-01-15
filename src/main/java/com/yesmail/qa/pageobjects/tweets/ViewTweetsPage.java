/***
 * File : ViewTweetsPage.java
 * Description : This java class is added to verify the status of the created tweets to be delivered
 * @author Ojan
 * 
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified as per review comments 
 *   
 */

package com.yesmail.qa.pageobjects.tweets;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.pageobjects.PagesHelper;

public class ViewTweetsPage {

	// View Tweet page web element allocation
	@FindBy(css = "div.ym-page-content header form select")
	private WebElement allTwitterMasterDropDown;

	@FindBy(css = "table tbody tr td:nth-child(1)")
	private List<WebElement> jobIdTds;

	@FindBys({ @FindBy(css = "div:nth-child(2) table tbody tr") })
	private List<WebElement> trCollections;

	@FindBy(css = ".dataTable tbody")
	private WebElement tableBody;

	private WebDriver driver;
	private String pageUrl;

	// Constructor

	public ViewTweetsPage(WebDriver driver, String pageUrl) {

		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(tableBody), driver,30))
			throw new FrameworkException(ViewTweetsPage.class.getName()
					+ "is not loaded");

	}

	public ViewTweetsPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	/**
	 * This method is added to select the all twitter matsters from the type
	 * drop down.
	 * 
	 * @param twitterMaster
	 *            - text to select
	 */
	public void selectViewType(String twitterMaster) {

		DriverUtility
				.selectDropDown(allTwitterMasterDropDown, twitterMaster, 5);
		DriverUtility.waitforElementDisplay(driver, tableBody, 30);
	}

	/***
	 * This method is added to verify the status of the created tweets to be
	 * delivered
	 * 
	 * @param jobId
	 *            - created tweet id
	 * @param expectedStatus
	 *            - expected status of the created tweet id
	 * @return
	 */

	public boolean verifyTweetMasterStatus(String jobId, String expectedStatus) {
		
		int index;
		boolean jobFound = false;
		boolean expStatus = false;
		int retryCount = 0;

		long startTime = System.currentTimeMillis() / 1000;

		long stopTime = startTime + 300;

		while (System.currentTimeMillis() / 1000 <= stopTime) {

			DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(trCollections),driver, 30);

			for (index = 0; index < trCollections.size(); index++) {

				if (jobIdTds.get(index).getText().equalsIgnoreCase(jobId)) {
					jobFound = true;
					WebElement statusCol = driver
							.findElement(By
									.cssSelector("div:nth-child(2) table tbody tr:nth-of-type("
											+ (index + 1) + ")> td span"));
					if (statusCol.getText().equalsIgnoreCase(expectedStatus)) {
						expStatus = true;
						break;
					}
				}
			}
			if (!jobFound) {
				retryCount++;
			}

			if (expStatus || retryCount == 3) {
				break;
			}
			driver.navigate().refresh();
			DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(trCollections),driver, 30);			
		}
		return expStatus;
	}

}
