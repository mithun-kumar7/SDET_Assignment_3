package com.qa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	public static WebDriver driver;
	private static Logger logger=LogManager.getLogger(TestBase.class);

	public static void initialise() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browser = (String) prop.get("browser");
		if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome,driver", ("user.dir") + "\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver();
			logger.info("Chrome Browser is opened");
		}
		if (browser.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", ("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		logger.info("Navigated to url: "+prop.getProperty("url"));
	}

	public static String takeScreenShot(String methodName) {
		String fileName=getScreenShotName(methodName);
		String directory=System.getProperty("user.dir")+"/Screenshots/";
		new File(directory).mkdirs();
		String path=directory+fileName;
		
		try {
			File screenShot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShot, new File(path));
			System.out.println("************************************");
			System.out.println("Screenshot Stored At: "+path);
			System.out.println("************************************");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenShotName(String methodName) {
		Date d=new Date();
		String fileName=methodName+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return fileName;
	}

}
