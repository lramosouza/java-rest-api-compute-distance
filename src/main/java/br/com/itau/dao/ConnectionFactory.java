package br.com.itau.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory extends DAOFactory {
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/itau";

	public static Connection createConnection() throws Exception {
		Class.forName(DRIVER);  
		return DriverManager.getConnection(URL, "root", "root");
	}
	
	@Override
	public DestinationDAO getDestinationDAO() {
		return new DestinationDAOImpl();
	}

}
