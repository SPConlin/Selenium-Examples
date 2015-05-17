package com.wolvesbanedesigns.selenium.examples.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.wolvesbanedesigns.selenium.examples.pageobjects.WPMain;

public class HomePageSearchTest {
	WebDriver driver = null;
	
  @Test
  public void testHomePagePrimarySidebarSearchBox() {
	  // Navigate to the website
	  driver.get("http://automation.wolvesbanedesigns.com");
	  
	  // Instantiate blog page
	  WPMain blogPage = new WPMain(driver);
	  
	  // Perform a test and confirm the correct page is returned
	  blogPage.performSearch("Test Automation");
	  Assert.assertTrue(blogPage.verifyPageHeaderTitle("Search Results for: Test Automation"), "Incorrect page header displayed.");
  }
  @BeforeMethod
  public void beforeMethod() {
	  // Instantiate a new Firefox driver session
	  driver = new FirefoxDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  // Close the browser session
	  driver.quit();
  }

}
