package org.example.models.user.User;


import org.example.models.user.Admin.AdminBuilder;
import org.example.models.user.Prof.ProfessorBuilder;

public class UserFactory {

  public static User createUser(String id,  String password ,String firstName, String lastName, String login, String role) {
    switch (role.toLowerCase()) {
      case "professor":
        return new ProfessorBuilder()
          .setId(id)
          .setFirstName(firstName)
          .setLastName(lastName)
          .setLogin(login)
          .setPassword(password)
          .build();

      case "administrator":
        return new AdminBuilder()
          .setId(id)
          .setFirstName(firstName)
          .setLastName(lastName)
          .setPassword(password)
          .setLogin(login)
          .build();

      default:
        throw new IllegalArgumentException("Unknown role: " + role);
    }
  }
}
