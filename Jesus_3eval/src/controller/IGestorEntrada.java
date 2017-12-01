package controller;

import javax.swing.DefaultComboBoxModel;

public interface IGestorEntrada {
	public boolean compruebaUsuario(Usuario u);
	public DefaultComboBoxModel<Usuario> getUsuarios();
	public void incVisitas(Usuario u);
	public boolean addUsuario(Usuario u);
	public void modUsuario(Usuario viejo, Usuario nuevo);
	public void delUsuario(Usuario u);
	
}
