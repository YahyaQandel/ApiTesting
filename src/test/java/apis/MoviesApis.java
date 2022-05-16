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
    public List<Movie> searchMoviesByName(String movieName){
        List<HashMap<String,String>> apiResponseMovies = apiClient.getJsonResponse(String.format("?s=%s",movieName)).getList("Search");
        return mapArrayListToMovieList(apiResponseMovies);
    }

    public Movie getMovieByID(String movieID){
        return apiClient.getResponse(String.format("?r=json&i=%s",movieID)).as(Movie.class);
    }

    private List<Movie> mapArrayListToMovieList(List<HashMap<String,String>> apiMovieList){
        List<Movie> movies = new ArrayList<>(); ;
        for (HashMap<String, String> apiMovie : apiMovieList) {
            Movie movie = new Movie();
            movie.setYear(apiMovie.get("Year"));
            movie.setType(apiMovie.get("Type"));
            movie.setTitle(apiMovie.get("Title"));
            movie.setImdbID(apiMovie.get("imdbID"));
            movies.add(movie);
        }
        return movies;
    }
}
