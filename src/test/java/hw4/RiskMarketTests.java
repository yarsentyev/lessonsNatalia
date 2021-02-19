package hw4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import hw4.BaseTest;
import hw4.GetPropertiesValues;
import hw4.JsonParser;


import java.io.IOException;


public class RiskMarketTests extends BaseTest {

    // Тест обращается к google.com и проверяет, что в ответе получает StatusCode=200
  @Test
  public void getRequestToGoogleTest()  {
   RestAssured.
            when().get("https://google.com").
            then().assertThat().statusCode(200);
  }

  // Авторизация как Админ и получение параметров из Response
  @Test
  public void adminAuthorizationRiskMarketTest() throws IOException {

  authData = authorizationRM("admin");
  System.out.println(authData);
  }

  //Создание подсказки админом и получение созданной подсказки
  @Test
  public void adminHintCreationRequestToRMTest() throws IOException {
    //Авторизация и получение всех необходимых параметров для работы
    // Получение credentials из файла с properties
    authData = authorizationRM("admin");

    // Считывание json из файла
    JsonParser js = new JsonParser();
    String jss = js.jsonToString(jsonPath);

    //создание подсказки
    ValidatableResponse creationHintrequest = createHint(authData,jss)
            .spec(responseCommonSpec());
    String hintID = creationHintrequest.extract().body().asString();

   //получение подсказки
    ValidatableResponse hint =  gettingOfHint(authData,hintID)
            .spec(responseCommonSpec());
    checkGetHint(hint.extract().body().asString());
  }

  // Авторизованный пользователь пытается создать подсказку
  @Test
  public void userAuthCreateHintTest() throws IOException {

    authData = authorizationRM("user");

    // Считывание json из файла
    JsonParser js = new JsonParser();
    String jss = js.jsonToString(jsonPath);

    //Создание подсказки
    ValidatableResponse creationHintrequest = createHint(authData,jss)
            .statusCode(403);
  }

  //Авторизованный пользователь - запрос на получение подсказки, созданной админом
    @Test
      public void  userAuthGetHintTest() throws IOException {
      authData = authorizationRM("user");

      // Считывание json из файла
      JsonParser js = new JsonParser();
      String jss = js.jsonToString(jsonPath);

      //Авторизованный пользователь получает подсказку, созданную администратором
      ValidatableResponse hint =  gettingOfHint(authData,"6327")
              .spec(responseCommonSpec());
      checkGetHint(hint.extract().body().asString());
    }

  // не авторизованный пользователь пытается получить подсказку
    @Test
    public void userNonAuthorGetHintRiskMarketTest() throws IOException {
        property.getPropValues("user");

        Response userNonAuthResponse = RestAssured.given().log().all()
                .header("X-XSRF-TOKEN", "null_csrf")
                .when().get(RestAssured.basePath + "/6316");
                userNonAuthResponse.prettyPrint();
        Assert.assertEquals(401, userNonAuthResponse.getStatusCode());
    }

}



