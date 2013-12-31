package com.yesmail.qa.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;

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
	
	public void checkLoading()
	{
		
	}
	
	/***
	 * Return Map containing Status and Message 
	 * Values can be retrieved like returnMessage.get("Status") or returnMessage.get("Message")
	 * @param timeToWaitInSeconds
	 * @return
	 */
	public Map<String,String> getRibbonText(int timeToWaitInSeconds)
	{
		if(null == DriverUtility.waitFor(ExpectedConditions.elementToBeClickable(ribbonMessage), driver, timeToWaitInSeconds))
			return null;
		Map<String,String> returnMessage = new HashMap<String, String>();
		returnMessage.put("Status",ribbonStatus.getText());
		returnMessage.put("Message",ribbonMessage.getText());
		return returnMessage;
		
	}

}
