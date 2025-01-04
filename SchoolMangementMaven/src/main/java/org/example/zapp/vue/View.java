package org.example.zapp.vue;

import java.util.List;

public  class View implements ViewInterface {
  @Override
  public void renderMenu(List<String> options) {
    System.out.println("\nMain Menu:");
    for (int i = 0; i < options.size(); i++) {
      System.out.printf("%d. %s%n", i + 1, options.get(i));
    }
  }

  @Override
  public void displayMessage(String message) {
    System.out.println("\n" + message);
  }


}