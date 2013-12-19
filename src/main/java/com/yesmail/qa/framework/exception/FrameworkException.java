/**File name : FrameworkException
 * Description: This  Class is Framework Runtime Exception
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.exception;

@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {
	
	public FrameworkException(String message)
	{
		super(message);
	}

}
