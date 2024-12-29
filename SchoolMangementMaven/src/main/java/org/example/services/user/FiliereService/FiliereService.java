package org.example.services.user.FiliereService;
import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.repositories.FiliereDAO.FiliereDAO;
import org.example.repositories.FiliereDAO.FiliereDAOImpl;
import org.example.repositories.StudentGradeRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
public class FiliereService {
    private final FiliereDAO filiereDAO = FiliereDAOImpl.getInstance();

    public void addFiliere(Filiere filiere, String userId) {
        filiereDAO.addFiliere(filiere, userId);
    }

    public void deleteFiliere(String code, String userId) {
        filiereDAO.deleteFiliere(code, userId);
    }

    public void updateFiliere(Filiere filiere, String userId) {
        filiereDAO.updateFiliere(filiere, userId);
    }

    public List<Filiere> getAllFilieres() {
        return filiereDAO.getAllFilieres();
    }

    public Optional<Filiere> getFiliereByCode(String code) {
        return filiereDAO.getFiliereByCode(code);
    }
}*/


public class FiliereService implements FiliereServiceInterface{
    // Instance unique de FiliereService (Singleton)
    private static FiliereService instance;
    private FiliereDAO filiereDAO = FiliereDAOImpl.getInstance();




    // Constructeur privé pour empêcher l'instanciation directe
    private FiliereService() {}

    // Méthode pour obtenir l'instance unique de FiliereService
    public static FiliereService getInstance() {
        if (instance == null) {
            instance = new FiliereService();
        }
        return instance;
    }

    public void addFiliere(Filiere filiere) throws SQLException {
        filiereDAO.addFiliere(filiere);
    }

    public void deleteFiliere(String code) throws SQLException {
        filiereDAO.deleteFiliere(code);
    }

    public void updateFiliere(Filiere filiere) throws SQLException {
        filiereDAO.updateFiliere(filiere);
    }

    public List<Filiere> getAllFilieres() throws SQLException {
        return filiereDAO.getAllFilieres();
    }

    public Optional<Filiere> getFiliereByCode(String code) throws SQLException {
        return filiereDAO.getFiliereByCode(code);
    }

    @Override
    public List<Module> getModulesByFiliereCode(String code) throws SQLException {
        return filiereDAO.getModulesByFiliereCode(code);
    }
}