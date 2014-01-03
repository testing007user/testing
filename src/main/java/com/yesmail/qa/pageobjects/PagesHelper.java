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

	public final static String EMAIL_MASTER = prop.getValue("email.master");
	public final static String URL = prop.getValue("URL");
	public final static String USERNAME = prop.getValue("USERNAME");
	public final static String PASSWORD = prop.getValue("PASSWORD");
	public static final String EMAIL_DIVISION = prop.getValue("email.division");
	public static final String EMAIL_CAMPAIGN_NAME = prop.getValue("email.campaign");
	public static final String EMAIL_ENCODING_TYPE = prop.getValue("email.encoding.type");

	//Added For MultiVariate
       public final static String MULTIVARIATE_SETUP_NAME = prop.getValue("multivariate.setuppage.name");
       public final static String MULTIVARIATE_SETUP_DESC = prop.getValue("multivariate.setuppage.description");
       public final static String MULTIVARIATE_SETUP_AUTOSEND_CRITERIA = prop.getValue("multivariate.setuppage.criteriaAutoSend");
       public final static String MULTIVARIATE_ENVELOPE_NAME = prop.getValue("multivariate.envelope.name");
       public final static String MULTIVARIATE_ENVELOPE_DESCRIPTION = prop.getValue("multivariate.envelope.description");
       public final static String MULTIVARIATE_ENVELOPE_DIVISION = prop.getValue("multivariate.envelope.division");
       public final static String MULTIVARIATE_CONTENT_NAME = prop.getValue("multivariate.content.name");
       public final static String MULTIVARIATE_CONTENT_DESC = prop.getValue("multivariate.content.description");
       public final static String MULTIVARIATE_TARGET_EMAILID = prop.getValue("multivariate.target.email");
       public final static String MULTIVARIATE_SEARCH_TEXT = prop.getValue("multivariate.search.mvt"); 
       public final static String MULTIVARIATE_TARGET_SUBSCRIPTION_STATUS = prop.getValue("multivariate.target.subscription");
       public final static String MULTIVARIATE_TARGET_FILTER_ATTRI = prop.getValue("multivariate.target.attributes");
       public final static String MULTIVARIATE_TARGET_FILTER_ATTRI_EMAIL= prop.getValue("multivariate.target.attremail");
       public final static String MULTIVARIATE_TARGET_FILTER_ATTRI_STRING= prop.getValue("multivariate.target.attristring");
       public final static String MULTIVARIATE_ENVELOPE_CAMPAIGN_NAME= prop.getValue("multivariate.envelope.campaign.name");
       public final static String MULTIVARIATE_ENVELOPE_ENCODING = "Western(ISO-8859-1)";
	

	
       


}
