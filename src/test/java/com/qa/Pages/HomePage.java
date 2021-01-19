package com.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	//Class will contain locator available on Home page and activity required by home page for script.
	
	private By loginSuccessNotification = By.xpath("//span[contains(text(),'signed in')]");
	private By shippingList = By.xpath("//nav//div//a[@href='/shopping-list/']");

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);		
	}
	
	public void waitloginSuccessNotificationdisappear() {
		//function will wait until invisibility of successful login notification.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loginSuccessNotification));
	}
	public void waitForShippingListToClickable() {
		//function will wait until shipping list menu is clickable.
		wait.until(ExpectedConditions.elementToBeClickable(shippingList));        
	}
	public void clickOnShippingListMenu() {
		//button will click on shipping list menu.
		clickButton(shippingList);
	}

}
