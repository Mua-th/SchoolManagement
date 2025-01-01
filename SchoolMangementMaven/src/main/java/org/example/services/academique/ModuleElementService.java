package org.example.services.academique;

import org.example.models.academique.ModuleElement;
import org.example.models.users.Student.Student;

import java.sql.SQLException;
import java.util.List;

public interface ModuleElementService {
  ModuleElement getModuleElementById(String code) throws SQLException;
  List<ModuleElement> getAllModuleElements() throws SQLException;

  boolean createModuleElement(ModuleElement moduleElement) throws SQLException;
  boolean updateModuleElement(ModuleElement moduleElement) throws SQLException;
  boolean deleteModuleElement(String code) throws SQLException;
  List<ModuleElement> getModuleElementsByProfId(String professorId) throws SQLException; // New method


  boolean validateModuleElement(String moduleElementCode) throws Exception;

  List<Student> getSubscribedStudents(ModuleElement selectedmoduleElement) throws SQLException;
}