package org.example.models.user;

import models.academique.Filiere;
import models.academique.ModuleElement;
import models.note.StudentGrade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
  private String id;
  private String firstName;
  private String lastName;
  private Filiere filiere;
  private Map<ModuleElement, List<StudentGrade>> grades;

  public Student(String id, String firstName, String lastName, Filiere filiere) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.filiere = filiere;
    this.grades = new HashMap<>();
  }
}