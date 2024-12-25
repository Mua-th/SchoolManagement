package org.example.models.user.Student;

import org.example.models.academique.Filiere;
import org.example.models.academique.ModuleElement;
import org.example.models.note.StudentGrade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
  private String id;
  private String firstName;
  private String lastName;
  private Filiere filiere;
  private Map<ModuleElement, StudentGrade> grades;

  public Student(String id, String firstName, String lastName, Filiere filiere) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.filiere = filiere;
    this.grades = new HashMap<>();
  }
}