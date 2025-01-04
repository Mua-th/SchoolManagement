package org.example.repositories.ElementDAO;

import org.example.models.academique.ModuleElement;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ElementDao {
    void add(ModuleElement element) throws SQLException;

    List<ModuleElement> findAll() throws SQLException;

    Optional<ModuleElement> findByCode(String element) throws SQLException;

    void update(ModuleElement element) throws SQLException;

    void delete(String element) throws SQLException;
}
