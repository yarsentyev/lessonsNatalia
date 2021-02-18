import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import resources.BaseTest;
import resources.GetPropertiesValues;
import resources.JsonParser;


import java.io.IOException;


public class RiskMarketTests extends BaseTest {

    // Тест обращается к google.com и проверяет, что в ответе получает StatusCode=200
  @Test
  public void getRequestToGoogleTest() throws IOException {
   RestAssured.
            when().get("https://google.com").
            then().assertThat().statusCode(200);
  }

  // Авторизация как Админ и получение параметров из Response
  @Test
  public void adminAuthorizationRiskMarketTest() throws IOException {

    GetPropertiesValues property = new GetPropertiesValues(); //получение credentials админа из файла application.properties
    property.getPropValues("admin");

    ValidatableResponse r2 = RestAssured.given().log().all()
            .header("X-XSRF-TOKEN", "null_csrf")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .cookie("CLIENT-ID", clientID)
            .formParams("grant_type", "password", "username", property.login, "password", property.psw)
            .when().post("https://dev.riskmarket.tech/gateway/user-service/oauth/token?remember-me=true")
            .then().log().all()
            .statusCode(200);

    String XsrfToken = r2.extract().cookie("XSRF-TOKEN");
    String authObject = r2.extract().cookie("authObject");
  }

  //Создание подсказки админом и получение созданной подсказки
  @Test
  public void adminHintCreationRequestToRMTest() throws IOException {

    //Авторизация и получение всех необходимых параметров для работы
       // Получение credentials из файла с properties
    GetPropertiesValues property = new GetPropertiesValues(); //получение credentials админа из файла application.properties
    property.getPropValues("admin");

    System.out.println("//////////////////////////////////////////////////////");
    ValidatableResponse authRequest = RestAssured.given().log().all()
            .header("X-XSRF-TOKEN", "null_csrf")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .cookie("CLIENT-ID", clientID)
            .formParams("grant_type", "password", "username", property.login, "password", property.psw)
            .when().post("https://dev.riskmarket.tech/gateway/user-service/oauth/token?remember-me=true")
            .then().log().all()
            .statusCode(200);

    String xSrfToken = authRequest.extract().cookie("XSRF-TOKEN");
    String authObject = authRequest.extract().cookie("authObject");
    String clientIdVisible = authRequest.extract().cookie("CLIENT-ID-VISIBLE");

    // Считывание json из файла
    JsonParser js = new JsonParser();
    String jss = js.jsonToString();

    // Post на создание подсказки
    ValidatableResponse postHintrequest = RestAssured.given().log().all()
            .cookie("XSRF-TOKEN", xSrfToken)
            .header("X-XSRF-TOKEN", xSrfToken)
            .header("Content-Type", "application/json")
            .header("CLIENT-ID-VISIBLE", clientIdVisible)
            .header("CLIENT-ID", clientID)
            .cookie("CLIENT-ID-VISIBLE", clientIdVisible)
            .cookie("CLIENT-ID", clientID)
            .cookie("authObject", authObject)
            .contentType(ContentType.JSON)
            .body(jss)
            .when().post(RestAssured.basePath)
            .then().log().all()
            .statusCode(200);
    // Получение id подсказки
    System.out.println("id подсказки");
    String hintID = postHintrequest.extract().body().asString();
    System.out.println(hintID);

    //получение подсказки
    Response  hintResponse = RestAssured.given().log().all()
            .cookie("XSRF-TOKEN", xSrfToken)
            .header("X-XSRF-TOKEN", xSrfToken)
            .header("Content-Type", "application/json")
            .header("CLIENT-ID-VISIBLE", clientIdVisible)
            .header("CLIENT-ID", clientID)
            .cookie("CLIENT-ID-VISIBLE", clientIdVisible)
            .cookie("CLIENT-ID", clientID)
            .cookie("authObject", authObject)
            .contentType(ContentType.JSON)
            .when().get(RestAssured.basePath + "/"+hintID);

    Assert.assertEquals(200, hintResponse.getStatusCode());

    checkGetHint(hintResponse.body().asString());
  }

  // Авторизация как Пользователь и получение параметров из Response
  @Test
  public void userAuthorizationRiskMarketTest() throws IOException {

    GetPropertiesValues property = new GetPropertiesValues(); //получение credentials пользователя из файла application.properties
    property.getPropValues("user");


    ValidatableResponse userResponse = RestAssured.given().log().all()
            .header("X-XSRF-TOKEN", "null_csrf")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .cookie("CLIENT-ID", clientID)
            .formParams("grant_type", "password", "username", property.login, "password", property.psw)
            .when().post(RestAssured.baseURI).then().log().all()
            .statusCode(200);

    String XsrfToken = userResponse.extract().cookie("XSRF-TOKEN");
    String authObject = userResponse.extract().cookie("authObject");
    String clientIdVisible = userResponse.extract().cookie("CLIENT-ID-VISIBLE");

    // Считывание json из файла
    JsonParser js = new JsonParser();
    String jss = js.jsonToString();

    // Post на создание подсказки пользователем
    ValidatableResponse postHintrequest = RestAssured.given().log().all()
            .cookie("XSRF-TOKEN", XsrfToken)
            .header("X-XSRF-TOKEN", XsrfToken)
            .header("Content-Type", "application/json")
            .header("CLIENT-ID-VISIBLE", clientIdVisible)
            .header("CLIENT-ID", clientID)
            .cookie("CLIENT-ID-VISIBLE", clientIdVisible)
            .cookie("CLIENT-ID", clientID)
            .cookie("authObject", authObject)
            .contentType(ContentType.JSON)
            .body(jss)
            .when().post(RestAssured.basePath)
            .then().log().all()
            .statusCode(403);

    //Авторизованный пользователь получает подсказку, созданную администратором
    Response  hintResponse = RestAssured.given().log().all()
            .cookie("XSRF-TOKEN", XsrfToken)
            .header("X-XSRF-TOKEN", XsrfToken)
            .header("Content-Type", "application/json")
            .header("CLIENT-ID-VISIBLE", clientIdVisible)
            .header("CLIENT-ID", clientID)
            .cookie("CLIENT-ID-VISIBLE", clientIdVisible)
            .cookie("CLIENT-ID", clientID)
            .cookie("authObject", authObject)
            .contentType(ContentType.JSON)
            .when().get(RestAssured.basePath + "/6316");

    Assert.assertEquals(200, hintResponse.getStatusCode());

    checkGetHint(hintResponse.body().asString());

  }

  // не авторизованный пользователь пытается получить подсказку
    @Test
    public void userNonAuthorGetHintRiskMarketTest() throws IOException {
        GetPropertiesValues property = new GetPropertiesValues(); //получение credentials пользователя из файла application.properties
        property.getPropValues("user");

        Response userNonAuthResponse = RestAssured.given().log().all()
                .header("X-XSRF-TOKEN", "null_csrf")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParams("grant_type", "password", "username", property.login, "password", property.psw)
                .when().get(RestAssured.basePath + "6316");
                userNonAuthResponse.prettyPrint();
        Assert.assertEquals(401, userNonAuthResponse.getStatusCode());
    }

}



