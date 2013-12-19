/**File name : Utils
 * Description: This class contains generic Utility function a
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.libraries;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Utils {

	/***
	 * method for getting hours with +2 minutes
	 * 
	 */
	public static String getHourString() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, 2);
		String hoursString = calendar.get(Calendar.HOUR_OF_DAY) % 12 + "";
		if (calendar.get(Calendar.HOUR_OF_DAY) == 12)
			hoursString = "12";
		return (hoursString.length() == 1) ? ("0" + hoursString) : hoursString;
	}

	/**
	 * This method is for setting Minute string in schedule page with +2
	 * minutes.
	 */
	public static String getMinuteString() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, 2);
		String minuteString = calendar.get(Calendar.MINUTE) + "";
		return (minuteString.length() == 1) ? ("0" + minuteString)
				: minuteString;
	}

	/***
	 * Return text appended with unique String
	 * 
	 * @param text
	 * @return
	 */

	public static String getUniqueName(String text) {
		return text + UUID.randomUUID();
	}

	/***
	 * Return Substring text appended with unique String
	 * 
	 * @param text
	 * @param charCount
	 *            :Count of the Characters starting with 0
	 * @return
	 */
	public static String getUniqueName(String text, int charCount) {
		String trimText = (text + UUID.randomUUID()).substring(0, charCount);
		return trimText;
	}

	/**
	 * This method is for setting Am and Pm.
	 */
	public static String getMeriDian() {
		if (new GregorianCalendar().get(Calendar.HOUR_OF_DAY) >= 12) {
			return "pm";
		}
		return "am";
	}

}
