package mapper;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

public interface Mapper<T> {

	public void map(T entity, JdbcRowSet rowset) throws SQLException;
	public T unmap(JdbcRowSet rowset) throws SQLException;
	}
