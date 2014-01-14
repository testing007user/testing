package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;
/**
 * This class will contain Facebook related testcases
 * @author achyutd
 *
 */
public class FacebookTest {
	
	SAssert a = new SAssert();
	String facebookMasterId = null;
	
	 @Test(testName = "createFacebookMaster", description = "Create a Facebook Master & enable the same", timeOut = 500000, groups = {"BAT","Facebook"})
		public void createFacebookMaster() {

			PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
			pof.loginPage().load().isLoaded();
			pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
			pof.homePage().isLoaded();

			Reporter.log("Creating Facebook Master<br>",true);
			pof.facebookContentPage().load().isLoaded();
			a.assertTrue(pof.facebookContentPage().fillFacebookContent(),"Creating Facebook Content Page");
			
			Reporter.log("Scheduling Facebook master <br>",true);
			pof.tweetsSchedulePage().load().isLoaded();				
			facebookMasterId = pof.facebookSchedulePage().getMasterId();
			Reporter.log("Generated Tweet master Id: "+facebookMasterId+"<br>",true);
			a.assertTrue(pof.facebookSchedulePage().scheduleMaster(),"Scheduling Facebook Content");
			a.assertTrue(pof.facebookSchedulePage().enableandConfirmFacebook(),"Enabling and Confirming the Facebook Schudule for Job Id: "+facebookMasterId);
		
			Reporter.log("Loading View Facebook Page<br>", true);
			pof.viewFacebookPage().load().isLoaded();
			a.assertTrue(pof.viewFacebookPage().verifyFacebookMasterStatus(facebookMasterId,PagesHelper.STATUSPUBLISHED), "Verify Facebook Status for Master Id: "+facebookMasterId);
			a.assertAll();
		}
}
