package restAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class DataConverter {

	public static JsonPath rawToJson(Response response) {

		String text = response.asString();
		JsonPath js = new JsonPath(text);

		return js;

	}

	public static XmlPath rawToXml(Response response) {

		String text = response.asString();
		XmlPath xm = new XmlPath(text);

		return xm;

	}
}
