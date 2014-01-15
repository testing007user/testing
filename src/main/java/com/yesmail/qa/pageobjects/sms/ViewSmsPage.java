/***
 * File : ViewSmsPage.java
 * Description : This mehtod is added to verify the status of the created message ID
 * @author sangeetap
 * Version: Draft 1.0 
 * @since Completed 10th December, 2013
 * Version History  
 * Version name Updated By Reason / Comments 
 *  
 * */

package com.yesmail.qa.pageobjects.sms;

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

public class ViewSmsPage {

	@FindBys({ @FindBy(css = "tbody > tr > td > a") })
	private List<WebElement> masterIds;

	@FindBys({ @FindBy(css = "table.dataTable > tbody > tr") })
	private List<WebElement> trCollections;

	private String pageUrl;

	private WebDriver driver;

	/***
	 * Constructior for ViewSmsPage class
	 */

	public ViewSmsPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
		load();
		isLoaded();
	}

	public ViewSmsPage load() {
		driver.navigate().to(PagesHelper.URL+pageUrl);
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(trCollections),
				driver, 30))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");

	}

	/***
	 * This mehtod is added to verify the status of the created Master ID
	 * 
	 * @param masterID
	 *            - Master ID to search
	 * @return - MasterId Found(True/False)
	 */
	public boolean verifySmsStatus(String masterID, String expectedStatus) {

		int index;
		String statusCol = null;
		boolean mmIDFound = false;
		boolean expStatus = false;
		int retryCount = 0;
		long startTime = System.currentTimeMillis() / 1000;
		long stopTime = startTime + 300;

		while (System.currentTimeMillis() / 1000 <= stopTime) {
			for (index = 0; index < trCollections.size(); index++) {
				if (masterIds.get(index).getText().equalsIgnoreCase(masterID)) {
					mmIDFound = true;
					statusCol = driver.findElement(
							By.cssSelector("tbody > tr:nth-of-type("
									+ (index + 1) + ") >"
									+ " td:nth-of-type(5) > span")).getText();

					if (statusCol.trim()
							.equalsIgnoreCase(expectedStatus.trim())) {
						expStatus = true;
						break;
					}
				}
			}
			if (!mmIDFound) {
				retryCount++;
			}

			if (expStatus || retryCount == 3) {
				break;
			}
			driver.navigate().refresh();
			DriverUtility.waitFor(ExpectedConditionExtended
					.elementToBeClickable(trCollections), driver, 30);
		}
		return mmIDFound;
	}

}
