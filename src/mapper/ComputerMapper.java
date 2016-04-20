package mapper;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDAO;

public class ComputerMapper implements Mapper<Computer> {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String INTRODUCED = "introduced";
    public static final String DISCONTINUED = "discontinued";
    public static final String COMPANY_ID = "company_id";

    @Override
    public void map(Computer computer, JdbcRowSet rowset) throws SQLException {
        rowset.updateLong(ID, computer.getId());
        rowset.updateString(NAME, computer.getName());
        rowset.updateTimestamp(INTRODUCED, computer.getIntroduced());
        rowset.updateTimestamp(DISCONTINUED, computer.getDiscontinued());
        rowset.updateLong(COMPANY_ID, computer.getCompany());
    }

    @Override
    public Computer unmap(JdbcRowSet rowset) throws SQLException {
        Computer computer = new Computer();
        computer.setId(rowset.getLong(ID));
        computer.setName(rowset.getString(NAME));
        computer.setIntroduced(rowset.getTimestamp(INTRODUCED));
        computer.setDiscontinued(rowset.getTimestamp(DISCONTINUED));
        computer.setCompany(rowset.getLong(COMPANY_ID));
        return computer;
    }

}
