package movie;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class MovieController {

    public static Movie getMovieFromMoviesList(List<Movie> moviesList, String movieName){
         return moviesList.stream().filter(e -> e.getTitle().equals(movieName)).collect(Collectors.toList()).get(0);
    }

    public static List<Movie> mapArrayListToMovieList(List<HashMap<String,String>> apiMovieList)  throws IOException {
        List<Movie> movies = new ArrayList<>(); ;
        ObjectMapper mapper = new ObjectMapper();
        for (HashMap<String, String> apiMovie : apiMovieList) {
            Movie movie = mapper.readValue(new ObjectMapper().writeValueAsString(apiMovie), Movie.class);
            movies.add(movie);
        }
        return movies;
    }
}
