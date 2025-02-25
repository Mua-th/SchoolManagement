package org.example.repositories.academique;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.academique.Module;
import org.example.models.academique.ModuleElement;
import org.example.models.note.StudentGrade;
import org.example.models.users.Student.Student;
import org.example.repositories.SuperRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleElementDaoImpl extends SuperRepo implements ModuleElementDao {
  private ModuleElementDaoImpl() {
    super(myDatabase);
  }
  private static ModuleElementDaoImpl instance;
  public static ModuleElementDaoImpl getInstance() {
    if (instance == null) {
      instance = new ModuleElementDaoImpl();
    }
    return instance;
  }
  @Override
  public ModuleElement findById(String code) throws SQLException {
    String query = "SELECT * FROM ModuleElement WHERE code = ?";
    try (Connection connection = myDatabase.connect();
         PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, code);
      ResultSet rs = myDatabase.fetchResults(stmt);
      if (rs.next()) {
        return mapResultSetToModuleElement(rs);
      }
    }
    return null;
  }
  @Override
  public List<ModuleElement> findAll() {
    List<ModuleElement> moduleElements = new ArrayList<>();
    String query = "SELECT * FROM ModuleElement";
    try (Connection connection = myDatabase.connect();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        moduleElements.add(mapResultSetToModuleElement(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return moduleElements;
  }
  @Override
  public boolean save(ModuleElement moduleElement) throws SQLException {
    String query = "INSERT INTO ModuleElement (code, coefficient, moduleCode) VALUES (?, ?, ?)";
    try (Connection connection = myDatabase.connect();
         PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, moduleElement.getCode());
      stmt.setDouble(2, moduleElement.getCoefficient());
      stmt.setString(3, moduleElement.getParentModule().getCode());
      myDatabase.executeStatement(stmt);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  @Override
  public boolean delete(String code) {
    String query = "DELETE FROM ModuleElement WHERE code = ?";
    try (Connection connection = myDatabase.connect();
         PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, code);
      myDatabase.executeStatement(stmt);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  @Override
  public List<ModuleElement> findAllByProfId(String professorId) throws SQLException {
    List<ModuleElement> moduleElements = new ArrayList<>();
    String query = "SELECT me.* FROM ModuleElement me " +
      "JOIN ProfessorModuleElement pme ON me.code = pme.moduleElementCode " +
      "WHERE pme.professorId = ?";
    try (Connection connection = myDatabase.connect();
         PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, professorId);
      ResultSet rs = myDatabase.fetchResults(stmt);
      while (rs.next()) {
        moduleElements.add(mapResultSetToModuleElement(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return moduleElements;
  }
  @Override
  public boolean update(ModuleElement moduleElement) throws SQLException {
    String query = "UPDATE ModuleElement SET coefficient = ?, isValidated = ?, moduleCode = ? WHERE code = ?";
    try (Connection connection = myDatabase.connect();
         PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setDouble(1, moduleElement.getCoefficient());
      stmt.setBoolean(2, moduleElement.isValidated());
      stmt.setString(3, moduleElement.getParentModule().getCode());
      stmt.setString(4, moduleElement.getCode());
      int rowsUpdated = stmt.executeUpdate();
      return rowsUpdated > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  @Override
  public List<Student> getStudentsByModuleElement(String moduleElementCode) throws SQLException {
    List<Student> students = new ArrayList<>();

    String query = "SELECT distinct s.* FROM students s " +
      "JOIN studentgrade sg ON s.id = sg.studentId " +
      "WHERE sg.moduleElementCode = ?";

    try (Connection connection = myDatabase.connect();
         PreparedStatement stmt = connection.prepareStatement(query)) {

      stmt.setString(1, moduleElementCode);
      ResultSet rs = myDatabase.fetchResults(stmt);

      while (rs.next()) {
        Student student = new Student(
          rs.getString("id"),
          rs.getString("firstName"),
          rs.getString("lastName"),
          null // Assuming Filiere is set elsewhere
        );
        students.add(student);
      }
    }
    return students;
  }
  private ModuleElement mapResultSetToModuleElement(ResultSet rs) throws SQLException {
    ModuleElement moduleElement = new ModuleElement();
    moduleElement.setCode(rs.getString("code"));
    moduleElement.setCoefficient(rs.getDouble("coefficient"));
    moduleElement.setValidated(rs.getBoolean("isValidated"));
    moduleElement.setParentModule(new Module(rs.getString("moduleCode")));
    return moduleElement;
  }
}