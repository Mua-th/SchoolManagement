package org.example.dao;


import java.util.List;
import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;

public interface ModuleDao {
    Module findByCode(String var1);

    List<Module> findAll();

    void save(Module var1);

    void update(Module var1);

    void delete(String var1);

    List<Filiere> getAllFilieres();

    List<Semester> getAllSemesters();
}
