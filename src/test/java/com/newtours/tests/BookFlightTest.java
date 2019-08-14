package com.newtours.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newtours.pages.FindFlightPage;
import com.newtours.pages.FlightConfirmationPage;
import com.newtours.pages.FlightDetailsPage;
import com.newtours.pages.RegistrationConfirmationPage;
import com.newtours.pages.RegistrationPage;
import com.tests.BaseTest;

public class BookFlightTest extends BaseTest {

	private String numOfPassengers;
	private String expectedPrice;
	
	@BeforeTest
	@Parameters({"numOfPassengers", "expectedPrice"})
	public void setupParameters(String numOfPassengers, String expectedPrice){
		this.numOfPassengers = numOfPassengers;
		this.expectedPrice = expectedPrice;
	}
	
	@Test
	public void registrationPageTest(){
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();
		registrationPage.enterUserCredentails("selenium", "docker");
		registrationPage.enterUserCredentails("selenium", "docker");
		registrationPage.submit();
	}
	
	@Test(dependsOnMethods = "registrationPageTest")
	public void registrationConfirmationTest(){
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		registrationConfirmationPage.goToDetailsPage();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightDetailsTest(){
		FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
		flightDetailsPage.selectPassengers(numOfPassengers);
		flightDetailsPage.goToFindFlightsPage();
	}
	
	@Test(dependsOnMethods = "flightDetailsTest")
	public void findFlightTest(){
		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.submitFindFlightPage();
		findFlightPage.goToFlightConfirmationPage();
	}
	
	@Test(dependsOnMethods = "findFlightTest")
	public void flightConfirmationTest(){
		FlightConfirmationPage flightConfirmaitonPage = new FlightConfirmationPage(driver);
		String actualPrice = flightConfirmaitonPage.getPrice();
		Assert.assertEquals(actualPrice, expectedPrice);
	}
	
}
