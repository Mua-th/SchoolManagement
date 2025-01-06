package org.example.controllers;

import org.example.models.academique.Filiere;
import org.example.models.academique.ModuleElement;
import org.example.services.user.ElementService.ElementService;
import org.example.services.user.ElementService.ElementServiceImpl;
import org.example.services.user.FiliereService.FiliereService;
import org.example.models.academique.Module;
import org.example.services.user.StudentServicesabrin.StudentService;
import org.example.services.user.moduleserviceHM.ModuleService;
import org.example.services.user.moduleserviceHM.ModuleServiceImpl;
import org.example.models.users.Student.Student;

import org.example.services.user.FiliereService.FiliereService;
import org.example.models.academique.Module;
import org.example.services.user.StudentServicesabrin.StudentServiceImpl;

import org.example.zapp.vue.Admin.AdminView;
import org.example.zapp.vue.Admin.AdminViewInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminController {
//  private final AppState state;
//  private final Renderer renderer;

  private AdminViewInterface adminView ;

  private FiliereService filiereService ;
  private ModuleService moduleService ;

  private ElementService elementService ;


  private StudentService studentService;





  public AdminController(FiliereService filiereService, ElementService elementService, ModuleService moduleService, StudentService studentService , AdminViewInterface adminView) {
    this.filiereService = filiereService;
    this.elementService = elementService;
    this.moduleService = moduleService;
    this.studentService = studentService;
    this.adminView = adminView;
  }

  public void handleInput() throws SQLException {

    String choice = adminView.getUserChoice();

    switch (choice) {
      case "1":
        handleGestionFilières();
        break;
      case "2":
        handleManageStudents();
        break;
      case "3":
        break;
      case "4":
        handleGestionModules();
//        state.setAuthenticated(false);
//        state.setUsername(null);
//        state.setCurrentMenu("login");
//        System.out.println("\nYou have logged out successfully.");
        break;
      case "5":
        handleGestionElements();
        break;
      default:
        System.out.println("\nInvalid choice. Please try again.");
    }

   // renderer.render();
  }

  private void handleGestionFilières() throws SQLException {
    AdminView.getInstance().displayGestionFiliereMenu();
    String choix = adminView.getUserChoice();
    handleMenuFiliere(choix);
  }
  private void handleGestionElements() throws SQLException {
    AdminView.getInstance().displayGestionElementMenu();
    String choix = adminView.getUserChoice();
    handleMenuElement(choix);
  }
  private void handleGestionModules() throws SQLException {
    AdminView.getInstance().displayGestionModuleMenu();
    String choix = adminView.getUserChoice();
    handleMenuModule(choix);
  }

  private void handleManageStudents() throws SQLException {
    AdminView.getInstance().displayGestionetudiantMenu();
    String choix = adminView.getUserChoice();
    handleMenuStudents(choix);
  }

  public void handleMenuFiliere(String  choix) throws SQLException {
    switch (choix) {
      case "1":
        ajouterFiliere();
        break;
      case "2":
        mettreAJourFiliere();
        break;
      case "3":
        supprimerFiliere();
        break;
      case "4":
        afficherFilieres();
        break;
      case "5":
        rechercherFiliere();
        break;
      case "6":
        afficherModulesParFiliere();
        break;
      case "7":
        System.out.println("Au revoir !");
        break;
      default:
        System.out.println("Choix invalide, veuillez réessayer.");
        break;
    }
  }

  private void afficherModulesParFiliere() throws SQLException {
    String code = adminView.getFiliereCode();
    List<Module> modules = filiereService.getModulesByFiliereCode(code);
    adminView.afficherModulesParFiliere(modules);
  }


  private void afficherFilieres() throws SQLException {
    List<Filiere> filieres = filiereService.getAllFilieres();
   adminView.afficherFilieres(filieres);
  }



  private void rechercherFiliere() throws SQLException {
   String code = adminView.getFiliereCode();
   Optional<Filiere> filiere = filiereService.getFiliereByCode(code);
   if(filiere.isPresent()){
     adminView.afficherFiliere(filiere.get());
   }
  }

  private void supprimerFiliere() throws SQLException {
    String code = adminView.supprimerFiliere();
    filiereService.deleteFiliere(code);
  }



  private void mettreAJourFiliere() throws SQLException {
    Filiere filiere = adminView.mettreAJourFiliere();
   filiereService.updateFiliere(filiere);
  }



  private void ajouterFiliere() throws SQLException {
    Filiere filiere = adminView.ajouterFiliere();
    filiereService.addFiliere(filiere);
  }
/*Gestion des éléments*/
  public void handleMenuElement(String  choix) throws SQLException {
    switch (choix) {
      case "1":
        ajouterElement();
        break;
      case "2":
        mettreAJourElement();
        break;
      case "3":
        RechercherElementParCode();
        break;
      case "4":
        supprimerElement();
        break;
      case "5":
        afficherElements();
        break;
      case "6":
        System.out.println("Au revoir !");
        break;
      default:
        System.out.println("Choix invalide, veuillez réessayer.");
        break;
    }
  }

  private void ajouterElement() throws SQLException {
    ModuleElement element = adminView.ajouterElement();
    elementService.addElement(element);
  }
  private void afficherElements() throws SQLException {
    List<ModuleElement> elements = elementService.getAllElements();
    adminView.afficherElements(elements);
  }
  private void RechercherElementParCode() throws SQLException {
    String code = adminView.getElementbyCode();
    Optional<ModuleElement> element = elementService.getElementByCode(code);
    if(element.isPresent()){
      adminView.afficherElement(element.get());
    }
  }
  private void supprimerElement() throws SQLException {
    String code = adminView.supprimerElement();
    elementService.removeElement(code);
  }

  private void mettreAJourElement() throws SQLException {
    ModuleElement element = adminView.mettreAJourElement();
    elementService.modifyElement(element);
  }

  /*Gestion des éléments*/
  public void handleMenuModule(String  choix) throws SQLException {
    switch (choix) {
      case "1":
        ajouterModule();
        break;
      case "2":
        mettreAJourModule();
        break;
      case "3":
        RechercherModuleParCode();
        break;
      case "4":
        supprimerModule();
        break;
      case "5":
        afficherMoudles();
        break;
      case "6":
        System.out.println("Au revoir !");
        break;
      default:
        System.out.println("Choix invalide, veuillez réessayer.");
        break;
    }
  }
/*Gestion des Modules*/
  private void ajouterModule() throws SQLException {
    Module module = adminView.ajouterModule();
    moduleService.addModule(module);
  }
  private void afficherMoudles() throws SQLException {
    List<Module> modules = moduleService.getAllModules();
    adminView.afficherModules(modules);
  }
  private void RechercherModuleParCode() throws SQLException {
    String code = adminView.getElementbyCode();
    Optional<Module> module = moduleService.getModuleByCode(code);
    if(module.isPresent()){
      adminView.afficherModule(module.get());
    }
  }
  private void supprimerModule() throws SQLException {
    String code = adminView.supprimerModule();
   moduleService.deleteModule(code);
  }


  private void mettreAJourModule() throws SQLException {
    Module module = adminView.mettreAJourModule();
    moduleService.updateModule(module);
  }

  private void handleManageProfessors() {
    // Implement logic to manage professors
    System.out.println("Managing professors...");
    // Example: List all professors, add a new professor, etc.
  }


  private void handleManagePrograms() {
    // Implement logic to manage programs
    System.out.println("Managing programs...");
    // Example: List all programs, add a new program, etc.
  }

  // partie étudiants

  public void handleMenuStudents(String  choix) throws SQLException{

    switch (choix) {
      case "1":
        ajouterStudent();
        break;
      case "2":
        mettreAJourStudent();
        break;
      case "3":
        supprimerStudent();
        break;
      case "4":
        afficherStudents();
        break;
      case "5":
        rechercherStudent();
        break;
      case "6":
        System.out.println("Au revoir !");
        break;
      default:
        System.out.println("Choix invalide, veuillez réessayer.");
        break;

  }


  }

  //Ajouter etudiant
  private void ajouterStudent() throws SQLException {
    Student student = adminView.GetStudent();
    adminView.afficherMessageajoutetudiant(StudentServiceImpl.getInstance().addStudent(student));
  }

  private void mettreAJourStudent() throws SQLException {
    Student student = adminView.mettreAJourStudent();
    studentService.updateStudent(student);
  }

  private void supprimerStudent() throws SQLException {
    String id = adminView.supprimerStudent();
    studentService.deleteStudent(id);
  }

  private void afficherStudents() throws SQLException {
    List<Student> students = studentService.getAllStudents();
    adminView.afficherStudents(students);
  }

  private void rechercherStudent() throws SQLException {
    String lastName = adminView.getStudentName();
    Optional<Student> student = studentService.findStudentByLastName(lastName);
    if(student.isPresent()){
      adminView.afficherStudent(student.get());
    }
  }







}