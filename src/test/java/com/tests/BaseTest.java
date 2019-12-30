package com.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		// docker-compose up -d --scale chrome=4 --scale firefox=4 --> to execute tests in parallel we need to scale out hub services
		// when scaling in (decreasing number of hub services, docker-compose kills the service but the hub service doesn't know about it immediately, it might take a minute or so to be updated in the hub dashboard)
		// to run zalenium use --> docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start
		// view zalneium console at --> http://192.168.139.128:4444/grid/admin/live
		// view zalenium dashboard for replay and logs at --> http://192.168.139.128:4444/dashboard/#
		// To package both page object jar and test jar execute --> mvn clean package -DskipTests
		// To execute a test suite from command line use whitin the target folder --> java -cp selenium-docker.jar;selenium-docker-tests.jar;libs/* org.testng.TestNG ../search-module.xml //windows, for linux replace ; with :
		// To pass environmnet variables for execution use --> java -cp selenium-docker.jar;selenium-docker-tests.jar;libs/* -DBROWSER=firefox org.testng.TestNG ../search-module.xml
		// You can filter the docker-compose up by --> docker-compose up | grep -e 'search-module' -e 'book-flight-module'
		// Create more services/containers to improve performance/parallel execution -->  docker-compose up --scale chrome=2 --scale firefox=2
		// remove "none" images from docker images --> docker system prune -f
		
		// For file upload scenario, assuming driver is the name of the variable for WebDriver instance, We need to add the below code.
		// if(driver instanceof RemoteWebDriver){
		// ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		
		String host = "192.168.139.128";
		DesiredCapabilities dc;
		
		if (System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
			dc = DesiredCapabilities.firefox();
		}
		else {
			dc = DesiredCapabilities.chrome();
			dc.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
		}
		
		if (System.getProperty("HUB_HOST") != null) {
			host = System.getProperty("HUB_HOST");
		}
		
		String completeUrl = "http://" + host + ":4444/wd/hub";
		// will show test name from testng in zalenium dashboard
		dc.setCapability("name", ctx.getCurrentXmlTest().getName());
		this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
		
	  //Uncomment to test locally	
//	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\naviram\\Downloads\\chromedriver_win32_78\\chromedriver.exe");	
//      this.driver = new ChromeDriver();
//      this.driver.manage().window().maximize();
	}
	
	@AfterTest
	public void quitDriver(){
		Cookie cookie = new Cookie("zaleniumTestPassed", "true");
	    this.driver.manage().addCookie(cookie);
		this.driver.quit();
	}
	
}
