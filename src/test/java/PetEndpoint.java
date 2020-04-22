import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.SC_OK;

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
                .statusCode(SC_OK);
    }
    public ValidatableResponse uploadImage(long petId) {
        return given()
                .contentType("multipart/form-data")
                .multiPart(new File("/Users/maryna/Downloads/bat.jpg"))
                .when()
                .post(UPLOAD_IMAGE, petId)
                .then()
                .statusCode(SC_OK);
    }
    public ValidatableResponse updatePetByDataForm(long petId) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .param("name", "Spike")
                .param("status", "available")
                .when()
                .post(UPDATE_PET_BY_DATA_FORM, petId)
                .then()
                .body("message", is(String.valueOf(petId)));
        //.statusCode(SC_OK);
    }
    public ValidatableResponse getPet(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body( "id", anyOf(is(petId), is(Status.AVAILABLE)))
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
                .statusCode(SC_OK);
    }

    public ValidatableResponse getPetByStatus(String status) {
        return given()
                .when()
                .param("status", status)
                .get(GET_PET_BY_STATUS)
                .then()
                .body("status", everyItem(equalTo("pending")))
                .statusCode(SC_OK);
    }
}