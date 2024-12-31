package org.example.services.user.moduleserviceHM;

import org.example.dao.ModuleDaoHouda;
import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;
import java.util.List;

public class ModuleServiceImpl implements ModuleService {
    private final ModuleDaoHouda moduleDao;

    public ModuleServiceImpl(ModuleDaoHouda moduleDao) {
        this.moduleDao = moduleDao;
    }

    public org.example.models.academique.Module getModuleByCode(String code) {
        return this.moduleDao.findByCode(code);
    }

    public List<org.example.models.academique.Module> getAllModules() {
        return this.moduleDao.findAll();
    }

    public void addModule(org.example.models.academique.Module module) {
        if (module.getFiliere() == null) {
            throw new IllegalArgumentException("Module's Filiere cannot be null.");
        } else {
            this.moduleDao.save(module);
        }
    }

    public void updateModule(Module module) {
        this.moduleDao.update(module);
    }

    public void deleteModule(String code) {
        this.moduleDao.delete(code);
    }

    public List<Semester> getAllSemesters() {
        return this.moduleDao.getAllSemesters();
    }

    public List<Filiere> getAllFilieres() {
        return this.moduleDao.getAllFilieres();
    }
}
