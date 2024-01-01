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
  }
}