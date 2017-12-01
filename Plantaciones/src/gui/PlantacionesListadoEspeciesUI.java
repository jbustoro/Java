package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.IGestorPlantaciones;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class PlantacionesListadoEspeciesUI extends JFrame {

	private JPanel contentPane;
	private PlantacionesUI frmListadoEspecies;
	private JTextArea txtListadoEspecies;
	private IGestorPlantaciones gp;

	public PlantacionesListadoEspeciesUI(PlantacionesUI frmListadoEspecies,IGestorPlantaciones gp, String[] Especies) {
		setTitle("Especies");
		this.frmListadoEspecies=frmListadoEspecies;
		//asignar el mismo gestor de plantaciones
		this.gp=gp;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 218, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtListadoEspecies = new JTextArea();
		txtListadoEspecies.setBounds(10, 6, 196, 266);
		contentPane.add(txtListadoEspecies);
		
		String cadena = "";
		for(String p: Especies){
			if(p!=null){
				cadena = cadena + p + "\n";
			}
		}
		txtListadoEspecies.setText(cadena);
	}
}
