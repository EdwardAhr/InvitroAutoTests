package TestsUi;

import PageObject.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.*;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.*;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestClass {

    TitlePage titlePage = new TitlePage();
    RadiologyPage radiologyPage = new RadiologyPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();
    AnalyzesPage analyzesPage = new AnalyzesPage();
    TestsUi.TestHelper testHelper = new TestsUi.TestHelper();
    BucketPage bucketPage = new BucketPage();

    @BeforeAll
    public static void SetUp(){
    Configuration.browser = "chrome";
    Configuration.timeout = 30000;
    baseUrl = "https://www.invitro.ru/";
    open(baseUrl);
    WebDriverRunner.getWebDriver().manage().window().maximize();
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    public void afterTest(){
        open(baseUrl);
        refresh();
        $(titlePage.getContainerBase()).shouldBe(visible);
    }

    //Тест некорректный, ибо после первой не открывшейся вкладки он упадет, лучше на каждую вкладку написать свой тест
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
        String city = "Омск";
        titlePage.changeCityInHeaderMenu(city);
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
        assertEquals(expectedColor,$(registrationResultsPage.codeInzField()).getCssValue("color"),"Поле 'Код Инз' не окрашено в красный");
        assertEquals(expectedColor,$(registrationResultsPage.birthdayField()).getCssValue("color"),"Поле 'Дата' не окрашено в красный");
        assertEquals(expectedColor,$(registrationResultsPage.lastNameField()).getCssValue("color"),"Поле 'ФИО' не окрашено в красный");
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
    assertTrue(bucketPage.getClearBucketTitle().isDisplayed(),"Корзина не очистилась");
    }

    @Description("Тест на проверку поискового поля")
    @Test
    public void SearchFieldTest(){
    String searchElement = "1515";
    titlePage.searchFieldSendKeys(searchElement);
    assertTrue($(titlePage.getSearchPageResult()).text().contains(searchElement),"Поиск по элементу не прошел");
    }

    @Description("Тест на открытия элементов popUp меню")
    @Test
    public void PopupPageTest(){
        titlePage.selectPopupPage(PopUpMenuElement.DOCTORS);
        assertEquals(PopUpMenuElement.DOCTORS.getValue(),$(titlePage.getPopupPageBtn()).text(),"Не открылась вкладка ПоПап меню");
    }

    @AfterAll
    public static void tearDown(){
        closeWindow();
    }
}