import java.io.Serializable;
import java.util.Objects;

public class Movie implements Serializable {
  private static final long serialVersionUID = 1L;
  private final String title;
  private final String director;
  private final int releaseYear;
  private final int runningTime;

  public Movie(String title, String director, int releaseYear, int runningTime) {
    validateInput(title, director, releaseYear, runningTime);

    this.title = title;
    this.director = director;
    this.releaseYear = releaseYear;
    this.runningTime = runningTime;
  }

  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }

  public int getReleaseYear() {
    return releaseYear;
  }

  public int getRunningTime() {
    return runningTime;
  }

  public String toCSVString() {
    return String.format("%s,%s,%d,%d", escapeCsv(title), escapeCsv(director), releaseYear, runningTime);
  }

  @Override
  public String toString() {
    return String.format("Title: %s , Director: %s , ReleaseYear: %d , RunningTime: %d",
        title, director, releaseYear, runningTime);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Movie movie = (Movie) obj;
    return releaseYear == movie.releaseYear &&
        runningTime == movie.runningTime &&
        Objects.equals(title, movie.title) &&
        Objects.equals(director, movie.director);
  }

  private static String escapeCsv(String value) {
    if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
      return "\"" + value.replace("\"", "\"\"") + "\"";
    }
    return value;
  }

  private static void validateInput(String title, String director, int releaseYear, int runningTime) {
    if (!isValidString(title) || !isValidString(director)) {
      throw new IllegalArgumentException("Title and director cannot be null or empty");
    }
    if (!isValidReleaseYear(releaseYear)) {
      throw new IllegalArgumentException("Invalid release year. Must be between 1866 and 2023");
    }
    if (runningTime <= 0) {
      throw new IllegalArgumentException("Running time must be greater than 0");
    }
  }

  private static boolean isValidString(String str) {
    return str != null && !str.isEmpty();
  }

  private static boolean isValidReleaseYear(int releaseYear) {
    return releaseYear >= 1866 && releaseYear <= 2023;
  }
}
