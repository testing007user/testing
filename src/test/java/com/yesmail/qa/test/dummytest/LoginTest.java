package com.yesmail.qa.test.dummytest;

import org.testng.annotations.Test;
import com.yesmail.qa.pageobjects.*;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.framework.testng.support.SAssert;

public class LoginTest {

	public SAssert a = new SAssert();

	@Test
	public void loginTest1() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load();

		a.assertTrue(true,"testing the assert");
			//	"Checking for Web Element on Home page");

		a.assertAll();

	}


	
}
