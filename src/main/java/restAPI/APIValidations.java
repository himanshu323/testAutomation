package restAPI;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasItems;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.testautomation.commonutils.CommonUtils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIValidations {

	/**
	 * @author harora
	 */

	public static RequestSpecBuilder reqBuilder;
	public static RequestSpecification reqSpec;
	public static ResponseSpecBuilder respBuilder;
	public static ResponseSpecification respSpec;

	public static void intialize(String baseURIValue, String basePath) {

		RestAssured.baseURI = baseURIValue;
		RestAssured.basePath = basePath;
	}

	public static RequestSpecification setRequestSpecAlphaCode(String... alpha2Code) {
		reqBuilder = new RequestSpecBuilder();
		String f = "";
		for (String code : alpha2Code) {
			f = f + code + ";";
		}
		reqBuilder.addQueryParam("codes", f);

		reqSpec = reqBuilder.build();
		return reqSpec;
	}

	public static RequestSpecification setRequestSpecUserId(String userId) {
		reqBuilder = new RequestSpecBuilder();
		reqBuilder.addParam("userId", userId);
		reqSpec = reqBuilder.build();
		return reqSpec;

	}

	public static ResponseSpecification setResponseValidations() {
		respBuilder = new ResponseSpecBuilder();
		respBuilder.expectHeader("Server", "cloudflare");
		respBuilder.expectHeader("Content-Type", "application/json;charset=utf-8");

		respBuilder.expectStatusCode(200);

		respSpec = respBuilder.build();
		return respSpec;

	}

	public static ResponseSpecification setErrorResponseValidations() {

		respBuilder = new ResponseSpecBuilder();
		respBuilder.expectHeader("Server", "cloudflare");
		respBuilder.expectHeader("Content-Type", "application/json;charset=utf-8");

		respBuilder.expectStatusCode(404);
		respBuilder.expectBody("status", equalTo(404));

		respBuilder.expectBody("message", equalToIgnoringCase("Not Found"));

		respSpec = respBuilder.build();
		return respSpec;

	}

	public static ResponseSpecification setResponseValidationForCountries(String... countries) {

		respBuilder = new ResponseSpecBuilder();
		respBuilder.expectHeader("Server", "cloudflare");
		respBuilder.expectHeader("Content-Type", "application/json;charset=utf-8");

		respBuilder.expectStatusCode(200);
		for (String c : countries) {
			respBuilder.expectBody("alpha2Code", hasItems(c));
		}

		respSpec = respBuilder.build();
		return respSpec;

	}

	public static PostCountryObj buildPostBodyForCountry(HashMap<String, String> countryPostValues) {

		PostCountryObj postBodyObj = new PostCountryObj();

		postBodyObj.setName(countryPostValues.get("name"));

		postBodyObj.setAlpha2_code(countryPostValues.get("alpha2_code"));

		postBodyObj.setAlpha3_code(countryPostValues.get("alpha3_code"));

		return postBodyObj;

	}

	public static void validateCountriesResponse(JsonPath js) throws IOException {

		List<String> countryUS = js.get(
				"findAll{it.alpha2Code=='US' &&  it.capital=='Washington, D.C.' && it.numericCode=='840' && it.name=='United States of America'}");

		CommonUtils.verifyEquals(countryUS.size(), 1, "Country USA Validation Passed", true);

		List<String> countryDE = js.get(
				"findAll{it.alpha2Code=='DE' &&  it.capital=='Berlin' && it.numericCode=='276' && it.name=='Germany'}");

		CommonUtils.verifyEquals(countryDE.size(), 1, "Country DE Validation Passed", true);

		List<String> countryGB = js.get(
				"findAll{it.alpha2Code=='GB' &&  it.capital=='London' && it.numericCode=='826' && it.name=='United Kingdom of Great Britain and Northern Ireland'}");

		CommonUtils.verifyEquals(countryGB.size(), 1, "Country GB Validation Passed", true);

	}

	public static List<String> fetchSourceValueForValueStatement(JsonPath js) {

		List<String> sourceValues = js.get("sourceOfValue");

		return sourceValues;

	}
}