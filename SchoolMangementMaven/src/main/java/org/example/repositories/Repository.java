package org.example.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T , ID> {
  T findById(ID id ) throws SQLException;
  List<T> findAll() throws SQLException;
  boolean save(T t) throws SQLException;
  boolean delete(ID id) throws SQLException;

}

