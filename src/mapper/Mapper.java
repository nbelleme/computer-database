package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {

    public void map(T entity, PreparedStatement stmt) throws SQLException;
    public T unmap(ResultSet rs) throws SQLException;
    }
