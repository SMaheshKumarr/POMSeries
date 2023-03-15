package com.qa.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.constants.WebConstant;
import com.qa.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	private Map<String,String> productInfoMap;
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails>li>a>img");
	private By productMeta = By.xpath("//div[@class='col-sm-4']//ul[@class='list-unstyled'][1]/li");
	private By priceMeta = By.xpath("//div[@class='col-sm-4']//ul[@class='list-unstyled'][2]/li");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eUtil=new ElementUtil(driver);
	}

	
	public String getProductName() {
		String productHeaderValue = eUtil.doGetElementText(productHeader);
		return productHeaderValue;
	}
	
	public int productImageCount() {
		return eUtil.waitForElementsVisible(productImages, WebConstant.WAIT_MEDIUM_TIMEOUT).size();
		
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String,String>();
		productInfoMap.put("ProductName", getProductName());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}
	
	private void getProductMetaData() {
		//Brand: Apple
		//Product Code: Product 17
		//Reward Points: 700
		//Availability: In Stock
		
		List<WebElement> metaList = eUtil.waitForElementsVisible(productMeta, WebConstant.WAIT_MEDIUM_TIMEOUT);
		for(WebElement e:metaList) {
			String meta=e.getText();
			System.out.println(meta);
			String metaInfo[] = meta.split(":");
			String Key=metaInfo[0];
			String Value=metaInfo[1];
			productInfoMap.put(Key, Value);
		}
		System.out.println("Product Details"+productInfoMap);
		 
	}
	
	
	private void getProductPriceData() {
//		$1,202.00
//		Ex Tax: $1,000.00
		List<WebElement> priceList = eUtil.waitForElementsVisible(priceMeta, WebConstant.WAIT_MEDIUM_TIMEOUT);
		String price=priceList.get(0).getText();
		String exTax=priceList.get(1).getText();
		String exTaxVal = exTax.split(":")[1].trim();
		productInfoMap.put("ProductPrice", price);
		productInfoMap.put("exTax", exTaxVal);
		
	 } 
	
}
