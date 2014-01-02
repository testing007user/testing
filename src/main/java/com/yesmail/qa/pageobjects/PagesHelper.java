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

	public final static String EMAIL_MASTER = prop.getValue("email.master");
	public final static String URL = prop.getValue("URL");
	public final static String USERNAME = prop.getValue("USERNAME");
	public final static String PASSWORD = prop.getValue("PASSWORD");

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
       
     //Added for Facebook
     	
     	public final static String FACEBOOK_ACCOUNT=prop.getValue("facebook.content.account");
     	public final static String FACEBOOK_ACCOUNT_PAGE=prop.getValue("facebook.content.page");
     	public final static String FACEBOOK_CONTENT_CAMPAIGN_NAME= prop.getValue("facebook.content.campaign.name");
     	public final static String FACEBOOK_CONTENT_NAME = prop.getValue("facebook.content.name");
     	public final static String FACEBOOK_CONTENT_DESC = prop.getValue("facebook.content.description");
     	public final static String FACEBOOK_PAGE_NAME = prop.getValue("facebook.page.name");
     	public final static String FACEBOOK_NAME = prop.getValue("facebook.name");
   	
     //Added for Tweets
     	public final static String TWEETS_CONTENT_NAME=prop.getValue("tweets.content.name");

     	//Added for HomePage
    	public final static String HOME_COMPANY_SELECT=prop.getValue("home.company.name");
    	public final static String HOME_SEARCH_MMID=prop.getValue("home.search.mmid");

    	
    	//Added for SMS
    	
    	public final static String SMSVIEWBY = "All active email masters";

    	public final static String STATUSENABLED = "enabled";
    	public final static String STATUSDELIVERED = "delivered";	
    	public final static String STATUSPUBLISHED = "published";
    	public final static String STATUSHALTED = "halted";
    	public final static String STATUSDISABLED= "disabled";
}
