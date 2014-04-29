

package com.yesmail.qa.framework.libraries;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import org.apache.log4j.Logger;


public class Utils {
	
	private static final Logger log = Logger.getLogger(Utils.class);
	
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
	
	/***
	 * This method return full path of the resource file name using class loader
	 * @param className : Class which is calling this method
	 * @param fileName
	 * @return
	 */
	public static String getResources(Object className,String fileName)
	{
		log.debug("File Name Recieved is:"+fileName);
		String returFilePath = null ;
		try {
			returFilePath	= className.getClass().getResource("/"+fileName).toURI().getPath().toString();
			returFilePath = returFilePath.replace("/",File.separator).substring(1);
			log.debug("Returned File Path is:::"+returFilePath);
			return returFilePath;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch(NullPointerException e){
			log.info("Not able to find File with path:"+returFilePath+" in class path.Returning Null");
			return null;
		}
	}

}
