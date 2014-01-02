/***
 * File Name: LoginPage.java 
 * Description: This Java Class is used to log in to the application with user valid credential
 * 
 * @author sangeetap
 * @version Draft 1.0
 * @since completed by 14/11/2013
 * @Version History
 * 
 * 
 * */

package com.yesmail.qa.pageobjects.login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.PagesHelper;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class LoginPage {

	
	private WebDriver driver;
	@FindBy(name = "username")
	private WebElement emailInput;
	
	@FindBy(css = "#gigya-login-screen > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")
	//@FindBy(css = "input[name='password']")
	private WebElement passwordInput;
	
	@FindBy(css = "#gigya-login-screen > form:nth-child(1)> div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")
	//@FindBy(css = "div:nth-child(4) .gigya-input-submit")
	private WebElement submitBtn;
	
	@FindBy(id = "recentMessagesIcon" )
	private WebElement recentMessagesIcon;
	
	
	 public LoginPage(WebDriver driver) {
		 PageFactory.initElements(driver, this);
		 this.driver = driver;
	
	}
	 
	 public LoginPage load()
	 {
		 driver.get(PagesHelper.URL);
		 driver.navigate().refresh();
		 return this;
	 }
	 
	 public boolean isLoaded()
	 {
		 if(null == DriverUtility.waitFor(elementToBeClickable(By.name("username")), driver, 50))
		 {
				throw new FrameworkException(this.getClass().getName()
						+ " is not loaded in 50 seconds");
		 }
		 return true;
	 }
	
	 /**
	  * This method is added to log in to the app
	  * @param userName - valid username 
	  * @param password - valid password
	  */
	public void loginAs(String userName,String password) {
		emailInput.sendKeys(userName);
		passwordInput.sendKeys(password);
		submitBtn.click();
	}
	
	/**
	 * This method is used to check for homepage load
	 * @return
	 */
	public boolean check()
	{
		return DriverUtility.waitforElementDisplay(driver, recentMessagesIcon, 20);		
	}
	
}
