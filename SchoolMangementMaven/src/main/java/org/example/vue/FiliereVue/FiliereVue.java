package org.example.vue.FiliereVue;
import org.example.models.academique.Filiere;
import org.example.services.user.FiliereService.FiliereServiceInterface;
import org.example.services.user.FiliereService.FiliereService;
import org.example.models.academique.Module;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FiliereVue implements IFiliereVue {
    private static FiliereVue instance; // Singleton instance
    private final FiliereServiceInterface filiereService; // Service interface
    private final Scanner scanner; // Scanner pour la saisie utilisateur

    // Constructeur privé
    private FiliereVue() {
        filiereService = FiliereService.getInstance();
        scanner = new Scanner(System.in);
    }

    // Méthode pour obtenir l'instance unique (Singleton)
    public static FiliereVue getInstance() {
        if (instance == null) {
            instance = new FiliereVue();
        }
        return instance;
    }

    // Menu principal
    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\n--- MENU GESTION DES FILIÈRES ---");
            System.out.println("1. Ajouter une filière");
            System.out.println("2. Mettre à jour une filière");
            System.out.println("3. Supprimer une filière");
            System.out.println("4. Afficher toutes les filières");
            System.out.println("5. Rechercher une filière par code");
            System.out.println("6. Afficher les modules d'une filière");
            System.out.println("7. Quitter");
            System.out.print("Entrez votre choix : ");


            choix = scanner.nextInt();
            scanner.nextLine(); // Pour gérer la nouvelle ligne restante


        } while (choix != 7);
    }


    // Ajouter une filière
    public void ajouterFiliere() {
        System.out.print("Code : ");
        String code = scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        Filiere filiere = new Filiere(code, nom);

        System.out.println("Filière ajoutée avec succès !");
    }

    // Mettre à jour une filière
    public void mettreAJourFiliere() throws SQLException {
        System.out.print("Code : ");
        String code = scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String nom = scanner.nextLine();

        Filiere filiere = new Filiere(code, nom);
        filiereService.updateFiliere(filiere);
        System.out.println("Filière mise à jour avec succès !");
    }

    // Supprimer une filière
    public void supprimerFiliere() throws SQLException {
        System.out.print("Code : ");
        String code = scanner.nextLine();
        filiereService.deleteFiliere(code);
        System.out.println("Filière supprimée avec succès !");
    }

    // Afficher toutes les filières
    public void afficherFilieres() throws SQLException {
        List<Filiere> filieres = filiereService.getAllFilieres();
        System.out.println("Liste des filières :");
        for (Filiere filiere : filieres) {
            System.out.println(filiere.getCode() + " - " + filiere.getName());
        }
    }

    // Rechercher une filière par code
    public void rechercherFiliere() throws SQLException {
        System.out.print("Code : ");
        String code = scanner.nextLine();

        Optional<Filiere> filiere = filiereService.getFiliereByCode(code);
        if (filiere.isPresent()) {
            Filiere f = filiere.get();
            System.out.println("Filière trouvée : " + f.getCode() + " - " + f.getName());
        } else {
            System.out.println("Filière non trouvée.");
        }
    }

    private void afficherModulesParFiliere() throws SQLException {
        System.out.print("Code de la filière : ");
        String code = scanner.nextLine();

        List<Module> modules = filiereService.getModulesByFiliereCode(code);
        if (modules.isEmpty()) {
            System.out.println("Aucun module trouvé pour cette filière.");
        } else {
            System.out.println("Modules associés : ");
            for (Module module : modules) {
                System.out.println(module.getCode() + " - " + module.getName());
            }
        }
    }

}
