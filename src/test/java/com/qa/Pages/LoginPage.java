package com.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
	//Class will contain locator available on Login page and activity required by login page for script.
	//PageLocators
	By acceptCookies = By.xpath("//button//div[text()='Accept cookies']");
	By userName = By.xpath("//input[@name='username']");
	By password = By.xpath("//input[@name='password']");
	By submit = By.xpath("//button[@type='submit']");
	
	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void doLogin(String userNameValue, String passwordValue) {
		/**
		 * This function will perform login operation.
		 * It will require user name and password.
		 */
		clickButton(acceptCookies);
		setValueForTextBox(userName, userNameValue);
		clickButton(submit);
		//will wait until visibility of password field.
		wait.until(ExpectedConditions.visibilityOfElementLocated(password));
		setValueForTextBox(password, passwordValue);
		clickButton(submit);
	}
}
