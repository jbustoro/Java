package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import datos.Plantacion;
import logic.GestorPlantacionesBBDD;
import logic.IGestorPlantaciones;
import javax.swing.JScrollBar;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;

public class PlantacionesUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtFechaPlant;
	private JTextField txtCantPlant;
	private JTextField txtFechaRec;
	private JTextField txtReco;
	private JTextField txtParcela;
	private JTextField txtEspecie;
	private JList<Plantacion> listPlantaciones;
	private IGestorPlantaciones gp=new GestorPlantacionesBBDD();
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlantacionesUI frame = new PlantacionesUI();
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
	public PlantacionesUI() {
		
		setTitle("Plantaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 225, 180);
		contentPane.add(scrollPane);
		
		JLabel lblListado = new JLabel("Listado:");
		lblListado.setBounds(20, 11, 46, 14);
		contentPane.add(lblListado);
		
		//JList listPlantaciones = new JList(gp.getPlantaciones().toArray());
		listPlantaciones = new JList();
		actualizarListado(gp.getPlantaciones());
		scrollPane.setViewportView(listPlantaciones);
		listPlantaciones.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				 if (!arg0.getValueIsAdjusting()) {
					 if(listPlantaciones.getSelectedValue() == null){
					 	
					 }else{
						 mostrarDatos((Plantacion)listPlantaciones.getSelectedValue());
					 }
				 }
			}
			
		});
		listPlantaciones.setBounds(20, 36, 225, 180);
		listPlantaciones.setCellRenderer(new SelectedListCellRenderer());
		
		//pedir las plantaciones al gestor de plantaciones
		//contentPane.add(listPlantaciones);
		
		JLabel lblFechaPlantacin = new JLabel("Fecha Plantaci\u00F3n:");
		lblFechaPlantacin.setBounds(255, 11, 115, 14);
		contentPane.add(lblFechaPlantacin);
		
		txtFechaPlant = new JTextField();
		txtFechaPlant.setBounds(255, 34, 115, 20);
		contentPane.add(txtFechaPlant);
		txtFechaPlant.setColumns(10);
		
		JLabel lblCantidadPlant = new JLabel("Cantidad Plant:");
		lblCantidadPlant.setBounds(255, 82, 115, 14);
		contentPane.add(lblCantidadPlant);
		
		txtCantPlant = new JTextField();
		txtCantPlant.setColumns(10);
		txtCantPlant.setBounds(255, 105, 115, 20);
		contentPane.add(txtCantPlant);
		
		JLabel lblFechaRecogida = new JLabel("Fecha Recogida:");
		lblFechaRecogida.setBounds(255, 157, 115, 14);
		contentPane.add(lblFechaRecogida);
		
		txtFechaRec = new JTextField();
		txtFechaRec.setColumns(10);
		txtFechaRec.setBounds(255, 180, 115, 20);
		contentPane.add(txtFechaRec);
		
		JLabel lblCantidadRec = new JLabel("Cantidad Rec:");
		lblCantidadRec.setBounds(385, 157, 115, 14);
		contentPane.add(lblCantidadRec);
		
		txtReco = new JTextField();
		txtReco.setColumns(10);
		txtReco.setBounds(385, 180, 115, 20);
		contentPane.add(txtReco);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNuevoClick();
			}
		});
		btnNuevo.setBounds(50, 227, 103, 23);
		contentPane.add(btnNuevo);
		
		JButton btnRecolectar = new JButton("Recolectar");
		btnRecolectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int parcela = Integer.parseInt(txtParcela.getText());
				Date fechaPlant = null;
				try {
					fechaPlant = sdf.parse(txtFechaPlant.getText());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int cantReco = Integer.parseInt(txtReco.getText());
				gp.recolectar(parcela, fechaPlant, cantReco);
				listPlantaciones.getSelectedValue().setCantRec(cantReco);
				actualizarListado(gp.getPlantaciones());
			}
		});
		btnRecolectar.setBounds(50, 272, 103, 23);
		contentPane.add(btnRecolectar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(356, 272, 103, 23);
		contentPane.add(btnSalir);
		
		JLabel lblPacela = new JLabel("Parcela:");
		lblPacela.setBounds(385, 11, 115, 14);
		contentPane.add(lblPacela);
		
		txtParcela = new JTextField();
		txtParcela.setColumns(10);
		txtParcela.setBounds(385, 34, 115, 20);
		contentPane.add(txtParcela);
		
		JLabel lblEspecie = new JLabel("Especie:");
		lblEspecie.setBounds(385, 82, 115, 14);
		contentPane.add(lblEspecie);
		
		txtEspecie = new JTextField();
		txtEspecie.setColumns(10);
		txtEspecie.setBounds(385, 105, 115, 20);
		contentPane.add(txtEspecie);
		
		JButton btnParcelas = new JButton("Parcelas");
		btnParcelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					btnParcelasClick();
			}
		});
		btnParcelas.setBounds(203, 227, 103, 23);
		contentPane.add(btnParcelas);
		
		JButton btnEspecies = new JButton("Especies");
		btnEspecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEspeciesClick();
			}
		});
		btnEspecies.setBounds(356, 227, 103, 23);
		contentPane.add(btnEspecies);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtParcela.getText().equals("") || txtFechaPlant.getText().equals("")){
					JOptionPane.showMessageDialog(null, "No has seleccionado ninguna plantacion", "Plantacion no seleccionada", 
							JOptionPane.INFORMATION_MESSAGE);
				}else{
					int parcela = Integer.parseInt(txtParcela.getText());
					Date fechaPlan = null;
					try {
						fechaPlan = sdf.parse(txtFechaPlant.getText());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					gp.eliminar(parcela, fechaPlan);
					actualizarListado(gp.getPlantaciones());
				}
			}
		});
		btnEliminar.setBounds(203, 272, 103, 23);
		contentPane.add(btnEliminar);
		
	}
	private void btnNuevoClick(){
		PlantacionesEditUI frmPlantEdit=new PlantacionesEditUI(this,gp);
		frmPlantEdit.setVisible(true);
	}
	private void btnParcelasClick(){
		PlantacionesListadoParcelasUI frmListadoParcelas = new PlantacionesListadoParcelasUI(this, gp, gp.getParcelas());
		frmListadoParcelas.setVisible(true);
	}
	private void btnEspeciesClick(){
		PlantacionesListadoEspeciesUI frmListadoEspecies = new PlantacionesListadoEspeciesUI(this, gp, gp.getEspecies());
		frmListadoEspecies.setVisible(true);
	}
	//muestra los datos del par�metro en el jList
	public void actualizarListado(ArrayList<Plantacion> lstPlantas){
		DefaultListModel<Plantacion> dlm=new DefaultListModel<Plantacion>();

		for(Plantacion p:lstPlantas){
				dlm.addElement(p);
			}
		//pasar el modelo al Jlist
		listPlantaciones.setModel(dlm);
	}
	//cargar la informacion de una plantaci�n en las cajas de texto
	private void mostrarDatos(Plantacion p){
		txtParcela.setText(Integer.toString(p.getParcela()));
		txtFechaPlant.setText(sdf.format(p.getFechaPlan()));
		txtCantPlant.setText(Integer.toString(p.getCantPlant()));
		txtFechaRec.setText(sdf.format(p.getFechaRec()));
		txtReco.setText(Integer.toString(p.getCantRec()));
		txtEspecie.setText(p.getEspecie());
	}
	
	public class SelectedListCellRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
			Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			Date hoy=new Date();
			if ((((Plantacion)value).getCantRec()==0) && ((Plantacion)value).getFechaRec().compareTo(hoy) < 0 ) {
				c.setBackground(Color.RED);
		    }else if((((Plantacion)value).getCantRec()>0)){
		    	c.setBackground(Color.GREEN);
		    }else{
		    	c.setBackground(Color.WHITE);
		    }
			if (isSelected){
		    	c.setBackground(getBackground().darker());
			}
		    return c;
		}
	}

}
