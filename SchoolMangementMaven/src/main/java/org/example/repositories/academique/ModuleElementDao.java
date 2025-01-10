package org.example.repositories.academique;

import org.example.models.academique.ModuleElement;
import org.example.models.users.Student.Student;
import org.example.repositories.Repository;

import java.sql.SQLException;
import java.util.List;

public interface ModuleElementDao extends Repository<ModuleElement,String> {
  List<ModuleElement> findAllByProfId(String professorId) throws SQLException; // New method
 boolean update(ModuleElement moduleElement) throws SQLException;
  List<Student> getStudentsByModuleElement(String moduleElementCode) throws SQLException;

}
