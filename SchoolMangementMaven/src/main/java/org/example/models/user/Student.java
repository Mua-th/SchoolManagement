package org.example.models.user;

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

  public Student() {
  }

  // Getters
  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Filiere getFiliere() {
    return filiere;
  }

  public Map<ModuleElement, StudentGrade> getGrades() {
    return grades;
  }

  // Setters
  public void setId(String id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFiliere(Filiere filiere) {
    this.filiere = filiere;
  }

  public void setGrades(Map<ModuleElement, StudentGrade> grades) {
    this.grades = grades;
  }

  // toString
  @Override
  public String toString() {
    return "Student{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", filiere=" + filiere +
            '}';
  }


}