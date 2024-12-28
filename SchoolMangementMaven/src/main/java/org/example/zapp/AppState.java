package org.example.zapp;

import org.example.config.DatabaseFactory;
import org.example.models.users.User.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppState {
  private boolean isAuthenticated;
  private static User user;
  private static AppState instance;

  public static String getGuiType() {
    Properties properties = new Properties();
    try (InputStream input = DatabaseFactory.class.getClassLoader().getResourceAsStream("app.properties")) {
      if (input == null) {
        throw new RuntimeException("Unable to find app.properties");
      }
      properties.load(input);
    } catch (IOException ex) {
      throw new RuntimeException("Error reading app.properties", ex);
    }

    return properties.getProperty("gui.type");
  }


  private AppState() {
    this.isAuthenticated = false;
    this.user = null;
  }

  public static AppState getInstance() {
    if (instance == null) {
      synchronized (AppState.class) {
        if (instance == null) {
          instance = new AppState();
        }
      }
    }
    return instance;
  }

  public boolean isAuthenticated() {
    return isAuthenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    isAuthenticated = authenticated;
  }

  public static User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}