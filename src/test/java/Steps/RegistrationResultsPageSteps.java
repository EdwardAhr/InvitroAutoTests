package Steps;

import PageObject.RegistrationResultsPage;
import io.cucumber.java.ru.И;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegistrationResultsPageSteps extends RegistrationResultsPage {

    @И("Нажимаем на кнопку 'Найти результаты'")
    public void searchResultClick(){
        searchResultBtnClick();
    }

    @И("Проверяем отображение ошибки об обязательном заполнении полей")
    public void checkUnauthError(){
        $(getUnauthResultsError()).shouldBe(visible);
    }

    @И("Проверяем, что поля для заполнения, окрасились в красный цвет")
    public void checkErrorColorField(){
        String expectedColor = "rgba(255, 0, 0, 1)";
        assertEquals(expectedColor,$(getCodeInzField()).getCssValue("color"),"Поле 'Код Инз' не окрашено в красный");
        assertEquals(expectedColor,$(getBirthdayField()).getCssValue("color"),"Поле 'Дата' не окрашено в красный");
        assertEquals(expectedColor,$(getLastNameField()).getCssValue("color"),"Поле 'ФИО' не окрашено в красный");
    }

    @И("Заполняем поля, где Код Инз = {string}, Дата = {string}, Фамилия = {string}")
    public void putDataInFields(String codeInz,String birthday, String lastName){
        putDataInCodeInzField(codeInz);
        putDataBirthdayField(birthday);
        putDataLastNameField(lastName);
    }
}
