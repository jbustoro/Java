package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import model.GestorBDSQLite;

public class GestorEntradas implements IGestorEntrada{
	private GestorBDSQLite gsqlite = new GestorBDSQLite();
	private DefaultComboBoxModel<Usuario> cmbUsuarios = new DefaultComboBoxModel<Usuario>();

	@Override
	public boolean compruebaUsuario(Usuario u) {
		String sql = "Select * from usuario where cod = '"+u.getCod()+"' and pass = '"+u.getPass()+"';";
		
		ResultSet rs = gsqlite.executeSQL(sql);
		
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DefaultComboBoxModel<Usuario> getUsuarios() {
		String sql = "Select * from usuario;";
		
		ResultSet rs = gsqlite.executeSQL(sql);
		try {
			while(rs.next()){
				String cod = rs.getString("cod");
				String pass = rs.getString("pass");
				int contVisitas = rs.getInt("contVisitas");
				
				Usuario u = new Usuario(cod, pass, contVisitas);
				cmbUsuarios.addElement(u);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cmbUsuarios;
	}

	@Override
	public void incVisitas(Usuario u) {
		//incrementar en la bbdd
		String sql = "update usuario set contVisitas=contVisitas+1  where cod = '"+u.getCod()+"' and pass = '"+u.getPass()+"';";
		gsqlite.updateSQL(sql);
		//incrementar en memoria( DefaulComboBoxModel)
		u.incContVistitas();
	}

	@Override
	public boolean addUsuario(Usuario u) {
		//comprobar si ya existe el usuario
		String sql = "Select * from usuario where cod = '"+u.getCod()+"';";
		
		ResultSet rs = gsqlite.executeSQL(sql);
		
		try {
			if(rs.next()){
				//mostrar mens error
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cmbUsuarios.addElement(u);
		
		String cod = u.getCod();
		String pass = u.getPass();
		int contVisitas = u.getContVisitas();
		sql = "insert into usuario values('"+cod+"','"+pass+"',"+contVisitas+");";
		gsqlite.updateSQL(sql);
		return true;
	}

	@Override
	public void modUsuario(Usuario viejo, Usuario nuevo) {
		String codNuevo = nuevo.getCod();
		String passNuevo = nuevo.getPass();
		String codViejo = viejo.getCod();
		
		String sql = "update usuario set cod = '"+codNuevo+"', pass = '"+passNuevo+"' where cod = '"+codViejo+"';";
    	gsqlite.updateSQL(sql);

	}

	@Override
	public void delUsuario(Usuario u) {
		String cod = u.getCod();
		
		String sql = "delete from usuario where cod = '"+cod+"';";
		gsqlite.updateSQL(sql);
		
		cmbUsuarios.removeElement(u);
	}

}
