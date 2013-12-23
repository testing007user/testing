/**File name : CommandLineArgs
 * Description: This  Class is contains logic for setting
 * variables from commandline or properties file 
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.configuration;

import org.apache.log4j.Logger;

import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.IProperty;
import com.yesmail.qa.framework.libraries.PropertyMapping;
import com.yesmail.qa.framework.libraries.Utils;

/***
 * This class set the variable required to configure Driver either from Command
 * Line or property file ,Command Line is given preference
 * 
 * @author kapilag
 * 
 */

public class CommandLineArgs {
	private static final Logger log = Logger.getLogger(CommandLineArgs.class);
	private static String browserName;
	private static boolean remoteFlag;
	private static String remoteURL;
	private static int driverTimeout;
	private static boolean screenShotFlag;
	private static int retryFailedTestCasesCount;
	private static boolean displayGui;
	private static boolean testLinkLog;
	private static String testLBuildName;
	private static String testLSuiteName;
	private static String testLPlanName;
	private static String testLProjectName;
	private static String testLPlatformName;
	private static String testLDevkey;
	private static String testLURL;
	private static String chromeServerPath;
	private static String ieServerPath;
	private static IProperty frameworkProp;
	private static IProperty customProp;

	/***
	 * hiding the constructor
	 */
	private CommandLineArgs() {

	}

	public static String getBrowserName() {
		return browserName;
	}

	public static boolean getRemoteFlag() {
		return remoteFlag;
	}

	public static String getRemoteURL() {
		return remoteURL;
	}

	public static String getChromeServerPath() {
		return chromeServerPath;
	}

	public static String getIeServerPath() {
		return ieServerPath;
	}

	public static int getDriverTimeOut() {
		return driverTimeout;
	}

	public static boolean getScreenShotFlag() {

		return CommandLineArgs.screenShotFlag;
	}

	public static int getRetryFailedTestCaseCount() {
		return retryFailedTestCasesCount;
	}

	public static boolean getdisplayGuiFlag() {
		return displayGui;
	}

	public static boolean isTestLinkLog() {
		return testLinkLog;
	}

	public static String getTestLBuildName() {
		return testLBuildName;
	}

	public static String getTestLSuiteName() {
		return testLSuiteName;
	}

	public static String getTestLPlanName() {
		return testLPlanName;
	}

	public static String getTestLProjectName() {
		return testLProjectName;
	}

	public static String getTestLPlatformName() {
		return testLPlatformName;
	}

	public static String getTestLDevkey() {
		return testLDevkey;
	}

	public static String getTestLURL() {
		return testLURL;
	}

	static {
		// Sets value to static variables
		// ustomPropPresent = loadCustomPropertiesFile();
		intializeFields();

	}

	/***
	 * for setting all the fields
	 */
	private static void intializeFields() {
		log.debug("Intializing Commandline variables");
		loadDefaultProperties();
		setBrowser();
		setRemoteFlag();
		setRemoteURL();
		setDriverTimeout();
		setChromeServerPath();
		setIeServerPath();
		setScreenShotFlag();
		setRetryCountFailedTestCases();
		displayGUI();
		setTestLinkLog();
		setTestLBuildName();
		setTestLSuiteName();
		setTestLPlanName();
		setTestLProjectName();
		setTestLPlatformName();
		setTestLDevkey();
		setTestLURL();
		printConfiguredParams();
	}

	/***
	 * returns the browser from the command Line or set it to firefox
	 * 
	 * @return
	 * @author kapilag
	 */
	private static void setBrowser() {
		String value = getValue("browser");
		if (null != value)
			CommandLineArgs.browserName = value;
		else
			CommandLineArgs.browserName = "firefox";

	}

	/***
	 * check if remoteFlag present in the commandLine else set it true
	 * 
	 * @return
	 */
	private static void setRemoteFlag() {
		String value = getValue("remoteFlag");

		if (null != value)
			CommandLineArgs.remoteFlag = Boolean.valueOf(value);
		else
			CommandLineArgs.remoteFlag = false;

	}

	/***
	 * return remoteHubURL for the HUB
	 * 
	 * @return
	 */
	private static void setRemoteURL() {

		if (CommandLineArgs.remoteFlag) {
			String value = getValue("hubURL");
			if (null != value)
				CommandLineArgs.remoteURL = getValue("hubURL");
			else
				throw new IllegalStateException(
						"Provide URL either in commandLine framework");

		} else
			CommandLineArgs.remoteURL = "";
	}

	private static void setDriverTimeout() {
		String value = getValue("driverTimeOut");
		if (null != value)
			CommandLineArgs.driverTimeout = Integer.valueOf(value);
		else
			CommandLineArgs.driverTimeout = 10;

	}

	private static void setChromeServerPath() {
		if (!CommandLineArgs.remoteFlag) {
			if (CommandLineArgs.browserName.equalsIgnoreCase("chrome")) {
				String value = getValue("chromeServerPath");

				if (null != value)
					CommandLineArgs.chromeServerPath = value;
				else
					throw new FrameworkException(
							"Please set chromeServer Path ");
			}
		}

	}

	private static void setIeServerPath() {

		if (!CommandLineArgs.remoteFlag) {
			if (CommandLineArgs.browserName.equalsIgnoreCase("ie")) {

				String value = getValue("ieServerPath");
				if (null != value)
					CommandLineArgs.ieServerPath = value;
				else
					throw new FrameworkException("PLease set IE Server Path");
			}
		}
	}

	private static void setScreenShotFlag() {
		String value = getValue("screenShotFlag");
		if (value != null)
			CommandLineArgs.screenShotFlag = Boolean.valueOf(value);
		else
			CommandLineArgs.screenShotFlag = false;
	}

	private static void setRetryCountFailedTestCases() {
		String value = getValue("retryFailedTestCasesCount");
		if (null != value)
			CommandLineArgs.retryFailedTestCasesCount = Integer
					.valueOf(getValue("retryFailedTestCasesCount"));
		else
			CommandLineArgs.retryFailedTestCasesCount = 0;

	}

	private static void displayGUI() {
		String value = getValue("displayGui");
		if (null != value)
			CommandLineArgs.displayGui = Boolean.valueOf(value);
		else
			CommandLineArgs.displayGui = true;

	}

	private static void setTestLinkLog() {
		String value = getValue("testLinkLog");
		if (null != value)
			CommandLineArgs.testLinkLog = Boolean.valueOf(value);
		else
			CommandLineArgs.testLinkLog = false;

	}

	private static void setTestLBuildName() {
		if (testLinkLog) {
			String value = getValue("testLBuildName");
			if (null != value)
				CommandLineArgs.testLBuildName = value;
		} else
			CommandLineArgs.testLBuildName = "";
	}

	private static void setTestLSuiteName() {
		if (testLinkLog) {
			String value = getValue("testLSuiteName");
			if (null != value)
				CommandLineArgs.testLSuiteName = value;
			else
				CommandLineArgs.testLSuiteName = "";
		} else
			CommandLineArgs.testLSuiteName = "";
	}

	private static void setTestLPlanName() {
		if (testLinkLog) {
			String value = getValue("testLPlanName");
			if (null != value)
				CommandLineArgs.testLPlanName = value;
			else
				CommandLineArgs.testLPlanName = "";
		} else
			CommandLineArgs.testLPlanName = "";
	}

	private static void setTestLProjectName() {
		if (testLinkLog) {
			String value = getValue("testLProjectName");
			if (null != value)
				CommandLineArgs.testLProjectName = value;
			else
				CommandLineArgs.testLProjectName = "";

		} else
			CommandLineArgs.testLProjectName = "";
	}

	private static void setTestLPlatformName() {
		if (testLinkLog) {
			String value = getValue("testLPlatformName");
			if (null != value)
				CommandLineArgs.testLPlatformName = value;
			else
				CommandLineArgs.testLPlatformName = "";
		} else
			CommandLineArgs.testLPlatformName = "";
	}

	private static void setTestLDevkey() {
		if (testLinkLog) {
			String value = getValue("testLDevkey");
			if (null != value)
				CommandLineArgs.testLDevkey = value;
			else
				CommandLineArgs.testLDevkey = "";
		} else
			CommandLineArgs.testLDevkey = "";
	}

	private static void setTestLURL() {
		if (testLinkLog) {
			String value = getValue("testLURL");
			if (null != value)
				CommandLineArgs.testLURL = value;
			else
				CommandLineArgs.testLURL = "";
		} else
			CommandLineArgs.testLURL = "";
	}

	/***
	 * load the framework Properties file present in classpath
	 */
	private static void loadDefaultProperties() {
		if (null == frameworkProp) {
//			frameworkProp = new PropertyMapping(CommandLineArgs.class
//					.getResource("/Framework.properties").getPath().toString());
			frameworkProp = new PropertyMapping(Utils.getResources(CommandLineArgs.class,"Framework.properties"));
		}

	}

	/****
	 * Check if custom proerties file present or not if presnet intialize
	 * customProp to prop file
	 * 
	 * @return: true or false
	 */
	protected static boolean loadCustomPropertiesFile(IProperty prop) {
		if (prop == null) {
			return false;
		} else {
			log.debug("Loading new Properties File");
			customProp = CustomFrameworkProperties.prop;
			intializeFields();
			return true;
		}

	}

	/***
	 * Logic for Hierarchical order
	 * 
	 * @param key
	 *            :CommandLine paramters
	 * @return
	 */
	private static String getValue(String key) {
		if (System.getProperty(key) != null)
			return System.getProperty(key);

		if (customProp != null) {
			if (customProp.getValue(key) != null)
				return customProp.getValue(key);
		}

		if (frameworkProp.getValue(key) != null)
			return frameworkProp.getValue(key);

		return null;
	}

	/***
	 * This method print all the configuration parameters of the class
	 * 
	 * @author kapilag
	 */
	public static void printConfiguredParams() {
		log.info("-------------------------------------------------------------");
		log.info("------------------Configuration------------------------------");
		log.info("Browser Name::" + CommandLineArgs.browserName);
		log.info("Remote Browser Switch::" + CommandLineArgs.remoteFlag);
		if (CommandLineArgs.remoteFlag)
			log.debug("Configured HUB URL::" + remoteURL);
		else {
			if (CommandLineArgs.browserName.equalsIgnoreCase("chrome"))
				log.debug("Chrome Server Path::"
						+ CommandLineArgs.chromeServerPath);
			if (CommandLineArgs.browserName.equalsIgnoreCase("ie"))
				log.info("IE Server Path::" + CommandLineArgs.ieServerPath);
		}
		log.info("Screen Shot Switch for Taking Failed Test Case Screen Shot::"
				+ CommandLineArgs.screenShotFlag);
		log.info("Number of Times Failed TestCases to be Executed::"
				+ CommandLineArgs.retryFailedTestCasesCount);
		log.info("GUI Support On/OFF::" + CommandLineArgs.displayGui);
		log.info("Logging into TestLink Switch::" + CommandLineArgs.testLinkLog);
		if (CommandLineArgs.testLinkLog) {
			log.info("Test Link URL is::" + CommandLineArgs.testLURL);
			log.info("Dev Key for TestLink is::" + CommandLineArgs.testLDevkey);
			log.info("Test Link Build Name is::"
					+ CommandLineArgs.testLBuildName);
			log.info("Test Link Test Plan Name is::"
					+ CommandLineArgs.testLPlanName);
			log.info("Test Link Test Suite Name is::"
					+ CommandLineArgs.testLSuiteName);
			log.info("Test Link Project Name is::"
					+ CommandLineArgs.testLProjectName);
			log.info("Test Platform Name is::"
					+ CommandLineArgs.testLPlatformName);
		}

		log.info("-------------------------------------------------------------");
	}

}
