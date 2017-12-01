package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.GestorEntradas;
import controller.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModUsuarioUI extends JFrame {

	private GestorEntradas ge = new GestorEntradas();
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtPass;

	public ModUsuarioUI(Usuario viejo) {
		setTitle("Modificar usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 266, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(40, 36, 60, 14);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(110, 33, 89, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		//coger el nombre del usuario que vamos a modificar
		txtUsuario.setText(viejo.getCod());
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(40, 83, 60, 14);
		contentPane.add(lblPassword);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(110, 80, 89, 20);
		contentPane.add(txtPass);
		//coger el nombre del usuario que vamos a modificar
		txtPass.setText(viejo.getPass());
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModClick(viejo);
			}
		});
		btnModificar.setBounds(24, 135, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelClick();
			}
		});
		btnCancelar.setBounds(137, 135, 89, 23);
		contentPane.add(btnCancelar);
	}
	
	private void btnModClick(Usuario viejo){
		Usuario nuevo = viejo;
		
		String cod = txtUsuario.getText();
		String pass = txtPass.getText();
		
		nuevo.setCod(cod);
		nuevo.setPass(pass);
		
		ge.modUsuario(viejo, nuevo);
		JOptionPane.showMessageDialog(null, "El usuario se a modificado correctamente!" , "Usuario modificado", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
	
	private void btnCancelClick(){
		this.dispose();
	}
}
