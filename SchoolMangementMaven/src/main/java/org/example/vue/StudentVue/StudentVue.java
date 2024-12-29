package org.example.vue.StudentVue;

import org.example.models.academique.Filiere;
import org.example.services.user.StudentService.StudentService;
import org.example.models.user.Student;
import org.example.services.user.StudentService.StudentServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class StudentVue implements IStudentVue {
    private static StudentVue instance; // Singleton instance
    private final StudentService studentService; // Service interface
    private final Scanner scanner; // Scanner pour la saisie utilisateur

    // Constructeur privé
    private StudentVue() throws SQLException {
        studentService = StudentServiceImpl.getInstance();
        scanner = new Scanner(System.in);
    }

    // Méthode pour obtenir l'instance unique (Singleton)
    public static StudentVue getInstance() throws SQLException{
        if (instance == null) {
            instance = new StudentVue();
        }
        return instance;
    }

    // Menu principal
    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\n--- MENU GESTION DES ÉTUDIANTS ---");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Mettre à jour un étudiant");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Afficher tous les étudiants");
            System.out.println("5. Rechercher un étudiant par nom");
            System.out.println("6. Quitter");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour gérer la nouvelle ligne restante

            switch (choix) {
                case 1:
                    ajouterStudent();
                    break;
                case 2:
                    mettreAJourStudent();
                    break;
                case 3:
                    supprimerStudent();
                    break;
                case 4:
                    afficherStudents();
                    break;
                case 5:
                    rechercherStudent();
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (choix != 6);
    }

    public void ajouterStudent() {
            System.out.print("ID : ");
            String id = scanner.nextLine();
            System.out.print("Prénom : ");
            String firstName = scanner.nextLine();
            System.out.print("Nom : ");
            String lastName = scanner.nextLine();
            System.out.print("Code Filière : ");
            String filiereCode = scanner.nextLine();

            // Rechercher la Filière dans la base de données en fonction du code
            Filiere filiere = studentService.getFiliereByCode(filiereCode);

            if (filiere == null) {
                System.out.println("Filière non trouvée avec le code : " + filiereCode);
                return; // Arrêter l'ajout si la filière n'existe pas
            }

            // Créer l'étudiant avec l'objet Filiere
            Student student = new Student(id, firstName, lastName, filiere);
            studentService.addStudent(student);
            System.out.println("Étudiant ajouté avec succès !");
    }

    // Mettre à jour un étudiant
    public void mettreAJourStudent() {
        System.out.print("ID : ");
        String id = scanner.nextLine();
        System.out.print("Nouveau prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String lastName = scanner.nextLine();

        studentService.updateStudent(id, firstName, lastName);
        System.out.println("Étudiant mis à jour avec succès !");
    }

    // Supprimer un étudiant
    public void supprimerStudent() {
        System.out.print("ID : ");
        String id = scanner.nextLine();
        studentService.deleteStudent(id);
        System.out.println("Étudiant supprimé avec succès !");
    }

    // Afficher tous les étudiants
    public void afficherStudents() {
        List<Student> students = studentService.getAllStudents();
        System.out.println("Liste des étudiants :");
        for (Student student : students) {
            System.out.println(student.getId() + " - " + student.getFirstName() + " " + student.getLastName());
        }
    }

    // Rechercher un étudiant par nom
    public void rechercherStudent() {
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();

        // Appel au service pour rechercher l'étudiant par nom
        Optional<Student> studentOptional = studentService.findStudentByLastName(lastName);

        // Vérification si l'étudiant a été trouvé
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            System.out.println("Étudiant trouvé : " + student.getId() + " - " + student.getFirstName() + " " + student.getLastName());
        } else {
            System.out.println("Étudiant non trouvé.");
        }
    }

}
