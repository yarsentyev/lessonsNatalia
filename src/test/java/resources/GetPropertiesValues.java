package resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesValues {
  public static final String PATH_TO_PROPERTIES = "application.properties";
  String result = "";
  InputStream inputStream;
  public String login = "";
  public String psw = "";

  public GetPropertiesValues(String login, String psw) {
    this.login = login;
    this.psw = psw;
  }

  public GetPropertiesValues() {
    this.login = "";
    this.psw = "";
  }

  public GetPropertiesValues getPropValues(String log) throws IOException { // в качестве параметра передается user или админ, для определения
                                                                            // необходимых пропертей
    try {
      Properties prop = new Properties();
      String propFileName = PATH_TO_PROPERTIES;
      inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

      if (inputStream != null) {
        prop.load(inputStream);
      } else {
        throw new FileNotFoundException("property file " + propFileName + " not found in the classpath");
      }

      this.login = prop.getProperty(log + "Name");
      this.psw = prop.getProperty(log + "Psw");

    }


    catch (Exception e) {
      System.out.println("Exception: " + e);
    }
    finally {
      inputStream.close();
    }

    return this;
  }

}


