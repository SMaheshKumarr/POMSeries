package com.qa.Dfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	 public WebDriver driver;
	 public Properties prop;
	 public OptionsManager optionsManager;
	 public static String highlight;
	 
	 public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

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
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if (browserName.equalsIgnoreCase("safari")) {
			//driver = new SafariDriver(optionsManager.getSafariOptions());
			tlDriver.set(new SafariDriver(optionsManager.getSafariOptions()));
		}
		else if (browserName.equalsIgnoreCase("Edge")) {
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		else {
			System.out.println("please enter correct browser"+browserName);
		}
			
		getDriver().manage().deleteAllCookies();  
		getDriver().manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	/**
	 * Get the local thread copy of the driver. Set local driver is already set in the above method
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
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
	
	

	/**
	 * To take the screenshot
	 * @return
	 */
	public static String getScreenshot() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
}
