package Steps;

import PageObject.RadiologyPage;
import io.cucumber.java.ru.*;


public class MedicalServicesPageSteps extends RadiologyPage{

   @И("Проходимся по всем вкладкам, как дочерним, так и родительским, проверяя их отображение")
        public void checkAllElementsInRadiologyPage(){
       iterateOverChildElements("МРТ",childElementsMRT);
       iterateOverChildElements("КТ",childElementsKT);
       iterateOverChildElements("Рентген",childElementsRentgen);
       selectSidePageElements("Денситометрия");
       selectSidePageElements("Маммография");
       selectSidePageElements("Лазерная терапия в гинекологии");
       selectSidePageElements("Биопсия");
       selectSidePageElements("Эстетическая гинекология");
       selectSidePageElements("Оториноларингология");
       selectSidePageElements("ГСГ");
       iterateOverChildElements("Эндоскопия",childElementsEndoscopy);
       selectSidePageElements("Функциональная диагностика");
       selectSidePageElements("Кольпоскопия");
       iterateOverChildElements("УЗИ",childElementsUltrasound);
       selectSidePageElements("Эхокардиография");
       selectSidePageElements("Плазмотерапия");
       selectSidePageElements("Вакцинация");
       selectSidePageElements("Инъекции");
       selectSidePageElements("Флебология");
       selectSidePageElements("Прочие услуги");
   }
    }

