package org.example.repositories.ModuleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.models.academique.*;
import org.example.models.academique.Module;
import org.example.repositories.SuperRepo;

public class ModuleDaoImplhouda extends SuperRepo implements ModuleDaoHouda {
    private static ModuleDaoImplhouda instance;

    private ModuleDaoImplhouda() {
        super(myDatabase);
    }

    public static ModuleDaoImplhouda getInstance() {
        if (instance == null) {
            instance = new ModuleDaoImplhouda();
        }
        return instance;
    }

    @Override
    public Optional<Module> findByCode(String code) throws SQLException {
        String query = "SELECT * FROM module WHERE code = ?";
        try (Connection conn = myDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.ofNullable(mapToModule(rs));  // Return mapped module inside an Optional
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            myDatabase.disconnect();
        }
        return Optional.empty();  // Return empty Optional if module is not found
    }

    @Override
    public List<Module> findAll() throws SQLException {
        String query = "SELECT * FROM module";
        List<Module> modules = new ArrayList<>();

        // Etablissement de la connexion et exécution de la requête
        try (Connection conn = myDatabase.connect();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            // Traitement des résultats et mappage aux objets Module
            while (resultSet.next()) {
                // Récupérer les valeurs du ResultSet
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String semesterStr = resultSet.getString("semester");
                String filiereCode = resultSet.getString("filiereCode");

                // Conversion du semestre
                Semester semester = getSemesterFromString(semesterStr);

                // Récupérer la filière associée
                Filiere filiere = new Filiere(filiereCode);

                // Créer un nouvel objet Module
                Module module = new Module(code, name, semester, filiere);

                // Ajouter le module à la liste
                modules.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la récupération des modules", e);
        }

        return modules;
    }


    @Override
    public void save(Module module) throws SQLException {
        // Vérification de l'existence de la Filière
        try {
            validateFiliere(module.getFiliere().getCode());
        } catch (SQLException e) {
            // Afficher un message d'erreur sans interrompre le programme
            System.out.println("La filière avec le code '" + module.getFiliere().getCode() + "' n'existe pas.");
            return;  // Ne pas continuer l'ajout du module si la filière n'existe pas
        }

        String query = "INSERT INTO module (code, name, semester, filiereCode) VALUES (?, ?, ?, ?)";
        try (Connection conn = myDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, module.getCode());
            stmt.setString(2, module.getName());
            stmt.setString(3, module.getSemester().name());
            stmt.setString(4, module.getFiliere().getCode());
            stmt.executeUpdate();
            System.out.println("Module ajouté avec succès !");  // Message de succès
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de l'ajout du module.", e);
        }
    }

    @Override
    public void update(Module module) throws SQLException {
        String query = "UPDATE module SET name = ?, semester = ? WHERE code = ?";
        try (Connection conn = myDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Mettre à jour le nom du module
            stmt.setString(1, module.getName());
            // Vérification et mise à jour du semestre
            Semester semester = getSemesterFromString(module.getSemester().name());
            if (semester != null) {
                stmt.setString(2, semester.name());
            } else {
                System.out.println("Semestre invalide : " + module.getSemester().name() + ". Aucun changement effectué.");
                return;  // Ne pas effectuer la mise à jour si le semestre est invalide
            }
            // Ne pas toucher à la filière (pas de modification)
            stmt.setString(3, module.getCode());
            // Exécution de la mise à jour
            stmt.executeUpdate();
            System.out.println("Module mis à jour avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la mise à jour du module.", e);
        }
    }

    @Override
    public void delete(String code) throws SQLException {
        String query = "DELETE FROM module WHERE code = ?";
        try (Connection conn = myDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting module", e);
        } finally {
            myDatabase.disconnect();
        }
    }

    private Module mapToModule(ResultSet rs) throws SQLException {
        return new ModuleBuilder()
                .setCode(rs.getString("code"))
                .setName(rs.getString("name"))
                .setSemester(Semester.valueOf(rs.getString("semester").toUpperCase()))
                .setFiliere(findFiliereByCode(rs.getString("filiereCode")))
                .build();
    }

    private Filiere findFiliereByCode(String code) throws SQLException {
        String query = "SELECT * FROM filiere WHERE code = ?";
        try (Connection conn = myDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Filiere(rs.getString("code"), rs.getString("name"));
                }
            }
        } finally {
            myDatabase.disconnect();
        }
        return null; // If Filiere does not exist
    }

    private void validateFiliere(String code) throws SQLException {
        String query = "SELECT COUNT(*) FROM filiere WHERE code = ?";
        try (Connection conn = myDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new SQLException("Filiere with code '" + code + "' does not exist.");
                }
            }
        }
    }

    private Semester getSemesterFromString(String semesterStr) {
        if (semesterStr != null) {
            try {
                return Semester.valueOf(semesterStr);  // Essaie de convertir en Enum
            } catch (IllegalArgumentException e) {
                System.err.println("Semestre invalide : " + semesterStr);  // Affiche un message d'erreur
                return null;  // Retourne null si le semestre n'est pas valide
            }
        }
        return null;  // Retourne null si la chaîne est vide ou nulle
    }
}