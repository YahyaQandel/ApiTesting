package movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieController {

    public static Movie getMovieFromMoviesList(List<Movie> moviesList, String movieName){
         return moviesList.stream().filter(e -> e.getTitle().equals(movieName)).collect(Collectors.toList()).get(0);

    }
}
