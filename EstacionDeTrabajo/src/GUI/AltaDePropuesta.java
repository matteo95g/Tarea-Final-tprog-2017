package GUI;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import datatypes.Retorno;
import excepciones.PropuestaRepetidaException;
import interfaces.IControladorCategoria;
import interfaces.IControladorPropuesta;
import interfaces.IControladorUsuario;
import logica.Categoria;
import logica.Proponente;
import manejadores.ManejadorCategoria;

@SuppressWarnings("serial")
public class AltaDePropuesta extends JInternalFrame {
	
	
	private IControladorUsuario ICU;
	private IControladorCategoria ICC;
	private IControladorPropuesta ICP;
	
	private JTextField txtTitulo;
	private JTextField txtLugar;
	private JTextField txtEntrada;
	private JTextField txtMonto;
	private JTextField txtImg;
	private JTree tree;
	private DefaultMutableTreeNode nodoSeleccionado;
	private JTextField txtTipo;
	private JComboBox<Proponente> comboBoxProponentes;
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<String> comboBoxAnio;
	private JTextArea txtDesc;
	private JCheckBox chckbxEntradas;
	private JCheckBox chckbxPorcentaje;
	private File foto;
	private Calendar fecha;
	private List<Retorno> retornos;
	
	private int iAnio, iDia;

	public AltaDePropuesta(IControladorUsuario icusu, IControladorCategoria icc, IControladorPropuesta icp) {
		
		ICU = icusu;
		ICC = icc;
		ICP = icp;
		 
		
		setTitle("Alta de Propuesta");
		setClosable(true);
		setBounds(60, 40, 677, 628);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(538, 559, 117, 25);
		btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
				AltaPropuestaActionPerformed(e);
				limpiarFormulario();
            }
        });
		getContentPane().add(btnAceptar);
		
		
		comboBoxProponentes = new JComboBox<Proponente>();
		comboBoxProponentes.setBounds(107, 15, 375, 24);
		getContentPane().add(comboBoxProponentes);
		JLabel lblProponente = new JLabel("Proponente:");
		lblProponente.setBounds(12, 20, 96, 15);
		getContentPane().add(lblProponente);
		
		JLabel lblTipoDeEspectaculo = new JLabel("Tipo de Espectaculo:"); 
		lblTipoDeEspectaculo.setBounds(12, 55, 150, 15);
		getContentPane().add(lblTipoDeEspectaculo);
		List<Categoria> categorias = (LinkedList<Categoria>)ICC.getCategorias();
		ListIterator<Categoria> iter = categorias.listIterator();
		Categoria root = iter.next();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(root.getNombre());
		createNodes(top,root);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(409, 559, 117, 25);
		getContentPane().add(btnCancelar);
		 
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(280, 83, 70, 15);
		getContentPane().add(lblTitulo);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(280, 218, 96, 15);
		getContentPane().add(lblDescripcion);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(280, 110, 70, 15);
		getContentPane().add(lblLugar);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(280, 137, 70, 15);
		getContentPane().add(lblFecha);
		
		JLabel lblPrecioDeLa = new JLabel("Precio de la entrada:");
		lblPrecioDeLa.setBounds(280, 164, 158, 15);
		getContentPane().add(lblPrecioDeLa);
		
		JLabel lblMontoNecesario = new JLabel("Monto necesario:");
		lblMontoNecesario.setBounds(280, 191, 132, 15);
		getContentPane().add(lblMontoNecesario);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(280, 330, 70, 15);
		getContentPane().add(lblImagen);
		
		txtTitulo = new JTextField();
		txtTitulo.setToolTipText("");
		txtTitulo.setBounds(366, 81, 289, 19);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblRetorno = new JLabel("Retorno:");
		lblRetorno.setBounds(280, 303, 70, 15);
		getContentPane().add(lblRetorno);
		
		txtLugar = new JTextField();
		txtLugar.setColumns(10);
		txtLugar.setBounds(366, 108, 289, 19);
		getContentPane().add(txtLugar);
		
		txtEntrada = new JTextField();
		txtEntrada.setColumns(10);
		txtEntrada.setBounds(456, 162, 199, 19);
		getContentPane().add(txtEntrada);
		
		txtMonto = new JTextField();
		txtMonto.setColumns(10);
		txtMonto.setBounds(456, 189, 199, 19);
		getContentPane().add(txtMonto);
		
		txtImg = new JTextField();
		txtImg.setEditable(false);
		txtImg.setColumns(10);
		txtImg.setBounds(384, 328, 210, 19);
		getContentPane().add(txtImg);
	
		foto = null;
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Imagenes JPG & PNG", "jpg", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(getParent()); 
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                	foto = chooser.getSelectedFile();
                	txtImg.setText(foto.getAbsolutePath());
                }
			}
		});
		button.setBounds(606, 330, 49, 15);
		getContentPane().add(button);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(280, 357, 375, 190);
		getContentPane().add(lblImg);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(384, 218, 271, 68);
		getContentPane().add(scrollPane_1);
		
		txtDesc = new JTextArea();
		txtDesc.setLineWrap(true);
		scrollPane_1.setViewportView(txtDesc);
		
		txtTipo = new JTextField();
		txtTipo.setEditable(false);
		txtTipo.setToolTipText("");
		txtTipo.setColumns(10);
		txtTipo.setBounds(169, 53, 158, 19);
		getContentPane().add(txtTipo);
		
		
		
		tree = new JTree();
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				nodoSeleccionado = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if (nodoSeleccionado == null) {
					return;
				} else {
					Object nodeInfo = nodoSeleccionado.getUserObject();
					String categoria = (String)nodeInfo;
					txtTipo.setText(categoria);
				}
			}
		});
		tree.setBackground(UIManager.getColor("Button.background"));
		tree.setBounds(12, 81, 250, 503);
		getContentPane().add(tree);
		
		chckbxEntradas = new JCheckBox("Entradas");
		chckbxEntradas.setBounds(384, 299, 97, 23);
		getContentPane().add(chckbxEntradas);
		
		chckbxPorcentaje = new JCheckBox("Porcentaje");
		chckbxPorcentaje.setBounds(509, 299, 132, 23);
		getContentPane().add(chckbxPorcentaje);
		
		comboBoxDia = new JComboBox<String>();
		comboBoxDia.setBackground(Color.WHITE);
		comboBoxDia.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		comboBoxDia.setBounds(86, 140, 60, 24);
		comboBoxDia.setBounds(366, 134, 60, 20);
		getContentPane().add(comboBoxDia);
		
		comboBoxMes = new JComboBox<String>();
		comboBoxMes.setBackground(Color.WHITE);
		comboBoxMes.setModel(new DefaultComboBoxModel<String>(new String[] { "Mes", "Enero", "Febrero", "Marzo",
				"Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre" }));
		comboBoxMes.setBounds(454, 134, 88, 20);
		getContentPane().add(comboBoxMes);
		
		comboBoxAnio = new JComboBox<String>();
		comboBoxAnio.setBackground(Color.WHITE);
		comboBoxAnio.setModel(new DefaultComboBoxModel<String>(new String[] {"Año", "2017", "2018", "2019", "2020", "2021","2022"}));
		comboBoxAnio.setBounds(569, 134, 62, 20);
		getContentPane().add(comboBoxAnio);
		
	}
	
	public void cargarProponentes() {
		Vector<Proponente> model = new Vector<Proponente>();
		Map<String,Proponente> proponentes = (HashMap<String,Proponente>)ICU.getProponentes();
		Iterator<Map.Entry<String, Proponente>> it = proponentes.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String,Proponente> e = (Map.Entry<String,Proponente>)it.next();
		    Proponente prop = e.getValue();
		    model.add(prop);
		}
		DefaultComboBoxModel<Proponente> modelo = new DefaultComboBoxModel<Proponente>(model);
		comboBoxProponentes.setModel(modelo);
		comboBoxProponentes.setSelectedIndex(-1);
	}
	
	public void cargarCategorias() {
		List<Categoria> categorias = (LinkedList<Categoria>)ICC.getCategorias();
		ListIterator<Categoria> iter = categorias.listIterator();
		Categoria root = iter.next();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(root.getNombre());
		createNodes(top,root);
		DefaultTreeModel newModel = new DefaultTreeModel(top);
		tree.setModel(newModel);
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
	

	protected void AltaPropuestaActionPerformed(ActionEvent e) {
		
		String precioEnt = txtEntrada.getText();		
		String montoNes = txtMonto.getText();
		try {
			Integer ImontoNes = Integer.parseInt(montoNes);
			Integer IprecioEnt = Integer.parseInt(precioEnt);
			
			
			String titulo = txtTitulo.getText();
			String lugar = txtLugar.getText();
			String dia = (String)comboBoxDia.getSelectedItem();
			String mes = (String)comboBoxMes.getSelectedItem();
			String anio = (String)comboBoxAnio.getSelectedItem();
		
			
			String descripcion = txtDesc.getText();
			Proponente prop = (Proponente) comboBoxProponentes.getSelectedItem();
			
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
			


			/* == LISTA DE RETORNOS == */
			retornos = new LinkedList<Retorno>();
			if (chckbxEntradas.isSelected()) {
				retornos.add(Retorno.Entradas);
			}
			if (chckbxPorcentaje.isSelected()) {
				retornos.add(Retorno.Porcentaje);
			}
			/* == FIN LISTA DE RETORNOS == */

			if (checkFormulario()) {
				ManejadorCategoria mcat = ManejadorCategoria.getInstancia();
				Categoria categoria = mcat.getCategoria(txtTipo.getText());
				fecha = Calendar.getInstance();			
				fecha.set(iAnio, iMes, iDia);
				
				try {
					
					File fotoFile = foto;
					if (fotoFile != null) {
						
						String extImg = null;
						
						String absolutePath = fotoFile.getAbsolutePath();
						int i = absolutePath.lastIndexOf('.');
						if (i > 0) {
							extImg = "." + absolutePath.substring(i+1);
						}

						byte[] fotoBytes = new byte[(int) fotoFile.length()];
						FileInputStream fotoInputStream = new FileInputStream(fotoFile);
						fotoInputStream.read(fotoBytes);
						fotoInputStream.close();
						
						ICP.addPropuesta(titulo, descripcion, fotoBytes, extImg, lugar, fecha, IprecioEnt, ImontoNes, retornos, categoria, prop);
						
					} else {
						
						byte[] arrayVacio = new byte[0];

						ICP.addPropuesta(titulo, descripcion, arrayVacio, null, lugar, fecha, IprecioEnt, ImontoNes, retornos, categoria, prop);
						
					}
					
					//CAMBIO DE ESTADO DE INGRESADA A PUBLICADA (NO VA MAS)
					//Propuesta propu = ICP.seleccionarPropuesta(titulo);
					//FechaCambio estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
					//propu.setEstado(estado);
					
					JOptionPane.showMessageDialog(this, "La propuesta " + titulo + " se ha creado con éxito", "Alta de Perfil",
	                        JOptionPane.INFORMATION_MESSAGE);
					
					limpiarFormulario();
					setVisible(false);
				}catch (PropuestaRepetidaException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Alta de Perfil", JOptionPane.ERROR_MESSAGE);
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
				}
				
			}
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Solo valores numericos para el monto y los precios", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
		}
		
		

	}
	
	private boolean checkFormulario() {
		
		String titulo = txtTitulo.getText();
		String lugar = txtLugar.getText();
		String dia = (String)comboBoxDia.getSelectedItem();
		String mes = (String)comboBoxMes.getSelectedItem();
		String anio = (String)comboBoxAnio.getSelectedItem();
		String precioEnt = txtEntrada.getText();
		String montoNes = txtMonto.getText();
		String descripcion = txtDesc.getText();
		
		if (titulo.isEmpty() || lugar.isEmpty() || dia.equals("Dia") || mes.equals("Mes") || anio.equals("Año") || precioEnt.isEmpty()
				|| montoNes.isEmpty() || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Rellene los campos obligatorios(*)", "Alta de Propuesta",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		if (!ICP.tituloLibre(titulo) ) {
			JOptionPane.showMessageDialog(this, "Ya existe otra propuesta con este titulo", "Alta de Propuesta",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		if (retornos.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un retorno", "Alta de Propuesta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (comboBoxProponentes.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un proponente", "Alta de Propuesta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (txtTipo.getText().isEmpty() || (txtTipo.getText().equals("Categoria"))) {
			JOptionPane.showMessageDialog(this, "Seleccione una categoria", "Alta de Propuesta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!checkFecha()) {
			JOptionPane.showMessageDialog(this, "Fecha incorrecta", "Alta de Propuesta",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		return true;
	}
	
	
	private void limpiarFormulario() {
		txtTitulo.setText("");
		txtLugar.setText("");
		txtEntrada.setText("");
		txtMonto.setText("");
		txtImg.setText("");
		foto=null;
		comboBoxDia.setSelectedIndex(0);
		comboBoxMes.setSelectedIndex(0);
		comboBoxAnio.setSelectedIndex(0);
		txtDesc.setText("");
		txtTipo.setText("");
		foto = null;
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
			iMes = 9;
		} else if (mes.equals("Noviembre")) {
			iMes = 10;
		} else {
			iMes = 11;
		}
		
		if ((iMes == 3) || (iMes == 5) || (iMes == 8) || (iMes == 10)) {
			if (iDia > 30) {
				return false;
			}
		}
		if ((iMes==1) && (iDia > 28)){
			return false;
		}
		
		Calendar hoy = Calendar.getInstance();
		int hoyDia = hoy.get(Calendar.DATE);
		int hoyMes = hoy.get(Calendar.MONTH);

		
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
}