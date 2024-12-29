package org.example.zapp.vue;

import org.example.models.note.EvaluationModality;

import java.util.List;
import java.util.Observer;

public interface ViewInterface  {
  void renderMenu(List<String> options);
  void displayMessage(String message);


}