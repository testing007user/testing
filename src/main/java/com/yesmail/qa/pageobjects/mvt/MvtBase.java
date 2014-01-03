package com.yesmail.qa.pageobjects.mvt;

/**File name : MvtBase.java
 * Description: This java class is added to navigate to the SetUp, Envelop, Content, Target and Schedule test page.
 * 
 * @author sangeetap 
 * Version: Draft 1.0 
 * @scompleted by 12/2/13
 * Version History
 * Version name Updated By Reason / Comments 
 * Version:1.1 Updated for below reason
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.yesmail.qa.pageobjects.BasePage;

public class MvtBase extends BasePage {

	private WebDriver driver;
	


	// Page Elements for MVTBase class

	@FindBy(css="button[class='ym-btn ym-btn-secondary create-test']")
	private WebElement createNewTestBtn;
	
	@FindBy(css = ".mvt-ico-details > span:nth-child(2)")
	private WebElement setUpTab;

	@FindBy(css = "a.mvt-toolbar-button:nth-child(2) > span:nth-child(2)")
	private WebElement envelopTab;

	@FindBy(css = "a.mvt-toolbar-button:nth-child(3) > span:nth-child(2)")
	private WebElement contentTab;

	@FindBy(css = "a.mvt-toolbar-button:nth-child(4) > span:nth-child(2)")
	private WebElement targetTab;

	@FindBy(css = "a.mvt-toolbar-button:nth-child(5) > span:nth-child(2)")
	private WebElement scheduleTab;

	@FindBy(css = ".mvt-ico-summary > span:nth-child(1)")
	private WebElement summaryTab;

	@FindBy(css = ".mvt-ico-results > span:nth-child(1)")
	private WebElement resultsTab;


	
	public MvtBase(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		

	}

	/*public void load() {
		
		driver.navigate().to(PagesHelper.URL+pageUrl);
			}*/

	
	/*public void isLoaded() {
		DriverUtility.waitforElementDisplay(driver, setUpTab, 30);
	}*/

	/***
	 * This method is added to create new view
	 */
	public void createNewView()
	{
		createNewTestBtn.click();
	}
	
	/***
	 * Navigate to SetUp Test page
	 */
	public void navigateToSetUpTab() {
		Reporter.log("Navigating to --> Test Setup Tab");
		setUpTab.click();
	}

	/***
	 * Navigate to Envelope Test page
	 */
	public void navigateToEnvelopeTab() {
		Reporter.log("Navigating to --> Test Envelope Tab");
		envelopTab.click();
	}

	/***
	 * Navigate to Content Test page
	 */
	public void navigateToContentTab() {
		Reporter.log("Navigating to --> Test Content Tab");
		contentTab.click();

	}

	/***
	 * Navigate to Target Test page
	 */
	public void navigateToTargetTab() {
		Reporter.log("Navigating to --> Test Target Tab");
		targetTab.click();
	}

	/***
	 * Navigate to Schedule Test page
	 */
	public void navigateToScheduleTab() {
		scheduleTab.click();
	}
	
	/***
	 * Navigate to Summary Test page
	 */
	
	public void navigateToSummaryTab() {
		Reporter.log("Navigating to --> Test Summary Tab");
		summaryTab.click();
	}

	/***
	 * Navigate to Results Test page
	 */
	public void navigateToResults()

	{
		resultsTab.click();
	}
	
	

}
