package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constants.WebConstant;
import com.qa.utils.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	By totalProduct=By.cssSelector("div#content div.product-layout");
	
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		eUtil=new ElementUtil(driver);
	}
	
	public int getProductsCount() {
		int productCount = eUtil.waitForElementsVisible(totalProduct,WebConstant.WAIT_MEDIUM_TIMEOUT).size();
		System.out.println(productCount);
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eUtil.waitForElementVisible(productLocator, WebConstant.WAIT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}

}
