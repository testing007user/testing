
package com.yesmail.qa.test.configuration;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import com.yesmail.qa.framework.configuration.CustomFrameworkProperties;
import com.yesmail.qa.framework.exception.FrameworkError;
import com.yesmail.qa.framework.libraries.IProperty;
import com.yesmail.qa.framework.libraries.PropertyMapping;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

/***
 * This class is initialization of configuration data for Test cases
 * 
 * @author rahull
 * 
 */
public class ConfigurationClass {

	private static Logger log = Logger.getLogger(ConfigurationClass.class);
	
	public static IProperty PagesHelperProp;
	
	/***
	 * For Configuration of Test Data
	 */
	@BeforeSuite(alwaysRun = true)
	public void IntializeAll() {
		log.info("In Before Suite");
		switchReportNgEscapeProperty(true);
		switchWebDriverLogs(true);
		CustomFrameworkProperties.loadPropFile(getCustomEnvironmentFilePath());
		PagesHelperProp = new PropertyMapping(getPropertyFilePath());
		PagesHelper.intialize(PagesHelperProp);
	}

	/***
	 * Return the env File Path for the given env-type
	 * 
	 * @return
	 */
	public String getCustomEnvironmentFilePath() {
		String customEnvironmentPath = null;
		String environmentFileName =  System.getProperty("env-type").trim() + "-env.properties";
		customEnvironmentPath = Utils.getResources(this,environmentFileName);
		if (customEnvironmentPath != null) {
			return customEnvironmentPath;
		} else {
			throw new FrameworkError(
					"Not able find Properties file for environment:"
							+ System.getProperty("env-type")
							+ ". Constructed Path will be:"
							+ customEnvironmentPath);
		}

	}

	/***
	 * return Pages Helper path for the given environment
	 * 
	 * @return
	 */
	public String getPropertyFilePath() {
		String pagesHelperFilePath = null;
		String pagesHelperFileName = System.getProperty("env-type").trim()
				+ "-pagesHelper.properties";
		pagesHelperFilePath = Utils.getResources(this, pagesHelperFileName);
		if (pagesHelperFilePath != null) {			
			return pagesHelperFilePath;
		} else {
			throw new FrameworkError(
					"Not able find Properties file for environment:"
							+ System.getProperty("env-type")
							+ ". Constructed Path will be:"
							+ pagesHelperFilePath);
		}
	}
	
	/**
	 * For Report NG HTML_ESCAPE Property
	 * @param value
	 */
	public void switchReportNgEscapeProperty(boolean value)
	{
		if(value)
		{
			final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
			System.setProperty(ESCAPE_PROPERTY, "false");
		}
	}
	
	/***
	 * For Switching on/off WebDriver detailed logs
	 * @param value
	 */
	public void switchWebDriverLogs(boolean value)
	{
		if(value)
		{
			System.setProperty("org.apache.commons.logging.Log",
					"org.apache.commons.logging.impl.SimpleLog");
			System.setProperty(
					"org.apache.commons.logging.simplelog.log.org.apache.http",
					"warn");
		}
	}

}
