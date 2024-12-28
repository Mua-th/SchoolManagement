package org.example.zapp.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingLoginView implements LoginViewInterface {
  private JFrame frame;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private String username;
  private String password;
  private ActionListener loginListener;

  public SwingLoginView() {
    frame = new JFrame("Login");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 150);
    frame.setLayout(new GridLayout(3, 2));

    JLabel usernameLabel = new JLabel("Username:");
    usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password:");
    passwordField = new JPasswordField();

    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        username = usernameField.getText();
        password = new String(passwordField.getPassword());
        if (loginListener != null) {
          loginListener.actionPerformed(e);
        }
      }
    });

    frame.add(usernameLabel);
    frame.add(usernameField);
    frame.add(passwordLabel);
    frame.add(passwordField);
    frame.add(new JLabel()); // Empty cell
    frame.add(loginButton);
  }

  @Override
  public void displayLoginPrompt() {
    frame.setVisible(true);
  }

  @Override
  public void displayPasswordPrompt() {
    // No need to implement as password prompt is part of the login window
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void displayLoginSuccess(String username) {
    JOptionPane.showMessageDialog(frame, "Welcome, " + username + "!");
  }

  @Override
  public void displayLoginFailure() {
    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
  }

  public void setLoginListener(ActionListener loginListener) {
    this.loginListener = loginListener;
  }
}