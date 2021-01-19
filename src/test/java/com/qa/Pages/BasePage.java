package com.qa.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	//Class will contains all General webelement like textbox, button and activities related to webelement.
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public BasePage(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	public WebElement getElement(By locator) {
		//function will return webelement based on locator.
		return driver.findElement(locator);
	}
	public List<WebElement> getElements(By locator){
		//function will return webelements based on locator in list.
		return driver.findElements(locator);
	}
	public String getElementText(By locator) {
		//function will return text for webelemt.
		return getElement(locator).getText();
	}
	public void setValueForTextBox(By locator, String value) {
		//function will set value for text box based on locator and value to set on it.
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	public void clickButton(By locator) {
		//function will click on button.
		getElement(locator).click();
	}
	public void waitTillElementToBeClickable(By locator) {
		//function will wait until element is not clickable.
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void waitTillElementToBeVisible(By locator) {
		//function will wait until element is not visible.
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(locator));	    
	}
	public void waitTillElementToInvisible(By locator) {
		//function will wait until element got invisible.
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

}
