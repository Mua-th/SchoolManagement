package org.example.services.user.StudentServicesabrin;
import org.example.models.academique.Filiere;
import org.example.models.users.Student.Student;
import org.example.repositories.FiliereDAO.FiliereDAO;
import org.example.repositories.FiliereDAO.FiliereDAOImpl;
import org.example.repositories.StudentDAO.StudentDAO;
import org.example.repositories.StudentDAO.StudentDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private static StudentServiceImpl instance;
    private StudentDAO studentDAO = StudentDAOImpl.getInstance();

    private FiliereDAO filiereDAO = FiliereDAOImpl.getInstance();

    private StudentServiceImpl()  {
    }

    public static StudentServiceImpl getInstance()  {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean addStudent(Student student) throws SQLException {

        if (filiereDAO.checkIfFiliereExists(student.getFiliere().getCode())) {
            studentDAO.addStudent(student);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
         studentDAO.updateStudent(student);

    }

    @Override
    public void deleteStudent(String id) throws SQLException {
         studentDAO.deleteStudent(id);

    }

    @Override
    public Optional<Student> findStudentByLastName(String lastName) throws SQLException {
        return studentDAO.findStudentByLastName(lastName);
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    @Override
    public Filiere getFiliereByCode(String filiereCode) throws SQLException {

        return studentDAO.getFiliereByCode(filiereCode);
    }



}
