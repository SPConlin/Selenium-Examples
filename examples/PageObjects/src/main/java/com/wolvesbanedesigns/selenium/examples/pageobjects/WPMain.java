/**
 * 
 */
package com.wolvesbanedesigns.selenium.examples.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wolvesbanedesigns.selenium.examples.components.FormElement;

/**
 * @author Shawn P. Conlin
 *
 */
public class WPMain extends BasePage {
	// Declare element locators
	private static final By TXTFLD_SEARCH = By.xpath("//*[@id='search-2']/form/label/input");
	private static final By PAGE_HEADER_TITLE = By.xpath("//*[@id='content']/header/h1[@class='page-title']");

	/**
	 * @param driver
	 */
	public WPMain(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Check if the specified text is present in the page header
	 * @param expectedTitle
	 * @return
	 */
	public boolean verifyPageHeaderTitle(String expectedTitle) {
		return this.isTextPresent(PAGE_HEADER_TITLE, expectedTitle, 5);
	}
	
	/**
	 * Search for the specified test using the search box in the primary side bar.
	 * @param term
	 */
	public void performSearch(String term) {
		FormElement searchBox = new FormElement(webDriver, TXTFLD_SEARCH);
		searchBox.setValue(term, 5);
		searchBox.submit();
	}

}
