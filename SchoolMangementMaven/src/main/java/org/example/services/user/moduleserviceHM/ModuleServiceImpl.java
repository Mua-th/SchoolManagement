package org.example.services.user.moduleserviceHM;

import org.example.repositories.ModuleDAO.ModuleDaoHouda;
import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;
import org.example.repositories.ModuleDAO.ModuleDaoImplhouda;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ModuleServiceImpl implements ModuleService {
    public static ModuleServiceImpl instance;
    private final ModuleDaoHouda moduleDao= ModuleDaoImplhouda.getInstance();

    // Constructeur privé pour empêcher l'instanciation directe
    private ModuleServiceImpl() {}

    // Méthode pour obtenir l'instance unique de ElementService
    public static ModuleServiceImpl getInstance() {
        if (instance == null) {
            instance = new ModuleServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<Module> getModuleByCode(String code) throws SQLException {
        return moduleDao.findByCode(code);
    }

    @Override
    public List<Module> getAllModules() throws SQLException {
        return moduleDao.findAll();
    }

    @Override
    public void addModule(Module module) throws SQLException {
        if (module.getFiliere() == null) {
            throw new IllegalArgumentException("Module's Filiere cannot be null.");
        }
        moduleDao.save(module);
    }

    @Override
    public void updateModule(Module module) throws SQLException {
        moduleDao.update(module);
    }

    @Override
    public void deleteModule(String code) throws SQLException {
        moduleDao.delete(code);
    }


}
