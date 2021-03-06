package tests.pet;

import endPoints.PetEndpoint;
import models.Pet;
import models.Status;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)

public class GetPetByStatusTest {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;

    private final Status status;

    public GetPetByStatusTest(Status status) {
      this.status = status;
    }

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {Status.AVAILABLE},
                {Status.SOLD},
                {Status.PENDING},
        });
    }
    @Before
    public void createPet() {
        Pet pet = Pet.builder()
                .id("0")
                .name("chupacabra")
                .status(Status.AVAILABLE)
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void getPetByStatus() {
        petEndpoint.getPetByStatus(status);
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}




