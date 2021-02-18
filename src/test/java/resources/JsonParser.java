package resources;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.Reader;

public class JsonParser {

  private String jsonPath = "C:\\SRDEV\\lessonsNatalia\\src\\main\\resources\\hintBody.json";

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


