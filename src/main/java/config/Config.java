package config;

public class Config {
    public static final String MAIN_URL = "http://qa-scooter.praktikum-services.ru/api/v1/";

    public static String getBaseUri(){
        return MAIN_URL;
    }

    public static final String COURIER = "courier";
    public static final String COURIER_LOGIN = COURIER + "/login";
    public static final String COURIER_ID = COURIER + "/{id}";
    public static final String ORDERS_ROOT = "orders";

}
