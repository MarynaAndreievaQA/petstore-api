import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePet {

    @Test
    public void updatePet() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("id", "8888888");
        json.put("name", "F");
        json.put("status", "available");

        request.body(json.toJSONString());

        Response response = request.put("https://petstore.swagger.io/v2/pet/8888888");

        int code = response.getStatusCode();

        System.out.println("response" + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}



//implement 5 new tests per each endpoint for Pet entity
//also commit/push your hometask to git repo