package homework;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetHW6 extends ReqresBaseUrl {
     /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
     @Test
    public void getHW6(){
         spec.pathParam("first","unknown");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        assertEquals(200,response.statusCode());

       JsonPath jsonPath = response.jsonPath();
          List<String >pantoneValues= jsonPath.getList("data.pantone_value");
        System.out.println("pantoneValues = " + pantoneValues);

        // List<String> pantoneValues = jsonPath.getList("data.findAll{it.pantone_value}.pantone_value");
        // System.out.println("pantoneValues = " + pantoneValues);

         //1.way
      List<Integer> ids= jsonPath.getList("data.id");
         System.out.println("ids = " + ids);
         List<Integer> idsGreaterThan3 = new ArrayList<>();
      for(Integer w :ids) {
          if(w>3){
              idsGreaterThan3.add(w);
          }
      }
         System.out.println("idsGreaterThan3 = " + idsGreaterThan3);
      assertEquals(3,idsGreaterThan3.size());

      //2.way
        List<Integer> idsWithGroovy= jsonPath.getList("data.findAll{it.id>3}.id");
         System.out.println("idsWithGroovy = " + idsWithGroovy);
       assertEquals(3,idsWithGroovy.size());

      List<String> namesIdLessThan3= jsonPath.getList("data.findAll{it.id<3}.name");
         System.out.println("names = " + namesIdLessThan3);
         assertEquals(2,namesIdLessThan3.size());
     }

}
