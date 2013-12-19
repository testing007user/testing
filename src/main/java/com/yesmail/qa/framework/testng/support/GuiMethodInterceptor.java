/**File name : GuiMethodInterceptor
 * Description: TestNG Interceptor for updating GUi table in starting
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.testng.support;

import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.yesmail.qa.framework.configuration.CommandLineArgs;
/**
 * For updating GUi initially with all the test method which will run by TestNG
 * @author kapilag
 *
 */
public class GuiMethodInterceptor implements IMethodInterceptor {

	private int row;
	private static boolean guiSet;

	public List<IMethodInstance> intercept(List<IMethodInstance> methods,
			ITestContext context) {

		if (CommandLineArgs.getdisplayGuiFlag()) {
			if (!guiSet) {
				GuiHelper.createFrame();
				GuiHelper.addColoumn("Sno");
				GuiHelper.addColoumn("TestMethodName");
				GuiHelper.addColoumn("Status");
				for (int i = 0; i < methods.size(); i++) {
					row = i + 1;
					GuiHelper.addRows(row, methods.get(i).getMethod()
							.getMethodName(), "Will be Executed");
				}
			}
			guiSet = true;

		}
		return methods;
	}

}
