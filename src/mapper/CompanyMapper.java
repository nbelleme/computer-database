package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Company;

public class CompanyMapper implements Mapper<Company>{
    public static final String ID = "id";
    public static final String NAME = "name";
    
    public CompanyMapper(){
        
    }
    
    @Override
    public void map(Company company, PreparedStatement stmt) throws SQLException {
    }

    @Override
    public Company unmap(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getLong(ID));
        company.setName(rs.getString(NAME));
        return company;
    }

}
