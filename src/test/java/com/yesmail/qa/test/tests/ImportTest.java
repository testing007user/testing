package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;
import com.yesmail.qa.pageobjects.imports.Import.DELIMITER;
/**
 * This class will contain Import related testcases
 * @author achyutd
 *
 */
public class ImportTest {
	
	String jobId = null;	
	
	SAssert a = new SAssert(); 
	
	@Test(testName = "ImportSubscriber", description = "Importing Subscriber", timeOut = 500000, enabled = true, groups = {"BAT","DEMO"})
	public void ImportSubscriber() {		
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Loading Import HomePage <br>",true);
		pof.importHomePage().load().isLoaded();		
		jobId = pof.importPage().importScrubscribers(true, DELIMITER.PIPE);
		Reporter.log("jobId created: "+jobId+" <br>",true);
		a.assertTrue(jobId != null,"Imported Subscriber with Job Id: "+jobId);
	
		Reporter.log("Verifying Import status for Job Id: "+jobId+"<br>",true);
		Reporter.log("Loading Import HomePage <br>",true);
		pof.importHomePage().load().isLoaded();
		pof.importHomePage().selectDropDownOnImportPage();		
		a.assertTrue(pof.importHomePage().verifyStatusOnImportPage(jobId, PagesHelper.STATUSFINISHED),"Verifying Import status for Job Id: "+jobId);		
		a.assertAll();	
	}	

}
