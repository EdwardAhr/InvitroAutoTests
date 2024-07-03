package PageObject;


import jdk.jfr.Description;

import static com.codeborne.selenide.Selenide.*;

public class AnalyzesPage {

    //Методы по работе с вкладкой 'Анализы'
    @Description("Метод нажимающий на родительскую вкладку бокового меню")
    public void clickOnSidePage(String pageName){
    $x(String.format("//span[@class = 'link_analizes' and contains(text(),'%s')]",pageName)).click();
    }

    @Description("Метод переходящий в дочернюю вкладку бокового меню")
    public void clickOnSidePageChild(String pageName,String childName){
        $x(String.format("//span[@class = 'link_analizes' and contains(text(),'%s')]",pageName)).click();
        $x(String.format("//a[contains(text(),'%s') and contains(@class,'side-bar-second')]",childName)).click();
    }

    @Description("Метод нажимающий третью по глубине вкладку бокового меню")
    public void clickOnSidePageChildChild(String pageName,String childName,String childChildName){
        $x(String.format("//span[@class = 'link_analizes' and contains(text(),'%s')]",pageName)).click();
        $x(String.format("//a[contains(text(),'%s') and contains(@class,'side-bar-second')]",childName)).click();
        $x(String.format("//a[contains(text(),'%s') and contains(@class,'side-bar-third__link')]",childChildName)).click();
    }

    @Description("Метод вовращает цисловое значение суммы анализа")
    public int analysisCost(String analysisName){
        String coast =  $x(String.format("//div[@class= 'analyzes-item__container']" +
                "/descendant::a[contains(text(),'%s')]" +
                "/ancestor::div[@class='analyzes-item__container']" +
                "//ancestor::div[@class='analyzes-item__total--sum']",analysisName)).text();
        coast = coast.substring(0, coast.length() - 1).replace(" ","");
        return Integer.parseInt(coast);
    }
    @Description("Метод добавляющий анализ в корзину")
    public void addToBucketAnalysis(String analysisName){
        $x(String.format("//div[@class='analyzes-item__container']/descendant::a[contains(text(),'%s')]/ancestor::div[@class='analyzes-item__container']//span[@class='ds_box_svg']",analysisName)).click();
    }
}
