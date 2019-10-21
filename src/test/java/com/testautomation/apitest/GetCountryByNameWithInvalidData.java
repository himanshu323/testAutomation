package com.testautomation.apitest;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import restAPI.APIBaseClass;
import restAPI.APIValidations;
import restAPI.GetAPI;

public class GetCountryByNameWithInvalidData extends APIBaseClass {

	ResponseSpecification respSpec;

	Response getResponse;

	@Test()
	public void validateInvalidCountryResponse() throws IOException {

		APIValidations.intialize(prop.getProperty("getCountriesBaseURI"), prop.getProperty("getCountriesBasePath"));

		respSpec = APIValidations.setErrorResponseValidations();

		getResponse = GetAPI.getCountryByNameWithInvalidData(respSpec);

		
	}
}
