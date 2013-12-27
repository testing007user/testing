package com.yesmail.qa.pageobjects.campaigns;
/** File Name: CampaignManagementBase.java  
 * Description: This Java Class is 
 * used for to perform different opetions Edit/Copy/ Rename/ Save as Template/Create Campaign
 *  /Create view for Campaign , Automated Campaign and Automated Templage. 
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * Date: 18/11/2013 
 * Version History  
 * 
 * Version name Updated By Reason / Comments 
 * Version:1.1 Updated for below reason
 * 1.Variable names should start with small character.
 * 2.Also you can remove navigation methods that are returning page objects. As we are going to use 'Page Object Factory' to get new page object. 
 * */


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;


public class CampaignManagement extends BasePage {

	private String pageURL;
	private WebDriver driver;

	@FindBy(css = ".pageTitle")
	private static WebElement CampaignPageTitle;

	@FindBy(css = "div:nth-child(2) button")
	private static WebElement createViewBtn;

	@FindBy(css = "div.leftControls:nth-child(1) ul li:nth-child(1) a")
	private static WebElement campaignsBtn;

	@FindBy(css = "div.leftControls:nth-child(1) ul li:nth-child(2) a")
	private WebElement automatedCampaignsBtn;

	@FindBy(css = ".ym-btn-toggleButton > li:nth-child(3) > a:nth-child(1)")
	private WebElement automatedTemplatesBtn;

	@FindBy(css = "input.search-query")
	private static WebElement campaignsSearchTxtBox;

	@FindBy(id="input[data-id='nameInput']")
	private static WebElement createCampaignnamePopupWindow;

	@FindBy(css =".checkbox input[type='checkbox']")
	private static WebElement automatedCampaignChkBox;

	@FindBy(css =".modal-footer button[class='btn btn-primary ok']")
	private static WebElement createCampaignOkBtn;

	@FindBy(css =".modal.fade.in")
	private static WebElement popUpModal;	
	
	@FindBys(value = {@FindBy(css ="tbody > tr > td:nth-of-type(2)")})
	private static List<WebElement> searchedCampaign;	

	//list of WebElement that represent campaign Name in the table	
	@FindBys(value = { @FindBy(css = "table tr>td:nth-of-type(2)") })
	private static List <WebElement> campaigns;

	@FindBy(css ="div.modal.fade.in div.controls > input")
	private static WebElement copyCampaignTxtBoxPopupWindow;

	@FindBy(css ="div.modal.fade.in div.controls > input")
	private static WebElement saveTemplateTxtBoxPopupWindow;

	@FindBy(css = "body div:nth-child(3) button[class='ym-btn ym-btn-primary ok']")
	private static WebElement copyCampaignOkBtnPopupWindow;

	@FindBy(css = "body div:nth-child(3) button[class='ym-btn ym-btn-primary ok']")
	private static WebElement saveTemplateOkBtnPopupWindow;

	/**
	 * Constructor section
	 * 
	 * @return
	 */

	public CampaignManagement(WebDriver driver, String pageUrl) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageURL = pageUrl;

	}


	/***
	 * This method is added to click on the 'Campaigns' button to list out all Campaign 
	 * 
	 * @author Sangeeta Pote
	 * @return 
	 * @since Completed on 18th Nov 2013
	 */

	public CampaignManagement loadCampaignManagementPage()
	{
		driver.get(PagesHelper.URL+pageURL);		
//		DriverUtility.waitFor(ExpectedConditions.visibilityOf(campaignsSearchTxtBox), driver, 120);
		return this;
	}
	
	public void isLoaded()
	{
		if(null == DriverUtility.waitFor(elementToBeClickable(By.cssSelector("input.search-query")), driver, 50))
		{
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
		}
	}

	public void clickCampaignsTab()
	{
		campaignsBtn.click();		
	}


	/***
	 * This method is added to click on the 'Automated Campaign' button to list out all Automated Campaign 
	 * @author Sangeeta Pote
	 * @param driver 
	 * @since Completed on 18th Nov 2013
	 */
	public void clickAutomatedCampaignsTab()
	{
		automatedCampaignsBtn.click();
	}

	/***
	 * This method is added to click on the 'Automated Campaign' button to list
	 * out all Automated Campaign
	 * 
	 * @author Sangeeta Pote
	 * @param driver 
	 * @since Completed on 18th Nov 2013
	 */

	public void clickAutomatedTemplatesTab(){
		automatedTemplatesBtn.click();		

	}

	/***
	 * This method is added to click on the 'Edit','Copy' and 'Rename'... link to
	 * the existing Campaign.
	 * 
	 * @author Sangeeta Pote
	 * @param driver 
	 * @return  
	 * @throws InterruptedException 
	 * @since Completed on 18th Nov 2013
	 */
	public boolean editLinkOperation(String Campaignslabel){
		int row;
		row = getRowIndex(Campaignslabel);
		
		WebElement editLink = driver.findElement(By.cssSelector("table tr:nth-of-type("+ row +") > td> ul > li:nth-of-type(1) > a"));		
		editLink.click();		
		return false;

	}
	
	public String copyLinkOperation(String Campaignslabel){
		int row;
		row = getRowIndex(Campaignslabel);
		
		WebElement copyLink = driver.findElement(By.cssSelector("table tr:nth-of-type("+ row +") > td> ul > li:nth-of-type(2) > a"));		
		copyLink.click();
		DriverUtility.waitforElementDisplay(driver, popUpModal, 5);

		Long number = System.currentTimeMillis();
		String copiedCampaignName = "test"+number;
		copyCampaignTxtBoxPopupWindow.sendKeys(copiedCampaignName);
		copyCampaignOkBtnPopupWindow.click();
		
		return copiedCampaignName;
	}
	
	public boolean renameLinkOperation(String Campaignslabel){
		int row;
		row = getRowIndex(Campaignslabel);
		
		WebElement renameLink =  driver.findElement(By.cssSelector("table tr:nth-of-type("+ row +") > td> ul > li:nth-of-type(3) > a"));
		renameLink.click();
		return true;
				
	}
	
	public boolean createCampaignLinkOperation(String Campaignslabel){
		int row;
		row = getRowIndex(Campaignslabel);
		
		WebElement createCampaignLink  = driver.findElement(By.cssSelector("table tr:nth-of-type("+ row +") > td> ul > li:nth-of-type(4) > a"));
		createCampaignLink.click();
		return true;
	}
	
	public boolean saveAsTemplateLinkOperation(String Campaignslabel){
		int row;
		row = getRowIndex(Campaignslabel);
		
		WebElement saveAsTemplateLink = driver.findElement(By.cssSelector("table tr:nth-of-type("+ row +") > td> ul > li:nth-of-type(4) > a"));
		saveAsTemplateLink.click();
		DriverUtility.waitforElementDisplay(driver, popUpModal, 5000);			
		String templateName = "test"+System.currentTimeMillis();
		saveTemplateTxtBoxPopupWindow.sendKeys(templateName);
		saveTemplateOkBtnPopupWindow.click();						
		return true;
	}
	
	public boolean reportLinkOperation(String Campaignslabel){
		int row;
		row = getRowIndex(Campaignslabel);	    
		
		WebElement reportLink =  driver.findElement(By.cssSelector("table tr:nth-of-type("+ row +") > td> ul > li:nth-of-type(5) > a"));
		reportLink.click();
		return true;
	}
	

	/***
	 * This method is added to search for specific Campaigns for Campaigns/Automated Campaigns/Automated Templates
	 * @author Sangeeta Pote
	 * @since Completed on 18th Nov 2013
	 */
	public boolean searchCampaigns(String searchlabel) {
		// TODO Auto-generated method stub		
		campaignsSearchTxtBox.clear();
		campaignsSearchTxtBox.sendKeys(searchlabel);
		System.out.println(searchedCampaign.size());
		if(searchlabel.equalsIgnoreCase(searchedCampaign.get(0).getText()))
			return true;
		else
			return false;
	}


	/***
	 * This method is added to create a new Campaign
	 * 
	 * @author Sangeeta Pote
	 * @since Completed on 18th Nov 2013
	 */

	public String createCampaign(boolean automatedCampaignCheckBox) {

		String campaignTextToType = null;
		createViewBtn.click();
		DriverUtility.waitforElementDisplay(driver,popUpModal,10);
		//createCampaignnamePopupWindow.clear();
		long number = System.currentTimeMillis();
		campaignTextToType = "test" + number;
		createCampaignnamePopupWindow.sendKeys(campaignTextToType);
		/*	Select s = new Select(webelement); 
				if (label==null)
				{ 
					s.selectByIndex(defaultIndex);	
				}
				s.selectByVisibleText(label);*/
		if(automatedCampaignCheckBox)
			automatedCampaignChkBox.click();
		createCampaignOkBtn.click();	
		return campaignTextToType;
	}

	private int getRowIndex(String label)
	{
		int index;

		for(index = 0; index < campaigns.size(); index++) {
			if(campaigns.get(index).getText().equalsIgnoreCase(label)){						
				break;
			}
		}
		return index + 1;
	}	

}
