package org.example.models.note;

import models.academique.ModuleElement;
import models.user.Student;

public  class StudentGrade {
  private Student student;
  private ModuleElement moduleElement;
  private EvaluationModality modality;
  private Double grade;
  private boolean isAbsent;

  public boolean isValidGrade() {
    return grade != null && grade >= 0 && grade <= 20;
  }
}