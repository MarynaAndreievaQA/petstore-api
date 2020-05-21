package tests.order;

import endPoints.PetEndpoint;
import endPoints.StoreEndpoint;
import models.Order;
import models.Pet;
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
    private PetEndpoint petEndpoint;
    private int createdPetId;

    @Steps
    private StoreEndpoint storeEndpoint;
    private int placedOrderId;

    @Before
    public void createPet() {
        Pet pet = Pet.builder()
                .id("3")
                .name("chupacabra")
                .status(Status.AVAILABLE)
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");

        Order order = Order.builder()
                .id("3")
                .petId(createdPetId)
                .shipDate(System.currentTimeMillis())
                .status(Status.PLACED)
                .complete(false)
                .build();
        ValidatableResponse responseOrder = storeEndpoint.placeOrder(order);
        placedOrderId = responseOrder.extract().path("id");
    }

    @After
    public void deleteOrder() {
        storeEndpoint.deleteOrder(placedOrderId);
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }


    @Test
    public void getOrderById() {
        storeEndpoint.getOrder(placedOrderId);
    }
}

