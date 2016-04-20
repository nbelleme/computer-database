package com.excilys.main;

import java.sql.SQLException;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.Database;

public class Main {

	public static void main(String[] args) {
		Database database = Database.getInstance();
		ComputerDAO computerDAO = new ComputerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		Computer computer = new Computer(413L);
		Company company = new Company(400L);
		
		try{
		    companyDAO.delete(company);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}

}
