package datos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class GestorBDMySQL {
	private	Connection connection = null;
	private Statement st=null;

  public GestorBDMySQL() {
	System.out.println("-------- MySQL JDBC Connection Testing ------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	try {
		connection = DriverManager.getConnection("jdbc:mysql://192.168.1.46:3306/plantaciones","root", "Zubiri17");
		st = connection.createStatement();

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}

	if (connection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
	}
  }

  public void updateSQL(String sql) {
	// TODO Auto-generated method stub
		try{
			st.executeUpdate(sql);
		} catch (SQLException e) {
			if(e.getMessage().startsWith("UNIQUE constraint failed")){
				JOptionPane.showMessageDialog(null, "Has introducido una parcela en uso", "Parcela en uso", JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
			}
		}
  }

  public ResultSet executeSQL(String sql) {
	// TODO Auto-generated method stub
	  try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
  }
}