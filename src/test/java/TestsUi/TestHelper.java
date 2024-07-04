package TestsUi;

import jdk.jfr.Description;

public class TestHelper {

    @Description("Сравнение суммы товара в списке и корзине")
    public void copmareProductsCoast(int productCoast,int productCoastBucket) throws CoastException {

        if (productCoast == productCoastBucket) {
            System.out.println("Цены равны ☑");
        }
        if(productCoastBucket == 10000){
            throw new CoastException("Цена товара в корзине равна 10 000");
        }
        if(productCoast < productCoastBucket){
            throw new CoastException("Цена в корзине Больше, чем в списке!");
        }
        if(productCoast > productCoastBucket){
            System.out.println("Цена в корзине Меньше, чем в списке!");
        }
    }
}
