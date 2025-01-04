package org.example.vue.ModuleVue;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;
import org.example.services.user.moduleserviceHM.ModuleService;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ModuleViewImpl {
    /*
    private final ModuleService moduleService;

    public ModuleViewImpl(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    public void displayMenu() {
        System.out.println("\n=== Module Management System ===");
        System.out.println("1. Add Module");
        System.out.println("2. Update Module");
        System.out.println("3. Delete Module");
        System.out.println("4. View All Modules");
        System.out.println("5. View Module by Code");
        System.out.println("6. Exit");
    }

    public void addModule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Module Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Module Name: ");
        String name = scanner.nextLine();
        List<String> availableSemesters = List.of("S1", "S2", "S3", "S4");
        System.out.println("Available Semesters:");

        for(int i = 0; i < availableSemesters.size(); ++i) {
            System.out.println(i + 1 + ". " + (String)availableSemesters.get(i));
        }

        while(true) {
            System.out.print("Choose Semester (e.g., S1, S2): ");
            String semesterInput = scanner.nextLine().toUpperCase();
            if (availableSemesters.contains(semesterInput)) {
                List<Filiere> filieres = this.moduleService.getAllFilieres();
                if (filieres.isEmpty()) {
                    System.out.println("No filières available!");
                    return;
                } else {
                    System.out.println("Available Filières:");

                    for(int i = 0; i < filieres.size(); ++i) {
                        System.out.println(i + 1 + ". " + ((Filiere)filieres.get(i)).getCode() + " - " + ((Filiere)filieres.get(i)).getName());
                    }

                    Filiere selectedFiliere = null;

                    while(selectedFiliere == null) {
                        System.out.print("Choose Filiere by number: ");

                        try {
                            int choice = Integer.parseInt(scanner.nextLine());
                            if (choice > 0 && choice <= filieres.size()) {
                                selectedFiliere = (Filiere)filieres.get(choice - 1);
                            } else {
                                System.out.println("Invalid choice! Please choose a valid filiere.");
                            }
                        } catch (NumberFormatException var9) {
                            System.out.println("Please enter a valid number.");
                        }
                    }

                    Module module = new Module(code, name, Semester.valueOf(semesterInput), selectedFiliere);
                    this.moduleService.addModule(module);
                    System.out.println("Module added successfully!");
                    return;
                }
            }

            System.out.println("Invalid semester choice! Please try again.");
        }
    }

    public void updateModule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Module Code to Update: ");
        String code = scanner.nextLine();
        org.example.models.academique.Module module = this.moduleService.getModuleByCode(code);
        if (module != null) {
            System.out.print("Enter New Name: ");
            module.setName(scanner.nextLine());
            System.out.print("Enter New Semester (S1, S2, etc.): ");
            module.setSemester(Semester.valueOf(scanner.nextLine().toUpperCase()));
            this.moduleService.updateModule(module);
            System.out.println("Module updated successfully!");
        } else {
            System.out.println("Module not found!");
        }

    }

    public void deleteModule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Module Code to Delete: ");
        String code = scanner.nextLine();
        this.moduleService.deleteModule(code);
        System.out.println("Module deleted successfully!");
    }

    public void viewModules() {
        List var10000 = this.moduleService.getAllModules();
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
    }

    public void viewModuleByCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Module Code to View: ");
        String code = scanner.nextLine();
        Module module = this.moduleService.getModuleByCode(code);
        if (module != null) {
            System.out.println(module);
        } else {
            System.out.println("Module not found!");
        }

    }*/
}
