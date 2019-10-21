package com.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testautomation.basemanager.BaseClass;
import com.testautomation.commonutils.CommonUtils;

public class ProcessOrderPage {

	@FindBy(name = "Submit")
	public WebElement addToCartButton;

	@FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	public WebElement proceedToCheckoutButton;

	@FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	public WebElement proceedToCheckoutButton2;

	@FindBy(name = "processAddress")
	public WebElement confirmAddressAndProceedToCheckout;

	@FindBy(id = "uniform-cgv")
	public WebElement agreeToTermsAndCondCheckbox;

	@FindBy(name = "processCarrier")
	public WebElement confirmShippingOptionAndProceedToCheckout;

	@FindBy(className = "bankwire")
	public WebElement paymentByBankwireButton;

	@FindBy(xpath = "//*[@id='cart_navigation']/button")
	public WebElement confirmMyOrderButton;

	@FindBy(id = "quantity_wanted")
	public WebElement quantityField;

	@FindBy(id = "group_1")
	public WebElement sizeSelect;

	WebDriver driver;

	public ProcessOrderPage(WebDriver driver) {

		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void processOrder(String shoppingItem, String quantityValue, String sizeValue) {

		CommonUtils.logInfo("Order Processsing-->Proceed to checkout-->Address-->Shipping-->Payment");

		driver.findElement(By.xpath("//a[@title='" + shoppingItem + "']/ancestor::li")).click();

		driver.findElement(By.xpath("//a[@title='" + shoppingItem + "']/ancestor::li")).click();

		quantityField.clear();

		quantityField.sendKeys(quantityValue);

		Select select = new Select(sizeSelect);

		select.selectByVisibleText(sizeValue);

		WebDriverWait wait = BaseClass.wait;

		wait.until(ExpectedConditions.visibilityOf(addToCartButton)).click();
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton)).click();
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton2)).click();
		wait.until(ExpectedConditions.visibilityOf(confirmAddressAndProceedToCheckout)).click();
		wait.until(ExpectedConditions.visibilityOf(agreeToTermsAndCondCheckbox)).click();
		confirmShippingOptionAndProceedToCheckout.click();
		wait.until(ExpectedConditions.visibilityOf(paymentByBankwireButton)).click();
		wait.until(ExpectedConditions.visibilityOf(confirmMyOrderButton)).click();

	}

}
