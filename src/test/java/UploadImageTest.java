import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UploadImageTest {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long createdPetId;

    @Before
    public void createPet() {
        Pet pet = new Pet("0", "ChuckNorris", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void uploadJpg() {
        petEndpoint.uploadImage(createdPetId, "bat.jpg");
    }

    @Test
    public void uploadDocx() {
        petEndpoint.uploadImage(createdPetId, "Nov2016.docx");
    }

    @Test
    public void uploadInfectedFile() {
        petEndpoint.uploadImage(createdPetId, "virus.txt");
    }

    @Test
    public void uploadWebm() {
        petEndpoint.uploadImage(createdPetId, "Iownit.webm");
    }

    @Test
    public void uploadHugeFile() {
        petEndpoint.uploadImage(createdPetId, "googlechrome.dmg");
    }

    @Test
    public void uploadJson() {
        petEndpoint.uploadImage(createdPetId, "response_1586600645432.json");
    }

    @Test
    public void uploadMp4() {
        petEndpoint.uploadImage(createdPetId, "zoom_0.mp4");
    }

    @Test
    public void uploadEmptyFile() {
        petEndpoint.uploadImage(createdPetId, "Empty_file");
    }

    @Test
    public void uploadGif() {
        petEndpoint.uploadImage(createdPetId, "hug.gif");
    }

    @Test
    public void uploadPdf() {
        petEndpoint.uploadImage(createdPetId, "id_sample.pdf");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }


}