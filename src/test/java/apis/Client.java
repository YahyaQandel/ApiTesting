package apis;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;

import static io.restassured.RestAssured.given;

public class Client {
    private String API_URL ;
    private String API_HOST;
    private String API_KEY ;

    public Client(String API_URL,String API_HOST,String API_KEY){
        this.API_URL = API_URL;
        this.API_HOST = API_HOST;
        this.API_KEY = API_KEY;
    }

    private ExtractableResponse<?> request(String urlParams){
        return given()
                .contentType(ContentType.JSON)
                .header("X-RapidAPI-Host", API_HOST)
                .header("X-RapidAPI-Key", API_KEY)
                .when()
                .get(String.format("%s%s",API_URL,urlParams))
                .then()
                .statusCode(200)
                .extract();
    }

    public JsonPath getJsonResponse(String urlParam){
        return this.request(urlParam).response().getBody().jsonPath();
    }

    public ExtractableResponse<?> getResponse(String urlParam){
        return this.request(urlParam);
    }

}
