package restAPI;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testautomation.basemanager.ExtentListeners;
import com.testautomation.commonutils.CommonUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class GetAPI {

	public static Response getCountryByAlphaCode(RequestSpecification reqSpec, ResponseSpecification respSpec)
			throws IOException {

		ExtentListeners.testReport.get().info(MarkupHelper.createLabel("Request:", ExtentColor.CYAN));

		CommonUtils.enableLogging("GetCountryByAlphaCode");

		Response rsp = given().log().all().spec(reqSpec).when().get(ResourcesAPI.getCountryByAlphaCode()).then()
				.spec(respSpec).extract().response();
		;

		CommonUtils.writeLog("GetCountryByAlphaCode");

		ExtentListeners.testReport.get().info(MarkupHelper.createLabel("Response:", ExtentColor.CYAN));

		ExtentListeners.testReport.get().info(MarkupHelper.createCodeBlock(rsp.asString()));

		return rsp;

	}

	public static Response getAllCountriesAPIResponse(ResponseSpecification respSpec) throws IOException {

		ExtentListeners.testReport.get().info(MarkupHelper.createLabel("Request:", ExtentColor.CYAN));
		CommonUtils.enableLogging("GetAllCountries");

		Response rsp = given().log().all().when().get(ResourcesAPI.getAllCountries()).then().spec(respSpec).extract()
				.response();

		CommonUtils.writeLog("GetAllCountries");

		ExtentListeners.testReport.get().info(MarkupHelper.createLabel("Response:", ExtentColor.CYAN));

		ExtentListeners.testReport.get().info(MarkupHelper.createCodeBlock(rsp.asString()));

		return rsp;

	}

	public static Response getCountryByNameWithInvalidData(ResponseSpecification respSpec) throws IOException {

		ExtentListeners.testReport.get().info(MarkupHelper.createLabel("Request:", ExtentColor.CYAN));

		CommonUtils.enableLogging("GetCountryByNameWithInvalidData");

		Response rsp = given().log().all().when().get(ResourcesAPI.getCountryByInvalidName()).then().spec(respSpec)
				.extract().response();
		;

		CommonUtils.writeLog("GetCountryByNameWithInvalidData");

		ExtentListeners.testReport.get().info(MarkupHelper.createLabel("Response:", ExtentColor.CYAN));

		ExtentListeners.testReport.get().info(MarkupHelper.createCodeBlock(rsp.asString()));
		return rsp;
	}
}