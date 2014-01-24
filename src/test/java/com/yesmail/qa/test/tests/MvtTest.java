package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.DriverUtility.CHECK_UNCHECK;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;

public class MvtTest {
	
	SAssert a = new SAssert();
	
	@Test(testName = "testMultiVariateMastersContentAndSubject", description = "Verify create Test with both content and subject", timeOut = 800000, enabled = true, groups = {"BAT","Mvt","MvtSubContent"})
	public void testMultiVariateMastersContentAndSubject()
	{
		
		int subjectCount =2 ,contentCount = 2;
		int multivariateCount = subjectCount * contentCount;
		
		String masterName =Utils.getUniqueName(
				PagesHelper.MULTIVARIATE_SETUP_NAME, 20);		
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Test SetUp page <br>");
		pof.testSetupPage().load().isLoaded();
		a.assertTrue(pof.testSetupPage().fillSetUpPage(CHECK_UNCHECK.CHECK,true, PagesHelper.MULTIVARIATE_AUTOWINNING_CHECKBOX,masterName),"Create Test SetUp Page");
		Reporter.log("Test Master Name : "+masterName+"<br>",true);
		
		Reporter.log("Creating Test Envelope page <br>",true);
		pof.testEnvelopPage().load().isLoaded();		
		a.assertTrue(pof.testEnvelopPage().fillEnvelopePage(subjectCount),"Creating Test Enevelope Page");		
		
		Reporter.log("Creating Test Content page <br>",true);
		pof.testContentPage().load().isLoaded();
	    pof.testContentPage().addContent(contentCount);		
		a.assertTrue(pof.testContentPage().stepCompleted(3, 10), "Create Test Content Page");
		
		Reporter.log("Creating Test Target page <br>",true);
		pof.testTargetPage().load().isLoaded();
		a.assertTrue(pof.testTargetPage().clickSaveGetCount(PagesHelper.MULTIVARIATE_TARGET_ATTRIBUTENAME,PagesHelper.MULTIVARIATE_TARGET_ATTRIBUTEVALUE,PagesHelper.MULTIVARIATE_TARGET_SUBSCRIPTION_STATUS),"Craeting Test Target Page");
		
		Reporter.log("Creating Test Schedule page <br>",true);
		pof.testSchedulePage().load().isLoaded();
		a.assertTrue(pof.testSchedulePage().fillSchedulePage(PagesHelper.MULTIVARIATE_AUTOWINNING_CHECKBOX)," Create Test Schedule");
		
		Reporter.log("Enabling and Confirming Test <br>",true);
		a.assertTrue(pof.testSchedulePage().enableAndConfirmSchedule()," Create Test Enable and Confirm");
		
		Reporter.log("Verifying Test Master <br>",true);
		pof.testSetupPage().navigateToSetUpTab(); //Redirection to setup page not working. Added this workaround to navigate back to setup page.  
		pof.testSetupPage().isLoaded();
		a.assertEquals(pof.testSetupPage().createdMastersCount(multivariateCount), multivariateCount,"Verify Test Master");		
		a.assertAll();
		
	}

	
	@Test(testName = "testMultiVariateMastersOnlyContent", description = "Verify create Test with only content", timeOut = 800000, enabled = true, groups = {"BAT","Mvt","MvtContent"})
	public void testMultiVariateMastersOnlyContent()
	{
		int subjectCount =1 ,contentCount = 2;
		int multivariateCount = subjectCount * contentCount; 
		
		String masterName =Utils.getUniqueName(
				PagesHelper.MULTIVARIATE_SETUP_NAME, 20);		
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Test SetUp page <br>",true);
		pof.testSetupPage().load().isLoaded();
		a.assertTrue(pof.testSetupPage().fillSetUpPage(CHECK_UNCHECK.UNCHECK, true, PagesHelper.MULTIVARIATE_AUTOWINNING_CHECKBOX,masterName),"Create Test SetUp Page");
		Reporter.log("Test Master Name : "+masterName+"<br>",true);
		
		Reporter.log("Creating Test Envelope page <br>",true);
		pof.testEnvelopPage().load().isLoaded();		
		a.assertTrue(pof.testEnvelopPage().fillEnvelopePage(subjectCount),"Creating Test Enevelope Page");		
		
		Reporter.log("Creating Test Content page <br>",true);
		pof.testContentPage().load().isLoaded();
		pof.testContentPage().addContent(contentCount);		
		a.assertTrue(pof.testContentPage().stepCompleted(3, 10), "Create Test Content Page");
		
		Reporter.log("Creating Test Target page <br>",true);
		pof.testTargetPage().load().isLoaded();
		a.assertTrue(pof.testTargetPage().clickSaveGetCount(PagesHelper.MULTIVARIATE_TARGET_ATTRIBUTENAME,PagesHelper.MULTIVARIATE_TARGET_ATTRIBUTEVALUE,PagesHelper.MULTIVARIATE_TARGET_SUBSCRIPTION_STATUS),"Craeting Test Target Page");
		
		Reporter.log("Creating Test Schedule page <br>",true);
		pof.testSchedulePage().load().isLoaded();
		a.assertTrue(pof.testSchedulePage().fillSchedulePage(PagesHelper.MULTIVARIATE_AUTOWINNING_CHECKBOX)," Create Test Schedule");
		
		Reporter.log("Enabling and Confirming Test <br>",true);
		a.assertTrue(pof.testSchedulePage().enableAndConfirmSchedule()," Create Test Enable and Confirm");
		
		Reporter.log("Verifying Test Master <br>",true);
		pof.testSetupPage().navigateToSetUpTab(); //Redirection to setup page not working. Added this workaround to navigate back to setup page.  
		pof.testSetupPage().isLoaded();
		a.assertEquals(pof.testSetupPage().createdMastersCount(multivariateCount), multivariateCount,"Verify Test Master");		
		a.assertAll();
		
	}
	
	@Test(testName = "testMultiVariateMastersOnlySubject", description = "Verify create Test with only subject", timeOut = 800000, enabled = true, groups = {"BAT","Mvt","MvtSubject"})
	public void testMultiVariateMastersOnlySubject()
	{
		int subjectCount =2 ,contentCount = 1;
		int multivariateCount = subjectCount * contentCount; 
		
		String masterName =Utils.getUniqueName(
				PagesHelper.MULTIVARIATE_SETUP_NAME, 20);		
		
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		Reporter.log("Creating Test SetUp page <br>");
		pof.testSetupPage().load().isLoaded();
		a.assertTrue(pof.testSetupPage().fillSetUpPage(CHECK_UNCHECK.CHECK, false, PagesHelper.MULTIVARIATE_AUTOWINNING_CHECKBOX,masterName),"Create Test SetUp Page");
		Reporter.log("Test Master Name : "+masterName+"<br>",true);
		
		Reporter.log("Creating Test Envelope page <br>");
		pof.testEnvelopPage().load().isLoaded();		
		a.assertTrue(pof.testEnvelopPage().fillEnvelopePage(subjectCount),"Creating Test Enevelope Page");		
		
		Reporter.log("Creating Test Content page <br>");
		pof.testContentPage().load().isLoaded();
		pof.testContentPage().uploadFile();		
		a.assertTrue(pof.testContentPage().stepCompleted(3, 10), "Create Test Content Page");
		
		Reporter.log("Creating Test Target page <br>");
		pof.testTargetPage().load().isLoaded();
		a.assertTrue(pof.testTargetPage().clickSaveGetCount(PagesHelper.MULTIVARIATE_TARGET_ATTRIBUTENAME,PagesHelper.MULTIVARIATE_TARGET_ATTRIBUTEVALUE,PagesHelper.MULTIVARIATE_TARGET_SUBSCRIPTION_STATUS),"Craeting Test Target Page");
		
		Reporter.log("Creating Test Schedule page <br>");
		pof.testSchedulePage().load().isLoaded();
		a.assertTrue(pof.testSchedulePage().fillSchedulePage(PagesHelper.MULTIVARIATE_AUTOWINNING_CHECKBOX)," Create Test Schedule");
		
		Reporter.log("Enabling and Confirming Test <br>");
		a.assertTrue(pof.testSchedulePage().enableAndConfirmSchedule()," Create Test Enable and Confirm");
		
		Reporter.log("Verifying Test Master <br>",true);
		pof.testSetupPage().navigateToSetUpTab(); //Redirection to setup page not working. Added this workaround to navigate back to setup page.  
		pof.testSetupPage().isLoaded();
		a.assertEquals(pof.testSetupPage().createdMastersCount(multivariateCount), multivariateCount,"Verify Test Master");		
		a.assertAll();
		
	}

}
