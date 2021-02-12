import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import resources.GetPropertiesValues;
import resources.jsonParser;
import sun.security.util.Resources;

import java.io.IOException;
import java.net.URL;


public class RiskMarketTests {

  // Тест обращается к google.com и проверяет, что в ответе получает StatusCode=200
  @Test
  public void getRequestToGoogleTest() throws IOException {
/*    RestAssured.
            when().get("https://google.com").
            then().assertThat().statusCode(200);*/
    jsonParser js = new jsonParser();
    System.out.println(js.jsonToString());
  }

  // Авторизация как Админ и получение параметров из Response
  @Test
  public void AuthorizationRiskMarketTest() throws IOException {

    Response r1 = RestAssured.given().log().all()
            .header("Content-Type", "application/json")
            .when().get("https://dev.riskmarket.tech/gateway/user-service/accounts/current");

    String clientIdFromResponse = r1.getCookie("CLIENT-ID");

    GetPropertiesValues property = new GetPropertiesValues(); //получение credentials админа из файла application.properties
    property.getPropValues("admin");
    String login = property.login;
    String psw = property.psw;
    System.out.println("///////////Credentials://////////////");
    System.out.println(login);
    System.out.println(psw);

    ValidatableResponse r2 = RestAssured.given().log().all()
            .header("X-XSRF-TOKEN", "null_csrf")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Host", "dev.riskmarket.tech")
            .cookie("CLIENT-ID", clientIdFromResponse)
            .formParams("grant_type", "password", "username", login, "password", psw)
            .when().post("https://dev.riskmarket.tech/gateway/user-service/oauth/token?remember-me=true").then().log().all()
            .statusCode(200);

    String XsrfToken = r2.extract().cookie("XSRF-TOKEN");
    String authObject = r2.extract().cookie("authObject");
    String clientID = clientIdFromResponse;
  }

  //Создание подсказки админом
  @Test
  public void adminHintCreationRequestToRMTest() throws IOException {

    //Авторизация и получение всех необходимых параметров для работы
    Response clientIdRequest = RestAssured.given().log().all()
            .header("Content-Type", "application/json")
            .when().get("https://dev.riskmarket.tech/gateway/user-service/accounts/current");
    String clientIdFromResponse = clientIdRequest.getCookie("CLIENT-ID");
    System.out.println("client ID value from Response = " + clientIdFromResponse);

    // Получение credentials из файла с properties
    GetPropertiesValues property = new GetPropertiesValues(); //получение credentials админа из файла application.properties
    property.getPropValues("admin");
    String login = property.login;
    String psw = property.psw;

    System.out.println("//////////////////////////////////////////////////////");
    ValidatableResponse authRequest = RestAssured.given().log().all()
            .header("X-XSRF-TOKEN", "null_csrf")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Host", "dev.riskmarket.tech")
            .cookie("CLIENT-ID", clientIdFromResponse)
            .formParams("grant_type", "password", "username", login, "password", psw)
            .when().post("https://dev.riskmarket.tech/gateway/user-service/oauth/token?remember-me=true").then().log().all().statusCode(200);

    String xSrfToken = authRequest.extract().cookie("XSRF-TOKEN");
    String authObject = authRequest.extract().cookie("authObject");
    //String clientID = authRequest.extract().cookie("CLIENT-ID-VISIBLE");

    System.out.println("//////////////////////////////HINT/////////////////////");
    // Считывание json из файла
    jsonParser js = new jsonParser();
    String jss = js.jsonToString();

    // Post на создание подсказки
    ValidatableResponse postHintrequest = RestAssured.given().log().all()
            .header("X-XSRF-TOKEN", xSrfToken)
            .header("Content-Type", "application/json")
            .header("CLIENT-ID", clientIdFromResponse)
            .header("Host", "dev.riskmarket.tech")
            .header("authObject", authObject)


            .queryParams("grant_type", "password", "username", login, "password", psw)
            .contentType(ContentType.JSON)
            .body(jss)
            .when().post("https://dev.riskmarket.tech/gateway/catalogue/hint/admin")
            .then().log().all()
            .statusCode(200);



  }
}


/*
  @Test
  public void getAuthTokenTest() {
    RestAssured.baseURI = "https://dev.riskmarket.tech/";
    RequestSpecification request = RestAssured.given().body("grant_type=password&username=txzgvzuk@emltmp.com" +
            "&password=Temp1234");
    request.header("Content-Type", "application/x-www-form-urlencoded");
    request.header("x-xsrf-token", "null_csrf");
    request.log().headers();
    request.log().body();
    Response response = request.post("gateway/user-service/oauth/token?remember-me=true");
    int StatusCode = response.getStatusCode();
    System.out.println();
    System.out.println("Status code : " + StatusCode);
    System.out.println("Response body: " + response.body().asString()); //Get Response Body
  }
*/

/*
  @Test
  public void postCreateHintsTest() {
    String someRandomString = String.format("%1$TH%1$TM%1$TS", new Date());
    JSONObject requestBody = new JSONObject();
    requestBody.put("FirstName", someRandomString);
    requestBody.put("LastName", someRandomString);
    requestBody.put("UserName", "admin");
    requestBody.put("Password", "Temp1234");
    requestBody.put("Email", "txzgvuk@emltmp.com");
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");//??????
    request.body(requestBody.toString());
    Response response = request.post("https://dev.riskmarket.tech/gateway/catalogue/hint/admin");

    int statusCode = response.getStatusCode();
    Assert.assertEquals(statusCode, 200);
    System.out.println(response.getBody().asString());
  }
}
*/
