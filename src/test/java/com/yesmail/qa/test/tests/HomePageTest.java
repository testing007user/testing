package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;

public class HomePageTest {
	
	SAssert a = new SAssert();
	
	@Test(testName = "homePage", description = "Verifying HomePage", timeOut = 500000, enabled = true, groups = {"BAT","HomePage"})
	public void homePage() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		
		Reporter.log("Loading HomePage<br>",true);
		a.assertTrue(pof.homePage().isLoaded(), "Verifying Homepage");
		a.assertTrue(pof.homePage().clickLearnMore(), "Verifying LearnMore link");
		a.assertAll();
    }
}
