package org.example.controllers;



import org.example.models.user.User.User;
import org.example.models.user.User.UserRole;
import org.example.services.user.UserService;
import org.example.zapp.AppState;
import org.example.zapp.Renderer;
import org.example.zapp.vue.LoginView;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginController {

  private final UserService userService ;
  private final AppState state;
  private final Renderer renderer;

  public LoginController(UserService userService, AppState state, Renderer renderer) {
    this.userService = userService;
    this.state = state;
    this.renderer = renderer;

  }

  public void handleLogin() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    // Get username
    LoginView.displayLoginPrompt();
    String login = scanner.nextLine();

    User user = userService.findByLogin(login) ;
    // Get password
    LoginView.displayPasswordPrompt();
    String password = scanner.nextLine();

    // Validate credentials
    if ( user.getPassword().equals(password)) {
      state.setAuthenticated(true);
      state.setUsername(user);
      if(user.getRole().equals(UserRole.Administrator)){
        state.setCurrentMenu("AdminMain");
      }
      else {
        state.setCurrentMenu("ProfMain");

      }
       // Redirect to main menu
      LoginView.displayLoginSuccess(user.getFirstName());
    } else {
      LoginView.displayLoginFailure();
    }

    renderer.render(); // Re-render UI after login attempt
  }

  public void handleLogout() {
    state.setAuthenticated(false);
    state.setUsername(null);
    state.setCurrentMenu("login");
    System.out.println("You have been logged out.");
    renderer.render();
  }
}
