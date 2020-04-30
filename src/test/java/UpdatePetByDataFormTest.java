import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)

public class UpdatePetByDataFormTest {

    @Steps
    private PetEndpoint petEndpoint;
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

