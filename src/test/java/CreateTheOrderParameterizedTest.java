import api.client.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.NewOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testdata.OrderTestData;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateTheOrderParameterizedTest {
    NewOrder newOrder;
    OrdersClient ordersClient;

        @Parameterized.Parameters(name = "Тестовые данные: {0}")
        public static Object[][] getColorForOrder() {
            return new Object[][]{
                    {new String[]{"BLACK"}},
                    {new String[]{"GREY"}},
                    {new String[]{"BLACK", "GREY"}},
                    {new String[]{}},
            };
        }

    @Parameterized.Parameter(0)
    public String[] color;

    @Before
    public void setup() {
        newOrder = OrderTestData.createNewOrder(color);
        ordersClient = new OrdersClient();
    }
        @Test
        @DisplayName("Создание заказа")
        @Description("Позитивный сценарий: создание заказа с корректными данными")
        public void createOrder() {
        ordersClient.getCorrectNewOrder(OrderTestData.createNewOrder(color))
                .then().statusCode(201)
                .and()
                .body("track", notNullValue());
        }
}
