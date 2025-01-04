package org.example.repositories.StudentDAO;

import org.example.models.academique.Filiere;
import org.example.models.users.Student.Student;
import org.example.repositories.SuperRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAOImpl extends SuperRepo implements StudentDAO {

    public StudentDAOImpl() {
        super(myDatabase);
    }

    private static StudentDAOImpl instance;

    public static StudentDAOImpl getInstance()  {
        if (instance == null) {
            instance = new StudentDAOImpl();
        }
        return instance;
    }


    @Override
    public void addStudent(Student student) throws SQLException {
        String filiereCode = student.getFiliere().getCode();
        // Vérifier si la filière existe dans la table filiere
        String checkFiliereSql = "SELECT COUNT(*) FROM filiere WHERE code = ?";
        Connection connection = myDatabase.connect() ;
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
        finally {
            myDatabase.disconnect();
        }
    }


    @Override
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET firstName = ?, lastName = ? WHERE id = ?";
        Connection connection = myDatabase.connect() ;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            myDatabase.disconnect();
        }
    }

    @Override
    public void deleteStudent(String id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        Connection connection = myDatabase.connect() ;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            myDatabase.disconnect();
        }
    }


    @Override
    public Optional<Student> findStudentByLastName(String lastName) throws SQLException {
        String query = "SELECT * FROM students WHERE lastName = ?";
        Connection connection = myDatabase.connect() ;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lastName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student(
                            rs.getString("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            new Filiere(rs.getString("Filierecode"))
                    );
                    return Optional.of(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            myDatabase.disconnect();
        }

        return Optional.empty();
    }


    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM students";
            Connection connection = myDatabase.connect() ;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        new Filiere(resultSet.getString("Filierecode"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            myDatabase.disconnect();
        }
        return students;
    }

    public Filiere getFiliereByCode(String filiereCode) throws SQLException {
        String sql = "SELECT * FROM filiere WHERE code = ?";
        Connection connection = myDatabase.connect() ;
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
        } finally {
            myDatabase.disconnect();
        }
    }




}