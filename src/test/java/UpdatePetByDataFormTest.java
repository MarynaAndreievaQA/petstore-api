import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdatePetByDataFormTest {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long createdPetId;
    private Pet pet;

    @Before
    public void createPet() {
        pet = new Pet("0", "ChuckNorris", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void updatePetByDataForm() {
        petEndpoint.updatePetByDataForm(pet);
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}

