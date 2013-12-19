package com.yesmail.qa.test.dummytest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.yesmail.qa.pageobjects.*;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;

public class LoginTest2 {

	public SAssert a = new SAssert();

	@Test(groups = "Testing2")
	public void loginTest() {

		WebDriver driver = Driver.getDriver();

		PageObjectFactory pof = new PageObjectFactory(driver);

		Reporter.log("<br>This is reporting 1 ");
		Reporter.log("<br>This is reporting 2 ");
		Reporter.log("<br>This is reporting 3 ");
		Reporter.log("<br>This is reporting 4 ");
		Reporter.log("<br>This is reporting 5 ");

		a.assertTrue(true, "Checking for Web Element on Home page");
		a.assertTrue(false, "Checking for Web Element on Home page");

		boolean flag = true;
		if (flag)
			throw new ElementNotFoundException("abc", "abc", "abc");

		a.assertTrue(true, "Checking for Web Element on Home page");
		a.assertTrue(true, "Checking for Web Element on Home page");

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");

		a.assertAll();

	}

	@Test(groups = "Testing2")
	public void loginTest33() {

//		WebDriver driver = Driver.getDriver();

		// PageObjectFactory pof = new PageObjectFactory(driver);

		Reporter.log("<br>This is reporting 1 ");
		Reporter.log("<br>This is reporting 2 ");
		Reporter.log("<br>This is reporting 3 ");
		Reporter.log("<br>This is reporting 4 ");
		Reporter.log("<br>This is reporting 5 ");

		a.assertTrue(false, "Checking for Web Element on Home page");

		a.assertAll();

	}

	@Test(groups = "Testing2")
	public void loginTest23() {

		WebDriver driver = Driver.getDriver();

		PageObjectFactory pof = new PageObjectFactory(driver);

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
		pof.subscriberPage().load();
		pof.subscriberPage().isLoaded();

		driver.findElement(By.id("searchUserId")).sendKeys("123123");

		pof.subscriberPage().clickCreateNewSubscriberbtn();

		a.assertAll();

	}

	@Test(groups = "Testing2")
	public void loginTest7() {

		WebDriver driver = Driver.getDriver();

		PageObjectFactory pof = new PageObjectFactory(driver);

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
		pof.subscriberPage().load();
		pof.subscriberPage().isLoaded();

		driver.findElement(By.id("searchUserId")).sendKeys("123123");

		pof.subscriberPage().clickCreateNewSubscriberbtn();

		a.assertAll();
		// pof.loginPage().
	}

	@Test(groups = "Testing2")
	public void loginTest42() {

		WebDriver driver = Driver.getDriver();

		PageObjectFactory pof = new PageObjectFactory(driver);

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
		pof.subscriberPage().load();
		pof.subscriberPage().isLoaded();

		driver.findElement(By.id("searchUserId")).sendKeys("123123");

		pof.subscriberPage().clickCreateNewSubscriberbtn();

		a.assertAll();

	}

	@Test(groups = "Testing2")
	public void loginTest8() {

		WebDriver driver = Driver.getDriver();

		PageObjectFactory pof = new PageObjectFactory(driver);

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
		pof.subscriberPage().load();
		pof.subscriberPage().isLoaded();

		driver.findElement(By.id("searchUserId")).sendKeys("123123");

		pof.subscriberPage().clickCreateNewSubscriberbtn();

		a.assertAll();

	}

	@Test(groups = "Testing2")
	public void loginTest6() {

		WebDriver driver = Driver.getDriver();

		PageObjectFactory pof = new PageObjectFactory(driver);

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
		pof.subscriberPage().load();
		pof.subscriberPage().isLoaded();

		driver.findElement(By.id("searchUserId")).sendKeys("123123");

		pof.subscriberPage().clickCreateNewSubscriberbtn();

		a.assertAll();

	}

	@Test(groups = "Testing2")
	public void loginTest4() {

		WebDriver driver = Driver.getDriver();

		PageObjectFactory pof = new PageObjectFactory(driver);

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
		pof.subscriberPage().load();
		pof.subscriberPage().isLoaded();

		driver.findElement(By.id("searchUserId")).sendKeys("123123");

		pof.subscriberPage().clickCreateNewSubscriberbtn();

		a.assertAll();

	}

	@Test(groups = "Testing")
	public void loginTest3() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());

		pof.loginPage().loginAs();
		a.assertTrue(pof.loginPage().check(),
				"Checking for Web Element on Home page");
	}

	@Test(groups = "Testing")
	public void loginTest2() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs();

	}

}
