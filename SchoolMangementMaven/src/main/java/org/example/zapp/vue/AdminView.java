package org.example.zapp.vue;



import java.util.Scanner;
import java.util.List;

public class AdminView {

  public static void displayAdminMenu() {
    System.out.println("Welcome Admin!");
    List<String> adminMenu = List.of(
      "1. Manage Professors",
      "2. Manage Students",
      "3. Manage Programs",
      "4. Logout"
    );
    for (String option : adminMenu) {
      System.out.println(option);
    }
  }

  public static String getUserChoice() {
    System.out.print("Enter your choice: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
