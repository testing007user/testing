/**File name : ExpectedConditionExtended
 * Description: This  Class is extended waitfor functionality to be used in Webdriver
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.libraries;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/***
 * Class having function similar to ExpcectedConditions class in Selenium
 * Support Should be used in DriverUtility.waitFor method
 * 
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
	 * 
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

	public static ExpectedCondition<String> waittillURLContainsNumber() {
		return new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				String url = driver.getCurrentUrl();
				if (url.matches(".*\\d.*"))
					return url;
				return null;
			}
		};
	}

	/**
	 * An expectation for checking that an element is either invisible or not
	 * present on the DOM.
	 * 
	 * @param locator
	 *            used to find the element
	 */
	public static ExpectedCondition<Boolean> invisibilityOfElementLocated(
			final WebElement webelement) {
		return new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				try {
					return !(webelement.isDisplayed());
				} catch (NoSuchElementException e) {
					// Returns true because the element is not present in DOM.
					// The
					// try block checks if the element is present but is
					// invisible.
					return true;
				} catch (StaleElementReferenceException e) {
					// Returns true because stale element reference implies that
					// element
					// is no longer visible.
					return true;
				}
			}

			@Override
			public String toString() {
				return "element to no longer be visible: "
						+ webelement.toString();
			}
		};
	}

	public static List<ExpectedCondition<WebElement>> elementToBeClickable(
			final WebElement... element) {
		List<ExpectedCondition<WebElement>> returnList = new ArrayList<ExpectedCondition<WebElement>>();

		for (final WebElement element1 : element) {
			ExpectedCondition<WebElement> e = new ExpectedCondition<WebElement>() {
				public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions
						.visibilityOf(element1);

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
			returnList.add(e);
		}
		return returnList;
	}

	/***
	 * This method accepts n number of WebElements and check for clickability
	 * if any of the WebElement is not clickable will return false
	 * @param elements
	 * @return
	 */
	public static ExpectedCondition<Boolean> elementsToBeClickable(
			final WebElement... elements) {
		final List<Boolean> statusList = new ArrayList<Boolean>();
		
		
		return new ExpectedCondition<Boolean>() {
			final StringBuilder sb = new StringBuilder();
			public Boolean apply(WebDriver driver) {
				for (WebElement w : elements) {
				ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions.visibilityOf(w);
				WebElement w1 = visibilityOfElement.apply(driver);
					
					try {
						
						if ( w1 != null && w1.isEnabled()) {
							statusList.add(true);
						} else {
							statusList.add(false);
						}
						
						
					}
					catch (StaleElementReferenceException e) {
						statusList.add(false);
					}
					
				}
				if(statusList.contains(false))
				{
					statusList.clear();
					return false;
				}
				return true;
			}

			@Override
			public String toString() {
				
				return "elements to be clickable: " + sb;
			}
		};
	}

}
