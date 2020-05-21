package endPoints;

import io.restassured.response.ValidatableResponse;
import models.Order;
import models.Status;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.CoreMatchers.*;
import static org.apache.http.HttpStatus.SC_OK;

public class StoreEndpoint extends Endpoints {

    @Step
    public ValidatableResponse placeOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(PLACE_ORDER)
                .then()
               // .body("id", is(String.valueOf(order.getId())))
                .body("petId", is(order.getPetId()))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getOrder(int orderId) {
        return given()
                .when()
                .get(GET_ORDER_BY_ID, orderId)
                .then()
                .body("id", is(orderId))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deleteOrder(int orderId) {
        return given()
                .when()
                .delete(DELETE_ORDER_BY_ID, orderId)
                .then()
                .body("message", is(String.valueOf(orderId)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse inventoriesStatus(Status status) {
        return given()
                .when()
                .param("status", status)
                .get(INVENTORIES_BY_STATUS)
                .then()
                .body("sold", instanceOf(Integer.class), "pending", instanceOf(Integer.class), "available", instanceOf(Integer.class))
                .statusCode(SC_OK);
    }
}
