package org.example.models.users.Student;

import org.example.models.academique.Filiere;
import org.example.models.academique.ModuleElement;
import org.example.models.note.StudentGrade;

import java.util.HashMap;
import java.util.Map;

public class StudentBuilder {
  private String id;
  private String firstName;
  private String lastName;
  private Filiere filiere;
  private Map<ModuleElement, StudentGrade> grades = new HashMap<>();

  public StudentBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public StudentBuilder setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public StudentBuilder setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public StudentBuilder setFiliere(Filiere filiere) {
    this.filiere = filiere;
    return this;
  }

  public StudentBuilder setGrades(Map<ModuleElement, StudentGrade> grades) {
    this.grades = grades;
    return this;
  }

  public Student build() {
    return new Student(id, firstName, lastName, filiere);
  }
}