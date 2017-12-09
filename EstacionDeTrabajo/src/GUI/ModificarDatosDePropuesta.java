package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controladores.Configuracion;
import interfaces.IControladorCategoria;
import interfaces.IControladorPropuesta;
import logica.Categoria;
import logica.Propuesta;
import manejadores.ManejadorCategoria;

@SuppressWarnings("serial")
public class ModificarDatosDePropuesta extends JInternalFrame {
	
	private JComboBox<Propuesta> comboBoxPropuestas;
	private JTextField txtMontoActual;
	private JTextField txtMontoNecesario;
	private JTextField txtPrecio;
	private JTextField txtLugar;
	private JTextField txtImagen;
	private JTextField txtTitulo;
	private IControladorPropuesta ICP;
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<String> comboBoxAnio;
	private File foto;
	private JTextPane txtDescripcion;
	private Configuracion Config;
	private JLabel lblImg;
	private int iAnio, iDia;
	private Calendar fecha;
	private JTree jTreeCategorias;
	private DefaultMutableTreeNode nodoSeleccionado;
	private IControladorCategoria ICCat;
	private JTextField txtCategoria;
	private String nomCategoria;

	
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

	public ModificarDatosDePropuesta(IControladorPropuesta icp, IControladorCategoria iccat, Configuracion config) {
		ICP = icp;
		Config = config;
		ICCat = iccat;
		
		setClosable(true);
		setTitle("Modificar datos de propuesta");
		setBounds(100, 100, 493, 799);
		getContentPane().setLayout(null);
		
		comboBoxPropuestas = new JComboBox<Propuesta>();
		//comboBoxPropuestas.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una propuesta"}));
		comboBoxPropuestas.addItemListener(new ItemListener() {
			//private AbstractButton lblImg;

			public void itemStateChanged(ItemEvent e) {
				Propuesta propSelec = (Propuesta)comboBoxPropuestas.getSelectedItem();
				if (propSelec != null && comboBoxPropuestas.getSelectedIndex() != -1) {
					txtCategoria.setText(propSelec.getCategoria().getNombre());
					txtTitulo.setText(propSelec.getTitulo());
					txtDescripcion.setText(propSelec.getDescripcion());
					txtImagen.setText(propSelec.getImagen());
					txtLugar.setText(propSelec.getLugar());
					String Sprecio = Integer.toString(propSelec.getPrecioEntrada());
					txtPrecio.setText(Sprecio);
					String SMontoN = Integer.toString(propSelec.getMontoNecesario());
					txtMontoNecesario.setText(SMontoN);
					String SMontoA = Integer.toString(propSelec.getMontoActual());
					txtMontoActual.setText(SMontoA);
					Calendar fecha = propSelec.getFechaRealizacion();
					int dia = fecha.get(Calendar.DAY_OF_MONTH);
					int mes = fecha.get(Calendar.MONTH);
					int anio = fecha.get(Calendar.YEAR);
					comboBoxDia.setSelectedIndex(dia);
					comboBoxMes.setSelectedIndex(mes);
					comboBoxAnio.setSelectedIndex(anio-2016);
					String pathImagenes = Config.getPathImagenes();
					String pathImg = pathImagenes + propSelec.getImagen();
					ImageIcon img = new ImageIcon(pathImg);
					Icon icono = new ImageIcon(img.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_DEFAULT));
					lblImg.setIcon(icono);
					
					
					
				}
				
			}
		});
		comboBoxPropuestas.setBounds(12, 12, 461, 24);
		getContentPane().add(comboBoxPropuestas);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(12, 71, 70, 15);
		getContentPane().add(lblTitulo);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 431, 98, 15);
		getContentPane().add(lblDescripcion);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(12, 110, 70, 15);
		getContentPane().add(lblImagen);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(12, 572, 70, 15);
		getContentPane().add(lblLugar);
		
		JLabel lblPrecioDeLa = new JLabel("Precio de la entrada:");
		lblPrecioDeLa.setBounds(12, 614, 160, 15);
		getContentPane().add(lblPrecioDeLa);
		
		JLabel lblMontoNecesario = new JLabel("Monto necesario:");
		lblMontoNecesario.setBounds(12, 660, 140, 15);
		getContentPane().add(lblMontoNecesario);
		
		JLabel lblMontoActual = new JLabel("Monto actual:");
		lblMontoActual.setBounds(12, 701, 140, 15);
		getContentPane().add(lblMontoActual);
		
		txtMontoActual = new JTextField();
		txtMontoActual.setEditable(false);
		txtMontoActual.setColumns(10);
		txtMontoActual.setBounds(195, 699, 258, 19);
		getContentPane().add(txtMontoActual);
		
		txtMontoNecesario = new JTextField();
		txtMontoNecesario.setColumns(10);
		txtMontoNecesario.setBounds(195, 658, 258, 19);
		getContentPane().add(txtMontoNecesario);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(195, 612, 258, 19);
		getContentPane().add(txtPrecio);
		
		txtLugar = new JTextField();
		txtLugar.setColumns(10);
		txtLugar.setBounds(86, 570, 367, 19);
		getContentPane().add(txtLugar);
		
		txtImagen = new JTextField();
		txtImagen.setColumns(10);
		txtImagen.setBounds(12, 130, 245, 19);
		getContentPane().add(txtImagen);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(86, 69, 367, 19);
		getContentPane().add(txtTitulo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				txtDescripcion.setText("");
				txtMontoActual.setText("");
				txtMontoNecesario.setText("");
				txtPrecio.setText("");
				txtLugar.setText("");
				txtImagen.setText("");
				txtTitulo.setText("");
			}
		});
		btnCancelar.setBounds(229, 730, 117, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatosActionPerformed(e);
				setVisible(false);
			}
		});
		btnGuardar.setBounds(356, 730, 117, 25);
		getContentPane().add(btnGuardar);
		
		comboBoxDia = new JComboBox<String>();
		comboBoxDia.setModel(new DefaultComboBoxModel<String>(new String[] {"Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDia.setBackground(Color.WHITE);
		comboBoxDia.setBounds(86, 524, 60, 24);
		getContentPane().add(comboBoxDia);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(12, 529, 70, 15);
		getContentPane().add(lblFecha);
		
		comboBoxMes = new JComboBox<String>();
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(new String[] {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"}));
		comboBoxMes.setBackground(Color.WHITE);
		comboBoxMes.setBounds(158, 524, 99, 24);
		getContentPane().add(comboBoxMes);
		
		comboBoxAnio = new JComboBox<String>();
		comboBoxAnio.setModel(new DefaultComboBoxModel<String>(new String[] {"Anio", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040"}));
		comboBoxAnio.setBackground(Color.WHITE);
		comboBoxAnio.setBounds(269, 524, 69, 24);
		getContentPane().add(comboBoxAnio);
		
		JButton chooseFile = new JButton("...");
		chooseFile.setBounds(111, 161, 41, 15);
		chooseFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	foto = null;
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Imagenes JPG & PNG", "jpg", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                	foto = chooser.getSelectedFile();
                	txtImagen.setText(chooser.getSelectedFile().getAbsolutePath());
                	ImageIcon img = new ImageIcon(txtImagen.getText());
					Icon icono = new ImageIcon(img.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_DEFAULT));
					lblImg.setIcon(icono);
                }
                
            }
        });
		getContentPane().add(chooseFile);
		
		lblImg = new JLabel("");
		lblImg.setBounds(31, 197, 190, 170);
		getContentPane().add(lblImg);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 454, 459, 58);
		getContentPane().add(scrollPane);
		
		txtDescripcion = new JTextPane();
		scrollPane.setViewportView(txtDescripcion);
		
		jTreeCategorias = new JTree();
		jTreeCategorias.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				nodoSeleccionado = (DefaultMutableTreeNode)jTreeCategorias.getLastSelectedPathComponent();
				if (nodoSeleccionado == null) {
					return;
				} else {
					Object nodeInfo = nodoSeleccionado.getUserObject();
					nomCategoria = (String)nodeInfo;
					txtCategoria.setText(nomCategoria);
				}
			}
		});
		jTreeCategorias.setBounds(269, 137, 190, 238);
		getContentPane().add(jTreeCategorias);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(321, 110, 70, 15);
		getContentPane().add(lblCategoria);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(269, 393, 190, 19);
		getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);
		
		

	}
	
	public void limpiarFormulario() {
		cargarPropuestas();
		cargarCategorias();
		txtTitulo.setText("");
		txtImagen.setText("");
		comboBoxPropuestas.setSelectedIndex(-1);
		txtDescripcion.setText("");
		txtLugar.setText("");
		txtPrecio.setText("");
		txtMontoNecesario.setText("");
		txtMontoActual.setText("");
		comboBoxDia.setSelectedIndex(0);
		comboBoxMes.setSelectedIndex(0);
		comboBoxAnio.setSelectedIndex(0);
		lblImg.setIcon(null);
	}
	
	private boolean checkFormulario() {
		String titulo = txtTitulo.getText();
		String descripcion = txtDescripcion.getText();
		String lugar = txtLugar.getText();
		String precio = txtPrecio.getText();
		String dia = (String)comboBoxDia.getSelectedItem();
		String mes = (String)comboBoxMes.getSelectedItem();
		String anio = (String)comboBoxAnio.getSelectedItem();
		String montoN = txtMontoNecesario.getText();
		String montoA = txtMontoActual.getText();
		
		if (titulo.isEmpty() || descripcion.isEmpty() || lugar.isEmpty() || precio.isEmpty() || dia.equals("Dia") ||
				mes.equals("Mes") || anio.equals("Año") || montoN.isEmpty() || montoA.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Rellene los campos obligatorios(*)", "Modificar Propuesta",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
//		if (!isNumeric(precio) || !isNumeric(montoN)) || !isNumeric(montoA) {
//		JOptionPane.showMessageDialog(this, "El precio y el monto deben ser numeros", "Alta de Propuesta",
//                    JOptionPane.ERROR_MESSAGE);
//          return false;
//		}
			

		if (comboBoxPropuestas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una propuesta", "Modificar Propuesta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!checkFecha()) {
			JOptionPane.showMessageDialog(this, "Ingrese una fecha valida", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	

	private boolean checkFecha() {
		String dia = (String)comboBoxDia.getSelectedItem();
		String mes = (String)comboBoxMes.getSelectedItem();
		String anio = (String)comboBoxAnio.getSelectedItem();
		iDia = Integer.parseInt(dia);
		iAnio = Integer.parseInt(anio);
		int iMes;
		if (mes.equals("Enero")) {
			iMes = 0;
		} else if (mes.equals("Febrero")) {
			iMes = 1;
		} else if (mes.equals("Marzo")) {
			iMes = 2;
		} else if (mes.equals("Abril")) {
			iMes = 3;
		} else if (mes.equals("Mayo")) {
			iMes = 4;
		} else if (mes.equals("Junio")) {
			iMes = 5;
		} else if (mes.equals("Julio")) {
			iMes = 6;
		} else if (mes.equals("Agosto")) {
			iMes = 7;
		} else if (mes.equals("Setiembre")) {
			iMes = 8;
		} else if (mes.equals("Octubre")) {
			iMes = 19;
		} else if (mes.equals("Noviembre")) {
			iMes = 10;
		} else {
			iMes = 11;
		}
		
		if ((iMes == 3) || (iMes == 5) || (iMes == 6) || (iMes == 10)) {
			if (iDia > 30) {
				return false;
			}
		}
		if ((iMes==1) && (iDia > 28)){
			return false;
		}
		
		Calendar hoy = Calendar.getInstance();
		int hoyDia = hoy.get(Calendar.DATE);
		int hoyMes = hoy.get(Calendar.MONTH) + 1;
		
		if (iAnio == 2017) {
			if (iMes < hoyMes)
				return false;
			else if (iMes == hoyMes) {
				if (iDia <= hoyDia)
					return false;
			}
		}
		
		return true;
	}
	
	protected void modificarDatosActionPerformed(ActionEvent e) {
		
		String titulo = txtTitulo.getText();
		String lugar = txtLugar.getText();
		String dia = (String)comboBoxDia.getSelectedItem();
		String mes = (String)comboBoxMes.getSelectedItem();
		String anio = (String)comboBoxAnio.getSelectedItem();
		String precio = txtPrecio.getText();
		Integer IprecioEnt = Integer.parseInt(precio);
		String montoN = txtMontoNecesario.getText();
		Integer ImontoNes = Integer.parseInt(montoN);
		String descripcion = txtDescripcion.getText();
		String montoA = txtMontoActual.getText();
		Integer ImontoA = Integer.parseInt(montoA);
		
		int iDia = Integer.parseInt(dia);
		int iAnio = Integer.parseInt(anio);
		int iMes;
		if (mes.equals("Enero")) {
			iMes = 0;
		} else if (mes.equals("Febrero")) {
			iMes = 1;
		} else if (mes.equals("Marzo")) {
			iMes = 2;
		} else if (mes.equals("Abril")) {
			iMes = 3;
		} else if (mes.equals("Mayo")) {
			iMes = 4;
		} else if (mes.equals("Junio")) {
			iMes = 5;
		} else if (mes.equals("Julio")) {
			iMes = 6;
		} else if (mes.equals("Agosto")) {
			iMes = 7;
		} else if (mes.equals("Setiembre")) {
			iMes = 8;
		} else if (mes.equals("Octubre")) {
			iMes = 9;
		} else if (mes.equals("Noviembre")) {
			iMes = 10;
		} else {
			iMes = 11;
		}
		
		if (checkFormulario()) {
			ManejadorCategoria mcat = ManejadorCategoria.getInstancia();
			Categoria categoria = mcat.getCategoria(txtCategoria.getText());
			fecha = Calendar.getInstance();
			fecha.set(iAnio, iMes, iDia);
			
			Propuesta prop = (Propuesta)comboBoxPropuestas.getSelectedItem();
			
			ICP.modificarPropuesta(prop, titulo, descripcion, lugar, fecha, ImontoNes, ImontoA, IprecioEnt, foto, categoria);
			setVisible(false);
		}
	}
	
	public void cargarCategorias() {
		List<Categoria> categorias = (LinkedList<Categoria>)ICCat.getCategorias();
		ListIterator<Categoria> iter = categorias.listIterator();
		Categoria root = iter.next();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(root.getNombre());
		createNodes(top,root);
		DefaultTreeModel newModel = new DefaultTreeModel(top);
		jTreeCategorias.setModel(newModel);
	}

	private void createNodes(DefaultMutableTreeNode node, Categoria categ) {
		if (categ.getHijas() != null) {
			ListIterator<Categoria> iter = categ.getHijas().listIterator();
			while (iter.hasNext()) {
				Categoria categHija = iter.next();
				DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(categHija.getNombre());
				node.add(nodoHijo);
				createNodes(nodoHijo,categHija);
			}
		}
	}
}
