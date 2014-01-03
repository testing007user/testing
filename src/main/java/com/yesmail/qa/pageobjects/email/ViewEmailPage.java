/***
 * File : ViewEmailPage.java
 * Description : Page object class for ViewEmailPage functionality 
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

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class ViewEmailPage extends BasePage {

	private WebDriver driver;
	private String pageUrl;

	@FindBys({ @FindBy(css = "div:nth-child(2) table tbody tr") })
	private List<WebElement> trCollections;

	@FindBy(css = ".dataTable tbody")
	private WebElement tableBody;

	@FindBy(css = "table tbody tr td:nth-child(1)")
	private List<WebElement> jobIdTds;

	/**
	 * Constructor section
	 * 
	 */
	public ViewEmailPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);

	}

	/***
	 * This method is added to load the page.
	 */
	public ViewEmailPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	/***
	 * This method is added to verify whether page is loaded.
	 */
	public void isLoaded() {
		if (null == DriverUtility.waitFor(elementToBeClickable(tableBody),
				driver, 50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
	}

	/***
	 * This mehtod is added to verify the status of the created Email job
	 * 
	 * @param jobId
	 *            - job number to search
	 * 
	 * @param label
	 *            - status of Email Master FINISHED/ERROR
	 * 
	 * @return - Expected Master Status Found (True/False)
	 */
	public boolean verifyEmailMasterStatus(String jobId, String expectedStatus) {
		int index;
		boolean jobFound = false;
		boolean expStatus = false;
		int retryCount = 0;
		long startTime = System.currentTimeMillis() / 1000;
		long stopTime = startTime + 300;

		while (System.currentTimeMillis() / 1000 <= stopTime) {
			DriverUtility.waitFor(
					ExpectedConditions.elementToBeClickable(tableBody), driver,
					30);

			for (index = 0; index < trCollections.size(); index++) {
				if (jobIdTds.get(index).getText().equalsIgnoreCase(jobId)) {
					jobFound = true;
					WebElement statusCol = driver
							.findElement(By
									.cssSelector("div:nth-child(2) table tbody tr:nth-of-type("
											+ (index + 1)
											+ ")> td:nth-of-type(3) span"));
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
			DriverUtility.waitFor(
					ExpectedConditions.elementToBeClickable(tableBody), driver,
					30);
		}
		return expStatus;
	}

}
