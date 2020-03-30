import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPetTest {

    @Test
    public void getPetById(){
        int id = 8888888;
        given()
                .log().all()
                .baseUri("https://petstore.swagger.io")
                .when()
                .get("/v2/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }

}


//implement 5 new tests per each endpoint for Pet entity
//also commit/push your hometask to git repo