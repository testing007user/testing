/**File name : DriverIntialization
 * Description: This  Class is TestNG Listner for seting WebDriver 
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework;

import java.io.File;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.Reporter;
import org.testng.ITestResult;
import com.yesmail.qa.framework.configuration.CommandLineArgs;
import com.yesmail.qa.framework.testng.support.HtmlTable;
import com.yesmail.qa.framework.testng.support.RetryIAnnotationTransformer;
import com.yesmail.qa.framework.testng.support.SAssert;

/***
 * This class is for Setting and Quitig Driver
 * 
 * @author kapilag
 * 
 */
public class DriverInitialization implements IInvokedMethodListener {
	private static final Logger log = Logger
			.getLogger(DriverInitialization.class);
	// output dir of TestNg currently being used in SAssert
	public static String outPutDir;

	/***
	 * This Method Set the driver if @BeforeMethod Configuration present if not
	 * then set the driver for @Test Methods
	 * 
	 * @author kapilag
	 */
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Setting the output directory
		if (outPutDir == null)
			outPutDir = testResult.getTestContext().getOutputDirectory();
		if (method.getTestMethod().isBeforeMethodConfiguration()) {
			log.info("Setting the WebDriver in Before Method");
			Driver.setDriverValue();
		}

		if (method.isTestMethod()) {
			// intialize AssertMap to be used in downstream threads,if not done
			// will produce unexpected output
			SAssert.assertMap.get();
			if (Driver.getDriver() == null) {
				Driver.setDriverValue();
				log.info("As the Driver is not set in Before Method setting it here. Method Name "
						+ "is :" + method.getTestMethod().getMethodName());
			}
		}
	}

	/***
	 * This method quits the Driver and generate table report if Before Method
	 * is not present and After Method is present then Driver.getDriver() in
	 * after method will be null
	 * 
	 * @author kapilag
	 */
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			Class<?> className = method.getTestMethod()
					.getConstructorOrMethod().getDeclaringClass();
			boolean beforeMethodPresent = checkBeforeMethod(className);
			boolean afterMethodShouldbePresent = checkAfterMethod(className);

			publishHtmlTable(testResult);
			addScreenShot(testResult);

			if (beforeMethodPresent) {
				if (!afterMethodShouldbePresent) {
					cleanup(method, testResult);
				}
			} else {
				cleanup(method, testResult);
			}
		}
		// Check for AfterMethod if present check for browser and quit
		if (method.getTestMethod().isAfterMethodConfiguration()) {
			if (Driver.getDriver() != null) {
				cleanup(method, testResult);
			}
		}

	}

	/***
	 * This Method Check for BeforeMethod in the given class and All super class
	 * till java.lang.Object
	 * 
	 * @param className
	 * @return
	 * @author kapilag
	 */
	private boolean checkBeforeMethod(Class<?> className) {
		try{
		String cName = className.getName();
		Class<?> classN = className;
		while (!cName.contains("java.lang.Object")) {
			if (RetryIAnnotationTransformer.beforeMethodClasses.contains(cName))
				return true;
			else {
				classN = classN.getSuperclass();
				cName = classN.getName();
			}
		}
		return false;
		}catch(Exception e){
			log.info("Catching exception in checkBeforeMethod and returning false");
			e.printStackTrace();
			return false;
		
		}
	}

	/***
	 * For quiting the driver and clearing AssertMap in Soft Assert
	 * 
	 * @param method
	 * @param testResult
	 * @author kapilag
	 */
	private void cleanup(IInvokedMethod method, ITestResult testResult) {
		try {
			Reporter.setCurrentTestResult(testResult);
			log.info("Quiting Driver for Method:"
					+ method.getTestMethod().getMethodName());
			if (Driver.getDriver() != null)
				Driver.tearDown();
		} catch (Exception e) {
			log.info("Catching Exception in After Invocation So test Result are not altered due to it");
			e.printStackTrace();
		} finally {
			if (Driver.getDriver() != null)
				Driver.tearDown();
			SAssert.m_errors.get().clear();
			SAssert.assertMap.get().clear();
		}

	}

	/**
	 * This Method Check for AfterMethod in the given class and All super class
	 * till java.lang.Object
	 * 
	 * @param className
	 * @return
	 * @author kapilag
	 */
	private boolean checkAfterMethod(Class<?> className) {
		try {
			String cName = className.getName();
			Class<?> classN = className;
			while (!cName.contains("java.lang.Object")) {
				if (RetryIAnnotationTransformer.afterMethodClasses
						.contains(cName))
					return true;
				else {
					classN = classN.getSuperclass();
					cName = classN.getName();
				}
			}
			return false;
		} catch (Exception e) {
			log.info("Catching exception in CheckAfterMethod and returnig false");
			e.printStackTrace();
			return false;
		}
	}

	/***
	 * This method adds HTML Table with data in the output report
	 * 
	 * @param testResult
	 */
	private void publishHtmlTable(ITestResult testResult) {
		try {
			Reporter.setCurrentTestResult(testResult);
			HtmlTable report = new HtmlTable(SAssert.assertMap.get(),
					testResult.getName());
			Reporter.log("Table Report is:::" + report.getTable());
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Catching exception in public HTML Method");
		}
	}

	/***
	 * This method add the screen shot to test case due to throwable exception
	 * other than Soft Assert
	 * 
	 * @param testResult
	 * @author kapilag
	 */
	private void addScreenShot(ITestResult testResult) {
		try{
		Reporter.setCurrentTestResult(testResult);
		if (CommandLineArgs.getScreenShotFlag()) {
			if (testResult.getThrowable() != null) {
				String throwMessage = (testResult.getThrowable().getMessage() != null) ? testResult
						.getThrowable().getMessage() : "";
				if (!throwMessage.contains("asserts failed")) {

					String outPutDirectory = testResult.getTestContext()
							.getOutputDirectory();
					String filePath = outPutDirectory + File.separator
							+ UUID.randomUUID() + ".png";
					DriverUtility.takeScreenShot(Driver.getDriver(), filePath);
					// Append the screen Shot in the Reporter Log
					Reporter.log("Test Case -" + testResult.getName()
							+ " failed due to exception screen shot below");
					Reporter.log("<div style=\"height:400px; width: 750px; overflow:scroll\"><img src=\""
							+ filePath + "\"></div>");

				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			log.info("Catching exception in add screen shot Method");
		}
	}

}
