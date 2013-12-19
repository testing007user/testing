/**File name : TestLinkUpdate
 * Description: This class updates test link with update method
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */


package com.yesmail.qa.framework.testlink;

import org.testng.ITestContext;
import org.testng.ITestResult;

import com.yesmail.qa.framework.configuration.CommandLineArgs;

/***
 * For updating TestLink
 * 
 * @author kapilag
 * 
 */
public class TestLinkUpdate {
	private static String testName;
	private static String testDesc;
	private static String url;
	private static String dev_key;
	private static String testsuitename;
	private static String testplanname;
	private static String testprojectname;
	private static String testbuildname;
	private static String testplatformname;

	/***
	 * This Method Check for the Command Line Parameters for Test Link if any
	 * found then set else look for Xml
	 */
	private static void setTestLParam() {
		testbuildname = CommandLineArgs.getTestLBuildName();
		testplanname = CommandLineArgs.getTestLPlanName();
		testsuitename = CommandLineArgs.getTestLSuiteName();
		testprojectname = CommandLineArgs.getTestLProjectName();
		testplatformname = CommandLineArgs.getTestLPlatformName();
		dev_key = CommandLineArgs.getTestLDevkey();
		url = CommandLineArgs.getTestLURL();
	}

	/***
	 * update test link with the status of the test
	 * 
	 * @param context
	 *            : test context having all the test and status
	 */
	public static void update(ITestContext context) {
		// intialize all the parameters from commandLine

		// create object of testlink

		// create Object
		if (CommandLineArgs.isTestLinkLog()) {
			setTestLParam();
			TestLinkWrapper t_w = new TestLinkWrapper(testsuitename,
					testplanname, testprojectname, testbuildname,
					testplatformname, dev_key, url, "");
			for (ITestResult t : context.getPassedTests().getAllResults()) {
				org.testng.annotations.Test ann = t.getMethod()
						.getConstructorOrMethod().getMethod()
						.getAnnotation(org.testng.annotations.Test.class);

				testName = (!ann.testName().isEmpty()) ? ann.testName() : t
						.getName();
				testDesc = (!ann.description().isEmpty()) ? ann.description()
						: t.getName();

				t_w.report(testName, testDesc, "pass");

			}

			for (ITestResult t : context.getSkippedTests().getAllResults()) {
				org.testng.annotations.Test ann = t.getMethod()
						.getConstructorOrMethod().getMethod()
						.getAnnotation(org.testng.annotations.Test.class);
				testName = (!ann.testName().isEmpty()) ? ann.testName() : t
						.getName();
				testDesc = (!ann.description().isEmpty()) ? ann.description()
						: t.getName();

				t_w.report(testName, testDesc, "block");

			}

			for (ITestResult t : context.getFailedTests().getAllResults()) {
				org.testng.annotations.Test ann = t.getMethod()
						.getConstructorOrMethod().getMethod()
						.getAnnotation(org.testng.annotations.Test.class);

				testName = (!ann.testName().isEmpty()) ? ann.testName() : t
						.getName();
				testDesc = (!ann.description().isEmpty()) ? ann.description()
						: t.getName();

				t_w.report(testName, testDesc, "fail");

			}
		}

	}

}
