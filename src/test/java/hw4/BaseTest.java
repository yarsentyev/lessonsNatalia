package hw4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {


    public static final String MAIN_PART_OF_URL = "https://dev.riskmarket.tech/gateway/";
    GetPropertiesValues property = new GetPropertiesValues();
    public String clientID;
    protected String jsonPath =  System.getProperty("user.dir") + "\\src\\test\\resources\\hintBody.json";
    HashMap<String, String> authData = new HashMap<>();

    static ResponseSpecification responseCommonSpec(){
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);

        return builder.build();
    }

    @Before
    public void setup() {
        //Авторизация
        baseURI = MAIN_PART_OF_URL + "user-service/oauth/token?remember-me=true";
        //Создание_Получение подсказки
        RestAssured.basePath = MAIN_PART_OF_URL + "catalogue/hint/admin";
        property = new GetPropertiesValues(); //получение credentials админа из файла application.properties
        clientID = getClientID();

    }

    public String getClientID() {
        Response r1 = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .when().get(MAIN_PART_OF_URL + "user-service/accounts/current");

       return r1.getCookie("CLIENT-ID");
    }

    //Авторизация пользователя
    public HashMap<String, String> authorizationRM(String userRole) throws IOException {
        property.getPropValues(userRole);

        ValidatableResponse authRequest =  RestAssured.given()

                .formParams("grant_type", "password", "username", property.login, "password", property.psw)
                .when().post(baseURI).then()
                .log().all();

        authData.put("xSrfToken", authRequest.extract().cookie("XSRF-TOKEN"));
        authData.put("authObject", authRequest.extract().cookie("authObject"));
        authData.put("clientIdVisible", authRequest.extract().cookie("CLIENT-ID-VISIBLE"));

        return authData;
    }

    //Запрос на создание подсказки
    public ValidatableResponse  createHint(HashMap<String, String> authData, String jsonBody){
       return RestAssured.given().log().all()
                .cookie("XSRF-TOKEN", authData.get("xSrfToken"))
                .header("X-XSRF-TOKEN", authData.get("xSrfToken"))
                .header("Content-Type", "application/json")
                .header("CLIENT-ID-VISIBLE", authData.get("clientIdVisible"))
                .header("CLIENT-ID", clientID)
                .cookie("CLIENT-ID-VISIBLE", authData.get("clientIdVisible"))
                .cookie("CLIENT-ID", clientID)
                .cookie("authObject", authData.get("authObject"))
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when().post(RestAssured.basePath)
                .then().log().all();
    }

    //получение подсказки
    public ValidatableResponse gettingOfHint(HashMap<String, String > authData, String hintID) {
        return RestAssured.given().log().all()
                .cookie("XSRF-TOKEN", authData.get("xSrfToken"))
                .header("X-XSRF-TOKEN", authData.get("xSrfToken"))
                .header("Content-Type", "application/json")
                .header("CLIENT-ID-VISIBLE", authData.get("clientIdVisible"))
                .header("CLIENT-ID", clientID)
                .cookie("CLIENT-ID-VISIBLE", authData.get("clientIdVisible"))
                .cookie("CLIENT-ID", clientID)
                .cookie("authObject", authData.get("authObject"))
                .contentType(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when().get(RestAssured.basePath + "/" + hintID).then();

    }

    public void checkGetHint(String hintBody){
        JSONObject jsonObject = new JSONObject(hintBody);  // парсинг json
        String text = jsonObject.getString("text");
        boolean active = jsonObject.getBoolean("active");
        String action = jsonObject.getString("action");
        String insuranceTypes = jsonObject.getJSONArray("insuranceTypes").toString();
        String countries = jsonObject.getJSONArray("countries").toString();

        Assert.assertTrue(active);
        Assert.assertEquals("NONE", action);
        Assert.assertEquals("text of the hint 2021", text);
        Assert.assertEquals("[\"RUSSIA124\"]", countries);
        Assert.assertEquals("[\"Direct\"]", insuranceTypes);

    }

}
