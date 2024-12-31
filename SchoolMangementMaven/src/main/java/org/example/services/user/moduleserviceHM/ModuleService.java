package org.example.services.user.moduleserviceHM;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;

import java.util.List;


public interface ModuleService {

        org.example.models.academique.Module getModuleByCode(String var1);

        List<org.example.models.academique.Module> getAllModules();

        void addModule(org.example.models.academique.Module var1);

        void updateModule(Module var1);

        void deleteModule(String var1);

        List<Semester> getAllSemesters();

        List<Filiere> getAllFilieres();

}
