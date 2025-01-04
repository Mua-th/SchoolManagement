package org.example.repositories;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    Connection connection = myDatabase.connect();
    String query = "SELECT * FROM studentgrade WHERE studentId = ? AND moduleElementCode = ? AND modality = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, studentGradeId.getStudentId());
    preparedStatement.setString(2, studentGradeId.getModuleElementCode());
    preparedStatement.setString(3, studentGradeId.getEvaluationModality().name());
    ResultSet resultSet = preparedStatement.executeQuery();

    StudentGrade studentGrade = null;
    if (resultSet.next()) {
      double grade = resultSet.getDouble("grade");
      studentGrade = new StudentGrade(studentGradeId, grade);
    }

    myDatabase.disconnect();
    return studentGrade;
  }

  @Override
  public List<StudentGrade> findAll() {
    //implement the findAll method to get all the grades of all students
    return null;
  }

  @Override
  public boolean save(StudentGrade studentGrade) throws SQLException {
    Connection connection = myDatabase.connect();
    try {
      // Check if the record already exists
      String checkQuery = "SELECT COUNT(*) FROM studentgrade WHERE studentId = ? AND moduleElementCode = ? AND modality = ?";
      PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
      checkStatement.setString(1, studentGrade.getStudentGradeId().getStudentId());
      checkStatement.setString(2, studentGrade.getStudentGradeId().getModuleElementCode());
      checkStatement.setString(3, studentGrade.getStudentGradeId().getEvaluationModality().name());
      ResultSet resultSet = checkStatement.executeQuery();
      resultSet.next();
      int count = resultSet.getInt(1);

      if (count > 0) {
        // Record exists, perform update
        String updateQuery = "UPDATE studentgrade SET grade = ? WHERE studentId = ? AND moduleElementCode = ? AND modality = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
        updateStatement.setDouble(1, studentGrade.getGrade());
        updateStatement.setString(2, studentGrade.getStudentGradeId().getStudentId());
        updateStatement.setString(3, studentGrade.getStudentGradeId().getModuleElementCode());
        updateStatement.setString(4, studentGrade.getStudentGradeId().getEvaluationModality().name());
        updateStatement.executeUpdate();
      } else {
        // Record does not exist, perform insert
        String insertQuery = "INSERT INTO studentgrade (studentId, moduleElementCode, modality, grade, isAbsent) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, studentGrade.getStudentGradeId().getStudentId());
        insertStatement.setString(2, studentGrade.getStudentGradeId().getModuleElementCode());
        insertStatement.setString(3, studentGrade.getStudentGradeId().getEvaluationModality().name());
        insertStatement.setDouble(4, studentGrade.getGrade());
        insertStatement.setInt(5, 0); // Assuming isAbsent is always false
        insertStatement.executeUpdate();
      }
    } catch (SQLException e) {
      if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
        System.err.println("Error: Cannot add or update a child row: a foreign key constraint fails.");
      } else {
        e.printStackTrace();
      }
      return false;
    } finally {
      myDatabase.disconnect();
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
          new StudentGradeId(studentId, moduleElementCode, EvaluationModality.valueOf(modality.toUpperCase()))
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
        .studentGradeId(new StudentGradeId(studentId, moduleElementCode, EvaluationModality.valueOf(modality.toUpperCase())))
        .build();

      studentGrades.add(studentGrade);
    }
    myDatabase.disconnect();

    return studentGrades;
  }

  public List<StudentGrade> getStudentGradesByModuleElement(String studentId, String moduleElementCode) throws SQLException {
    Connection connection = myDatabase.connect();
    ResultSet resultSet = myDatabase.fetchResults(String.format("SELECT * FROM studentgrade WHERE studentId = '%s' AND moduleElementCode = '%s';", studentId, moduleElementCode));
    List<StudentGrade> studentGrades = new ArrayList<>();

    while (resultSet.next()) {
      String grade = resultSet.getString("grade");
      String modality = resultSet.getString("modality");

      StudentGrade studentGrade = new StudentGradeBuilder()
        .grade(Double.parseDouble(grade))
        .studentGradeId(new StudentGradeId(studentId, moduleElementCode, EvaluationModality.valueOf(modality.toUpperCase())))
        .build();

      studentGrades.add(studentGrade);
    }

    return studentGrades;
  }

  public List<StudentGrade> getStudentGradesByModuleElementAndModality(String studentId, String moduleElementCode, EvaluationModality evaluationModality) throws SQLException {
    Connection connection = myDatabase.connect();
    String query = "SELECT * FROM studentgrade WHERE studentId = ? AND moduleElementCode = ? AND modality = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, studentId);
    preparedStatement.setString(2, moduleElementCode);
    preparedStatement.setString(3, evaluationModality.name());
    ResultSet resultSet = preparedStatement.executeQuery();

    List<StudentGrade> studentGrades = new ArrayList<>();
    while (resultSet.next()) {
      double grade = resultSet.getDouble("grade");
      StudentGrade studentGrade = new StudentGradeBuilder()
        .grade(grade)
        .studentGradeId(new StudentGradeId(studentId, moduleElementCode, evaluationModality))
        .build();
      studentGrades.add(studentGrade);
    }

    myDatabase.disconnect();
    return studentGrades;
  }
}
