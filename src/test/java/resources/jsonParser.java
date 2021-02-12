package resources;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.Reader;

public class jsonParser {

  private String jsonPath = "C:\\Users\\Nata\\LessonsYuriy\\src\\main\\resources\\hintBody.json";

  public String jsonToString(){
    String bodyFromJson = "";
    JSONParser parser = new JSONParser();
    try (Reader reader = new FileReader(jsonPath)) {
      JSONObject jsonObject = (JSONObject) parser.parse(reader);
      System.out.println(jsonObject);
      bodyFromJson = jsonObject.toString();
    }
    catch(Exception e){e.printStackTrace();}
    return bodyFromJson;
  }
}


