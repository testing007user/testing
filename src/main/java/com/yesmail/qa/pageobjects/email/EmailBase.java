package com.yesmail.qa.pageobjects.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.pageobjects.BasePage;

public class EmailBase extends BasePage {

	private WebDriver driver;
	private String pageUrl;

	// Page Elements for EmailBase class

	@FindBy(css = ".icon.icon-chevron-down")
	private WebElement iconDropdown;

	@FindBy(css = "div:nth-of-type(4) > div > ul > li:nth-of-type(1) > a")
	private WebElement emailEnvelopePage;

	@FindBy(css = "a.mvt-ico-content")
	private WebElement contentTab;

	@FindBy(css = "a.mvt-ico-schedule")
	private WebElement scheduleTab;

	@FindBy(css = "a.mvt-ico-target")
	private WebElement targetTab;

	@FindBy(css = ".mvt-step.complete")
	private WebElement mvtCompleteIcon;

	// Constructor section 
	public EmailBase(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/***
	 * Navigate to Email Content page
	 */
	public void navigateToContentTab() {
		contentTab.click();
	}

	/***
	 * Navigate to Email Target page
	 */
	public void navigateToTargetTab() {
		targetTab.click();
	}

	/***
	 * Navigate to Email Schedule page
	 */
	public void navigateToScheduleTab() {
		scheduleTab.click();
	}

	/***
	 * Method to Verify whether Step is completed.
	 * 
	 * @return: completed(True/False)
	 */
	public boolean stepCompleted() {
		return (DriverUtility
				.waitforElementDisplay(driver, mvtCompleteIcon, 10));
	}

}
