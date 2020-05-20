package com.qa.pageObjects;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.TestBase;

public class ReviewPage extends TestBase {
	
	private static Logger logger=LogManager.getLogger(TestBase.class);

	public ReviewPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h4[@class='font22 latoBold whiteText headerTitle']")
	WebElement reviewTitle;
	
	

	public void switchToReviewPage() throws InterruptedException {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(2000);
		logger.info("Switched to review page");
	}

	public String reviewTitle() throws InterruptedException {
		Thread.sleep(2000);
		logger.info("Review title is captured");
		return reviewTitle.getText();
	}

}
