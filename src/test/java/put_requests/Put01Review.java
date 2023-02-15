package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Put01Review extends JsonPlaceHolderBaseUrl {
      /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void put(){
        spec.pathParams("first","todos","second",198);

        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String,Object > requestBodyMap = requestBody.expectedDataJPH(21,"Wash the dishes",false);

        Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        Map<String, Object> actualData=response.as(HashMap.class);

        assertEquals(requestBodyMap.get("userId"), actualData.get("userId"));
        assertEquals(requestBodyMap.get("title"), actualData.get("title"));
        assertEquals(requestBodyMap.get("completed"), actualData.get("completed"));

        //How to use GSON in Serialization : Java object to ==> Json Data
        Map<String ,Integer> ages = new HashMap<>();
        ages.put("Ali Han",13);
        ages.put("Veli Han",16);
        ages.put("Ayse Kan",23);
        ages.put("Marry Star",60);

        Gson gson = new Gson();
        String jsonFromMap=gson.toJson(ages);
        System.out.println("jsonFromMap = " + jsonFromMap);
    }

}
