package com.yesmail.qa.pageobjects.mvt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.PagesHelper;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
/***
 * This method is added to search the test and check the status of the
 * respective test
 * 
 * @author sangeetap since completed by 12/9/13
 * 
 */

public class ViewTestPage {

	@FindBy(css = "#mainContentArea div:nth-child(2) input")
	private WebElement searchTestInut;

	@FindBy(css = "#mainContentArea div:nth-child(1) select")
	private WebElement viewSelectDropDown;

	@FindBy(css = "table.dataTable>tbody>tr>td:nth-child(4)>span.status-disabled")
	private WebElement statusDisabledButton;

	@FindBy(css = "table.dataTable>tbody>tr>td:nth-child(4)>span.status-tests-generated")
	private WebElement statusTestsGeneratedButton;

	@FindBy(css = "table.dataTable>tbody>tr>td:nth-child(4)>span.status-completed")
	private WebElement statusCompletedButton;

	@FindBys({ @FindBy(css = "table.dataTable>tbody>tr>td") })
	private List<WebElement> searchtable;

	@FindBy(css = "table.dataTable>tbody>tr>td:nth-child(4)")
	private WebElement statusCol;

	private WebDriver driver;

	private String pageUrl;

	public ViewTestPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
		load();
		isLoaded();
	
	}	

	private ViewTestPage load() {
		driver.navigate().to(pageUrl);
		return this;
	}
	
	public boolean isLoaded() {
		if(null == DriverUtility.waitFor(visibilityOfElementLocated(By.cssSelector(".dataTables_scrollHead [aria-label~='Id:']")), driver, 50))
		{
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
		}
		return true;
	}

	/***
	 * This mehtod is added to search Test
	 * @return : status of Test	 
	 */
	public String searchTest() {
		String resultStatus = null;
		String searchEnter = PagesHelper.MULTIVARIATE_SEARCH_TEXT;
		searchTestInut.clear();
		searchTestInut.sendKeys(searchEnter);
		searchtable.size();
		
		for (int i = 1; i <= searchtable.size(); i++) {

			if (searchtable.get(i).getText()
					.equalsIgnoreCase(PagesHelper.MULTIVARIATE_SEARCH_TEXT)) {
				  resultStatus = driver.findElement(
						By.cssSelector("table.dataTable>tbody>tr:nth-child("+ i +")>td:nth-child(4) span"))
						.getAttribute("class");
				break;
				
			}

		}
		return resultStatus;
	}
	
}
