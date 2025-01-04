package org.example.controllers;

import org.example.models.academique.Filiere;
import org.example.models.academique.ModuleElement;
import org.example.services.user.ElementService.ElementServiceImpl;
import org.example.services.user.FiliereService.FiliereService;
import org.example.models.academique.Module;
import org.example.services.user.moduleserviceHM.ModuleServiceImpl;
import org.example.zapp.vue.Admin.AdminView;
import org.example.zapp.vue.Admin.AdminViewInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminController {
//  private final AppState state;
//  private final Renderer renderer;

  private AdminViewInterface adminView ;


  public AdminController(AdminViewInterface adminView) {
     this.adminView = AdminView.getInstance();
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
    List<Module> modules = FiliereService.getInstance().getModulesByFiliereCode(code);
    adminView.afficherModulesParFiliere(modules);
  }


  private void afficherFilieres() throws SQLException {
    List<Filiere> filieres = FiliereService.getInstance().getAllFilieres();
   adminView.afficherFilieres(filieres);
  }

  private void rechercherFiliere() throws SQLException {
   String code = adminView.getFiliereCode();
   Optional<Filiere> filiere = FiliereService.getInstance().getFiliereByCode(code);
   if(filiere.isPresent()){
     adminView.afficherFiliere(filiere.get());
   }
  }

  private void supprimerFiliere() throws SQLException {
    String code = adminView.supprimerFiliere();
    FiliereService.getInstance().deleteFiliere(code);
  }

  private void mettreAJourFiliere() throws SQLException {
    Filiere filiere = adminView.mettreAJourFiliere();
    FiliereService.getInstance().updateFiliere(filiere);
  }

  private void ajouterFiliere() throws SQLException {
    Filiere filiere = adminView.ajouterFiliere();
    FiliereService.getInstance().addFiliere(filiere);
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
    ElementServiceImpl.getInstance().addElement(element);
  }
  private void afficherElements() throws SQLException {
    List<ModuleElement> elements = ElementServiceImpl.getInstance().getAllElements();
    adminView.afficherElements(elements);
  }
  private void RechercherElementParCode() throws SQLException {
    String code = adminView.getElementbyCode();
    Optional<ModuleElement> element = ElementServiceImpl.getInstance().getElementByCode(code);
    if(element.isPresent()){
      adminView.afficherElement(element.get());
    }
  }
  private void supprimerElement() throws SQLException {
    String code = adminView.supprimerElement();
    ElementServiceImpl.getInstance().removeElement(code);
  }

  private void mettreAJourElement() throws SQLException {
    ModuleElement element = adminView.mettreAJourElement();
    ElementServiceImpl.getInstance().modifyElement(element);
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
    ModuleServiceImpl.getInstance().addModule(module);
  }
  private void afficherMoudles() throws SQLException {
    List<Module> modules = ModuleServiceImpl.getInstance().getAllModules();
    adminView.afficherModules(modules);
  }
  private void RechercherModuleParCode() throws SQLException {
    String code = adminView.getElementbyCode();
    Optional<Module> module = ModuleServiceImpl.getInstance().getModuleByCode(code);
    if(module.isPresent()){
      adminView.afficherModule(module.get());
    }
  }
  private void supprimerModule() throws SQLException {
    String code = adminView.supprimerModule();
    ModuleServiceImpl.getInstance().deleteModule(code);
  }

  private void mettreAJourModule() throws SQLException {
    Module module = adminView.mettreAJourModule();
    ModuleServiceImpl.getInstance().updateModule(module);
  }
  /*les autres espaces de gestion*/
  private void handleManageProfessors() {
    // Implement logic to manage professors
    System.out.println("Managing professors...");
    // Example: List all professors, add a new professor, etc.
  }

  private void handleManageStudents() {
    // Implement logic to manage students
    System.out.println("Managing students...");
    // Example: List all students, add a new student, etc.
  }

  private void handleManagePrograms() {
    // Implement logic to manage programs
    System.out.println("Managing programs...");
    // Example: List all programs, add a new program, etc.
  }


}