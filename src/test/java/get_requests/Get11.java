package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Bhaaswar Achari", "Abhirath Kocchar", "Sher Dutta" are among the users
        And
            The female users are less than or equal to male users
     */
    @Test
    public void get11(){
        spec.pathParam("first","users");

      Response response=  given().spec(spec).when().get("/{first}");
      response.prettyPrint();

      response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(10),
              "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),"data.name",hasSize(10),
              "data.status",hasItem("active"),"data.name",hasItems("Bheeshma Bhattathiri", "Bhadrak Tandon", "Pres. Laal Mukhopadhyay"));

     JsonPath jsonPath= response.jsonPath();
      List<String> genders  =  jsonPath.getList("data.gender");
        System.out.println("genders = " + genders);

       int numOfFemale=0;
       for(String w: genders){
           if(w.equals("female")){
               numOfFemale++;
           }
       }
        System.out.println("numOfFemale = " + numOfFemale);
       int numOfMales = genders.size()-numOfFemale;
       assertTrue(numOfFemale<=numOfMales);




    }






















//    @Test
//    public void get11(){
//        //set the url
//        spec.pathParam("first","users");
//        //set the expected data
//
//        //Send the request and get the response
//      Response response =  given().spec(spec).when().get("/{first}");
//      response.prettyPrint();
//
//      //do assertion
//        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(10),
//              "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1") ,
//                "data.name",hasSize(10),"data.status",hasItem("active"),"data.name",hasItems("Pres. Soma Adiga", "Ms. Anjushree Shah", "The Hon. Triloki Nath Verma"));
//
//    //The female users are less than or equal to male users
//     //I will compare number of female and male users
//     //1.way:I will get all gender in a list then calculate the number of females then compare it with the list
//
//       JsonPath jsonPath = response.jsonPath();
//      List<String> genders = jsonPath.getList("data.gender");
//        System.out.println(genders);
//
//      int numOfFemales = 0;
//      for(String w: genders){
//        if(w.equals("female")){
//            numOfFemales++;
//        }
//      }
//        System.out.println("numOfFemales =" + numOfFemales);
//        int numOfMales = genders.size()-numOfFemales;
//        assertTrue(numOfFemales<=numOfMales);
//
//        //2nd way: I will get all females by using Groovy Language then compare it with males
//        List<String> femaleList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
//        System.out.println("femaleList = " + femaleList);
//
//        List<String> maleList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
//        System.out.println("maleList = " + maleList);
//
//        assertTrue(femaleList.size()<=maleList.size());
//
//
//
//    }
}
