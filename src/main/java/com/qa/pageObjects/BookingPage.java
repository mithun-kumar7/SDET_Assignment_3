package com.qa.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.TestBase;

public class BookingPage extends TestBase {
	
	private static Logger logger=LogManager.getLogger(TestBase.class);

	public BookingPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//span[@class='pointer']//span)[6]")
	WebElement pointerOption;

	@FindBy(xpath = "//span[@class='down sort-arrow']")
	WebElement downPointer;

	@FindBy(xpath = "//span[@class='pointer']//span[contains(text(),'Price')]")
	WebElement price;

	@FindBy(xpath = "//*[@class='fli-list one-way'][1]//div//div//div//div//div//div//button[@class='ViewFareBtn  text-uppercase ']")
	WebElement viewFare_btn;

	@FindBy(xpath = "(//button[@class='btn fli_primary_btn text-uppercase'])[1]")
	WebElement bookNow_Btn;

	public void waitMethod(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	

	public void clickOnLowToHighFilter() {
		waitMethod(price);
		String pointer = pointerOption.getAttribute("class");
		if (pointer.contains("up")) {
			downPointer.click();
		}
		logger.info("Clicked on filter");
	}

	public void clickOnViewFareBtn() {
		viewFare_btn.click();
		logger.info("Clicked on view fare");
	}

	public ReviewPage clickOnBookNow() {
		bookNow_Btn.click();
		logger.info("Clicked on Book now");
		return new ReviewPage();
	}

}
