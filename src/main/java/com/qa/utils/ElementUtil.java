package com.qa.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.ParametersAreNonnullByDefault;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.Dfactory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavascriptUtil jsUtil;

	// ******Constructor*******//
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavascriptUtil(driver);
	}

	// ******To Get Individual Element*******//
	public WebElement getElement(By locator) {
		
		 WebElement element = driver.findElement(locator);
		 if(Boolean.parseBoolean(DriverFactory.highlight)) {
			 jsUtil.flash(element); 
		 }
	
		 return element;

	}

	public WebElement getElement(By locator, int timeout) {
		return waitForElementVisible(locator, timeout);
	}

	// ******To Get Multiple Elements*******//
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// ******SendKeys*******//
	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	// ******Click*******//
	public void doClick(By locator) {
		getElement(locator).click();
	}

	// ******GetText*******//
	public String doGetElementText(By locator) {
		String iText = getElement(locator).getText();
		System.out.println(iText);
		return iText;
	}

	// ******GetTextFromMultipleElements*******//
	public List<String> doGetElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>(); // used to store the elements in dynamic array
		List<WebElement> mText = getElements(locator);
		System.out.println(mText);
		for (WebElement e : mText) {
			String etext = e.getText();
			eleTextList.add(etext); // used to store the elements in dynamic array
			System.out.println(eleTextList);
		}
		return eleTextList;
	}

	// ******GetAttributes*******//
	public void doGetElementsAttributes(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			System.out.println(attrValue);
		}
	}

	// ******GetCount*******//
	public int getTotalElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println(eleCount);
		return eleCount;
	}

	// ******Is Displayed*******//
	public boolean doElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	// ******SelectDropdDown Utils*******//

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public List<WebElement> getDropDownListFromSelectType(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> totalDropDownOptions = select.getOptions();
		System.out.println(totalDropDownOptions);
		return totalDropDownOptions;
	}

	public void selectDropDownValue(By locator, String value) {
		List<WebElement> getOptions = getDropDownListFromSelectType(locator);
		for (WebElement e : getOptions) {
			String optionsText = e.getText();
			if (optionsText.equals(value)) {
				e.click();
				break;
			}
		}

	}

	public int getDropDownSize(By locator) {
		int optionsCount = getDropDownListFromSelectType(locator).size();
		System.out.println("Toatl Count In DropDown Is-->" + optionsCount);
		return optionsCount;
	}

	public List<String> getDropDownOptionsTextList(By locator) {
		List<String> collectionsOfOptionsText = new ArrayList<>();
		List<WebElement> optionsList = getDropDownListFromSelectType(locator);
		for (WebElement e : optionsList) {
			String optionsText = e.getText();
			collectionsOfOptionsText.add(optionsText);
		}
		System.out.println(collectionsOfOptionsText);
		return collectionsOfOptionsText;
	}

	public List<String> getDropDownOptionsTextListInNonSelectType(By locator) {
		List<String> collectionsOfOptionsText = new ArrayList<>();
		List<WebElement> optionsList = getDropDownListFromSelectType(locator);
		for (WebElement e : optionsList) {
			String optionsText = e.getText();
			collectionsOfOptionsText.add(optionsText);
		}
		System.out.println(collectionsOfOptionsText);
		return collectionsOfOptionsText;
	}

	// **************************DropDown With Non
	// Select****************************//

	public void getDropDownOptionsListFromNonSelectType(By locator, String value) {
		List<WebElement> getDropDownList = getElements(locator);
		for (WebElement e : getDropDownList) {
			String dropDownTextList = e.getText();
			System.out.println(dropDownTextList);
			if (dropDownTextList.equals(value)) {
				e.click();
			}
		}
	}

	// *****************CheckBoxSelection*************//

	public void getCheckboxList(By locator) {
		List<WebElement> checkboxes = getElements(locator);
		System.out.println(checkboxes.size());
		for (WebElement e : checkboxes) {
			if (e.isDisplayed() && e.isEnabled()) {
				e.click();
			}
		}
	}

	// ***********DoSearchAndFetchTheSugestionWord*********//

	public void searchTheSuggestionWord(By locator, String value) {
		List<WebElement> suggestionList = getElements(locator);
		System.out.println(suggestionList.size());

		for (WebElement e : suggestionList) {
			String suggestionText = e.getText();
			System.out.println(suggestionText);

			if (suggestionText.contains(value)) {
				e.click();
				break;
			}
		}
	}

	// ******************Action Class************************//

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	// ******************WebDriver Wait************************//
	
	public void waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementPresenceUsingSendkeys(By locator, int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement foundEle = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		foundEle.sendKeys(value);
		return foundEle;
	}

	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForElementVisibleUsingSendkeys(By locator, int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement foundEle = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		return foundEle;
	}

	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public Alert waitForAlertPresence(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertTextUsingWait(int timeout) {
		return waitForAlertPresence(timeout).getText();
	}

	public void acceptAlertUsingWait(int timeout) {
		waitForAlertPresence(timeout).accept();
	}

	public void dismissAlertUsingWait(int timeout) {
		waitForAlertPresence(timeout).dismiss();
	}

	public void alertSendKeysUsingWait(int timeout, String value) {
		waitForAlertPresence(timeout).sendKeys(value);
	}

	public String waitForTitle(int timeout, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(titleValue));
		return driver.getTitle();
	}

	public String waitForUrl(int timeout, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(urlValue));
		return driver.getCurrentUrl();
	}

	public void waitForFrameAndSwitchToItByIndex(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToItByIdorName(String idOrName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitchToItByFrameWebElement(By frameLocator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	// ******************Fluent Wait************************//
	
	public WebElement waitForElementVisibilityUsingFluentWait(int timeout, int pollingTime, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								.withTimeout(Duration.ofSeconds(timeout))
								.ignoring(NoSuchElementException.class)
								.ignoring(StaleElementReferenceException.class)
								.pollingEvery(Duration.ofSeconds(pollingTime))
								.withMessage("..Element Not Found..");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public Alert waitForAlertUsingFluentWait(int timeout, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								.withTimeout(Duration.ofSeconds(timeout))
								.ignoring(NoAlertPresentException.class)
								.pollingEvery(Duration.ofSeconds(pollingTime))
								.withMessage("Alert Not Found");
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
}
