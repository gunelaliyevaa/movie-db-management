import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String USER_DATABASE_FILE = "userDatabase.txt";

  private String username;
  private String password;
  private List<Movie> watchlist;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.watchlist = new ArrayList<>();
  }

  public String getUsername() {
    return username;
  }

  public List<Movie> getWatchlist() {
    return watchlist;
  }

  public String addToWatchlist(Movie movie) {
    if (!watchlist.contains(movie)) {
      watchlist.add(movie);
      return "Movie added to your watchlist";
    }
    return "Movie already exists in the watchlist";
  }

  public boolean removeFromWatchlist(Movie movie) {
    return watchlist.remove(movie);
  }
}