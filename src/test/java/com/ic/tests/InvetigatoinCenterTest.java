package com.ic.tests;

import org.testng.annotations.Test;

import com.ic.pages.HomePage;
import com.ic.pages.JobConsole;
import com.ic.pages.LoginPage;
import com.tests.BaseTest;

public class InvetigatoinCenterTest extends BaseTest {
	
	private static final String USERNAME = "ic_admin";
	private static final String PASSWORD = "ic_admin"; 
	
	@Test
	public void login(){
		LoginPage loginPage = new LoginPage(driver);
		HomePage homepage = loginPage.login(USERNAME, PASSWORD);
		JobConsole jobConsole = homepage.goToJobConsole();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
