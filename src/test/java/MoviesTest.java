import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class MoviesTest {

    private final static String API_URL = "https://movie-database-alternative.p.rapidapi.com/";
    private final static String API_HOST = "movie-database-alternative.p.rapidapi.com";
    private final static String API_KEY = "d7729c4c68msh79e456503085291p10ed60jsn3190a78995a9";

    @Test
    public void assertThatSearchApiReturnsMatchingResults() {
        String movieName = "Avengers";
        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-RapidAPI-Host", this.API_HOST)
                .header("X-RapidAPI-Key", this.API_KEY)
                .when()
                .get(String.format("%s?s=%s",API_URL,movieName))
                .then()
                .statusCode(200)
                .extract().response();
        List<HashMap<String,String>> movies = response.getBody().jsonPath().getList("Search");
        HashMap<String,String> firstMovie = movies.get(0);
        assertTrue(firstMovie.get("Title").contains(movieName));
    }

    @Test
    public void assertThatPulpFictionMovieRuntimeStoredCorrectly(){
        String expectedPulpFictionMovieRuntime="154 min";
        String pulpFictionMovieName = "Pulp Fiction";
       // assert that pulp fiction movie stored has runtime 154 min
       /*
       sample api request by movie id to get movie details
       @Url: https://movie-database-imdb-alternative.p.rapidapi.com/?r=json&i=tt4154796
        */
    }
}
