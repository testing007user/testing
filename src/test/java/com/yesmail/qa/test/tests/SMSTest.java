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
	
	@Test(testName = "createSmsMaster", description = "Creating a Sms Master & enabling the same", timeOut = 500000, enabled = true, groups = {"BAT","SMS"})
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
		a.assertAll();
	}
	
	@Test(testName = "verifySmsMaster", dependsOnMethods = {"createSmsMaster"}, description = "Verifying Master Status on Listing Page", timeOut = 500000, enabled = true, groups = {"BAT","SMS"})
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
	
	@Test(testName = "smsSinglePreview", description = "Verify SMS  preview ", timeOut = 500000, enabled = true, groups = {"SMS", "SmsPreview","BAT"})
	 public void smsSinglePreview() 
	 {
	  PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
	  pof.loginPage().load().isLoaded();
	  pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
	  pof.homePage().isLoaded();
	  
	  Reporter.log("Loading Sms Header Page <br>",true);
	  pof.smsHeaderPage().load().isLoaded();
	  pof.smsHeaderPage().saveSmsHeader(PagesHelper.SMSSHORTCODE);
	  a.assertTrue(pof.smsHeaderPage().stepCompleted(1, 10),"Create SMS Header");
	  
	  Reporter.log("Loading Sms Content Page <br>",true);
	  pof.smsContentPage().load().isLoaded();	  
	  a.assertTrue(pof.smsContentPage().previewWithMobileNumber(PagesHelper.SMSMESSAGECONTENT, PagesHelper.SMSUSERID,PagesHelper.SMSMOBILENUMBER),"Create SMS Content Preview Mobile");
	  a.assertAll();
	 }
	
	@Test(testName = "smsSummaryPage", description = "Verify summary page displays correct data", timeOut = 500000, enabled = true, groups = {"SMS", "SmsSummary","BAT"})
	 public void smsSummaryPage() {	 
	
				
	  PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
	  pof.loginPage().load().isLoaded();
	  pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
	  pof.homePage().isLoaded();
	  
	  pof.smsHeaderPage().load(PagesHelper.SMSMMID).isLoaded();
	  String strSmsShortCode = pof.smsHeaderPage().getShortCode();
	  Reporter.log("SMS Header Page Short Code: "+strSmsShortCode+"<br>",true);	 
	  
	  pof.smsContentPage().load().isLoaded();
	  String strContentText = pof.smsContentPage().getContentMessage();
	  Reporter.log("SMS Content Message :"+strContentText+"<br>",true);
	  	  
	  pof.smsTargetPage().load();//Removed is load as it not valid condition
	  String strsmsTarget = pof.smsTargetPage().getEligibleRecipientsCount();
	  Reporter.log("SMS Target Page Recipients Count"+strsmsTarget+"<br>",true);
	    
	  pof.smsSchedulePage().load().isLoaded();	   	  	 
	  String strScheduleFinalTime = pof.smsSchedulePage().getScheduledDateTime();
	  Reporter.log("SMS Schedule Page Date :"+strScheduleFinalTime+"<br>",true);
	  
	  Reporter.log("Navigate To Summary Page <br>",true); 
	  pof.smsSummaryPage().load().isLoaded();
	  String strSummaryShortCode = pof.smsSummaryPage().shortCodeHeader();
	  Reporter.log("Summary Page Short Code"+strSummaryShortCode+"<br>",true);
	  a.assertEquals(strSmsShortCode, strSummaryShortCode, "Summary Header Section");
	  
	  String strSummaryContentText = pof.smsSummaryPage().contentTextmessage();
	  Reporter.log("Summary Page Content Text"+strSummaryContentText+"<br>",true);
	  a.assertEquals(strContentText,strSummaryContentText,"Summary Content Section");    
	    
	  String strSummaryTargetCount = pof.smsSummaryPage().targetEligibleRecipients();
	  Reporter.log("Summary Page Target Count"+strSummaryTargetCount+"<br>",true);
	  a.assertEquals(strsmsTarget,strSummaryTargetCount,"Summary Target Section");  
	  
	  String strSummarryScheduleDate = pof.smsSummaryPage().schedulingDeliveryDate();
	  Reporter.log("Summary Page Schedule Date : <br>"+strSummarryScheduleDate,true);
	  String strSummaryTime = pof.smsSummaryPage().schedulingDeliveryDate(); 
	  a.assertEquals(strScheduleFinalTime,strSummaryTime,"Summary Schedule Section");
	  
	  Reporter.log("Summary Advanced Targeting SQL<br>",true);
	  a.assertTrue(pof.smsSummaryPage().advancedTargetingSQL(),"Advanced Targeting SQL");
	  a.assertAll();
	 }

}
