package com.yesmail.qa.pageobjects.login;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class GooglePage {

	@FindBy(name = "q") 
	WebElement txtSearch;
	
	@FindBy(name = "btnG") 
	WebElement btnSearch;
	
	
	public GooglePage(WebDriver driver, String Url) {
		// TODO Auto-generated constructor stub
		driver.get(Url);
		PageFactory.initElements(driver, this);
		/*driver.manage().timeouts().setScriptTimeout(10000, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);*/
	}
	public void search(String strsearch)
	{
		txtSearch.sendKeys(strsearch);
		btnSearch.click();
		
	}
}
