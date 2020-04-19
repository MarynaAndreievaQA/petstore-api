import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreatePetTest {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long createdPetId;

    @Test
    public void createPet() {
        Pet pet = new Pet("0", "chupacabra", "available");
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}



