import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static SetUp.SetUp.getBaseUri;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetTheOrderListTest {
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверяем получение списка заказов, ожидаем список заказов и код ответа 200")
    public void getListOrder(){
        Response response = given()
                .baseUri(getBaseUri())
                .header("Content-type", "application/json")
                .get("orders");
        response.then().statusCode(200)
                .and()
                .assertThat().body("orders", notNullValue());

    }
}
