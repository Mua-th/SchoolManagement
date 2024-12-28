package org.example.repositories.User;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.users.Student.Student;
import org.example.models.users.Student.StudentBuilder;
import org.example.repositories.SuperRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl extends SuperRepo implements StudentDAO{

  //make it singleton

  private static StudentDAOImpl instance;

  public static StudentDAOImpl getInstance(){
    if(instance==null){
      instance = new StudentDAOImpl();
    }
    return instance;
  }


  private StudentDAOImpl() {
    super(myDatabase);
  }

  @Override
  public Student findById(String s) throws SQLException {
    // Implement logic to find a student by ID

    return null;
  }

  @Override
  public List<Student> findAll() {
    // Implement logic to find all students


    return null;
  }

  @Override
  public boolean save(Student student) throws SQLException {
    return false;
  }

  @Override
  public boolean delete(String s) {
    return false;
  }

  @Override
  public Student findByIdForProf(String professorId,String studentId) throws SQLException {
    String query = "SELECT s.* FROM students s " +
      "JOIN studentgrade sg ON s.id = sg.studentId " +
      "JOIN professormoduleelement pme ON sg.moduleElementCode = pme.moduleElementCode " +
      "WHERE pme.professorId = ? AND s.id = ?";


    try (Connection connection = myDatabase.connect();
         PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, professorId);
      stmt.setString(2, studentId);
      ResultSet rs = myDatabase.fetchResults(stmt);

      if (rs.next()) {
        return new StudentBuilder().setId(rs.getString("id"))
          .setFirstName(rs.getString("firstName"))
          .setLastName(rs.getString("lastName"))
          .build();
      }
    }


    return null;
  }
}
