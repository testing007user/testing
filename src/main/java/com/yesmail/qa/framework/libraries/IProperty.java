/**File name : IProperty
 * Description: INterface for property files
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

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
