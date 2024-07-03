package PageObject;

public enum PopUpMenuElement {
    DOCTORS("Врачам"),
    PATIENTS("Пациентам"),
    FRANCHISING("Франчайзинг"),
    CORPORATIONCLIENTS("Корпоративным клиентам"),
    PRESS("Прессе");

    private String value;

    PopUpMenuElement(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
