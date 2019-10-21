package restAPI;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.testautomation.commonutils.CommonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class PostAPI {

	public static Response postCountry(PostCountryObj postCountryObj, ResponseSpecification respSpec) throws IOException {

		CommonUtils.enableLogging("PostCountry");
		
		Response rsp = given().contentType(ContentType.JSON).log().all().when().body(postCountryObj)
				.post(ResourcesAPI.postCountry()).then().spec(respSpec).log().all().extract().response();
		;
		
		CommonUtils.writeLog("PostCountry");

		// System.out.println();

		return rsp;

	}
}
