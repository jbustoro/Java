package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import datos.Plantacion;
import logic.IGestorPlantaciones;
import logic.IGestorPlantaciones;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class PlantacionesEditUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtFechaPlant;
	private JTextField txtCantPlant;
	private JTextField txtFechaRec;
	private JTextField txtParcela;
	private JTextField txtEspecie;
	private IGestorPlantaciones gp;
	private PlantacionesUI frmPrincipal;
	private SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
	

	/**
	 * Create the frame.
	 */
	public PlantacionesEditUI(PlantacionesUI frmPrincipal,IGestorPlantaciones gp) {
		this.frmPrincipal=frmPrincipal;
		//asignar el mismo gestor de plantaciones
		this.gp=gp;
		
		
		setTitle("Edici\u00F3n de Plantaciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 287, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFechaPlantacin = new JLabel("Fecha Plantaci\u00F3n:");
		lblFechaPlantacin.setBounds(20, 11, 115, 14);
		contentPane.add(lblFechaPlantacin);
		
		txtFechaPlant = new JTextField();
		txtFechaPlant.setBounds(20, 34, 115, 20);
		contentPane.add(txtFechaPlant);
		txtFechaPlant.setColumns(10);
		
		JLabel lblCantidadPlant = new JLabel("Cantidad Plant:");
		lblCantidadPlant.setBounds(20, 65, 115, 14);
		contentPane.add(lblCantidadPlant);
		
		txtCantPlant = new JTextField();
		txtCantPlant.setColumns(10);
		txtCantPlant.setBounds(20, 88, 115, 20);
		contentPane.add(txtCantPlant);
		
		JLabel lblFechaRecogida = new JLabel("Fecha Recogida:");
		lblFechaRecogida.setBounds(20, 119, 115, 14);
		contentPane.add(lblFechaRecogida);
		
		txtFechaRec = new JTextField();
		txtFechaRec.setColumns(10);
		txtFechaRec.setBounds(20, 142, 115, 20);
		contentPane.add(txtFechaRec);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptarClick();
			}
		});
		btnAceptar.setBounds(20, 191, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(130, 191, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblPacela = new JLabel("Pacela:");
		lblPacela.setBounds(150, 11, 115, 14);
		contentPane.add(lblPacela);
		
		txtParcela = new JTextField();
		txtParcela.setColumns(10);
		txtParcela.setBounds(150, 34, 115, 20);
		contentPane.add(txtParcela);
		
		JLabel lblEspecie = new JLabel("Especie:");
		lblEspecie.setBounds(150, 65, 115, 14);
		contentPane.add(lblEspecie);
		
		txtEspecie = new JTextField();
		txtEspecie.setColumns(10);
		txtEspecie.setBounds(150, 88, 115, 20);
		contentPane.add(txtEspecie);
	}
	private void btnAceptarClick(){
		//leer los datos introducios por el usuario
		int parcela=Integer.parseInt(txtParcela.getText());
		Date fechaPlan=null;
		Date fechaRec=null;
		
		try {
			fechaPlan=sdf.parse(txtFechaPlant.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fechaRec=sdf.parse(txtFechaRec.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String especie=txtEspecie.getText();
		int cantPlant=Integer.parseInt(txtCantPlant.getText());
		//crear un plantación con los datos
		Plantacion p=new Plantacion(parcela,fechaPlan,fechaRec,especie,cantPlant);		
		//decir al gestor de plant que añada la plantación
		gp.plantar(p);
		//actualizar la lista de la ventana principal
		frmPrincipal.actualizarListado(gp.getPlantaciones());
		//descargar la ventana
		this.dispose();
		
	}
}
