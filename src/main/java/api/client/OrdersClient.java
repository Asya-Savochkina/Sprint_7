package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.NewOrder;
import static config.Config.ORDERS_ROOT;
import static config.Config.getBaseUri;
import static io.restassured.RestAssured.given;

public class OrdersClient {

    @Step("Запрос на создание нового заказа")
    public Response getCorrectNewOrder(NewOrder newOrder) {
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(getBaseUri())
                .body(newOrder)
                .post(ORDERS_ROOT);
        response.then();
        return  response;
    }

    @Step("Запрос на получение списка заказов")
    public Response getAllOrders() {
        Response response = given()
                .baseUri(getBaseUri())
                .header("Content-type", "application/json")
                .get("ORDERS_ROOT");
        response.then();
        return response;
    }
}


