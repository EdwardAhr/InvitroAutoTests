package TestsUi;

import PageObject.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.*;
import io.qameta.allure.selenide.*;
import jdk.jfr.Description;
import org.junit.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class TestClass {

    TitlePage titlePage = new TitlePage();
    RadiologyPage radiologyPage = new RadiologyPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();
    AnalyzesPage analyzesPage = new AnalyzesPage();
    TestHelper testHelper = new TestHelper();
    BucketPage bucketPage = new BucketPage();

    @BeforeClass
    public static void SetUp(){
    Configuration.browser = "chrome";
    Configuration.timeout = 15000;
    baseUrl = "https://www.invitro.ru/";
    open(baseUrl);
    WebDriverRunner.getWebDriver().manage().window().maximize();
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @After
    public void afterTest(){
        open(baseUrl);
        refresh();
        $x("//div[@class='container-content show']").shouldBe(visible);
    }


    @Test
    @Description("Тест на проверку отображения всех вкладок в меню 'Медицинские услуги'")
    public void MedicalServicesPagesIsDisplayed() {
        titlePage.changeCityInHeaderMenu("Москва");
        titlePage.headerMainMenuElementClick("Медицинские услуги");
        radiologyPage.iterateOverChildElements("МРТ",radiologyPage.childElementsMRT);
        radiologyPage.iterateOverChildElements("КТ",radiologyPage.childElementsKT);
        radiologyPage.iterateOverChildElements("Рентген",radiologyPage.childElementsRentgen);
        radiologyPage.selectSidePageElements("Денситометрия");
        radiologyPage.selectSidePageElements("Маммография");
        radiologyPage.selectSidePageElements("Лазерная терапия в гинекологии");
        radiologyPage.selectSidePageElements("Биопсия");
        radiologyPage.selectSidePageElements("Эстетическая гинекология");
        radiologyPage.selectSidePageElements("Оториноларингология");
        radiologyPage.selectSidePageElements("ГСГ");
        radiologyPage.iterateOverChildElements("Эндоскопия",radiologyPage.childElementsEndoscopy);
        radiologyPage.selectSidePageElements("Функциональная диагностика");
        radiologyPage.selectSidePageElements("Кольпоскопия");
        radiologyPage.iterateOverChildElements("УЗИ",radiologyPage.childElementsUltrasound);
        radiologyPage.selectSidePageElements("Эхокардиография");
        radiologyPage.selectSidePageElements("Плазмотерапия");
        radiologyPage.selectSidePageElements("Вакцинация");
        radiologyPage.selectSidePageElements("Инъекции");
        radiologyPage.selectSidePageElements("Флебология");
        radiologyPage.selectSidePageElements("Прочие услуги");
    }

    @Test
    @Description("Тест проверяющий смену города на титульной странице")
    public void ChangeCityOnTitlePage(){
        titlePage.changeCityInHeaderMenu("Омск");
    }

    @Test
    @Description("Тест на проверку заполнения реквизитов во вкладке 'Получить результаты анализов' ")
    public void FillingRequisiteOnGetResultsAnalyzesPage() {
        String codeInzData = "231231231";
        String birthdayData = "11.12.2000";
        String lastNameData = "тест";
        String expectedColor = "rgba(255, 0, 0, 1)";

        titlePage.resultsAnalyzesBtnClick();
        registrationResultsPage.searchResultBtnClick();
        $(registrationResultsPage.UnauthResultsError()).shouldBe(visible);
        Assert.assertEquals(expectedColor,$(registrationResultsPage.codeInzField()).getCssValue("color"));
        Assert.assertEquals(expectedColor,$(registrationResultsPage.birthdayField()).getCssValue("color"));
        Assert.assertEquals(expectedColor,$(registrationResultsPage.lastNameField()).getCssValue("color"));
        registrationResultsPage.putDataInCodeInzField(codeInzData);
        registrationResultsPage.putDataBirthdayField(birthdayData);
        registrationResultsPage.putDataLastNameField(lastNameData);
    }

    @Test
    @Description("Тест на проверку корректности суммы товара в корзине  ")
    public void CompressionGoodsAmountsInBucket() throws CoastException {

    String analysisName = "Глюкоза (в крови)";

    titlePage.headerMainMenuElementClick("Анализы");
    int productCoast = analyzesPage.analysisCost(analysisName);
    analyzesPage.addToBucketAnalysis(analysisName);
    titlePage.bucketBtnClick();
    int productBucketCoast = bucketPage.productCoast(analysisName);
    testHelper.copmareProductsCoast(productCoast,productBucketCoast);
    bucketPage.clickClearBucket();
    Assert.assertTrue(bucketPage.getClearBucketTitle().isDisplayed());
    }

    @Description("Тест на проверку поискового поля")
    @Test
    public void SearchFieldTest(){
    String searchElement = "1515";
    titlePage.searchFieldSendKeys(searchElement);
    Assert.assertTrue($(titlePage.getSearchPageResult()).text().contains(searchElement));
    }

    @Description("Тест на открытия элементов popUp меню")
    @Test
    public void PopupPageTest(){
        titlePage.selectPopupPage(PopUpMenuElement.DOCTORS);
        Assert.assertEquals(PopUpMenuElement.DOCTORS.getValue(),$(titlePage.getPopupPageBtn()).text());
    }

    @AfterClass
    public static void tearDown(){
        closeWindow();
    }
}