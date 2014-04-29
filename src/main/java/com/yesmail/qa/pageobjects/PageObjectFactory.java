package com.yesmail.qa.pageobjects;

import org.openqa.selenium.WebDriver;

import com.yesmail.qa.pageobjects.login.LoginPage;
import com.yesmail.qa.test.configuration.XMLParser;

/**
 * PageObjectFactory gives object for pages
 * 
 * @author rahull
 * 
 */
public class PageObjectFactory {

	private WebDriver driver;
	private LoginPage loginPage;
	

	

	public PageObjectFactory(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage loginPage() {
		if (loginPage == null)
			loginPage = new LoginPage(driver);
		return loginPage;
	}

	
}
