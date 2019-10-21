package com.testautomation.basemanager;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
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

public class ExtentListeners implements ITestListener, ISuiteListener {

	private static ExtentReports extent;
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {

		String suiteName = result.getTestClass().getName();

		System.out.println("Suite Name" + suiteName);

		if (!suiteName.contains("apitest")) {

			String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testReport.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
							+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>")
							+ "</details>" + " \n");

			try {

				ExtentManager.captureScreenshot();
				testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
						MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
			} catch (IOException e) {

			}

			String failureLogg = "TEST CASE FAILED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			testReport.get().log(Status.FAIL, m);
		} else {

			System.out.println("Inside Failure");
			String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testReport.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
							+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>")
							+ "</details>" + " \n");

			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>");

			String failureLogg = "TEST CASE FAILED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			testReport.get().log(Status.FAIL, m);

		}

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

		String suiteName = suite.getXmlSuite().getName();
		if (!suiteName.contains("API")) {
			if (System.getProperty("os.name").contains("Windows")) {
				extent = ExtentManager
						.createInstance(System.getProperty("user.dir") + "\\extentReport\\resultsUI.html");

			} else {
				extent = ExtentManager.createInstance(System.getProperty("user.dir") + "/extentReport/resultsUI.html");
			}
		} else {
			if (System.getProperty("os.name").contains("Windows")) {
				extent = ExtentManager
						.createInstance(System.getProperty("user.dir") + "\\extentReport\\resultsUI.html");

			} else {

				extent = ExtentManager.createInstance(System.getProperty("user.dir") + "/extentReport/resultsAPI.html");

			}
		}

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

	}

}
