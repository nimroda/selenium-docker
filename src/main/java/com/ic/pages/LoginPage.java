package com.ic.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id = "username")
	private WebElement usernameTxt;
	
	@FindBy(id = "password")
	private WebElement passwordTxt;
	
	@FindBy(xpath = "//a[@class='submit-button']")
	private WebElement loginBtn;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String aUser, String aPassword){
		this.driver.get("http://il02vlapp5000.cfrm.dev.local:30781/InvestigationCenter/");
		usernameTxt.sendKeys(aUser);
		passwordTxt.sendKeys(aPassword);
		loginBtn.click();
		return new HomePage(driver);
	}
	
//	public void goToHomePage(){
//		this.wait.until(ExpectedConditions.visibilityOf(this.videosLnk));
//		this.videosLnk.click();
//	}
}
