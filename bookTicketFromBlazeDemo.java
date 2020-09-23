package com.demo.test.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import junit.framework.Assert;


public class bookTicketFromBlazeDemo{
	WebDriver driver;
	@BeforeTest()
	public void setUp() {
		
		
		String baseUrl="https://blazedemo.com/";
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.addArguments("--test-type");
		options.addArguments("--disable-extensions"); 
		options.addArguments("--safebrowsing-disable-extension-blacklist");
		options.addArguments("--safebrowsing-disable-download-protection");
		options.setExperimentalOption("useAutomationExtension", false);
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
		
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		  driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
	}
/*This test will perform the below actions
 * Launching the URL
 * Verifying the page title
 * Verify the presence of welcome page header
 * @author: Sudhansu
 * Date: 23rd Sept 2020*/
	@Test(description = "Verify launching the application", priority = 1)
	public  void lanuchingTheUrl() throws Exception{
		
		
		//Verify the page title
		Assert.assertEquals("BlazeDemo", driver.getTitle().trim());
		
		//Verify the welcome page
		Assert.assertTrue(isElementVisible(By.xpath("//h1[text()='Welcome to the Simple Travel Agency!']"), driver));
	
	}
	/*This test will perform the below actions
	 * clicking on the find flights
	 * Choosing the flights
	 * @author: Sudhansu
	 * Date: 23rd Sept 2020*/
	@Test(description = "Verify launching the application", priority = 2)
	public void selectingTheFlight() throws Exception{
		//click on the find flight button
		driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
		//Clicking on CHooese the flight button
		driver.findElement(By.xpath("(//input[@value='Choose This Flight'])[1]")).click();
	}
	/*This test will perform the below actions
	 * Entering all the user details 
	 * clicking on the purchase flight button
	 * @author: Sudhansu
	 * Date: 23rd Sept 2020*/
	@Test(description = "ticket booking by providing all the user details", priority = 3)
	public void bookTheTicket() throws Exception{
		//Provide the first name
		driver.findElement(By.id("inputName")).sendKeys("Sudhansu");
		//Provide the city name
		driver.findElement(By.id("city")).sendKeys("Bangalore");
		//Provide the state name
		driver.findElement(By.id("state")).sendKeys("Karnataka");
		//Provide the ZIP code
		driver.findElement(By.id("zipCode")).sendKeys("560100");
		//Provide the ZIP code
		driver.findElement(By.id("nameOnCard")).sendKeys("Sudhansu Panda");
		//Clicking on Purchase flight button
		driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
	}
	/*This test will perform the below actions
	 * Verifying the Success message
	 * Verifying the ticket ID
	 * @author: Sudhansu
	 * Date: 23rd Sept 2020*/
	@Test(description = "Verify Ticket ID", priority = 4)
	public void verifyTheFlightID() throws Exception{
		//Verify the Success message present 
		Assert.assertTrue(isElementVisible(By.xpath("//h1[text()='Thank you for your purchase today!']"), driver));
		//Verify the Ticket ID
		Assert.assertNotNull(driver.findElement(By.xpath("//td[text()='Id']//following-sibling::td")).getText());
		}
	
	
	public static boolean isElementVisible(By ele, WebDriver driver) {
		try {
			driver.findElement(ele).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {

		/*
		 * extent.endTest(test); extent.flush();
		 */

		// driver.get(baseUrl);
		// obj_CRSPNGFunctions.fn_MS_Logout(driver);

		if (driver != null) {
			// Closing the driver once the suite execution is finished.
			driver.close();
			// Quitting the driver once the suite execution is finished.
			driver.quit();
		}
}
}
