package homework;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetHW4 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */
    @Test
    public void getHW4(){
        spec.pathParams("first","booking").queryParams("firstname","Brandon","lastname","Wilson");

      Response response =  given().spec(spec).when().get("/{first}");
      response.prettyPrint();

      assertEquals(200,response.statusCode());
      assertTrue(response.asString().contains("bookingid"));
    }

}
