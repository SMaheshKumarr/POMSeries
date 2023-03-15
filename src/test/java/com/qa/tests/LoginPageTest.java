package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.constants.WebConstant;

public class LoginPageTest extends BaseTest{
	
	
	@Test(priority = 1)
	public void loginPageTitle_Test() {
		String actualTitle = LoginPageObj.getPageTitle();
		System.out.println("Login Page Title:" + actualTitle);
		Assert.assertEquals(actualTitle, WebConstant.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void fPasswordLinkExist_Test() {
		boolean linkexist = LoginPageObj.verifyForPasswordLink();
		Assert.assertTrue(linkexist);
	}
	
	@Test(priority = 3)
	public void login_Test() {
		LoginPageObj.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}

}
