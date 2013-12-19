/**File name : PropertyMapping
 * Description: This class is used to load multiple properties file and
 * return single hashmap for fetching values
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.io.IOUtils;

import com.yesmail.qa.framework.exception.FrameworkException;

/***
 * This is used to load properties file This class loads the properties file and
 * return getValue Method which can be used to get the key value pairs
 * 
 * @author kapilag
 * 
 */
public class PropertyMapping implements IProperty {

	Properties prop = new Properties();
	Hashtable<String, String> propertiesValue = new Hashtable<String, String>();
	Set<String> classEnumCheck = new HashSet<String>();
	FileInputStream fis;
	boolean isEnumMappingChecked;

	public PropertyMapping(Properties prop) {
		this.prop = prop;
		createHashMap(prop);
	}

	@SuppressWarnings("unused")
	private PropertyMapping() {

	}

	public PropertyMapping(String filePath) {
		try {
			fis = new FileInputStream(new File(filePath));
			prop.load(fis);
			fis.close();
			createHashMap(prop);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fis);
		}
	}

	public PropertyMapping(String[] filePaths) {
		for (String filePath : filePaths) {
			try {
				fis = new FileInputStream(new File(filePath));
				prop.load(fis);
				fis.close();
				createHashMap(prop);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(fis);
			}
		}
	}

	/***
	 * return the value of the Enum passed for the properties file passed in
	 * constructor of the class If user is happy with null return then
	 * CheckEnumMapping can be removed from this
	 */

	public <E extends Enum<E>> String getValue(E key) {
		checkEnumMapping(key);
		String value;
		try {
			value = propertiesValue.get(key.toString());
			return value;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/***
	 * return the value of the key passed for the properties file passed in
	 * constructor of the class
	 */
	public String getValue(String key) {
		String value;
		try {
			value = propertiesValue.get(key);
			return value;
		} catch (NullPointerException e) {
			return null;
		}
	}
	/***
	 * Create has map for all the loaded properties file
	 * @param prop
	 */
	private void createHashMap(Properties prop) {
		String key;
		Iterator<Object> i = prop.keySet().iterator();
		while (i.hasNext()) {
			key = (String) i.next();
			propertiesValue.put(key, prop.getProperty(key));
		}
	}

	/***
	 * Compares the Enum with loaded properties file and prevents Null in Run
	 * time
	 * 
	 * @param key
	 */
	private <E extends Enum<E>> void checkEnumMapping(E key) {
		if (!classEnumCheck.contains(key.getClass().getName())) {
			Set<String> misMatchEnum = new HashSet<String>();
			for (Enum<?> value : key.getClass().getEnumConstants()) {
				if (propertiesValue.get(value.toString()) == null) {
					misMatchEnum.add("Key with text=" + value.toString()
							+ " in class=" + key.getClass().getName() + ""
							+ " is not present in the loaded Properties file");
				}
			}
			if (!misMatchEnum.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				Iterator<String> mmI = misMatchEnum.iterator();
				while (mmI.hasNext()) {
					sb.append(mmI.next());
					sb.append(System.lineSeparator());
				}
				throw new FrameworkException(sb.toString());
			}
			classEnumCheck.add(key.getClass().getName());

		}
	}

}
