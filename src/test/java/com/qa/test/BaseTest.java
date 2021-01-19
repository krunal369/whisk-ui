package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.Pages.BasePage;
public class BaseTest {
	//class will use to define some basic activities like initialize browser, reading values from property file, close browser etc.
	public WebDriver driver;
	public WebDriverWait wait;
	public BasePage basePage;
	
	
	public Properties readPropertyFileValue() {
		//Will load property file and return property class reference.
		Properties prop = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("configuration.properties")) {                
            prop.load(inputStream);
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	@BeforeMethod
	public void setUp(){
		//will initialize browser.
		try {
			System.setProperty("webdriver.chrome.driver", new File("./chormeDriverExe/chromedriver.exe").getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		driver.manage().window().maximize();
		driver.get(readPropertyFileValue().getProperty("baseUrl").trim());
		basePage= new BasePage(driver, wait);
	}
	
	@AfterMethod
	public void tearDown() {
		//close browser.
		driver.quit();
	}

}
