/***
 * File : AfterTheClick.java
 * Description : This java class is added to create a new page Role and new page tag for respective page role 
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *    
 */

package com.yesmail.qa.pageobjects.aftertheclick;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.PagesHelper;

public class AfterTheClickPage {

	 
	// After the click page web element allocation
	 

	@FindBy(css = "button.roleCell-newRoleButton")
	private WebElement createRoleBtn;

	@FindBy(css = "input.roleName")
	private WebElement roleNameTextBox;

	@FindBy(css = "button.saveRoleEdit")
	private WebElement saveRoleBtn;

	@FindBy(css = "button.cancelRoleEdit")
	private WebElement cancelRoleBtn;

	@FindBy(css = "input.tagName")
	private WebElement tagNameTextBox;

	@FindBy(css = "input.tagUrl")
	private WebElement tagNoteTextBox;

	@FindBy(css = "button.saveTagEdit")
	private WebElement saveNoteBtn;

	@FindBy(css = "button.cancelTagEdit")
	private WebElement cancelNoteBtn;

	@FindBys({ @FindBy(css = "table tbody tr td:nth-child(1) span") })
	private List<WebElement> roleCellLink;

	@FindBy(css = "button.tagCell-newTagButton")
	private WebElement createNewTagBtn;

	private WebDriver driver;

	private String pageUrl;

	 // define the constructor
	 
	public AfterTheClickPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}


	public AfterTheClickPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(createRoleBtn), driver,
				20);
	}

	/**
	 * This method is added to create new page role
	 * 
	 * @param strPageRoleName
	 *            - name for page role
	 * @return - created page role name
	 */

	public String createNewPageRole(String strPageRoleName) {
		createRoleBtn.click();
		roleNameTextBox.clear();
		roleNameTextBox.sendKeys(strPageRoleName);
		saveRoleBtn.click();
		return strPageRoleName;
	}

	/***
	 * This method is added to create new page tag
	 * 
	 * @param strPageRoleName
	 *            - name of the page role for which need to create new page tag
	 */
	public void createNewTag(String strPageRoleName) {
		String strContent = null;
		for (int index = 1; index < roleCellLink.size(); index++) {
			String cssSelector = "table tbody tr:nth-of-type(" + index
					+ ") td:nth-child(1) span";

			if (driver.findElement(By.cssSelector(cssSelector)) != null) {
				strContent = driver.findElement(By.cssSelector(cssSelector))
						.getText();
				if (strContent.equalsIgnoreCase(strPageRoleName)) {
					createNewTagBtn.click();
					tagNameTextBox.clear();
					String strTagName = Utils.getUniqueName("Tag_Name_",30);
					tagNameTextBox.sendKeys(strTagName);
					tagNoteTextBox.clear();
					String strTagNote = Utils.getUniqueName("Tag_Note_",30);
					tagNoteTextBox.sendKeys(strTagNote);
					saveNoteBtn.click();
					break;
				}
			}
		}

	}

}
