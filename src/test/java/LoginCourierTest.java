import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.Test;
import model.NewCourier;
import model.LoginForCourier;
import testdata.CourierTestData;
import testdata.LoginTestData;
import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.CourierTestData.getCourierRequestAllRequiredField;
import static testdata.LoginTestData.invalidLoginPassword;
import static testdata.LoginTestData.requestWithoutRequiredField;

public class LoginCourierTest {
    public static final String COURIER_LOGIN = "courier/login";
    private static final String COURIER = "courier";

    @AfterClass
    public static void setId() {
        LoginForCourier loginForCourier = LoginTestData.correctLogin(CourierTestData.getCourierRequestAllRequiredField());

        int id = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(loginForCourier)
                .post("courier/login")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");

        given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .delete("courier/" + id);
    }

    @Test
    @DisplayName("Авторизация курьера с корректной связкой логин-пароль")
    @Description("Проверяем возможность успешно авторизоваться с корректной связкой логин-пароль, ожидаем код 200 и возвращается ID")
    public void courierAuthWithCorrectPasswordLogin() {
        NewCourier newCourier = getCourierRequestAllRequiredField();

        given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(newCourier)
                .post(COURIER);

        LoginForCourier loginForCourier = LoginTestData.correctLogin(CourierTestData.getCourierRequestAllRequiredField());

        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(loginForCourier)
                .post(COURIER_LOGIN);
        response.then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера с несуществующим логином")
    @Description("Проверяем авторизацию с несуществующим логином, ожидаем сообщение с ошибкой")
    public void courierAuthWithNonexistentLogin() {
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(invalidLoginPassword())
                .post(COURIER_LOGIN);
        response.then().statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера без логина или пароля")
    @Description("Проверяем авторизацию с незаполненным логином или паролем, ожидаем ошибку")
    public void courierAuthWithoutRequiredField() {
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(requestWithoutRequiredField())
                .post(COURIER_LOGIN);
        response.then().statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}
