/**File name : SAssert
 * Description: Soft Assert class for testng
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.testng.support;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import com.beust.jcommander.internal.Maps;
import com.yesmail.qa.framework.Driver;
import com.yesmail.qa.framework.DriverInitialization;
import com.yesmail.qa.framework.DriverUtility;
import com.yesmail.qa.framework.configuration.CommandLineArgs;

/****
 * This is soft assert class 
 * 
 * @author kapilag
 * 
 */
public class SAssert extends Assertion {

	public static InheritableThreadLocal<Map<AssertionError, IAssert>> m_errors = new InheritableThreadLocal<Map<AssertionError, IAssert>>() {
		public Map<AssertionError, IAssert> initialValue() {
			return Maps.newHashMap();
		}
	};

	public static InheritableThreadLocal<Map<IAssert, String>> assertMap = new InheritableThreadLocal<Map<IAssert, String>>() {
		public Map<IAssert, String> initialValue() {
			return new LinkedHashMap<IAssert, String>();
		}
	};

	@Override
	public void executeAssert(IAssert a) {
		try {
			a.doAssert();
			assertMap.get().put(a, "");
		} catch (AssertionError ex) {
			String screenShotPath = "";
			String screenCaptureName ="";
			if (CommandLineArgs.getScreenShotFlag()) {
				String screenShotName = UUID.randomUUID() + ".png";
				screenShotPath = DriverInitialization.outPutDir
						+ File.separator + screenShotName;
				DriverUtility
						.takeScreenShot(Driver.getDriver(), screenShotPath);
				screenCaptureName = "../New_E8_Automation_Suite/"+screenShotName;
			}
			assertMap.get().put(a, screenCaptureName);
			m_errors.get().put(ex, a);
		}
	}

	public void assertAll() {
		if (!m_errors.get().isEmpty()) {
			StringBuilder sb = new StringBuilder(
					"The following asserts failed:\n");
			boolean first = true;
			for (Map.Entry<AssertionError, IAssert> ae : m_errors.get()
					.entrySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(ae.getValue().getMessage());
			}
			throw new AssertionError(sb.toString());
		}
	}
}
