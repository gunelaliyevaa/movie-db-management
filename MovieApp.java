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
  }
}