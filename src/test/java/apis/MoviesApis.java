package apis;
import movie.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MoviesApis {
    private final static String API_URL = "https://movie-database-imdb-alternative.p.rapidapi.com/";
    private final static String API_HOST = "movie-database-imdb-alternative.p.rapidapi.com";
    private final static String API_KEY = "21e6f4f25dmsh8bbed3c079a43acp14bd7bjsnb34a3bcca5f8";
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
        for (HashMap<String, String> apiMovies : apiMovieList) {
            Movie movie = new Movie();
            movie.setYear(apiMovies.get("Year"));
            movie.setType(apiMovies.get("Type"));
            movie.setTitle(apiMovies.get("Title"));
            movie.setImdbID(apiMovies.get("imdbID"));
            movies.add(movie);
        }
        return movies;
    }
}
