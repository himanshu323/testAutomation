package com.testautomation.ui;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.testautomation.basemanager.BaseClass;
import com.testautomation.pages.AuthPage;
import com.testautomation.pages.HomePage;
import com.testautomation.pages.MyAccountPage;

public class ShoppingPortalLoginTest extends BaseClass {

	HomePage homepage;

	AuthPage authpage;

	MyAccountPage myAccountPage;

	@Test(dataProvider = "ShoppingPortal", dataProviderClass = BaseClass.class)
	public void loginInTest(String shoppingItem, String quantity, String size, String email, String password,
			String firstName, String lastName) throws InterruptedException, IOException {

		homepage = PageFactory.initElements(getDriver(), HomePage.class);
		homepage.clickSignIn();

		authpage = PageFactory.initElements(getDriver(), AuthPage.class);
		authpage.loginToShoppingPortal(email, password);

		myAccountPage = PageFactory.initElements(getDriver(), MyAccountPage.class);
		myAccountPage.verifyLoginState(firstName, lastName);

	}

	@AfterTest
	public void clickLogout() {

		homepage.clickLogout();
	}

}
