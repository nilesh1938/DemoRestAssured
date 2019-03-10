package restAssuredFramework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static JsonPath rawToJson(Response response)
	{
		String responseString = response.asString();
		//System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		return js;
	}

}
