package org.example.zapp;



import org.example.zapp.vue.AdminView;
import org.example.zapp.vue.LoginView;
import org.example.zapp.vue.Prof.MainMenuProf;
import org.example.zapp.vue.View;

import java.util.Arrays;
import java.util.List;

public class Renderer {
  private final AppState state;

  public Renderer(AppState state) {
    this.state = state;
  }

  public void render() {
    switch (state.getCurrentMenu()) {
      case "login":
        LoginView.displayLoginPrompt();
        break;
      case "AdminMain":
          AdminView.displayAdminMenu();
        break;
      case  "ProfMain" :
          MainMenuProf.displayMenuProf();

        break;
      default:
        View.displayMessage("Unknown menu state!");
        break;
    }
  }
  private void renderAdminMenu() {
    View.displayMessage("Welcome Admin!");
    List<String> adminMenu = Arrays.asList(
      "Gerer Profs",
      "Gerer les étudiants",
      "Gerer les filières",
      "Logout"
    );
    View.renderMenu(adminMenu);
  }
  private void renderProfessorMenu() {
    View.displayMessage("Welcome Professor!");
    List<String> professorMenu = Arrays.asList(
      "Classes",
      "Logout"
    );
    View.renderMenu(professorMenu);
  }
}
