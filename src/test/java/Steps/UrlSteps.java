package Steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Когда;

import static com.codeborne.selenide.Selenide.open;

public class UrlSteps
{
    @Когда("Открываем сайт {string}")
    public void openUrl(String Url){
        open(Url);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}
