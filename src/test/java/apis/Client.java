package apis;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Client {
    private String API_URL ;
    public Client(String API_URL){
        this.API_URL = API_URL;
    }

    private ExtractableResponse request(String urlParams,HashMap<String,String> ...headersMap){
        HashMap<String,String> requestHeader = new HashMap<>();
        if (headersMap.length > 0){
            requestHeader = headersMap[0];
        }
        return (ExtractableResponse) given()
                .contentType(ContentType.JSON)
                .headers(requestHeader)
                .when()
                .get(String.format("%s%s",API_URL,urlParams))
                .then()
                .statusCode(200)
                .extract().response();
    }

    @SafeVarargs
    public final JsonPath getJsonResponse(String urlParam, HashMap<String, String>... requestHeaders){
        return this.request(urlParam,requestHeaders).response().getBody().jsonPath();
    }

    @SafeVarargs
    public final ExtractableResponse<?> getResponse(String urlParam, HashMap<String, String>... requestHeaders){
        return this.request(urlParam,requestHeaders);
    }

}
