package com.qa.Dfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;

	/**
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		
		optionsManager=new OptionsManager(prop);
		
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		highlight=prop.getProperty("highlight").trim();
		
		
		System.out.println("Browser Name Is" + " " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(optionsManager.getChromeOptions());
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
		}
		else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver(optionsManager.getSafariOptions());
		}
		else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
		}
		else {
			System.out.println("please enter correct browser"+browserName);
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	/**
	 * 
	 * @return
	 */
	public Properties initProp() {
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
}
