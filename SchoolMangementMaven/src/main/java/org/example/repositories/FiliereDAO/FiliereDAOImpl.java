package org.example.repositories.FiliereDAO;
import org.example.models.academique.Filiere;
import org.example.models.academique.Module;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.sql.*;


public class FiliereDAOImpl implements FiliereDAO {
    private static FiliereDAOImpl instance;
    private Connection connection;

    private FiliereDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static FiliereDAOImpl getInstance() {
        if (instance == null) {
            instance = new FiliereDAOImpl();
        }
        return instance;
    }

    @Override
    public void addFiliere(Filiere filiere) {
        String query = "INSERT INTO filiere (code, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiere.getCode());
            stmt.setString(2, filiere.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFiliere(String code) {
        String query = "DELETE FROM filiere WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFiliere(Filiere filiere) {
        String query = "UPDATE filiere SET name = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiere.getName());
            stmt.setString(2, filiere.getCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Filiere> getAllFilieres() {
        List<Filiere> filieres = new ArrayList<>();
        String query = "SELECT * FROM filiere";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Filiere filiere = new Filiere(rs.getString("code"), rs.getString("name"));
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filieres;
    }

    @Override
    public Optional<Filiere> getFiliereByCode(String code) {
        String query = "SELECT * FROM filiere WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Filiere filiere = new Filiere(rs.getString("code"), rs.getString("name"));
                    return Optional.of(filiere);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean checkIfFiliereExists(String code) {
        String query = "SELECT 1 FROM filiere WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Module> getModulesByFiliereCode(String code) { // Signature identique
        List<Module> modules = new ArrayList<>();
        String query = "SELECT * FROM module WHERE filiereCode = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Module module = new Module(
                            rs.getString("code"),
                            rs.getString("name")
                    );
                    modules.add(module);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

}