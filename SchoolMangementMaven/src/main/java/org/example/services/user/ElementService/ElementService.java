package org.example.services.user.ElementService;

import org.example.models.academique.ModuleElement;

import java.sql.SQLException;
import java.util.List;

public interface ElementService {
    List<ModuleElement> getAllElements() throws SQLException;

    ModuleElement getElementByCode(String var1) throws SQLException;

    void addElement(ModuleElement var1) throws SQLException;

    void modifyElement(ModuleElement var1) throws SQLException;

    void removeElement(String var1) throws SQLException;
}
