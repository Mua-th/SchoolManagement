package org.example;

import org.example.config.Database;
import org.example.config.DatabaseFactory;
import org.example.controllers.AdminController;
import org.example.controllers.LoginController;
import org.example.controllers.ProfController;
import org.example.controllers.ProfControllerBuilder;
import org.example.models.users.User.UserRole;
import org.example.repositories.SuperRepo;
import org.example.repositories.User.StudentDAOImpl;
import org.example.repositories.User.UserRepository;
import org.example.repositories.academique.ModuleElementDaoImpl;
import org.example.repositories.note.StudentGradeRepo;
import org.example.services.academique.ModuleElementServiceImpl;
import org.example.services.note.AccessControl;
import org.example.services.note.AccessControlImpl;
import org.example.services.note.StudentGradeService;

import org.example.services.academique.FiliereService;
import org.example.services.user.StudentService;
import org.example.services.user.StudentServiceImpl;
import org.example.services.user.UserService;
import org.example.services.academique.ModuleServiceImpl;
import org.example.zapp.AppState;
import org.example.zapp.vue.Admin.AdminView;
import org.example.zapp.vue.Admin.AdminViewInterface;
import org.example.zapp.vue.Admin.SwingAdminView;
import org.example.zapp.vue.Login.LoginView;
import org.example.zapp.vue.Login.LoginViewInterface;
import org.example.zapp.vue.Prof.SwingViewProf;
import org.example.zapp.vue.Prof.ViewProf;
import org.example.zapp.vue.Prof.ViewProfInterface;


import org.example.zapp.vue.Login.SwingLoginView;


import java.sql.SQLException;
import java.util.Observable;

public class Main {

  public static void main(String[] args) throws SQLException {
    Database myDatabase = DatabaseFactory.getDatabaseInstance();
    SuperRepo.getInstance(myDatabase);
    String guiType = AppState.getGuiType();
    LoginViewInterface loginView;
    ViewProfInterface viewProf;
    AdminViewInterface adminView;

    if ("swing".equalsIgnoreCase(guiType)) {
      SwingLoginView swingLoginView = new SwingLoginView();
      loginView = swingLoginView;
      SwingViewProf swingViewProf = new SwingViewProf();
      viewProf = swingViewProf;
      SwingAdminView swingAdminView = new SwingAdminView();
      adminView = swingAdminView;

      swingLoginView.addObserver(new LoginController(new UserService(UserRepository.getInstance()), loginView, viewProf, adminView));
    } else {
      loginView = LoginView.getInstance();
      viewProf = ViewProf.getInstance();
      adminView = AdminView.getInstance();
    }

    LoginController loginController = new LoginController(
      new UserService(UserRepository.getInstance()), loginView, viewProf, adminView);

    while (true) {
      if (AppState.getInstance().isAuthenticated()) {
        if (AppState.getInstance().getUser().getRole().equals(UserRole.Administrator)) {
          AdminController adminController = new AdminController(
            FiliereService.getInstance() ,
            new ModuleElementServiceImpl(
              ModuleElementDaoImpl.getInstance() ,
              StudentGradeService.getInstance(
                StudentGradeRepo.getInstance(),
                ModuleElementDaoImpl.getInstance(),
                new AccessControlImpl(
                  ModuleElementDaoImpl.getInstance()
                )
              )),

            ModuleServiceImpl.getInstance(),

            StudentServiceImpl.getInstance(StudentDAOImpl.getInstance()),
            adminView ) ;

          adminController.handleInput();
        } else {
          StudentService studentService = StudentServiceImpl.getInstance(StudentDAOImpl.getInstance());

          StudentGradeService studentGradeService = StudentGradeService.getInstance(
            StudentGradeRepo.getInstance(),
            ModuleElementDaoImpl.getInstance(),
            new AccessControlImpl(
              ModuleElementDaoImpl.getInstance()
            )
          ) ;
          ProfController profController = new ProfControllerBuilder()
            .setStudentService(studentService)
            .setModuleElementService(ModuleElementServiceImpl.getInstance(studentGradeService , ModuleElementDaoImpl.getInstance()))
            .setStudentGradeService(studentGradeService)
            .setAppState(AppState.getInstance())
            .setViewProf(viewProf)
            .build();

          if (viewProf instanceof Observable) {
            ((Observable) viewProf).addObserver(profController);
          }

          profController.handleInput();
        }
      } else {
        loginController.handleLogin();
      }
    }
  }
}