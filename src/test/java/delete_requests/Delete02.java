package delete_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class Delete02 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/{bookingId}
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is "Created"
     */
    @Test
    public void delete02(){
        spec.pathParams("first","booking","second",23797);

        String expectedData = "Created";

        Response response = given().spec(spec).contentType(ContentType.JSON).headers("Cookie","token=a7f6d86db6af0a4").when().delete("/{first}/{second}");
        response.prettyPrint();


        assertEquals(201,response.statusCode());
        assertEquals(expectedData,response.asString());



    }










//    @Test
//    public void delete02(){
//        spec.pathParams("first","booking","second",3254);
//
//        //set the expected data
//        String expectedData = "Created";
//
//        //send the request and get the response
//       Response response = given().spec(spec).headers("Cookie","token="+ generateToken()).
//                contentType(ContentType.JSON).when().delete("/{first}/{second}");
//       response.prettyPrint();
//     assertEquals(201,response.statusCode());
//     assertEquals(expectedData,response.asString()) ;
//
//    }
}
