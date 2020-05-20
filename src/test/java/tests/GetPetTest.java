package tests;

import endPoints.PetEndpoint;
import models.Pet;
import models.Status;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetPetTest {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;

    @Before
    public void createPet() {
       // Pet pet = new Pet("0", "ChuckNorris", Status.AVAILABLE);
        Pet pet = Pet.builder()
                .id("0")
                .name("chupacabra")
                .status(Status.AVAILABLE)
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void getPetById() {
        petEndpoint.getPet(createdPetId);
    }
}

