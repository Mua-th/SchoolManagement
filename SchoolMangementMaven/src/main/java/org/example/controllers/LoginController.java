package org.example.controllers;

import org.example.models.users.User.User;
import org.example.models.users.User.UserRole;
import org.example.services.user.UserService;
import org.example.zapp.AppState;
import org.example.zapp.vue.AdminView;
import org.example.zapp.vue.LoginViewInterface;
import org.example.zapp.vue.Prof.ViewProf;

import java.sql.SQLException;

public class LoginController {

  private final UserService userService;
  private final AppState state = AppState.getInstance();
  private final LoginViewInterface loginView;

  public LoginController(UserService userService, LoginViewInterface loginView) {
    this.userService = userService;
    this.loginView = loginView;
  }

  public void handleLogin() throws SQLException {
    loginView.displayLoginPrompt();
    String login = loginView.getUsername();

    User user = userService.findByLogin(login);
    loginView.displayPasswordPrompt();
    String password = loginView.getPassword();

    if (user.getPassword().equals(password)) {
      state.setAuthenticated(true);
      state.setUser(user);
      loginView.displayLoginSuccess(user.getLogin());

      if (user.getRole().equals(UserRole.Administrator)) {
        AdminView.displayAdminMenu();
      } else {
        ViewProf.getInstance().displayMenuProf();
      }

    } else {
      loginView.displayLoginFailure();
    }
  }
}