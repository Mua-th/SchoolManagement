package org.example.services.note;

import org.example.models.academique.ModuleElement;
import org.example.repositories.academique.ModuleElementDao;

import java.sql.SQLException;
import java.util.List;

public class AccessControlImpl implements AccessControl {
  private final ModuleElementDao moduleElementDao;

  public AccessControlImpl(ModuleElementDao moduleElementDao) {
    this.moduleElementDao = moduleElementDao;
  }

  @Override
  public boolean hasAccess(String userId, String moduleElementCode) {
    try {
      List<ModuleElement> moduleElements = moduleElementDao.findAllByProfId(userId);

      ModuleElement moduleElement = moduleElements.stream().filter(m -> m.getCode().equals(moduleElementCode)).findFirst().orElse(null);

      return moduleElement != null;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
