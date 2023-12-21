import java.util.ArrayList;
import java.util.List;

public class MovieDatabase {
    private List<Movie> movies;

    public MovieDatabase() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
            System.out.println("Movie added to the database: " + movie.getTitle());
        } else {
            throw new IllegalArgumentException("Movie already exists in the database.");
        }
    }
}