package apis;
import movie.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MoviesApis {
    private final static String API_URL = "https://movie-database-alternative.p.rapidapi.com/";
    private final static String API_HOST = "movie-database-alternative.p.rapidapi.com";
    private final static String API_KEY = "d7729c4c68msh79e456503085291p10ed60jsn3190a78995a9";
    Client apiClient;
    /**
     @param String movie name
     @return list of matching movies by name
     */
    public MoviesApis(){
     apiClient  = new Client(API_URL,API_HOST,API_KEY);
    }
    public List<HashMap<String,String>> searchMoviesByName(String movieName){
        return apiClient.getJsonResponse(String.format("?s=%s",movieName)).getList("Search");
    }
    /**
     @param String movie id
     @return movie details object
     */
    public Movie getMovieByID(String movieID){
        return apiClient.getResponse(String.format("?r=json&i=%s",movieID)).as(Movie.class);
    }

}
