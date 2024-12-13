package org.example;

import org.example.repositories.UserRepository;
import org.example.vue.MenuPrinter;
import org.example.vue.PrintableToConsole;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {

    System.out.println(UserRepository.getInstance().findById("prof-1")) ;

  }
}