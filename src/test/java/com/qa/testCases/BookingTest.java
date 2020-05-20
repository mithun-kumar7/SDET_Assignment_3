package com.qa.testCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.TestBase;
import com.qa.pageObjects.BookingPage;
import com.qa.pageObjects.ReviewPage;
import com.qa.pageObjects.SearchPage;



public class BookingTest extends TestBase {
	
	private static Logger logger=LogManager.getLogger(TestBase.class);
	
	SearchPage searchPage;
	BookingPage bookingPage;
	ReviewPage reviewPage;

	@BeforeMethod
	public void setUp(Method m) throws IOException {
		logger.info("\n"+"********** starting test: *********"+m.getName()+"***********"+"\n");
		initialise();
		searchPage=new SearchPage();
	}
 
	@Test
	public void bookingReviewTest() throws InterruptedException {
		searchPage.selectRoundTrip();
		searchPage.selectOneWay();
		searchPage.enterSrcCity("Kolkata");
		searchPage.enterDestCity("Bengaluru");
		searchPage.enterJourneyDate("September", 15);
		bookingPage=searchPage.clickOnSearchButton();
		bookingPage.clickOnLowToHighFilter();
		bookingPage.clickOnViewFareBtn();
		reviewPage=bookingPage.clickOnBookNow();
		reviewPage.switchToReviewPage();
		String title=reviewPage.reviewTitle();
		Assert.assertEquals(title, "Review your booking");
	}

	@AfterMethod
	public void teardown(Method m) {
		driver.quit();
		logger.info("Chrome browser is closed");
		logger.info("\n"+"********** Test is completed: *********"+m.getName()+"***********"+"\n");
	}

}
