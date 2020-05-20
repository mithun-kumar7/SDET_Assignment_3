package com.qa.utils;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class ExtentManager {
	
	private static ExtentReports extent;
	public static ExtentReports createInstance() {
		String fileName=getReportName();
		String directory=System.getProperty("user.dir")+"/Reports/";
		new File(directory).mkdirs();
		String path=directory+fileName;
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(path);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Make my trip::Automation Report");
		htmlReporter.config().setReportName("Automation test result");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.setSystemInfo("Wipro", "Assignment_3_Make My Trip");
		extent.setSystemInfo("browser", "Chrome");
		extent.attachReporter(htmlReporter);
		return extent;
	}
	
	public static String getReportName() {
		Date d=new Date();
		String fileName="AutomationReport_"+d.toString().replace(":", "_").replace(" ", "_")+".html";
		return fileName;
	}

}
