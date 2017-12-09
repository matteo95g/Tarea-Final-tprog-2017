package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controladores.Configuracion;
import interfaces.IControladorUsuario;
import logica.Colaboracion;
import logica.Colaborador;
import logica.Propuesta;


@SuppressWarnings("serial")
public class ConsultaPerfilColaborador extends JInternalFrame {
	private JTextField textFieldNick;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldMail;
	private JTextField textFieldFechaNacimiento;
	private JComboBox<Colaborador> comboBoxColaboradores;
	private JLabel lblImg;
	private IControladorUsuario ICUsu;
	private Configuracion Config;
	private JPanel panelDatos;
	private JPanel panelColaboraciones;
	private JButton btnCerrar;
	private JLabel lblColaboraciones;
	private JScrollPane scrollPaneColabs;
	private JList<Propuesta> listColabs;
	private Colaborador cmbBoxColSel;
	private JTextField textFieldTitulo;
	private JLabel lblProponente;
	private JTextField textFieldProponente;
	private JLabel lblImgProp;
	private JTextField textFieldCategoria;
	private JLabel lblDescripcion;
	private JScrollPane scrollPaneDesc;
	private JTextPane textPaneDesc;
	private JTextField textFieldLugar;
	private JTextField textFieldFecha;
	private JTextField textFieldPrecio;
	private JTextField textFieldDineroC;
	private JTextField textFieldDineroN;

	public ConsultaPerfilColaborador(IControladorUsuario icusu, Configuracion config) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		ICUsu = icusu;
		Config = config;
		
		setClosable(true);
		setTitle("Consulta de perfil de Colaborador");
		setBounds(100, 100, 545, 340);
		getContentPane().setLayout(null);
		
		JLabel lblSeleccionarCol = new JLabel("Seleccionar Colaborador:");
		lblSeleccionarCol.setBounds(12, 12, 186, 22);
		getContentPane().add(lblSeleccionarCol);
		
		comboBoxColaboradores = new JComboBox<Colaborador>();
		comboBoxColaboradores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cmbBoxColSel = (Colaborador)comboBoxColaboradores.getSelectedItem(); 
				if (cmbBoxColSel != null) {
					panelColaboraciones.setVisible(false);
					panelDatos.setVisible(true);
					textFieldNick.setText(cmbBoxColSel.getNickname());
					textFieldNombre.setText(cmbBoxColSel.getNombre());
					textFieldApellido.setText(cmbBoxColSel.getApellido());
					textFieldMail.setText(cmbBoxColSel.getCorreoElectronico());
					/* == CARGAR FECHA == */
					Calendar nac = cmbBoxColSel.getFechaNacimiento();
					int dia = nac.get(Calendar.DAY_OF_MONTH);
					int mes = nac.get(Calendar.MONTH) + 1;
					int anio = nac.get(Calendar.YEAR);
					String Sdia = Integer.toString(dia);
					String Smes = Integer.toString(mes);
					String Sanio = Integer.toString(anio);
					textFieldFechaNacimiento.setText(Sdia + " / " + Smes + " / " + Sanio);
					/* == FIN CARGAR FECHA == */
					/* == CARGAR IMAGEN == */
					String pathImagenes = Config.getPathImagenes();
					String pathImg = pathImagenes + cmbBoxColSel.getImagen();
					ImageIcon img = new ImageIcon(pathImg);
					Icon icono = new ImageIcon(img.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_DEFAULT));
					lblImg.setIcon(icono);
					/* == FIN CARGAR IMAGEN == */
					setSize(545, 340);
					btnCerrar.setLocation(215, 275);
					cargarColaboraciones();
				}
			}
		});
		comboBoxColaboradores.setBounds(195, 12, 323, 24);
		getContentPane().add(comboBoxColaboradores);
		
		/* ==================== PANEL DATOS ==================== */
		
		panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setBorder(null);
		panelDatos.setVisible(false);
		panelDatos.setBounds(10, 45, 515, 225);
		getContentPane().add(panelDatos);
		
		JLabel lblNick = new JLabel("NickName:");
		lblNick.setBounds(5, 15, 90, 15);
		panelDatos.add(lblNick);
		
		textFieldNick = new JTextField();
		textFieldNick.setBackground(Color.WHITE);
		textFieldNick.setEditable(false);
		textFieldNick.setBounds(100, 13, 215, 20);
		panelDatos.add(textFieldNick);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(5, 45, 90, 15);
		panelDatos.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(Color.WHITE);
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(100, 43, 215, 20);
		panelDatos.add(textFieldNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(5, 75, 90, 15);
		panelDatos.add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBackground(Color.WHITE);
		textFieldApellido.setEditable(false);
		textFieldApellido.setBounds(100, 73, 215, 20);
		panelDatos.add(textFieldApellido);
		
		JLabel lblEmail = new JLabel("e-Mail:");
		lblEmail.setBounds(5, 105, 70, 15);
		panelDatos.add(lblEmail);
		
		textFieldMail = new JTextField();
		textFieldMail.setBackground(Color.WHITE);
		textFieldMail.setEditable(false);
		textFieldMail.setBounds(100, 103, 215, 20);
		panelDatos.add(textFieldMail);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setBounds(5, 135, 155, 15);
		panelDatos.add(lblFechaDeNacimiento);
		
		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setBackground(Color.WHITE);
		textFieldFechaNacimiento.setEditable(false);
		textFieldFechaNacimiento.setBounds(170, 133, 145, 20);
		panelDatos.add(textFieldFechaNacimiento);

		lblImg = new JLabel();
		lblImg.setBounds(330,0,180,170);
		panelDatos.add(lblImg);
		
		JButton btnVerColaboraciones = new JButton("Ver colaboraciones");
		btnVerColaboraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFormulario()) {
					limpiarFormularioColaboraciones();
					cargarColaboraciones();
					setSize(545, 665);
					btnCerrar.setLocation(215, 600);
					panelDatos.setVisible(false);
					panelColaboraciones.setVisible(true);
				}
			}
		});
		btnVerColaboraciones.setBounds(165, 195, 183, 25);
		panelDatos.add(btnVerColaboraciones);
		
		/* ==================== FIN PANEL DATOS ==================== */
		
		/* ==================== PANEL COLABORACIONES ==================== */
		
		panelColaboraciones = new JPanel();
		panelColaboraciones.setLayout(null);
		panelColaboraciones.setBorder(null);
		panelColaboraciones.setVisible(false);
		panelColaboraciones.setBounds(10, 45, 515, 550);
		getContentPane().add(panelColaboraciones);
		
		lblColaboraciones = new JLabel("Colaboraciones:");
		lblColaboraciones.setBounds(7, 6, 136, 15);
		panelColaboraciones.add(lblColaboraciones);
		
		scrollPaneColabs = new JScrollPane();
		scrollPaneColabs.setBounds(7, 28, 220, 230);
		panelColaboraciones.add(scrollPaneColabs);
		
		listColabs = new JList<Propuesta>();
		listColabs.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listColabs.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		    	Propuesta propSel = listColabs.getSelectedValue();
		    	if (propSel != null) {
			        textFieldTitulo.setText(propSel.getTitulo());
			        textFieldProponente.setText(propSel.getProponente().getNickname());
		            /* == CARGAR IMAGEN ==*/
		            String pathImagenes = Config.getPathImagenes();
					String pathImg = pathImagenes + propSel.getImagen();
		            ImageIcon imgProp = new ImageIcon(pathImg);
					Icon iconoProp = new ImageIcon(imgProp.getImage().getScaledInstance(lblImgProp.getWidth(), lblImgProp.getHeight(), Image.SCALE_DEFAULT));
					lblImgProp.setIcon(iconoProp);
					/* == FIN CARGAR IMAGEN ==*/
		    		textFieldCategoria.setText(propSel.getCategoria().getNombre());
		    		textPaneDesc.setText(propSel.getDescripcion());
		    		textFieldLugar.setText(propSel.getLugar());
		    		/* == CARGAR FECHA == */
		    		Calendar fecha = propSel.getFechaRealizacion(); 
					int dia = fecha.get(Calendar.DAY_OF_MONTH);
					int mes = fecha.get(Calendar.MONTH) +1;
					int anio = fecha.get(Calendar.YEAR);
					String Sdia = Integer.toString(dia);
					String Smes = Integer.toString(mes);
					String Sanio = Integer.toString(anio);
					textFieldFecha.setText(Sdia + " / " + Smes + " / " + Sanio);
					/* == FIN CARGAR FECHA == */
					/* == CARGAR NUMEROS == */
					int precio = propSel.getPrecioEntrada();
					int dineroC = propSel.getMontoActual();
					int dineroN = propSel.getMontoNecesario();
					String Sprecio = Integer.toString(precio);
					String SdineroC = Integer.toString(dineroC);
					String SdineroN = Integer.toString(dineroN);
					textFieldPrecio.setText(Sprecio);
					textFieldDineroC.setText(SdineroC);
					textFieldDineroN.setText(SdineroN);
					/* == FIN CARGAR NUMEROS == */
		        }
		    }
		});
		scrollPaneColabs.setViewportView(listColabs);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(230, 30, 60, 15);
		panelColaboraciones.add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBackground(Color.WHITE);
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setBounds(288, 28, 215, 19);
		panelColaboraciones.add(textFieldTitulo);
		
		lblProponente = new JLabel("Proponente:");
		lblProponente.setBounds(230, 235, 96, 15);
		panelColaboraciones.add(lblProponente);
		
		textFieldProponente = new JTextField();
		textFieldProponente.setBackground(Color.WHITE);
		textFieldProponente.setEditable(false);
		textFieldProponente.setBounds(329, 233, 174, 19);
		panelColaboraciones.add(textFieldProponente);
		
		lblImgProp = new JLabel();
		lblImgProp.setBounds(285, 55, 175,170);
		panelColaboraciones.add(lblImgProp);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(20, 270, 85, 15);
		panelColaboraciones.add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBackground(Color.WHITE);
		textFieldCategoria.setEditable(false);
		textFieldCategoria.setBounds(100, 268, 205, 19);
		panelColaboraciones.add(textFieldCategoria);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(20, 300, 96, 15);
		panelColaboraciones.add(lblDescripcion);
		
		scrollPaneDesc = new JScrollPane();
		scrollPaneDesc.setBounds(20, 320, 485, 69);
		panelColaboraciones.add(scrollPaneDesc);

		textPaneDesc = new JTextPane();
		textPaneDesc.setEditable(false);
		scrollPaneDesc.setViewportView(textPaneDesc);
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(218, 400, 60, 15);
		panelColaboraciones.add(lblLugar);
		
		textFieldLugar = new JTextField();
		textFieldLugar.setBackground(Color.WHITE);
		textFieldLugar.setEditable(false);
		textFieldLugar.setBounds(275, 398, 230, 19);
		panelColaboraciones.add(textFieldLugar);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(20, 400, 62, 15);
		panelColaboraciones.add(lblFecha);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBackground(Color.WHITE);
		textFieldFecha.setEditable(false);
		textFieldFecha.setBounds(78, 398, 124, 19);
		panelColaboraciones.add(textFieldFecha);
		
		JLabel lblNewLabel_2 = new JLabel("Precio de la entrada:");
		lblNewLabel_2.setBounds(20, 430, 152, 15);
		panelColaboraciones.add(lblNewLabel_2);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBackground(Color.WHITE);
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setBounds(175, 428, 135, 19);
		panelColaboraciones.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JLabel lblDineroConseguido = new JLabel("Dinero conseguido:");
		lblDineroConseguido.setBounds(20, 460, 142, 15);
		panelColaboraciones.add(lblDineroConseguido);
		
		textFieldDineroC = new JTextField();
		textFieldDineroC.setBackground(Color.WHITE);
		textFieldDineroC.setEditable(false);
		textFieldDineroC.setBounds(175, 458, 135, 19);
		panelColaboraciones.add(textFieldDineroC);
		textFieldDineroC.setColumns(10);
		
		JLabel lblDineroNecesario = new JLabel("Dinero necesario:");
		lblDineroNecesario.setBounds(20, 490, 131, 15);
		panelColaboraciones.add(lblDineroNecesario);
		
		textFieldDineroN = new JTextField();
		textFieldDineroN.setBackground(Color.WHITE);
		textFieldDineroN.setEditable(false);
		textFieldDineroN.setBounds(175, 488, 135, 19);
		panelColaboraciones.add(textFieldDineroN);
		textFieldDineroN.setColumns(10);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setSize(545, 340);
				btnCerrar.setLocation(215, 275);
				panelColaboraciones.setVisible(false);
				panelDatos.setVisible(true);
			}
		});
		btnAtras.setBounds(205, 520, 100, 25);
		panelColaboraciones.add(btnAtras);
		
		/* ==================== FIN PANEL COLABORACIONES ==================== */
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
            }
        });
		btnCerrar.setBounds(215, 275, 100, 25);
		getContentPane().add(btnCerrar);
		

	}

	public void cargarColaboradores() {
		Vector<Colaborador> model = new Vector<Colaborador>();
		Map<String,Colaborador> colaboradores = (Map<String,Colaborador>)ICUsu.getColaboradores();
		Iterator<Map.Entry<String, Colaborador>> it = colaboradores.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String,Colaborador> e = (Map.Entry<String,Colaborador>)it.next();
		    Colaborador prop = e.getValue();
		    model.add(prop);
		}
		DefaultComboBoxModel<Colaborador> modelo = new DefaultComboBoxModel<Colaborador>(model);
		comboBoxColaboradores.setModel(modelo);
		comboBoxColaboradores.setSelectedIndex(-1);
	}
	
	public void cargarColaboraciones() {
		Map<Integer,Colaboracion> colaboraciones = (HashMap<Integer,Colaboracion>)cmbBoxColSel.getColaboraciones();
		Iterator<Map.Entry<Integer, Colaboracion>> it = colaboraciones.entrySet().iterator();
		DefaultListModel<Propuesta> listModel = new DefaultListModel<Propuesta>();
		while (it.hasNext()) {
			Map.Entry<Integer, Colaboracion> entry = it.next();
			Colaboracion col = entry.getValue();
			Propuesta propuesta = col.getPropuesta();
			listModel.addElement(propuesta);
		}
		listColabs.setModel(listModel);
	}
	
	public void limpiarFormularioDatos() {
		textFieldNick.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldMail.setText("");
		textFieldFechaNacimiento.setText("");
		lblImg.setIcon(null);
	}
	
	public void limpiarFormularioColaboraciones() {
		textFieldTitulo.setText("");
		textFieldProponente.setText("");
		lblImgProp.setIcon(null);
		textFieldCategoria.setText("");
		textPaneDesc.setText("");
		textFieldLugar.setText("");
		textFieldFecha.setText("");
		textFieldPrecio.setText("");
		textFieldDineroC.setText("");
		textFieldDineroN.setText("");
	}
	
	public void limpiarFormulario(){
		cargarColaboradores();
		comboBoxColaboradores.setSelectedItem(-1);
		limpiarFormularioDatos();
		limpiarFormularioColaboraciones();
		setSize(545, 340);
		btnCerrar.setLocation(215, 275);
		panelColaboraciones.setVisible(false);
		panelDatos.setVisible(false);
	}
	
	public boolean checkFormulario() {
		if (comboBoxColaboradores.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Seleccione un colaborador", "Consulta de Perfil de Colaborador",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}
