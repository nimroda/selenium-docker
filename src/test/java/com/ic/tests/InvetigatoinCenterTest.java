package com.ic.tests;

import org.testng.annotations.Test;

import com.ic.pages.LoginPage;
import com.tests.BaseTest;

public class InvetigatoinCenterTest extends BaseTest {
	
	private static final String USERNAME = "ic_admin";
	private static final String PASSWORD = "ic_admin"; 
	
	@Test
	public void login(){
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goToHomePage(USERNAME, PASSWORD);
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
