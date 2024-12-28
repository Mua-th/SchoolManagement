package org.example.controllers;

import org.example.zapp.AppState;

import org.example.zapp.vue.AdminView;

import java.util.Scanner;

public class AdminController {
//  private final AppState state;
//  private final Renderer renderer;

  public AdminController() {
//    this.state = state;
//    this.renderer = renderer;
  }

  public void handleInput() {
    Scanner scanner = new Scanner(System.in);
    AdminView.displayAdminMenu();
    String choice = scanner.nextLine();

    switch (choice) {
      case "1":
        handleManageProfessors(scanner);
        break;
      case "2":
        handleManageStudents(scanner);
        break;
      case "3":
        handleManagePrograms(scanner);
        break;
      case "4":
//        state.setAuthenticated(false);
//        state.setUsername(null);
//        state.setCurrentMenu("login");
//        System.out.println("\nYou have logged out successfully.");
        break;
      default:
        System.out.println("\nInvalid choice. Please try again.");
    }

   // renderer.render();
  }

  private void handleManageProfessors(Scanner scanner) {
    // Implement logic to manage professors
    System.out.println("Managing professors...");
    // Example: List all professors, add a new professor, etc.
  }

  private void handleManageStudents(Scanner scanner) {
    // Implement logic to manage students
    System.out.println("Managing students...");
    // Example: List all students, add a new student, etc.
  }

  private void handleManagePrograms(Scanner scanner) {
    // Implement logic to manage programs
    System.out.println("Managing programs...");
    // Example: List all programs, add a new program, etc.
  }
}