import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)

public class UpdatePetTest {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;

    @Before
    public void createPet() {
        Pet pet = new Pet("0", "ChuckNorris", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void updatePet() {
        Pet pet = new Pet("0", "Chuck", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.updatePet(pet);
        createdPetId = response.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }

}

