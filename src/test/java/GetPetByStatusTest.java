import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetPetByStatusTest {

    PetEndpoint petEndpoint = new PetEndpoint();

    long createdPetId;

    @Before
    public void createPet() {
        int id = 0;
        String body = "{\n" +
                "  \"id\": \""+ id +"\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Scooby\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"pending\"\n" +
                "}";
        ValidatableResponse response = petEndpoint.createPet(body);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void getPetByStatus() {
      petEndpoint.getPetByStatus("pending");
        }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}


