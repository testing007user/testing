package com.yesmail.qa.test.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.testng.support.SAssert;
import com.yesmail.qa.pageobjects.PageObjectFactory;
import com.yesmail.qa.pageobjects.PagesHelper;

public class ReportTest {
	
String reportId = null;

SAssert a = new SAssert(); 
	
	@Test(testName = "requestReport", description = "Request Report", timeOut = 500000, enabled = true, groups = {"BAT","Report"})
	public void requestReport() {
		PageObjectFactory pof = new PageObjectFactory(Driver.getDriver());		
		pof.loginPage().load().isLoaded();
		pof.loginPage().loginAs(PagesHelper.USERNAME, PagesHelper.PASSWORD);
		pof.homePage().isLoaded();
		
		pof.requestReportsPage().load().isLoaded();
		pof.requestReportsPage().selectReportType(PagesHelper.REPORT_TYPE);
		pof.requestReportsPage().selectMailingView(PagesHelper.REPORT_VIEW);
		reportId = pof.requestReportsPage().getReportId();
		pof.requestReportsPage().clickRequestReport();
		Reporter.log("Report Generated for Id: "+reportId+"<br>");
		
		pof.reportsPage().isLoaded();
		pof.reportsPage().selectDropDownOnReportsPage();
		a.assertTrue(pof.reportsPage().verifyReportsStatus(reportId, PagesHelper.STATUSFINISHED),"Verify Status of Report Id: "+reportId);				
		
	}
}
