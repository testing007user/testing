

package com.yesmail.qa.framework.libraries;

/***
 * Interface for Property File
 * 
 * @author kapilag
 * 
 */
public interface IProperty {

	public <E extends Enum<E>> String getValue(E envValue);

	public String getValue(String key);

}
