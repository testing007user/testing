/**File name : CustomFrameworkProperties
 * Description: This  Class is used to load custom properties file
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */
package com.yesmail.qa.framework.configuration;

import org.apache.log4j.Logger;

import com.yesmail.qa.framework.libraries.IProperty;
import com.yesmail.qa.framework.libraries.PropertyMapping;

/***
 * This class if for providing custom initialization to framework other than
 * framework.properties file This should be called before CommandLineObject is
 * created or before Driver.getDriver()
 * 
 * @author kapilag
 * 
 */
public class CustomFrameworkProperties {
	private static final Logger log = Logger
			.getLogger(CustomFrameworkProperties.class);

	protected static IProperty prop;

	private CustomFrameworkProperties() {

	}

	/***
	 * Load Custom Properties file and intialize CommandLineArgs This file
	 * Overrides all the Paramters of CommandLineArgs With Cluster Specific
	 * Param
	 * 
	 * @param filePath
	 * @author kapilag
	 */
	public static void loadPropFile(String filePath) {
		log.info("Loading New Properties File:" + filePath);
		prop = new PropertyMapping(filePath);
		CommandLineArgs.loadCustomPropertiesFile(prop);
	}

}
