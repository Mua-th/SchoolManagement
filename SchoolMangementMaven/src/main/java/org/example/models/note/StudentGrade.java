package org.example.models.note;


public  class StudentGrade {


  private Double grade;
  private StudentGradeId studentGradeId ;

  private boolean isAbsent;

  public StudentGrade(StudentGradeId studentGradeId, double v) {
    this.studentGradeId = studentGradeId;
    this.grade = v;
  }

  public boolean isAbsent() {
    return isAbsent;
  }

  public void setAbsent(boolean absent) {
    isAbsent = absent;
  }

  public StudentGrade(StudentGradeId studentGradeId, double v, boolean isAbsent) {
    this.studentGradeId = studentGradeId;
    this.grade = v;
    this.isAbsent = isAbsent;
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