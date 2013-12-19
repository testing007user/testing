/**File name : TestInterceptor
 * Description: TestNG Listner for
 * 1>console print
 * 2>GUI update
 * 3>TestLink Update
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.testng.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.yesmail.qa.framework.testlink.TestLinkUpdate;
/***
 * TestNG Listner for updating
 * 1>console print
 * 2>GUI update
 * 3>TestLink Update
 * @author kapilag
 *
 */
public class TestInterceptor implements ITestListener {

	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestInterceptor.class);
	private boolean LogToStandardOutput = true;
	private static int count = 1;

	public void onTestStart(ITestResult arg0) {
		Reporter.setCurrentTestResult(arg0);
		Reporter.log("<br>");
		Reporter.log(
				"********************************************************",
				LogToStandardOutput);
		Reporter.log("</br>");
		int no = count++;
		Reporter.log(no + "::Initiating TestCase::" + arg0.getName()
				+ " (" + arg0.getMethod().getId() + ")", LogToStandardOutput);
		log.debug(no + "::Initiating TestCase::" + arg0.getName()
				+ " (" + arg0.getMethod().getId() + ")");
		Reporter.log("</br>");
		Reporter.log("Start Time::" + getTimeReport(), LogToStandardOutput);
		log.debug("Start Time::" + getTimeReport());
		Reporter.log("</br>");
		GuiHelper.checkAndUpdateStatus(arg0.getMethod().getMethodName(),
				"Executing...");
	}

	public void onTestSuccess(ITestResult arg0) {
		Reporter.log("End Time " + getTimeReport(), LogToStandardOutput);
		log.debug("End Time " + getTimeReport());
		Reporter.log("</br>");
		long ms = arg0.getEndMillis() - arg0.getStartMillis();
		Reporter.log("Execution Time :: " + ms / 1000 + "." + ms % 1000
				+ " Seconds", LogToStandardOutput);
		Reporter.log("</br>");
		Reporter.log("Completed TestCase :: " + arg0.getName()
				+ " => Status: PASS", LogToStandardOutput);
		log.debug("Completed TestCase :: " + arg0.getName()
				+ " => Status: PASS");
		Reporter.log("</br>");
		Reporter.log(
				"********************************************************",
				LogToStandardOutput);
		GuiHelper
				.checkAndUpdateStatus(arg0.getMethod().getMethodName(), "Pass");
	}

	public void onTestFailure(ITestResult arg0) {
		Reporter.log("End Time " + getTimeReport(), LogToStandardOutput);
		log.debug("End Time " + getTimeReport());
		Reporter.log("</br>");
		long ms = arg0.getEndMillis() - arg0.getStartMillis();
		Reporter.log("Execution Time :: " + ms / 1000 + "." + ms % 1000
				+ " Seconds", LogToStandardOutput);
		Reporter.log("</br>");
		Reporter.log("Completed TestCase :: " + arg0.getName()
				+ " => Status: FAIL", LogToStandardOutput);
		log.debug("Completed TestCase :: " + arg0.getName()
				+ " => Status: FAIL");
		Reporter.log("</br>");
		GuiHelper
				.checkAndUpdateStatus(arg0.getMethod().getMethodName(), "Fail");
	}

	public void onTestSkipped(ITestResult result) {

		GuiHelper.checkAndUpdateStatus(result.getMethod().getMethodName(),
				"Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		Reporter.log("Starting with suite :: "
				+ context.getSuite().getParallel());
	}

	/***
	 * This is used for custom reports due to retry Analyzer
	 * Setting the id will take care of Data Provider as well
	 * 
	 * @param result
	 * @return
	 */
	public static int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = 31 * id + result.getMethod().getMethodName().hashCode();
		id = 31
				* id
				+ (result.getParameters() != null ? Arrays.hashCode(result
						.getParameters()) : 0);
		return id;
	}

	/***
	 * This is for retry Analyzer report Logic is Passed = Overall Passed
	 * Failed, Passed = Overall Passed Failed ,Failed = Overall Failed Test Case
	 * will be logged once
	 * 
	 * TestLink will also get Updated Based on the input Params
	 */
	public void onFinish(ITestContext context) {

		List<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		Set<Integer> passedTest = new HashSet<Integer>();
		// Create passTest List
		for (ITestResult passTest : context.getPassedTests().getAllResults()) {
			passedTest.add(getId(passTest));
		}

		Set<Integer> failedTestID = new HashSet<Integer>();
		// create Fail test case list and list which are duplicate in short test
		// case to be removed
		for (ITestResult failTest : context.getFailedTests().getAllResults()) {
			int failTestID = getId(failTest);
			if (failedTestID.contains(failTestID)
					|| passedTest.contains(failTestID))
				testsToBeRemoved.add(failTest);
			else
				failedTestID.add(failTestID);
		}
		// update the context
		for (Iterator<ITestResult> iterator = context.getFailedTests()
				.getAllResults().iterator(); iterator.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				iterator.remove();
			}
		}
		// update TestLink
		TestLinkUpdate.update(context);
	}

	private String getTimeReport() {
		Calendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		return ":: " + hour + ":" + minutes + ":" + seconds;
	}

}
