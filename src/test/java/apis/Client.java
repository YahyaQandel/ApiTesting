package apis;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;

import static io.restassured.RestAssured.given;

public class Client {
    private final static String API_URL = "https://movie-database-imdb-alternative.p.rapidapi.com/";
    private final static String API_HOST = "movie-database-imdb-alternative.p.rapidapi.com";
    private final static String API_KEY = "21e6f4f25dmsh8bbed3c079a43acp14bd7bjsnb34a3bcca5f8";

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
