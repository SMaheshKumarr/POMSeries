package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.constants.WebConstant;
import com.qa.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eUtil;

	// private By locators
	By email = By.id("input-email");
	By password = By.id("input-password");
	By loginButton = By.cssSelector(".btn.btn-primary[value='Login']");
	By forpwd = By.linkText("Forgotten Password");

	// const
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eUtil=new ElementUtil(driver);

	}

	// page actions/methods

	public String getPageTitle() {
		String loginPageTit = eUtil.waitForTitle(WebConstant.WAIT_MIN_TIMEOUT,WebConstant.LOGIN_PAGE_TITLE);
		//System.out.println("Login Page Title:" + loginPageTit);
		return loginPageTit;
	}

	public boolean verifyForPasswordLink() {
		return eUtil.waitForElementVisible(forpwd, WebConstant.WAIT_MEDIUM_TIMEOUT).isDisplayed();
	}

	public AccountPage doLogin(String email,String password) {
		
		System.out.println("Credentials:" + email + "," + password);
		/*
		 * driver.findElement(email).sendKeys(un);
		 * driver.findElement(password).sendKeys(pwd);
		 * driver.findElement(loginButton).click();
		 */
		
		eUtil.waitForElementVisible(this.email, WebConstant.WAIT_MEDIUM_TIMEOUT).sendKeys(email);
		eUtil.doSendKeys(this.password, password);
		eUtil.doClick(loginButton);
		
		return new AccountPage(driver);
	}
}
