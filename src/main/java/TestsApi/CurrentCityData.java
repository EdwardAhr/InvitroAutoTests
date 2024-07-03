package TestsApi;

public class CurrentCityData {
        private String city;
        private String code;
        private String guid;

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
