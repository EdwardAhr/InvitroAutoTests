package TestsApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestsApi {

    final String baseUrl = "https://www.invitro.ru/local/ajax";
    final String endpointCurrentCity = "/current-city.php?CODE=";

    @Description("Проверка Api запроса current-city")
    @Test
    public void currentCityTest() {
        RestAssured.useRelaxedHTTPSValidation();
            given()
                 .baseUri(baseUrl)
                 .basePath(endpointCurrentCity)
               .contentType(ContentType.JSON)
               .when()
                .get()
               .then()
                .statusCode(HTTP_OK & HTTP_ACCEPTED)
                 .body("$",allOf(hasKey("city"),hasKey("code"),hasKey("guid")))
                 .body("city",notNullValue(),"code",notNullValue(),"guid",notNullValue());

    }
}
