package org.example.services.user.moduleserviceHM;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface ModuleService {

        Optional<Module> getModuleByCode(String module) throws SQLException;

        List<Module> getAllModules()throws SQLException;

        void addModule(Module module)throws SQLException;

        void updateModule(Module module)throws SQLException;

        void deleteModule(String module)throws SQLException;

}
