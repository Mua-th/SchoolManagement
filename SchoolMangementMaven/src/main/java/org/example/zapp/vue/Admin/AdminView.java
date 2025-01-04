package org.example.zapp.vue.Admin;




import org.example.models.academique.*;

import org.example.models.academique.Filiere;

import org.example.models.academique.Module;
import org.example.models.users.Student.Student;
import org.example.models.users.Student.StudentBuilder;

import java.util.InputMismatchException;
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
    System.out.print("veuillez entrer votre choix: ");
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
    System.out.print("Entrez votre choix --Menu spécifique-- : ");
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


  @Override
  public void displayGestionElementMenu() {
    System.out.println("\n--- Menu Gestion des Éléments du Module ---");
    System.out.println("1. Ajouter un nouvel élément");
    System.out.println("2. Mettre à jour un élément");
    System.out.println("3. Rechercher une élément par code");
    System.out.println("4. Supprimer un élément");
    System.out.println("5. Afficher tous les éléments");
    System.out.println("6. Quitter");
    System.out.print("Entrez votre choix :");
  }

  @Override
  public ModuleElement ajouterElement() {
    System.out.print("Code : ");
    String code = scanner.nextLine();
    double coefficient = 0.0;
    boolean validInput = false;

    while(!validInput) {
      try {
        System.out.print("Coefficient (entre 0 et 1) : ");
        coefficient = scanner.nextDouble();
        if (!(coefficient < 0.0) && !(coefficient > 1.0)) {
          validInput = true;
        } else {
          System.err.println("Erreur : Le coefficient doit être compris entre 0 et 1.");
        }
      } catch (InputMismatchException var9) {
        System.err.println("Erreur : Le coefficient doit être un nombre valide entre 0 et 1.");
        scanner.nextLine();
      }
    }

    boolean isValidated = false;
    validInput = false;

    while(!validInput) {
      try {
        System.out.print("Validé (true/false) : ");
        isValidated = scanner.nextBoolean();
        validInput = true;
      } catch (InputMismatchException var8) {
        System.err.println("Erreur : Veuillez entrer true ou false pour la validation.");
        scanner.nextLine();
      }
    }

    scanner.nextLine();
    System.out.print("Code du module : ");
    String moduleCode = scanner.nextLine();
    ModuleElement newElement = new ModuleElement(code, coefficient, false ,new Module(moduleCode));
    System.out.println("Élément ajouté avec succès !");
    return newElement ;
  }

  @Override
  public ModuleElement mettreAJourElement() {
    System.out.print("Code de l'élément à modifier : ");
    String code = scanner.nextLine();
    System.out.print("Nouveau coefficient : ");
    double coefficient = scanner.nextDouble();
    System.out.print("Validé (true/false) : ");
    boolean isValidated = scanner.nextBoolean();
    scanner.nextLine();
    System.out.print("Nouveau code du module : ");
    String moduleCode = scanner.nextLine();
    ModuleElement updatedElement = new ModuleElement(code, coefficient, isValidated, new Module( moduleCode ));
    System.out.println("Élément mis à jour avec succès !");
    return updatedElement;
  }

  @Override
  public String supprimerElement() {
    System.out.print("Code : ");
    String code = scanner.nextLine();
    System.out.println("élément supprimée avec succès !");
    return code ;
  }

  @Override
  public void afficherElements(List<ModuleElement> Elements) {
    System.out.println("Liste des éléments :");
    for (ModuleElement element : Elements) {
      System.out.println(element.getCode() + " - " + element.getCoefficient() + " - " + element.isValidated() + " - " + element.getParentModule().getCode());
    }
  }

  // Ajouter etudiant
    @Override
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
  @Override
  public void afficherStudents(List<Student> students){
    System.out.println("Liste des étudiants :");
    for (Student student : students) {
      System.out.println(student.getId()+ " - " + student.getFirstName() + " - " + student.getLastName() + " - " + student.getFiliere().getCode());

    }
  }

  @Override

  public String getElementbyCode() {
    System.out.print("Code de l'élément : ");
    String code = scanner.nextLine();
    return code;
  }
  @Override
  public void afficherElement(ModuleElement element){
    if (element!=null) {
      System.out.println("élement trouvé : " + element.getCode() + " - " + element.getCoefficient()+ " - " +element.getParentModule().getCode());
    } else {
      System.out.println("élément non trouvé.");
    }}

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

  public void displayGestionModuleMenu() {
    System.out.println("\n--- Menu Gestion des Modules du Module ---");
    System.out.println("1. Ajouter un nouvel Module");
    System.out.println("2. Mettre à jour un Module");
    System.out.println("3. Rechercher une Module par code");
    System.out.println("4. Supprimer un Module");
    System.out.println("5. Afficher tous les Modules");
    System.out.println("6. Quitter");
    System.out.print("Entrez votre choix concernant la gestion des modules:");
  }

  @Override
  public Module ajouterModule() {
    System.out.print("Code : ");
    String code = scanner.nextLine();
    System.out.print("Nom : ");
    String nom = scanner.nextLine();
    System.out.print("Semestre : ");
    String semestre = scanner.nextLine();
    System.out.print("Filière code : ");
    String filiereCode = scanner.nextLine();
    // Utilisation du ModuleBuilder
    Module module = new ModuleBuilder()
            .setCode(code)
            .setName(nom)
            .setSemester(Semester.valueOf(semestre))
            .setFiliere(new Filiere(filiereCode, null))
            .build(); // Construction de l'objet Module
    return module;
  }

  @Override
  public Module mettreAJourModule() {
    System.out.print("Code du Module à modifier : ");
    String code = scanner.nextLine();
    System.out.print("Nouveau nom du Module : ");
    String nom = scanner.nextLine();
    System.out.print("Nouveau semestre : ");
    String semestre = scanner.nextLine();

    // Création d'une instance de Module avec le builder pour mettre à jour
    Module module = new ModuleBuilder()
            .setCode(code)
            .setName(nom)
            .setSemester(Semester.valueOf(semestre))// Nouvelle Filière avec seulement le code
            .build(); // Construction du module mis à jour
    return module; // Retour du module mis à jour
  }
  @Override
  public String supprimerModule() {
    System.out.print("Code : ");
    String code = scanner.nextLine();
    System.out.println("le module supprimée avec succès !");
    return code ;
  }

  @Override
  public void afficherModules(List<Module> modules) {
    System.out.println("Liste des Modules :");
    for (Module module : modules) {
      System.out.println(module.getCode() + " - " + module.getName() + " - " + module.getSemester() );
    }
  }

  @Override
  public String getModulebyCode() {
    System.out.print("Code du module : ");
    String code = scanner.nextLine();
    return code;
  }

  @Override
  public void afficherModule(Module module) {
    if (module!=null) {
      System.out.println("Module trouvé : " + module.getCode() + " - " + module.getName()+ " - " +module.getSemester()+ " - " +module.getFiliere());
    } else {
      System.out.println("Module non trouvé.");
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
