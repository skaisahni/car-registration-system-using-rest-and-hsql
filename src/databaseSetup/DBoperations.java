package databaseSetup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBoperations {

	public int createdata() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
		Statement stmt = conn.createStatement();
		int res =0;
		
		try {
			stmt.executeUpdate("CREATE TABLE Cars( Id INTEGER IDENTITY PRIMARY KEY , Name varchar(30) NOT NULL, Model varchar(30) NOT NULL, RegArea varchar(30) NOT NULL)");
		    res =1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      System.out.println("done");
	return res;
	}
	public int deldata() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
		Statement stmt = conn.createStatement();
		int res =0;
		try {
			stmt.executeUpdate("DELETE FROM Cars");
			res =1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      System.out.println("done");
	return res;
	}
	public int filldata() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
		Statement stmt = conn.createStatement();
		int res =0;
		try {
			stmt.executeUpdate("INSERT INTO Cars VALUES (NULL, 'KONA', 'SUV' , 'Dublin')");
			stmt.executeUpdate("INSERT INTO Cars VALUES (NULL, 'i20', 'compact' , 'Dublin')");
			stmt.executeUpdate("INSERT INTO Cars VALUES (NULL, 'XF', 'sedan' , 'Dublin')");
			stmt.executeUpdate("INSERT INTO Cars VALUES (NULL, 'Cobra', 'convertable' , 'Dublin')");
			stmt.executeUpdate("INSERT INTO Cars VALUES (NULL, 'A8', 'Sedan' , 'Athlone')");
			stmt.executeUpdate("INSERT INTO Cars VALUES (NULL, 'GLA', 'Supercar' , 'Cork')");
			stmt.executeUpdate("INSERT INTO Cars VALUES (NULL, 'punto', 'comapct' , 'Galway')");
			res =1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      System.out.println("done");
      return res;
	}

}
