package org.example.dao;

import org.example.models.academique.ModuleElement;

import java.sql.SQLException;
import java.util.List;

public interface ElementDao {
    void add(ModuleElement var1) throws SQLException;

    List<ModuleElement> findAll() throws SQLException;

    ModuleElement findByCode(String var1) throws SQLException;

    void update(ModuleElement var1) throws SQLException;

    void delete(String var1) throws SQLException;
}
