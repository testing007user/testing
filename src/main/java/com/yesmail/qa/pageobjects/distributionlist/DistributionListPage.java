/***
 * File : DistributionListPage.java
 * Description : This java class is used to delete the respective list
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/Modified as per review comments
 *    
 */

package com.yesmail.qa.pageobjects.distributionlist;

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
import com.yesmail.qa.pageobjects.PagesHelper;

public class DistributionListPage {

	// Initializing WebElement

	@FindBy(css = "button.create")
	private WebElement createDistributionBtn;

	@FindBys({ @FindBy(css = "table tbody tr") })
	private List<WebElement> trCollections;

	@FindBy(css = "table thead tr")
	private WebElement tableHeader;

	@FindBy(css = "button.ym-btn:nth-child(2)")
	private WebElement confirmDeleteBtn;

	private WebDriver driver;
	private String pageUrl;

	
	// define the constructor
	
	public DistributionListPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}// end of constructor

	public DistributionListPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(ExpectedConditions
				.elementToBeClickable(By.cssSelector("table thead tr")),
				driver, 20))
			throw new FrameworkException(DistributionListPage.class.getName()
					+ " is not loaded");

	}

	/**
	 * This method is added to navigate to the create distribution page
	 * 
	 * @author sangeetap
	 */
	public void navigateToCreateDistributionPage() {
		createDistributionBtn.click();
	}

	/**
	 * This method is added delete the repective distribution list
	 * 
	 * @author sangeetap
	 * @return - return true if deleted / false if failed to delete
	 */
	public boolean deleteDistributionList(String ListToBeDeleted) {
		boolean listFound = false;
		for (int index = 0; index < trCollections.size(); index++) {

			WebElement tdCollection = driver.findElement(By
					.cssSelector("table tbody tr:nth-of-type(" + (index + 1)
							+ ") td:nth-of-type(1) a"));

			if (tdCollection.getText().equalsIgnoreCase(ListToBeDeleted)) {
				listFound = true;
				WebElement deleteCell = driver.findElement(By
						.cssSelector("table tbody tr:nth-of-type("
								+ (index + 1) + ") td:nth-of-type(5) a"));
				deleteCell.click();
				confirmDeleteBtn.click();
				break;
			}
		}
		return listFound;
	}
}
