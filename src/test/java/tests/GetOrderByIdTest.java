package tests;

import endPoints.StoreEndpoint;
import models.Order;
import models.Status;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetOrderByIdTest {

    @Steps
    private StoreEndpoint storeEndpoint;
    private int placedOrderId;

    @Before
    public void placeOrder() {
        Order order = Order.builder()
                .id("0")
                .petId(69)
                .status(Status.PLACED)
                .build();
        ValidatableResponse response = storeEndpoint.placeOrder(order);
        placedOrderId = response.extract().path("id");
    }

    @After
    public void deleteOrder() {
        storeEndpoint.deleteOrder(placedOrderId);
    }

    @Test
    public void getOrderById() {
        storeEndpoint.getOrder(placedOrderId);
    }
}

