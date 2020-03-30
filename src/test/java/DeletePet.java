import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DeletePet {

    @Test
    public void deletePet() {

        RequestSpecification request = RestAssured.given();

        Response response = request.delete("https://petstore.swagger.io/v2/pet/8888888");

        int code = response.getStatusCode();

        Assert.assertEquals(code, 200);
    }

}



//implement 5 new tests per each endpoint for Pet entity
//also commit/push your hometask to git repo