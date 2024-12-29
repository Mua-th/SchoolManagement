package org.example.vue.ElementVue;

import org.example.models.academique.ModuleElement;
import org.example.services.user.ElementService.ElementService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ElementViewImpl implements ElementView{
    private final ElementService service;
    private final Scanner scanner;

    public ElementViewImpl(ElementService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void displayAllElements() throws SQLException {
        System.out.println("Liste des éléments du module :");
        List<ModuleElement> elements = this.service.getAllElements();
        Iterator var2 = elements.iterator();

        while(var2.hasNext()) {
            ModuleElement element = (ModuleElement)var2.next();
            System.out.println(element);
        }

    }

    public void displayElementDetails(String code) throws SQLException {
        ModuleElement element = this.service.getElementByCode(code);
        if (element != null) {
            System.out.println("Détails de l'élément : " + element);
        } else {
            System.out.println("Aucun élément trouvé avec le code : " + code);
        }

    }

    public void addElement() throws SQLException {
        System.out.println("Ajout d'un nouvel élément :");
        System.out.print("Code : ");
        String code = this.scanner.nextLine();
        double coefficient = 0.0;
        boolean validInput = false;

        while(!validInput) {
            try {
                System.out.print("Coefficient (entre 0 et 1) : ");
                coefficient = this.scanner.nextDouble();
                if (!(coefficient < 0.0) && !(coefficient > 1.0)) {
                    validInput = true;
                } else {
                    System.err.println("Erreur : Le coefficient doit être compris entre 0 et 1.");
                }
            } catch (InputMismatchException var9) {
                System.err.println("Erreur : Le coefficient doit être un nombre valide entre 0 et 1.");
                this.scanner.nextLine();
            }
        }

        boolean isValidated = false;
        validInput = false;

        while(!validInput) {
            try {
                System.out.print("Validé (true/false) : ");
                isValidated = this.scanner.nextBoolean();
                validInput = true;
            } catch (InputMismatchException var8) {
                System.err.println("Erreur : Veuillez entrer true ou false pour la validation.");
                this.scanner.nextLine();
            }
        }

        this.scanner.nextLine();
        System.out.print("Code du module : ");
        String moduleCode = this.scanner.nextLine();
        ModuleElement newElement = new ModuleElement(code, coefficient, moduleCode);
        this.service.addElement(newElement);
        System.out.println("Élément ajouté avec succès !");
    }

    public void updateElement() throws SQLException {
        System.out.println("Mise à jour d'un élément :");
        System.out.print("Code de l'élément à modifier : ");
        String code = this.scanner.nextLine();
        System.out.print("Nouveau coefficient : ");
        double coefficient = this.scanner.nextDouble();
        System.out.print("Validé (true/false) : ");
        boolean isValidated = this.scanner.nextBoolean();
        this.scanner.nextLine();
        System.out.print("Nouveau code du module : ");
        String moduleCode = this.scanner.nextLine();
        ModuleElement updatedElement = new ModuleElement(code, coefficient, isValidated, moduleCode);
        this.service.modifyElement(updatedElement);
        System.out.println("Élément mis à jour avec succès !");
    }

    public void deleteElement() throws SQLException {
        System.out.print("Code de l'élément à supprimer : ");
        String code = this.scanner.nextLine();
        this.service.removeElement(code);
        System.out.println("Élément supprimé avec succès !");
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Menu Gestion des Éléments du Module ---");
            System.out.println("1. Afficher tous les éléments");
            System.out.println("2. Afficher les détails d'un élément");
            System.out.println("3. Ajouter un nouvel élément");
            System.out.println("4. Mettre à jour un élément");
            System.out.println("5. Supprimer un élément");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choice = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                switch (choice) {
                    case 0:
                        System.out.println("Au revoir !");
                        break;
                    case 1:
                        this.displayAllElements();
                        break;
                    case 2:
                        System.out.print("Entrez le code de l'élément : ");
                        String code = this.scanner.nextLine();
                        this.displayElementDetails(code);
                        break;
                    case 3:
                        this.addElement();
                        break;
                    case 4:
                        this.updateElement();
                        break;
                    case 5:
                        this.deleteElement();
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } catch (SQLException var3) {
                SQLException e = var3;
                System.err.println("Erreur lors de l'opération sur les éléments : " + e.getMessage());
            }
        } while(choice != 0);

    }
}
