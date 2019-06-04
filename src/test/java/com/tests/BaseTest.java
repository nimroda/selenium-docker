package com.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeTest
	public void setupDriver(ITestContext ctx) throws MalformedURLException{
		
		// BROWSER ==> chrome / firefox
		// HUB_HOST ==> IP or hostname
		
		String host = "192.168.139.128";
		DesiredCapabilities dc;
		
		if (System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
			dc = DesiredCapabilities.firefox();
		}
		else {
			dc = DesiredCapabilities.chrome();
		}
		
		if (System.getProperty("HUB_HOST") != null) {
			host = System.getProperty("HUB_HOST");
		}
		
		String completeUrl = "http://" + host + ":4444/wd/hub";
		// will show test name from testng in zalenium dashboard
		dc.setCapability("name", ctx.getCurrentXmlTest().getName());
		this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
	}
	
	@AfterTest
	public void quitDriver(){
		Cookie cookie = new Cookie("zaleniumTestPassed", "true");
	    this.driver.manage().addCookie(cookie);
		this.driver.quit();
	}
	
}
