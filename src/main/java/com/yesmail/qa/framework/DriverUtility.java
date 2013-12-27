/**File name : DriverUtility
 * Description: This  Class is Driver Utility Methods 
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yesmail.qa.framework.configuration.CommandLineArgs;

/***
 * Only Selenium WebDriver Related Utility function
 * 
 * @author kapilag
 * 
 */
public class DriverUtility {

	public enum CHECK_UNCHECK {
		CHECK, UNCHECK
	};

	private static final Logger log = Logger.getLogger(DriverUtility.class);

	/***
	 * This Function is generic Function and return type should be compared with
	 * null as it will not return false
	 * 
	 * @param <T>
	 * @param expectedCondition
	 *            & ExpectedCondtionExtended
	 * @param driver
	 * @param i
	 * @return
	 */
	public static <T> T waitFor(ExpectedCondition<T> expectedCondition,
			WebDriver driver, int timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			T returnValue = new WebDriverWait(driver, timeOutInSeconds)
					.until(expectedCondition);
			driver.manage()
					.timeouts()
					.implicitlyWait(CommandLineArgs.getDriverTimeOut(),
							TimeUnit.SECONDS);
			return returnValue;
		} catch (TimeoutException e) {
			driver.manage()
					.timeouts()
					.implicitlyWait(CommandLineArgs.getDriverTimeOut(),
							TimeUnit.SECONDS);
			return null;
		}
	}

	/***
	 * This method is for switching between windows.
	 * 
	 * @param driver
	 * @param sString
	 *            :Target window Title
	 * @return:True if window switched
	 */
	public static boolean switchToWindow(WebDriver driver, String sString) {
		String currentHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		if (handles.size() >= 1) {
			for (String handle : handles) {
				log.debug("Switching to other window");
				driver.switchTo().window(handle);
				if (driver.getTitle().contains(sString)) {
					log.info("switched to window with title:" + sString);
					return true;
				}
			}
			driver.switchTo().window(currentHandle);

			log.info("Window with title:" + sString
					+ " Not present,Not able to switch");
			return false;
		} else {
			log.info("There is only one window handle :" + currentHandle);
			return false;
		}
	}

	/***
	 * Take screen Shot of WebDriver
	 * 
	 * @param driver
	 * @param path
	 *            :File path to store screen Shot
	 * @author kapilag
	 */
	public static File takeScreenShot(WebDriver driver, String path) {
		File saved = new File(path);
		File scrFile;
		try {
			if (CommandLineArgs.getRemoteFlag()) {
				Augmenter augumenter = new Augmenter();
				scrFile = ((TakesScreenshot) augumenter.augment(driver))
						.getScreenshotAs(OutputType.FILE);
			} else {
				scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
			}
			FileUtils.moveFile(scrFile, saved);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saved;
	}

	/***
	 * This Method double click on element using JS
	 * 
	 * @param element
	 *            :Element on which Double click needs to be performed
	 * @param clickStrategy : double click using javascript or using action class
	 * @param driver
	 */
	public static void doubleClick(WebElement element, WebDriver driver,
			CLICK_STRATEGY clickStrategy) {

		switch (clickStrategy) {

		case USING_ACTION:
			Actions action = new Actions(driver);
			action.doubleClick(element).perform();
			break;
		case USING_JS:
			((JavascriptExecutor) driver)
					.executeScript(
							"var evt = document.createEvent('MouseEvents');"
									+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
									+ "arguments[0].dispatchEvent(evt);",
							element);
		}

	}

	public enum CLICK_STRATEGY {
		USING_JS, USING_ACTION
	}

	/***
	 * Perform drag and drop
	 * 
	 * @param sourceElement
	 *            :element which need to be dragged
	 * @param targetElement
	 *            :element on which dragged Element needs to be dropped
	 * @param driver
	 *            :WebDriver
	 * @author kapilag
	 */
	public static void dragAndDrop(WebElement sourceElement,
			WebElement targetElement, WebDriver driver) {
		Actions a = new Actions(driver);
		a.dragAndDrop(sourceElement, targetElement).perform();
	}

	/***
	 * return clickable Element i.e dispplayed and enabled
	 * 
	 * @param driver
	 *            :webDriver to be passed
	 * @param locator
	 *            :Locator to search for like By.xpath,By.id,By.name etc
	 * @param timeout
	 *            :Enum Timeout like TIME_OUTS.MED_TIMEOUT
	 * @author kapilag
	 */
	public static WebElement getClickableElement(WebDriver driver, By locator,
			int timeout) {
		try {
			WebElement w = new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.elementToBeClickable(locator));
			return w;
		} catch (TimeoutException e) {
			log.info("Not able to find the Element " + locator.toString()
					+ " in:" + timeout + " seconds");
			return null;
		}
	}

	/***
	 * Wait For the Element to be displayed and enabled
	 * 
	 * @param driver
	 *            :webDriver to be passed
	 * @param locator
	 *            :Locator to search for like By.xpath,By.id,By.name etc
	 * @param timeout
	 *            :Enum Timeout like TIME_OUTS.MED_TIMEOUT
	 * @author kapilag
	 */
	public static boolean waitforElementDisplay(WebDriver driver,
			final WebElement webElement, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until

			(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					Boolean enabled = false;
					if (webElement.isEnabled() && webElement.isDisplayed())
						enabled = true;
					return enabled;
				}
			});
			return true;
		} catch (TimeoutException e) {
			log.info("Element not visible in:" + timeout + " seconds");
			return false;
		}
	}

	/***
	 * Select Value from Drop Down with visible Text ,if no Such Element Found <br>
	 * Select default index
	 * 
	 * @param webElement
	 *            :Select WebElement
	 * @param visibleText
	 *            :String to be Selected
	 * @param defaultIndex
	 *            :index to be selected by Default
	 * @author kapilag
	 */
	public static void selectDropDown(WebElement webElement,
			String visibleText, Integer defaultIndex) {
		Select s = new Select(webElement);
		try {
			if (visibleText == null)
				throw new NoSuchElementException(visibleText);
			s.selectByVisibleText(visibleText);
		} catch (NoSuchElementException e) {
			s.selectByIndex(defaultIndex);
		}
	}

	/***
	 * forcefully check or Uncheck checkbox irrespective of the state(Element
	 * should be visible)
	 * 
	 * @param webElement
	 *            :Check box element
	 * @param CHECK_UNCHECK
	 *            enum
	 */
	public static void checkUncheckCheckBox(WebElement webElement,
			CHECK_UNCHECK checkUnCheck) {
		boolean checked = webElement.isSelected();
		if (checked) {
			if (checkUnCheck.toString().equalsIgnoreCase("uncheck")) {
				webElement.click();
			}
		} else {
			if (checkUnCheck.toString().equalsIgnoreCase("check")) {
				webElement.click();
			}
		}
	}

}
