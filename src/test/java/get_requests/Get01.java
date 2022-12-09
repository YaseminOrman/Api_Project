package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1)postman used for manual API testing .
    2)we use Rest-Assured Library for API Automation Testing
    3)To type automation script follow the given steps:
       a)Understand the requirement
       b)Type test cases
         To type cases we use "Gherkin Language"
         The keywords are    1)Given :it is for pre-condition
                             2)When  :it is for actions
                             3)Then  :it is for output
                             4)And   :it is for multiple given ,when and then
      c)Start to type automation script
          1)Set the URL
          2)Set the expected data(post-put-patch)
          3)Type code to send the request
          4)Do assertion

     */

    /*
   Given
       https://restful-booker.herokuapp.com/booking/10
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01(){
    //set the url
        String url = "https://restful-booker.herokuapp.com/booking/10";

    //set the expected data


    //Send the request and get the response
   Response response = given().when().get(url);
   response.prettyPrint();

           //do assertion
     //http status code should be 200
     //Content Type should be JSON
     //status line should be HTTP/1.1 200 OK
     response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

     //How to print status code on the console
        System.out.println("Status Code :"+response.statusCode());

      //How to print content type on the console
        System.out.println("Content Type :"+response.contentType());

     //How to print status line on the console
        System.out.println("Status Line:"+response.statusLine());

     //How to print header on the console
        System.out.println(response.getHeader("Connection"));

        System.out.println("=========================");

        //How to print headers on the console
        System.out.println(response.getHeaders());

        //How to print time on the console
        System.out.println(response.getTime());

    }



}
