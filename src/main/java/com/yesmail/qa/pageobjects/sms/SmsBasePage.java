
/** File Name: SmsBasePage.java  
 * Description: This Java Class is 
 * used to navigate to to navigate to Content page, Target page, Schedule page
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * Date: 18/11/2013 
 * Version History  
 * 
 * Version name Updated By Reason / Comments 
 * Version:1.1 Updated for below reason
 * 1.Variable names should start with small character.
 * 2.Also you can remove navigation methods that are returning page objects. As we are going to use 'Page Object Factory' to get new page object. 
 * */

package com.yesmail.qa.pageobjects.sms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.pageobjects.BasePage;

public class SmsBasePage extends BasePage {

	@FindBy(css = "div:nth-child(2) a:nth-of-type(2) span:nth-of-type(2)")
	private WebElement contentTab;

	@FindBy(css = "div:nth-child(2) a:nth-of-type(3) span:nth-of-type(2)")
	private WebElement targetTab;

	@FindBy(css = "div:nth-child(2) a:nth-of-type(4) span:nth-of-type(2)")
	private WebElement scheduleTab;

	@FindBy(css = "a.mvt-ico-summary")
	private WebElement summaryTab;
	
	private WebDriver driver;

	public SmsBasePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	/***
	 * This method is added to navigate to Content page
	 * 
	 * @author sangeetap
	 */
	public void navigateToContent() {
		contentTab.click();
	}

	/***
	 * This method is added to navigate to Target page
	 * 
	 * @author sangeetap
	 */
	public void navigateToTarget() {
		targetTab.click();
	}

	/***
	 * This method is added to navigate to Schedule page
	 * 
	 * @author sangeetap
	 */
	public void navigateToSchedule() {
		scheduleTab.click();
	}
	
	/***
	 * This method is added to navigate to Summary page
	 * 
	 * @author sangeetap
	 */
	public void navigateToSummary() {
		summaryTab.click();
	}

}
