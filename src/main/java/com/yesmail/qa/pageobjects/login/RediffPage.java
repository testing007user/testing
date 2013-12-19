package com.yesmail.qa.pageobjects.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RediffPage {

/*		@FindBy(name = "q") 
		WebElement txtSearch;
		
		@FindBy(name = "btnG") 
		WebElement btnSearch;
*/		
		
		public RediffPage(WebDriver driver, String Url) {
			// TODO Auto-generated constructor stub
			driver.get(Url);
			PageFactory.initElements(driver, this);
			
		}

}


