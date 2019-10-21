package com.testautomation.apitest;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import restAPI.APIBaseClass;
import restAPI.APIValidations;
import restAPI.PostAPI;
import restAPI.PostCountryObj;

public class PostCountryTest extends APIBaseClass {

	ResponseSpecification respSpec;

	Response postResponse;

	PostCountryObj postCountryObj;

	@Test()
	public void postCountry() throws IOException {

		APIValidations.intialize(prop.getProperty("getCountriesBaseURI"), prop.getProperty("getCountriesBasePath"));

		HashMap<String, String> countryPostValues = new HashMap<String, String>();

		countryPostValues.put("name", prop.getProperty("postCountryName"));

		countryPostValues.put("alpha2_code", prop.getProperty("postCountryAlpha2_Code"));

		countryPostValues.put("alpha3_code", prop.getProperty("postCountryAlpha3_Code"));

		postCountryObj = APIValidations.buildPostBodyForCountry(countryPostValues);

		respSpec = APIValidations.setResponseValidations();

		postResponse = PostAPI.postCountry(postCountryObj, respSpec);

	}

}
