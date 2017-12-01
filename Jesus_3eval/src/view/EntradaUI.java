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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EntradaUI extends JFrame {

	private GestorEntradas ge = new GestorEntradas();
	private JPanel contentPane;
	private JTextField txtPassword;
	private JComboBox cmbUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntradaUI frame = new EntradaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EntradaUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(54, 52, 59, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(54, 97, 70, 14);
		contentPane.add(lblPassword);
		
		cmbUsuarios = new JComboBox(ge.getUsuarios());
		cmbUsuarios.setBounds(123, 49, 93, 20);
		contentPane.add(cmbUsuarios);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(123, 94, 93, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAceptarClick();
			}
		});
		btnAceptar.setBounds(38, 145, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarClick();
			}
		});
		btnCancelar.setBounds(157, 145, 89, 23);
		contentPane.add(btnCancelar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 351, 21);
		contentPane.add(menuBar);
		
		JMenu mnGestionarUsuarios = new JMenu("Gestionar usuarios");
		menuBar.add(mnGestionarUsuarios);
		
		JMenuItem mntmAadir = new JMenuItem("A\u00F1adir");
		mntmAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddClick();
			}
		});
		mnGestionarUsuarios.add(mntmAadir);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModClick();
			}
		});
		mnGestionarUsuarios.add(mntmModificar);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDelClick();
			}
		});
		mnGestionarUsuarios.add(mntmEliminar);
	}
	
	private void btnAceptarClick(){
		String cod = cmbUsuarios.getSelectedItem().toString();
		String pass = txtPassword.getText();
		
		Usuario u = new Usuario();
		u.setCod(cod);
		u.setPass(pass);
		
		if(ge.compruebaUsuario(u)){
			u=(Usuario)cmbUsuarios.getSelectedItem();
			ge.incVisitas(u);
			BienvenidoUI frmBievenido=new BienvenidoUI(u);
			frmBievenido.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(new JFrame(), "Contraseña incorrecta!", "Error",JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void btnCancelarClick(){
		this.dispose();
	}
	
	private void btnAddClick(){
		AddUsuarioUI frmAdd = new AddUsuarioUI();
		frmAdd.setVisible(true);
	}
	
	private void btnModClick(){
		ModUsuarioUI frmMod = new ModUsuarioUI((Usuario)cmbUsuarios.getSelectedItem());
		frmMod.setVisible(true);
		
	}
	private void btnDelClick(){
		int dialogButton = JOptionPane.showConfirmDialog (null, "Estas seguro que quieres eliminar el usuario "
				+((Usuario)cmbUsuarios.getSelectedItem()).getCod(),"Eliminar",JOptionPane.YES_NO_OPTION);
		if(dialogButton == JOptionPane.YES_OPTION){
			ge.delUsuario((Usuario)cmbUsuarios.getSelectedItem());
		}
		
		
	}
}
