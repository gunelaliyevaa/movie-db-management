import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabase {
  private List<Movie> movies;
  private final String movieDatabaseFile;

  public MovieDatabase(String movieDatabaseFile) {
    this.movies = new ArrayList<>();
    this.movieDatabaseFile = movieDatabaseFile;
    loadMoviesFromCSV();
  }

  public void addMovie(Movie movie) {
    if (!movies.contains(movie)) {
      movies.add(movie);
      saveMoviesToCSV();
      System.out.println("Movie added to the database: " + movie.getTitle());
    } else {
      throw new IllegalArgumentException("Movie already exists in the database.");
    }
  }

  public boolean removeMovie(String title) {
    boolean removed = movies.removeIf(movie -> movie.getTitle().equals(title));
    if (removed) {
      saveMoviesToCSV();
    }
    return removed;
  }

  public Movie getMovieByTitle(String title) {
    return movies.stream()
        .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Movie not found in the database."));
  }

  public void displayMovies() {
    movies.forEach(System.out::println);
  }

  public List<Movie> getMovies() {
    return new ArrayList<>(movies);
  }

  private void loadMoviesFromCSV() {
    try (BufferedReader reader = new BufferedReader(new FileReader(movieDatabaseFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        String title = data[0];
        String director = data[1];
        int releaseYear = Integer.parseInt(data[2]);
        int runningTime = Integer.parseInt(data[3]);

        Movie movie = new Movie(title, director, releaseYear, runningTime);
        movies.add(movie);
      }
    } catch (IOException e) {
      handleIOException("Error loading movies from CSV file", e);
    }
  }

  private void saveMoviesToCSV() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(movieDatabaseFile))) {
      for (Movie movie : movies) {
        writer.write(movie.toCSVString());
        writer.newLine();
      }
    } catch (IOException e) {
      handleIOException("Error saving movies to CSV file", e);
    }
  }

  private void handleIOException(String message, IOException e) {
    System.err.println(message + ": " + e.getMessage());
  }

}
