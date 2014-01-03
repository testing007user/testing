/** File Name: ShortCodePage.java  
 * Description: This Java Class is used to edit existing Rules or create new rules for SMS shortcode.
 *  
 * Author: Sangeeta Pote 
 * Version: Draft 1.0 
 * Date: 25/11/2013 
 * Version History 
 * Version name Updated By Reason / Comments */

package com.yesmail.qa.pageobjects.smsinbound;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ShortCodePage extends SmsInBoundRulesPage {

	/** Element locator section */

	private WebDriver driver;

	private String pageUrl;

	@FindBy(css = "div:nth-child(3) .sideBar div table tr[data-message-name='Keyword']")
	private WebElement conditionKeywordTxtLink;

	@FindBy(css = "table tr[data-message-name='Done']")
	private WebElement actionsDonelabelTxtLink;

	@FindBy(css = "table tr[data-message-name='Send Text Message']")
	private WebElement actionSendTextMessageTxtLink;

	@FindBy(css = "table tr[data-message-name='Send Text Message']")
	private WebElement actionUnsubscribeTxtLink;

	@FindBy(css = "table tr[data-message-name='Subscribe']")
	private WebElement actionSubscribeTxtLink;

	@FindBy(css = ".ruleSegment:nth-child(1) .keyword")
	private WebElement conditionWhenkeywordisTxtBox;

	@FindBy(css = "i[class='icon ruleSegment-close']")
	private WebElement closeIconRulesSegment;

	@FindBy(css = ".select-small select")
	private WebElement actionSendTextMessageDropdown;

	@FindBy(css = ".grid .rules .ruleSegments div .ruleSegment .content")
	private WebElement ruleSegmentContent;

	@FindBy(css = ".grid .rules .ruleSegments div .ruleSegment .header")
	private WebElement rulesSegmentheader;

	@FindBy(css = ".pageTitle")
	private WebElement shortCodenumbertxt;

	@FindBy(css = "button[class='pull-right ym-btn ym-btn-primary ok']")
	private WebElement saveShortCodeRulesBtn;

	@FindBy(css = ".ym-btn-toggleButton li a[data-id='defaultRules']")
	private WebElement showDefaultRulesBtn;

	@FindBy(css = ".ym-btn-toggleButton .selected")
	private WebElement hideDefaultRulesBtn;

	// WebElement define for drop areas
	@FindBy(css = ".ruleSegments")
	private WebElement dropruleSegmentsAreas;

	public ShortCodePage(WebDriver driver, String pageUrl) {
		super(driver, pageUrl);
		this.driver = driver;
		this.pageUrl = pageUrl;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is added to create condition for the respective shortcode
	 * 
	 * @author sangeetap
	 */
	public void createCondition() {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(conditionKeywordTxtLink)
				.moveToElement(dropruleSegmentsAreas)
				.release(dropruleSegmentsAreas).build();
		dragAndDrop.perform();

	}

	/**
	 * This method is added to create action for the respective condition
	 * 
	 * @param driver
	 *            ,conditionDragEle, actionDragEle, dropEle - Element to be drag
	 *            for condition and action, Element to drop
	 * @author sangeetap
	 */
	public void createAction(WebDriver driver, WebElement conditionDragEle,
			WebElement dropEle, WebElement actionDragEle) {

		Actions builder = new Actions(driver);
		builder.dragAndDrop(conditionDragEle, dropEle).perform();
	}

	/***
	 * This method is added to click on show default rules button
	 * 
	 * @author sangeetap
	 */
	public void showDefaultRules() {
		showDefaultRulesBtn.click();

	}

	/***
	 * This method is added to click on hide default rules button
	 * 
	 * @author sangeetap
	 */
	public void hideDefaultRules() {
		hideDefaultRulesBtn.click();

	}

	/***
	 * This method is added to save the created short code rules
	 * 
	 * @param driver
	 * @param conditionDragEle
	 * @param actionDragEle
	 * @param dropEle
	 * @author sangeetap
	 * @since Completed on 25th Nov 2013
	 */

	public void saveShortCodeRules(WebDriver driver,
			WebElement conditionDragEle, WebElement actionDragEle,
			WebElement dropEle) {
		Actions builder = new Actions(driver);
		builder.dragAndDrop(conditionDragEle, dropEle).perform();
		conditionWhenkeywordisTxtBox.clear();
		String keysToSend = null;
		conditionWhenkeywordisTxtBox.sendKeys(keysToSend);// keysToSend comes
															// from PageHelper
		builder.dragAndDrop(actionDragEle, dropEle).perform();
		if (actionDragEle == actionSendTextMessageDropdown) {
			WebElement element = null;
			Select s = new Select(element);// // Need to get value from
											// PagesHelper for
											// SendTextMessageDropdown
		}
		saveShortCodeRulesBtn.click();
	}

}
