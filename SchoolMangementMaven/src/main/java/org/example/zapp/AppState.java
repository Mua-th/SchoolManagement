package org.example.zapp;


import org.example.models.user.User.User;

public class AppState {
  private String currentMenu;
  private boolean isAuthenticated;
  private User user;

  public AppState() {
    this.currentMenu = "login";
    this.isAuthenticated = false;
    this.user = null;
  }


  public String getCurrentMenu() {
    return currentMenu;
  }

  public void setCurrentMenu(String currentMenu) {
    this.currentMenu = currentMenu;
  }

  public boolean isAuthenticated() {
    return isAuthenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    isAuthenticated = authenticated;
  }

  public User getUser() {
    return user;
  }

  public void setUsername(User user) {
    this.user = user;
  }
}
