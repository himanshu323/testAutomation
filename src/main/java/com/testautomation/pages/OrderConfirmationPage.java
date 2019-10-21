package com.testautomation.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testautomation.basemanager.BaseClass;
import com.testautomation.commonutils.CommonUtils;

public class OrderConfirmationPage {

	@FindBy(css = "h1")
	public WebElement orderConfirmationHeader;

	@FindBy(xpath = "//li[@class='step_done step_done_last four']")
	public WebElement shippingLabel;

	@FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
	public WebElement paymentLabel;

	@FindBy(xpath = "//*[@class='cheque-indent']/strong")
	public WebElement orderConfirmationLabel;

	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;

	}

	public void verifyOrderConfirmation() throws IOException {

		CommonUtils.logInfo("Verify Order Confirmation Details");

		WebElement heading = BaseClass.wait.until(ExpectedConditions.visibilityOf(orderConfirmationHeader));

		CommonUtils.verifyEquals(heading.getText(), "ORDER CONFIRMATION", "ORDER CONFIRMATION Header");

		CommonUtils.verifyIsTrue(shippingLabel.isDisplayed(), "Shipping Label");

		CommonUtils.verifyIsTrue(paymentLabel.isDisplayed(), "Payment Label");

		CommonUtils.verifyIsTrue(orderConfirmationLabel.getText().contains("Your order on My Store is complete."),
				"Order Confirmation Label");

		CommonUtils.verifyIsTrue(driver.getCurrentUrl().contains("controller=order-confirmation"),
				"Current Window URL");

	}

}
