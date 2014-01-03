package com.yesmail.qa.pageobjects.smsinbound;

/** File Name: SmsInBoundRulesPage.java  
 * Description: This Java Class is added to select  the SMS Company Country and corresponding shortcode 
 *  
 * @author sangeetap 
 * Version: Draft 1.0 
 * Date: 25/11/2013 
 * Version History 
 * Version name Updated By Reason / Comments */

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.yesmail.qa.framework.DriverUtility;

public class SmsInBoundRulesPage {

	/** Element locator section */

	@FindBy(css = "select-large")
	private WebElement SMSCompanyCountrydropdown;

	@FindBy(css = "div:nth-child(3) table tbody tr >td > a")
	private List<WebElement> shortCodeTable;

	@FindBy(css = "div:nth-child(3) table tbody tr td:nth-of-type(1)>a ")
	private List<WebElement> shortCodeCol;

	private WebDriver driver;
	private String pageUrl;

	/**
	 * Constructor section
	 * 
	 * @return
	 */
	public SmsInBoundRulesPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
		load();
	}

	private void load() {
		driver.navigate().to(pageUrl);
		DriverUtility.waitforElementDisplay(driver, SMSCompanyCountrydropdown,
				10);
	}

	/***
	 * This method is added to select the SMS Company Country from the country
	 * drop down
	 * 
	 * 
	 * @return
	 */
	public void selectSMSCompanyCountry(WebElement element, String label,
			Integer defaultIndexValue) {
		DriverUtility.selectDropDown(SMSCompanyCountrydropdown, "USA", 1);
	}

	/***
	 * This method is added to select the shortcode
	 * 
	 * @param shortCode
	 *            - shortcode to select
	 */

	public void navigateToShortCodePage(String shortCode) {
		for (int index = 1; index < shortCodeTable.size(); index++) {

			if (shortCodeCol.get(index).getText().equalsIgnoreCase(shortCode)) {
				(shortCodeTable.get(index)).click();
				break;
			}

		}

	}

}
