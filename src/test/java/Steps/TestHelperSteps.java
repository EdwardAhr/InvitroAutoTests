package Steps;

import TestsUi.CoastException;
import TestsUi.TestHelper;
import io.cucumber.java.ru.И;

public class TestHelperSteps extends TestHelper {
    AnalyzesPageSteps analyzesPageSteps = new AnalyzesPageSteps();
    BucketSteps bucketSteps = new BucketSteps();

    @И("Сравниваем стоимость товара")
    public void compareProductCoasts() throws CoastException {
        copmareProductsCoast(analyzesPageSteps.getAnalysisCoast(),bucketSteps.getProductCoast());
    }
}
