package org.example.repositories.ModuleDAO;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ModuleDaoHouda {
    Optional<Module> findByCode(String module)throws SQLException;

    List<Module> findAll()throws SQLException;

    void save(Module module)throws SQLException;

    void update(Module module)throws SQLException;

    void delete(String module)throws SQLException;

}
