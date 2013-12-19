/**File name : Driver
 * Description: This  Class is 
 * used to getDriver. 
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */
package com.yesmail.qa.framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.yesmail.qa.framework.configuration.CommandLineArgs;

/***
 * This class return Driver
 * 
 * @author kapilag
 * 
 */
public class Driver {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(Driver.class);

	private static InheritableThreadLocal<WebDriver> driver = new InheritableThreadLocal<WebDriver>();

	/***
	 * sets the driver in ThreadLocal
	 */
	protected static void setDriverValue() {
		DriverFactory df = new DriverFactory(CommandLineArgs.getBrowserName(),
				CommandLineArgs.getRemoteFlag(), CommandLineArgs.getRemoteURL());
		driver.set(df.intializeDriver());
	}

	/***
	 * return the driver instance for a particular thread
	 * 
	 * @return
	 * @author kapilag
	 */
	public static WebDriver getDriver() {
		try {
			return driver.get();
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * This method checks if driver present yes then quit else ignore
	 */
	protected static void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}

	}

}
