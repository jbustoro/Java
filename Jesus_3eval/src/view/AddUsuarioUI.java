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

public class AddUsuarioUI extends JFrame {

	private GestorEntradas ge = new GestorEntradas();
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtPassWord;

	public AddUsuarioUI() {
		setTitle("A\u00F1adir usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 254, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCod = new JLabel("Usuario: ");
		lblCod.setBounds(25, 31, 63, 14);
		contentPane.add(lblCod);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(98, 28, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblPass = new JLabel("Password: ");
		lblPass.setBounds(25, 70, 69, 14);
		contentPane.add(lblPass);
		
		txtPassWord = new JTextField();
		txtPassWord.setColumns(10);
		txtPassWord.setBounds(98, 67, 86, 20);
		contentPane.add(txtPassWord);
		
		JButton btnAdd = new JButton("A\u00F1adir");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddClick();
			}
		});
		btnAdd.setBounds(20, 121, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelClick();
			}
		});
		btnCancel.setBounds(129, 121, 89, 23);
		contentPane.add(btnCancel);
	}
	
	private boolean btnAddClick(){
		String cod = txtUsuario.getText();
		String pass = txtPassWord.getText();
		Usuario u = new Usuario(cod,pass,0);
	
		if(ge.addUsuario(u)==false){
			JOptionPane.showMessageDialog(null, "Has introducido un usuario en uso", "Usuario en uso", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			return false;
		};
		
		JOptionPane.showMessageDialog(null, "Has añadido el usuario "+u.getCod()+" correctamente", "Usuario añadido", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		return true;
		
	}
	
	private void btnCancelClick(){
		this.dispose();
	}
}
