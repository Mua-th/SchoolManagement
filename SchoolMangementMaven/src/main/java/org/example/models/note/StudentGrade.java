package org.example.models.note;

import org.example.models.academique.ModuleElement;
import org.example.models.user.Student.Student;



public  class StudentGrade {


  private Double grade;
  private StudentGradeId studentGradeId ;

  public StudentGrade(StudentGradeId studentGradeId, double v) {
    this.studentGradeId = studentGradeId;
    this.grade = v;
  }

  public StudentGrade() {
  }

  @Override
  public String toString() {
    return "StudentGrade{" +
      "grade=" + grade +
      ", studentGradeId=" + studentGradeId +
      '}';
  }

  public boolean isValidGrade() {
    return grade != null && grade >= 0 && grade <= 20;
  }


  public StudentGradeId getStudentGradeId() {
    return studentGradeId;
  }

  public void setStudentGradeId(StudentGradeId studentGradeId) {
    this.studentGradeId = studentGradeId;
  }
  public Double getGrade() {
    return grade;
  }
  public void setGrade(Double grade) {
    this.grade = grade;
  }

}