package mapper;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import com.excilys.model.Company;

public class CompanyMapper implements Mapper<Company>{
    public static final String ID = "id";
    public static final String NAME = "name";
    
    public CompanyMapper(){
        
    }
    
    @Override
    public void map(Company company, JdbcRowSet rowset) throws SQLException {
        rowset.updateLong(ID, company.getId());
        rowset.updateString(NAME, company.getName());
    }

    @Override
    public Company unmap(JdbcRowSet rowset) throws SQLException {
        Company company = new Company();
        company.setId(rowset.getLong(ID));
        company.setName(rowset.getString(NAME));
        return company;
    }

}
