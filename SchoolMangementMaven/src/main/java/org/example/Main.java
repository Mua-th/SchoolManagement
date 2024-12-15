package org.example;

import org.example.controllers.Controller;
import org.example.controllers.LoginController;
import org.example.repositories.UserRepository;
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



      while (true) {
        if (!appState.isAuthenticated()) {
          loginController.handleLogin();
        } else {
          controller.handleInput(); // Handle other inputs (e.g., main menu)
        }
      }
    }

}