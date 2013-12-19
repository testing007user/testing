package com.yesmail.qa.pageobjects.imports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.yesmail.qa.framework.DriverUtility;

public class ImportHomePage {
	
	private WebDriver driver;
	private String pageUrl;

	// Owned by WebElement locator
	@FindBy(css = "div:nth-child(2).span3 span")
	private WebElement OwnedByDropDown;

	// Owned by drop down WebElement locators
	@FindBy(css = "div:nth-child(2).span3 span option:nth-of-type(1)")
	private WebElement OwnedByMeDropDown;

	@FindBy(css = "div:nth-child(2).span3 span option:nth-of-type(2)")
	private WebElement OwnedByAnyOneDropDown;

	// Type by WebElement locator
	@FindBy(css = "div:nth-child(1).span3 span")
	private WebElement typeByDropDown;

	// Type drop down WebElement locators
	@FindBy(css = "div:nth-child(1).span3 span option:nth-of-type(1)")
	private WebElement anyTypeOption;

	@FindBy(css = "div:nth-child(1).span3 span option:nth-of-type(2)")
	private WebElement importTypeOption;

	@FindBy(css = "div:nth-child(1).span3 span option:nth-of-type(3)")
	private WebElement countTaskTypeOption;

	// Status by WebElement locator
	@FindBy(css = "div:nth-child(3).span3 span")
	private WebElement statusByDropDown;

	// Status by drop down WebElement locators
	@FindBy(css = "div:nth-child(3).span3 span option:nth-of-type(1)")
	private WebElement AnyStatusOption;

	@FindBy(css = "div:nth-child(3).span3 span option:nth-of-type(2)")
	private WebElement requestedStatusOption;

	@FindBy(css = "div:nth-child(3).span3 span option:nth-of-type(3)")
	private WebElement inProgressStatusOption;

	@FindBy(css = "div:nth-child(3).span3 span option:nth-of-type(4)")
	private WebElement finishedStatusOption;

	@FindBy(css = "div:nth-child(3).span3 span option:nth-of-type(5)")
	private WebElement cancelledorStoppedStatusOption;

	@FindBy(css = "div:nth-child(3).span3 span option:nth-of-type(6)")
	private WebElement errorStatusOption;

	// Requested by WebElement locator
	@FindBy(css = "div:nth-child(4).span3 span")
	private WebElement requestedByDropDown;

	// Requested by drop down WebElement locators
	@FindBy(css = "div:nth-child(4).span3 span option:nth-of-type(1)")
	private WebElement last24hrsrequestedOption;

	@FindBy(css = "div:nth-child(4).span3 span option:nth-of-type(2)")
	private WebElement last7hrsrequestedOption;

	@FindBy(css = "div:nth-child(4).span3 span option:nth-of-type(3)")
	private WebElement allNonArchivedrequestedOption;

	public ImportHomePage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
		load();
		isLoaded();
	}
	
	public void load()
	{
		driver.get(pageUrl);
	}
	
	public void isLoaded()
	{
		DriverUtility.waitforElementDisplay(driver, importTypeOption, 30);
	}

	/***
	 * 
	 * @param searchTypeEle
	 *            : Type to select from Type drop down
	 * @param label
	 *            : select type label to match
	 * @param defaultIndex
	 *            : select defaultIndex option if given search string does not
	 *            present.
	 */

	public enum selectDropDown {
		 Type,Ownedby,Status,Requested} 

	/*
	 * ("Any","Owned By","Status","Requested"), ("Me","Anyone"),
	 * ("Any","Requested","In Progress", "Finished", "Cancelled/Stopped",
	 * "Error"), ("Within the last 24 hours",
	 * "Within the last 7 hours","All Non-Archieved Jobs")
	 */

	public void selectDropDownOnImportPage(selectDropDown selectDropDown,
			String TextValue) {
		WebElement targetElement = null;
		switch (selectDropDown) {
		case Type:
			targetElement = importTypeOption; 
			break;
		case Ownedby:
			targetElement = OwnedByMeDropDown; 
			break;
		case Status:
			targetElement = inProgressStatusOption;
			break;
		case Requested:
			targetElement = allNonArchivedrequestedOption;
			break;

		}
		// user utility function selectdropdown
		Select s=new Select(targetElement);
		if(TextValue==null)
		{
			int defaultIndex=0;
			s.selectByIndex(defaultIndex);
		}
		s.selectByVisibleText(TextValue);
	}
}
