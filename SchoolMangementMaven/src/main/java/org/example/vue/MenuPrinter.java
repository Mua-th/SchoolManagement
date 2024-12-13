package org.example.vue;

public class MenuPrinter implements  PrintableToConsole{


  @Override
  public  void print() {
    System.out.println("----------------------------------- WELCOME :) ---------------------------------");
    System.out.println("1. Manage Students");
    System.out.println("2. Manage Teachers");
    System.out.println("3. Manage Courses");
    System.out.println("4. View Reports");
    System.out.println("5. Exit");
    System.out.println("--------------------------------------------------------------------------------");
    System.out.print("Please select an option: ");
  }
}
