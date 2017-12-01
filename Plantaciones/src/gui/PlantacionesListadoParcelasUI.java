package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.IGestorPlantaciones;

import javax.swing.JTextPane;

public class PlantacionesListadoParcelasUI extends JFrame {

	private JPanel contentPane;
	private PlantacionesUI frmListadoParcelas;
	private JTextPane txtListadoParcelas;
	private IGestorPlantaciones gp;


	public PlantacionesListadoParcelasUI(PlantacionesUI frmListadoParcelas,IGestorPlantaciones gp, String[] parcelas) {
		setTitle("Parcelas");
		this.frmListadoParcelas=frmListadoParcelas;
		//asignar el mismo gestor de plantaciones
		this.gp=gp;
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 211, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtListadoParcelas = new JTextPane();
		txtListadoParcelas.setBounds(8, 6, 195, 266);
		contentPane.add(txtListadoParcelas);
		
		String cadena = "";
		for(String p:parcelas){
			if(p!=null){
				cadena = cadena + p + "\n";
			}
		}
		txtListadoParcelas.setText(cadena);
	}

}
