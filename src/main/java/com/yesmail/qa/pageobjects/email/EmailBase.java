/***
 * File : EmailBase.java
 * Description : Page object class for EmailBase functionality 
 * @author sangeetap
 * Version History : Draft 0.1
 *
 *    
 */

package com.yesmail.qa.pageobjects.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.pageobjects.BasePage;

/***
 * Base Page having common Functionality specific to email
 * @author sangeetap
 *
 */
public class EmailBase extends BasePage {

	@SuppressWarnings("unused")
	private WebDriver driver;

	// Page Elements for EmailBase class
	
	@FindBy(css = "a.mvt-ico-content")
	private WebElement contentTab;

	@FindBy(css = "a.mvt-ico-schedule")
	private WebElement scheduleTab;

	@FindBy(css = "a.mvt-ico-target")
	private WebElement targetTab;	

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

}
