package com.yesmail.qa.test.dummytest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.yesmail.qa.framework.testng.support.SAssert;

public class TestingAssert {
	
	public SAssert a = new SAssert();
	
	@Test(timeOut = 5)
	public void testing1()
	{
		a.assertTrue(true, "This is method");
		a.assertTrue(false, "This is method");
		boolean flag = true;
		if (flag)
			throw new ElementNotFoundException("abc", "abc", "abc");

		//a.assertTrue(false, "This is false");
		a.assertAll();
	}
	@Test(timeOut = 5)
	public void testing64431()
	{
		a.assertTrue(false, "This is method");
		a.assertTrue(false, "This is method");
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		
		Assert.assertFalse(true, "Testing");
		a.assertTrue(false, "This is method");
		a.assertAll();
}

	@Test(timeOut = 5)
	public void testing78731()
	{
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.assertAll();
}
	
	@Test(timeOut = 5)
	public void testing64931()
	{
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.assertAll();
}
	
	@Test(timeOut = 5)
	public void testing2()
	{
		a.assertTrue(true, "This is method");
		a.assertTrue(true, "This is method");
		//a.assertTrue(false, "This is false");
		a.assertAll();
	}
	
	@Test
	public void testing3()
	{
		a.assertTrue(false, "This is method");
		//a.assertTrue(false, "This is false");
		a.assertAll();
	}
	
	@Test
	public void testing4()
	{
		a.assertTrue(true, "This is method");
		a.assertTrue(false, "This is method");
		//a.assertTrue(false, "This is false");
		a.assertAll();
	}
	
	@Test
	public void testing5()
	{
		a.assertTrue(true, "This is method");
		//a.assertTrue(false, "This is false");
		a.assertAll();
}
	
	@Test
	public void testing6231()
	{
		a.assertTrue(true, "This is method");
		//a.assertTrue(false, "This is false");
		a.assertAll();
}
	
	@Test
	public void testing6()
	{
		a.assertTrue(true, "This is method");
		//a.assertTrue(false, "This is false");
		a.assertAll();
}
}
