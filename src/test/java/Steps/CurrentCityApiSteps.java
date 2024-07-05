package Steps;

import io.cucumber.java.ru.*;
import pojoClasses.CurrentCityData;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class CurrentCityApiSteps {


    @Когда("Отправлен запрос на Url с кодом {string} и прверено его тело на содержание ключей city,code,guid и его статус код = {int}")
    public void currentCityRequest(String cityCode, int answerCode) {
        given().
                get ("https://www.invitro.ru/local/ajax/current-city.php?CODE="+cityCode)
                .then().statusCode(answerCode)
                .body("$",allOf(hasKey("city"),hasKey("code"),hasKey("guid")))
                .body("city",notNullValue(),"code",notNullValue(),"guid",notNullValue());
    }
}
