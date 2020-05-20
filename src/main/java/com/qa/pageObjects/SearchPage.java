package com.qa.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.TestBase;

public class SearchPage extends TestBase {
	private static Logger logger=LogManager.getLogger(TestBase.class);

	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li[contains(text(),'Round Trip')]")
	WebElement roundTrip_radioBtn;

	@FindBy(xpath = "//li[contains(text(),'Oneway')]")
	WebElement oneWay_radioBtn;

	@FindBy(xpath = "//input[@id='fromCity']")
	WebElement fromCity_txt_box;

	@FindBy(xpath = "//input[@placeholder='From']")
	WebElement fromCity_field;

	@FindBy(xpath = "//span[contains(text(),'To')]")
	WebElement toCity_txt_box;

	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement toCity_field;

	@FindBy(xpath = "//span[contains(text(),'DEPARTURE')]")
	WebElement calender_field;

	@FindBy(xpath = "//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Caption']")
	WebElement MonthElement;

	@FindBy(xpath = "//span[contains(@class,'DayPicker-NavButton DayPicker-NavButton--next')]")
	WebElement next_button;

	@FindBy(xpath = "//a[contains(@class,'primaryBtn font24 latoBold widgetSearchBtn')]")
	WebElement search_btn;

	public void selectRoundTrip() {
		roundTrip_radioBtn.click();
		logger.info("Selected round trip radio button");
	}

	public void selectOneWay() {
		oneWay_radioBtn.click();
		logger.info("Selected one way radio button");
	}

	public void enterSrcCity(String cityName) throws InterruptedException {
		fromCity_txt_box.click();
		fromCity_field.sendKeys(cityName);
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//p[contains(text(),'" + cityName + "')]"))).click().perform();
		logger.info("Source city is entered: "+cityName);
	}

	public void enterDestCity(String cityName) throws InterruptedException {
		toCity_txt_box.click();
		toCity_field.sendKeys(cityName);
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//p[contains(text(),'" + cityName + "')]"))).click().perform();
		logger.info("Destination city is entered: "+cityName);
	}

	public void enterJourneyDate(String monthName, int day) throws InterruptedException {
		Thread.sleep(1000);
		calender_field.click();
		while (true) {
			String currentText = MonthElement.getText();
			String afterFormat = currentText.replaceAll("[^a-zA-Z]", "");
			if (afterFormat.contains(monthName)) {
				break;
			} else {
				next_button.click();
			}
		}
		driver.findElement(By.xpath(
				"//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Body']//div//div[@aria-disabled='false']//*[text()='"
						+ day + "']"))
				.click();
		logger.info("journey date: "+day+"/"+monthName);
	}

	public BookingPage clickOnSearchButton() throws InterruptedException {
		search_btn.click();
		logger.info("Clicked on search button");
		return new BookingPage();
	}

}
