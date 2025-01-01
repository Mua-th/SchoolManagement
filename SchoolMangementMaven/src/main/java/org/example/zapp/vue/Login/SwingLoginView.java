package org.example.zapp.vue.Login;

import org.example.zapp.vue.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class SwingLoginView extends Observable implements LoginViewInterface {
  private JPanel panel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private String username;
  private String password;

  public SwingLoginView() {
    panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel usernameLabel = new JLabel("Username:");
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(usernameLabel, gbc);

    usernameField = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 0;
    panel.add(usernameField, gbc);

    JLabel passwordLabel = new JLabel("Password:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(passwordLabel, gbc);

    passwordField = new JPasswordField(20);
    gbc.gridx = 1;
    gbc.gridy = 1;
    panel.add(passwordField, gbc);

    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        username = usernameField.getText();
        password = new String(passwordField.getPassword());
        setChanged();
        notifyObservers();
      }
    });
    gbc.gridx = 1;
    gbc.gridy = 2;
    panel.add(loginButton, gbc);
  }

  @Override
  public void displayLoginPrompt() {
    MainFrame.getInstance().setPanel(panel);
    MainFrame.getInstance().setVisible(true);
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
    JOptionPane.showMessageDialog(MainFrame.getInstance(), "Welcome, " + username + "!");
  }

  @Override
  public void displayLoginFailure() {
    JOptionPane.showMessageDialog(MainFrame.getInstance(), "Invalid username or password. Please try again.");
  }
}