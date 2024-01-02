import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class MovieApp extends JFrame {
  private User currentUser;
  private MovieDatabase movieDatabase = new MovieDatabase("movieDatabaseFile.csv");

  public MovieApp() {
    initializeLookAndFeel();

    setTitle("Movie Application");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(Color.white);

    JButton loginButton = createStyledButton("Login");
    JButton registerButton = createStyledButton("Register");

    loginButton.addActionListener(e -> showFrame(new LoginFrame(), "Login"));
    registerButton.addActionListener(e -> showFrame(new RegistrationFrame(), "User Registration"));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(50, 0, 50, 0);
    panel.add(loginButton, gbc);

    gbc.gridy = 1;
    panel.add(registerButton, gbc);

    add(panel);
    setVisible(true);
  }

  private void initializeLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
        | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  private JButton createStyledButton(String text) {
    JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(400, 90));
    button.setFont(new Font("Arial", Font.BOLD, 20));
    return button;
  }

  private void showFrame(JFrame frame, String title) {
    frame.setTitle(title);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
      JPanel panel = new JPanel();

      JLabel usernameLabel = new JLabel("Username:");
      JLabel passwordLabel = new JLabel("Password:");
      usernameField = new JTextField(20);
      passwordField = new JPasswordField(20);
      JButton loginButton = new JButton("Login");

      loginButton.addActionListener(e -> handleLogin());

      GroupLayout layout = new GroupLayout(panel);
      panel.setLayout(layout);
      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);

      layout.setHorizontalGroup(layout.createSequentialGroup()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addComponent(usernameLabel)
              .addComponent(passwordLabel))
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addComponent(usernameField)
              .addComponent(passwordField)
              .addComponent(loginButton)));

      layout.setVerticalGroup(layout.createSequentialGroup()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
              .addComponent(usernameLabel)
              .addComponent(usernameField))
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
              .addComponent(passwordLabel)
              .addComponent(passwordField))
          .addComponent(loginButton));

      add(panel);
    }

    private void handleLogin() {
      String username = usernameField.getText();
      char[] passwordChars = passwordField.getPassword();
      String password = new String(passwordChars);

      try {
        currentUser = User.login(username, password);
        showMovieManagementFrame(currentUser);
        dispose();
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private class RegistrationFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegistrationFrame() {
      JPanel panel = new JPanel();
      JLabel usernameLabel = new JLabel("Username:");
      JLabel passwordLabel = new JLabel("Password:");
      usernameField = new JTextField(100);
      passwordField = new JPasswordField(100);
      JButton registerButton = new JButton("Register");
      registerButton.addActionListener(e -> handleRegistration());

      GroupLayout layout = new GroupLayout(panel);
      panel.setLayout(layout);
      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);

      layout.setHorizontalGroup(layout.createSequentialGroup()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addComponent(usernameLabel)
              .addComponent(passwordLabel))
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
              .addComponent(usernameField)
              .addComponent(passwordField)
              .addComponent(registerButton)));

      layout.setVerticalGroup(layout.createSequentialGroup()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
              .addComponent(usernameLabel)
              .addComponent(usernameField))
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
              .addComponent(passwordLabel)
              .addComponent(passwordField))
          .addComponent(registerButton));

      add(panel);
    }

    private void handleRegistration() {
      String username = usernameField.getText();
      char[] passwordChars = passwordField.getPassword();
      String password = new String(passwordChars);

      try {
        String result = User.register(username, password);
        JOptionPane.showMessageDialog(null, result);
        dispose();
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    private void showMovieManagementFrame(User currentUser) {
      if (currentUser != null) {
        showFrame(new MovieManagementFrame(currentUser), "Movie Management");
      } else {
        JOptionPane.showMessageDialog(null, "User not logged in", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public class MovieManagementFrame extends JFrame {
    private User currentUser;

    public MovieManagementFrame(User currentUser) {
      this.currentUser = currentUser;
      setTitle("Movie Management");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLocationRelativeTo(null);

      JLabel headerLabel = new JLabel("Welcome to our 'Online Movie Database Management System' Project");
      headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
      headerLabel.setHorizontalAlignment(JLabel.CENTER);

      JButton browseButton = createStyledButton("Browse Movies");
      JButton watchlistButton = createStyledButton("Watchlist");
      JButton addButton = createStyledButton("Add Movie");
      JButton removeButton = createStyledButton("Remove Movie");

      setFixedButtonSize(browseButton);
      setFixedButtonSize(watchlistButton);
      setFixedButtonSize(addButton);

      setFixedButtonSize(removeButton);

      browseButton.addActionListener(e -> browseMovies());
      watchlistButton.addActionListener(e -> showWatchlist());
      addButton.addActionListener(e -> showAddMovieForm());

      removeButton.addActionListener(e -> removeMovie());

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridBagLayout());

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      buttonPanel.add(headerLabel, gbc);

      gbc.gridy = 1;
      buttonPanel.add(browseButton, gbc);

      gbc.gridy = 2;
      buttonPanel.add(watchlistButton, gbc);

      gbc.gridy = 3;
      buttonPanel.add(addButton, gbc);

      gbc.gridy = 4;
      buttonPanel.add(removeButton, gbc);

      JPanel containerPanel = new JPanel(new GridBagLayout());
      containerPanel.add(buttonPanel);

      add(containerPanel, BorderLayout.CENTER);
    }

    private void setFixedButtonSize(JButton button) {
      button.setPreferredSize(new Dimension(200, 50));
      button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void showAddMovieForm() {
      MovieFormFrame movieForm = new MovieFormFrame(this, "Add Movie");
      movieForm.setVisible(true);
    }

    private void browseMovies() {
      if (movieDatabase != null) {
        List<Movie> movies = movieDatabase.getMovies();
        displayMoviesWithAddToWatchlist(movies);
      } else {
        JOptionPane.showMessageDialog(null, "MovieDatabase not initialized", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void displayMoviesWithAddToWatchlist(List<Movie> movies) {
    JPanel moviePanel = new JPanel();
    moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

    if (!movies.isEmpty()) {
      for (Movie movie : movies) {
        JPanel movieInfoPanel = new JPanel();
        movieInfoPanel.setLayout(new BorderLayout());

        JLabel movieLabel = new JLabel(movie.toString());

        JButton addToWatchlistButton = new JButton("Add to Watchlist");
        addToWatchlistButton.addActionListener(e -> addToWatchlist(movie));

        movieInfoPanel.add(movieLabel, BorderLayout.CENTER);
        movieInfoPanel.add(addToWatchlistButton, BorderLayout.EAST);

        moviePanel.add(movieInfoPanel);
      }
    } else {
      JOptionPane.showMessageDialog(null, "No movies found", "Movie Database", JOptionPane.INFORMATION_MESSAGE);
    }

    JButton sortButton = new JButton("Sort Movies");
    sortButton.addActionListener(e -> sortMovies(movies));
    moviePanel.add(sortButton); // Add the button at the end of the panel

    JScrollPane scrollPane = new JScrollPane(moviePanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    JOptionPane.showMessageDialog(this, scrollPane, "Movie Database", JOptionPane.INFORMATION_MESSAGE);
  }

  private void sortMovies(List<Movie> movies) {
    String[] options = { "Title", "Director", "Release Year", "Running Time" };
    String sortBy = (String) JOptionPane.showInputDialog(
        this,
        "Sort By:",
        "Sort Movies",
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);

    if (sortBy != null) {
      Comparator<Movie> comparator = getComparator(sortBy);
      if (comparator != null) {
        movies.sort(comparator);
      }

      // Redraw the movie list with sorted movies
      displayMoviesWithAddToWatchlist(movies);
    }
  }

  private void addToWatchlist(Movie movie) {
    if (currentUser != null) {
      String result = currentUser.addToWatchlist(movie);
      JOptionPane.showMessageDialog(null, result);
      currentUser.saveWatchlistToFile();
    } else {
      JOptionPane.showMessageDialog(null, "User not logged in", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private Comparator<Movie> getComparator(String sortBy) {
    switch (sortBy) {
      case "Title":
        return Comparator.comparing(Movie::getTitle);
      case "Director":
        return Comparator.comparing(Movie::getDirector);
      case "Release Year":
        return Comparator.comparingInt(Movie::getReleaseYear);
      case "Running Time":
        return Comparator.comparingInt(Movie::getRunningTime);
      default:
        return null;
    }
  }

  private void removeMovie() {
    String titleToRemove = promptForMovieName("Enter the title of the movie to remove from the database:");
    if (titleToRemove != null) {
      boolean removed = movieDatabase.removeMovie(titleToRemove);
      if (removed) {
        JOptionPane.showMessageDialog(null, "Movie removed from the database");
      } else {
        JOptionPane.showMessageDialog(null, "Movie not found in the database", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private String promptForMovieName(String message) {
    return JOptionPane.showInputDialog(null, message, "Enter Movie Name", JOptionPane.QUESTION_MESSAGE);
  }

  private void showWatchlist() {
    if (currentUser != null && !currentUser.getWatchlist().isEmpty()) {
      JPanel watchlistPanel = new JPanel();
      watchlistPanel.setLayout(new BoxLayout(watchlistPanel, BoxLayout.Y_AXIS));

      for (Movie movie : currentUser.getWatchlist()) {
        JPanel movieInfoPanel = new JPanel();
        movieInfoPanel.setLayout(new BorderLayout());

        JLabel movieLabel = new JLabel(movie.toString());
        JButton deleteButton = new JButton("Delete from Watchlist");
        deleteButton.addActionListener(e -> removeFromWatchlist(movie));

        movieInfoPanel.add(movieLabel, BorderLayout.CENTER);
        movieInfoPanel.add(deleteButton, BorderLayout.EAST);

        watchlistPanel.add(movieInfoPanel);
      }

      JScrollPane scrollPane = new JScrollPane(watchlistPanel);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      JOptionPane.showMessageDialog(null, scrollPane, "Your Watchlist", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "Watchlist is empty", "Watchlist", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private void removeFromWatchlist(Movie movie) {
    if (currentUser != null) {
      boolean removed = currentUser.removeFromWatchlist(movie);
      if (removed) {
        JOptionPane.showMessageDialog(null, "Movie removed from your watchlist");
        currentUser.saveWatchlistToFile();
      } else {
        JOptionPane.showMessageDialog(null, "Movie not found in your watchlist", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, "User not logged in", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

}