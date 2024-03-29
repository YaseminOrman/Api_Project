package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

 protected static ObjectMapper mapper;

 static {//static block works before every process and this object will be created once and used every classes.
   mapper = new ObjectMapper();
 }
  //This method will accept two parameters and convert first parameter to second parameter data type by using ObjectMapper class.
 public static <T> T convertJsonToJavaObject(String json,Class<T> cls){//generic method
       T javaResult = null;
   try {
        javaResult= mapper.readValue(json,cls);
   } catch (IOException e) {
       e.printStackTrace();
         }
   return javaResult;

      }

      //2.method is used to convert Java Object to Json Data.Serialization Method
    public static String convertJavaToJson(Object obj){

     String jsonResult = null;
        try {
            jsonResult= mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java object could not be converted to Json"+e.getMessage());
        }
        return jsonResult;
    }
}