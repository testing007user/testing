/***
 * File : FacebookContentPage.java
 * Description : This java class is used to fill the content of Facebook post
 * @author ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * 1.Add header comment and comment for each method in 
 * FacebookContentPage java files.  
 * 2.The method selectPage(String) is undefined for the type FacebookContentPage .Please create. 
 *   
 */

package com.yesmail.qa.pageobjects.facebook;

import java.util.UUID;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.ExpectedConditionExtended;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class FacebookContentPage extends BasePage {

	@FindBy(css = "span[data-target='account'] select")
	private WebElement accountDropDown;

	@FindBy(id = "message")
	private WebElement messageTextBox;

	@FindBy(id = "name")
	private WebElement nameTextBox;

	// @FindBy(id="pages")
	@FindBy(css = "div:nth-child(2)>span[data-target='page'] select")
	private WebElement pageDropDown;

	@FindBy(id = "savePost")
	private WebElement savebutton;

	@FindBy(css = "#pages option[selected='selected']")
	private WebElement defaultPageSelected;

	@FindBy(css = "div:nth-child(2)>a:nth-of-type(1)")
	private WebElement contentTab;

	@FindBy(css = "div:nth-child(2) input[id='scheduleDatePicker']")
	public WebElement dateBox;
	
	@FindBy(css = "div.twitter button#save")
	private WebElement saveScheduleButton;

	private WebDriver driver;
	private String pageUrl;

	// define the constructor

	public FacebookContentPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	} // end of constructor

	public void isLoaded() {
		if (null == DriverUtility.waitFor(
				ExpectedConditionExtended.elementToBeClickable(nameTextBox), driver,
				50))
			throw new FrameworkException(FacebookContentPage.class.getName()
					+ " is not loaded");
	}

	public FacebookContentPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	/***
	 * This method is added to navigate to Content Tab
	 */
	public void navigateToContentTab() {
		contentTab.click();
	}

	/**
	 * 
	 * *This method is added to fill and save the Facebook contents
	 * 
	 * @author sangeetap
	 * 
	 */
	public boolean fillFacebookContent() {
		enterFacebookName();
		selectFacebookAccount();
		selectPage();
		enterFacebookContent();
		return saveFacebookContent();
	}

	/***
	 * This method is added to select the Facebook page
	 * 
	 * @author sangeetap
	 */

	private void selectPage() {

		DriverUtility.waitforElementDisplay(driver, defaultPageSelected, 10);
		DriverUtility.selectDropDown(pageDropDown,
				PagesHelper.FACEBOOK_PAGE_NAME, 1);
	}

	/***
	 * This method is added to select Facebook account
	 * 
	 * @author sangeetap
	 * 
	 */

	private void selectFacebookAccount() {
		DriverUtility.selectDropDown(accountDropDown,
				PagesHelper.FACEBOOK_ACCOUNT, 2);
	}

	/***
	 * This method is added to enter the Facebook message content
	 */

	private void enterFacebookContent() {
		String strMsgName = PagesHelper.FACEBOOK_CONTENT_NAME
				+ UUID.randomUUID();
		messageTextBox.clear();
		messageTextBox.sendKeys(strMsgName);
	}

	/***
	 * This method is added to enter the Facebook post name
	 */

	private void enterFacebookName() {
		String strFbName = Utils.getUniqueName(PagesHelper.FACEBOOK_NAME, 25);
		nameTextBox.clear();
		nameTextBox.sendKeys(strFbName);
	}

	/***
	 * This method is added to click on save button
	 * 
	 * @return getMaserId - return generated Facebook matster id
	 */
	private boolean saveFacebookContent() {
		savebutton.click();
		 Reporter.log("Ribbon text for facebook content page :"+getRibbonText(10)+"<br>");
		 DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(saveScheduleButton), driver, 15);//This is added as workaround for FaceBook Content Page reload issue.
		return stepCompleted(1, 10);
	}

}
