/***
 * File : CreateDistributionListPage.java
 * Description : This java class is used to create new distribution list 
 * @author ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated / Modified as per the review comments
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
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class CreateDistributionListPage {

	// Initializing WebElement

	@FindBy(css = "button.create")
	private WebElement createDistributionBtn;

	@FindBy(id = "seedlist")
	private WebElement seedlistRadioBtn;

	@FindBy(id = "testgroup")
	private WebElement testgroupRadioBtn;

	@FindBy(id = "txtListName")
	private WebElement listNameTextBox;

	@FindBy(css = "input.divisionsCheckBox")
	private WebElement divisionsCheckBox;

	@FindBy(css = "select.divisionSelectBox")
	private WebElement divisionSelectDropDownBox;

	@FindBy(id = "btnSaveList")
	private WebElement saveDistributionListBtn;

	@FindBy(id = "selectBoxDistList")
	private WebElement availableAddressesDropDownBox;

	@FindBy(css = "input[name='userId']")
	private WebElement userIdTextBox;

	@FindBy(css = "div.span3 input:nth-child(2)")
	private WebElement emailTextBox;

	@FindBy(css = "input[name='firstName']")
	private WebElement firstNameTextBox;

	@FindBy(css = "input[name='lastName']")
	private WebElement lastNameTextBox;

	@FindBy(css = "button.moveRight")
	private WebElement moveRightSingleArrow;

	@FindBy(css = "button.btn-success")
	private WebElement searchBtn;

	@FindBy(css = "table#avlUsersTable thead tr")
	private WebElement avlUsersTableheader;

	@FindBys({ @FindBy(css = "table#avlUsersTable tbody tr td:nth-child(1)") })
	private List<WebElement> tds;

	@FindBys({ @FindBy(css = "table#avlUsersTable tbody tr td:nth-child(2)") })
	private List<WebElement> emailAddressCell;

	private WebDriver driver;
	private String pageUrl;

	

	public CreateDistributionListPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}

	public CreateDistributionListPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded()

	{
		if (null == DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(By.id("seedlist")),
				driver, 30))
			throw new FrameworkException(DistributionListPage.class.getName()
					+ " is not loaded");

	}

	/**
	 * This method is added to navigate to the create distribution page
	 * 
	 * @author sangeetap
	 */
	public void navigateToCreateDistributionList() {
		createDistributionBtn.click();
	}

	/***
	 * This method is added to select the type of seed list
	 * 
	 * @author sangeetap
	 */

	public void selectTypeSeedList() {
		seedlistRadioBtn.click();
	}

	/***
	 * This method is added to select the type of test group list
	 */
	public void selectTypeTestGroup() {
		testgroupRadioBtn.click();
	}

	/***
	 * This method is added to enter the list name
	 * 
	 * @author sangeetap
	 * @return - created list name
	 */
	public String enterListName() {
		String strListName = Utils.getUniqueName("Distlist_");
		listNameTextBox.clear();
		listNameTextBox.sendKeys(strListName);
		return strListName;
	}

	/***
	 * This method is added to select all divisions check box
	 * 
	 * @author sangeetap
	 */
	public void selectAllDivisions() {
		divisionsCheckBox.click();
	}

	/**
	 * This method is added to select the division from diviosion drop down
	 * 
	 * @author sangeetap
	 */
	public void selectDivisionDropDown() {
		DriverUtility.selectDropDown(divisionSelectDropDownBox, "marketing", 1);
	}

	/**
	 * This method is added to select the search option form available addresses
	 * drop down
	 * 
	 * @author sangeetap
	 */
	public void selectAvailableAddresses() {
		DriverUtility.selectDropDown(availableAddressesDropDownBox,
				"Search for users to add...", 4);
	}

	/**
	 * This method is added to enter the user id
	 * 
	 * @author sangeetap
	 */
	public void enterUserIdforSearch(String userId) {
		userIdTextBox.sendKeys(/* "236703" */userId);
	}

	/**
	 * This method is added to create and save seed list
	 * 
	 * @author sangeetap
	 */

	public String createSeedList(String userEmail) {

		selectTypeSeedList();
		String strSeedList = enterListName();
		selectAllDivisions();
		selectDivisionDropDown();
		selectAvailableAddresses();
		DriverUtility.waitforElementDisplay(driver, emailTextBox, 20);
		emailTextBox.clear();
		emailTextBox.sendKeys(userEmail);
		searchBtn.click();
		DriverUtility.waitforElementDisplay(driver, avlUsersTableheader, 20);
		for (int index = 0; index < emailAddressCell.size();) {
			WebElement trEle = driver.findElement(By
					.cssSelector("table#avlUsersTable tbody tr:nth-child("
							+ (index + 1) + ") td:nth-child(2)"));
			if (trEle.getText().equalsIgnoreCase(userEmail))
				trEle.click();
			break;
		}
		moveRightSingleArrow.click();
		saveDistributionListBtn.click();
		return strSeedList;
	}

	/**
	 * This method is added to create and save test group list
	 * 
	 * @author sangeetap
	 */
	public String createTestGroupList(String userEmail) {

		selectTypeTestGroup();
		String testGroupList = enterListName();
		selectAllDivisions();
		selectDivisionDropDown();
		selectAvailableAddresses();
		// userIdTextBox.sendKeys(userEmail);
		DriverUtility.waitforElementDisplay(driver, emailTextBox, 10);
		emailTextBox.clear();
		emailTextBox.sendKeys(userEmail);
		searchBtn.click();
		DriverUtility.waitforElementDisplay(driver, avlUsersTableheader, 20);
		for (int index = 0; index < emailAddressCell.size();) {
			WebElement trEle = driver.findElement(By
					.cssSelector("table#avlUsersTable tbody tr:nth-child("
							+ (index + 1) + ") td:nth-child(2)"));
			if (trEle.getText().equalsIgnoreCase(userEmail))
				trEle.click();
			break;
		}
		moveRightSingleArrow.click();
		saveDistributionListBtn.click();
		return testGroupList;
	}

}
