package org.example.zapp.vue;


public class LoginView {
  public static void displayLoginPrompt() {
    System.out.println("\n=== Login ===");
    System.out.print("Username: ");
  }

  public static void displayPasswordPrompt() {
    System.out.print("Password: ");
  }

  public static void displayLoginSuccess(String username) {
    System.out.println("\nWelcome, " + username + "!");
  }

  public static void displayLoginFailure() {
    System.out.println("\nInvalid username or password. Please try again.");
  }
}
