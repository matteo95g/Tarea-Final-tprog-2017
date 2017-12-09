package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controladores.Configuracion;
import interfaces.IControladorUsuario;
import logica.Colaborador;
import logica.Proponente;
import logica.Propuesta;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class ConsultaPerfilProponente extends JInternalFrame {
	
	private JTextField textFieldNick;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldMail;
	private JTextField textFieldFechaNacimiento;
	private JTextField textFieldDireccion;
	private JTextField textFieldSitioWeb;
	private JComboBox<Proponente> comboBoxProponentes;
	private JLabel lblImgUsr;
	private IControladorUsuario ICUsu;
	private Configuracion Config;
	private DefaultMutableTreeNode nodoSeleccionado;
	private Proponente cmbBoxPropSel;
	private JTree tree;
	private JPanel panelDatos;
	private JPanel panelPropuestas;
	private JTextField textFieldTitulo;
	private JTextField textFieldLugar;
	private JTextField textFieldFecha;
	private JTextField textFieldPrecio;
	private JTextField textFieldDineroC;
	private JTextField textFieldDineroN;
	private JLabel lblImgProp;
	private JTextField textFieldCategoria;
	private JTextPane textPaneDesc;
	private Propuesta prop;
	private JList<Colaborador> listCols;
	private JButton btnCerrar;
	private JScrollPane scrollPaneBio;
	private JTextPane textPaneBio;
	private JScrollPane scrollPaneDesc;

	public ConsultaPerfilProponente(IControladorUsuario icusu, Configuracion config) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		ICUsu = icusu;
		Config = config;
		
		setClosable(true);
		setTitle("Consulta de perfil de proponente");
		setBounds(100,100,545,555);
		getContentPane().setLayout(null);
		
		JLabel lblSeleccionarProponente = new JLabel("Seleccionar Proponente:");
		lblSeleccionarProponente.setBounds(10, 12, 186, 22);
		getContentPane().add(lblSeleccionarProponente);
		
		comboBoxProponentes = new JComboBox<Proponente>();
		comboBoxProponentes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cmbBoxPropSel = (Proponente)comboBoxProponentes.getSelectedItem();
				if (cmbBoxPropSel != null) {
					panelDatos.setVisible(true);
					panelPropuestas.setVisible(false);
					textFieldNick.setText(cmbBoxPropSel.getNickname());
					textFieldNombre.setText(cmbBoxPropSel.getNombre());
					textFieldApellido.setText(cmbBoxPropSel.getApellido());
					textFieldMail.setText(cmbBoxPropSel.getCorreoElectronico());
					textFieldDireccion.setText(cmbBoxPropSel.getDireccion());
					textPaneBio.setText(cmbBoxPropSel.getBiografia());
					textFieldSitioWeb.setText(cmbBoxPropSel.getSitioWeb());
					Calendar nac = cmbBoxPropSel.getFechaNacimiento();
					int dia = nac.get(Calendar.DAY_OF_MONTH);
					int mes = nac.get(Calendar.MONTH) + 1;
					int anio = nac.get(Calendar.YEAR);
					String Sdia = Integer.toString(dia); 
					String Smes = Integer.toString(mes);
					String Sanio = Integer.toString(anio);
					textFieldFechaNacimiento.setText(Sdia + " / " + Smes + " / " + Sanio);
					String pathImagenes = Config.getPathImagenes();
					String pathImg = pathImagenes + cmbBoxPropSel.getImagen();
					ImageIcon img = new ImageIcon(pathImg);
					Icon icono = new ImageIcon(img.getImage().getScaledInstance(lblImgUsr.getWidth(), lblImgUsr.getHeight(), Image.SCALE_DEFAULT));
					lblImgUsr.setIcon(icono);
					setSize(545, 555);
					btnCerrar.setLocation(225,495);
					cargarPropuestas();
				}
			}
		});
		comboBoxProponentes.setBounds(195, 12, 323, 24);
		getContentPane().add(comboBoxProponentes);
		
	/* ==================== BEGIN PANEL DATOS ==================== */
		
		panelDatos = new JPanel();
		panelDatos.setBorder(null);
		panelDatos.setBounds(12, 46, 508, 442);
		panelDatos.setLayout(null);
		getContentPane().add(panelDatos);
		
		JLabel lblNick = new JLabel("NickName:");
		lblNick.setBounds(12, 15, 83, 15);
		panelDatos.add(lblNick);
		
		textFieldNick = new JTextField();
		textFieldNick.setBackground(Color.WHITE);
		textFieldNick.setEditable(false);
		textFieldNick.setBounds(100, 15, 215, 20);
		panelDatos.add(textFieldNick);
		textFieldNick.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 45, 70, 15);
		panelDatos.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(Color.WHITE);
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(100, 45, 215, 20);
		panelDatos.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(12, 75, 70, 15);
		panelDatos.add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBackground(Color.WHITE);
		textFieldApellido.setEditable(false);
		textFieldApellido.setBounds(100, 75, 215, 20);
		panelDatos.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("e-Mail:");
		lblEmail.setBounds(12, 105, 70, 15);
		panelDatos.add(lblEmail);
		
		textFieldMail = new JTextField();
		textFieldMail.setBackground(Color.WHITE);
		textFieldMail.setEditable(false);
		textFieldMail.setBounds(100, 105, 215, 20);
		panelDatos.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setBounds(12, 135, 155, 15);
		panelDatos.add(lblFechaDeNacimiento);
		
		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setBackground(Color.WHITE);
		textFieldFechaNacimiento.setEditable(false);
		textFieldFechaNacimiento.setBounds(169, 135, 146, 20);
		panelDatos.add(textFieldFechaNacimiento);
		textFieldFechaNacimiento.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(12, 185, 70, 15);
		panelDatos.add(lblDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBackground(Color.WHITE);
		textFieldDireccion.setEditable(false);
		textFieldDireccion.setBounds(132, 185, 373, 20);
		panelDatos.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		scrollPaneBio = new JScrollPane();
		scrollPaneBio.setBounds(132, 215, 373, 145);
		panelDatos.add(scrollPaneBio);
		
		textPaneBio = new JTextPane();
		textPaneBio.setEditable(false);
		scrollPaneBio.setViewportView(textPaneBio);
		
		JLabel lblSitioWeb = new JLabel("Sitio Web:");
		lblSitioWeb.setBounds(12, 375, 83, 15);
		panelDatos.add(lblSitioWeb);
		
		textFieldSitioWeb = new JTextField();
		textFieldSitioWeb.setBackground(Color.WHITE);
		textFieldSitioWeb.setEditable(false);
		textFieldSitioWeb.setBounds(132, 375, 373, 19);
		panelDatos.add(textFieldSitioWeb);
		textFieldSitioWeb.setColumns(10);
		
		JButton btnVerPropuestas = new JButton("Ver propuestas");
		btnVerPropuestas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (checkFormulario()) {
	               cargarPropuestas();
	               limpiarFormularioPropuestas();
	               limpiarFormulario();
	               setSize(545, 750);
	               btnCerrar.setLocation(215,685);
	               panelDatos.setVisible(false);
	               panelPropuestas.setVisible(true);
            	}
            }
        });
		btnVerPropuestas.setBounds(183, 410, 160, 25);
		panelDatos.add(btnVerPropuestas);
		
		JLabel lblBiografia = new JLabel("Biografia:"); 
		lblBiografia.setBounds(12, 215, 70, 15);
		panelDatos.add(lblBiografia);
		
		lblImgUsr = new JLabel();
		lblImgUsr.setBounds(325,0,182,175);
		panelDatos.add(lblImgUsr);
		
	/* ==================== END PANEL DATOS ==================== */
		
	/* ==================== BEGIN PANEL PROPUESTAS ==================== */
		
		panelPropuestas = new JPanel();
		panelPropuestas.setBorder(null);
		panelPropuestas.setBounds(10, 50, 515, 630);
		panelPropuestas.setLayout(null);
		getContentPane().add(panelPropuestas);
		
		JScrollPane scrollPaneTree = new JScrollPane();
		scrollPaneTree.setBounds(1, 1, 220, 449);
		panelPropuestas.add(scrollPaneTree);
		
		tree = new JTree();
		tree.setBorder(null);
		scrollPaneTree.setViewportView(tree);
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				nodoSeleccionado = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if (nodoSeleccionado == null) {
					return;
				} else {
					Object nodeInfo = nodoSeleccionado.getUserObject();
					String filtro = nodeInfo.toString();
					if (!filtro.equals("Propuestas") && !filtro.equals("Ingresadas") && !filtro.equals("Publicadas") &&
							!filtro.equals("En Financiación") && !filtro.equals("Financiadas") && !filtro.equals("No Financiadas")
							&& !filtro.equals("Canceladas")) {
						prop = (Propuesta)nodeInfo;
						textFieldTitulo.setText(prop.getTitulo());
						textFieldCategoria.setText(prop.getCategoria().getNombre());
						textPaneDesc.setText(prop.getDescripcion());
						textFieldLugar.setText(prop.getLugar());
						/* == CARGO FECHA == */
						Calendar nac = prop.getFechaRealizacion();
						int dia = nac.get(Calendar.DAY_OF_MONTH);
						int mes = nac.get(Calendar.MONTH) + 1;
						int anio = nac.get(Calendar.YEAR);
						String Sdia = Integer.toString(dia);
						String Smes = Integer.toString(mes);
						String Sanio = Integer.toString(anio);
						textFieldFecha.setText(Sdia + " / " + Smes + " / " + Sanio);
						/* == FIN CARGO FECHA == */
						/* == CARGO NUMEROS == */
						int precioEnt = prop.getPrecioEntrada();
						int montoNec = prop.getMontoNecesario();
						int montoAct = prop.getMontoActual();
						String SprecioEnt = Integer.toString(precioEnt);
						String SmontoNec = Integer.toString(montoNec);
						String SmontoAct = Integer.toString(montoAct);
						textFieldPrecio.setText(SprecioEnt);
						textFieldDineroC.setText(SmontoAct);
						textFieldDineroN.setText(SmontoNec);
						/* == FIN CARGO NUMEROS == */
						/* == CARGO LA IMAGEN == */
						String pathImagenes = Config.getPathImagenes();
						String pathImg = pathImagenes + prop.getImagen();
						ImageIcon img = new ImageIcon(pathImg);
						Icon icono = new ImageIcon(img.getImage().getScaledInstance(lblImgProp.getWidth(), lblImgProp.getHeight(), Image.SCALE_DEFAULT));
						lblImgProp.setIcon(icono);
						/* == FIN CARGO LA IMAGEN == */
						/* == CARGO LISTA DE COLABORACIONES == */
						cargarColaboraciones();
						/* == FIN CARGO LISTA DE COLABORACIONES == */
					}
				}
			}
		});
		tree.setBackground(UIManager.getColor("Button.background"));
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(545, 555);
				btnCerrar.setLocation(225,495);
				panelPropuestas.setVisible(false);
				limpiarFormulario();
				panelDatos.setVisible(true);
			}
		});
		btnAtras.setBounds(205, 600, 100, 25);
		panelPropuestas.add(btnAtras);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(225, 10, 52, 15);
		panelPropuestas.add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBackground(Color.WHITE);
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setBounds(275, 8, 230, 19);
		panelPropuestas.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(225, 240, 88, 15);
		panelPropuestas.add(lblDescripcin);

		scrollPaneDesc = new JScrollPane();
		scrollPaneDesc.setBounds(226, 260, 280, 69);
		panelPropuestas.add(scrollPaneDesc);

		textPaneDesc = new JTextPane();
		textPaneDesc.setEditable(false);
		scrollPaneDesc.setViewportView(textPaneDesc);
		
		JLabel lblNewLabel = new JLabel("Lugar:");
		lblNewLabel.setBounds(225, 335, 52, 15);
		panelPropuestas.add(lblNewLabel);
		
		textFieldLugar = new JTextField();
		textFieldLugar.setBackground(Color.WHITE);
		textFieldLugar.setEditable(false);
		textFieldLugar.setBounds(275, 333, 230, 19);
		panelPropuestas.add(textFieldLugar);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		lblNewLabel_1.setBounds(225, 360, 52, 15);
		panelPropuestas.add(lblNewLabel_1);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBackground(Color.WHITE);
		textFieldFecha.setEditable(false);
		textFieldFecha.setBounds(275, 358, 124, 19);
		panelPropuestas.add(textFieldFecha);
		
		JLabel lblNewLabel_2 = new JLabel("Precio de la entrada:");
		lblNewLabel_2.setBounds(225, 385, 142, 15);
		panelPropuestas.add(lblNewLabel_2);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBackground(Color.WHITE);
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setBounds(372, 383, 135, 19);
		panelPropuestas.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JLabel lblDineroConseguido = new JLabel("Dinero conseguido:");
		lblDineroConseguido.setBounds(225, 410, 142, 15);
		panelPropuestas.add(lblDineroConseguido);
		
		textFieldDineroC = new JTextField();
		textFieldDineroC.setBackground(Color.WHITE);
		textFieldDineroC.setEditable(false);
		textFieldDineroC.setBounds(372, 408, 135, 19);
		panelPropuestas.add(textFieldDineroC);
		textFieldDineroC.setColumns(10);
		
		JLabel lblDineroNecesario = new JLabel("Dinero necesario:");
		lblDineroNecesario.setBounds(225, 435, 131, 15);
		panelPropuestas.add(lblDineroNecesario);
		
		textFieldDineroN = new JTextField();
		textFieldDineroN.setBackground(Color.WHITE);
		textFieldDineroN.setEditable(false);
		textFieldDineroN.setBounds(372, 433, 135, 19);
		panelPropuestas.add(textFieldDineroN);
		textFieldDineroN.setColumns(10);
		
		lblImgProp = new JLabel();
		lblImgProp.setBounds(285, 35, 175,170);
		panelPropuestas.add(lblImgProp);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(225, 215, 88, 15);
		panelPropuestas.add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBackground(Color.WHITE);
		textFieldCategoria.setEditable(false);
		textFieldCategoria.setBounds(300, 213, 205, 19);
		panelPropuestas.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		JLabel lblColaboradores = new JLabel("Colaboradores:");
		lblColaboradores.setBounds(10, 460, 124, 15);
		panelPropuestas.add(lblColaboradores);
		
		JScrollPane scrollPaneColab = new JScrollPane();
		scrollPaneColab.setBounds(148, 462, 355, 130);
		panelPropuestas.add(scrollPaneColab);
		
		listCols = new JList<Colaborador>();
		listCols.setBorder(null);
		scrollPaneColab.setViewportView(listCols);
		
		
		
	/* ==================== END PANEL PROPUESTAS ==================== */
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               limpiarFormulario();
               setVisible(false);
            }
        });
		btnCerrar.setBounds(225,495,100,25);
		getContentPane().add(btnCerrar);

	}
	
	public void cargarProponentes() {
		Vector<Proponente> model = new Vector<Proponente>();
		Map<String,Proponente> proponentes = (HashMap<String,Proponente>)ICUsu.getProponentes();
		Iterator<Map.Entry<String, Proponente>> it = proponentes.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String,Proponente> e = it.next();
		    Proponente prop = e.getValue();
		    model.add(prop);
		}
		DefaultComboBoxModel<Proponente> modelo = new DefaultComboBoxModel<Proponente>(model);
		comboBoxProponentes.setModel(modelo);
		comboBoxProponentes.setSelectedIndex(-1);
	}
	
	public void cargarPropuestas() {
		Map<String,Propuesta> propuestas = (HashMap<String, Propuesta>)cmbBoxPropSel.getPropuestas();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Propuestas");
		DefaultMutableTreeNode ingresadas = new DefaultMutableTreeNode("Ingresadas");
		top.add(ingresadas);
		DefaultMutableTreeNode publicadas = new DefaultMutableTreeNode("Publicadas");
		top.add(publicadas);
		DefaultMutableTreeNode enFinanciacion = new DefaultMutableTreeNode("En Financiación");
		top.add(enFinanciacion);
		DefaultMutableTreeNode financiadas = new DefaultMutableTreeNode("Financiadas");
		top.add(financiadas);
		DefaultMutableTreeNode noFinanciadas = new DefaultMutableTreeNode("No Financiadas");
		top.add(noFinanciadas);
		DefaultMutableTreeNode canceladas = new DefaultMutableTreeNode("Canceladas");
		top.add(canceladas);
		Iterator<Map.Entry<String, Propuesta>> it = propuestas.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Propuesta> entry = it.next();
			Propuesta prop = entry.getValue();
			switch(prop.getEstado().getEstado()) {
				case Ingresada:
					DefaultMutableTreeNode nuevaIng = new DefaultMutableTreeNode(prop);
					ingresadas.add(nuevaIng);
					break;
				case Publicada:
					DefaultMutableTreeNode nuevaPub = new DefaultMutableTreeNode(prop);
					publicadas.add(nuevaPub);
					break;
				case EnFinanciacion:
					DefaultMutableTreeNode nuevaEnf = new DefaultMutableTreeNode(prop);
					enFinanciacion.add(nuevaEnf);
					break;
				case Financiada:
					DefaultMutableTreeNode nuevaFin = new DefaultMutableTreeNode(prop);
					financiadas.add(nuevaFin);
					break;
				case NoFinanciada:
					DefaultMutableTreeNode nuevaNof = new DefaultMutableTreeNode(prop);
					noFinanciadas.add(nuevaNof);
					break;
				case Cancelada:
					DefaultMutableTreeNode nuevaCan = new DefaultMutableTreeNode(prop);
					canceladas.add(nuevaCan);
					break;
			}
		}
		DefaultTreeModel treemodel = new DefaultTreeModel(top);
		tree.setModel(treemodel);
	}
	
	public void cargarColaboraciones() {
		Map<String,Colaborador> colabs = (HashMap<String,Colaborador>)prop.getColaboradores();
		Iterator<Map.Entry<String, Colaborador>> it = colabs.entrySet().iterator();
		DefaultListModel<Colaborador> listModel = new DefaultListModel<Colaborador>();
		while (it.hasNext()) {
			Map.Entry<String, Colaborador> entry = it.next();
			Colaborador col = entry.getValue();
			listModel.addElement(col);
		}
		listCols.setModel(listModel);
	}
	
	public void limpiarFormularioDatos() {
		textFieldNick.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldMail.setText("");
		textFieldFechaNacimiento.setText("");
		comboBoxProponentes.setSelectedItem(-1);
		textFieldDireccion.setText("");
		textPaneBio.setText("");
		textFieldSitioWeb.setText("");
		lblImgUsr.setIcon(null);
	}
	
	public void limpiarFormularioPropuestas() {
		textFieldTitulo.setText("");
		textPaneDesc.setText("");
		textFieldLugar.setText("");
		textFieldFecha.setText("");
		textFieldPrecio.setText("");
		textFieldDineroC.setText("");
		textFieldDineroN.setText("");
		lblImgProp.setIcon(null);
		textFieldCategoria.setText("");
		DefaultListModel<Colaborador> listModel = new DefaultListModel<Colaborador>();
		listCols.setModel(listModel);
	}
	
	public void limpiarFormulario() {
		cargarProponentes();
		comboBoxProponentes.setSelectedItem(-1);
		limpiarFormularioDatos();
		limpiarFormularioPropuestas();
		panelPropuestas.setVisible(false);
		setSize(545, 555);
		btnCerrar.setLocation(225,495);
		panelDatos.setVisible(false);
		DefaultListModel<Colaborador> listModel = new DefaultListModel<Colaborador>();
		listCols.setModel(listModel);
	}
	
	public boolean checkFormulario() {
		if (comboBoxProponentes.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Seleccione un proponente", "Consulta de Perfil de Proponente",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}
