package hw4;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.Reader;

public class JsonParser {

  public String jsonToString(String jsonPath){
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


