import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.containsString;

import java.io.File;

public class PetEndpoint {

    private final static String CREATE_PET = "/pet";
    private final static String GET_PET_BY_ID = "/pet/{petId}";
    private final static String DELETE_PET_BY_ID = "/pet/{petId}";
    private final static String UPDATE_PET_BY_ID = "/pet";
    private final static String UPDATE_PET_BY_DATA_FORM = "/pet/{petId}";
    private final static String GET_PET_BY_STATUS = "/pet/findByStatus";
    private final static String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";

    static {
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL));
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .body("name", is("ChuckNorris"))
                .statusCode(SC_OK);
    }

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

    public ValidatableResponse updatePetByDataForm(Pet pet) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .param("name", pet.getName())
                .param("status", pet.getStatus())
                .when()
                .post(UPDATE_PET_BY_DATA_FORM, pet.getId())
                .then()
                .body("message", is(String.valueOf(pet.getId())))
                .statusCode(SC_OK);
    }

    public ValidatableResponse getPet(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body("id", is(petId))
                .statusCode(SC_OK);
    }

    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);

    }

    public ValidatableResponse updatePet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .put(UPDATE_PET_BY_ID)
                .then()
                .body("name", is(pet.getName()))
                .statusCode(SC_OK);
    }

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