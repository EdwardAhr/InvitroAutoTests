package Steps;

import PageObject.BucketPage;
import io.cucumber.java.ru.И;

public class BucketSteps extends BucketPage {
    private int productCoast;

    public int getProductCoast() {
        return productCoast;
    }

    @И("Берем стоимость товара {string}")
    public void setProductCoast(String productName){
        this.productCoast = productCoast(productName);
    }
}
