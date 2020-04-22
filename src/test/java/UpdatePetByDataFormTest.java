import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdatePetByDataFormTest {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long createdPetId;

    @Before
    public void createPet() {
        Pet pet = new Pet("0", "chupacabra", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

  @Test
    public void updatePetByDataForm() {
        petEndpoint.updatePetByDataForm(createdPetId);
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}

