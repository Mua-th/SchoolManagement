package org.example.models.academique;

import java.util.List;
public class ModuleBuilder {
    private String code;
    private String name;
    private Filiere filiere;
    private Semester semester;
    private List<ModuleElement> moduleElements;


    // Méthodes pour définir les attributs
    public ModuleBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public ModuleBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ModuleBuilder setFiliere(Filiere filiere) {
        this.filiere = filiere;
        return this;
    }

    public ModuleBuilder setSemester(Semester semester) {
        this.semester = semester;
        return this;
    }

    public ModuleBuilder setModuleElements(List<ModuleElement> moduleElements) {
        this.moduleElements = moduleElements;
        return this;
    }

    // Méthode pour construire un objet Module
    public Module build() {
        Module module = new Module();
        module.setCode(this.code);
        module.setName(this.name);
        module.setSemester(this.semester);
        module.setFiliere(this.filiere);
        return module;
    }
}