package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/3619
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                     "firstname": "john",
                     "lastname": "Brown",
                     "totalprice": 111,
                      "depositpaid": true,
                      "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                  },
                "additionalneeds": "Breakfast"
                 }
     */


    @Test
    public void get12(){
    //set the url
        spec.pathParams("first","booking","second",3619);

    //set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01") ;
        BookingPojo expectedData = new BookingPojo("john","Brown",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

    //Send the request and get the response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    //do assertion
     BookingPojo actualData =response.as(BookingPojo.class) ;
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
    }

}
