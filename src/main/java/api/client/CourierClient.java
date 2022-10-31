package api.client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.LoginForCourier;
import model.NewCourier;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static config.Config.*;

public class CourierClient {
    @Step("Запрос на регистрацию нового курьера")
    public Response getCorrectNewCourier(NewCourier newCourier) {
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .and()
                .body(newCourier)
                .when()
                .post(COURIER);
        response.then();
        return  response;
    }

    @Step("Регистрация курьера с недостаточными данными")
    public Response getUncorrectCourier(NewCourier newCourier) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .and()
                .body(newCourier)
                .when()
                .post(COURIER);
    }

    @Step("Логин курьера")
    public Response login(LoginForCourier loginForCourier) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .and()
                .body(loginForCourier)
                .when()
                .post(COURIER_LOGIN);
    }

    @Step("Удаление курьера")
    public Response deleteCourier(LoginForCourier loginForCourier) {
        Integer id = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(loginForCourier)
                .post(COURIER_LOGIN)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");

        return given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .delete(COURIER + '/' + id);
    }
}