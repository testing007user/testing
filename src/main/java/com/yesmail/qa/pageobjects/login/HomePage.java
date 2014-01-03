/***
 * File Name: HomePage.java Description: This Java Class is used click on learn
 * more button to see the help popup window
 * 
 * @author sangeetap
 * @version Draft 1.0
 * @since completed by 14/11/2013
 * @Version History
 * 
 * @Vesion: 1.1 Version name Updated By Reason / Comments Removed verification
 *          code for the ClickLearnMore method Added code in
 *          'navigateToAdminPage' method to return new Page Object for Admin
 *          Page, same changes done for the 'navigateToUsersPage' method to
 *          return new Page Object for Users Page
 * */

package com.yesmail.qa.pageobjects.login;

import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
public class HomePage extends BasePage {

	/***
	 * Element locator section
	 * */

	@FindBy(css = "a.logout")
	private WebElement linkLogOut;

	@FindBy(css = "li>a.firefinder-match")
	private WebElement tabUsers;

	@FindBy(css = "div:nth-child(2) a.learn-more")
	private WebElement linklearnMoreButton;

	@FindBy(css = "a[href='#adminDelivery']")
	private WebElement tabAdmin;

	@FindBy(css = "div.ym-footer-inner div:nth-child(2)")
	private WebElement lastLoadedElement;

	@FindBy(css = "select.company-select-dropdown")
	private WebElement companyDropDown;

	@FindBy(css = "header div form input")
	private WebElement searchTextBox;

	@FindBy(css = "header div form button")
	private WebElement searchButton;

	

	private WebDriver driver;

	/**
	 * Constructor section
	 * 
	 * @return
	 */

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}

	public boolean isLoaded() {
			if(null == DriverUtility.waitFor(elementToBeClickable(searchTextBox), driver, 50))
			{
				throw new FrameworkException(this.getClass().getName()
						+ " is not loaded in 50 seconds");
			}
			return true;
	}

	public HomePage load() {

		driver.navigate().to(PagesHelper.URL);
		return this;
	}

	/**
	 * This Method is added to navigate Users to Admin page after clicking on
	 * Admin Tab present at the top panel
	 * 
	 * @author sangeetap
	 * 
	 */

	public void navigateToAdminPage() {
		tabAdmin.click();
	}

	/**
	 * This Method is added to navigate Users to Users page after clicking on
	 * Users Tab present at the top panel
	 * 
	 * @author sangeetap
	 * 
	 */

	public void navigateToUsersPage() {
		tabUsers.click();

	}

	/**
	 * Description: This Method is added to log out user from application
	 * 
	 * @author sangeetap
	 * 
	 */

	public void logout() {
		linkLogOut.click();
	}

	/**
	 * Description: This Method is added to click on the learn more link
	 * 
	 * @author sangeetap
	 * 
	 */

	public boolean clickLearnMore() {
		boolean winHandle = false;
		
		DriverUtility.waitforElementDisplay(driver, linklearnMoreButton, 10);
		linklearnMoreButton.click();
		java.util.Set<String> windowID = driver.getWindowHandles();
		Iterator<String> iterator = windowID.iterator();

		String mainId = iterator.next();
		String newWinId = iterator.next();

		driver.switchTo().window(newWinId);
		if (driver.getCurrentUrl().equalsIgnoreCase(
				"https://qa2-platform.yesmail.com/#help"))// get String from
		{	
			winHandle=true;}
		// pagesHelper
			driver.close();
		driver.switchTo().window(mainId);
		
		return winHandle;

	}

	/***
	 * This method is added to select the company
	 * 
	 * @author sangeetap
	 */
	public void selectCompany(String visibleCompany) {
		DriverUtility.selectDropDown(companyDropDown,
				visibleCompany, 0);
	}

	/**
	 * This method is added to search MMID data
	 * 
	 * @author sangeetap
	 */

	public void searchMMID(String mmID) {
		searchTextBox.sendKeys(mmID);
		searchButton.click();
		// Search functionality is not implemented yet

	}

}
