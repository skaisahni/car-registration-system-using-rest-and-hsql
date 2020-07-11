package databaseSetup;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class csvdata {
	 ResultSet rs;
 
	public int csvprint() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "");
	
		Statement stmt = conn.createStatement();
		String cmd = "select * from Cars;";
		int res=0;
		try{					
			rs= stmt.executeQuery(cmd); 	
			writeToFile(rs);
			res=1;
		}
		catch(Exception e1){e1.printStackTrace();}
		return res;
		
	}
	private void writeToFile(ResultSet rs){
		try{
			System.out.println("In writeToFile");
			FileWriter outputFile = new FileWriter("Sikander1.csv");
			PrintWriter printWriter = new PrintWriter(outputFile);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();

			for(int i=0;i<numColumns;i++){
				printWriter.print(rsmd.getColumnLabel(i+1)+",");
			}
			printWriter.print("\n");
			while(rs.next()){
				for(int i=0;i<numColumns;i++){
					printWriter.print(rs.getString(i+1)+",");
				}
				printWriter.print("\n");
				printWriter.flush();
			}
			printWriter.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
}
