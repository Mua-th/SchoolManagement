package org.example.zapp.vue.Admin;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.users.Student.Student;
import org.example.models.users.Student.StudentBuilder;

import java.util.Scanner;
import java.util.List;


public class AdminView implements AdminViewInterface {

  private static AdminView instance;
  private static Scanner scanner = new Scanner(System.in);

  private AdminView() {
  }

  public static AdminView getInstance() {
    if (instance == null) {
      synchronized (AdminView.class) {
        if (instance == null) {
          instance = new AdminView();
        }
      }
    }
    return instance;
  }

  @Override
  public void displayAdminMenu() {
    System.out.println("Welcome Admin!");
    List<String> adminMenu = List.of(
            "1. Gestion des Filières",
            "2. Gestion des Étudiants",
            "3. Gestion des Professeurs",
            "4. Gestion des Modules",
            "5. Gestion des Éléments",
            "6. Logout"

    );
    for (String option : adminMenu) {
      System.out.println(option);
    }
  }

  @Override
  public String getUserChoice() {
    System.out.print("Enter your choice: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public void displayGestionFiliereMenu(){
    System.out.println("\n--- MENU GESTION DES FILIÈRES ---");
    System.out.println("1. Ajouter une filière");
    System.out.println("2. Mettre à jour une filière");
    System.out.println("3. Supprimer une filière");
    System.out.println("4. Afficher toutes les filières");
    System.out.println("5. Rechercher une filière par code");
    System.out.println("6. Afficher les modules d'une filière");
    System.out.println("7. Quitter");
    System.out.print("Entrez votre choix : ");
  }

  @Override
  public void displayGestionetudiantMenu(){
    System.out.println("\n--- MENU GESTION DES ÉTUDIANTS ---");
    System.out.println("1. Ajouter un étudiant");
    System.out.println("2. Mettre à jour un étudiant");
    System.out.println("3. Supprimer un étudiant");
    System.out.println("4. Afficher tous les étudiants");
    System.out.println("5. Rechercher un étudiant par nom");
    System.out.println("6. Quitter");
    System.out.print("Entrez votre choix : ");
  }



  // Ajouter une filière
  @Override
  public Filiere ajouterFiliere() {
    System.out.print("Code : ");
    String code = scanner.nextLine();
    System.out.print("Nom : ");
    String nom = scanner.nextLine();
    Filiere filiere = new Filiere(code, nom);
    System.out.println("Filière ajoutée avec succès !");
    return filiere ;
  }



  // Mettre à jour une filière
  @Override
  public Filiere mettreAJourFiliere() {
    System.out.print("Code : ");
    String code = scanner.nextLine();
    System.out.print("Nouveau nom : ");
    String nom = scanner.nextLine();
    Filiere filiere = new Filiere(code, nom);
    System.out.println("Filière mise à jour avec succès !");
    return filiere ;
  }

  // Supprimer une filière
  @Override
  public String supprimerFiliere() {
    System.out.print("Code : ");
    String code = scanner.nextLine();
    System.out.println("Filière supprimée avec succès !");
    return code ;
  }

  // Afficher toutes les filières
  @Override
  public void afficherFilieres(List<Filiere> filieres) {
    System.out.println("Liste des filières :");
    for (Filiere filiere : filieres) {
      System.out.println(filiere.getCode() + " - " + filiere.getName());
    }
  }

  // Rechercher une filière par code
  @Override
  public String  getFiliereCode() {
    System.out.print("Code de la filière : ");
    String code = scanner.nextLine();
    return code;
  }


  @Override
  public void afficherFiliere(Filiere filiere){
    if (filiere!=null) {
      System.out.println("Filière trouvée : " + filiere.getCode() + " - " + filiere.getName());
    } else {
      System.out.println("Filière non trouvée.");
    }

  }

  @Override
  public void afficherModulesParFiliere(List<Module> modules) {

    if (modules.isEmpty()) {
      System.out.println("Aucun module trouvé pour cette filière.");
    } else {
      System.out.println("Modules associés : ");
      for (Module module : modules) {
        System.out.println(module.getCode() + " - " + module.getName());
      }
    }
  }


  // Ajouter etudiant
  public Student GetStudent() {
    System.out.print("ID : ");
    String id = scanner.nextLine();
    System.out.print("Prénom : ");
    String firstName = scanner.nextLine();
    System.out.print("Nom : ");
    String lastName = scanner.nextLine();
    System.out.print("Code Filière : ");
    String filiereCode = scanner.nextLine();
    Student s = new StudentBuilder().setId(id)
            .setFirstName(firstName)
            .setLastName(lastName)
            .setFiliere(new Filiere(filiereCode))
            .build();
    return s;
    }

  @Override
  public String getStudentName() {
    System.out.print("Le nom de l'étudiant : ");
    String lastname = scanner.nextLine();
    return lastname;
  }

  @Override
  public void afficherStudent(Student student) {
    if (student!=null) {
      System.out.println(" Etudiant trouvée : " + student.getId()+ " - " + student.getFirstName()+ " - " + student.getLastName() + " - " + student.getFiliere().getCode());
    } else {
      System.out.println("Etudiant non trouvée.");
    }

  }


  // Méthode pour mettre à jour un étudiant
  public Student mettreAJourStudent(){
    System.out.print("ID : ");
    String id = scanner.nextLine();
    System.out.print("Nouveau prénom : ");
    String firstName = scanner.nextLine();
    System.out.print("Nouveau nom : ");
    String lastName = scanner.nextLine();
    Student s = new StudentBuilder().setId(id)
            .setFirstName(firstName)
            .setLastName(lastName)
            .build();
    System.out.println("étudiant mise à jour avec succès !");
    return s;
  }

  // Méthode pour supprimer un étudiant
  public String supprimerStudent(){
    System.out.print("ID : ");
    String id = scanner.nextLine();
    System.out.println(" étudiant supprimée avec succès !");
    return id ;

  }

  // Méthode pour afficher tous les étudiants
  public void afficherStudents(List<Student> students){
    System.out.println("Liste des étudiants :");
    for (Student student : students) {
      System.out.println(student.getId()+ " - " + student.getFirstName() + " - " + student.getLastName() + " - " + student.getFiliere().getCode());
    }
  }

  @Override
  public void rechercherStudent(Student s){
    System.out.print("Nom : ");
    String lastName = scanner.nextLine();
    if (s!=null) {
      System.out.println("étudiant trouvée : " + s.getId()+ " - " + s.getFirstName() + " - " + s.getFirstName() + " - " + s.getLastName() + " - " + s.getFiliere().getCode());
    } else {
      System.out.println("étudiant non trouvée.");
    }

  }

  @Override
  public void afficherMessageajoutetudiant(boolean b) {
    if(b){
      System.out.println("Étudiant ajouté avec succès !");
    } else{

      System.out.println("Étudiant non ajouté car la filiére n'existe pas !");

    }
  }


}
