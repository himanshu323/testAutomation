package com.testautomation.ui;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.testautomation.basemanager.BaseClass;
import com.testautomation.commonutils.CommonUtils;
import com.testautomation.pages.AuthPage;
import com.testautomation.pages.HomePage;
import com.testautomation.pages.MyAccountPage;
import com.testautomation.pages.RegistrationPage;

public class ShoppingPortalRegistrationTest extends BaseClass {

	HomePage homepage;

	AuthPage authpage;

	RegistrationPage registrationPage;

	MyAccountPage myAccountPage;

	@Test()
	public void registrationForShoppingPortal() throws InterruptedException, IOException {

		CommonUtils.logInfo("Launching Shopping Portal");

		homepage = PageFactory.initElements(getDriver(), HomePage.class);

		homepage.clickSignIn();

		authpage = PageFactory.initElements(getDriver(), AuthPage.class);

		authpage.enterEmailAddress();

		authpage.clickSubmit();

		registrationPage = PageFactory.initElements(getDriver(), RegistrationPage.class);

		registrationPage.enterCustomerRegistrationDetails();

		myAccountPage = PageFactory.initElements(getDriver(), MyAccountPage.class);

		HashMap<String, String> customerData = CommonUtils.customerData;

		myAccountPage.verifyLoginState(customerData.get("firstname"), customerData.get("lastname"));

	}

	@AfterTest
	public void clickLogout() {

		homepage.clickLogout();
	}

}
