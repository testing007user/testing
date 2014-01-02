package com.yesmail.qa.test.dummytest;


import org.testng.annotations.Test;
import com.yesmail.qa.pageobjects.*;
import com.yesmail.qa.test.configuration.XMLParser;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.DriverUtility.CHECK_UNCHECK;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.framework.testng.support.SAssert;

public class LoginTest {

	public SAssert a = new SAssert();

//	@Test(groups = "Testing2")
	public void loginTest1() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs(PagesHelper.USERNAME,PagesHelper.PASSWORD);
		a.assertTrue(pof.homePage().isLoaded(),
				"Checking for Web Element on Home page");

		a.assertAll();
		
	}



//	@Test(groups = "Testing2",timeOut = 300000)
	public void loginTest2() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs(PagesHelper.USERNAME,PagesHelper.PASSWORD);
		a.assertTrue(pof.homePage().isLoaded(),
				"Checking for Web Element on Home page");
		 a.assertAll();


	}

	//@Test(groups = "Testing3",timeOut = 500000)
	public void loginTest3() {
//		System.out.println(XMLParser.readComponentValueFromXML("Content.contentUploadFileName"));
//		System.out.println(Utils.getResources(this, XMLParser.readComponentValueFromXML("Content.contentUploadFileName")));
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME,PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		pof.testSetupPage().load().isLoaded();
		a.assertTrue(true,"Job ID is:"+pof.testSetupPage().fillSetUpPage(CHECK_UNCHECK.CHECK, true, false));
		pof.testEnvelopPage().load().isLoaded();
		pof.testEnvelopPage().fillEnevlopePage(1,"marketing");
		pof.testContentPage().load().isLoaded();
//		System.out.println(XMLParser.readComponentValueFromXML("Content.contentUploadFileName"));
		pof.testContentPage().uploadFile(XMLParser.readComponentValueFromXML("Content.contentUploadFileName"));
		pof.testTargetPage().load().isLoaded();
		
		a.assertAll();

	}
	
	@Test(timeOut = 5000000)
	public void testing(){
		System.out.println(Utils.getResources(this, "Test 12 March 2013.zip"));
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs("sudhakar.a@yesmail.com", "Infogroup01!");
		pof.homePage().isLoaded();
		pof.emailEnvelopePage().load().isLoaded();
				
		a.assertTrue(pof.emailEnvelopePage().createEnvelope(),"Creating email envelope page");
		
		pof.emailContentPage().navigateToContentTab();		
		pof.emailContentPage().isLoaded();
		
		String masterId = pof.emailContentPage().getMasterId();
		System.out.println(masterId);
		//pof.emailContentPage().createContent("html",false);
		//pof.emailContentPage().createContent("text",false);		
		a.assertTrue(pof.emailContentPage().uploadFile(),"Creating email content page");

		pof.emailTargetPage().navigateToTargetTab();
		pof.emailTargetPage().isLoaded();
		a.assertTrue(pof.emailTargetPage().clickSaveGetCount("eMail", "sangeeta.pote@yesmail.com" , "Subscribed"),"Creating email target Page");
		
		pof.emailSchedulePage().navigateToScheduleTab();
		pof.emailSchedulePage().isLoaded();		
		pof.emailSchedulePage().setDateTime();
		pof.emailSchedulePage().selectOccurence();
		pof.emailSchedulePage().checkObeyLimit();		
		//pof.emailSchedulePage().preCompileSetformattedTime();	
		a.assertTrue(pof.emailSchedulePage().saveScheduleButton(),"Creating schedule page");
		pof.emailSchedulePage().enableAndConfirmSchedule();	
				
		pof.viewEmailPage().load();
		pof.viewEmailPage().isLoaded();
		a.assertTrue(pof.viewEmailPage().verifyEmailMasterStatus(masterId, "Delivered"),"Verifying Master status on Listing Page");
		
		pof.countsPage().load();
		pof.countsPage().isLoaded();
		a.assertTrue(pof.countsPage().verifyStatusOnCountsPage(masterId, "Finished")," verifying status Master ID "+masterId+ " on Counts Page");
		
		
		
		
		/*PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().loginAs("sudhakar.a@yesmail.com", "Infogroup01!");
		threadSleep(5);
		pof.dataAttributesPage().load();
		pof.dataAttributesPage().isLoaded();
		pof.dataAttributesPage().clickCreateAttributeBtn();
		String attributeName = Utils.getUniqueName("test", 12);
		pof.dataAttributesPage().enterDisplayName(attributeName);
		pof.dataAttributesPage().enterColumnName();
		pof.dataAttributesPage().selectStringAttribute("String", "Test");
		pof.dataAttributesPage().selectTableName();
		pof.dataAttributesPage().selectDivisions();
		pof.dataAttributesPage().saveAttribute();
		a.assertTrue(pof.dataAttributesPage().searchAttribute(attributeName),"searching attribute");
		a.assertTrue(pof.dataAttributesPage().deleteAttribute(attributeName),"deleting the attribute");	*/	
		
	}

	
	
	
	public void threadSleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
