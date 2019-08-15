package com.ic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//div[contains(text(),'MY ALERTS')]")
	private WebElement dashboard;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public JobConsole goToJobConsole() {
		this.wait.until(ExpectedConditions.visibilityOf(this.dashboard));
		this.waitForElementClickable(driver, By.xpath(".//*[@id='app-navigation']/ul/li[4]/button"), 60);
		WebElement we = this.waitForElementOnDOM(driver, By.xpath(".//div[@id='app-navigation']//a[contains(., 'Jobs & Services Manager')]"), 60);
		//Clicking on invisible Element
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", we);
		
	//	driver.navigate().refresh();
		return new JobConsole(driver);
	}
	
	//Checking that an element is present on the DOM of a page. 
	//This does not necessarily mean that the element is visible.
	private WebElement waitForElementOnDOM(WebDriver driver, By by, int timeOutInSeconds) {
		WebElement dynamicElement = (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
		return dynamicElement;
	}
	
	//Checking that an element is visible and enabled such that you can click it.
	private WebElement waitForElementClickable(WebDriver driver, By by, int timeOutInSeconds) {
		WebElement dynamicElement = (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(by));
		return dynamicElement;
	}
}
