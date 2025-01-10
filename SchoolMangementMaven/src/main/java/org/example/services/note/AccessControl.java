package org.example.services.note;

public interface AccessControl {
  boolean hasAccess(String userId, String moduleElementCode);
}