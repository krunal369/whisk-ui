package com.qa.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingListPage  extends BasePage{
	//Class will contain locator available on shipping page and activity required by shipping page for script.
	
	private By createNewList;
	private By shippingListName;
	private By createButton;
	private By searchItem;
	private By searhedListItem;
	private By shippingItemList;
	private By allShippingList;
	private By deleteButton;
	private By deleteButtonOnConfirmationBox;
	private By deletedItemNotification;
	
	//xpaths are for some dynamic elements.		
	private String newlyCreatedShippingListxpath= "//div[@data-testid='shopping-lists-list-name' and text()='";
	private String xpathForShippingListMenu="(//*[@data-testid='shopping-lists-list-name']/parent::div/following-sibling::div//button)[";
	
	public ShippingListPage(WebDriver driver, WebDriverWait wait) {
		//will define all locator.
		super(driver, wait);	
		createNewList=By.xpath("//a//div[text()='Create new list']");
		shippingListName = By.xpath("//input[@name='name' and @placeholder='Name your list']");
		createButton = By.xpath("//button[@data-testid='create-new-shopping-list-create-button']");
		searchItem = By.xpath("//input[@data-testid='desktop-add-item-autocomplete']");
		searhedListItem = By.xpath("(//div[@data-testid='desktop-add-item-autocomplete']//div//div)[1]");
		shippingItemList = By.xpath("//span[@data-testid='shopping-list-item-name']");
		allShippingList = By.xpath("//*[@data-testid='shopping-lists-list-name']");
		deleteButton=By.xpath("//button[@data-testid='shopping-list-delete-menu-button']");
		deleteButtonOnConfirmationBox= By.xpath("//button[@data-testid='confirm-delete-button']");
		deletedItemNotification = By.xpath("//span[contains(text(),'List was deleted')]");
	}
	
	public void createNewShippingList(String newListName) {
		/**
		 *function use: To create new shipping list.
		 *It will accept list name in parameter.
		 */
		clickButton(createNewList);
		setValueForTextBox(shippingListName, Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		setValueForTextBox(shippingListName, newListName);
		clickButton(createButton);
	}
	
	public void addItemasIntoShippingList(ArrayList<String> ShippingList) {
		/**
		 * function use: To add item into shipping list.
		 * It will take new shipping item list in parameter.
		 * Flow: it will search name of item one by one and pick first item from the searched items. [Note:
		 * right now just for exercise purpose I pick first item. To pick exact item we can elaborate logic.]
		 * click on that item. So it will be add into shipping list.
		 */
		for (String item : ShippingList) {
			setValueForTextBox(searchItem, item.trim());
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchItem));
			clickButton(searhedListItem);
		}
	}
	public ArrayList<String> getShippingListItems() {
		/**
		 * function will return shipping list items in ArrayList.
		 */
		ArrayList<String> ActualItemList = new ArrayList<String>();
		for (WebElement webElement : getElements(shippingItemList)) {
        	ActualItemList.add(webElement.getText());			
		}
		return ActualItemList;
	}
	public void waitNewlyAddedShippingListClickable(String randomShippingList) {
		//function will wait until newly added shipping list
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newlyCreatedShippingListxpath + randomShippingList+"']")));
	}
	public void deleteShippingList(String shippinglistToDelete) {
		/**
		 * function use: To delete shipping list.
		 * It will search shipping list and click on menu available beside shipping list.
		 * It will click on delete button then confirmation button.
		 */
		List<WebElement> allshippingLists=getElements(allShippingList);
		int i=1;
		for (WebElement webElement : allshippingLists) {
			if (webElement.getText().equals(shippinglistToDelete)) {
				getElement(By.xpath(xpathForShippingListMenu+ i+"]")).click();
				clickButton(deleteButton);
				wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButtonOnConfirmationBox));
				clickButton(deleteButtonOnConfirmationBox);		        
				wait.until(ExpectedConditions.invisibilityOfElementLocated(deletedItemNotification));
		        break;
			}
			i++;
		}
	}
	public ArrayList<String> getAllShippingList(){
		/**
		 * function use: To delete shipping list.
		 * It will return all shipping lists available.
		 */
		ArrayList<String> actaulShippingList = new ArrayList<String>();
		for (WebElement element : getElements(allShippingList)) {
			actaulShippingList.add(element.getText());
		}
		return actaulShippingList;
	}
	
}
