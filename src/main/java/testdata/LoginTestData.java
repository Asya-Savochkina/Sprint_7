package testdata;
import org.apache.commons.lang3.RandomStringUtils;
import objects.NewCourier;
import objects.LoginForCourier;

public class LoginTestData {
    private static final String LOGIN = RandomStringUtils.randomAlphanumeric(5);
    private static final String PASSWORD = RandomStringUtils.randomAlphanumeric(5);
    public static LoginForCourier from(NewCourier newCourier) {
        LoginForCourier loginForCourier = new LoginForCourier();
        loginForCourier.setLogin(newCourier.getLogin());
        loginForCourier.setPassword(newCourier.getPassword());
        return loginForCourier;

    }

    public static LoginForCourier invalidLoginPassword() {
        LoginForCourier loginForCourier = new LoginForCourier();
        loginForCourier.setLogin(LOGIN);
        loginForCourier.setPassword(PASSWORD);
        return loginForCourier;

    }

    public static LoginForCourier requestWithoutRequiredField()
    {
        LoginForCourier loginForCourier = new LoginForCourier();
        loginForCourier.setPassword(PASSWORD);
        return loginForCourier;
    }
}
