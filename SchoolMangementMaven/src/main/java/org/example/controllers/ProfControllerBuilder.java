package org.example.controllers;

import org.example.services.academique.ModuleElementService;
import org.example.services.note.StudentGradeService;
import org.example.services.user.StudentService;
import org.example.zapp.AppState;
import org.example.zapp.vue.Prof.ViewProfInterface;

public class ProfControllerBuilder {
  private ModuleElementService moduleElementService;
  private StudentGradeService studentGradeService;
  private StudentService studentService;
  private ViewProfInterface viewProf;
  private AppState appState;

  public ProfControllerBuilder setModuleElementService(ModuleElementService moduleElementService) {
    this.moduleElementService = moduleElementService;
    return this;
  }

  public ProfControllerBuilder setStudentGradeService(StudentGradeService studentGradeService) {
    this.studentGradeService = studentGradeService;
    return this;
  }

  public ProfControllerBuilder setStudentService(StudentService studentService) {
    this.studentService = studentService;
    return this;
  }

  public ProfControllerBuilder setViewProf(ViewProfInterface viewProf) {
    this.viewProf = viewProf;
    return this;
  }

  public ProfControllerBuilder setAppState(AppState appState) {
    this.appState = appState;
    return this;
  }

  public ProfController build() {
    return new ProfController(moduleElementService, studentGradeService, studentService, appState,viewProf);
  }
}