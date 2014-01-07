/** File Name: SubscribersPage.java  
 * Description: This Java Class is 
 * used to create new Subscriber and search for the exisiting subscribers by userId/First name/Last Name/mobile number/
 * email/postal code/Distribution List fields
 *  
 * @author sangeetap
 * Version: Draft 1.0 
 * Date: 16 December 2013 
 * Version History 
 * Version name Updated By Reason / Comments
 * version: 1.1 Updated for below changes 
 * 1.Please check below elements 
 private String cssTemplateForDataAttributes = ".attributePanel"; 
 private String cssForSearchResults = "DataTables_Table_0"; 
 private String cssForSearchResultHeaders = ".dataTables_scrollHead"; 
 private String subscriberEmails = "#DataTables_Table_0 tbody tr td:nth-of-type(3)"; 

 These needs to be define as Webelements. 

 2.Please use .equals() for string comparison 

 */

package com.yesmail.qa.pageobjects.subscriber;

import java.util.List;
import java.util.UUID;
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
import com.yesmail.qa.pageobjects.facebook.FacebookContentPage;

public class SubscribersPage {

	// ** Variable declaration section

	private WebDriver driver;
	private String pageUrl;

	/** Element locator section */

	@FindBy(css = "div.btn-group button.ym-btn")
	private WebElement createNewSubscriberButton;

	@FindBy(css = "input[id='searchUserId']")
	private WebElement userIdInput;

	@FindBy(css = "input[id='searchFirstName']")
	private WebElement firstNameInput;

	@FindBy(css = "input[id='searchLastName']")
	private WebElement lastNameInput;

	@FindBy(id = "searchMobile")
	private WebElement mobileNumberInput;

	@FindBy(id = "searchEmail")
	private WebElement emailInput;

	@FindBy(id = "searchPostal")
	private WebElement postalCodeInput;

	@FindBy(id = "searchDistro")
	private WebElement secondaryListDropDown;

	@FindBy(css = "button[class='ym-btn ym-btn-primary']")
	private WebElement searchButton;

	@FindBy(css = "button[class='ym-btn clear']")
	private WebElement clearButton;

	@FindBys({ @FindBy(css = "tbody > tr > td:nth-of-type(3)") })
	private List<WebElement> searchedSubscriberEmail;

	@FindBy(css = "div[data-target='subscriberTable']")
	private WebElement subscriberTable;

	@FindBy(css = "div[data-target=subscriberDetails]")
	private WebElement subscriberDetailsSection;

	@FindBy(id = "inputReferral")
	private WebElement subscribedCheckBox;

	@FindBy(id = "inputEmail")
	private WebElement emailForNewSubscriber;

	@FindBy(id = "inputFirstName")
	private WebElement firstNameForNewSubscriber;

	@FindBy(id = "inputLastName")
	private WebElement lastNameForNewSubscriber;

	@FindBy(id = "inputPostal")
	private WebElement postalCodeForNewSubscriber;

	@FindBy(id = "inputEmailTypeHtml")
	private WebElement htmlEmailTypeRadioForNewSubscriber;

	@FindBy(id = "inputEmailTypeText")
	private WebElement textEmailTypeRadioForNewSubscriber;

	@FindBy(id = "inputMobile")
	private WebElement mobileNumberForNewSubscriber;

	@FindBy(id = "divisionMembership")
	private WebElement divisionMembershipForNewSubscriber;

	@FindBy(id = "divisionSubscription")
	private WebElement divisionSubscriptionForNewSubscriber;

	@FindBy(id = "mobileMembership")
	private WebElement mobileMembershipForNewSubscriber;

	@FindBy(id = "mobileSubscription")
	private WebElement mobileSubscriptionForNewSubscriber;

	@FindBy(css = "button[class='ym-btn ym-btn-primary save pull-right']")
	private WebElement saveButton;

	@FindBy(css = "button[class='ym-btn cancel pull-right']")
	private WebElement cancelButton;

	@FindBy(css = "form div:nth-child(1) span:nth-of-type(1)")
	private WebElement lastModifiedTime;

	@FindBy(css = "form div:nth-child(1) span:nth-of-type(2)")
	private WebElement lastSubscribedTime;

	@FindBy(css = "form div:nth-child(1) span:nth-of-type(3)")
	private WebElement lastUnsubscribedTime;

	@FindBy(css = ".attributePanel")
	private WebElement cssTemplateForDataAttributes;

	@FindBy(css = "DataTables_Table_0")
	private WebElement cssForSearchResults;

	@FindBy(css = ".dataTables_scrollHead")
	private WebElement cssForSearchResultHeaders;

	@FindBy(css = "#DataTables_Table_0 tbody tr td:nth-of-type(3)")
	private WebElement subscriberEmails;

	/**
	 * Constructor section
	 * 
	 * @return
	 */
	public SubscribersPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}

	public SubscribersPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {

		if (null == DriverUtility.waitFor(ExpectedConditions
				.elementToBeClickable(createNewSubscriberButton), driver, 50))
			throw new FrameworkException(SubscribersPage.class.getName()
					+ " is not loaded in 50 seconds");

	}

	/***
	 * This method is added to fill user id
	 * 
	 * @author sangeetap
	 */
	public void fillUserId() {
		userIdInput.clear();
		userIdInput.sendKeys("236703");
	}

	/***
	 * This method is added to fill first name
	 * 
	 * @author sangeetap
	 */
	public void fillFirstName() {
		clearButton.click();
		firstNameInput.sendKeys("sangeeta");
	}

	/***
	 * This method is added to fill last name
	 * 
	 * @author sangeetap
	 */
	public void fillLastName() {
		clearButton.click();
		firstNameInput.sendKeys("Last Name");
	}

	/***
	 * This method is added to fill mobile number
	 * 
	 * @author sangeetap
	 */
	public void fillMobile() {
		clearButton.click();
		mobileNumberInput.sendKeys("1234566734");
	}

	/***
	 * This method is added to fill email
	 * 
	 * @author sangeetap
	 */
	public void fillEmail() {
		clearButton.click();
		String strSubscriberEmail = "postqa" + UUID.randomUUID() + "@yahoo.com";
		emailInput.sendKeys(strSubscriberEmail);
	}

	/***
	 * This method is added to fill postal code
	 * 
	 * @author sangeetap
	 */
	public void fillPostalCode() {
		clearButton.click();
		postalCodeInput.sendKeys("123457");
	}

	/***
	 * This method is added to select distribution list
	 */
	public void selectDistributionList(String option) {
		DriverUtility.selectDropDown(secondaryListDropDown, option, 2);
	}

	/**
	 * This method is added to click on search button
	 */
	public void clickSearchButton() {
		searchButton.click();
	}

	/***
	 * This method is added to search subscriber by Email
	 * 
	 * @param (subscriberEmail)
	 * @return true-subscriber found/ false-subscriber not found
	 * @author sangeetap
	 */
	public boolean searchSubscriber(String subscriberEmail) {

		DriverUtility.waitforElementDisplay(driver, subscriberTable, 10);
		for (WebElement emailfound : searchedSubscriberEmail) {
			if (emailfound.getText().equals(subscriberEmail))
				break;
		}
		return true;
	}

	/***
	 * This method is added to save/create New Subscriber
	 * 
	 * @author sangeetap
	 **/
	public boolean CreateNewSubscriber() {
		createNewSubscriberButton.click();
		DriverUtility.waitforElementDisplay(driver, subscriberDetailsSection,
				20);
		String strNameEmail = Utils.getUniqueName("postqa", 25);
		String subscriberEmail = strNameEmail + "@yahoo.com";
		emailForNewSubscriber.sendKeys(subscriberEmail);
		firstNameForNewSubscriber.sendKeys("First Name");
		lastNameForNewSubscriber.sendKeys("Last Name");
		postalCodeForNewSubscriber.sendKeys("123456");
		htmlEmailTypeRadioForNewSubscriber.click();
		DriverUtility.selectDropDown(divisionMembershipForNewSubscriber,
				PagesHelper.EMAIL_DIVISION, 1);
		DriverUtility.selectDropDown(divisionSubscriptionForNewSubscriber,
				PagesHelper.EMAIL_DIVISION, 1);

		saveButton.click();
		DriverUtility.waitforElementDisplay(driver, subscriberTable, 10);
		return searchSubscriber(subscriberEmail);
	}

}
