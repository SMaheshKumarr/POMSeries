package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.constants.WebConstant;
import com.qa.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private By headers=By.xpath("//h2");
	private By search = By.xpath("//input[@name='search']");
	private By searchIcon = By.cssSelector("#search button");
	

	public AccountPage(WebDriver driver) {
		this.driver=driver;
		eUtil=new ElementUtil(driver);
	}

	//Page Title
	public String getAccountPageTitle() {
		String accountPageTitle = eUtil.waitForTitle(WebConstant.WAIT_MIN_TIMEOUT, WebConstant.ACCOUNT_PAGE_TITLE);
		System.out.println("Account Page Title:" + accountPageTitle);
		return accountPageTitle;
	}
	
	//Page URL
	public String getAccountPageUrl() {
		String accountPageUrl = eUtil.waitForUrl(WebConstant.WAIT_MAX_TIMEOUT, WebConstant.ACCCOUNT_PAGE_PATH_URL);
		System.out.println("Acc Page Url:"+accountPageUrl);
		return accountPageUrl;
	}
	
	// LHS Menu Headers
	public List<String> availableMenuHeader() {
		
		List<String> al=new ArrayList<String>();
		List<WebElement> headerValues = eUtil.waitForElementsVisible(headers, WebConstant.WAIT_MEDIUM_TIMEOUT);
		for(WebElement e:headerValues) {
			String headerText = e.getText();
			//System.out.println("Headers:"+headerText);
			al.add(headerText);
		}
		return al;
	}
	
	// Check Search box is visible
	public boolean verifySearchExist() {
		return eUtil.waitForElementVisible(search, WebConstant.WAIT_MEDIUM_TIMEOUT).isDisplayed();
	}
	
	//Enter the keyword in search and click on the result
	public SearchPage performSearch(String searchKey) {
		if(verifySearchExist()) {
			eUtil.doSendKeys(search, searchKey);
			eUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search is not present on the page");
			return null;
		}
	}
}
