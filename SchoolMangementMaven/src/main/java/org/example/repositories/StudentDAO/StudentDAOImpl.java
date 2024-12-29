package org.example.repositories.StudentDAO;

import org.example.models.academique.Filiere;
import org.example.models.user.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAOImpl implements StudentDAO {

    private static StudentDAOImpl instance;
    private Connection connection;

    private StudentDAOImpl()  {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static StudentDAOImpl getInstance()  {
        if (instance == null) {
            instance = new StudentDAOImpl();
        }
        return instance;
    }

    @Override
    public void addStudent(Student student) {
        String filiereCode = student.getFiliere().getCode();

        // Vérifier si la filière existe dans la table filiere
        String checkFiliereSql = "SELECT COUNT(*) FROM filiere WHERE code = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkFiliereSql)) {
            checkStatement.setString(1, filiereCode);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // La filière existe, ajouter l'étudiant
                String sql = "INSERT INTO students (id, firstName, lastName, Filierecode) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, student.getId());
                    statement.setString(2, student.getFirstName());
                    statement.setString(3, student.getLastName());
                    statement.setString(4, filiereCode);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("La filière avec le code " + filiereCode + " n'existe pas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateStudent(String id, String firstName, String lastName) {
        String sql = "UPDATE students SET firstName = ?, lastName = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, id);
            statement.executeUpdate(); // Ne retourne rien
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate(); // Ne retourne rien
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<Student> findStudentByLastName(String lastName) {
        String query = "SELECT * FROM students WHERE lastName = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lastName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student(
                            rs.getString("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            null // Filtrage de la Filière si nécessaire
                    );
                    return Optional.of(student); // Retourner l'étudiant encapsulé dans un Optional
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty(); // Si aucun étudiant n'est trouvé, retourner un Optional vide
    }


    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM students";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Filiere getFiliereByCode(String filiereCode) {
        String sql = "SELECT * FROM filiere WHERE code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // On prépare la requête avec le code de la filière
            statement.setString(1, filiereCode);
            // On exécute la requête
            ResultSet resultSet = statement.executeQuery();

            // Si un résultat est trouvé
            if (resultSet.next()) {
                // Récupérer les données de la filière
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                // Créer un objet Filiere et le retourner
                return new Filiere(code, name);
            } else {
                return null; // Aucun résultat trouvé
            }
        } catch (SQLException e) {
            // Gestion de l'exception SQL
            System.out.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
            return null; // Retourner null si une exception survient
        }
    }

}