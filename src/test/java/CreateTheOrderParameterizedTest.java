import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import objects.NewOrder;

import static SetUp.SetUp.getBaseUri;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.OrderTestData.getOrderRequestAllField;

@RunWith(Parameterized.class)
public class CreateTheOrderParameterizedTest {
        private String[] color;

        public CreateTheOrderParameterizedTest(String[] color) {
            this.color = color;
        }


        @Parameterized.Parameters
        public static Object[][] getColorForOrder() {
            return new Object[][]{
                    {new String[]{"BLACK"}},
                    {new String[]{"GREY"}},
                    {new String[]{"BLACK", "GREY"}},
                    {new String[]{}},
            };
        }

        @Test
        @DisplayName("Создание заказа")
        @Description("Позитивный сценарий: создание заказа с корректными данными")
        public void createOrder() {
            NewOrder newOrder = new NewOrder();
            newOrder.setFirstName(getOrderRequestAllField().getFirstName());
            newOrder.setLastName(getOrderRequestAllField().getLastName());
            newOrder.setAddress(getOrderRequestAllField().getAddress());
            newOrder.setMetroStation(getOrderRequestAllField().getMetroStation());
            newOrder.setPhone(getOrderRequestAllField().getPhone());
            newOrder.setRentTime(getOrderRequestAllField().getRentTime());
            newOrder.setDeliveryDate(getOrderRequestAllField().getDeliveryDate());
            newOrder.setComment(getOrderRequestAllField().getComment());
            newOrder.setColor(color);

            int track = given()
                    .header("Content-type", "application/json")
                    .baseUri(getBaseUri())
                    .body(newOrder)
                    .post("orders")
                    .then()
                    .assertThat()
                    .statusCode(201)
                    .and()
                    .body("track", notNullValue())
                    .extract()
                    .path("track");

        }
}
