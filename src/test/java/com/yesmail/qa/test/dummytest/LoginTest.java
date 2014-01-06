package com.yesmail.qa.test.dummytest;

import org.apache.commons.httpclient.methods.GetMethod;
import org.testng.annotations.Test;
import com.yesmail.qa.pageobjects.*;
import com.yesmail.qa.test.configuration.XMLParser;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.DriverUtility.CHECK_UNCHECK;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.framework.testng.support.SAssert;

public class LoginTest {

	public SAssert a = new SAssert();

	// @Test(groups = "Testing2")
	public void loginTest1() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		a.assertTrue(pof.homePage().isLoaded(),
				"Checking for Web Element on Home page");

		a.assertAll();

	}

	// @Test(groups = "Testing2",timeOut = 300000)
	public void loginTest2() {

		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		a.assertTrue(pof.homePage().isLoaded(),
				"Checking for Web Element on Home page");
		a.assertAll();

	}

	// @Test(groups = "Testing3",timeOut = 500000)
	public void loginTest3() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		pof.testSetupPage().load().isLoaded();
		a.assertTrue(
				true,
				"Job ID is:"
						+ pof.testSetupPage().fillSetUpPage(
								CHECK_UNCHECK.CHECK, true, false));
		pof.testEnvelopPage().load().isLoaded();
		pof.testEnvelopPage().fillEnevlopePage(1, "marketing");
		pof.testContentPage().load().isLoaded();
		pof.testContentPage()
				.uploadFile(
						XMLParser
								.readComponentValueFromXML("Content.contentUploadFileName"));
		pof.testTargetPage().load().isLoaded();
		a.assertAll();

	}

	//@Test(timeOut = 5000000)
	public void testing() {
		System.out.println(Utils.getResources(this, "Test 12 March 2013.zip"));
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		pof.emailEnvelopePage().load().isLoaded();

				
		String MasterName = pof.emailEnvelopePage().createEnvelope();
		a.assertTrue(pof.emailEnvelopePage().stepCompleted(1, 10),"Creating email envelope page");
		
		pof.emailContentPage().navigateToContentTab();		

		pof.emailContentPage().isLoaded();
		String masterId = pof.emailContentPage().getMasterId();
		System.out.println(masterId);
		a.assertTrue(pof.emailContentPage().uploadFile(),
				"Creating email content page");
		pof.emailTargetPage().navigateToTargetTab();
		pof.emailTargetPage().isLoaded();

		a.assertTrue(pof.emailTargetPage().clickSaveGetCount("eMail", "postqa1@yahoo.com" , "Subscribed"),"Creating email target Page");
		

		pof.emailSchedulePage().navigateToScheduleTab();
		pof.emailSchedulePage().isLoaded();
		pof.emailSchedulePage().setDateTime();
		pof.emailSchedulePage().selectOccurence();
		pof.emailSchedulePage().checkObeyLimit();
		a.assertTrue(pof.emailSchedulePage().saveScheduleButton(),
				"Creating schedule page");
		pof.emailSchedulePage().enableAndConfirmSchedule();
		pof.viewEmailPage().load();
		pof.viewEmailPage().isLoaded();
		a.assertTrue(
				pof.viewEmailPage().verifyEmailMasterStatus(masterId,
						"Delivered"), "Verifying Master status on Listing Page");
		pof.countsPage().load();
		pof.countsPage().isLoaded();

		a.assertTrue(pof.countsPage().verifyStatusOnCountsPage(masterId, "Finished")," verifying status Master ID "+masterId+ " on Counts Page");
		
		pof.yahooPage().load().isLoaded();
		pof.yahooPage().clickMailLink();
		pof.yahooPage().loginToYahoo();
		pof.yahooPage().clickInbox();
		pof.yahooPage().findEmail(MasterName, 5);
		
		
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

	// @Test(groups= "TestCampaign", timeOut= 30000000)
	public void createCampaign() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		pof.afterTheClickPage().load().isLoaded();
		pof.afterTheClickPage().createNewTag("input.tagName");
	}

	 @Test(groups="testStatus",timeOut=30000000)
	public void testStatus() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		pof.tweetsContentPage().load().isLoaded();
		a.assertTrue(pof.tweetsContentPage().createTweet("magellanGmail"),"creating tweet content");
		String strTweetId = pof.tweetsContentPage().getMasterId();
		pof.tweetsSchedulePage().load().isLoaded();
		pof.tweetsSchedulePage().scheduleTweet();
		pof.viewTweetsPage().load().isLoaded();
		pof.viewTweetsPage().verifyTweetMasterStatus(strTweetId, "PUBLISHED");
	}
	 
	 @Test(groups="testStatus1232",timeOut=50000000)
	 public void test1232(){
		 PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		 pof.loginPage().load().isLoaded();
		 pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		 pof.homePage().isLoaded();
		 pof.facebookContentPage().load().isLoaded();
		 a.assertTrue(pof.facebookContentPage().fillFacebookContent(),"fill facebook content");		 
		 pof.facebookSchedulePage().navigateToScheduleTab();
		 pof.facebookSchedulePage().isLoaded();
		 String masterId = pof.facebookContentPage().getMasterId();
		 pof.facebookSchedulePage().scheduleMaster();
		 pof.viewFacebookPage().load().isLoaded();
		 pof.viewFacebookPage().verifyFacebookMasterStatus(masterId, "PUBLISHED");
		 
	 }

	// @Test(groups="testList",timeOut=300000000)
	public void testList() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		pof.distributionListPage().load().isLoaded();
		pof.distributionListPage().navigateToCreateDistributionPage();
		String strTestGroup = pof.createDistributionListPage().createSeedList(
				"sangeeta.pote@infogroup.com");
		System.out.println(strTestGroup);
		pof.distributionListPage().load().isLoaded();
		pof.distributionListPage().deleteDistributionList(strTestGroup);
	}
	
}
