package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqResTestData {

    public Map<String,String> expectedDataSetUp(String name,String job){
      Map<String,String> expectedData = new HashMap<>();
      expectedData.put("name",name);
      expectedData.put("job",job);

      return expectedData;
    }

}



// "name": "morpheus",
//  "job": "leader"