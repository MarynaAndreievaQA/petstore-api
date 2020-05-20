package endPoints;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.Order;
import models.Status;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.CoreMatchers.*;
import static org.apache.http.HttpStatus.SC_OK;

public class StoreEndpoint {
    private final static String PLACE_ORDER = "/store/order";
    private final static String GET_ORDER_BY_ID = "/store/order/{orderId}";
    private final static String DELETE_ORDER_BY_ID = "/store/order/{orderId}";
    private final static String INVENTORIES_BY_STATUS = "/store/inventory";

    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    @Step
    public ValidatableResponse placeOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(PLACE_ORDER)
                .then()
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
