package GUI;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import controladores.Configuracion;
import datatypes.Retorno;
import excepciones.UsuarioYaColaboraException;
import interfaces.IControladorColaboracion;
import interfaces.IControladorPropuesta;
import interfaces.IControladorUsuario;
import logica.Colaborador;
import logica.Propuesta;

import javax.swing.border.EtchedBorder;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ItemEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class RegistrarColaboracionAPropuesta extends JInternalFrame {
	private JTextField textTitulo;
	private JTextField textEspectaculo;
	private JTextField textFecha;
	private JTextField textLugar;
	private JTextField textPrecio;
	private JPanel panelImagen;
	private JComboBox<Colaborador> comboColaboradores;
	private JComboBox<Propuesta> comboPropuestas;
	private JPanel panelInfo;
	private JButton btnCancelar;
	private JButton btnSiguiente;
	private JPanel panelConfir;
	private JLabel lblNewLabel_2;
	private JTextField textMontoColaborar; 
	private JButton btnCancelar_1 ;
	private IControladorUsuario ICUsu;
	private IControladorPropuesta ICP;
	private IControladorColaboracion ICola;
	private JButton btnAceptar;
	private JComboBox<Retorno> comboTipoRet;
	private JTextPane textPane;
	private JLabel lblImg;
	private Configuracion config = Configuracion.getInstancia();
	private JLabel lblColaboradores;
	private JLabel lblMontoAColaborar;

	public RegistrarColaboracionAPropuesta(IControladorUsuario Icusu,IControladorPropuesta icp,IControladorColaboracion icola)  {
		setTitle("Registrar Colaboracion a Propuesta");
		ICUsu = Icusu;
		ICP = icp;
		ICola = icola;
		this.setClosable(true);
		setBounds(100, 100, 510, 200);
		getContentPane().setLayout(null);
		
		comboColaboradores = new JComboBox<Colaborador>();
		comboColaboradores.setBounds(152, 29, 336, 20);
		getContentPane().add(comboColaboradores);
		
		JLabel lblPropuestas = new JLabel("Propuestas:");
		lblPropuestas.setFont(new Font("Dialog", Font.BOLD, 11));
		lblPropuestas.setBounds(13, 76, 116, 14);
		getContentPane().add(lblPropuestas);
		
		comboPropuestas = new JComboBox<Propuesta>();
		comboPropuestas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cambioProp();
			}
		});
		comboPropuestas.setBounds(152, 73, 336, 20);
		getContentPane().add(comboPropuestas);
		
		panelInfo = new JPanel();
		panelInfo.setVisible(false);
		panelInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelInfo.setBounds(13, 137, 480, 343);
		getContentPane().add(panelInfo);
		panelInfo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 11));
		lblTitulo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTitulo.setBounds(16, 27, 46, 14);
		panelInfo.add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Espectaculo:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(16, 52, 87, 14);
		panelInfo.add(lblNewLabel);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(16, 78, 64, 14);
		panelInfo.add(lblFecha);
		
		JLabel lblNewLabel_1 = new JLabel("Lugar:");
		lblNewLabel_1.setBounds(16, 107, 46, 14);
		panelInfo.add(lblNewLabel_1);
		
		JLabel lblPrecioEntrada = new JLabel("Precio Entrada:");
		lblPrecioEntrada.setFont(new Font("Dialog", Font.BOLD, 11));
		lblPrecioEntrada.setBounds(16, 133, 134, 14);
		panelInfo.add(lblPrecioEntrada);
		
		textTitulo = new JTextField();
		textTitulo.setEditable(false);
		textTitulo.setBounds(118, 24, 164, 20);
		panelInfo.add(textTitulo);
		textTitulo.setColumns(10);
		
		textEspectaculo = new JTextField();
		textEspectaculo.setEditable(false);
		textEspectaculo.setBounds(118, 49, 164, 20);
		panelInfo.add(textEspectaculo);
		textEspectaculo.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setEditable(false);
		textFecha.setBounds(118, 76, 164, 20);
		panelInfo.add(textFecha);
		textFecha.setColumns(10);
		
		textLugar = new JTextField();
		textLugar.setEditable(false);
		textLugar.setBounds(116, 104, 166, 20);
		panelInfo.add(textLugar);
		textLugar.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setBounds(176, 130, 106, 20);
		panelInfo.add(textPrecio);
		textPrecio.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(16, 161, 93, 14);
		panelInfo.add(lblDescripcion);
		
		panelImagen = new JPanel();
		panelImagen.setBorder(UIManager.getBorder("Spinner.border"));
		panelImagen.setBounds(304, 12, 164, 163);
		panelInfo.add(panelImagen);
		
		lblImg = new JLabel("");
		panelImagen.add(lblImg);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboPropuestas.setSelectedIndex(0);
				comboColaboradores.setSelectedIndex(0);
				setSize(510,200);
				panelInfo.setVisible(false);
				panelConfir.setVisible(false);
				setVisible(false);
				
			}
		});
		btnCancelar.setBounds(244, 309, 106, 23);
		panelInfo.add(btnCancelar);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelInfo.setVisible(false);
				panelConfir.setVisible(true);
				comboColaboradores.setEnabled(false);
				comboPropuestas.setEnabled(false);
				
				
			}
		});
		btnSiguiente.setBounds(362, 309, 106, 23);
		panelInfo.add(btnSiguiente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 187, 452, 87);
		panelInfo.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		btnSiguiente.setVisible(false);
		btnCancelar.setVisible(false);
		
		panelConfir = new JPanel();
		panelConfir.setBounds(46, 137, 411, 343);
		panelConfir.setVisible(false);
		getContentPane().add(panelConfir);
		panelConfir.setBackground(UIManager.getColor("Button.background"));
		panelConfir.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Tipo de Retorno:");
		lblNewLabel_2.setBounds(10, 33, 172, 14);
		panelConfir.add(lblNewLabel_2);
		
		comboTipoRet = new JComboBox<Retorno>();
		comboTipoRet.setBounds(200, 30, 199, 20);
		panelConfir.add(comboTipoRet);

		textMontoColaborar = new JTextField();

		textMontoColaborar.setBounds(200, 83, 199, 20);
		panelConfir.add(textMontoColaborar);
		textMontoColaborar.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textMontoColaborar.getText().isEmpty()) {
					agregarColaboracion();		
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese Monto", "Registrar Colaboracion", JOptionPane.ERROR_MESSAGE);
				}
			}
		});		
		btnAceptar.setBounds(304, 308, 95, 23);
		panelConfir.add(btnAceptar);
		
		btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboPropuestas.setSelectedIndex(0);
				comboColaboradores.setSelectedIndex(0);
				comboColaboradores.setEnabled(true);
				comboPropuestas.setEnabled(true);
				panelInfo.setVisible(false);
				panelConfir.setVisible(false);
				setSize(510,200);
				setVisible(false);
			}
		});
		btnCancelar_1.setBounds(186, 308, 106, 23);
		panelConfir.add(btnCancelar_1);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelInfo.setVisible(true);
				panelConfir.setVisible(false);
				comboColaboradores.setEnabled(true);
				comboPropuestas.setEnabled(true);
			}
		});
		btnAtras.setBounds(77, 308, 95, 23);
		panelConfir.add(btnAtras);
		
		lblMontoAColaborar = new JLabel("Monto a colaborar:");
		lblMontoAColaborar.setBounds(10, 85, 172, 14);
		panelConfir.add(lblMontoAColaborar);
		
		lblColaboradores = new JLabel("Colaboradores:");
		lblColaboradores.setFont(new Font("Dialog", Font.BOLD, 11));
		lblColaboradores.setBounds(13, 32, 116, 14);
		getContentPane().add(lblColaboradores);

	}
	
	public void cargarDatos(){
		//Cargo Colaboradores
		Vector<Colaborador> model = new Vector<Colaborador>();
		Map<String,Colaborador> colaboradores = (Map<String,Colaborador>)ICUsu.getColaboradores();
		Iterator<Map.Entry<String, Colaborador>> it = colaboradores.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String,Colaborador> e = (Map.Entry<String,Colaborador>)it.next();
		    Colaborador prop = e.getValue();
		    model.add(prop);
		}
		DefaultComboBoxModel<Colaborador> modelo = new DefaultComboBoxModel<Colaborador>(model);
		comboColaboradores.setModel(modelo);
		comboColaboradores.setSelectedIndex(-1);
		
		//Cargo Propuestas
		Vector<Propuesta> model2 = new Vector<Propuesta>();		
		List<Propuesta> lstProp = ICP.getPropuestas();
		Iterator<Propuesta> it2 = lstProp.iterator();		
		while (it2.hasNext()) {
			Propuesta p = it2.next();
			model2.add(p);
		}
		DefaultComboBoxModel<Propuesta> modelo2 = new DefaultComboBoxModel<Propuesta>(model2);
		comboPropuestas.setModel(modelo2);
		comboPropuestas.setSelectedIndex(-1);
		// Inicializo
		setSize(510,200);
		textMontoColaborar.setText("");
		panelConfir.setVisible(false);
		panelInfo.setVisible(false);
		comboColaboradores.setEnabled(true);
		comboPropuestas.setEnabled(true);
		
	}
	
	
	private void agregarColaboracion() {
		Colaborador col = (Colaborador) comboColaboradores.getSelectedItem();
		Propuesta prop = (Propuesta) comboPropuestas.getSelectedItem();
		String c = col.getNickname();
		String p = prop.getTitulo();
		int monto = 0;
		try {
			 monto = Integer.parseInt(textMontoColaborar.getText());
			 Retorno ret = (Retorno)comboTipoRet.getSelectedItem();
				Calendar fecha = Calendar.getInstance();		
				try {
				ICola.agregarColaboracion(c, p, monto, fecha, ret);
				JOptionPane.showMessageDialog(this, "se agrego la colaboracion", "Registrar Colaboracion", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				} catch (UsuarioYaColaboraException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Colaboracion", JOptionPane.ERROR_MESSAGE);
				}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Solo valores numericos para el monto", "Registrar Colaboracion", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	
	private void cambioProp(){
		setSize(510, 520);
		btnCancelar.setVisible(true);
		btnSiguiente.setVisible(true);
		panelInfo.setVisible(true);
		Propuesta prop = (Propuesta) comboPropuestas.getSelectedItem();
		if (prop != null) {
			textTitulo.setText(prop.getTitulo());
			textEspectaculo.setText(prop.getCategoria().getNombre());
			textLugar.setText(prop.getLugar());
			textPrecio.setText(prop.getPrecioEntrada().toString());
			textPane.setText(prop.getDescripcion());	
			int dia = prop.getFechaRealizacion().get(Calendar.DAY_OF_MONTH);
			int mes = prop.getFechaRealizacion().get(Calendar.MONTH) + 1;
			int anio = prop.getFechaRealizacion().get(Calendar.YEAR);
			String Sdia = Integer.toString(dia); 
			String Smes = Integer.toString(mes);
			String Sanio = Integer.toString(anio);
			textFecha.setText(Sdia + "/" + Smes + "/" + Sanio);
			List<Retorno> retornos = prop.getRetornos();
			ListIterator<Retorno> it = (ListIterator<Retorno>) retornos.iterator();
			comboTipoRet.removeAllItems();
			while (it.hasNext()) {
				Retorno r = it.next();
				if (r == Retorno.Entradas) {
					comboTipoRet.addItem(Retorno.Entradas);
				} else {
					comboTipoRet.addItem(Retorno.Porcentaje);
				}
			}
			
			if (prop.getImagen() != null) {
				String pathImagenes = config.getPathImagenes();
				String pathImg = pathImagenes + prop.getImagen();
				ImageIcon img = new ImageIcon(pathImg);
				lblImg.setBounds(0, 0, 100, 140);
				Icon icono = new ImageIcon(img.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_AREA_AVERAGING));
				
				lblImg.setIcon(icono);
				
			}else {
				ImageIcon icono = new ImageIcon("");
				lblImg.setBounds(0, 0, 107, 148);
				lblImg.setIcon(icono);
				
			}	
			
			
			

		}
	}
} 
