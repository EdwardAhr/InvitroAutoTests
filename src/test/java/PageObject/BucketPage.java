package PageObject;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import jdk.jfr.Description;

import static com.codeborne.selenide.Selenide.*;

public class BucketPage {

    //Локаторы элементов

    //Заголовок h1, когла корзина пуста
    private final SelenideElement clearBucketTitle = $x("//h1[text()='Ваша корзина пуста']");


    //Методы по работе с корзиной

    @Description("Удаление анализа из корзины")
    public void deleteProduct(String productName){
        $x(String.format("//div[contains(@class,'CartProduct_topContainer')]/descendant::button[contains(text(),'%s')]/ancestor::div[contains(@class,'CartProduct_topContainer')]/descendant::button[contains(@class,'CartProduct_productDelete')]",productName)).click();
    }

    @Description("Метод нажимающий на кнопку 'Очистить корзину'")
    public void clickClearBucket(){
        $x("//button[contains(@class,'cartClear')]").click();
    }

    @Description("Метод возвращает цисловое значение стоимости товара по его имени ")
    public int productCoast(String productName){
        String coast = $x(String.format("//div[contains(@class,'CartProduct_topContainer')]/descendant::button[contains(text(),'%s')]/ancestor::div[contains(@class,'CartProduct_topContainer')]/descendant::span",productName)).text();
        coast = coast.substring(0,coast.length()-1).replace(" ","");
        return Integer.parseInt(coast);
    }

    @Description("Возвращает элемент h1 'Ваша корзина пуста'")
    public SelenideElement getClearBucketTitle(){
     return clearBucketTitle;
    }
}
