package com.qa.utils;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.TestBase;



public class TestListeners implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getTestClass().getName() + "::" + result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Successful<b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);

	}

	public void onTestFailure(ITestResult result) {
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summery><b><font color=red>" + "Exception Occured, click to see details: "
				+ "</font></b></summery>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		
		String path=TestBase.takeScreenShot(result.getMethod().getMethodName());
		try {
			extentTest.get().fail("<b><font color=red>"+"Screenshot Of Failure"+"</font></br>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(IOException e) {
			extentTest.get().fail("Test Failed, cannot attach screenshot");
		}
		
		String logText="<b>Test Method "+result.getMethod().getMethodName()+" Failed</b>";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped<b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		if(extent != null) {
			extent.flush();
		}

	}

}
