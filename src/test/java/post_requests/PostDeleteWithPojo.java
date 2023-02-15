package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class PostDeleteWithPojo extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url
            I send Delete Request to the Url
        Then
            response body is like { }
     */
    @Test
    public void postDelete(){
        spec.pathParam("first","todos");
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        //Get the id of newly created data
        JsonPath json=response.jsonPath();
        Integer id = json.getInt("id");

        spec.pathParams("first","todos","second",id);
        Response response1=given().spec(spec).when().delete("/{first}/{second}");
        Map<String,Object> actualData = response1.as(HashMap.class);
        assertTrue(actualData.size()==0);
    }

}
