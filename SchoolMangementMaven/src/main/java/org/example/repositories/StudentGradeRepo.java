package org.example.repositories;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeRepo extends SuperRepo implements Repository<StudentGrade, StudentGradeId>{







  //implement the singleton pattern for the StudentGradeRepo
  private static StudentGradeRepo instance;

  private StudentGradeRepo() {
    super(myDatabase);
  }

  public static StudentGradeRepo getInstance() {
    if (instance == null) {
      instance = new StudentGradeRepo();
    }
    return instance;
  }



  @Override
  public StudentGrade findById(StudentGradeId studentGradeId) throws SQLException {


    return null;
  }

  @Override
  public List<StudentGrade> findAll() {
    //implement the findAll method to get all the grades of all students
    return null;
  }

  @Override
  public boolean save(StudentGrade studentGrade) throws SQLException {
    try {
      myDatabase.connect();
      myDatabase.executeQuery(String.format(
        "INSERT INTO studentgrade (studentId, moduleElementCode, modality, grade, isAbsent) VALUES ('%s', '%s', '%s', '%s', '%s');",
        studentGrade.getStudentGradeId().getStudentId(),
        studentGrade.getStudentGradeId().getModuleElementCode(),
        studentGrade.getStudentGradeId().getEvaluationModality(),
        studentGrade.getGrade(),
        0 // Assuming isAbsent is always false as per your initial table definition
      ));
    } catch (SQLException e) {
      if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
        System.err.println("Error: Cannot add or update a child row: a foreign key constraint fails.");
      } else {
        e.printStackTrace();
      }
      return false;
    }
    return true;
  }
  @Override
  public boolean delete(StudentGradeId studentGradeId) {
    return false;
  }

  // implement a method to get all the grades of a student use sql query to get the grades
  public List<StudentGrade> getStudentGrades(String studentId) throws SQLException {
    Connection connection = myDatabase.connect();

    ResultSet resultSet = myDatabase.fetchResults(String.format("SELECT * FROM studentgrade where studentid ='%s';" , studentId));
    List<StudentGrade> studentGrades = new ArrayList<>();

    while (resultSet.next()) {
      // get the studentId, courseId, grade, and semester from the result set
      String moduleElementCode = resultSet.getString("moduleElementCode");
      String grade = resultSet.getString("grade");
      String modality = resultSet.getString("modality");

      // create a new StudentGrade object using the StudentGradeBuilder
      StudentGrade studentGrade = new  StudentGradeBuilder()
        .grade(Double.parseDouble(grade))
        .studentGradeId(
          new StudentGradeId(studentId, moduleElementCode, modality)
        )
        .build();

      // add the studentGrade to the list of studentGrades
      studentGrades.add(studentGrade);

    }



    return studentGrades;
  }

  public List<StudentGrade> findByModuleElement(String moduleElementCode) throws SQLException {
    Connection connection = myDatabase.connect();
    ResultSet resultSet = myDatabase.fetchResults(String.format("SELECT * FROM studentgrade WHERE moduleElementCode = '%s';", moduleElementCode));
    List<StudentGrade> studentGrades = new ArrayList<>();

    while (resultSet.next()) {
      String studentId = resultSet.getString("studentId");
      String grade = resultSet.getString("grade");
      String modality = resultSet.getString("modality");

      StudentGrade studentGrade = new StudentGradeBuilder()
        .grade(Double.parseDouble(grade))
        .studentGradeId(new StudentGradeId(studentId, moduleElementCode, modality))
        .build();

      studentGrades.add(studentGrade);
    }

    return studentGrades;
  }
}
