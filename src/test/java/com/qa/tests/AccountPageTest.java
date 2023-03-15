package com.qa.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.constants.WebConstant;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		AccountPageObj = LoginPageObj.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Test(priority=1)
	public void verifyAccPageTitle_Test() {
		String AccPageTit = AccountPageObj.getAccountPageTitle();
		Assert.assertEquals(AccPageTit, WebConstant.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyAccUrl_Test() {
		String AccPageUrl = AccountPageObj.getAccountPageUrl();
		Assert.assertTrue(AccPageUrl.contains(WebConstant.ACCCOUNT_PAGE_PATH_URL));
	}

	@Test(priority=3)
	public void accPageHeaders_Test() {
		List<String> actualHeaderResult = AccountPageObj.availableMenuHeader();
		System.out.println(actualHeaderResult);
		Assert.assertEquals(actualHeaderResult.size(), 4);
	}
	
	@Test(priority=4)
	public void searchProduct_Test() {
		
		SearchPageObj = AccountPageObj.performSearch(prop.getProperty("product").trim());
		if(SearchPageObj.getProductsCount()>0) {
			ProductInfoPageObj=SearchPageObj.selectProduct(prop.getProperty("selectproduct").trim());
		}
		
	}
	
	@Test(priority=5)
	public void verifyProductNameTest() {
		String PITit = ProductInfoPageObj.getProductName();
		System.out.println(PITit);
		Assert.assertEquals(PITit, prop.getProperty("selectproduct").trim());
	}
}
