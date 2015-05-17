/**
 * 
 */
package com.wolvesbanedesigns.selenium.examples.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Shawn P. Conlin
 *
 */
public class FormElement implements WebElement {
	private WebDriver webDriver = null;
	private By elementLocator = null;

	/**
	 * 
	 */
	public FormElement(WebDriver driver, By locator) {
		this.webDriver = driver;
		this.elementLocator = locator;
	}
	/**
	 * Accepts a locator and a value object and determines the type of field to processed and casts the object to the
	 * correct type and handles the field accordingly This function currently supports only HTML 4 input types
	 * 
	 * @param locator
	 * @param value
	 * @return
	 */
	public boolean setValue(Object value, int timeout) {
		boolean success = false;
		
		try {
			WebElement field = webDriver.findElement(elementLocator); 
			if (null != field) {
				switch (field.getTagName().toLowerCase().trim()) {
					case "input":
						// TODO add support for HTML Input types
						switch (field.getAttribute("type").toLowerCase().trim()) {
							case "text":
							case "hidden":
							case "password":
							case "search":
								if (!(field.getText().equalsIgnoreCase((String) value) || (field.getAttribute("value").equalsIgnoreCase((String) value)))) {
									field.clear();
									field.sendKeys((String) value);
									success = true;
								}
								break;
							case "checkbox":
								if (field.isSelected() != (boolean) value) {		
									field.click();
								}
								success = true;
								break;
							case "radio":
								field.click();
								success = true;
								break;
							case "button":
							case "submit":
							case "reset":
							case "image":
								field.click();
								success = true;
								break;
							case "file":
								// TODO: add processing code for the file input type
						}
						break;
					case "textarea":
						field.clear();
						field.sendKeys((String) value);
						success = true;
						break;
					case "select":
						try {
							Select DDL = new Select(field);
							if (value.getClass().toString().contains("ArrayList")) {
								// TODO - add multiple selection logic for list boxes
								if (DDL.isMultiple()) {
									
								} else {
									success = false;
								}
							} else {
									DDL.selectByVisibleText((String) value);
									success = true;
							}
						} catch (NoSuchElementException NSEE) {
							String holder = (String) value;
							if (holder.equals("") || holder.isEmpty()) {
								success = true;
							}
							success = false;
						}
						break;
					default:
						success = false;
				}

			}
		} catch (StaleElementReferenceException sere) {
			return setValue(value, timeout);
		} catch (TimeoutException toe) {
			success = false;
		} catch (Exception ex) {
			throw ex;			
		}

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#clear()
	 */
	public void clear() {
		webDriver.findElement(elementLocator).clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#click()
	 */
	public void click() {
		webDriver.findElement(elementLocator).click();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#findElement(org.openqa.selenium.By)
	 */
	public WebElement findElement(By by) {
		WebElement webTable = null;

		try {
			webTable = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (TimeoutException toe) {
			// captureError("timeout" + new
			// SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + ".jpg");
			throw toe;
		}
		return webTable.findElement(by);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#findElements(org.openqa.selenium.By)
	 */
	public List<WebElement> findElements(By by) {
		List<WebElement> items = new ArrayList<>();
		items = webDriver.findElement(elementLocator).findElements(by);
		return items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#getAttribute(java.lang.String)
	 */
	public String getAttribute(String name) {
		return webDriver.findElement(elementLocator).getAttribute(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#getCssValue(java.lang.String)
	 */
	public String getCssValue(String propertyName) {
		return webDriver.findElement(elementLocator).getCssValue(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#getLocation()
	 */
	public Point getLocation() {
		return webDriver.findElement(elementLocator).getLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#getSize()
	 */
	public Dimension getSize() {
		return webDriver.findElement(elementLocator).getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#getTagName()
	 */
	public String getTagName() {
		return webDriver.findElement(elementLocator).getTagName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#getText()
	 */
	public String getText() {
		return webDriver.findElement(elementLocator).getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#isDisplayed()
	 */
	public boolean isDisplayed() {
		return webDriver.findElement(elementLocator).isDisplayed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#isEnabled()
	 */
	public boolean isEnabled() {
		return webDriver.findElement(elementLocator).isEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#isSelected()
	 */
	public boolean isSelected() {
		return webDriver.findElement(elementLocator).isSelected();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#sendKeys(java.lang.CharSequence[])
	 */
	public void sendKeys(CharSequence... keysToSend) {
		webDriver.findElement(elementLocator).sendKeys(keysToSend);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.WebElement#submit()
	 */
	public void submit() {
		webDriver.findElement(elementLocator).submit();
	}

}