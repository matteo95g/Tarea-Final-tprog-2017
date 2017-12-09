package GUI;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import datatypes.EstadoPropuesta;
import interfaces.IControladorPropuesta;
import logica.Propuesta;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class EvaluacionPropuesta extends JInternalFrame {
	
	private JComboBox<Propuesta> comboBoxPropuestas;
	private JLabel lblProp;
	private JButton btnIngresarPropuesta;
	private JButton btnCancelarPropuesta;
	private JButton btnSalir;
	private IControladorPropuesta ICP;




	public EvaluacionPropuesta(IControladorPropuesta icp)  {
		ICP = icp;
		
		setResizable(false);
		setBounds(100, 100, 559, 378);
		getContentPane().setLayout(null);
		setVisible(false);
		
		comboBoxPropuestas = new JComboBox<Propuesta>();
		comboBoxPropuestas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			cambiarProp();
				
				
			}
		});
		comboBoxPropuestas.setBounds(158, 69, 306, 24);
		getContentPane().add(comboBoxPropuestas);
		
		JLabel lblPropuestas = new JLabel("Propuestas");
		lblPropuestas.setBounds(12, 74, 128, 15);
		getContentPane().add(lblPropuestas);
		
		lblProp = new JLabel("");
		lblProp.setBounds(12, 132, 400, 15);
		getContentPane().add(lblProp);
		
		btnIngresarPropuesta = new JButton("Publicar Propuesta");
		btnIngresarPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modProp(true);
			}
		});
		btnIngresarPropuesta.setBounds(53, 212, 191, 25);
		getContentPane().add(btnIngresarPropuesta);
		
		btnCancelarPropuesta = new JButton("Cancelar Propuesta");
		btnCancelarPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modProp(false);
			}
		});
		btnCancelarPropuesta.setBounds(304, 212, 191, 25);
		getContentPane().add(btnCancelarPropuesta);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnSalir.setBounds(26, 307, 117, 25);
		getContentPane().add(btnSalir);
	}
	
	
	public void cargarPropuestas() {
		lblProp.setText("");
		Vector<Propuesta> model = new Vector<Propuesta>();		
		List<Propuesta> lstProp = ICP.getPropuestas();
		Iterator<Propuesta> it = lstProp.iterator();		
		while (it.hasNext()) {
			Propuesta p = it.next();
			if (p.getEstado().getEstado() == EstadoPropuesta.Ingresada) {
			model.add(p);
			}
		}
		DefaultComboBoxModel<Propuesta> modelo = new DefaultComboBoxModel<Propuesta>(model);
		comboBoxPropuestas.setModel(modelo);
		comboBoxPropuestas.setSelectedIndex(-1);
	}
	
	private void cambiarProp(){
		Propuesta propSelec = (Propuesta)comboBoxPropuestas.getSelectedItem();
		if (propSelec != null && comboBoxPropuestas.getSelectedIndex() != -1) {
			lblProp.setText("Proponente "+ propSelec.getProponente().toString());
		}
		
	}
	
	private void modProp(boolean sel) {
		Propuesta propSelec = (Propuesta)comboBoxPropuestas.getSelectedItem();
		if (propSelec != null && comboBoxPropuestas.getSelectedIndex() != -1) {
			lblProp.setText("Proponente "+ propSelec.getProponente().toString());
			if (sel) {
				ICP.modificarEstado(propSelec.getTitulo(), EstadoPropuesta.Publicada);
			} else {
				ICP.modificarEstado(propSelec.getTitulo(), EstadoPropuesta.Cancelada);
			}
		cargarPropuestas();	
		}  else {
			JOptionPane.showMessageDialog(this, "Seleccione una Propuesta", "Evaluacion de Propuesta",
                    JOptionPane.ERROR_MESSAGE);
		}
	}

}
