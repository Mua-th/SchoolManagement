package org.example;

import org.example.config.Database;
import org.example.config.DatabaseFactory;
import org.example.controllers.AdminController;
import org.example.controllers.LoginController;
import org.example.controllers.ProfController;
import org.example.controllers.ProfControllerBuilder;
import org.example.models.users.User.UserRole;
import org.example.repositories.SuperRepo;
import org.example.repositories.User.StudentDAO;
import org.example.repositories.User.StudentDAOImpl;
import org.example.repositories.User.UserRepository;
import org.example.services.academique.ModuleElementService;
import org.example.services.academique.ModuleElementServiceImpl;
import org.example.services.note.StudentGradeService;
import org.example.services.user.StudentService;
import org.example.services.user.StudentServiceImpl;
import org.example.services.user.UserService;
import org.example.zapp.AppState;
import org.example.zapp.vue.LoginView;
import org.example.zapp.vue.LoginViewInterface;
import org.example.zapp.vue.SwingLoginView;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException {
    Database myDatabase = DatabaseFactory.getDatabaseInstance();
    SuperRepo.getInstance(myDatabase);
    String guiType = AppState.getGuiType();
    LoginViewInterface loginView;

    if ("swing".equalsIgnoreCase(guiType)) {
      loginView = new SwingLoginView();
    } else {
      loginView = LoginView.getInstance();
    }

    LoginController loginController = new LoginController(
      new UserService(
        UserRepository.getInstance()),
      loginView
    );

    while (true) {
      if (AppState.getInstance().isAuthenticated()) {
        if (AppState.getInstance().getUser().getRole().equals(UserRole.Administrator)) {
          AdminController adminController = new AdminController();
          adminController.handleInput();
        } else {
          StudentService studentService =  StudentServiceImpl.getInstance( StudentDAOImpl.getInstance());

          //use ProfControllerBuilder to inject dependencies

          ProfController profController = new ProfControllerBuilder()
            .setStudentService(studentService)
            .setModuleElementService(
              new ModuleElementServiceImpl())
            .setStudentGradeService(
              StudentGradeService.getInstance())

            .build();
          profController.handleInput();
        }
      } else {
        loginController.handleLogin();
      }
    }
  }
}