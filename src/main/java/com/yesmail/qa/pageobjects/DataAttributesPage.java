package com.yesmail.qa.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.libraries.Utils;

public class DataAttributesPage {

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

	@FindBy(css = ".alert-success")
	private WebElement successMessage;

	@FindBy(css = "tbody >tr")
	private WebElement tBody;

	/**
	 * Initializing Constructor
	 */
	public DataAttributesPage(WebDriver driver, String pageUrl) {
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}
	
	public void load() {
		driver.get(pageUrl);
	}

	public boolean isLoaded() {
		return DriverUtility.waitforElementDisplay(driver, tBody, 10);
	}

	/**
	 * Objective of this method is to Click Create Attribute Button
	 */
	public void clickCreateAttributeBtn() {
		DriverUtility.waitforElementDisplay(driver, createAttributeBtn, 10);
		createAttributeBtn.click();
	}

	/**
	 * Objective of this method is to enter Display Name
	 */
	public void enterDisplayName(String name) {
		DriverUtility.waitforElementDisplay(driver, displayNameTxtBox, 10);
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
		DriverUtility.waitforElementDisplay(driver, successMessage, 10);
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
				DriverUtility.waitforElementDisplay(driver, successMessage, 10);
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
		DriverUtility.waitforElementDisplay(driver, maxLengthTxtBox, 10);
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
		DriverUtility.waitforElementDisplay(driver, trueValueTxtBox, 10);
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
		DriverUtility.waitforElementDisplay(driver, minValueTxtBox, 10);
		minValueTxtBox.sendKeys(minValue);
		maxValueTxtBox.sendKeys(maxValue);
		precisionalueTxtBox.sendKeys(precision);
	}

}
