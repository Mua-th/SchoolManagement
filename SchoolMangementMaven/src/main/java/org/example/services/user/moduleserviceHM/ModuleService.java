package org.example.services.user.moduleserviceHM;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;

import java.util.List;


public interface ModuleService {

        Module getModuleByCode(String var1);

        List<Module> getAllModules();

        void addModule(Module var1);

        void updateModule(Module var1);

        void deleteModule(String var1);

        List<Semester> getAllSemesters();

        List<Filiere> getAllFilieres();

}
