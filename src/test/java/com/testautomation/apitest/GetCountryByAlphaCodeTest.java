package com.testautomation.apitest;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import restAPI.APIBaseClass;
import restAPI.APIValidations;
import restAPI.DataConverter;
import restAPI.GetAPI;

public class GetCountryByAlphaCodeTest extends APIBaseClass {

	RequestSpecification reqSpec;

	ResponseSpecification respSpec;

	Response getResponse;

	JsonPath getDocJSONPath;

	@Test()
	public void getCountryByAlphaCodeAndValidateResponse() throws IOException, InterruptedException {

		APIValidations.intialize(prop.getProperty("getCountriesBaseURI"), prop.getProperty("getCountriesBasePath"));

		reqSpec = APIValidations.setRequestSpecAlphaCode("US", "DE", "GB");

		respSpec = APIValidations.setResponseValidations();

		getResponse = GetAPI.getCountryByAlphaCode(reqSpec, respSpec);

		getDocJSONPath = DataConverter.rawToJson(getResponse);

		APIValidations.validateCountriesResponse(getDocJSONPath);

	}
}
