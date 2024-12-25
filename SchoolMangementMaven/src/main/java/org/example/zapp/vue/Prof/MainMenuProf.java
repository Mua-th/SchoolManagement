package org.example.zapp.vue.Prof;

import org.example.zapp.vue.View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenuProf {



  public static void displayMenuProf() {

    View.renderMenu(Arrays.asList("mes classes" , "Logout"));
  }

  public static String getUserChoice() {
    System.out.print("Enter your choice: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }


}
