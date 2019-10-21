# Test Automation using Selenium with Java for UI and Rest Assured with Java for API

## Steps to run automated tests

* Download Java JDK 1.8 or higher.
* Setup the environment variable **JAVA_HOME** and configure the same under the **Path** variable
* Download Apache Maven and configure **MAVEN_HOME** environment variable for the same and add it into existing “Path” environment variable 
* Install Eclipse IDE any other IDE of your choice.
* Clone/Download the project and import the  maven project in Eclipse IDE workspace. 
  `https://github.com/himanshu323/web-test.git`
* Update the maven project , just to make ensure that maven has downloaded all the necessary dependencies in your local repository from maven central.( `web-test -> Right Click -> Maven -> Update Project`)
* Open command prompt and navigate to project directory, run `mvn clean test` to start the automation execution OR right click on the project in your IDE->Run As->Maven Test.
* Test Run will contain both UI tests and API tests. `Both testngUI.xml and testngAPI.xml files are executed once you run 'mvn clean test'` .
* After the test run finishes, Extent report can be accessed from location **“extentreport/resultsUI.html”**  for UI Tests and **“extentreport/resultsAPI.html”** for API Tests `Overall Test Run will contain 4 Passes in resultsUI.html for UI tests and [3 passes,1 failure] for API tests in resultsAPI.html `. [ Test for Post country is expected to fail since this API doesn't exist yet]


## Features Included in the Framework

* **TestNG** is used as the testing framework/runner
* **Apache POI** is used for reading the data from excel for UI tests `[ src/test/resources/shoppingPortalTestData.xlsx- contains the UI test data]`
* **Maven** is used as the build tool for downloading all the necessary dependencies
* TestNG assertions for verifying the test results
* **Page Factory Model** is used as the design pattern
* Support for `Cross Browser Testing` as well as `Cross-platform`
* Added `Logging` for tests using Extent Report Logger.
* **Extent Report** is used for reporting and also added screenshot capture functionality within extent report in case of failures
* Support for **`Parallel`** testing added w.r.t Shopping Portal UI Tests using ThreadLocal class.

## Note for API Tests automated

`[ src/test/resources/apiconfig.properties- contains the API test data]`

1.  GetAllCountriesTest- It gets all the countries using `https://restcountries.eu/rest/v2/all`
2.  GetCountryByAlphaCodeTest- It gets all the countries using Alpha2Code
3.  GetCountryByNameWithInvalidData- This test is provided with invalid data to test error **404** response.
4.  PostCountryTest- This API doesn't exist yet and hence sample request/response is used for reference to build the test case [ this test is expected to fail ].

## Note for UI Tests automated

* Click *Sign in* button
* Fill *Email address* to create an account
* Click *Create an account* 
* Fill all fields with correct data 
* Click *Register* button
#### Assertions
* My account page(?controller=my-account) is opened
* Proper username is shown in the header
* Log out action is available

## Log in
#### Preconditions
* Existing customer
#### Test steps
* Open [Home page](http://automationpractice.com/index.php)
* Click *Sign in* button (in the header)
* Fill *Email address* in _Already registered_ block
* Fill *Password* in _Already registered_ block
* Click *Sign in* 
#### Assertions
* My account page(?controller=my-account) is opened
* Proper username is shown in the header
* Log out action is available

## Checkout
#### Preconditions
* Existing customer
* Order details
#### Test steps
* Log in as existing customer
* Click *Women* button in the header
* Click the product with name "Faded Short Sleeve T-shirts"
* Click *Add to card*
* Click *Proceed to checkout*
* Click *Proceed to checkout*
* Click *Proceed to checkout*
* Click by *Terms of service* to agree
* Click *Proceed to checkout*
* Click by payment method *Pay by bank wire*
* Click *I confirm my order*
#### Assertions
* Order confirmation page(?controller=order-confirmation) is opened
* The order is complete.
* Current page is the last step of ordering 



## Screenshots


#### Extent Report UI Tests Snapshot - I:
![ScreenShot](https://github.com/himanshu323/web-test/blob/master/images/UITests.png?raw=true)


#### Extent Report API Tests Snapshot - II:
![ScreenShot](https://github.com/himanshu323/web-test/blob/master/images/APITests.png?raw=true)

