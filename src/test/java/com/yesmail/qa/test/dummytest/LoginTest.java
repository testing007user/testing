package com.yesmail.qa.test.dummytest;


import org.testng.annotations.Test;
import com.yesmail.qa.pageobjects.*;
import com.yesmail.qa.test.configuration.XMLParser;
import com.yesmail.qa.framework.Driver;
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

		a.assertTrue(true,"Job ID is:"+pof.testSetupPage().fillSetUpPage(true, true, false));
		pof.testEnvelopPage().load().isLoaded();
		pof.testEnvelopPage().fillEnevlopePage(1,"marketing");
		pof.testContentPage().load().isLoaded();
		a.assertAll();
		pof.createCampaignPage().load().isLoaded();
		a.assertTrue(true,"Create Campaign Name:"+pof.createCampaignPage().createCampaign(true));
		System.out.println(XMLParser.readComponentValueFromXML("Content.contentUploadFileName"));
		pof.testContentPage().uploadFile(XMLParser.readComponentValueFromXML("Content.contentUploadFileName"));
		pof.testTargetPage().load().isLoaded();
		
		a.assertAll();
	
	}
//@Test(groups= "TestCampaign", timeOut= 30000000)
public void createCampaign()
{
	PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
	
	pof.loginPage().load().isLoaded();
	pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
	pof.homePage().isLoaded();
	/*pof.createCampaignPage().load().isLoaded();
	a.assertTrue(true, "Created Campaign Name:"+pof.createCampaignPage().createCampaign(true));*/
	pof.afterTheClickPage().load().isLoaded();
	//a.assertTrue(true,"Created Page Role Name:"+pof.afterTheClickPage().createNewPageRole("RoleName"));
	pof.afterTheClickPage().createNewTag("input.tagName");
}
	
//@Test(groups="VerifyTweetsStatus", timeOut=30000000)
public void verifyTweetsStatus()
{
	PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
	pof.loginPage().load().isLoaded();
	pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
	pof.homePage().isLoaded();
	//pof.tweetsContentPage().load().isLoaded();
	//String tweetId = pof.tweetsContentPage().createTweet("magellanGmail");
	//a.assertTrue(tweetId != null,"Created Twitter ID : "+pof.tweetsContentPage().createTweet("magellanGmail"));
	//pof.tweetsSchedulePage().load().isLoaded();
	//pof.tweetsSchedulePage().scheduleTweet();
	pof.viewTweetsPage().load().isLoaded();
	pof.viewTweetsPage().verifyTweetMasterStatus("1231126", "DISABLED");
}

@Test(groups="CreateDistributionList",timeOut=300000000)
public void createSeedDistributionList()
{
	PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
	pof.loginPage().load().isLoaded();
	pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
	pof.homePage().isLoaded();
	pof.distributionListPage().load().isLoaded();
	pof.distributionListPage().navigateToCreateDistributionPage();
	String strTestGroup = pof.createDistributionListPage().createSeedList("sangeeta.pote@infogroup.com");
	//pof.createDistributionListPage().createSeedList("236703");
	System.out.println(strTestGroup);
	pof.distributionListPage().load().isLoaded();
	pof.distributionListPage().deleteDistributionList(strTestGroup);
}
}
