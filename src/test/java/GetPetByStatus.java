import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

/* public class GetPetByStatus{

   @Test
    public void findPetByStatus(){
        String status = "available";

        given()
                .log().all()
                .baseUri("https://petstore.swagger.io")
                .when()
                .get("/v2/pet/findByStatus/", status)
                .then()
                .log().all()
                .statusCode(200);
    }
}

*/

public class GetPetByStatus {

    @org.testng.annotations.Test
    public void getPetByStatus() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.getOrDefault("status", "pending");

        request.body(json.toJSONString());

        Response response = request.get("https://petstore.swagger.io/v2/pet");

        int code = response.getStatusCode();

        Assert.assertEquals(code, 200);
    }

}
