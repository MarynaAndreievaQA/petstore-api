package endPoints;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class Endpoints {

    protected final static String CREATE_PET = "/pet";
    protected final static String GET_PET_BY_ID = "/pet/{petId}";
    protected final static String DELETE_PET_BY_ID = "/pet/{petId}";
    protected final static String UPDATE_PET_BY_ID = "/pet";
    protected final static String UPDATE_PET_BY_DATA_FORM = "/pet/{petId}";
    protected final static String GET_PET_BY_STATUS = "/pet/findByStatus";
    protected final static String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";
    protected final static String PLACE_ORDER = "/store/order";
    protected final static String GET_ORDER_BY_ID = "/store/order/{orderId}";
    protected final static String DELETE_ORDER_BY_ID = "/store/order/{orderId}";
    protected final static String INVENTORIES_BY_STATUS = "/store/inventory";


    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    public RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }
}

