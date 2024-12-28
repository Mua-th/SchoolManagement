package org.example.controllers;

import org.example.zapp.AppState;
import org.example.zapp.vue.View;
import org.example.zapp.vue.ViewInterface;

public class LogoutController {
  private final AppState state;
  private final ViewInterface view = new View();

  public LogoutController(AppState state) {
    this.state = state;
  }

  public void handleLogout() {
    state.setAuthenticated(false);
    state.setUser(null);
    view.displayMessage("You have logged out successfully.");
    //System.out.println("\nYou have logged out successfully.");
  }
}