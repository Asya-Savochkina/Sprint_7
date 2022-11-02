package model;

import org.apache.commons.lang3.RandomStringUtils;

public class NewCourier {
    private String login;
    private String password;
    private String firstName;

    public NewCourier() {
    }

    public NewCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private static final String LOGIN = RandomStringUtils.randomAlphanumeric(5);
    private static final String PASSWORD = "qwerty";
    private static final String FIRST_NAME = "mike";

    public static NewCourier getCourierRequestAllRequiredField()
    {
        NewCourier newCourier = new NewCourier();
        newCourier.setLogin(LOGIN);
        newCourier.setPassword(PASSWORD);
        newCourier.setFirstName(FIRST_NAME);
        return newCourier;
    }

    public static NewCourier getCourierRequestExistingLogin(NewCourier existingCourier) {
        NewCourier newCourier = new NewCourier();
        newCourier.setLogin(existingCourier.getLogin());
        newCourier.setPassword(existingCourier.getPassword());
        newCourier.setFirstName(existingCourier.getFirstName());
        return newCourier;
    }

    public static NewCourier getCourierRequestWithoutRequiredField()
    {
        NewCourier newCourier = new NewCourier();
        newCourier.setPassword(PASSWORD);
        newCourier.setFirstName(FIRST_NAME);
        return newCourier;
    }
}
