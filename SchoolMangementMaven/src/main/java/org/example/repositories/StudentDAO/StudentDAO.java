package org.example.repositories.StudentDAO;
import org.example.models.academique.Filiere;
import org.example.models.users.Student.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentDAO {

    void addStudent(Student student) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    void deleteStudent(String id) throws SQLException;
    Optional<Student> findStudentByLastName(String lastName) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
    public Filiere getFiliereByCode(String filiereCode) throws SQLException;


}

