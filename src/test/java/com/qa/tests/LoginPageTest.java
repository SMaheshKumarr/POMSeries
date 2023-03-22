package com.qa.tests;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.constants.WebConstant;
import com.qa.utils.ExcelUtil;

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
	
	@DataProvider
	public Object[][] getTestData() throws FileNotFoundException {
		
			Object loginData[][]= ExcelUtil.getExcelData();
			
			return loginData;
		
	}
	
	
	@Test(priority = 3, dataProvider="getTestData")
	public void login_Test(String Email, String Password) {
		LoginPageObj.doLogin(Email,Password);
	}

}
