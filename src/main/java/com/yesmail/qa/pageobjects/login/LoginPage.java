package com.yesmail.qa.pageobjects.login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.libraries.IProperty;
import com.yesmail.qa.pageobjects.PagesHelper;
import com.yesmail.qa.pageobjects.PagesHelperEnum;
import com.yesmail.qa.test.configuration.ConfigurationClass;


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
	 
	 public void load()
	 {
		 
		 
	 }
	
	public void loginAs() {
		
		driver.get(PagesHelper.URL);
		driver.navigate().refresh();
		DriverUtility.waitforElementDisplay(driver, emailInput, 50);
		emailInput.sendKeys(PagesHelper.USERNAME);
		passwordInput.sendKeys(PagesHelper.PASSWORD);
		submitBtn.click();
		DriverUtility.waitFor(ExpectedConditions.invisibilityOfElementLocated(By.name("username")), driver, 20);
	}
	
	public boolean check()
	{
		return DriverUtility.waitforElementDisplay(driver, recentMessagesIcon, 20);		
	}
	
}
