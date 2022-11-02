import api.client.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetTheOrderListTest {
    OrdersClient ordersClient;
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверяем получение списка заказов, ожидаем список заказов и код ответа 200")
    public void getListOrder(){
        ordersClient.getAllOrders()
        .then().statusCode(200)
                .and()
                .assertThat().body("orders", notNullValue());
    }
}
