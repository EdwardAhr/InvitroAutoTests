package pojoClasses;

public class CurrentCityData {
        private final String city;
        private final String code;
        private final String guid;

        public CurrentCityData(String city, String code, String guid) {
            this.city = city;
            this.code = code;
            this.guid = guid;
        }
        public String getCity() {
            return city;
        }
        public String getCode() {
            return code;
        }
        public String getGuid() {
            return guid;
        }
}
