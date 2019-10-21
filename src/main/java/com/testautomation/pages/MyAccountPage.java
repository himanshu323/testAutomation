package com.testautomation.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.basemanager.BaseClass;
import com.testautomation.commonutils.CommonUtils;

public class MyAccountPage {

	@FindBy(css = "h1")
	public WebElement myAccountHeader;

	@FindBy(className = "account")
	public WebElement accountNameLabel;

	@FindBy(className = "info-account")
	public WebElement accountInfo;

	@FindBy(className = "logout")
	public WebElement logOutField;

	@FindBy(linkText = "Women")
	public WebElement womenLabel;

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;

	}

	public void verifyLoginState(String firstName, String lastName) throws IOException {

		CommonUtils.logInfo("Verify if the user is successfully logged into");

		WebElement heading = BaseClass.wait.until(ExpectedConditions.visibilityOf(myAccountHeader));

		CommonUtils.verifyEquals(heading.getText(), "MY ACCOUNT", "My Account Header");

		CommonUtils.verifyEquals(accountNameLabel.getText(), firstName + " " + lastName, "Account Name Label");

		CommonUtils.verifyIsTrue(accountInfo.getText().contains("Welcome to your account."), "Account Info Text");

		CommonUtils.verifyIsTrue(logOutField.isDisplayed(), "Logout Button");

		CommonUtils.verifyIsTrue(driver.getCurrentUrl().contains("controller=my-account"), "My Account Window URL");

	}

	public void clickWomenLabel() {

		CommonUtils.logInfo("Click Women Label");

		BaseClass.wait.until(ExpectedConditions.visibilityOf(womenLabel)).click();

	}

}
