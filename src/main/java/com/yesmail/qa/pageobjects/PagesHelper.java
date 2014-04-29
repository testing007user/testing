package com.yesmail.qa.pageobjects;

import com.yesmail.qa.framework.libraries.IProperty;
import com.yesmail.qa.test.configuration.ConfigurationClass;

/***
 * Page helper class gives test data required for test cases
 * 
 * @author rahull
 * 
 */
public class PagesHelper {

	static  IProperty prop = ConfigurationClass.PagesHelperProp;
	

	private PagesHelper() {

	}

	public static void intialize(IProperty prop) {
		PagesHelper.prop = prop;

	}
	
	public final static String YAHOO_USERNAME = prop.getValue("yahooUserName");
	public final static String YAHOO_PASSWORD = prop.getValue("yahooPassword");		
		
}
