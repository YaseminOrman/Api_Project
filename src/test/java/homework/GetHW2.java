package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetHW2 extends ReqresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */
    @Test
    public void getHW2(){
      spec.pathParams("first","users","second",23) ;

     Response response= given().spec(spec).when().get("/{first}/{second}");
     response.prettyPrint();

     response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
     assertEquals("cloudflare",response.getHeader("Server"));
     assertEquals(0,response.as(HashMap.class).size());
    }

}
