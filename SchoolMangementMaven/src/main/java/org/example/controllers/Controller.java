package org.example.controllers;


import org.example.zapp.AppState;
import org.example.zapp.Renderer;


import java.util.Scanner;

public class Controller {
  private final AppState state;
  private final Renderer renderer;

  public Controller(AppState state, Renderer renderer) {
    this.state = state;
    this.renderer = renderer;
  }

  /**
   * Handles user input based on the current menu in AppState.
   */
  public void handleInput() {
    System.out.println("wawawaw");
    Scanner scanner = new Scanner(System.in);

    switch (state.getCurrentMenu()) {
      case "main" : handleMainMenu(scanner);
      case "gerer Profs" : performGererProfs(scanner);
      case "action2" : performAction2(scanner);
      default : {
        System.out.println("Invalid menu state. Returning to the main menu.");
        state.setCurrentMenu("main");
        renderer.render();
      }
    }
  }

  /**
   * Handles the main menu and user selections.
   */
  private void handleMainMenu(Scanner scanner) {

    String choice = scanner.nextLine();

    switch (choice) {
      case "1" : state.setCurrentMenu("gerer Profs");
      break ;
      case "2" : state.setCurrentMenu("action2");
        break ;
      case "3" : {
        state.setAuthenticated(false);
        state.setUsername(null);
        state.setCurrentMenu("login");
        System.out.println("\nYou have logged out successfully.");
      }
      break ;
      default : System.out.println("\nInvalid choice. Please try again.");
    }

    renderer.render();
  }


  private void performGererProfs(Scanner scanner) {
    System.out.println("\n--- Action 1 ---");
    System.out.println("You selected Action 1. Perform relevant tasks here.");
    System.out.print("Press Enter to return to the main menu...");
    scanner.nextLine();

    // Return to the main menu
    state.setCurrentMenu("main");
    renderer.render();
  }


  private void performAction2(Scanner scanner) {
    System.out.println("\n--- Action 2 ---");
    System.out.println("You selected Action 2. Perform relevant tasks here.");
    System.out.print("Press Enter to return to the main menu...");
    scanner.nextLine();

    // Return to the main menu
    state.setCurrentMenu("main");
    renderer.render();
  }
}
