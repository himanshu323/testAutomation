package com.testautomation.basemanager;

import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.testautomation.commonutils.ExcelUtils;
import com.testautomation.pages.AuthPage;
import com.testautomation.pages.HomePage;
import com.testautomation.pages.MyAccountPage;
import com.testautomation.pages.OrderConfirmationPage;
import com.testautomation.pages.ProcessOrderPage;
import com.testautomation.pages.RegistrationPage;

public class BaseClass extends DriverManager {

	public static Properties prop;

	public static WebDriver driver;
	public static WebDriverWait wait;

	protected HomePage homepage;

	protected AuthPage authpage;

	protected RegistrationPage registrationPage;

	protected MyAccountPage myAccountPage;

	protected ProcessOrderPage processOrderPage;

	protected OrderConfirmationPage orderConfirmationPage;

	@BeforeTest
	@Parameters({ "browser", "shoppingPortalURL" })

	public void setupTest(@Optional String browser, @Optional String shoppingPortalURL) throws MalformedURLException {

		switch (browser.toLowerCase()) {

		case "chrome":
			if (System.getProperty("os.name").contains("Windows")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");

			} else {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");

			}

			driver = new ChromeDriver();
			setDriver(driver);

			break;

		case "firefox":

			if (System.getProperty("os.name").contains("Windows")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");

			} else {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
			}
			driver = new FirefoxDriver();

			setDriver(driver);

			break;

		case "ie":

			if (System.getProperty("os.name").contains("Windows")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\IEDriverServer.exe");

				driver = new InternetExplorerDriver();

				setDriver(driver);

			}

			break;
		default:
			break;
		}

		getDriver().manage().window().maximize();

		wait = new WebDriverWait(getDriver(), 10, 50);

		getDriver().get(shoppingPortalURL);

	}

	@DataProvider(name = "ShoppingPortal")

	public static Object[][] Authentication() throws Exception {

		// String environment= System.getProperty("environment");

		Object[][] testObjArray;
		if (System.getProperty("os.name").contains("Windows")) {
			testObjArray = ExcelUtils.getData(
					System.getProperty("user.dir") + "\\src\\test\\resources\\shoppingPortalTestData.xlsx",
					"shoppingPortalQA");
		} else {
			testObjArray = ExcelUtils.getData(
					System.getProperty("user.dir") + "/src/test/resources/shoppingPortalTestData.xlsx",
					"shoppingPortalQA");
		}

		return (testObjArray);

	}

	@AfterTest
	public void CloseBrowser() {

		getDriver().quit();
	}
}
