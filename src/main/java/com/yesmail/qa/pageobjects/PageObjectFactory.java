package com.yesmail.qa.pageobjects;

import org.openqa.selenium.WebDriver;

import com.yesmail.qa.pageobjects.campaign.CreateCampaignPage;
import com.yesmail.qa.pageobjects.imports.ImportHomePage;
import com.yesmail.qa.pageobjects.login.LoginPage;

import com.yesmail.qa.pageobjects.mvt.*;

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
	private ImportHomePage importHomePage;
	private SubscribersPage subscriberPage;
	private TestContentPage testContentPage;
	private TestEnvelopPage testEnvelopPage;
	private TestSchedulePage testSchedulePage;
	private TestSetupPage testSetupPage;
	private TestTargetPage testTargetPage;
	private CreateCampaignPage createCampaignPage;

	public PageObjectFactory(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage loginPage() {
		if (loginPage == null)
			loginPage = new LoginPage(driver);
		return loginPage;
	}

	public ImportHomePage importP() {
		if (importHomePage == null)
			importHomePage = new ImportHomePage(driver,
					"https://qa2-platform.yesmail.com/#jobs?type=Any");
		return importHomePage;
	}

	public SubscribersPage subscriberPage() {
		if (subscriberPage == null)
			subscriberPage = new SubscribersPage(driver,
					XMLParser.readComponentValueFromXML("Subscribers.pageUrl"));
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

	public CreateCampaignPage createCampaignPage() {
		if (createCampaignPage == null)
			createCampaignPage = new CreateCampaignPage(
					driver,
					XMLParser
							.readComponentValueFromXML("CreateCampaignPage.pageUrl"));
		return createCampaignPage;

	}
}
