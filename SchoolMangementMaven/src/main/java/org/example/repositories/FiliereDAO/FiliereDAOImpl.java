package org.example.repositories.FiliereDAO;
import org.example.config.Database;
import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.repositories.SuperRepo;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.sql.*;


public class FiliereDAOImpl extends SuperRepo implements FiliereDAO {


    public FiliereDAOImpl() {
        super(myDatabase);
    }

    private static FiliereDAOImpl instance ;
    public static FiliereDAOImpl getInstance(){
        if(instance == null) {
            instance = new FiliereDAOImpl();

        }
        return instance ;
    }

    @Override
    public void addFiliere(Filiere filiere) throws SQLException {
        String query = "INSERT INTO filiere (code, name) VALUES (?, ?)";
        Connection connection = myDatabase.connect() ;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiere.getCode());
            stmt.setString(2, filiere.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            myDatabase.disconnect();
        }
    }

    @Override
    public void deleteFiliere(String code) throws SQLException {
        String query = "DELETE FROM filiere WHERE code = ?";
        Connection connection = myDatabase.connect() ;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            myDatabase.disconnect();
        }
    }

    @Override
    public void updateFiliere(Filiere filiere) throws SQLException {
        String query = "UPDATE filiere SET name = ? WHERE code = ?";

        Connection connection = myDatabase.connect();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiere.getName());
            stmt.setString(2, filiere.getCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            myDatabase.disconnect();
        }
    }

    @Override
    public List<Filiere> getAllFilieres() throws SQLException {
        List<Filiere> filieres = new ArrayList<>();
        String query = "SELECT * FROM filiere";
        Connection connection = myDatabase.connect();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Filiere filiere = new Filiere(rs.getString("code"), rs.getString("name"));
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            myDatabase.disconnect();
        }
        return filieres;
    }

    @Override
    public Optional<Filiere> getFiliereByCode(String code) throws SQLException {
        String query = "SELECT * FROM filiere WHERE code = ?";
        Connection connection = myDatabase.connect();
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
        finally {
            myDatabase.disconnect();
        }
        return Optional.empty();
    }

    public boolean checkIfFiliereExists(String code) throws SQLException {
        String query = "SELECT 1 FROM filiere WHERE code = ?";
        Connection connection = myDatabase.connect();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            myDatabase.disconnect();
        }
        return false;
    }

    @Override
    public List<Module> getModulesByFiliereCode(String code) throws SQLException { // Signature identique
        List<Module> modules = new ArrayList<>();
        String query = "SELECT * FROM module WHERE filiereCode = ?";
        Connection connection = myDatabase.connect();
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
        }finally {
            myDatabase.disconnect();
        }
        return modules;
    }

}