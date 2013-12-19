/**File name : FrameworkError
 * Description: This  Class is Framework Error
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.exception;

@SuppressWarnings("serial")
public class FrameworkError extends Error {
	
	public FrameworkError(String message)
	{
		super(message);
	}

}
