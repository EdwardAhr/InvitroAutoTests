package Steps;

import PageObject.PopUpMenuElement;
import PageObject.TitlePage;
import io.cucumber.java.ru.И;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TitlePageSteps extends TitlePage {

    @И("Выставляем город {string} на титульной странице,проверяя, его отображение на титульной странице")
    public void selectCityOnTitlePage(String city){
        changeCityInHeaderMenu(city);
    }

    @И("Нажимаем на вкладку меню {string}")
    public void selectHeaderElement(String headerElement){
        headerMainMenuElementClick(headerElement);
    }

    @И("Нажимаем на кнопку 'Результаты анализов'")
    public void resultsAnalyzesBtnClick(){
        $(getResultsAnalyzesBtn()).click();
    }

    @И("Открываем корзину")
    public void openBucket(){
        $(getBucketBtn()).click();
    }

    @И("Открываем элемент {string} popUp меню и проверям его отображение")
    public void selectPopUpPage(String popUpMenuElement){
        selectPopupPage(PopUpMenuElement.DOCTORS);
        assertEquals(PopUpMenuElement.DOCTORS.getValue(),$(getPopupPageBtn()).text(),"Не открылась вкладка ПоПап меню");
    }

    @И("Вводим данные {string} в поисковое поле и проверям отображение результата")
    public void checkSearchField(String searchElement){
        searchFieldSendKeys(searchElement);
        assertTrue($(getSearchPageResult()).text().contains(searchElement),"Поиск по элементу не прошел");
    }
}
