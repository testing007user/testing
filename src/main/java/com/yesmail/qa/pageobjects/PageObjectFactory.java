package com.yesmail.qa.pageobjects;

import org.openqa.selenium.WebDriver;

import com.yesmail.qa.pageobjects.aftertheclick.AfterTheClickPage;
import com.yesmail.qa.pageobjects.campaigns.CampaignManagement;
import com.yesmail.qa.pageobjects.campaigns.CreateCampaignPage;
import com.yesmail.qa.pageobjects.distributionlist.CreateDistributionListPage;
import com.yesmail.qa.pageobjects.distributionlist.DistributionListPage;

import com.yesmail.qa.pageobjects.attributes.DataAttributesPage;
import com.yesmail.qa.pageobjects.content.ContentLibraryPage;
import com.yesmail.qa.pageobjects.counts.CountsPage;
import com.yesmail.qa.pageobjects.email.EmailContentPage;
import com.yesmail.qa.pageobjects.email.EmailEnvelopePage;
import com.yesmail.qa.pageobjects.email.EmailSchedulePage;
import com.yesmail.qa.pageobjects.email.EmailSummaryPage;
import com.yesmail.qa.pageobjects.email.EmailTargetPage;
import com.yesmail.qa.pageobjects.email.ViewEmailPage;

import com.yesmail.qa.pageobjects.facebook.FacebookContentPage;
import com.yesmail.qa.pageobjects.facebook.FacebookSchedulePage;
import com.yesmail.qa.pageobjects.facebook.ViewFacebookPage;

import com.yesmail.qa.pageobjects.email.YahooPage;

import com.yesmail.qa.pageobjects.homepage.HomePage;
import com.yesmail.qa.pageobjects.imports.Import;
import com.yesmail.qa.pageobjects.imports.ImportHomePage;
import com.yesmail.qa.pageobjects.login.LoginPage;
import com.yesmail.qa.pageobjects.mvt.*;
import com.yesmail.qa.pageobjects.reports.ReportsPage;
import com.yesmail.qa.pageobjects.reports.RequestReportsPage;
import com.yesmail.qa.pageobjects.sms.SmsContentPage;
import com.yesmail.qa.pageobjects.sms.SmsHeaderPage;
import com.yesmail.qa.pageobjects.sms.SmsSchedulePage;
import com.yesmail.qa.pageobjects.sms.SmsSummaryPage;
import com.yesmail.qa.pageobjects.sms.SmsTargetPage;
import com.yesmail.qa.pageobjects.sms.ViewSmsPage;
import com.yesmail.qa.pageobjects.subscriber.SubscribersPage;
import com.yesmail.qa.pageobjects.tweets.TweetsContentPage;
import com.yesmail.qa.pageobjects.tweets.TweetsSchedulePage;
import com.yesmail.qa.pageobjects.tweets.ViewTweetsPage;
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
	private ViewTweetsPage viewTweetsPage;

	private SmsHeaderPage smsHeaderPage;
	private SmsContentPage smsContentPage;
	private SmsTargetPage smsTargetPage;
	private SmsSchedulePage smsSchedulePage;

	private AfterTheClickPage afterTheClickPage;
	private TweetsContentPage tweetsContentPage;
	private TweetsSchedulePage tweetsSchedulePage;
	private DistributionListPage distributionListPage;
	private CreateDistributionListPage createDistributionListPage;
	private ViewSmsPage viewSmsPage;

	private DataAttributesPage dataAttributesPage;
	private EmailEnvelopePage emailEnvelopePage;
	private EmailContentPage emailContentPage;
	private EmailTargetPage emailTargetPage;
	private EmailSchedulePage emailSchedulePage;
	private ViewEmailPage viewEmailPage;
	private CountsPage countsPage;
	private ContentLibraryPage contentLibraryPage;

	private CampaignManagement campaignManagement;

	private ReportsPage reportsPage;
	private RequestReportsPage requestReportsPage;
	private FacebookContentPage facebookContentPage;
	private FacebookSchedulePage facebookSchedulePage;
	private ViewFacebookPage viewFacebookPage;
	private EmailSummaryPage emailSummaryPage;
	private SmsSummaryPage smsSummaryPage;

	private YahooPage yahooPage;

	public PageObjectFactory(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage loginPage() {
		if (loginPage == null)
			loginPage = new LoginPage(driver);
		return loginPage;
	}

	public HomePage homePage() {
		if (homePage == null)
			homePage = new HomePage(driver);
		return homePage;
	}

	public ImportHomePage importHomePage() {
		if (importHomePage == null)

			importHomePage = new ImportHomePage(
					driver,
					XMLParser
							.readComponentValueFromXML("ImportHomePage.pageUrl"));
		return importHomePage;
	}

	public Import importPage() {
		if (importPage == null)
			importPage = new Import(
					driver,
					XMLParser
							.readComponentValueFromXML("ImportHomePage.pageUrl"));
		return importPage;
	}

	public SubscribersPage subscriberPage() {
		if (subscriberPage == null)
			subscriberPage = new SubscribersPage(driver,
					XMLParser.readComponentValueFromXML("Subscribers.pageUrl"));
		return subscriberPage;
	}

	public TestContentPage testContentPage() {
		if (testContentPage == null)
			testContentPage = new TestContentPage(driver);
		return testContentPage;
	}

	public TestEnvelopPage testEnvelopPage() {
		if (testEnvelopPage == null)
			testEnvelopPage = new TestEnvelopPage(driver);
		return testEnvelopPage;
	}

	public TestSchedulePage testSchedulePage() {
		if (testSchedulePage == null)
			testSchedulePage = new TestSchedulePage(driver);
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
			testTargetPage = new TestTargetPage(driver);
		return testTargetPage;
	}

	public ViewTestPage viewTestPage() {
		if (viewTestPage == null)
			viewTestPage = new ViewTestPage(driver,
					XMLParser.readComponentValueFromXML("MasterStatus.pageUrl"));
		return viewTestPage;
	}

	public SmsHeaderPage smsHeaderPage() {
		if (null == smsHeaderPage)
			smsHeaderPage = new SmsHeaderPage(driver,
					XMLParser.readComponentValueFromXML("SMSHeader.pageUrl"));

		return smsHeaderPage;
	}

	public SmsContentPage smsContentPage() {
		if (smsContentPage == null)
			smsContentPage = new SmsContentPage(driver,
					XMLParser.readComponentValueFromXML("SMSContent.pageUrl"));
		return smsContentPage;
	}

	public SmsTargetPage smsTargetPage() {
		if (smsTargetPage == null)
			smsTargetPage = new SmsTargetPage(driver,
					XMLParser.readComponentValueFromXML("SMSTarget.pageUrl"));
		return smsTargetPage;
	}

	public SmsSchedulePage smsSchedulePage() {
		if (smsSchedulePage == null)
			smsSchedulePage = new SmsSchedulePage(driver,
					XMLParser.readComponentValueFromXML("SMSSchedule.pageUrl"));
		return smsSchedulePage;

	}

	public ViewSmsPage viewSmsPage() {
		if (viewSmsPage == null)
			viewSmsPage = new ViewSmsPage(driver,
					XMLParser.readComponentValueFromXML("SMSStatus.pageUrl"));
		return viewSmsPage;
	}

	public CampaignManagement campaignManagement() {
		if (campaignManagement == null)
			campaignManagement = new CampaignManagement(driver,
					XMLParser.readComponentValueFromXML("Campaign.pageUrl"));
		return campaignManagement;
	}

	public ReportsPage reportsPage() {
		if (reportsPage == null)
			reportsPage = new ReportsPage(driver,
					XMLParser.readComponentValueFromXML("Report.pageUrl"));
		return reportsPage;
	}

	public RequestReportsPage requestReportsPage() {
		if (requestReportsPage == null)
			requestReportsPage = new RequestReportsPage(driver,
					XMLParser
							.readComponentValueFromXML("RequestReport.pageUrl"));
		return requestReportsPage;
	}

	public DataAttributesPage dataAttributesPage() {
		if (dataAttributesPage == null)
			dataAttributesPage = new DataAttributesPage(
					driver,
					XMLParser
							.readComponentValueFromXML("DataAttributes.pageUrl"));
		return dataAttributesPage;
	}

	public EmailEnvelopePage emailEnvelopePage() {
		if (emailEnvelopePage == null)
			emailEnvelopePage = new EmailEnvelopePage(driver,
					XMLParser
							.readComponentValueFromXML("EMAILEnvelope.pageUrl"));
		return emailEnvelopePage;
	}

	public EmailContentPage emailContentPage() {
		if (emailContentPage == null)
			emailContentPage = new EmailContentPage(driver);
		return emailContentPage;
	}

	public EmailTargetPage emailTargetPage() {
		if (emailTargetPage == null)
			emailTargetPage = new EmailTargetPage(driver);
		return emailTargetPage;
	}

	public EmailSchedulePage emailSchedulePage() {
		if (emailSchedulePage == null)
			emailSchedulePage = new EmailSchedulePage(driver);
		return emailSchedulePage;
	}

	public ViewEmailPage viewEmailPage() {
		if (viewEmailPage == null)
			viewEmailPage = new ViewEmailPage(driver,
					XMLParser.readComponentValueFromXML("MasterStatus.pageUrl"));
		return viewEmailPage;
	}

	public CountsPage countsPage() {
		if (countsPage == null)
			countsPage = new CountsPage(driver,
					XMLParser.readComponentValueFromXML("CountsPage.pageUrl"));
		return countsPage;
	}

	public ContentLibraryPage contentLibraryPage() {
		if (contentLibraryPage == null)
			contentLibraryPage = new ContentLibraryPage(
					driver,
					XMLParser
							.readComponentValueFromXML("ContentLibrary.pageUrl"));
		return contentLibraryPage;
	}

	public YahooPage yahooPage() {
		if (yahooPage == null)
			yahooPage = new YahooPage(driver,XMLParser
					.readComponentValueFromXML("Yahoo.pageUrl"));
		return yahooPage;

	}

	public CreateCampaignPage createCampaignPage() {
		if (createCampaignPage == null)
			createCampaignPage = new CreateCampaignPage(
					driver,
					XMLParser
							.readComponentValueFromXML("CreateCampaignPage.pageUrl"));
		return createCampaignPage;
	}

	public AfterTheClickPage afterTheClickPage() {
		if (afterTheClickPage == null)
			afterTheClickPage = new AfterTheClickPage(
					driver,
					XMLParser
							.readComponentValueFromXML("AfterTheClickPage.pageUrl"));
		return afterTheClickPage;
	}

	public TweetsContentPage tweetsContentPage() {

		if (tweetsContentPage == null)
			tweetsContentPage = new TweetsContentPage(
					driver,
					XMLParser
							.readComponentValueFromXML("TweetsContentPage.pageUrl"));
		return tweetsContentPage;
	}

	public TweetsSchedulePage tweetsSchedulePage() {

		if (tweetsSchedulePage == null)
			tweetsSchedulePage = new TweetsSchedulePage(driver, "");
		return tweetsSchedulePage;
	}

	public ViewTweetsPage viewTweetsPage() {
		if (viewTweetsPage == null)
			viewTweetsPage = new ViewTweetsPage(
					driver,
					XMLParser
							.readComponentValueFromXML("ViewTweetsPage.pageUrl"));
		return viewTweetsPage;
	}

	public DistributionListPage distributionListPage() {
		if (distributionListPage == null)
			distributionListPage = new DistributionListPage(
					driver,
					XMLParser
							.readComponentValueFromXML("DistributionListPage.pageUrl"));
		return distributionListPage;
	}

	public CreateDistributionListPage createDistributionListPage() {
		if (createDistributionListPage == null)
			createDistributionListPage = new CreateDistributionListPage(
					driver,
					XMLParser
							.readComponentValueFromXML("CreatedistributionListPage.pageUrl"));
		return createDistributionListPage;
	}

	public FacebookContentPage facebookContentPage() {
		if (facebookContentPage == null)
			facebookContentPage = new FacebookContentPage(
					driver,
					XMLParser
							.readComponentValueFromXML("FacebookContentPage.pageUrl"));
		return facebookContentPage;
	}

	public FacebookSchedulePage facebookSchedulePage() {
		if (facebookSchedulePage == null)
			facebookSchedulePage = new FacebookSchedulePage(driver);
		return facebookSchedulePage;
	}

	public ViewFacebookPage viewFacebookPage() {
		if (viewFacebookPage == null)
			viewFacebookPage = new ViewFacebookPage(
					driver,
					XMLParser
							.readComponentValueFromXML("ViewFacebookPage.pageUrl"));
		return viewFacebookPage;
	}
	
	public EmailSummaryPage emailSummaryPage() {
		if (emailSummaryPage == null)
			emailSummaryPage = new EmailSummaryPage(
					driver);
		return emailSummaryPage;
	}
	
	public SmsSummaryPage smsSummaryPage() {
		if (smsSummaryPage == null)
			smsSummaryPage = new SmsSummaryPage(
					driver);
		return smsSummaryPage;
	}
}
