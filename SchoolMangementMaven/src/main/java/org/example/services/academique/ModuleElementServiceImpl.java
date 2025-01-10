package org.example.services.academique;

import org.example.models.academique.ModuleElement;
import org.example.models.note.StudentGrade;
import org.example.models.users.Student.Student;
import org.example.repositories.academique.ModuleElementDao;
import org.example.repositories.academique.ModuleElementDaoImpl;
import org.example.services.note.StudentGradeService;
import org.example.services.note.StudentGradeServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class ModuleElementServiceImpl implements ModuleElementService {
    private final ModuleElementDao moduleElementDao ;
    private final StudentGradeServiceInterface studentGradeService ;

    // implement singleton

    private static ModuleElementServiceImpl instance;


    public static ModuleElementServiceImpl getInstance(StudentGradeServiceInterface studentGradeService , ModuleElementDao moduleElementDao) {
        if (instance == null) {
            synchronized (ModuleElementServiceImpl.class) {
                if (instance == null) {
                    instance = new ModuleElementServiceImpl(
                            moduleElementDao,
                      studentGradeService
                    );
                }
            }
        }
        return instance;
    }

    public ModuleElementServiceImpl(ModuleElementDao moduleElementDao, StudentGradeServiceInterface studentGradeService) {
        this.moduleElementDao = moduleElementDao;
        this.studentGradeService = studentGradeService;
    }

    @Override
    public boolean updateModuleElement(ModuleElement moduleElement) throws SQLException {
        return moduleElementDao.update(moduleElement);
    }


    @Override
    public List<ModuleElement> getModuleElementsByProfId(String professorId) throws SQLException {
        // Implement logic to get all ModuleElements by professor ID
        return moduleElementDao.findAllByProfId(professorId);
    }

    @Override
    public boolean validateModuleElement(String moduleElementCode) throws Exception {
        ModuleElement moduleElement = moduleElementDao.findById(moduleElementCode);
        if (moduleElement != null) {
            List<StudentGrade> studentGrades = studentGradeService.findByModuleElement(moduleElementCode);

            for (StudentGrade grade : studentGrades) {
                if (grade.getGrade() == null) {
                    throw new Exception("All grades must be entered before validation.");
                }
            }
            // Check if all grades are between 0 and 20
            for (StudentGrade grade : studentGrades) {
                if (grade.getGrade() < 0 || grade.getGrade() > 20) {
                    throw new Exception("All grades must be between 0 and 20.");
                }
            }
            // Mark absent students
            for (StudentGrade grade : studentGrades) {
                if (grade.isAbsent()) {
                    grade.setGrade(0.0);
                    studentGradeService.updateStudentGrade(grade);
                }
            }
            moduleElement.setValidated(true);
            return moduleElementDao.update(moduleElement);
        }
        return false;
    }
    @Override
    public List<Student> getSubscribedStudents(ModuleElement selectedmoduleElement) throws SQLException {
        // Implement logic to get all students subscribed to a ModuleElement
        return moduleElementDao.getStudentsByModuleElement(selectedmoduleElement.getCode());
    }



    @Override
    public ModuleElement findById(String s) throws SQLException {
        return moduleElementDao.findById(s);
    }

    @Override
    public List<ModuleElement> findAll() throws SQLException {
        return moduleElementDao.findAll();
    }

    @Override
    public boolean save(ModuleElement moduleElement) throws SQLException {
        return moduleElementDao.save(moduleElement);
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return moduleElementDao.delete(s);
    }
}