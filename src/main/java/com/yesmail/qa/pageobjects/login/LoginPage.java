/***
 * File : LoginPage.java
 * Description : Page object class for LoginPage functionality 
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *    
 */

package com.yesmail.qa.pageobjects.login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.pageobjects.PagesHelper;



public class LoginPage {

	
	private WebDriver driver;
	
	@FindBy(id = "recentMessagesIcon" )
	private WebElement recentMessagesIcon;
	
	private String pageUrl;
	
	 public LoginPage(WebDriver driver,String pageUrl) {
		 PageFactory.initElements(driver, this);
		 this.driver = driver;
		 this.pageUrl = pageUrl;
	
	}
	 
	 public LoginPage load()
	 {
		 driver.get(pageUrl);		 
		 return this;
	 }
	 
	 public boolean isLoaded()
	 {
		 if(null == DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(recentMessagesIcon), driver, 50))
		 {
			 
				throw new FrameworkException(this.getClass().getName()
						+ " is not loaded in 50 seconds");
		 }
		 return true;
	 }
	
	}
