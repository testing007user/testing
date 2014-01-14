package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;
/**
 * This class will contain Sms related testcases
 * @author achyutd
 *
 */
public class SMSTest {
	String masterId = null;
	
SAssert a = new SAssert(); 
	
	@Test(testName = "createSmsMaster", description = "Creating a Sms Master & enabling the same", timeOut = 500000, enabled = true, groups = {"BAT","DEMO"})
	public void createSmsMaster() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Sms Header Page <br>",true);
		pof.smsHeaderPage().load().isLoaded();		
		pof.smsHeaderPage().selectCampaign();		
		a.assertTrue(pof.smsHeaderPage().saveSmsHeader(PagesHelper.SMS_SHORTCODE),"Creating Sms Header Page");		
		
		Reporter.log("Creating SMS Content Page <br>",true);
		pof.smsContentPage().load().isLoaded();		
		a.assertTrue(pof.smsContentPage().saveContent(PagesHelper.SMS_CONTENT, PagesHelper.SMS_CONTENT_USERID, PagesHelper.SMS_MOBILENUMBER),"Creating Sms Content Page");
		
		Reporter.log("Creating Sms Target Page <br>",true);
		pof.smsTargetPage().load().isLoaded();
		masterId = pof.smsTargetPage().getMasterId();
		Reporter.log("Generated SMS Master Id: "+masterId+"<br>",true);
		a.assertTrue(pof.smsTargetPage().clickSaveGetCount(PagesHelper.SMS_ATTR_NAME, PagesHelper.SMS_ATTR_VALUE, PagesHelper.SMS_SUBSCRITPION_STATUS),"Creating Sms Target Page");
		
		Reporter.log("Creating Sms Schedule Page <br>",true);
		pof.smsSchedulePage().load().isLoaded();
		a.assertTrue(pof.smsSchedulePage().scheduleSMSMaster(),"Creating Sms Schedule Page");	
		
		Reporter.log("Enabling and confirming on schedule Page <br>",true);
		a.assertTrue(pof.smsSchedulePage().enableButtonAndConfirm(),"Enabling and Confirming Sms Schedule");
	
	}
	
	@Test(testName = "verifySmsMaster", dependsOnMethods = {"createSmsMaster"}, description = "Verifying Master Status on Listing Page", timeOut = 500000, enabled = true, groups = {"BAT","DEMO"})
	public void verifySmsMaster() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Verifying Status of MasterID: "+ masterId+ " on View Sms Page<br>",true);		
		pof.viewSmsPage().load().isLoaded();				
		a.assertTrue(pof.viewSmsPage().verifySmsStatus(masterId,PagesHelper.STATUSDELIVERED), "Verifying Master ID "+ masterId +" status on View Sms Page");		
		a.assertAll();
	}

}
