import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class MovieApp extends JFrame {
  private User currentUser;
  private MovieDatabase movieDatabase = new MovieDatabase("movieDatabaseFile.csv");
}