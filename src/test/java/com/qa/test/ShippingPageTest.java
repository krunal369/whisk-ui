package com.qa.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.Pages.HomePage;
import com.qa.Pages.LoginPage;
import com.qa.Pages.ShippingListPage;

public class ShippingPageTest extends BaseTest{
	//class will contain all test case related to shipping page.
	
	private LoginPage loginPageReferece;
	private HomePage homePageReference;
	private ShippingListPage shippingListPageReference;
	private Properties properties;
	
	public void initializePageObjects() {
		/**
		 * This function will initialize required page object to run Shipping list test.
		 */
		loginPageReferece = new LoginPage(driver, wait);
		homePageReference = new HomePage(driver, wait);
		shippingListPageReference= new ShippingListPage(driver, wait);
		properties = readPropertyFileValue();
	}
	
	//Test -1
	@Test
	public void VerifyShippingList() {
		initializePageObjects();
		//1.Navigate to https://my.whisk-dev.com/
		//2. Sign in
		loginPageReferece.doLogin(properties.getProperty("UserName").trim(), properties.getProperty("Password").trim());
		homePageReference.waitloginSuccessNotificationdisappear();
		homePageReference.waitForShippingListToClickable();		
		
		//3.Navigate to the Shopping tab
		homePageReference.clickOnShippingListMenu();
		
		//4/Create a Shopping list
		//Here to generate random name it will do as below.
		//1)get current date
		//2)get random number between 1 to 1000.
		//3)Append date with random number and create a new name for shipping list.
		String randomShoppingList= new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()).concat(Integer.toString(new Random().nextInt(10000)));  
		
		//5. Add 5 items into shipping list.
		//Get Va;
		shippingListPageReference.createNewShippingList(randomShoppingList);
		ArrayList<String> ExpectedItemList = new ArrayList<String>(Arrays.asList(properties.getProperty("ShippingListItems").split("\\s*,\\s*")));
        shippingListPageReference.addItemasIntoShippingList(ExpectedItemList);
        
        //6.Check by Name that 5 items are added to the Shopping list.
        Assert.assertEquals(shippingListPageReference.getShippingListItems().containsAll(ExpectedItemList),true, "All Items are not available in shipping list.");
	}
	
	//Test -2
	@Test
	public void VerifyShippingName() {
		initializePageObjects();
		//1.Navigate to https://my.whisk-dev.com/
		//2. Sign in.
		loginPageReferece.doLogin(properties.getProperty("UserName"), properties.getProperty("Password"));
		homePageReference.waitloginSuccessNotificationdisappear();
		homePageReference.waitForShippingListToClickable();		
		
		//3.Navigate to the Shopping tab.
		homePageReference.clickOnShippingListMenu();
		String randomShoppingList= new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()).concat(Integer.toString(new Random().nextInt(10000)));  
		
		//4. Create a Shopping list.
		shippingListPageReference.createNewShippingList(randomShoppingList);
		shippingListPageReference.waitNewlyAddedShippingListClickable(randomShoppingList);
		
		//5.Delete Shopping list.
		shippingListPageReference.deleteShippingList(randomShoppingList);
		
		//6.Check that user doesn't have Shopping lists.
		Assert.assertEquals(shippingListPageReference.getAllShippingList().contains(randomShoppingList), false,  "Newly added shopping list is not deeted.");
	}
}
