package org.example.zapp.vue;

import java.util.Scanner;

public class LoginView implements LoginViewInterface {

  private static LoginView instance = null;

  private LoginView() {
  }

  public static LoginView getInstance() {
    if (instance == null) {
      instance = new LoginView();
    }
    return instance;
  }

  @Override
  public void displayLoginPrompt() {
    System.out.println("\n=== Login ===");
    System.out.print("Username: ");
  }

  @Override
  public void displayPasswordPrompt() {
    System.out.print("Password: ");
  }

  @Override
  public String getUsername() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public String getPassword() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public void displayLoginSuccess(String username) {
    System.out.println("\nWelcome, " + username + "!");
  }

  @Override
  public void displayLoginFailure() {
    System.out.println("\nInvalid username or password. Please try again.");
  }



}