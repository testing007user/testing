package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;
/**
 * This class will contain Twitter related testcases
 * @author achyutd
 *
 */
public class TweetTest {
	
	private String twitterMasterId = null; 
	
	SAssert a = new SAssert(); 
	
	 @Test(testName = "createTwitterMaster", description = "Create a Twitter Master & enable the same", timeOut = 500000, groups ={"BAT","Twitter"})
		public void createTwitterMaster() {

			PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
			pof.loginPage().load().isLoaded();
			pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
			pof.homePage().isLoaded();

			Reporter.log("Creating Twitter Master<br>",true);
			pof.tweetsContentPage().load().isLoaded();
			a.assertTrue(pof.tweetsContentPage().createTweet(PagesHelper.TWITTER_ACCOUNT),"Creating Twitter Content");
			
			Reporter.log("Scheduling Twitter master <br>",true);
			pof.tweetsSchedulePage().load().isLoaded();				
			twitterMasterId = pof.tweetsSchedulePage().getMasterId();
			Reporter.log("Generated Twitter master Id: "+twitterMasterId+"<br>",true);
			a.assertTrue(pof.tweetsSchedulePage().scheduleTweet(),"Scheduling Twitter Content");
			a.assertTrue(pof.tweetsSchedulePage().enableandConfirmTweet(),"Enabling and Confirming the Twitter Schudule for Job Id: "+twitterMasterId);
			a.assertAll();

		}

		@Test(testName = "verifyTwitterStatus", description = "Verifying the status of Twitter", timeOut = 500000, groups ={"BAT","Twitter"}, dependsOnMethods = {"createTwitterMaster"})
		public void verifyTwitterStatus() {

			PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
			pof.loginPage().load().isLoaded();
			pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
			pof.homePage().isLoaded();

			Reporter.log("Loading View Twitter Page<br>", true);
			pof.viewTweetsPage().load().isLoaded();
			a.assertTrue(pof.viewTweetsPage().verifyTweetMasterStatus(twitterMasterId,PagesHelper.STATUSPUBLISHED, 3), "Verify Twitter Status for Master Id: "+twitterMasterId);
			a.assertAll();
		}

}
