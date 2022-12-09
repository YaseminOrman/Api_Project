package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/32
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is “application/json”
       And
           Response body should be like;
               {
               "firstname": "Mark",
               "lastname": "Jackson",
               "totalprice": 554,
               "depositpaid": false,
               "bookingdates": {
                   "checkin": "2017-09-11",
                   "checkout": "2021-03-14"
               },
               "additionalneeds": "Breakfast"
               }
    */
    @Test
    public void get06() {
        //Set the url
        spec.pathParams("first", "booking", "second", 2);
        //set the expected data

     Response response =   given().spec(spec).when().get("/{first}/{second}");
     response.prettyPrint();

     //Do assertion
        //1.way
     response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
             body("firstname",equalTo("Susan"),
                     "lastname",equalTo("Jones"),
                              "totalprice",equalTo(696),
                              "depositpaid",equalTo(true),
                     "bookingdates.checkin",equalTo("2021-07-11"),
                     "bookingdates.checkout",equalTo("2022-05-01"),
                     "additionalneeds",equalTo("Breakfast"));


        //2nd Way: We will use JsonPath Class
        JsonPath jsonPath = response.jsonPath();

        //Hard Assertion
        assertEquals("Mark",jsonPath.getString("firstname"));
        assertEquals("Jackson",jsonPath.getString("lastname"));
        assertEquals(554,jsonPath.getInt("totalprice"));
        assertEquals(false,jsonPath.getBoolean("depositpaid"));
        assertEquals("2017-09-11",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2021-03-14",jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast",jsonPath.getString("additionalneeds"));

        //How to do Soft Assertion:
        //1: Create Soft Assertion Object
        SoftAssert softAssert=new SoftAssert();

        //2: Do Assertion
        softAssert.assertEquals(jsonPath.getString("firstname"),"Mark","firstname did not match!");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Jackson","lastname did not match!");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),554,"totalprice did not match!");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"),false,"depositpaid did not match!");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2017-09-11","checkin did not match!");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2021-03-14","checkout did not match!");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds did not match!");

        //3: Use assertAll() method.(Otherwise soft assertion will pass in every scenario)
        softAssert.assertAll();


    }
}













