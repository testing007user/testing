package com.yesmail.qa.pageobjects;

import org.openqa.selenium.WebDriver;


import com.yesmail.qa.pageobjects.campaign.CreateCampaignPage;
import com.yesmail.qa.pageobjects.campaigns.CampaignManagement;
import com.yesmail.qa.pageobjects.imports.Import;
import com.yesmail.qa.pageobjects.imports.ImportHomePage;
import com.yesmail.qa.pageobjects.login.HomePage;
import com.yesmail.qa.pageobjects.login.LoginPage;

import com.yesmail.qa.pageobjects.mvt.*;

import com.yesmail.qa.pageobjects.sms.SmsContentPage;
import com.yesmail.qa.pageobjects.sms.SmsHeaderPage;
import com.yesmail.qa.pageobjects.sms.SmsSchedulePage;
import com.yesmail.qa.pageobjects.sms.SmsTargetPage;
import com.yesmail.qa.pageobjects.subscriber.SubscribersPage;
import com.yesmail.qa.test.configuration.XMLParser;

/**
 * PageObjectFactory gives object for pages
 * 
 * @author rahull
 * 
 */
public class PageObjectFactory {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private ImportHomePage importHomePage;
	private Import importPage;
	private SubscribersPage subscriberPage;
	
	private TestContentPage testContentPage;
	private TestEnvelopPage testEnvelopPage;
	private TestSchedulePage testSchedulePage;
	private TestSetupPage testSetupPage;
	private TestTargetPage testTargetPage;
	private CreateCampaignPage createCampaignPage;
	private ViewTestPage viewTestPage;
	
	private SmsHeaderPage smsHeaderPage;
	private SmsContentPage smsContentPage;
	private SmsTargetPage smsTargetPage;
	private SmsSchedulePage smsSchedulePage;
	
	private CampaignManagement campaignManagement;


	public PageObjectFactory(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage loginPage() {
		if (loginPage == null)
			loginPage = new LoginPage(driver);
		return loginPage;
	}
	
	public HomePage homePage()
	{
		if(homePage == null)
			homePage = new HomePage(driver);
		return homePage;
	}

	public ImportHomePage importHomePage() {
		if (importHomePage == null)
			importHomePage = new ImportHomePage(driver,
					XMLParser.readComponentValueFromXML(PAGE_NAME.ImportHomePage,PAGE_ATTRI.pageUrl));
		return importHomePage;
	}
	
	public Import importPage()
	{
		if(importPage == null)
			importPage = new Import(driver);
		return importPage;
	}

	public SubscribersPage subscriberPage() {
		if (subscriberPage == null)
			subscriberPage = new SubscribersPage(driver,
					XMLParser.readComponentValueFromXML(PAGE_NAME.Subscribers,PAGE_ATTRI.pageUrl));
		return subscriberPage;
	}

	public TestContentPage testContentPage() {
		if (testContentPage == null)
			testContentPage = new TestContentPage(driver, "");
		return testContentPage;
	}

	public TestEnvelopPage testEnvelopPage() {
		if (testEnvelopPage == null)
			testEnvelopPage = new TestEnvelopPage(driver, "");
		return testEnvelopPage;
	}

	public TestSchedulePage testSchedulePage() {
		if (testSchedulePage == null)
			testSchedulePage = new TestSchedulePage(driver, "");
		return testSchedulePage;
	}

	public TestSetupPage testSetupPage() {
		if (testSetupPage == null)
			testSetupPage = new TestSetupPage(driver,
					XMLParser
							.readComponentValueFromXML("TestSetupPage.pageUrl"));
		return testSetupPage;
	}

	public TestTargetPage testTargetPage() {
		if (testTargetPage == null)
			testTargetPage = new TestTargetPage(driver, "");
		return testTargetPage;
	}
	
	public ViewTestPage viewTestPage()
	{
		if(viewTestPage == null)
			viewTestPage = new ViewTestPage(driver,XMLParser.readComponentValueFromXML("MasterStatus.pageUrl"));
		return viewTestPage;
	}
	
	public SmsHeaderPage smsHeaderPage()
	{
		if(null == smsHeaderPage)
			smsHeaderPage = new SmsHeaderPage(driver, XMLParser.readComponentValueFromXML(PAGE_NAME.SMSHeader, PAGE_ATTRI.pageUrl));
		return smsHeaderPage;
	}
	
	public SmsContentPage smsContentPage()
	{
		if(smsContentPage == null)
			smsContentPage = new SmsContentPage(driver, XMLParser.readComponentValueFromXML("SMSContent.pageUrl"));
		return smsContentPage;
	}
	
	public SmsTargetPage smsTargetPage()
	{
		if(smsTargetPage == null)
			smsTargetPage = new SmsTargetPage(driver, XMLParser.readComponentValueFromXML("SMSTarget.pageUrl"));
		return smsTargetPage;
	}
	
	public SmsSchedulePage smsSchedulePage()
	{
		if(smsSchedulePage == null)
			smsSchedulePage = new SmsSchedulePage(driver, XMLParser.readComponentValueFromXML("SMSSchedule.pageUrl"));
		return smsSchedulePage;
	}
	
	public CampaignManagement campaignManagement()
	{
		if(campaignManagement == null)
			campaignManagement = new CampaignManagement(driver, XMLParser.readComponentValueFromXML("Campaign.pageUrl"));
		return campaignManagement;
	}

	public CreateCampaignPage createCampaignPage() {
		if (createCampaignPage == null)
			createCampaignPage = new CreateCampaignPage(
					driver,
					XMLParser
							.readComponentValueFromXML("CreateCampaignPage.pageUrl"));
		return createCampaignPage;

	}
}
