package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Usuario;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BienvenidoUI extends JFrame {

	private JPanel contentPane;
	private JTextPane txtpnBienvenido;

	public BienvenidoUI(Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtpnBienvenido = new JTextPane();
		txtpnBienvenido.setEditable(false);
		if(u.getContVisitas()==1){
			txtpnBienvenido.setText("Bienvenid@ "+u.getCod()+"\r\n\r\nnos has visitado "+u.getContVisitas()+" vez");
		}else{
			txtpnBienvenido.setText("Bienvenid@ "+u.getCod()+"\r\n\r\nnos has visitado "+u.getContVisitas()+" veces");
		}
		txtpnBienvenido.setBounds(121, 35, 208, 120);
		contentPane.add(txtpnBienvenido);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalirClick();
			}
		});
		btnSalir.setBounds(160, 204, 89, 23);
		contentPane.add(btnSalir);
	}
	
	private void btnSalirClick(){
		this.dispose();
	}
}
