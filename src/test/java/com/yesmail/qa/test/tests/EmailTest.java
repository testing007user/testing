package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;

public class EmailTest {	
	
	private String masterId = null;
	private String masterName = null;
	
	SAssert a = new SAssert(); 
	
	@Test(testName = "createMaster", description = "Creating a Master & enabling the same", timeOut = 500000, enabled = true, groups = {"BAT","DEMO"})
	public void createMaster() {
				
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Email Envelope Page<br>");		
		pof.emailEnvelopePage().load().isLoaded();		
		masterName = pof.emailEnvelopePage().createEnvelope();
		Reporter.log("Envelope Created with Master Name: "+masterName+"<br>");
		a.assertTrue(pof.emailEnvelopePage().stepCompleted(1, 10),"Creating email envelope page");
		
		Reporter.log("Creating Email Content Page <br>");
		pof.emailContentPage().load().isLoaded();
		masterId = pof.emailContentPage().getMasterId();
		Reporter.log("Created MasterID = "+masterId+ "<br>");
		a.assertTrue(pof.emailContentPage().uploadFile(),"Creating email content page");
		
		Reporter.log("Creating Email Target Page <br>");
		pof.emailTargetPage().load().isLoaded();
		a.assertTrue(pof.emailTargetPage().clickSaveGetCount(PagesHelper.EMAIL_ATTR_NAME, PagesHelper.EMAIL_ATTR_VALUE, PagesHelper.EMAIL_SUBSCRITPION_STATUS),"Creating email target Page");		

		Reporter.log("Creating Email Schedule Page <br>");
		pof.emailSchedulePage().load().isLoaded();		
		pof.emailSchedulePage().setDateTime();
		pof.emailSchedulePage().selectOccurence();
		pof.emailSchedulePage().checkObeyLimit();
		a.assertTrue(pof.emailSchedulePage().saveScheduleButton(),"Creating email schedule page");
		
		Reporter.log("Enabling and confirming on schedule Page <br>");
		a.assertTrue(pof.emailSchedulePage().enableAndConfirmSchedule(),"Enabling and Confirming Email Schedule");		
		a.assertAll();
		
	}
	
	@Test(testName = "verifyMaster", dependsOnMethods = {"createMaster"}, description = "Verifying Master Status on Listing Page", timeOut = 500000, enabled = true, groups = {"BAT","DEMO"})
	public void verifyMaster() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Verifying Master Status on View Email Page <br>");		
		pof.viewEmailPage().load();
		pof.viewEmailPage().isLoaded();
		Reporter.log("Verifying Status for MasterID: "+ masterId+ "on View Page<br>");
		a.assertTrue(pof.viewEmailPage().verifyEmailMasterStatus(masterId,PagesHelper.STATUSDELIVERED), "Verifying Master ID "+ masterId +" status on View Email Page");		
		a.assertAll();
	}
	
	@Test(testName = "VerifyEmailOnYahoo", dependsOnMethods = {"verifyMaster"}, description = "Verifying Email from Master on Yahoo", timeOut = 500000, enabled = true, groups= {"YAHOO","BAT"})
	public void VerifyEmailOnYahoo() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		Reporter.log("Verifying Email from Master : "+masterName+"<br>");
		a.assertTrue(pof.yahooPage().verifyEmailOnYahoo(masterName, 1),"Verifying Email on yahoo from Master :"+masterName);
		a.assertAll();
	}
	
	
}