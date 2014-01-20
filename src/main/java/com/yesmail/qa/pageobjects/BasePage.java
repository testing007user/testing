/**File name : BasePage.java
 * @Description: Base Page having common functionality accross Application 
 * @author kapilag 
 * Version: Draft 1.0 
 * @since completed by 12/17/13
 * @Version History
 * Version name Updated By Reason / Comments
 *  
 * */
package com.yesmail.qa.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;

/***
 * Common Functionality accross UI
 * @author kapilag
 *
 */
public class BasePage {
	
	WebDriver driver;
	@FindBy(css = ".page [class~=alert] div")
	private WebElement ribbonMessage;
	@FindBy(css = ".page [class~=alert] strong")
	private WebElement ribbonStatus;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//To be added Later ,when needed
	public void checkLoading()
	{
		
	}
	
	/***
	 * Return String containing Status and Message seprated by _
	 * Values can be retrieved with Strign split on 1st undersocre
	 * @param timeToWaitInSeconds
	 * @author kapilag
	 * @return
	 */
	public String getRibbonText(int timeToWaitInSeconds)
	{
		try{
		if(null == DriverUtility.waitFor(ExpectedConditionExtended.elementToBeClickable(ribbonMessage), driver, timeToWaitInSeconds))
			return null;
		String returnMessage = ribbonStatus.getText()+"_"+ribbonMessage.getText();
		return returnMessage;
		}
		catch(Exception ex)
		{
			return "Selenium didn't catch ribbon text";
		}
	}
	
	/***
	 * This checks if the page is completed and green mark is displayed
	 * Step Number starts with 1 and max to 5
	 * @author kapilag
	 * @return
	 */
	public boolean stepCompleted(int stepNumber,int timeToWaitInSeconds){
			String cssPath = "a:nth-of-type("+stepNumber+") >  span.mvt-step[class~=complete]";
			if(null == DriverUtility.waitFor(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssPath)), driver, timeToWaitInSeconds))
				return false;
			return true;
		 }

}
