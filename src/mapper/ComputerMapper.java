package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String INTRODUCED = "introduced";
    public static final String DISCONTINUED = "discontinued";
    public static final String COMPANY_ID = "company_id";

    private static ComputerMapper _instance = null;

    public static ComputerMapper getMapper() {
        if (_instance == null) {
            _instance = new ComputerMapper();
        }
        return _instance;
    }

    @Override
    public void map(Computer entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getName());
        stmt.setTimestamp(2, entity.getIntroduced());
        stmt.setTimestamp(3, entity.getDiscontinued());
        stmt.setLong(4, entity.getCompany());
    }

    @Override
    public Computer unmap(ResultSet rs) throws SQLException {
        Computer computer = new Computer();
        computer.setId(rs.getLong(ID));
        computer.setName(rs.getString(NAME));
        computer.setIntroduced(rs.getTimestamp(INTRODUCED));
        computer.setDiscontinued(rs.getTimestamp(DISCONTINUED));
        computer.setCompany(rs.getLong(COMPANY_ID));
        return computer;
    }

}
