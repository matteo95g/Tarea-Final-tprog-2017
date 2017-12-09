package GUI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import controladores.Configuracion;
import datatypes.Retorno;
import interfaces.Fabrica;
import interfaces.IControladorPropuesta;
import logica.Colaborador;
import logica.Propuesta;



@SuppressWarnings("serial")

public class ConsultaDePropuesta extends JInternalFrame {
	
	private JComboBox<Propuesta> comboBoxPropuestas;
	private JComboBox<Colaborador> comboBoxColaboradores;
	private JTextPane txtTitulo;
	private JTextPane txtDescripcion;
	private JTextPane txtEstado;
	private JTextPane txtLugar;
	private JTextPane txtFecha;
	private JTextPane txtEntrada;
	private JTextPane txtMonto;
	private JTextPane txtMontoActual;
	private JTextPane txtFechaIngreso;
	private JCheckBox chckbxEntradas;
	private JCheckBox chckbxPorcentaje;
	private JTextPane txtCategoria;
	
	private JLabel lblImg;
	private IControladorPropuesta ICP;
	private Configuracion config = Configuracion.getInstancia();
	
	public void cargarPropuestas() {
		Vector<Propuesta> model = new Vector<Propuesta>();		
		List<Propuesta> lstProp = ICP.getPropuestas();
		Iterator<Propuesta> it = lstProp.iterator();		
		while (it.hasNext()) {
			Propuesta p = it.next();
			model.add(p);
		}
		DefaultComboBoxModel<Propuesta> modelo = new DefaultComboBoxModel<Propuesta>(model);
		comboBoxPropuestas.setModel(modelo);
		comboBoxPropuestas.setSelectedIndex(-1);
	}
	
	

	public ConsultaDePropuesta(IControladorPropuesta icp) {
		ICP = icp;
		
		setTitle("Consulta De Propuesta");
		setClosable(true);
		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(null); 
		
		comboBoxPropuestas = new JComboBox<Propuesta>();		
		comboBoxPropuestas.setModel(new DefaultComboBoxModel<Propuesta>());
		//comboBoxPropuestas.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un colaborador"}));
		comboBoxPropuestas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Propuesta propSelec = (Propuesta)comboBoxPropuestas.getSelectedItem();
				if (propSelec != null && comboBoxPropuestas.getSelectedIndex() != -1) {
					chckbxPorcentaje.setSelected(false);
					chckbxEntradas.setSelected(false);
					txtTitulo.setText(propSelec.getTitulo());
					//cargo los colab para esa prop
					cargarColaboradores(propSelec.getTitulo());
					txtDescripcion.setText(propSelec.getDescripcion());
					txtLugar.setText(propSelec.getLugar());
					txtEntrada.setText(Integer.toString(propSelec.getPrecioEntrada()));
					txtMonto.setText(Integer.toString(propSelec.getMontoNecesario()));
					txtMontoActual.setText(Integer.toString(propSelec.getMontoActual()));
					txtCategoria.setText(propSelec.getCategoria().getNombre());
					switch(propSelec.getEstado().getEstado()) { 
					case Ingresada:
						txtEstado.setText("Ingresada");
						break;
					case Publicada:
						txtEstado.setText("Publicada");
						break;
					case EnFinanciacion:
						txtEstado.setText("En Financiacion");
						break;
					case Financiada:
						txtEstado.setText("Financiada");
						break;
					case NoFinanciada:
						txtEstado.setText("No Financiada");
						break;
					case Cancelada:
						txtEstado.setText("Cancelada");
						break;
					}
					Iterator<Retorno> it = propSelec.getRetornos().iterator();
					while (it.hasNext()) {
						Retorno ret = it.next();
						if (ret == Retorno.Entradas)
							chckbxEntradas.setSelected(true);
						if (ret == Retorno.Porcentaje)					
							chckbxPorcentaje.setSelected(true);
					}
					
					Calendar fecha = propSelec.getFechaRealizacion();
					int dia = fecha.get(Calendar.DAY_OF_MONTH);
					int mes = fecha.get(Calendar.MONTH) + 1; 

					int anio = fecha.get(Calendar.YEAR);
					txtFecha.setText(Integer.toString(dia) + " / " + Integer.toString(mes) + " / " +  Integer.toString(anio));
					
					
					Calendar fechaIng = propSelec.getFechaIngreso();
					int diaIng = fechaIng.get(Calendar.DAY_OF_MONTH);
					int mesIng = fechaIng.get(Calendar.MONTH) + 1;
					int anioIng = fechaIng.get(Calendar.YEAR);
					int horaIng = fechaIng.get(Calendar.HOUR_OF_DAY);
					int minIng = fechaIng.get(Calendar.MINUTE);
					txtFechaIngreso.setText(Integer.toString(diaIng) + " / " + Integer.toString(mesIng) + " / " +  
					Integer.toString(anioIng) + " - " + Integer.toString(horaIng) + ":" + Integer.toString(minIng));
					
					if (propSelec.getImagen() != null) {
						String pathImagenes = config.getPathImagenes();
						String pathImg = pathImagenes + propSelec.getImagen();
						ImageIcon img = new ImageIcon(pathImg);
						Icon icono = new ImageIcon(img.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_AREA_AVERAGING));
						lblImg.setBounds(516, 117, 300, 300);
						lblImg.setIcon(icono);
						setSize(860, 600);
					}else {
//						ImageIcon icono = new ImageIcon("");
//						lblImg.setBounds(516, 117, 300, 300);
//						lblImg.setIcon(icono);
						setSize(500, 600);
					}
					
				}
			}
		});
		comboBoxPropuestas.setBounds(12, 12, 453, 24);
		getContentPane().add(comboBoxPropuestas);
		
		lblImg = new JLabel();
		lblImg.setBounds(516, 117, 300, 300);
		getContentPane().add(lblImg);
		
		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setBounds(12, 90, 70, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 117, 100, 15);
		getContentPane().add(lblDescripcion);
		
		JLabel lblMontoActual = new JLabel("Monto actual:");
		lblMontoActual.setBounds(12, 333, 124, 15);
		getContentPane().add(lblMontoActual);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(12, 198, 70, 15);
		getContentPane().add(lblLugar);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(12, 233, 70, 15);
		getContentPane().add(lblFecha);
		
		JLabel lblPrecioDeLa = new JLabel("Precio de la entrada:");
		lblPrecioDeLa.setBounds(12, 266, 155, 15);
		getContentPane().add(lblPrecioDeLa);
		
		JLabel lblMontoNecesario = new JLabel("Monto necesario:");
		lblMontoNecesario.setBounds(12, 300, 155, 15);
		getContentPane().add(lblMontoNecesario);
		
		JLabel lblFechaDeIngreso = new JLabel("Fecha de ingreso:");
		lblFechaDeIngreso.setBounds(12, 366, 168, 15);
		getContentPane().add(lblFechaDeIngreso);
		
		txtTitulo = new JTextPane();
		txtTitulo.setEditable(false);
		txtTitulo.setBounds(174, 84, 291, 21);
		getContentPane().add(txtTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 117, 291, 63);
		getContentPane().add(scrollPane);
		
		txtDescripcion = new JTextPane();
		txtDescripcion.setEditable(false);
		scrollPane.setViewportView(txtDescripcion);
		
		txtLugar = new JTextPane();
		txtLugar.setEditable(false);
		txtLugar.setBounds(174, 192, 291, 21);
		getContentPane().add(txtLugar);
		
		txtFecha = new JTextPane();
		txtFecha.setEditable(false);
		txtFecha.setBounds(174, 227, 291, 21);
		getContentPane().add(txtFecha);
		
		txtEntrada = new JTextPane();
		txtEntrada.setEditable(false);
		txtEntrada.setBounds(174, 260, 291, 21);
		getContentPane().add(txtEntrada);
		
		txtMonto = new JTextPane();
		txtMonto.setEditable(false);
		txtMonto.setBounds(174, 293, 291, 21);
		getContentPane().add(txtMonto);
		
		txtMontoActual = new JTextPane();
		txtMontoActual.setEditable(false);
		txtMontoActual.setBounds(174, 327, 291, 21);
		getContentPane().add(txtMontoActual);
		
		txtFechaIngreso = new JTextPane();
		txtFechaIngreso.setEditable(false);
		txtFechaIngreso.setBounds(174, 360, 291, 21);
		getContentPane().add(txtFechaIngreso);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnFinalizar.setBounds(348, 531, 117, 25);
		getContentPane().add(btnFinalizar);
		
		JLabel lblColaboradores = new JLabel("Colaboradores:");
		lblColaboradores.setBounds(12, 403, 141, 15);
		getContentPane().add(lblColaboradores);		
		
		
		comboBoxColaboradores = new JComboBox<Colaborador>();		
		comboBoxColaboradores.setBounds(174, 398, 291, 24);
		getContentPane().add(comboBoxColaboradores);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(12, 440, 141, 15);
		getContentPane().add(lblEstado);
		
		txtEstado = new JTextPane();
		txtEstado.setEditable(false);
		txtEstado.setBounds(174, 434, 291, 21);
		getContentPane().add(txtEstado);
		
		JLabel lblRetornos = new JLabel("Retorno:");
		lblRetornos.setBounds(12, 467, 141, 15);
		getContentPane().add(lblRetornos);
		
		chckbxEntradas = new JCheckBox("Entradas");
		chckbxEntradas.setEnabled(false);
		chckbxEntradas.setBounds(174, 463, 129, 23);
		getContentPane().add(chckbxEntradas);
		
		chckbxPorcentaje = new JCheckBox("Porcentaje");
		chckbxPorcentaje.setEnabled(false);
		chckbxPorcentaje.setBounds(336, 463, 129, 23);
		getContentPane().add(chckbxPorcentaje);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(12, 57, 75, 15);
		getContentPane().add(lblCategoria);
		
		txtCategoria = new JTextPane();
		txtCategoria.setEditable(false);
		txtCategoria.setBounds(174, 51, 291, 21);
		getContentPane().add(txtCategoria);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(516, 90, 75, 15);
		getContentPane().add(lblImagen);
		
		
		
	}
	
	public void cargarColaboradores(String titulo) {
		Vector<Colaborador> model = new Vector<Colaborador>();
		Fabrica fab = Fabrica.getInstance();
		ICP = fab.getIControladorPropuesta();
		Map<String,Colaborador> colaboradores = (Map<String,Colaborador>)ICP.obtenerColaboradores(titulo);
		if (colaboradores != null) {
			Iterator<Map.Entry<String, Colaborador>> it = colaboradores.entrySet().iterator();
			while (it.hasNext()) {
			    Map.Entry<String,Colaborador> e = (Map.Entry<String,Colaborador>)it.next();
			    Colaborador prop = e.getValue();
			    model.add(prop);
			}
			DefaultComboBoxModel<Colaborador> modelo = new DefaultComboBoxModel<Colaborador>(model);
			comboBoxColaboradores.setModel(modelo);
		}
		comboBoxColaboradores.setSelectedIndex(-1);
	}
	
	
	public void limpiarFormulario() {
		comboBoxPropuestas.setSelectedIndex(-1);
		txtTitulo.setText("");
		txtDescripcion.setText("");
		txtLugar.setText("");
		txtFecha.setText("");
		txtEntrada.setText("");
		txtMonto.setText("");
		txtCategoria.setText("");
		txtMontoActual.setText("");
		txtFechaIngreso.setText("");
		chckbxPorcentaje.setSelected(false);
		chckbxEntradas.setSelected(false);
		lblImg.setIcon(null);
		txtEstado.setText("");
		setSize(500, 600);		
	}
}
