package org.example.vue.ModuleVue;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.dao.ModuleDaoHouda;

import org.example.dao.ModuleDaoImplhouda;
import org.example.repositories.SuperRepo;
import org.example.services.user.moduleserviceHM.ModuleService;
import org.example.services.user.moduleserviceHM.ModuleServiceImpl;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        SuperRepo.getInstance(Database.getInstance());
        MySQLDatabase database = (MySQLDatabase) Database.getInstance();
        ModuleDaoImplhouda moduleDao = ModuleDaoImplhouda.getInstance();
        ModuleService moduleService = new ModuleServiceImpl((ModuleDaoHouda) moduleDao);
        ModuleView moduleView = new ModuleViewImpl(moduleService);
        Scanner scanner = new Scanner(System.in);

        while(true) {
            while(true) {
                moduleView.displayMenu();
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        moduleView.addModule();
                        break;
                    case 2:
                        moduleView.updateModule();
                        break;
                    case 3:
                        moduleView.deleteModule();
                        break;
                    case 4:
                        moduleView.viewModules();
                        break;
                    case 5:
                        moduleView.viewModuleByCode();
                        break;
                    case 6:
                        System.out.println("Exiting the application... Thank you for joining us!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        }
    }
}

