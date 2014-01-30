package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;

/**
 * This class will contain Email related testcases
 * @author achyutd
 *
 */

public class EmailTest {	
	
	private String masterId = null;
	private String masterName = null;
	
	SAssert a = new SAssert(); 
	
	@Test(testName = "createMaster", description = "Creating a Master & enabling the same", timeOut = 500000, enabled = true, groups = {"BAT","DEMO","Email"})
	public void createMaster() {
				
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Email Envelope Page <br>",true);		
		pof.emailEnvelopePage().load().isLoaded();		
		masterName = pof.emailEnvelopePage().createEnvelope();
		Reporter.log("Envelope Created with Master Name: "+masterName+"<br>",true);
		a.assertTrue(pof.emailEnvelopePage().stepCompleted(1, 10),"Creating email envelope page");
		
		Reporter.log("Creating Email Content Page <br>",true);
		pof.emailContentPage().load().isLoaded();
		masterId = pof.emailContentPage().getMasterId();
		Reporter.log("Created MasterID = "+masterId+ "<br>",true);
		a.assertTrue(pof.emailContentPage().uploadFile(),"Creating email content page");
		
		Reporter.log("Creating Email Target Page <br>",true);
		pof.emailTargetPage().load().isLoaded();
		a.assertTrue(pof.emailTargetPage().clickSaveGetCount(PagesHelper.EMAIL_ATTR_NAME, PagesHelper.EMAIL_ATTR_VALUE, PagesHelper.EMAIL_SUBSCRITPION_STATUS),"Creating email target Page");		

		Reporter.log("Creating Email Schedule Page <br>",true);
		pof.emailSchedulePage().load().isLoaded();		
		pof.emailSchedulePage().setDateTime();
		pof.emailSchedulePage().selectOccurence();
		pof.emailSchedulePage().checkObeyLimit();
		a.assertTrue(pof.emailSchedulePage().saveScheduleButton(),"Creating email schedule page");
		
		Reporter.log("Enabling and confirming on schedule Page <br>",true);
		a.assertTrue(pof.emailSchedulePage().enableAndConfirmSchedule(),"Enabling and Confirming Email Schedule");		
		a.assertAll();
		
	}
	
	@Test(testName = "verifyMaster", dependsOnMethods = {"createMaster"}, description = "Verifying Master Status on Listing Page", timeOut = 500000, enabled = true, groups = {"BAT","DEMO","Email"})
	public void verifyMaster() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Verifying Master Status on View Email Page <br>",true);		
		pof.viewEmailPage().load().isLoaded();		
		Reporter.log("Verifying Status for MasterID: "+ masterId+ "on View Page <br>",true);
		a.assertTrue(pof.viewEmailPage().verifyEmailMasterStatus(masterId,PagesHelper.STATUSDELIVERED), "Verifying Master ID "+ masterId +" status on View Email Page");		
		a.assertAll();
	}
	
	@Test(testName = "VerifyEmailOnYahoo", dependsOnMethods = {"verifyMaster"}, description = "Verifying Email from Master on Yahoo", timeOut = 500000, enabled = true, groups= {"BAT","YAHOO","Email"})
	public void VerifyEmailOnYahoo() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		Reporter.log("Verifying Email from Master : "+masterName+" <br>",true);
		a.assertTrue(pof.yahooPage().verifyEmailOnYahoo(masterName, 4),"Verifying Email on yahoo from Master :"+masterName);
		a.assertAll();
	}
	
	@Test(testName = "singlePreviewEmail", description = "Verifying Single Preview on Email", timeOut = 500000, enabled = true, groups = {"BAT","DEMO","Email","Preview"})
	public void singlePreviewEmail() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Email Envelope Page <br>",true);		
		pof.emailEnvelopePage().load().isLoaded();		
		masterName = pof.emailEnvelopePage().createEnvelope();
		Reporter.log("Envelope Created with Master Name: "+masterName+"<br>",true);
		a.assertTrue(pof.emailEnvelopePage().stepCompleted(1, 10),"Creating email envelope page");
		
		Reporter.log("Creating Email Content Page <br>",true);
		pof.emailContentPage().load().isLoaded();
		Reporter.log("Uploading Email Content <br>",true);
		a.assertTrue(pof.emailContentPage().uploadFile(),"Uploading email Content");		
		pof.emailContentPage().selectSinglePreview();	
		
		Reporter.log("Verifying Email on Yahoo <br>",true);
		a.assertTrue(pof.yahooPage().verifyEmailOnYahoo(masterName,3),"Verify Email on Yahoo");		
		a.assertAll();
		
	}
	
	@Test(testName = "groupPreviewEmail", description = "Verifying Group Preview on Email", timeOut = 500000, enabled = true, groups = {"BAT","DEMO","Email","Preview"})
	public void groupPreviewEmail() {
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Email Envelope Page <br>",true);		
		pof.emailEnvelopePage().load().isLoaded();		
		masterName = pof.emailEnvelopePage().createEnvelope();
		Reporter.log("Envelope Created with Master Name: "+masterName+"<br>",true);
		a.assertTrue(pof.emailEnvelopePage().stepCompleted(1, 10),"Creating email envelope page");
		
		Reporter.log("Creating Email Content Page <br>",true);
		pof.emailContentPage().load().isLoaded();
		Reporter.log("Uploading Email Content <br>",true);
		a.assertTrue(pof.emailContentPage().uploadFile(),"Uploading email Content");		
		pof.emailContentPage().selectGroupPreview();	
		
		Reporter.log("Verifying Email on Yahoo <br>",true);
		a.assertTrue(pof.yahooPage().verifyEmailOnYahoo(masterName, 3),"Verify Email on Yahoo");		
		a.assertAll();
	}
	
	
	@Test(testName = "emailSummary", description = "Verifying Email Summary", timeOut = 500000, enabled = true, groups = {"BAT","DEMO","Email"})
	public void emailSummary() {		
		String envelopeDivision = null;
		String envelopeEncoding = null;
		String envelopeFromName = null;
		String targetCount = null;
		String scheduledDateTime = null;	
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Loading Email Envelope Page <br>",true);		
		pof.emailEnvelopePage().load(PagesHelper.EMAIL_MMID).isLoaded();
		envelopeDivision = pof.emailEnvelopePage().envelopeDivision();
		envelopeEncoding = pof.emailEnvelopePage().envelopeEncoding();
		envelopeFromName = pof.emailEnvelopePage().envelopeFromName();
		
		Reporter.log("Loading Email Target Page <br>",true);		
		pof.emailTargetPage().load().isLoaded();		
		targetCount = pof.emailTargetPage().targetCount();
		
		Reporter.log("Loading Email Schedule Page <br>",true);
		pof.emailSchedulePage().load();
		scheduledDateTime = pof.emailSchedulePage().scheduleDateTime();
		
		Reporter.log("Loading Email Summary Page <br>",true);
		pof.emailSummaryPage().load().isLoaded();	
		
		a.assertEquals(targetCount,pof.emailSummaryPage().targetCount(),"Verifying Target Count");
		a.assertEquals(scheduledDateTime,pof.emailSummaryPage().scheduledStartDate(),"Verifying Scheduled Start Date");
		a.assertEquals(envelopeDivision, pof.emailSummaryPage().envelopeDivision(),"Verifying Envelope Division");
		a.assertEquals(envelopeEncoding, pof.emailSummaryPage().envelopeEncoding(),"Verifying Envelope Encoding");
		a.assertTrue(pof.emailSummaryPage().envelopeFromText().contains(envelopeFromName),"Verifying Envelope FromName");		
		a.assertTrue(pof.emailSummaryPage().verifyContentSection(),"Verifying Content Section");
		a.assertTrue(pof.emailSummaryPage().verifySqlSection(),"Verifying Targeting SQL Section");			
		a.assertAll();
	}	
	
	
}
