package org.example.services.user.ElementService;

import org.example.dao.ElementDao;
import org.example.models.academique.ModuleElement;
import org.example.repositories.academique.ModuleElementDao;

import java.sql.SQLException;
import java.util.List;

public class ElementServiceImpl implements ElementService {
    private final ElementDao moduleElementDao;

    public ElementServiceImpl(ModuleElementDao moduleElementDao) {
        this.moduleElementDao = moduleElementDao;
    }

    public void addElement(ModuleElement element) throws SQLException {
        this.moduleElementDao.add(element);
    }

    public List<ModuleElement> getAllElements() throws SQLException {
        return this.moduleElementDao.findAll();
    }

    public ModuleElement getElementByCode(String code) throws SQLException {
        return this.moduleElementDao.findByCode(code);
    }

    public void modifyElement(ModuleElement element) throws SQLException {
        this.moduleElementDao.update(element);
    }

    public void removeElement(String code) throws SQLException {
        this.moduleElementDao.delete(code);
    }
}
