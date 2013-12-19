/**File name : RetryIAnnotationTransformer
 * Description: TestNg Listener to add ReTry analyzer annotation on all Tests
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.testng.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.IAnnotationTransformer2;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;

/***
 * For Appending Retry Annotation on All Test Cases and creating Map of classes
 * which are having @AfterMethod and @BeforeMethod
 * 
 * @author kapilag
 * 
 */
public class RetryIAnnotationTransformer implements IAnnotationTransformer,
		IAnnotationTransformer2 {
	private static final Logger log = Logger
			.getLogger(RetryIAnnotationTransformer.class);
	
	public static Set<String> beforeMethodClasses = new HashSet<String>();
	public static Set<String> afterMethodClasses = new HashSet<String>();

	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		if (testMethod != null) {
			if (annotation.getRetryAnalyzer() == null) {
				annotation.setRetryAnalyzer(RetryAnalyzer.class);
				log.debug("Setting Retry Analyzer for Method:"
						+ testMethod.getName());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void transform(IConfigurationAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		if (testMethod != null) {
			if (testMethod
					.getAnnotation(org.testng.annotations.BeforeMethod.class) != null)
				beforeMethodClasses.add(testMethod.getDeclaringClass()
						.getName());
			if (testMethod
					.getAnnotation(org.testng.annotations.AfterMethod.class) != null)
				afterMethodClasses
						.add(testMethod.getDeclaringClass().getName());
		}

	}

	public void transform(IDataProviderAnnotation annotation, Method method) {
		// TODO Auto-generated method stub

	}

	public void transform(IFactoryAnnotation annotation, Method method) {
		// TODO Auto-generated method stub

	}

}
