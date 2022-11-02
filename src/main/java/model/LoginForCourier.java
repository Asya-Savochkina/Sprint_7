package model;

import org.apache.commons.lang3.RandomStringUtils;

public class LoginForCourier {
    private String login;
    private String password;
    public LoginForCourier() {
    }

    public LoginForCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static final String LOGIN = RandomStringUtils.randomAlphanumeric(5);
    private static final String PASSWORD = "qwerty";

    public static LoginForCourier getLoginRequestAllRequiredField(NewCourier newCourier)
    {
        LoginForCourier loginForCourier = new LoginForCourier();
        loginForCourier.setLogin(newCourier.getLogin());
        loginForCourier.setPassword(newCourier.getPassword());
        return loginForCourier;
    }
}
