package org.example.repositories.User;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.academique.Filiere;
import org.example.models.users.Student.Student;
import org.example.models.users.Student.StudentBuilder;
import org.example.repositories.SuperRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    String query = "SELECT * FROM students WHERE id = ?";
    Connection connection = myDatabase.connect() ;


    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, s);
      ResultSet rs = myDatabase.fetchResults(stmt);
      if (rs.next()) {
        return new StudentBuilder().setId(rs.getString("id"))
          .setFirstName(rs.getString("firstName"))
          .setLastName(rs.getString("lastName"))
          .build();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      myDatabase.disconnect();
    }

    return null;
  }

  @Override
  public List<Student> findAll() throws SQLException {
    // Implement logic to find all students

    List<Student> students = new ArrayList<>();
    try {
      String sql = "SELECT * FROM students";
      Connection connection = myDatabase.connect() ;
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        students.add(new Student(
          resultSet.getString("id"),
          resultSet.getString("firstName"),
          resultSet.getString("lastName"),
          new Filiere(resultSet.getString("Filierecode"))
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      myDatabase.disconnect();
    }
    return students;

  }

  @Override
  public boolean save(Student student) throws SQLException {
    String filiereCode = student.getFiliere().getCode();
    // Vérifier si la filière existe dans la table filiere
    String checkFiliereSql = "SELECT COUNT(*) FROM filiere WHERE code = ?";
    Connection connection = myDatabase.connect() ;
    try (PreparedStatement checkStatement = connection.prepareStatement(checkFiliereSql)) {
      checkStatement.setString(1, filiereCode);
      ResultSet resultSet = checkStatement.executeQuery();

      if (resultSet.next() && resultSet.getInt(1) > 0) {
        // La filière existe, ajouter l'étudiant
        String sql = "INSERT INTO students (id, firstName, lastName, filierecode) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
          statement.setString(1, student.getId());
          statement.setString(2, student.getFirstName());
          statement.setString(3, student.getLastName());
          statement.setString(4, filiereCode);
          statement.executeUpdate();

          return true;
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false ;
    }
    finally {
      myDatabase.disconnect();
}
    return true ;
  }

  @Override
  public boolean delete(String s) throws SQLException {
    String sql = "DELETE FROM students WHERE id = ?";
    Connection connection = myDatabase.connect() ;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, s);
      statement.executeUpdate();

      return true ;
    } catch (SQLException e) {
      e.printStackTrace();
      return false ;
    }
    finally {
      myDatabase.disconnect();
}

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

  @Override
  public Student findStudentByLastName(String lastName) throws SQLException {
    String query = "SELECT * FROM students WHERE lastName = ?";
    Connection connection = myDatabase.connect() ;

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, lastName);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Student student = new Student(
            rs.getString("id"),
            rs.getString("firstName"),
            rs.getString("lastName"),
            new Filiere(rs.getString("Filierecode"))
          );
          return student;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      myDatabase.disconnect();
    }

    return null;
  }



  @Override
  public void updateStudent(Student student) throws SQLException {
    String sql = "UPDATE students SET firstName = ?, lastName = ? WHERE id = ?";
    Connection connection = myDatabase.connect() ;
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, student.getFirstName());
      statement.setString(2, student.getLastName());
      statement.setString(3, student.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      myDatabase.disconnect();
    }
  }



}
