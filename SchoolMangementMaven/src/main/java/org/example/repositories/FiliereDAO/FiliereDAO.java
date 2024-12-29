package org.example.repositories.FiliereDAO;
import org.example.models.academique.Module;
import org.example.models.academique.Filiere;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FiliereDAO {
    void addFiliere(Filiere filiere) throws SQLException;
    void deleteFiliere(String code) throws SQLException;
    void updateFiliere(Filiere filiere) throws SQLException;
    List<Filiere> getAllFilieres() throws SQLException;
    Optional<Filiere> getFiliereByCode(String code) throws SQLException;
    List<Module> getModulesByFiliereCode(String code) throws SQLException;
}
