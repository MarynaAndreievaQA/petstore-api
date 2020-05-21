package endPoints;

import io.restassured.response.ValidatableResponse;
import models.Pet;
import models.Status;
import net.thucydides.core.annotations.Step;
import org.hamcrest.CoreMatchers;

import static org.hamcrest.CoreMatchers.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.containsString;

import java.io.File;

public class PetEndpoint extends Endpoints {

    @Step
    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .body("name", CoreMatchers.is(pet.getName()))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse uploadImage(long petId, String fileName) {

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return given()
                .contentType("multipart/form-data")
                .multiPart(file)
                .when()
                .post(UPLOAD_IMAGE, petId)
                .then()
                .body("message", allOf(containsString("File uploaded"), containsString(file.getName())))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse updatePetByDataForm(Pet pet) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .param("id", pet.getId())
                .param("name", pet.getName())
                .param("status", pet.getStatus())
                .when()
                .post(UPDATE_PET_BY_DATA_FORM, pet.getId())
                .then()
                .body("name", CoreMatchers.is(pet.getName()))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getPet(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body("id", is(petId))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);

    }

    @Step
    public ValidatableResponse updatePet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .put(UPDATE_PET_BY_ID)
                .then()
                .body("name", CoreMatchers.is(pet.getName()))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getPetByStatus(Status status) {
        return given()
                .when()
                .param("status", status)
                .get(GET_PET_BY_STATUS)
                .then()
                .body("status", everyItem(equalTo(status.toString())))
                .statusCode(SC_OK);
    }
}