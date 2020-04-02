import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PetTest{
    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }

    @Test
    public void getPetByStatusAvailable() {
        String status = "available";
        given()
                .log()
                .all()
                .when()
                .param("status", status)
                .get("/pet/findByStatus")
                .then()
                .log()
                .all()
                .body("status", (hasItem(status)))
                .statusCode(200);
    }

    @Test
    public void getPetByStatusSold() {
        String status = "sold";
        given()
                .log()
                .all()
                .when()
                .param("status", status)
                .get("/pet/findByStatus")
                .then()
                .log()
                .all()
                .body("status", (hasItem(status)))
                .statusCode(200);
    }

    @Test
    public void getFindByStatusPending() {
        String status = "pending";
        given()
                .log()
                .all()
                .when()
                .param("status", status)
                .get("/pet/findByStatus")
                .then()
                .log()
                .all()
                .body("status", (hasItem(status)))
                .statusCode(200);
    }

    @Test
    public void addNewPetToStore() {
        String body = "{\n" +
                "  \"id\": 8888888,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Charlie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        given()
                .log()
                .all()
                .body(body)
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .body("name", is("Charlie"))
                .statusCode(200);
    }

    @Test
    public void getPetById() {
        int id = 1;
        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body("id", (is(id)))
                .statusCode(200);
    }

    @Test
    public void updatePetByDataForm() {
        //String id = RandomStringUtils.randomNumeric(4);
        String id = "8888888";
        given()
                .log()
                .all()
                .contentType("application/x-www-form-urlencoded")
                .param("name", "Charlie2")
                .param("status", "available")
                .when()
                .post("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body("message", is(id))
                .statusCode(200);
    }

    @Test
    public void updateExistingPet() {
        String body = "{\n" +
                "  \"id\": 8888888,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Charlie3\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";


        given()
                .log()
                .all()
                .body(body)
                .when()
                .put("/pet")
                .then()
                .log()
                .all()
                .body("name", is("Charlie3"))
                .statusCode(200);
    }

    @Test
    public void deletePetById() {
        String id = "8888888";
        given()
                .log()
                .all()
                .when()
                .delete("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body("message", is(id))
                .statusCode(200);
    }
}