package com.testautomation.basemanager;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	public static final ThreadLocal<WebDriver> driveThread=new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {
		return driveThread.get();
	}
	
	public static void setDriver(WebDriver driver) {
		driveThread.set(driver);
	}
}
