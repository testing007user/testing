/***
 * File : ViewFacebookPage.java
 * Description : This java class is used to to verify the status of the created facebook post to be delivered
 * @author ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *   
 */
package com.yesmail.qa.pageobjects.facebook;

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

public class ViewFacebookPage {

	// View Facebook page web element allocation

	@FindBy(css = "div.ym-page-content header form select")
	private WebElement allFacebookMasterDropDown;

	@FindBy(css = "table tbody tr td:nth-child(1)")
	private List<WebElement> jobIdTds;

	@FindBys({ @FindBy(css = "div:nth-child(2) table tbody tr") })
	private List<WebElement> trCollections;

	@FindBy(css = ".dataTable tbody")
	private WebElement tableBody;

	private String pageUrl;

	private WebDriver driver;

	// Constructor
	public ViewFacebookPage(WebDriver driver, String pageUrl) {

		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(tableBody), driver, 30))				
			throw new FrameworkException(ViewFacebookPage.class.getName()
					+ "is not loaded");

	}

	public ViewFacebookPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	/***
	 * This method is added to slect the view type from drop down
	 * 
	 * @param twitterMaster
	 *            - select view type for Facebook Master
	 */
	public void selectViewType(String facebookMaster) {

		DriverUtility.selectDropDown(allFacebookMasterDropDown, facebookMaster,
				5);
		DriverUtility.waitforElementDisplay(driver, tableBody, 30);
	}

	/***
	 * This method is added to verify the status of the generated facebook post
	 * 
	 * @param jobId
	 *            - number id of the generated facebook post
	 * @param expectedStatus
	 *            - expected status of the generated post
	 * @return
	 */
	public boolean verifyFacebookMasterStatus(String jobId,
			String expectedStatus) {
		int index;
		boolean jobFound = false;
		boolean expStatus = false;
		int retryCount = 0;

		long startTime = System.currentTimeMillis() / 1000;

		long stopTime = startTime + 300;

		while (System.currentTimeMillis() / 1000 <= stopTime) {

			DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(trCollections), driver, 30);

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
			DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(trCollections), driver, 30);
		}
		return expStatus;
	}
}
