import apis.MoviesApis;
import movie.Movie;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoviesTest {

    @Test
    public void assertThatSearchApiReturnsMatchingResults() {
        String movieName = "Avengers";
        Movie firstMovie = new MoviesApis().searchMoviesByName(movieName).get(0);
        assertTrue(firstMovie.getTitle().contains(movieName));
    }

    @Test
    public void assertThatPulpFictionMovieRuntimeStoredCorrectly(){
        String expectedPulpFictionMovieRuntime="154 min";
        String pulpFictionMovieName = "Pulp Fiction";
        List<Movie> moviesMatchesPulpFictionName = new MoviesApis().searchMoviesByName(pulpFictionMovieName);
        Movie pulpFiction =  moviesMatchesPulpFictionName.stream().filter(e -> e.getTitle().equals(pulpFictionMovieName)).collect(Collectors.toList()).get(0);
        Movie movieRetrievedFromApiResponse = new MoviesApis().getMovieByID(pulpFiction.getImdbID());
        assertEquals(expectedPulpFictionMovieRuntime,movieRetrievedFromApiResponse.getRuntime());
    }

    @Test
    public void testGetMovieByIDAndAssertOnItAsObject() {
        String movieId = "tt4154796";
        Movie testMovie = new Movie();
        testMovie.setTitle("Avengers: Endgame");
        testMovie.setType("movie");
        testMovie.setYear("2019");
        Movie retrievedMovie = new MoviesApis().getMovieByID(movieId);
        assertEquals(testMovie, retrievedMovie);
    }
}
