package org.example;

import org.example.controllers.Controller;
import org.example.controllers.LoginController;
import org.example.controllers.ProfController;
import org.example.models.user.User.UserRole;
import org.example.repositories.User.UserRepository;
import org.example.services.user.UserService;
import org.example.zapp.AppState;
import org.example.zapp.Renderer;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
      AppState appState = new AppState(); // State
      Renderer renderer = new Renderer(appState); // Render logic

      LoginController loginController = new LoginController(
        new UserService(
        new UserRepository()
      ),
        appState,
        renderer);

      Controller controller = new Controller(appState, renderer); // Input handling
      ProfController profController = new ProfController(appState,renderer) ;

      while (true) {
        if (!appState.isAuthenticated()) {
          loginController.handleLogin();
        } else if(appState.getUser().getRole().equals(UserRole.Professor)) {
          profController.handleInput(); // Handle other inputs (e.g., main menu)
        }
        else {
          controller.handleInput();
        }
      }
    }

}