package org.example.zapp.vue;

public interface LoginViewInterface {
   void displayLoginPrompt();
  void displayPasswordPrompt();
  String getUsername();
  String getPassword();
  void displayLoginSuccess(String username);
  void displayLoginFailure();
}