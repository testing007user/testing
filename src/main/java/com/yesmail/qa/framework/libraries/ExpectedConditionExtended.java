/**File name : ExpectedConditionExtended
 * Description: This  Class is extended waitfor functionality to be used in Webdriver
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.libraries;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
/***
 * Class having function similar to ExpcectedConditions class in Selenium Support
 * Should be used in DriverUtility.waitFor method
 * @author kapilag
 *
 */
public class ExpectedConditionExtended {
	/***
	 * wait for the Element to be Clickable
	 * 
	 * @param element
	 *            : WebElement
	 * @return
	 */
	public static ExpectedCondition<WebElement> elementToBeClickable(
			final WebElement element) {
		return new ExpectedCondition<WebElement>() {
			public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions
					.visibilityOf(element);

			public WebElement apply(WebDriver driver) {
				WebElement element = visibilityOfElement.apply(driver);
				try {
					if (element != null && element.isEnabled()) {
						return element;
					} else {
						return null;
					}
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}

			@Override
			public String toString() {
				return "element to be clickable: " + element;
			}
		};
	}

	/***
	 * wait for the Element to be Disabled
	 * @param element
	 * @return
	 */
	public static ExpectedCondition<Boolean> elementToBeDisabled(
			final WebElement element) {
		return new ExpectedCondition<Boolean>() {

			public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions
					.visibilityOf(element);

			public Boolean apply(WebDriver driver) {
				WebElement element = visibilityOfElement.apply(driver);
				try {
					if (element != null && !(element.isEnabled())) {
						return true;
					} else {
						return false;
					}
				} catch (StaleElementReferenceException e) {
					return false;
				}
			}

			@Override
			public String toString() {
				return "element to be clickable: " + element;
			}
		};
	}
	
	public static ExpectedCondition<String> waittillURLContainsNumber()
	{
		return new ExpectedCondition<String>() {
			public String apply(WebDriver driver){
				String url = driver.getCurrentUrl();
				if(url.matches(".*\\d.*"))
					return url;
				return null;		
			}
		};
	}
}
