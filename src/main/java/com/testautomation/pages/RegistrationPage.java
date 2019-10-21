package com.testautomation.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.testautomation.basemanager.BaseClass;
import com.testautomation.commonutils.CommonUtils;

public class RegistrationPage {

	@FindBy(id = "id_gender2")
	public WebElement salutationButton;

	@FindBy(id = "customer_firstname")
	public WebElement firstNameField;

	@FindBy(id = "customer_lastname")
	public WebElement lastNameField;

	@FindBy(id = "passwd")
	public WebElement passwordField;

	@FindBy(id = "days")
	public WebElement dobDaysSelect;

	@FindBy(id = "months")
	public WebElement dobMonthsSelect;

	@FindBy(id = "years")
	public WebElement dobYearsSelect;

	@FindBy(id = "company")
	public WebElement companyField;

	@FindBy(id = "address1")
	public WebElement address1Field;

	@FindBy(id = "address2")
	public WebElement address2Field;

	@FindBy(id = "city")
	public WebElement cityField;

	@FindBy(id = "id_state")
	public WebElement stateSelect;

	@FindBy(id = "postcode")
	public WebElement postCodeField;

	@FindBy(id = "other")
	public WebElement othersField;

	@FindBy(id = "phone_mobile")
	public WebElement mobileNumberField;

	@FindBy(id = "email")
	public WebElement emailField;

	@FindBy(id = "alias")
	public WebElement aliasField;

	@FindBy(id = "submitAccount")
	public WebElement registerButton;

	WebDriver driver;

	public String amountToBeConverted = null;

	public String currencyExchangeRate = null;

	public String toCurrencyValue = null;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;

	}

	public void enterCustomerRegistrationDetails() {

		HashMap<String, String> customerData = CommonUtils.generateCustomerRegistrationData();

		CommonUtils.logInfo("Enter customer registration details");
		BaseClass.wait.until(ExpectedConditions.visibilityOf(salutationButton)).click();

		firstNameField.sendKeys(customerData.get("firstname"));
		lastNameField.sendKeys(customerData.get("lastname"));
		passwordField.sendKeys(customerData.get("password"));

		Select select = new Select(dobDaysSelect);
		select.selectByValue(customerData.get("dobDays"));

		select = new Select(dobMonthsSelect);
		select.selectByValue(customerData.get("dobMonth"));

		select = new Select(dobYearsSelect);
		select.selectByValue(customerData.get("dobYear"));

		companyField.sendKeys(customerData.get("company"));
		address1Field.sendKeys(customerData.get("address1"));
		address2Field.sendKeys(customerData.get("address2"));
		cityField.sendKeys(customerData.get("city"));

		select = new Select(stateSelect);
		select.selectByValue(customerData.get("state"));

		postCodeField.sendKeys(customerData.get("postcode"));
		othersField.sendKeys("Qwerty");
		mobileNumberField.sendKeys(customerData.get("mobileNumber"));
		aliasField.sendKeys("hf");

		registerButton.click();

	}

}
