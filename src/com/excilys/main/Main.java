package com.excilys.main;

import java.sql.SQLException;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.Database;

public class Main {

    public static void main(String[] args) {
        try{
            ComputerDAO computerDAO = new ComputerDAO();
            CompanyDAO companyDAO = new CompanyDAO();   
            computerDAO.findAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }

}
