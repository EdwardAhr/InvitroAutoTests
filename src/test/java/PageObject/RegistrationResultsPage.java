package PageObject;

import com.codeborne.selenide.SelenideElement;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationResultsPage {

    // Локаторы элементов на странице 'Результаты анализов'

    //Заголовок страницы 'Введите индивидуальный номер заказа, чтобы посмотреть результаты анализов'
    public final By registrationResultsHeader = new By.ByXPath("//h2[contains(text(),'Введите индивидуальный номер заказа, чтобы посмотреть результаты анализов')]");

    //Поле 'Код ИНЗ'
    private final By codeInzField = new By.ByXPath("//input[@name = 'orderNumber']");

    public By getUnauthResultsError() {
        return unauthResultsError;
    }

    public By getSearchResultBtn() {
        return searchResultBtn;
    }

    public By getLastNameField() {
        return lastNameField;
    }

    public By getBirthdayField() {
        return birthdayField;
    }

    public By getCodeInzField() {
        return codeInzField;
    }

    //Поле 'Дата рождения'
    private final By birthdayField = new By.ByXPath("//input[@name = 'birthday']");

    //Поле 'Фамилия'
    private final By lastNameField = new By.ByXPath("//input[@name = 'lastName']");

    //Кнопка 'Найти результаты'
    private final By searchResultBtn = new By.ByXPath("//button[text()='Найти результаты']");

    //Ошибка об обязательном заполнении полей
    private final By unauthResultsError = new By.ByXPath("//div[contains(@class,'UnauthResultsPage_error')]");

    //Методы работы с вкладкой 'Результаты анализов'

    @Description("Нажать на кнопку 'Найти результаты'")
    public void searchResultBtnClick(){
        $(searchResultBtn).click();
    }

    @Description("Вписать данные в поле 'Код ИНЗ'")
    public void putDataInCodeInzField(String Data) {
    $(codeInzField).sendKeys(Data);
    $x(String.format("//input[contains(@value,'%s') and @name = 'orderNumber']",Data)).shouldBe(visible);
    }

    @Description("Вписать данные в поле 'Дата рождения'")
    public void putDataBirthdayField(String Data) {
    // Пофиксил костылём
    $(birthdayField).click();
    $(birthdayField).sendKeys(Data);
    $x(String.format("//input[contains(@value,'%s') and @name = 'birthday']",Data)).shouldBe(visible);

    }

    @Description("Вписать данные в поле 'Фамилия'")
    public void putDataLastNameField(String Data) {
    $(lastNameField).sendKeys(Data);
    $x(String.format("//input[contains(@value,'%s') and @name = 'lastName']",Data)).shouldBe(visible);
    }

    @Description("Метод возвращающий поле ошибки об обязательном заполнении полей")
    public SelenideElement unauthResultsError() {
        return  $(unauthResultsError);
    }

    @Description("Метод возвращающий локатор поля 'Код ИНЗ'")
    public SelenideElement codeInzField() {
        return $(codeInzField);
    }

    @Description("Метод возвращающий локатор поля 'Дата рождения'")
    public SelenideElement birthdayField() {
        return $(birthdayField);
    }

    @Description("Метод возвращающий локатор поля 'Фамилия'")
    public SelenideElement lastNameField() {
        return $(lastNameField);
    }
}

