package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.basemanager.BaseClass;
import com.testautomation.commonutils.CommonUtils;

public class HomePage {

	@FindBy(className = "login")
	public WebElement signInButton;

	@FindBy(className = "logout")
	public WebElement logOutButton;

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	public void clickSignIn() {

		CommonUtils.logInfo("Click Sign In");

		BaseClass.wait.until(ExpectedConditions.visibilityOf(signInButton)).click();

	}

	public void clickLogout() {

		CommonUtils.logInfo("Click Logout");

		logOutButton.click();
	}

}
