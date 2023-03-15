package com.qa.base;

import java.util.Properties;

import javax.security.auth.login.LoginContext;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.Dfactory.DriverFactory;
import com.qa.Dfactory.OptionsManager;
import com.qa.pages.AccountPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductInfoPage;
import com.qa.pages.SearchPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory DriverFactoryObj;
	OptionsManager OptionsManagerObj;
	protected Properties prop;
	protected LoginPage LoginPageObj;
	protected AccountPage AccountPageObj;
	protected SearchPage SearchPageObj;
	protected ProductInfoPage ProductInfoPageObj;
	
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setUp() {
		
		DriverFactoryObj = new DriverFactory();
		
		prop=DriverFactoryObj.initProp();
		
		driver=DriverFactoryObj.initDriver(prop);
		
		LoginPageObj =  new LoginPage(driver);
		
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
