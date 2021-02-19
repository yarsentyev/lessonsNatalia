package hw4;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;

public class BaseTest {

    public static final String MAIN_PART_OF_URL = "https://dev.riskmarket.tech/gateway/";
    public String clientID = getClientID();
    protected GetPropertiesValues property;
    protected String jsonPath =  System.getProperty("user.dir") + "\\src\\test\\resources\\hintBody.json";
    String XsrfToken;
    String authObject;
    String clientIdVisible;

    @Before
    public void setup(){
        //Авторизация
        RestAssured.baseURI = MAIN_PART_OF_URL + "user-service/oauth/token?remember-me=true";
        //Создание_Получение подсказки
        RestAssured.basePath = MAIN_PART_OF_URL + "catalogue/hint/admin";
        property = new GetPropertiesValues(); //получение credentials админа из файла application.properties
    }


    public String getClientID(){
        Response r1 = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .when().get(MAIN_PART_OF_URL + "user-service/accounts/current");

        String clientIdFromResponse = r1.getCookie("CLIENT-ID");
        return clientIdFromResponse;
    }


    public void checkGetHint(String hintBody){
        JSONObject jsonObject = new JSONObject(hintBody);  // парсинг json
        String text = jsonObject.getString("text");
        boolean active = jsonObject.getBoolean("active");
        String action = jsonObject.getString("action");
        String insuranceTypes = jsonObject.getJSONArray("insuranceTypes").toString();
        String countries = jsonObject.getJSONArray("countries").toString();

        Assert.assertEquals(true, active);
        Assert.assertEquals("NONE", action);
        Assert.assertEquals("text of the hint 2021", text);
        Assert.assertEquals("[\"RUSSIA124\"]", countries);
        Assert.assertEquals("[\"Direct\"]", insuranceTypes);

    }

}
