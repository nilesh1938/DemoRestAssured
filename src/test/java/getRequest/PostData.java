package getRequest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restAssuredFramework.ReusableMethods;
import restAssuredFramework.payload;
import restAssuredFramework.resource;

public class PostData {
	
	Properties prop;
	
	@BeforeTest
	public void getData() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\workspace\\restAssuredFramework\\src\\main\\resources\\file\\env.properties");
		prop.load(fis);
	}
	
	@Test(dataProvider="BooksData")
	public void testPostMethod(String isbn, String aisle)
	{
		RestAssured.baseURI=prop.getProperty("host");
		
		// Create a book response(book id)
		Response res = 
		given().
				body(payload.getPostData(isbn, aisle)).
		when().
				post(resource.placePostData()).
				
		then().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).
				and().body("Msg",equalTo("successfully added")).
		
		extract().
				response();
		
		
		//Grab the book id from the response
		//String responseString = res.asString();
		//System.out.println(responseString);
		//JsonPath js = new JsonPath(responseString);
		String placeID=ReusableMethods.rawToJson(res).get("ID");
		System.out.println("Place id " +placeID);
		
		// delete the Place id which we got from the response
		
		given().
		body("{" +
				"\"ID\" : \"" +placeID+ "\""+
			 "}").
		when().
		post("/Library/DeleteBook.php").
		then().//assertThat().statusCode(200).and().
		body("msg", equalTo("book is successfully deleted")); 
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getPayloadData()
	{
		return new Object[][] {{"1099","2099"},{"3099","4099"},{"5099","6099"}};
	}

}
