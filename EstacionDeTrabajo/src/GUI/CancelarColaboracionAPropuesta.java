package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import interfaces.IControladorColaboracion;
import interfaces.IControladorUsuario;
import logica.Colaboracion;
import logica.Colaborador;

@SuppressWarnings("serial")
public class CancelarColaboracionAPropuesta extends JInternalFrame {
	private JTextField textFieldRetorno;
	private JTextField textFieldFecha;
	private JTextField textFieldId;
	private JTextField textFieldMonto;
	private JComboBox<Colaborador> colaboradorComboBox;
	private JComboBox<Colaboracion> ColaboracionesComboBox;
	private IControladorUsuario ICUsu;
	private IControladorColaboracion ICola;

	

	/**
	 * Create the frame.
	 */
	public CancelarColaboracionAPropuesta(IControladorUsuario Icusu,IControladorColaboracion icola) {
		setClosable(true);
		setTitle("Cancelar Colaboracion a Propuesta");
		setBounds(100, 100, 367, 323);
		getContentPane().setLayout(null);
		ICUsu = Icusu;
		ICola = icola;
		colaboradorComboBox = new JComboBox<Colaborador>();
		colaboradorComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cargarColaboraciones();			
			}
		});
		colaboradorComboBox.setToolTipText("");
		colaboradorComboBox.setBounds(12, 33, 334, 24);
		getContentPane().add(colaboradorComboBox);
		
		ColaboracionesComboBox = new JComboBox<Colaboracion>();
		ColaboracionesComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cargarDatosColaboraciones();
			}
		});
		ColaboracionesComboBox.setToolTipText("");
		ColaboracionesComboBox.setBounds(12, 93, 334, 24);
		getContentPane().add(ColaboracionesComboBox);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 128, 88, 15);
		getContentPane().add(lblId);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(12, 154, 88, 15);
		getContentPane().add(lblFecha);
		
		JLabel lblTipoDeRetorno = new JLabel("Tipo de retorno:");
		lblTipoDeRetorno.setBounds(12, 180, 126, 15);
		getContentPane().add(lblTipoDeRetorno);
		
		textFieldRetorno = new JTextField();
		textFieldRetorno.setEditable(false);
		textFieldRetorno.setColumns(10);
		textFieldRetorno.setBounds(139, 177, 207, 19);
		getContentPane().add(textFieldRetorno);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(139, 150, 207, 19);
		getContentPane().add(textFieldFecha);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(139, 125, 207, 19);
		getContentPane().add(textFieldId);
		
		JButton btnAceptar = new JButton("Cerrar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnAceptar.setBounds(21, 255, 117, 25);
		getContentPane().add(btnAceptar);
		
		JLabel lblColaboracionesAPropuestas = new JLabel("Colaboraciones a Propuestas");
		lblColaboracionesAPropuestas.setBounds(12, 68, 187, 14);
		getContentPane().add(lblColaboracionesAPropuestas);
		
		JLabel lblNewLabel = new JLabel("Colaboradores");
		lblNewLabel.setBounds(12, 8, 106, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(12, 207, 88, 15);
		getContentPane().add(lblMonto);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setEditable(false);
		textFieldMonto.setColumns(10);
		textFieldMonto.setBounds(139, 210, 207, 19);
		getContentPane().add(textFieldMonto);
		
		JButton btnCancelarColaboracion = new JButton("Cancelar Colaboracion");
		btnCancelarColaboracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Colaboracion colaObj = (Colaboracion) ColaboracionesComboBox.getSelectedItem();
				if (colaObj != null){
					colaObj.getPropuesta().eliminarColaboracion(colaObj);
					colaObj.getPropuesta().eliminarColaborador(colaObj.getUsuario().getNickname());
					Map<String,Colaborador> colaboradores = (Map<String,Colaborador>)ICUsu.getColaboradores();
					Colaborador c= colaboradores.get(colaObj.getUsuario().getNickname());
					c.eliminarColaboracion(colaObj.getId());
					ICola.eliminarColaboracion(colaObj.getUsuario().getNickname(),colaObj.getPropuesta(),colaObj.getId(),colaObj.getMonto());
				}
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnCancelarColaboracion.setBounds(153, 255, 193, 25);
		getContentPane().add(btnCancelarColaboracion);

	}
	
	public void cargarDatos(){
		
		Vector<Colaborador> model = new Vector<Colaborador>();
		Map<String,Colaborador> colaboradores = (Map<String,Colaborador>)ICUsu.getColaboradores();
		Iterator<Map.Entry<String, Colaborador>> it = colaboradores.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String,Colaborador> e = (Map.Entry<String,Colaborador>)it.next();
		    Colaborador prop = e.getValue();
		    model.add(prop);
		}
		DefaultComboBoxModel<Colaborador> modelo = new DefaultComboBoxModel<Colaborador>(model);
		colaboradorComboBox.setModel(modelo);
		colaboradorComboBox.setSelectedIndex(-1);
		ColaboracionesComboBox.setSelectedIndex(-1);
		textFieldId.setText("");
		textFieldRetorno.setText("");
		textFieldFecha.setText("");
		textFieldMonto.setText("");
		
	}
	
	
	private void cargarColaboraciones() {
		
		Vector<Colaboracion> model = new Vector<Colaboracion>();
		Colaborador colObj = (Colaborador) colaboradorComboBox.getSelectedItem();
		if (colObj != null) {
			Map<Integer, Colaboracion> colaboraciones = colObj.getColaboraciones();
			Iterator<Entry<Integer, Colaboracion>> it = colaboraciones.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<Integer, Colaboracion> e = (Map.Entry<Integer, Colaboracion>) it.next();
				Colaboracion colaObj = e.getValue();
				model.add(colaObj);
			}

			DefaultComboBoxModel<Colaboracion> modelo = new DefaultComboBoxModel<Colaboracion>(model);
			ColaboracionesComboBox.setModel(modelo);
			ColaboracionesComboBox.setSelectedIndex(-1);
			
		}
	}
	
	private void limpiarFormulario(){
		textFieldRetorno.setText("");
		textFieldFecha.setText("");
		textFieldMonto.setText("");
		textFieldId.setText("");
		colaboradorComboBox.setSelectedIndex(-1);
		ColaboracionesComboBox.setSelectedIndex(-1);
	}
	
	
	private void cargarDatosColaboraciones(){
		Colaboracion colaObj = (Colaboracion) ColaboracionesComboBox.getSelectedItem();
		if (colaObj != null) {
		textFieldId.setText(String.valueOf(colaObj.getId()));
		String anio = String.valueOf(colaObj.getFechaColaboracion().get(Calendar.YEAR));
		int mesI = (colaObj.getFechaColaboracion().get(Calendar.MONTH)) + 1;
		String mes = String.valueOf(mesI);
		String dia = String.valueOf(colaObj.getFechaColaboracion().get(Calendar.DATE));
		String hora = String.valueOf(colaObj.getFechaColaboracion().get(Calendar.HOUR));
		String min = String.valueOf(colaObj.getFechaColaboracion().get(Calendar.MINUTE));
		textFieldFecha.setText(dia + "/" + mes + "/" + anio + " - " + hora +":"+min );
		textFieldRetorno.setText(colaObj.getRetornoElegido().toString());
		textFieldMonto.setText(colaObj.getMonto().toString());
		}
	}
} 
