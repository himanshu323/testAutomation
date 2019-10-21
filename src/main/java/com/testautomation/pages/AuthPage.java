package com.testautomation.pages;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testautomation.commonutils.CommonUtils;

public class AuthPage {

	@FindBy(id = "email_create")
	public WebElement emailField;

	@FindBy(id = "SubmitCreate")
	public WebElement submitButton;

	@FindBy(id = "email")
	public WebElement existingEmailField;

	@FindBy(id = "passwd")
	public WebElement existingPasswordField;

	@FindBy(id = "SubmitLogin")
	public WebElement loginButton;

	WebDriver driver;

	public AuthPage(WebDriver driver) {

		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void enterEmailAddress() {

		CommonUtils.logInfo("Enter Email Address required for Signup");
		String timestamp = String.valueOf(new Date().getTime());
		String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";

		CommonUtils.customerData.put("email", email);

		emailField.sendKeys(email);

	}

	public void clickSubmit() {
		CommonUtils.logInfo("Click Create Account");
		submitButton.click();
	}

	public void loginToShoppingPortal(String email, String password) {

		CommonUtils.logInfo("Login into the shopping portal with Email : " + email);

		existingEmailField.sendKeys(email);

		existingPasswordField.sendKeys(password);

		loginButton.click();
	}

}
