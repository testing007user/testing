package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;
/**
 * This class will contain Tweet related testcases
 * @author achyutd
 *
 */
public class TweetTest {
	
	private String tweetMasterId = null; 
	
	SAssert a = new SAssert(); 
	
	 @Test(testName = "createTweetsMaster", description = "Create a Tweet & enable the same", timeOut = 500000, groups ={"BAT","Tweet"})
		public void createTweetsMaster() {

			PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
			pof.loginPage().load().isLoaded();
			pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
			pof.homePage().isLoaded();

			Reporter.log("Creating Tweets Master<br>",true);
			pof.tweetsContentPage().load().isLoaded();
			a.assertTrue(pof.tweetsContentPage().createTweet(PagesHelper.TWITTER_ACCOUNT),"Creating Tweet Content");
			
			Reporter.log("Scheduling Tweet master <br>",true);
			pof.tweetsSchedulePage().load().isLoaded();				
			tweetMasterId = pof.tweetsSchedulePage().getMasterId();
			Reporter.log("Generated Tweet master Id: "+tweetMasterId+"<br>",true);
			a.assertTrue(pof.tweetsSchedulePage().scheduleTweet(),"Scheduling Tweet Content");
			a.assertTrue(pof.tweetsSchedulePage().enableandConfirmTweet(),"Enabling and Confirming the Tweets Schudule for Job Id: "+tweetMasterId);
			a.assertAll();

		}

		@Test(testName = "verifyTweetsStatus", description = "Verifying the status of Tweets", timeOut = 500000, groups ={"BAT","Tweet"}, dependsOnMethods = {"createTweetsMaster"})
		public void verifyTweetsStatus() {

			PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
			pof.loginPage().load().isLoaded();
			pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
			pof.homePage().isLoaded();

			Reporter.log("Loading View Tweets Page<br>", true);
			pof.viewTweetsPage().load().isLoaded();
			a.assertTrue(pof.viewTweetsPage().verifyTweetMasterStatus(tweetMasterId,PagesHelper.STATUSPUBLISHED), "Verify Tweet Status for Master Id: "+tweetMasterId);
			a.assertAll();
		}

}
