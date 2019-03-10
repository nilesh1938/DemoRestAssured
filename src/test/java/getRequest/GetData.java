package getRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;



public class GetData {
	
	
	
	@Test
	public void testGetMethod()
	{
		
		RestAssured.baseURI="https://samples.openweathermap.org";
		
		
		given().
				param("q","London,uk").
				param("appid", "b6907d289e10d714a6e88b30761fae22").
		when().
				get("data/2.5/weather").
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("weather[0].main", equalTo("Drizzle")).and().header("Server", "openresty/1.9.7.1");
		/*Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		System.out.println("Response status is " +response.getStatusCode());
		System.out.println("Response header is " +response.headers());
		System.out.println("Response status line is " +response.getStatusLine());
		System.out.println("Response time  is " +response.getTime());
		System.out.println("Response is " +response.asString());
		Assert.assertEquals(response.getStatusCode(), 200); 
		
		//Grab the book id from the response
		//String responseString = res.asString();
		//System.out.println(responseString);
		//JsonPath js = new JsonPath(responseString);
		String placeID=ReusableMethods.rawToJson(res).get("ID");
		System.out.println("Place id " +placeID);
		*/
		
	}
	
	@Test
	public void testResponseCode()
	{
		Response response = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		System.out.println("Response status is " +response.getStatusCode());
		System.out.println("Response header is " +response.headers());
		System.out.println("Response status line is " +response.getStatusLine());
		System.out.println("Response time  is " +response.getTime());
		System.out.println("Response is " +response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
