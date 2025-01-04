package org.example.services.user.ElementService;

import org.example.repositories.ElementDAO.ElementDao;
import org.example.models.academique.ModuleElement;
import org.example.repositories.ElementDAO.ElementDaoImpl;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ElementServiceImpl implements ElementService {

    // Instance unique de ElementService (Singleton)
    private static ElementServiceImpl instance;
    private ElementDao elementdao = ElementDaoImpl.getInstance();




    // Constructeur privé pour empêcher l'instanciation directe
    private ElementServiceImpl() {}

    // Méthode pour obtenir l'instance unique de ElementService
    public static ElementServiceImpl getInstance() {
        if (instance == null) {
            instance = new ElementServiceImpl();
        }
        return instance;
    }
    public void addElement(ModuleElement element) throws SQLException {
        elementdao.add(element);
    }

    public List<ModuleElement> getAllElements() throws SQLException {
        return elementdao.findAll();
    }

    public Optional<ModuleElement> getElementByCode(String code) throws SQLException {
        return elementdao.findByCode(code);
    }

    public void modifyElement(ModuleElement element) throws SQLException {
        elementdao.update(element);
    }

    public void removeElement(String code) throws SQLException {
        elementdao.delete(code);
    }
}
