import java.util.ArrayList;
import java.util.List;

public class MovieDatabase {
    private List<Movie> movies;

    public MovieDatabase(String s) {
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

    public void removeMovie(String title) {
        movies.removeIf(movie -> movie.getTitle().equals(title));
        System.out.println("Movie removed from the database: " + title);
    }

    public Movie getMovieByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found in the database."));
    }

    public void displayMovies() {
        movies.forEach(System.out::println);
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }
}