package question1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.Set;

import databaseSetup.connection;

public enum carDao {
  INSTANCE;
	
	connection conn = new connection();
	
	
	private carDao() {
		
		
	}
	private car extractcarFromResultSet(ResultSet rs) throws SQLException {

	    car car = new car();

	    car.setId( rs.getInt("id") );

	    car.setName( rs.getString("name") );

	    car.setModel( rs.getString("model") );

	    car.setRegarea( rs.getString("regarea") );

	    return car;

	}


	public car getcar(int id) throws ClassNotFoundException, SQLException {

	    Connection connection = conn.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE id=" + id);

	        if(rs.next())

	        {

	            return extractcarFromResultSet(rs);

	        }

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	public car getcarbyname(String name) throws ClassNotFoundException, SQLException {

	    Connection connection = conn.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE Name=" + name);

	        if(rs.next())

	        {

	            return extractcarFromResultSet(rs);

	        }

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	public List<car> getAllcars() throws ClassNotFoundException, SQLException {

	   

	    Connection connection = conn.getConnection();

	    try {

	        Statement stmt = connection.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM Cars");

	        List<car> cars = new ArrayList<car>();

	        while(rs.next())

	        {

	            car car = extractcarFromResultSet(rs);

	            cars.add(car);

	        }

	        return cars;

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return null;

	}
	public boolean insertcar(car car) throws ClassNotFoundException, SQLException {

	    

	    Connection connection = conn.getConnection();

	    try {

	        PreparedStatement ps = connection.prepareStatement("INSERT INTO Cars VALUES (NULL, ?, ?, ?)");

	        ps.setString(1, car.getName());

	        ps.setString(2, car.getModel());

	        ps.setString(3, car.getRegarea());

	        int i = ps.executeUpdate();

	      if(i == 1) {

	        return true;

	      }

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return false;

	}
	public boolean updatecar(car car) throws ClassNotFoundException, SQLException {



	    Connection connection = conn.getConnection();

	    try {

	        PreparedStatement ps = connection.prepareStatement("UPDATE Cars SET name=?, model=?, regarea=? WHERE id=?");

	        ps.setString(1, car.getName());

	        ps.setString(2, car.getModel());

	        ps.setString(3, car.getRegarea());

	        ps.setInt(4, car.getId());

	        int i = ps.executeUpdate();

	      if(i == 1) {

	    return true;

	      }

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return false;

	}
	public boolean deletecar(int id) throws ClassNotFoundException, SQLException {



	    Connection connection = conn.getConnection();

	    try {

	        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Cars WHERE id=" + id);

	        int i = stmt.executeUpdate();

	      if(i == 1) {

	    return true;

	      }

	    } catch (SQLException ex) {

	        ex.printStackTrace();

	    }

	    return false;

	}
	
	
	
}
