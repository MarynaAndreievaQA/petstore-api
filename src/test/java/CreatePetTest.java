import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CreatePetTest {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;

    @Test
    public void createPet() {
        Pet pet = new Pet("0", "ChuckNorris", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}



