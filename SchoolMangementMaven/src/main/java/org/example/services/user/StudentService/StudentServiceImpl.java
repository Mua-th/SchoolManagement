package org.example.services.user.StudentService;


import org.example.models.academique.Filiere;
import org.example.models.user.Student;
import org.example.repositories.StudentDAO.StudentDAO;
import org.example.repositories.StudentDAO.StudentDAOImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private static StudentServiceImpl instance;
    private StudentDAO studentDAO;

    private StudentServiceImpl()  {
        this.studentDAO = StudentDAOImpl.getInstance();
    }

    public static StudentServiceImpl getInstance()  {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public void addStudent(Student student) {
         studentDAO.addStudent(student);
    }

    @Override
    public void updateStudent(String id, String firstName, String lastName)  {
         studentDAO.updateStudent(id, firstName, lastName);
    }

    @Override
    public void deleteStudent(String id)  {
         studentDAO.deleteStudent(id);
    }

    @Override
    public Optional<Student> findStudentByLastName(String lastName)  {
        return studentDAO.findStudentByLastName(lastName);
    }

    @Override
    public List<Student> getAllStudents()  {
        return studentDAO.getAllStudents();
    }

    @Override
    public Filiere getFiliereByCode(String filiereCode) {
        // Appel de la méthode du DAO pour récupérer la filière
        return studentDAO.getFiliereByCode(filiereCode);
    }
}
