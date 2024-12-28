package org.example.zapp.vue;

import java.util.List;

public interface ViewInterface {
  void renderMenu(List<String> options);
  void displayMessage(String message);
}