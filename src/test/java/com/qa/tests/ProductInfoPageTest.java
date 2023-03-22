package com.qa.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{

	
	@BeforeClass
	public void productPageSetup() {
		AccountPageObj = LoginPageObj.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductImages_TestData() {
		return new Object[][]{
				{"Macbook","MacBook Air",4},
				//{"iMac","iMac",3}
				};
	}
	
	@Test(dataProvider="getProductImages_TestData")
	public void productImageCount_Test(String searchProduct,String selectProduct,int imageCount) {
		
		SearchPageObj=AccountPageObj.performSearch(searchProduct);
		ProductInfoPageObj=SearchPageObj.selectProduct(selectProduct);
		int imgCount = ProductInfoPageObj.productImageCount();
		AssertJUnit.assertEquals(imgCount, imageCount);
	}
	
	@Test
	public void getProductInfo_Test() {
		SearchPageObj=AccountPageObj.performSearch(prop.getProperty("product").trim());
		ProductInfoPageObj=SearchPageObj.selectProduct(prop.getProperty("selectproduct").trim());
		Map<String, String> actProductInfo = ProductInfoPageObj.getProductInfo();
		System.out.println(actProductInfo);
		
		AssertJUnit.assertEquals(actProductInfo.get("Brand"), " Apple");
		AssertJUnit.assertEquals(actProductInfo.get("Product Code"), " Product 17");
		
		softAssert.assertAll();
		
	}
}
