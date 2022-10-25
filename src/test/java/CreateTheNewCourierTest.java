import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import objects.LoginForCourier;
import org.junit.AfterClass;
import org.junit.Test;
import testdata.LoginTestData;

import static SetUp.SetUp.getBaseUri;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.CourierTestData.getCourierRequestAllRequiredField;
import static testdata.CourierTestData.getCourierRequestWithoutRequiredField;

public class CreateTheNewCourierTest {
    public static final String COURIER_LOGIN = "courier/login";
    private static final String COURIER = "courier";

    private static final String EXPECTED_MESSAGE_400 = "Недостаточно данных для создания учетной записи";
    private static final String EXPECTED_MESSAGE_409 = "Этот логин уже используется. Попробуйте другой.";


    @Test
    @DisplayName("Корректное создание курьера")
    @Description("Проверяем позитивный сценарий создания курьера. Ожидаем, что возвращается код ответа 201")
    public void createNewCourierForCode() {

        Response response;
        response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(getCourierRequestAllRequiredField())
                .post(COURIER);
        response.then().statusCode(201);
    }

    @Test
    @DisplayName("Корректное создание курьера")
    @Description("Проверяем позитивный сценарий создания курьера. Ожидаем, что возвращается ok: true")
    public void createNewCourierForMessage() {

        Response response;
        response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(getCourierRequestAllRequiredField())
                .post(COURIER);
        response.then().assertThat().body("ok", equalTo(true));

    }

    @Test
    @DisplayName("Создание дубля курьера")
    @Description("При попытке создать курьера с повторяющимся логином ожидаем, что возвращается код 409")
    public void createDuplicateCourierForCode() {
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(getCourierRequestAllRequiredField())
                .post(COURIER);
        response.then().statusCode(409);
    }

    @Test
    @DisplayName("Создание дубля курьера")
    @Description("При попытке создать курьера с повторяющимся логином ожидаем, что в теле ответа message \"Этот логин уже используется. Попробуйте другой.\"")
    public void createDuplicateCourierForMessage() {
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(getCourierRequestAllRequiredField())
                .post(COURIER);
        response.then().assertThat().body("message", equalTo(EXPECTED_MESSAGE_409));
    }


    @Test
    @DisplayName("Некорректное создание курьера")
    @Description("Создание курьера без одного из обязательных полей, ожидаем код ответа 400")
    public void createCourierWithoutRequiredFieldForCode(){
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(getCourierRequestWithoutRequiredField())
                .post(COURIER);
        response.then().statusCode(400);
    }

    @Test
    @DisplayName("Некорректное создание курьера")
    @Description("Создание курьера без одного из обязательных полей, ожидаем в ответе message \"Недостаточно данных для создания учетной записи\"")
    public void createCourierCopyWithoutRequiredFieldForMessage(){
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(getCourierRequestWithoutRequiredField())
                .post(COURIER);
        response.then().assertThat().body("message", equalTo(EXPECTED_MESSAGE_400));
    }

    @AfterClass
    public static void setId() {

        LoginForCourier loginForCourier = LoginTestData.from(getCourierRequestAllRequiredField());

        int id = given()
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

        given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .delete(COURIER + '/' + id);
    }
}
