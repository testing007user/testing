package com.yesmail.qa.pageobjects.subscriber;
/** File Name: SubscribersPage.java  
 * Description: This Java Class is 
 * used to create new Subscriber and search for the exisiting subscribers by userId/First name/Last Name/mobile number/
 * email/postal code/Distribution List fields
 *  
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * Date: 22/11/2013 
 * Version History 
 * version: 1.1 Updated for below changes 
 * 1.Please check below elements 
private String cssTemplateForDataAttributes = ".attributePanel"; 
private String cssForSearchResults = "DataTables_Table_0"; 
private String cssForSearchResultHeaders = ".dataTables_scrollHead"; 
private String subscriberEmails = "#DataTables_Table_0 tbody tr td:nth-of-type(3)"; 

These needs to be define as Webelements. 

2.Please use .equals() for string comparison 

 * Version name Updated By Reason / Comments */


import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.PagesHelper;

public class SubscribersPage {

	// ** Variable declaration section


	private  WebDriver driver;
	private String pageUrl;

	/** Element locator section */
	
	@FindBy(css = "button[class='ym-btn ym-btn-secondary alternative create']")
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

	// * Crate New Subscriber Webelement

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

	@FindBy(css ="DataTables_Table_0")
	private WebElement cssForSearchResults;

	@FindBy(css = ".dataTables_scrollHead" )
	private WebElement cssForSearchResultHeaders;

	@FindBy(css="#DataTables_Table_0 tbody tr td:nth-of-type(3)")
	private WebElement subscriberEmails;

	
	/**
	 * Constructor section
	 * 
	 * @return
	 */
	public SubscribersPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(this.driver, this);
	//	load();
	//	isLoaded();
	}
	
	public void load()
	{
		driver.get(PagesHelper.URL+ pageUrl);
		
	}
	
	public void isLoaded()
	{
	
		if(null == DriverUtility.waitFor(ExpectedConditions.elementToBeClickable(createNewSubscriberButton),driver,50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds ");
	}

	
	/***
	 * This method is added to search specific susbciber by User Id/ First Name /Last Name/Mobile Number/Email/Distribution List... 
	 * @param (names = "label", description = "field label to")
	 * @param (names = "textToEnter")
	 * 
	 * @author Sangeeta Pote
	 * @since Completed on 22th Nov 2013
	 */
	public void enterTextForSearch(String label, String textToEnter) {
		clearButton.click();
		if (label.equalsIgnoreCase("User Id"))
			userIdInput.sendKeys(textToEnter);
		else if (label.equalsIgnoreCase("First Name"))
			firstNameInput.sendKeys(textToEnter);
		else if (label.equalsIgnoreCase("Last Name"))
			lastNameInput.sendKeys(textToEnter);
		else if (label.equalsIgnoreCase(" Mobile Number"))
			mobileNumberInput.sendKeys(textToEnter);
		else if (label == "Email")
			emailInput.sendKeys(textToEnter);
		else if (label.equalsIgnoreCase("Distribution List"))
			secondaryListDropDown.click();
		searchButton.click();
	}

	
	/***
	 * This method is added to click on the 'Create New Subscriber' button to takes to 
	 * create new subscriber page
	 * 
	 * @author Sangeeta Pote
	 * @since Completed on 22th Nov 2013
	 */
	public void clickCreateNewSubscriberbtn() {
		//DriverUtility.waitforElementDisplay(driver, createNewSubscriberButton, 20,"clickCreateNewSubscriberbtn :" +createNewSubscriberButton.toString()  );
		createNewSubscriberButton.click();
		//PageFactory.initElements(driver, this);
	}

	
	/***
	 * This method is added to save New Subscriber
	 * 
	 * @author Sangeeta Pote
	 * @since Completed on 22th Nov 2013
	 */
	public void saveNewSubscriber() {

		String subscriberEmail = "postqa" + UUID.randomUUID() + "@yahoo.com";
		emailForNewSubscriber.sendKeys(subscriberEmail);
		firstNameForNewSubscriber.sendKeys("First Name");
		lastNameForNewSubscriber.sendKeys("Last Name");
		postalCodeForNewSubscriber.sendKeys("123456");
		htmlEmailTypeRadioForNewSubscriber.click();
		// Need to get value from PagesHelper for DivisionMembership and
		// Division Subscription fields
		divisionMembershipForNewSubscriber.sendKeys("marketing");
		divisionSubscriptionForNewSubscriber.sendKeys("marketing");
		saveButton.click();
	}

}
