import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatePet {

    @Test
    public void createPet() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("id", "8888888");
        json.put("name", "Fog");
        json.put("status", "available");

        request.body(json.toJSONString());

        Response response = request.post("https://petstore.swagger.io/v2/pet");

        int code = response.getStatusCode();

        Assert.assertEquals(code, 200);
    }

}



//implement 5 new tests per each endpoint for Pet entity
//also commit/push your hometask to git repo