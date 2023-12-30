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

  public static User login(String username, String password) {
    List<User> users = readUsersFromFile();
    for (User user : users) {
      if (user.getUsername().equals(username) && user.authenticate(password)) {
        return user; // Successfully logged in
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
      return (List<User>) ois.readObject();
    } catch (ClassNotFoundException | IOException e) {
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
}