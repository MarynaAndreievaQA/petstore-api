import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PetTest {
    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }

    @Test
    public void updatePetByDataForm() {

        int id = 888888;
        String str = Integer.toString(id);

        //Check if this Pet exists
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 404) {

            //Create New Pet
            String body = "{\n" +
                    "  \"id\": \"888888\",\n" +
                    "  \"category\": {\n" +
                    "    \"id\": 0,\n" +
                    "    \"name\": \"string\"\n" +
                    "  },\n" +
                    "  \"name\": \"Bobik\",\n" +
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
                    .body("name", is("Bobik"));

        } else if (response.getStatusCode() == 200) {

            //Update already Created Pet
            given()
                    .log()
                    .all()
                    .contentType("application/x-www-form-urlencoded")
                    .param("name", "Spike")
                    .param("status", "available")
                    .when()
                    .post("/pet/{id}", id)
                    .then()
                    .log()
                    .all()
                    .body("message", is(str));

            //Delete Pet
            given()
                    .when()
                    .delete("/pet/{id}", id)
                    .then()
                    .log()
                    .all()
                    .body("message", is(str))
                    .statusCode(200);
        }
    }


    @Test
        public void updateExistingPet () {
            int id = 555555;
            String str = Integer.toString(id);

            //Check if this Pet exists
            Response response = given().get("/pet/{id}", id);
            if (response.getStatusCode() == 404) {

                //Create New Pet
                String body = "{\n" +
                        "  \"id\": 555555,\n" +
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
                        .put("/pet")
                        .then()
                        .log()
                        .all()
                        .body("name", is("Charlie"))
                        .statusCode(200);
            } else if (response.getStatusCode() == 200) {

                //Update already Created Pet
                String body = "{\n" +
                        "  \"id\": 555555,\n" +
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

                //Delete Pet
                given()
                        .when()
                        .delete("/pet/{id}", id)
                        .then()
                        .log()
                        .all()
                        .body("message", is(str))
                        .statusCode(200);
            }
        }

        @Test
            public void deletePetById () {
            int id = 4444;
            String str = Integer.toString(id);

            //Check if this Pet exists
            Response response = given().get("/pet/{id}", id);
            if (response.getStatusCode() == 404) {

                //Create new Pet
                String body = "{\n" +
                        "  \"id\": 4444,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"John\",\n" +
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
                        .body("name", is("John"))
                        .statusCode(200);

                // Delete already Created Pet
                given()
                        .when()
                        .delete("/pet/{id}", id)
                        .then()
                        .log()
                        .all()
                        .body("message", is(str))
                        .statusCode(200);
            }
            else if (response.getStatusCode() == 200){
                // Delete already existing Pet
                given()
                        .when()
                        .delete("/pet/{id}", id)
                        .then()
                        .log()
                        .all()
                        .body("message", is(str))
                        .statusCode(200);
            }
        }

        @Test
        public void getPetByStatusAvailable () {
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
                    .body("status", everyItem(equalTo(status)))
                    .statusCode(200);
        }

        @Test
        public void getPetByStatusSold () {
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
                    .body("status", everyItem(equalTo(status)))
                    .statusCode(200);
        }

        @Test
        public void getFindByStatusPending () {
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
                    .body("status", everyItem(equalTo(status)))
                    .statusCode(200);
        }


        @Test
        public void addNewPetToStore () {
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

            //Delete Pet
            int id = 8888888;
            String str = Integer.toString(id);
            given()
                    .when()
                    .delete("/pet/{id}", id)
                    .then()
                    .log()
                    .all()
                    .body("message", is(str))
                    .statusCode(200)
                    .log()
                    .all();
        }

        @Test
        public void getPetById () {
            int id = 1;
            String str = Integer.toString(id);

            //Check if this Pet exists
            Response response = given().get("/pet/{id}", id);
            if (response.getStatusCode() == 404) {

                //Create new Pet
                String body = "{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"Parker\",\n" +
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
                        .body("name", is("Parker"))
                        .statusCode(200);

                // Delete already existing Pet
                given()
                        .when()
                        .delete("/pet/{id}", id)
                        .then()
                        .log()
                        .all()
                        .body("message", is(str))
                        .statusCode(200);

            }
            else if (response.getStatusCode() == 200) {
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


                // Delete already existing Pet
                given()
                        .when()
                        .delete("/pet/{id}", id)
                        .then()
                        .log()
                        .all()
                        .body("message", is(str))
                        .statusCode(200);

            }

        }
    }