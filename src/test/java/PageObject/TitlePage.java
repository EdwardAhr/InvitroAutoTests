package PageObject;


import com.codeborne.selenide.SelenideElement;
import jdk.jfr.Description;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitlePage {

    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();

   //Локаторы

    //Кнопка города на главной странице
    private final By cityBtnInHeader = new By.ByXPath("//span[@class ='city__name city__btn city__name--label']");

    //Кнопка "Выбрать другой" в модальном окне после нажатия на кнпоку города
    private final By selectAnotherCityBtn = new By.ByXPath("//a[contains(@class,'city__change-btn')]");

    //Поисковое поле города в модальном окне
    private final By searchCityField = new By.ByXPath("//input[contains(@class,'change-city-search-input')]");

    //Кнопка 'Результаты анализов' на титульной странице
    private final By ResultsAnalyzesBtn = new By.ByXPath("//div[@class ='invitro_header-bottom_right']/a[@class ='invitro_header-get_result']/span[contains(text(),'Результаты анализов')]");

    //Кнопка корзины на титульной странице
    private final By BucketBtn = new By.ByXPath("//*[@id='headerCartDynamic']/div/a");

    //Поле поисковой строки
    private final By searchField = new  By.ByXPath("//input[@class='search__input form-header-search_input']");

    //Поле поискового результата
    private final By searchPageResult = new By.ByXPath("//div[@class='search-page__result']");

    //Главное меню при открытии сайта
    private final By containerBase = new By.ByXPath("//div[@class='container-content show']");

    public By getCityBtnInHeader() {
        return cityBtnInHeader;
    }

    public By getSelectAnotherCityBtn() {
        return selectAnotherCityBtn;
    }

    public By getSearchCityField() {
        return searchCityField;
    }

    public By getResultsAnalyzesBtn() {
        return ResultsAnalyzesBtn;
    }

    public By getBucketBtn() {
        return BucketBtn;
    }

    public By getSearchField() {
        return searchField;
    }

    public By getSearchPageResult() {
        return searchPageResult;
    }

    public By getPopupPageBtn() {
        return popupPageBtn;
    }
    public By getContainerBase() {return containerBase;}

    //Кнопка открывающая выпадающее меню разделов (Пациентам, Врачам, Франчайзинг, Корпоративным клиентам, Прессе)
    private final By popupPageBtn = new By.ByXPath("//div[@class='invitro_header-target_audience']");

    //Методы по работе с титульной страницей

    @Description("Метод возвращающий первый элемент в поисковой таблице городов")
    public SelenideElement firstElementInSearchCityTable(String city){
    return $x(String.format("//li[@class='selected']/div/b[contains(text(),'%s')]",city));
    }

    @Description("Метод для смены города на титульной странице")
    public void changeCityInHeaderMenu(String city)  {
            $(cityBtnInHeader).click();
            if($(selectAnotherCityBtn).isDisplayed()){
                $(selectAnotherCityBtn).click();
                $(searchCityField).sendKeys(city);
                assertEquals(city, firstElementInSearchCityTable(city).getText(),"Элемент поиска города не был найден");
                $(searchCityField).pressEnter();
                $(cityBtnInHeader).shouldBe(visible);
                assertEquals(city, $(cityBtnInHeader).getText(),"Город не был изменен");
            }
            else {
            $(searchCityField).sendKeys(city);
            assertEquals(city, firstElementInSearchCityTable(city).getText(),"Элемент поиска города не был найден");
            $(searchCityField).pressEnter();
            $(cityBtnInHeader).shouldBe(visible);
            assertEquals(city, $(cityBtnInHeader).getText(),"Город не был изменен");
            }
    }


    @Description("Метод нажимающий на пункт меню по переданному названию вкладки")
    public  void headerMainMenuElementClick(String elementHeaderMenu){
        $x(String.format("//ul[@id = 'topmenuDynamicFrameUl']/child::li/a[text() = '%s']",elementHeaderMenu)).click();
    }

    @Description("Метод возвращает xPath титульного блока по переданному в него имени")
    public SelenideElement getTitleBlockName(String titleBlockName){
        return $x(String.format("//h1[contains(text(),'%s')]",titleBlockName));
    }
    @Description("Метод нажимающий на кнопку 'Получить результаты анализов' ")
    public void resultsAnalyzesBtnClick(){
        $(ResultsAnalyzesBtn).click();
        $(registrationResultsPage.registrationResultsHeader).shouldBe(visible);
        }

    @Description("Метод перехода в корзину")
    public void bucketBtnClick(){
        $(BucketBtn).click();
    }

    @Description("Метод вписывающий данные в поисковое поле на титульной странице")
    public void searchFieldSendKeys(String sendKey){
        $(searchField).sendKeys(sendKey);
        $(searchField).pressEnter();
        $(searchPageResult).shouldBe(visible);
    }

    @Description("Выбрать переданный эелемнт popUp меню")
    public void selectPopupPage(PopUpMenuElement pageName){
        $(popupPageBtn).click();
        $x(String.format("//div/a[@class='invitro_header-target_audience-item ']/span[contains(text(),'%s')]",pageName.getValue())).click();
    }

}
