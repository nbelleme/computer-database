package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Company;

public class CompanyMapper implements Mapper<Company>{
    public static final String ID = "id";
    public static final String NAME = "name";

    private static CompanyMapper _instance = null;
    
    public static CompanyMapper getMapper() {
        if (_instance == null) {
            _instance = new CompanyMapper();
        }
        return _instance;
    }
    
    @Override
    public void map(Company company, PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, company.getId());
        stmt.setString(2, company.getName());
    }

    @Override
    public Company unmap(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getLong(ID));
        company.setName(rs.getString(NAME));
        return company;
    }

}
