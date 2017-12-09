
package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controladores.Configuracion;
import interfaces.IControladorPropuesta;
import logica.Colaborador;
import logica.Propuesta;

@SuppressWarnings("serial")
public class ConsultaDePropuestaPorEstado extends JInternalFrame {

	private IControladorPropuesta ICP;
	private JPanel panelPropuestas;
	private JTree tree;
	private Configuracion Config;
	private JButton btnCerrar;
	private JTextField textFieldTitulo;
	private JScrollPane scrollPaneDesc;
	private JTextPane textPaneDesc;
	private JTextField textFieldLugar;
	private JTextField textFieldFecha;
	private JTextField textFieldPrecio;
	private DefaultMutableTreeNode nodoSeleccionado;
	private Propuesta prop;
	private JTextField textFieldCategoria;
	private JTextField textFieldDineroN;
	private JTextField textFieldDineroC;
	private JLabel lblImgProp;
	private JList<Colaborador> listCols;
	private JScrollPane scrollPaneColab;

	public ConsultaDePropuestaPorEstado(IControladorPropuesta icp, Configuracion config) {

		ICP= icp;
		Config = config;
		
		setTitle("Consulta De Propuesta Por Estado");
		setClosable(true);
		setBounds(100,100, 584, 750);
		getContentPane().setLayout(null);
		
		panelPropuestas = new JPanel();
		panelPropuestas.setBorder(null);
		panelPropuestas.setBounds(10, 12, 552, 694);
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
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
            }
        });
		btnCerrar.setBounds(225,495,100,25);
		getContentPane().add(btnCerrar);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(225, 186, 52, 15);
		panelPropuestas.add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setBackground(Color.WHITE);
		textFieldTitulo.setBounds(310, 184, 230, 19);
		panelPropuestas.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(225, 240, 88, 15);
		panelPropuestas.add(lblDescripcin);

		scrollPaneDesc = new JScrollPane();
		scrollPaneDesc.setBounds(226, 260, 314, 69);
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
		textFieldLugar.setBounds(310, 333, 230, 19);
		panelPropuestas.add(textFieldLugar);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		lblNewLabel_1.setBounds(225, 360, 52, 15);
		panelPropuestas.add(lblNewLabel_1);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBackground(Color.WHITE);
		textFieldFecha.setEditable(false);
		textFieldFecha.setBounds(392, 358, 148, 19);
		panelPropuestas.add(textFieldFecha);
		
		JLabel lblNewLabel_2 = new JLabel("Precio de la entrada:");
		lblNewLabel_2.setBounds(225, 385, 166, 15);
		panelPropuestas.add(lblNewLabel_2);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBackground(Color.WHITE);
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setBounds(392, 383, 148, 19);
		panelPropuestas.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JLabel lblDineroConseguido = new JLabel("Dinero conseguido:");
		lblDineroConseguido.setBounds(225, 410, 142, 15);
		panelPropuestas.add(lblDineroConseguido);
		
		textFieldDineroC = new JTextField();
		textFieldDineroC.setBackground(Color.WHITE);
		textFieldDineroC.setEditable(false);
		textFieldDineroC.setBounds(392, 408, 148, 19);
		panelPropuestas.add(textFieldDineroC);
		textFieldDineroC.setColumns(10);
		
		JLabel lblDineroNecesario = new JLabel("Dinero necesario:");
		lblDineroNecesario.setBounds(225, 435, 131, 15);
		panelPropuestas.add(lblDineroNecesario);
		
		textFieldDineroN = new JTextField();
		textFieldDineroN.setBackground(Color.WHITE);
		textFieldDineroN.setEditable(false);
		textFieldDineroN.setBounds(392, 433, 148, 19);
		panelPropuestas.add(textFieldDineroN);
		textFieldDineroN.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(225, 215, 88, 15);
		panelPropuestas.add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBackground(Color.WHITE);
		textFieldCategoria.setEditable(false);
		textFieldCategoria.setBounds(310, 213, 230, 19);
		panelPropuestas.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		JLabel lblColaboradores = new JLabel("Colaboradores:");
		lblColaboradores.setBounds(76, 464, 131, 15);
		panelPropuestas.add(lblColaboradores);
		
		scrollPaneColab = new JScrollPane();
		scrollPaneColab.setBounds(225, 462, 315, 130);
		panelPropuestas.add(scrollPaneColab);
		
		listCols = new JList<Colaborador>();
		listCols.setBorder(null);
		scrollPaneColab.setViewportView(listCols);
		
		lblImgProp = new JLabel();
		lblImgProp.setBounds(364, 1, 176, 176);
		panelPropuestas.add(lblImgProp);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnFinalizar.setBounds(423, 657, 117, 25);
		panelPropuestas.add(btnFinalizar);
		
	}


	
	public void cargarPropuestas() {
		Map<String,Propuesta> propuestas = (HashMap<String, Propuesta>)ICP.getPropuestasTitulo();
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
	
	public void limpiarFormulario() {
		cargarPropuestas();
		textFieldTitulo.setText("");
		textPaneDesc.setText("");
		textFieldLugar.setText("");
		textFieldFecha.setText("");
		textFieldPrecio.setText("");
		textFieldDineroC.setText("");
		textFieldDineroN.setText("");
		lblImgProp.setIcon(null);
		DefaultListModel<Colaborador> listModel = new DefaultListModel<Colaborador>();
		listCols.setModel(listModel);
		
	}
}
