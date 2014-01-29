/**File name : DriverFactory
 * Description: This  Class is 
 * used to intialize driver. 
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.yesmail.qa.framework.configuration.CommandLineArgs;

/***
 * Driver factory Class for Returning Driver Instances
 * 
 * @author kapilag
 * 
 */
class DriverFactory {
	private static final Logger log = Logger.getLogger(DriverFactory.class);

	private boolean remoteFlag;
	private String browser;
	private String remoteURL;
	private DesiredCapabilities dc;

	public DriverFactory(String browser, boolean remote, String remoteURL) {
		this.browser = browser;
		this.remoteFlag = remote;
		this.remoteURL = remoteURL;
	}

	/***
	 * Return WebDriver either remote or noraml based on remoteFlag
	 * 
	 * @return
	 */
	public WebDriver intializeDriver() {
		WebDriver webDriver = null;

		if (remoteFlag) {
			webDriver = intializeRemoteWebDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			log.debug("Returning firefox driver-Without Remote.");
			webDriver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					CommandLineArgs.getIeServerPath());
			log.debug("Returning ie driver-Without Remote.");
			webDriver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					CommandLineArgs.getChromeServerPath());
			log.debug("Returning chrome driver-Without Remote.");
			webDriver = new ChromeDriver();
		}

		// For maximizing driver windows and wait
		if (webDriver != null)
		{
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(CommandLineArgs.getDriverTimeOut(),TimeUnit.SECONDS);
		}
		return webDriver;

	}

	/***
	 * sets the desired capability for remote browser
	 */
	private void setDesiredCapability() {
		if (remoteFlag) {
			if (browser.equalsIgnoreCase("firefox")) {
				log.debug("Returning REMOTE firefox driver.");
				dc = DesiredCapabilities.firefox();
				dc.setCapability("nativeEvents", false);
			} else if (browser.equalsIgnoreCase("ie")) {
				log.debug("Returning REMOTE IE driver.");
				dc = DesiredCapabilities.internetExplorer();
			} else if (browser.equalsIgnoreCase("chrome")) {
				log.debug("Returning REMOTE Chrome driver.");
				dc = DesiredCapabilities.chrome();
			}
		}

	}

	/***
	 * return WebDriver for Remote
	 * 
	 * @return
	 */
	private WebDriver intializeRemoteWebDriver() {
		try {
			setDesiredCapability();
			RemoteWebDriver rw = new RemoteWebDriver(new URL(remoteURL), dc);
			rw.setFileDetector(new LocalFileDetector());
			return rw;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
