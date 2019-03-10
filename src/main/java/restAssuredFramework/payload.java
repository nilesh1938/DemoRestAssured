package restAssuredFramework;

public class payload {
	
	public static String getPostData(String isbn, String aisle)
	{
		String postData="{" + 
				"\"name\":\"Learn Appium Automation with Java5895\"," + 
				"\"isbn\":\"" +isbn+ "\"," + 
				"\"aisle\":\""+aisle+"\"," + 
				"\"author\":\"John foe5895\"" + 
			 "}";
		return postData;
	}

}
