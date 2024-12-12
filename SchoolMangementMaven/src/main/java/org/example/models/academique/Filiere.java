package org.example.models.academique;

import java.util.ArrayList;
import java.util.List;

public class Filiere {
private String code;
private String name;
private List<java.lang.Module> modules;

public Filiere(String code, String name) {
  this.code = code;
  this.name = name;
  this.modules = new ArrayList<>();
  }
  }