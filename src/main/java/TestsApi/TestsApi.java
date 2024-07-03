package TestsApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;
import steps.CurrentCityApiSteps;


import static io.restassured.RestAssured.*;
import static java.net.HttpURLConnection.*;


public class TestsApi {

    final String baseUrl = "https://www.invitro.ru/local/ajax";
    final String endpointCurrentCity = "/current-city.php?CODE=";

    @Description("Проверка Api запроса current-city")
    @Test
    public void currentCityTest() {
        RestAssured.useRelaxedHTTPSValidation();
         String  requestCity = given()
                 .baseUri(baseUrl)
                 .basePath(endpointCurrentCity)
               .contentType(ContentType.JSON)
               .when()
                .get()
               .then()
                .statusCode(HTTP_OK & HTTP_ACCEPTED)
               .log().body().extract().asString();
        Assert.assertTrue(requestCity.contains("city"));
        Assert.assertTrue(requestCity.contains("code"));
        Assert.assertTrue(requestCity.contains("guid"));
    }
}
