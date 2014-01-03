/***
 * File : DataAttributesPage.java
 * Description : Page object class for DataAttributesPage functionality 
 * @author Ojan
 * Version History : Draft 0.1
 * Version name Updated By Reason / Comments 
 * 1.0  updated for  below Reason / Comments
 * 
 * @author sangeetap
 * Updated/modified code as per review comments
 *    
 */
package com.yesmail.qa.pageobjects.attributes;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.exception.FrameworkException;
import com.yesmail.qa.framework.libraries.Utils;
import com.yesmail.qa.pageobjects.BasePage;
import com.yesmail.qa.pageobjects.PagesHelper;

public class DataAttributesPage extends BasePage {

	private WebDriver driver;
	private String pageUrl;

	@FindBy(css = ".createAttributeBtn")
	private WebElement createAttributeBtn;

	@FindBy(css = ".displayName")
	private WebElement displayNameTxtBox;

	@FindBy(css = ".attributeTypeList")
	private WebElement attributeTypeDropdown;

	@FindBy(css = ".sideTableList")
	private WebElement tableNameDropdown;

	@FindBy(css = ".columnName")
	private WebElement columnNameTxtBox;

	@FindBy(css = ".targetingDivisions")
	private WebElement targetingDivisions;

	@FindBy(css = ".reportingDivisions")
	private WebElement reportingDivisions;

	@FindBy(css = ".personalizationDivisions")
	private WebElement personalizationDivisions;

	@FindBy(css = ".saveAttribute")
	private WebElement saveAtrtributeBtn;

	@FindBy(css = ".maxlength")
	private WebElement maxLengthTxtBox;

	@FindBy(css = ".trueValueName")
	private WebElement trueValueTxtBox;

	@FindBy(css = ".falseValueName")
	private WebElement falseValueTxtBox;

	@FindBy(css = ".minValue")
	private WebElement minValueTxtBox;

	@FindBy(css = ".maxValue")
	private WebElement maxValueTxtBox;

	@FindBy(css = ".precision")
	private WebElement precisionalueTxtBox;

	@FindBys({ @FindBy(css = "tbody > tr > td:nth-of-type(3)") })
	private List<WebElement> trNameCollections;

	@FindBy(css = "tbody >tr")
	private WebElement tBody;

	/**
	 * Initializing Constructor
	 */
	public DataAttributesPage(WebDriver driver, String pageUrl) {
		super(driver);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}

	public DataAttributesPage load() {
		driver.navigate().to(PagesHelper.URL + pageUrl);
		return this;
	}

	public void isLoaded() {
		if (null == DriverUtility.waitFor(elementToBeClickable(tBody), driver,
				50))
			throw new FrameworkException(this.getClass().getName()
					+ " is not loaded in 50 seconds");
	}

	/**
	 * Objective of this method is to Click Create Attribute Button
	 */
	public void clickCreateAttributeBtn() {
		DriverUtility.waitFor(elementToBeClickable(createAttributeBtn), driver,
				10);
		createAttributeBtn.click();
	}

	/**
	 * Objective of this method is to enter Display Name
	 */
	public void enterDisplayName(String name) {
		DriverUtility.waitFor(elementToBeClickable(displayNameTxtBox), driver,
				10);
		displayNameTxtBox.sendKeys(name);
	}

	/**
	 * Objective of this method is to enter Column Name
	 */
	public void enterColumnName() {
		String name = Utils.getUniqueName("test", 12);
		columnNameTxtBox.sendKeys(name);
	}

	/**
	 * Objective of this method is to enter Table Name
	 */
	public void selectTableName() {
		DriverUtility.selectDropDown(tableNameDropdown, "Users", 1);
	}

	/**
	 * Objective of this method is to select Divisions
	 */
	public void selectDivisions() {
		DriverUtility.selectDropDown(targetingDivisions, "marketing", 1);
		DriverUtility.selectDropDown(reportingDivisions, "marketing", 1);
		DriverUtility.selectDropDown(personalizationDivisions, "marketing", 1);
	}

	/**
	 * Objective of this method is to Save Created Attribute
	 */
	public void saveAttribute() {
		saveAtrtributeBtn.click();
		getRibbonText(10);

	}

	/**
	 * Objective of this method is to Search Required attribute in the list
	 * 
	 * @param : attribute name to be searched
	 * @return : searched attribute found(True/False)
	 */
	public boolean searchAttribute(String attributeName) {
		load();
		isLoaded();
		boolean attributeFound = false;
		for (WebElement name : trNameCollections) {
			if (name.getText().equalsIgnoreCase(attributeName)) {
				attributeFound = true;
				break;
			}
		}
		return attributeFound;
	}

	/**
	 * Objective of this method is to delete required attribute in the list
	 * 
	 * @param : attribute name to be deleted
	 * @return : successfully deleted(True/False)
	 */
	public boolean deleteAttribute(String attributeName) {
		for (int i = 1; i < trNameCollections.size(); i++) {
			WebElement nameElement = driver.findElement(By
					.cssSelector("tbody > tr:nth-of-type(" + i
							+ ") > td:nth-of-type(3)"));

			if (nameElement.getText().equalsIgnoreCase(attributeName)) {
				WebElement deleteElement = driver.findElement(By
						.cssSelector("tbody > tr:nth-of-type(" + i
								+ ") > td:nth-of-type(5) > a.deleteAttribute"));
				deleteElement.click();
				getRibbonText(10);
				break;
			}
		}
		driver.navigate().refresh();
		return !searchAttribute(attributeName);

	}

	/**
	 * Objective of this method is to select String type attribute
	 * 
	 * @param : attribute type, attribute value
	 * 
	 */
	public void selectStringAttribute(String attributeType,
			String attributeValue) {
		DriverUtility.selectDropDown(attributeTypeDropdown, attributeType, 1);
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(maxLengthTxtBox),
				driver, 10);
		maxLengthTxtBox.sendKeys(attributeValue);

	}

	/**
	 * Objective of this method is to select Boolean type attribute
	 * 
	 * @param : attribute type, attribute value
	 * 
	 */
	public void selectBooleanAttribute(String attributeType,
			String attributeTrueValue, String attributeFalseValue) {
		DriverUtility.selectDropDown(attributeTypeDropdown, attributeType, 1);
		DriverUtility.waitFor(
				ExpectedConditions.elementToBeClickable(trueValueTxtBox),
				driver, 10);
		trueValueTxtBox.sendKeys(attributeTrueValue);
		falseValueTxtBox.sendKeys(attributeTrueValue);
	}

	/**
	 * Objective of this method is to select Number type attribute
	 * 
	 * @param : attribute type, attribute value
	 * 
	 */
	public void selectNumberAttribute(String attributeType, String minValue,
			String maxValue, String precision) {
		DriverUtility.selectDropDown(attributeTypeDropdown, attributeType, 1);
		DriverUtility.waitFor(elementToBeClickable(minValueTxtBox), driver, 10);

		minValueTxtBox.sendKeys(minValue);
		maxValueTxtBox.sendKeys(maxValue);
		precisionalueTxtBox.sendKeys(precision);
	}

}
