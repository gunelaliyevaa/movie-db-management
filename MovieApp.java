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
}