package org.example.controllers;

import org.example.models.users.User.User;
import org.example.models.users.User.UserRole;
import org.example.services.user.UserService;
import org.example.zapp.AppState;
import org.example.zapp.vue.Admin.AdminView;
import org.example.zapp.vue.Admin.AdminViewInterface;
import org.example.zapp.vue.LoginViewInterface;
import org.example.zapp.vue.Prof.ViewProfInterface;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class LoginController implements Observer {

  private final UserService userService;
  private final AppState state = AppState.getInstance();
  private final LoginViewInterface loginView;
  private final ViewProfInterface viewProf ;
  private AdminViewInterface adminView ;

  public LoginController(UserService userService, LoginViewInterface loginView, ViewProfInterface viewProf, AdminViewInterface adminView) {
    this.userService = userService;
    this.loginView = loginView;
    this.viewProf = viewProf;
    this.adminView = adminView ;
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

        adminView.displayAdminMenu();
      } else {
        //ViewProf.getInstance().displayMenuProf();
        viewProf.displayMenuProf();
      }

    } else {
      loginView.displayLoginFailure();
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    try {
      handleLogin();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}