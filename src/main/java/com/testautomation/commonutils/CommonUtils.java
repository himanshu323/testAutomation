package com.testautomation.commonutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testautomation.basemanager.BaseClass;
import com.testautomation.basemanager.ExtentListeners;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;

public class CommonUtils extends BaseClass {

	public static HashMap<String, String> customerData = new HashMap<String, String>();;

	static String fileNameValue;

	public static void verifyEquals(String actualData, String expectedData, String elementLabel) throws IOException {
		try {

			Assert.assertEquals(actualData, expectedData, elementLabel + " Verification Failed");

			ExtentListeners.testReport.get().pass(elementLabel + " Verification Passed");
		}

		catch (Error e) {

			ExtentListeners.testReport.get().fail(elementLabel + " Verification Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(capture()).build());

			Assert.fail(elementLabel + " Verification Failed");
		}

	}

	public static void verifyEquals(String actualData, String expectedData, String elementLabel, boolean disableCapture)
			throws IOException {
		try {

			Assert.assertEquals(actualData, expectedData, elementLabel + " Verification Failed");

			ExtentListeners.testReport.get().pass(elementLabel + " Verification Passed");
		}

		catch (Error e) {

			ExtentListeners.testReport.get().fail(elementLabel + " Verification Failed");

			Assert.fail(elementLabel + " Verification Failed");
		}

	}

	public static void verifyEquals(int actualData, int expectedData, String elementLabel) throws IOException {
		try {

			Assert.assertEquals(actualData, expectedData, elementLabel + " Verification Failed");

			ExtentListeners.testReport.get().pass(elementLabel + " Verification Passed");
		}

		catch (Error e) {

			ExtentListeners.testReport.get().fail(elementLabel + " Verification Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(capture()).build());

			Assert.fail(elementLabel + " Verification Failed");
		}

	}

	public static void verifyEquals(int actualData, int expectedData, String elementLabel, boolean disableCapture)
			throws IOException {
		try {

			Assert.assertEquals(actualData, expectedData, elementLabel + " Verification Failed");

			ExtentListeners.testReport.get().pass(elementLabel + " Verification Passed");
		}

		catch (Error e) {

			ExtentListeners.testReport.get().fail(elementLabel + " Verification Failed");

			Assert.fail(elementLabel + " Verification Failed");
		}

	}

	public static void verifyIsTrue(boolean condition, String elementLabel) throws IOException {
		try {

			Assert.assertTrue(condition, elementLabel + " Verification Failed");

			ExtentListeners.testReport.get().pass(elementLabel + " Verification Passed");
		}

		catch (Error e) {

			ExtentListeners.testReport.get().fail(elementLabel + " Verification Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(capture()).build());

			Assert.fail(elementLabel + " Verification Failed");
		}

	}

	public static String capture() throws IOException {

		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		File Dest = new File("reports/ErrImages/" + System.currentTimeMillis() + ".png");

		String errflpath = Dest.getAbsolutePath();

		FileUtils.copyFile(scrFile, Dest);

		return errflpath;

	}

	public static void logInfo(String message) {

		ExtentListeners.testReport.get().info(message);

	}

	public static boolean waitForJSandJQueryToLoad() {

		WebDriverWait wait = new WebDriverWait(getDriver(), 30);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public static void enableLogging(String fileName) {
		FileWriter fileWriter;
		// fileNameValue=fileName;
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				fileWriter = new FileWriter(System.getProperty("user.dir") + "\\logs\\" + fileName + ".json");

			} else {
				fileWriter = new FileWriter(System.getProperty("user.dir") + "/logs/" + fileName + ".json");

			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		PrintStream printStream = new PrintStream(new WriterOutputStream(fileWriter, "UTF-8"), true);
		RestAssured.config = null;
		RestAssured.config = RestAssured.config()
				.logConfig(LogConfig.logConfig().defaultStream(printStream).enablePrettyPrinting(true));

	}

	public static void writeLog(String fileName) throws IOException {

		File file;

		if (System.getProperty("os.name").contains("Windows")) {
			file = new File(System.getProperty("user.dir") + "\\logs\\" + fileName + ".json");

		} else {
			file = new File(System.getProperty("user.dir") + "/logs/" + fileName + ".json");

		}
		String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		ExtentListeners.testReport.get().info(MarkupHelper.createCodeBlock(content));

	}

	public static HashMap<String, String> generateCustomerRegistrationData() {

		customerData.put("firstname", RandomStringUtils.randomAlphabetic(6, 12));

		customerData.put("lastname", RandomStringUtils.randomAlphabetic(6, 12));

		customerData.put("password", RandomStringUtils.randomAlphanumeric(6, 15));

		customerData.put("dobDays", String.valueOf(ThreadLocalRandom.current().nextInt(1, 27)));

		customerData.put("dobMonth", String.valueOf(ThreadLocalRandom.current().nextInt(1, 13)));

		customerData.put("dobYear", String.valueOf(ThreadLocalRandom.current().nextInt(1980, 2001)));

		customerData.put("company", RandomStringUtils.randomAlphabetic(4, 8));

		customerData.put("address1", RandomStringUtils.randomAlphabetic(6, 20));

		customerData.put("address2", RandomStringUtils.randomAlphabetic(6, 20));

		customerData.put("city", RandomStringUtils.randomAlphabetic(6, 8));

		customerData.put("state", String.valueOf(ThreadLocalRandom.current().nextInt(1, 51)));

		customerData.put("postcode", RandomStringUtils.randomNumeric(5, 5));

		customerData.put("mobileNumber", RandomStringUtils.randomNumeric(10, 10));

		return customerData;

	}
}
