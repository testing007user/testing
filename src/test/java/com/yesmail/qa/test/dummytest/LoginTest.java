package com.yesmail.qa.test.dummytest;


import org.testng.annotations.Test;
import com.yesmail.qa.pageobjects.*;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;

public class LoginTest {

	public SAssert a = new SAssert();

	@Test(groups = "Testing2")
	public void loginTest1() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");

		a.assertAll();
		
	}



	@Test(groups = "Testing2",timeOut = 300000)
	public void loginTest2() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
		 a.assertAll();


	}

	@Test(groups = "Testing3",timeOut = 500000)
	public void loginTest3() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs();
		pof.testSetupPage().load().isLoaded();
		a.assertTrue(true,"Job ID is:"+pof.testSetupPage().fillSetUpPage(true, true, false));
		pof.testEnvelopPage().load().isLoaded();
		pof.testEnvelopPage().fillEnevlopePage(1);
		pof.testContentPage().load().isLoaded();
		a.assertAll();
		
		

	}

	

}
