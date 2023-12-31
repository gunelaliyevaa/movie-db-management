import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String USER_DATABASE_FILE = "userDatabase.csv";

  private final String username;
  private final String password;
  private final List<Movie> watchlist;
  private final String watchlistFile;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.watchlist = new ArrayList<>();
    this.watchlistFile = username + "_watchlist.csv";
  }

  public String getUsername() {
    return username;
  }

  public List<Movie> getWatchlist() {
    return new ArrayList<>(watchlist);
  }

  public String addToWatchlist(Movie movie) {
    if (!watchlist.contains(movie)) {
      watchlist.add(movie);
      saveWatchlistToFile();
      return "Movie added to watchlist";
    }
    return "Movie already exists in watchlist";
  }

  public boolean removeFromWatchlist(Movie movie) {
    boolean removed = watchlist.remove(movie);
    if (removed) {
      saveWatchlistToFile();
    }
    return removed;
  }

  public static User login(String username, String password) {
    List<User> users = readUsersFromFile();
    for (User user : users) {
      if (user.getUsername().equals(username) && user.authenticate(password)) {
        user.loadWatchlistFromFile();
        return user;
      }
    }
    throw new IllegalArgumentException("Invalid username or password");
  }

  public static String register(String username, String password) {
    List<User> users = readUsersFromFile();
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return "Username already exists. Choose a different username.";
      }
    }

    // Check if the password meets the minimum length requirement
    if (password.length() < 4) {
      return "Password must be at least 4 characters long.";
    }

    User newUser = new User(username, password);
    users.add(newUser);
    saveUsersToFile(users);

    return "Registration successful!";
  }

  public boolean authenticate(String password) {
    return this.password.equals(password);
  }

  private static List<User> readUsersFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATABASE_FILE))) {
      @SuppressWarnings("unchecked")
      List<User> userList = (List<User>) ois.readObject();
      return userList;
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

  private static void saveUsersToFile(List<User> users) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATABASE_FILE))) {
      oos.writeObject(users);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Error writing to the user database");
    }
  }

  public void saveWatchlistToFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(watchlistFile))) {
      oos.writeObject(watchlist);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Error writing to the watchlist file");
    }
  }

  private void loadWatchlistFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(watchlistFile))) {
      @SuppressWarnings("unchecked")
      List<Movie> watchlistFromFile = (List<Movie>) ois.readObject();
      watchlist.clear();
      watchlist.addAll(watchlistFromFile);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
