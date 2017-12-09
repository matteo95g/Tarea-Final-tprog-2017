package GUI;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import persistencia.ColaboracionPer;
import persistencia.ProponentePer;
import persistencia.PropuestaPer;

import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthSpinnerUI;

import interfaces.IPersistencia;
import logica.Colaborador;
import logica.Usuario;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

@SuppressWarnings("serial")
public class VerPropEliminados extends JInternalFrame  {

	private JTextField textFieldNick;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldContra;
	private JTextField textFieldFechaNac;
	private JTextField textFieldFechaBaja;
    private JComboBox<ProponentePer> comboBoxProponentes;
    private JComboBox<PropuestaPer> comboBoxPropuestas;
    private IPersistencia IPer;
    private JPanel panelProponentes;
    private JPanel panelPropuestas;
    private JTextField textFieldTituloProp;
    private JTextField textFieldMontoRequeridoProp;
    private JTextField textFieldLugarProp;
    private JTextField textFieldFechaRealizacionProp;
    private JTextField textFieldPrecioEntrada;
    private JTextField textFieldTiposRetorno;
    private JButton btnAtras;
    private JList listCola;
	/**
	 * Create the application.
	 */
	public VerPropEliminados(IPersistencia iper) {
		
		IPer = iper;
		setClosable(true);
		setBounds(100, 100, 600, 400);
		
		setTitle("Ver Proponentes Eliminados");
		getContentPane().setLayout(null);
		
		panelPropuestas = new JPanel();
		panelPropuestas.setBounds(12, 12, 547, 327);
		getContentPane().add(panelPropuestas);
		panelPropuestas.setLayout(null);
		
		textFieldTituloProp = new JTextField();
		textFieldTituloProp.setBounds(162, 56, 190, 19);
		panelPropuestas.add(textFieldTituloProp);
		textFieldTituloProp.setColumns(10);
		
		JLabel lblTituloProp = new JLabel("Titulo:");
		lblTituloProp.setBounds(25, 58, 84, 15);
		panelPropuestas.add(lblTituloProp);
		
		comboBoxPropuestas = new JComboBox();
		comboBoxPropuestas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxPropuestas.getSelectedItem() != null) {
					
					seleccionPropuesta((PropuestaPer)comboBoxPropuestas.getSelectedItem());
				
				}
				//
			}
		});
		comboBoxPropuestas.setBounds(118, 8, 368, 24);
		panelPropuestas.add(comboBoxPropuestas);
		
		JLabel lblMontoProp = new JLabel("Monto Requerido:");
		lblMontoProp.setBounds(24, 112, 130, 15);
		panelPropuestas.add(lblMontoProp);
		
		textFieldMontoRequeridoProp = new JTextField();
		textFieldMontoRequeridoProp.setBounds(162, 110, 114, 19);
		panelPropuestas.add(textFieldMontoRequeridoProp);
		textFieldMontoRequeridoProp.setColumns(10);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(24, 85, 70, 15);
		panelPropuestas.add(lblLugar);
		
		textFieldLugarProp = new JTextField();
		textFieldLugarProp.setBounds(162, 87, 190, 19);
		panelPropuestas.add(textFieldLugarProp);
		textFieldLugarProp.setColumns(10);
		
		JLabel lblFechaRealizacion = new JLabel("Fecha Realizacion:");
		lblFechaRealizacion.setBounds(25, 139, 139, 15);
		panelPropuestas.add(lblFechaRealizacion);
		
		textFieldFechaRealizacionProp = new JTextField();
		textFieldFechaRealizacionProp.setBounds(162, 137, 114, 19);
		panelPropuestas.add(textFieldFechaRealizacionProp);
		textFieldFechaRealizacionProp.setColumns(10);
		
		JLabel lblPrecioEntrada = new JLabel("Precio Entrada:");
		lblPrecioEntrada.setBounds(25, 163, 144, 15);
		panelPropuestas.add(lblPrecioEntrada);
		
		textFieldPrecioEntrada = new JTextField();
		textFieldPrecioEntrada.setBounds(162, 161, 119, 19);
		panelPropuestas.add(textFieldPrecioEntrada);
		textFieldPrecioEntrada.setColumns(10);
		
		JLabel lblTiposRetorno = new JLabel("Tipos Retorno: ");
		lblTiposRetorno.setBounds(24, 200, 130, 15);
		panelPropuestas.add(lblTiposRetorno);
		
		textFieldTiposRetorno = new JTextField();
		textFieldTiposRetorno.setBounds(162, 198, 190, 19);
		panelPropuestas.add(textFieldTiposRetorno);
		textFieldTiposRetorno.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(162, 229, 318, 86);
		panelPropuestas.add(scrollPane);
		
		listCola = new JList();
		scrollPane.setViewportView(listCola);
		
		JLabel lblColaboraciones = new JLabel("Colaboraciones");
		lblColaboraciones.setBounds(25, 251, 129, 15);
		panelPropuestas.add(lblColaboraciones);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPropuestas.setVisible(false);
				panelProponentes.setVisible(true);
				
			}
		});
		btnAtras.setBounds(22, 290, 117, 25);
		panelPropuestas.add(btnAtras);
		
		panelProponentes = new JPanel();
		panelProponentes.setBounds(12, 12, 547, 327);
		getContentPane().add(panelProponentes);
		panelProponentes.setLayout(null);
		
		JLabel lblProponentes = new JLabel("Proponentes");
		lblProponentes.setBounds(26, 17, 115, 15);
		panelProponentes.add(lblProponentes);
		//getContentPane().add(lblProponentes);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(26, 85, 109, 15);
		panelProponentes.add(lblNickname);
		//getContentPane().add(lblNickname);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(26, 111, 97, 15);
		panelProponentes.add(lblNombre);
	//	getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(26, 138, 97, 15);
		panelProponentes.add(lblApellido);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(26, 192, 109, 15);
		panelProponentes.add(lblContrasea);
	//	getContentPane().add(lblContrasea);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(26, 219, 147, 24);
		panelProponentes.add(lblFechaNacimiento);
	//	getContentPane().add(lblFechaNacimiento);
		
		JLabel lblFechaBaja = new JLabel("Fecha Baja:");
		lblFechaBaja.setBounds(26, 255, 109, 15);
		panelProponentes.add(lblFechaBaja);
	//	getContentPane().add(lblFechaBaja);
		
		textFieldNick = new JTextField();
		textFieldNick.setBounds(141, 83, 249, 19);
	//	getContentPane().add(textFieldNick);
		panelProponentes.add(textFieldNick);
		textFieldNick.setColumns(10);
		
		
		
		JButton btnPropuestas = new JButton("Propuestas");
		btnPropuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPropuestas.setVisible(true);
				panelProponentes.setVisible(false);
				if (comboBoxProponentes.getSelectedItem() != null) {
				datosPropuesta((ProponentePer)comboBoxProponentes.getSelectedItem());
				}
			}
		});
		btnPropuestas.setBounds(26, 290, 116, 25);
		panelProponentes.add(btnPropuestas);
		
		textFieldFechaBaja = new JTextField();
		textFieldFechaBaja.setBounds(153, 255, 118, 19);
		panelProponentes.add(textFieldFechaBaja);
		textFieldFechaBaja.setColumns(10);
		
		textFieldFechaNac = new JTextField();
		textFieldFechaNac.setBounds(164, 222, 114, 19);
		panelProponentes.add(textFieldFechaNac);
		textFieldFechaNac.setColumns(10);
		
		textFieldContra = new JTextField();
		textFieldContra.setBounds(141, 190, 175, 19);
		panelProponentes.add(textFieldContra);
		textFieldContra.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(141, 159, 249, 19);
		panelProponentes.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(141, 136, 249, 19);
		panelProponentes.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(141, 109, 249, 19);
		panelProponentes.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
			
			JLabel lblEmail = new JLabel("EMail:");
			lblEmail.setBounds(26, 165, 42, 15);
			panelProponentes.add(lblEmail);
			
			comboBoxProponentes = new JComboBox();
			comboBoxProponentes.setBounds(146, 12, 351, 24);
			panelProponentes.add(comboBoxProponentes);
			comboBoxProponentes.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if (comboBoxProponentes.getSelectedItem() != null) {
						datosProponente((ProponentePer)comboBoxProponentes.getSelectedItem());
					}
					
				}
			});
		
	}
	


	public void cargarDatos() {

		
		panelPropuestas.setVisible(false);
		panelProponentes.setVisible(true);
		Vector<ProponentePer> vector = new Vector<ProponentePer>();
		List<ProponentePer> proponentes = IPer.getProponentesPer();

		for (Object p : proponentes) {
			ProponentePer prop = (ProponentePer) p;
			vector.add(prop);
		}

		ComboBoxModel<ProponentePer> modelo1 = new DefaultComboBoxModel<ProponentePer>(vector);
		comboBoxProponentes.setModel(modelo1);
		comboBoxProponentes.setSelectedIndex(-1);

		textFieldNick.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEmail.setText("");
		textFieldContra.setText("");
		textFieldFechaNac.setText("");
		textFieldFechaBaja.setText("");

	}
	
	
	private void datosProponente(ProponentePer prop) {

		

		textFieldNick.setText(prop.getNickname());
		textFieldNombre.setText(prop.getNombre());
		textFieldApellido.setText(prop.getApellido());
		textFieldEmail.setText(prop.getCorreoElectronico());
		textFieldContra.setText(prop.getContra());

		Calendar nac = prop.getFechaNacimiento();
		Calendar baj = prop.getFechaBaja();
		int dia = nac.get(Calendar.DAY_OF_MONTH);
		int mes = nac.get(Calendar.MONTH) + 1;
		int anio = nac.get(Calendar.YEAR);
		String Sdia = Integer.toString(dia);
		String Smes = Integer.toString(mes);
		String Sanio = Integer.toString(anio);
		textFieldFechaNac.setText(Sdia + "/" + Smes + "/" + Sanio);

		dia = baj.get(Calendar.DAY_OF_MONTH);
		mes = baj.get(Calendar.MONTH) + 1;
		anio = baj.get(Calendar.YEAR);
		Sdia = Integer.toString(dia);
		Smes = Integer.toString(mes);
		Sanio = Integer.toString(anio);
		textFieldFechaBaja.setText(Sdia + "/" + Smes + "/" + Sanio);

	}
	
	
	private void datosPropuesta(ProponentePer prop){
		
		List<PropuestaPer>  propuestas = IPer.getPropuestasPer(prop.getNickname());
		Vector<PropuestaPer> vecProp = new Vector<PropuestaPer>();
		
		for (Object p : propuestas) {
			PropuestaPer propu = (PropuestaPer) p;
			vecProp.add(propu);
		}
		
		ComboBoxModel<PropuestaPer> modelo1 = new DefaultComboBoxModel<PropuestaPer>(vecProp);
		comboBoxPropuestas.setModel(modelo1);
		comboBoxPropuestas.setSelectedIndex(-1);
		
		 textFieldTituloProp.setText("");
	     textFieldMontoRequeridoProp.setText("");
	     textFieldLugarProp.setText("");
	     textFieldFechaRealizacionProp.setText("");
	     textFieldPrecioEntrada.setText("");
	     textFieldTiposRetorno.setText("");
	     DefaultListModel<ColaboracionPer> listModel = new DefaultListModel<ColaboracionPer>();
	     listCola.setModel(listModel);
	}
	
	
	private void seleccionPropuesta(PropuestaPer p ){
		
		textFieldTituloProp.setText(p.getTitulo());
		textFieldMontoRequeridoProp.setText(p.getMontoNecesario().toString());
		textFieldLugarProp.setText(p.getLugar());
		textFieldPrecioEntrada.setText(p.getPrecioEntrada().toString());
		textFieldTiposRetorno.setText(p.getTiposRetorno());
		
		Calendar fec = p.getFechaRealizacion();
		int dia = fec.get(Calendar.DAY_OF_MONTH);
		int mes = fec.get(Calendar.MONTH) + 1;
		int anio = fec.get(Calendar.YEAR);
		String Sdia = Integer.toString(dia);
		String Smes = Integer.toString(mes);
		String Sanio = Integer.toString(anio);
		textFieldFechaRealizacionProp.setText(Sdia + "/" + Smes + "/" + Sanio);
		List<ColaboracionPer> colaboraciones = IPer.getColaboracionesPer(p.getTitulo());
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (Object ob1 : colaboraciones) {
			ColaboracionPer cola = (ColaboracionPer) ob1;
			listModel.addElement(cola.toString());
			
		}
		
		listCola.setModel(listModel);
				
	}
}
