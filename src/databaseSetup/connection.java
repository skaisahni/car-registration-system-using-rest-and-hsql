package databaseSetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
	 public static Connection getConnection() throws ClassNotFoundException, SQLException
	 {
		 Class.forName("org.hsqldb.jdbcDriver");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
		return conn;
		 
	 }


}
