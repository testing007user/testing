/**File name : RetryAnalyzer
 * Description: TestNg Listner to Re-Run test cases based on the count
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.testng.support;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.yesmail.qa.framework.configuration.CommandLineArgs;

/***
 * For Re-Running Failed Test Cases
 * 
 * @author kapilag
 * 
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private static final Logger log = Logger.getLogger(RetryAnalyzer.class);
	private int count = 0;
	private int maxCount;

	public boolean retry(ITestResult result) {
		maxCount = CommandLineArgs.getRetryFailedTestCaseCount();
		log.debug("Max retry count for a Test case is: " + maxCount);
		if (count < maxCount) {
			log.info("Error in " + result.getName() + " with status "
					+ result.getStatus() + " Retrying " + count + " times");
			count++;
			return true;
		}
		return false;
	}

}
