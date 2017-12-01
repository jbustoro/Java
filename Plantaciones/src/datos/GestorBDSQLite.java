package datos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import javax.swing.JOptionPane;

public class GestorBDSQLite{
	private static final String RUTA_BD="plantaciones.sqlite";
	private static final String CREATE_TABLE="CREATE TABLE "
			+ "'tareas' ('ID' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 'Titulo' TEXT, 'Hecho' INTEGER);";
	private Connection cn=null;
	private Statement st=null;
	
	public GestorBDSQLite(){
		//cargar driver y conectar bd
		try
		{
			//cargar el driver jdbc-sqlite
			Class.forName("org.sqlite.JDBC");
			
			//comprobar si existe la base de datos
			File f = new File(RUTA_BD);
			if(f.exists()) { 
				//conectarnos la la bd
				cn =DriverManager.getConnection("jdbc:sqlite:"+RUTA_BD);
				st = cn.createStatement();
			}else{
				//si no existe la base de datos la crea
				//conectarnos la la bd
				cn =DriverManager.getConnection("jdbc:sqlite:"+RUTA_BD);
				st = cn.createStatement();
				st.executeUpdate(CREATE_TABLE);
			}
		}
		catch (ClassNotFoundException ex){
			System.out.println("sqlite driver not found");
		}
		catch(SQLException ex1){
			System.out.println("Error abriendo bd");
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
	
	public void desconectarBD(){
		
	}
	
}