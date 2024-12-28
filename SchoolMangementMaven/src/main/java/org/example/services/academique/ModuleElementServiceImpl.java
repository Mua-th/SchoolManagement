package org.example.services.academique;

import org.example.models.academique.ModuleElement;
import org.example.models.users.Student.Student;
import org.example.repositories.academique.ModuleElementDao;
import org.example.repositories.academique.ModuleElementDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class ModuleElementServiceImpl implements ModuleElementService {
    private final ModuleElementDao moduleElementDao = new ModuleElementDaoImpl();

    @Override
    public ModuleElement getModuleElementById(String code) throws SQLException {
        return moduleElementDao.findById(code);
    }

    @Override
    public List<ModuleElement> getAllModuleElements() throws SQLException {
        return moduleElementDao.findAll();
    }

    @Override
    public boolean createModuleElement(ModuleElement moduleElement) throws SQLException {
        return moduleElementDao.save(moduleElement);
    }

    @Override
    public boolean updateModuleElement(ModuleElement moduleElement) throws SQLException {
        return moduleElementDao.update(moduleElement);
    }

    @Override
    public boolean deleteModuleElement(String code) throws SQLException {
        return moduleElementDao.delete(code);
    }


    @Override
    public List<ModuleElement> getModuleElementsByProfId(String professorId) throws SQLException {
        // Implement logic to get all ModuleElements by professor ID
        return moduleElementDao.findAllByProfId(professorId);
    }

    @Override
    public List<Student> getSubscribedStudents(ModuleElement selectedmoduleElement) throws SQLException {
        // Implement logic to get all students subscribed to a ModuleElement

        return moduleElementDao.getStudentsByModuleElement(selectedmoduleElement.getCode());
    }
}