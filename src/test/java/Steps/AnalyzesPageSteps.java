package Steps;

import PageObject.AnalyzesPage;
import io.cucumber.java.ru.И;

public class AnalyzesPageSteps extends AnalyzesPage {
    private int analysisCoast;

    public int getAnalysisCoast() {
        return analysisCoast;
    }

    @И("Получаем стоимость анализа {string}")
    public void setAnalysisCoast(String analysisName){
        this.analysisCoast = analysisCost(analysisName);
    }

    @И("Добавляем товар {string} в корзину")
    public void addProductInBucket(String analysisName){
        addToBucketAnalysis(analysisName);
    }
}
