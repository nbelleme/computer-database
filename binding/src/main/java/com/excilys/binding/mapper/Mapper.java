package com.excilys.binding.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {

  /**
   * @param entity
   *          entity that needs to be mapped
   * @param stmt
   *          statement that need to be mapped
   * @throws SQLException
   *           if any error occurs
   */
  void map(T entity, PreparedStatement stmt) throws SQLException;

  /**
   * @param rs
   *          results to inject into the return entity
   * @return entity entity mapped
   * @throws SQLException
   *           if any error occurs
   */
  T unmap(ResultSet rs) throws SQLException;
}
