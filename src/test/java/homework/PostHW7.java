package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqResTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostHW7 extends ReqresBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void posHW7(){
        spec.pathParam("first","users");

        //set the expected data
        ReqResTestData obj = new ReqResTestData();
       Map<String,String>expectedData= obj.expectedDataSetUp("morpheus","leader");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
       response.prettyPrint();

       //do assertion
       Map<String,String> actualData= response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
       assertEquals(expectedData.get("name"),actualData.get("name"));
       assertEquals(expectedData.get("job"),actualData.get("job"));


    }

}
