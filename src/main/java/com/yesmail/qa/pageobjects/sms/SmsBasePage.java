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

	private WebDriver driver;
	public SmsBasePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
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


}
