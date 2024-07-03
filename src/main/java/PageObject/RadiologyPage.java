package PageObject;

import jdk.jfr.Description;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RadiologyPage {
    TitlePage titlePage = new TitlePage();

    // Список всех дочерних элементов пункта меню "МРТ"
    public ArrayList<String> childElementsMRT = new ArrayList<String>(Arrays.asList(
            "МРТ головы",
            "МРТ внутренних органов",
            "МРТ мягких тканей",
            "МРТ при беременности",
            "МРТ периферической нервной системы",
            "МРТ молочных желез",
            "МРТ позвоночника",
            "МРТ суставов",
            "МРТ с контрастом",
            "Дополнительные услуги"));

    // Список всех дочерних элементов пункта меню "КТ"
    public ArrayList<String> childElementsKT = new ArrayList<String>(Arrays.asList(
            "КТ головы",
            "КТ с контрастом",
            "КТ позвоночника",
            "КТ суставов и костей",
            "КТ внутренних органов",
            "КТ мягких тканей",
            "Дополнительные услуги"));

    // Список всех дочерних элементов пункта меню "КТ"
    public ArrayList<String> childElementsRentgen = new ArrayList<String>(Arrays.asList(
            "Рентген головы",
            "Рентген тела",
            "Рентген суставов и костей",
            "Дополнительные услуги"));

    // Список всех дочерних элементов пункта меню "Эндоскопия"
    public ArrayList<String> childElementsEndoscopy = new ArrayList<String>(Arrays.asList(
            "Капсульная эндоскопия",
            "Гастроскопия",
            "Колоноскопия",
            "Ректосигмоскопия",
            "Полипэктомия",
            "Дополнительные услуги"));

    // Список всех дочерних элементов пункта меню "Узи"
    public ArrayList<String> childElementsUltrasound = new ArrayList<String>(Arrays.asList(
            "УЗИ при беременности",
            "УЗИ предстательной железы",
            "УЗИ в педиатрии",
            "УЗИ сосудов",
            "УЗИ мочевыделительной системы",
            "УЗИ щитовидной железы",
            "УЗИ органов брюшной полости",
            "УЗИ органов малого таза",
            "Маммологическое обследование",
            "Прочие исследования"));

    //Методы работы с вкладкой "Медицинские услуги"

    @Description("Метод для выбора родительсткого элемента")
    public void selectSidePageElements(String sidePageElement){
            $x(String.format("//a[contains(@class, 'side-bar-second') and contains(text(),'%s')]",sidePageElement)).click();
            $(titlePage.getTitleBlockName(sidePageElement)).shouldBe(visible);
    }

    @Description("Метод для выбора дочернего элемента")
    public void selectChildSidePageElements( String sidePageElement,String childSidePageElement)
    {
        $x(String.format("//li[contains(@class,'side-bar-second')]/a[text() = '%s']/following-sibling::*//li/a[contains(text(),'%s')]",sidePageElement,childSidePageElement)).click();
        $(titlePage.getTitleBlockName(childSidePageElement)).shouldBe(visible);
    }

    @Description("Метод перебирающий все дочерние элементы")
    public void iterateOverChildElements(String sidePageElement, ArrayList<String> childElementsPage){
        selectSidePageElements(sidePageElement);
        for(String childElement : childElementsPage){
            selectChildSidePageElements(sidePageElement,childElement);
        }
    }
}
